import javafx.application.Application;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class TrainStation extends Application {

    private int mem=0;

    private static Passenger[] waitingRoom;

    private static PassengerQueue trainQueue;

    public static void main(String[] args){
        trainQueue  = new PassengerQueue();

        // load passenger data from file to waitingRoom
        addPassengersToWaitingRoom();
        launch();
    }

    @Override
    public void start(Stage primaryStage) {

        Scanner sc = new Scanner(System.in);

        menu:
        while (true) {
            System.out.println("Enter \"A\" to add a passenger to the trainQueue");
            System.out.println("Enter \"V\" to view the trainQueue");
            System.out.println("Enter \"D\" to delete passenger from the trainQueue");
            System.out.println("Enter \"S\" to store trainQueue data into a plain text file");
            System.out.println("Enter \"L\" to load data back from the file into the trainQueue");
            System.out.println("Enter \"R\" to run the simulation and produce report");
            System.out.println("Enter \"Q\" to exit");

            System.out.print("Enter your option : ");
            String option = sc.next();

            switch (option) {
                case "A":
                case "a":
                    addToQueue();
                    break;
                case "V":
                case "v":
                    viewTrainQueue();
                    break;
                case "D":
                case "d":
                    deleteFromQueue();
                    break;
                case "S":
                case "s":
                    storeQueueDataToFile();
                    break;
                case "L":
                case "l":
                    loadDataToTrainQueue();
                    break;
                case "R":
                case "r":
                    runSimulationAndGenerateReport();
                    break;
                case "Q":
                case "q":
                    System.out.println("Exit");
                    break menu;
                default:
                    System.out.println("Invalid Input");
            }
        }
    }

    private void addToQueue() {
        Random rand = new Random();
        int rand1 = 1+rand.nextInt(6);
        System.out.println(rand1);
        System.out.println(mem);
        if (waitingRoom.length>mem+rand1){
            for (int i = mem; i < mem+rand1; i++) {
                trainQueue.add(waitingRoom[i]);
            }
        } else {
            System.out.println("Not enough customers or dice rolled more than expect value!\n Try again!");
        }
        mem+=rand1;
        viewTrainQueue();
    }

    private void viewTrainQueue() {
        trainQueue.display();
    }

    private void deleteFromQueue() { trainQueue.remove(); }

    private void storeQueueDataToFile() {
        try {
            FileWriter fw = new FileWriter("trainQueue.txt");
            for (int i = 0; i < trainQueue.getLength(); i++) {
               fw.write(trainQueue.queueArray[i].getName() + " " + trainQueue.queueArray[i].getSeat() + "\n");
            }
            fw.close();
            System.out.println("Successfully saved data to the file");
        } catch (Exception b) {
            System.out.println("Error saving to file" + b);
        }
    }

    private void loadDataToTrainQueue() {
        try {
            Scanner scanner = new Scanner(new File("trainQueue.txt"));
            trainQueue = new PassengerQueue();
            while (scanner.hasNext()) {
                String name = scanner.next();
                int seat = scanner.nextInt();
                System.out.println(name + " " + seat);
                Passenger passenger = new Passenger(){};
                passenger.setName(name, "");
                passenger.setSeat(seat);
                trainQueue.add(passenger);
            }
            System.out.println("Loaded data from file");
        } catch (Exception e) {
            System.out.println("Error - Cannot find a file to load data " + e);
        }
    }

    private void runSimulationAndGenerateReport() {
        addPassengersToWaitingRoom();

        int processingDelay = 0;
        int k = 0;
        int maxQueueLength = 0;
        while (waitingRoom != null || trainQueue.getLength() > 0) {
            // Randomly generate a number using 1 six sided die
            Random randomNumbers = new Random();
            int passengerCount = 1 + randomNumbers.nextInt(6);

            // Move the passengers to trainQueue
            for (int i = 0; i < passengerCount && waitingRoom != null && i < waitingRoom.length; i++) {
                trainQueue.add(waitingRoom[i]);
            }
            System.out.println("Moved " + passengerCount + " to the trainQueue");
            System.out.println("Length of the queue " + trainQueue.getLength());
            if (maxQueueLength < trainQueue.getLength()) maxQueueLength = trainQueue.getLength();

            // Remove passengers from the waiting room
            if (waitingRoom != null &&  passengerCount >= waitingRoom.length) {
                waitingRoom = null;
            } else if (waitingRoom != null) {
                waitingRoom = Arrays.copyOfRange(waitingRoom, passengerCount, waitingRoom.length - 1);
            }

            // Randomly generate a processing delay using 3 six sided dice
            int fullProcessingDelay = processingDelay + 1 + randomNumbers.nextInt(3);

            // Adding delay for all passengers in the queue
            for (int j = k; j < trainQueue.getLength(); j++) {
                trainQueue.queueArray[j].setSecondsInQueue(fullProcessingDelay);
            }

            // Removing the next passenger to board the train
            trainQueue.removeauto();
            k++;
            processingDelay = fullProcessingDelay;
        }
        System.out.println("Simulation ran successfully");
        generateReport(maxQueueLength, k);
    }

    private static void addPassengersToWaitingRoom() {
        try {
            // Get the passenger count using number of lines in the file
            BufferedReader reader = new BufferedReader(new FileReader("E:\\TrainCW2\\CW1_Train\\data.txt"));
            int lines = 0;
            while (reader.readLine() != null) lines++;
            reader.close();

            // Initialize the waitingRoom array with the passenger count
            waitingRoom = new Passenger[lines];

            // Read the file and populate waitingRoom array with passengers
            Scanner scanner = new Scanner(new File("E:\\TrainCW2\\CW1_Train\\data.txt"));
            int count = 0;
            while (scanner.hasNext()) {
                String date = scanner.next();
                String name = scanner.next();
                int seat = scanner.nextInt();
                System.out.println(date + " " + name + " " + seat);
                Passenger passenger = new Passenger();
                passenger.setName(name, "");
                passenger.setSeat(seat);
                waitingRoom[count] = passenger;
                count++;
            }
            System.out.println("Loaded data from file");
        } catch (Exception e) {
            System.out.println("Error - Cannot find a file to load data " + e);
        }
    }

    private void generateReport(int maxQueueLength, int numberOfPassengers) {
        try {
            FileWriter fw = new FileWriter("report.txt", true);
            fw.write("Max Queue Length - " + maxQueueLength + "\n");
            fw.write("Number of passengers - " + numberOfPassengers + "\n");
            fw.close();
            System.out.println("Successfully saved data to the file");
        } catch (Exception b) {
            System.out.println("Error saving to file" + b);
        }
    }

}
