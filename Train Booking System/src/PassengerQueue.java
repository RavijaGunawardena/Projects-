import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.FileWriter;

public class PassengerQueue {

    private static int maxLength = 42;
    public Passenger[] queueArray = new Passenger[maxLength];
    private int first;
    private int last;
    private int maxStayInQueue = 0;
    private int minWaitingTime = 0;
    private int allTimeSpentInQueue = 0;
    private int size;
    private int count = 0;

    // Add a passenger to the last position of the queue
    public void add(Passenger next) throws NullPointerException, IllegalStateException {
        if (size == maxLength)
            throw new IllegalStateException("Queue is full");
        else {
            size++;
            queueArray[last] = next;
            last = (last + 1) % maxLength;
        }
    }

    public void remove() throws IllegalStateException {
        System.out.println(size);
        for (int i=0; i<size; i++){
            Passenger data = queueArray[i];
            if (data != null ) {
                System.out.print(data.getSeat()+",");
            }else {
                continue;
            }
        }
        int min=42;
        int counts=0;
        if (size == 0)
            throw new IllegalStateException("Queue is empty");
        else {
            size--;
            for (int i=0;i<42;i++){
                if (queueArray[i] != null){
                    counts++;
                } else {
                    counts++;
                    continue;
                }
            }
            System.out.println("*"+counts);
            for(int i =0;i<counts;i++ ){
                if (queueArray[i] != null){
                    Passenger data = queueArray[i];
                    System.out.println(data.getSeat());
                    if (data.getSeat()<min){
                        min=data.getSeat();
                    }
                } else {
                    continue;
                }
            }
            for(int i =0;i<counts;i++ ){
                if (queueArray[i] != null){
                    Passenger data = queueArray[(first % maxLength)];
                    System.out.println(data.getSeat());
                    if (data.getSeat()==min){
                        queueArray[first] = null;
                        break;
                    }else {
                        first = (first + 1) % maxLength;
                        continue;
                    }
                } else {
                    first = (first + 1) % maxLength;
                    continue;
                }
            }
            first=0;
            System.out.println("Successfully deleted a passenger from the queue");
            count++;
        }
    }

    private static int getMinSeatPassenger(Passenger[] passengers) {
        int minValue = passengers[0].getSeat();
        int index = 0;
        for (int i = 1; i < passengers.length; i++) {
            if (passengers[i].getSeat() < minValue) {
                index = i;
            }
        }
        return index;
    }

    public boolean isEmpty(){
        return (size == 0);
    }

    public boolean isFull(){
        return (size == maxLength);
    }

    public void display() {
        FlowPane flowPane = createFlowPane();
        flowPane.setHgap(10.0);
        flowPane.setVgap(10.0);

        for (int i = 1; i <= maxLength; i++) {
            Button button = new Button("Seat " + i);
            button.setId(Integer.toString(i));
            flowPane.getChildren().add(button);
            button.setStyle("-fx-background-color: blue; -fx-text-fill: white");
            button.setPrefHeight(20.0);
            button.setPrefWidth(120.0);

            for (int j = 0; j < queueArray.length; j++) {
                if (queueArray[j] != null && queueArray[j].getSeat() == i) {
                    flowPane.getChildren().remove(button);
                    button = new Button(i + " " + queueArray[j].getName());
                    flowPane.getChildren().add(button);
                    button.setStyle("-fx-background-color: red; -fx-text-fill: white");
                    button.setPrefHeight(20.0);
                    button.setPrefWidth(120.0);
                }
            }
        }
        Stage primaryStage = new Stage();
        primaryStage.setTitle("View All Seats");
        showStage(primaryStage, flowPane);
    }

    public int getLength(){
        return size;
    }

    public int getMaxStay(){
        return maxStayInQueue;
    }

    private FlowPane createFlowPane() {
        FlowPane flowPane = new FlowPane();
        flowPane.setHgap(20);
        flowPane.setVgap(20);
        flowPane.setOrientation(Orientation.HORIZONTAL);
        flowPane.setAlignment(Pos.CENTER);
        return flowPane;
    }

    private void showStage(Stage primaryStage, FlowPane flowPane) {
        Scene scene = new Scene(flowPane, 500, 600);
        primaryStage.setScene(scene);
        primaryStage.showAndWait();
    }

    public void removeauto() throws IllegalStateException {
        if (size == 0)
            throw new IllegalStateException("Queue is empty");
        else {
            size--;
            Passenger data = queueArray[(first % maxLength)];
            queueArray[first] = null;
            first = (first + 1) % maxLength;

            System.out.println("Successfully deleted a passenger from the queue");
            System.out.println(data.getName() + " waited " + data.getSeconds() + " seconds in the queue");

            // Get maximum and minimum seconds stayed in queue
            if (maxStayInQueue < data.getSeconds()) maxStayInQueue = data.getSeconds();
            if (minWaitingTime >= data.getSeconds()) minWaitingTime = data.getSeconds();

            // Get average time spent in queue
            allTimeSpentInQueue = allTimeSpentInQueue + data.getSeconds();
            count++;
        }

        Stage primaryStage =new Stage();
        primaryStage.setTitle("Report");
        AnchorPane anchorPane = new AnchorPane();

        Label heading=new Label("Train Queue Report");
        heading.setStyle("-fx-background-color:#EA2ADE ; -fx-text-fill: white;-fx-font-weight: bolder;-fx-font-size: 40;");
        heading.setAlignment(Pos.CENTER);
        AnchorPane.setLeftAnchor(heading, 60.0);
        AnchorPane.setRightAnchor(heading, 60.0);
        anchorPane.getChildren().add(heading);

        Label item1=new Label("01. Maximum length - " + count);
        AnchorPane.setTopAnchor(item1,80.0);
        anchorPane.getChildren().add(item1);

        Label item2=new Label("02. Maximum time - " + maxStayInQueue);
        AnchorPane.setTopAnchor(item2,110.0);
        anchorPane.getChildren().add(item2);

        Label item3=new Label("03. Minimum time - " + minWaitingTime);
        AnchorPane.setTopAnchor(item3,140.0);
        anchorPane.getChildren().add(item3);

        Label item4=new Label("04. Average time - " + (allTimeSpentInQueue/count));
        AnchorPane.setTopAnchor(item4,170.0);
        anchorPane.getChildren().add(item4);

        anchorPane.setStyle("-fx-text-fill: black;-fx-font-weight: bolder;-fx-font-size: 25;-fx-background-color:#1EDFD2  " +
                " ");
        Scene scene = new Scene(anchorPane, 1000, 750);
        primaryStage.setScene(scene);
        primaryStage.showAndWait();
        generateTheReport(maxStayInQueue, minWaitingTime, allTimeSpentInQueue/count);
    }

    private void generateTheReport(int maxStayInQueue, int minWaitingTime, int averageTimeSpentInQueue) {
        System.out.println("Max Stay in Queue is " + maxStayInQueue + " seconds");
        try {
            FileWriter fw = new FileWriter("report.txt");
            fw.write("Max stay in queue - " + maxStayInQueue + "\n");
            fw.write("Min stay in queue - " + minWaitingTime + "\n");
            fw.write("Average time spent on queue - " + averageTimeSpentInQueue + "\n");
            fw.close();
            System.out.println("Successfully saved data to the file");
        } catch (Exception b) {
            System.out.println("Error saving to file" + b);
        }
    }
}
