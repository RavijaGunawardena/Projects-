import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class Main extends Application {
    static final int SEATING_CAPACITY = 42;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        HashMap<String, HashMap> customerDetailsByDateMap = new HashMap<String, HashMap>();
        Scanner sc = new Scanner(System.in);

        menu:
        while (true) {
            System.out.println("Enter \"A\" to add a new customer");
            System.out.println("Enter \"V\" to view all seats");
            System.out.println("Enter \"E\" to view empty seats");
            System.out.println("Enter \"D\" to delete booked seat");
            System.out.println("Enter \"F\" to find the seat for a customer");
            System.out.println("Enter \"S\" to save booking data to a file");
            System.out.println("Enter \"L\" to load booking data from the file");
            System.out.println("Enter \"O\" to sorting");
            System.out.println("Enter \"Q\" to exit");

            System.out.print("Enter your option : ");
            String option = sc.next();

            switch (option) {
                case "A":
                case "a":
                    addNewCustomer(customerDetailsByDateMap);
                    break;
                case "V":
                case "v":
                    viewAllSeats(customerDetailsByDateMap);
                    break;
                case "E":
                case "e":
                    viewEmptySeats(customerDetailsByDateMap);
                    break;
                case "D":
                case "d":
                    deleteBookedSeats(customerDetailsByDateMap);
                    break;
                case "F":
                case "f":
                    findSeatByName(customerDetailsByDateMap);
                    break;
                case "S":
                case "s":
                    saveToFile(customerDetailsByDateMap);
                    break;
                case "L":
                case "l":
                    loadFromFile(customerDetailsByDateMap);
                    break;
                case "O":
                case "o":
                    sortByName(customerDetailsByDateMap);
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

    private void addNewCustomer(HashMap<String, HashMap> customerDetailsByDateMap) {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Add New Customer");
        FlowPane flowPane = createFlowPane();
        flowPane.setHgap(10.0);
        flowPane.setVgap(10.0);


        Scanner date = new Scanner(System.in);
        System.out.println("Please select a date (ex: 2020-03-20): ");
        String selectedDate = date.next();

        HashMap customerDetailsMap = new HashMap<String, Integer>();
        if (customerDetailsByDateMap.get(selectedDate) != null) {
            customerDetailsMap = customerDetailsByDateMap.get(selectedDate);
        }
        final HashMap customerDetails = customerDetailsMap;

        for (int i = 1; i <= SEATING_CAPACITY; i++) {
            Button button = new Button("Seat " + i);
            button.setId(Integer.toString(i));
            button.setStyle("-fx-background-color: blue; -fx-text-fill: white");
            button.setPrefHeight(20.0);
            button.setPrefWidth(120.0);
            flowPane.getChildren().add(button);

            // Disable already booked seats
            if (customerDetailsMap.containsValue(Integer.parseInt(button.getId()))) {
                button.setDisable(true);
                button.setStyle("-fx-background-color: red; -fx-text-fill: white");
            } else {
                // Open dialog box when a button is clicked
                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        button.setStyle("-fx-background-color: red; -fx-text-fill: white");
                        Stage dialogStage = new Stage();
                        dialogStage.initModality(Modality.WINDOW_MODAL);

                        Button submit = new Button("Confirm Booking");
                        TextField name = new TextField();
                        VBox vbox = new VBox(new Text("Name"), name, submit);
                        vbox.setSpacing(20);
                        vbox.setAlignment(Pos.CENTER);
                        vbox.setPadding(new Insets(15));

                        submit.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent e) {
                                // Save data to the HashMap
                                customerDetails.put(name.getText(), Integer.parseInt(button.getId()));
                                customerDetailsByDateMap.put(selectedDate, customerDetails);
                                System.out.println(name.getText() + " you have successfully booked a seat " + button.getId());
                                dialogStage.close();
                                primaryStage.close();
                            }
                        });
                        dialogStage.setScene(new Scene(vbox, 200, 200));
                        dialogStage.showAndWait();
                    }
                });
            }
        }
        showStage(primaryStage, flowPane);
    }

    private void viewEmptySeats(HashMap<String, HashMap> customerDetailsByDateMap) {
        FlowPane flowPane = createFlowPane();
        flowPane.setHgap(10.0);
        flowPane.setVgap(10.0);
        flowPane.setStyle("-fx-padding: 4;");

        final HashMap customerDetails = getCustomerDetailsByDate(customerDetailsByDateMap);

        for (int i = 1; i <= SEATING_CAPACITY; i++) {
            Button button = new Button("Seat " + i);
            button.setId(Integer.toString(i));
            flowPane.getChildren().add(button);
            button.setStyle("-fx-background-color: blue; -fx-text-fill: white");
            button.setPrefHeight(20.0);
            button.setPrefWidth(120.0);

            // Hide button
            if (customerDetails.containsValue(Integer.parseInt(button.getId()))) {
                button.setVisible(false);
            }
        }
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Empty Seats");
        showStage(primaryStage, flowPane);
    }

    private void viewAllSeats(HashMap<String, HashMap> customerDetailsByDateMap) {
        FlowPane flowPane = createFlowPane();
        flowPane.setHgap(10.0);
        flowPane.setVgap(10.0);

        final HashMap customerDetails = getCustomerDetailsByDate(customerDetailsByDateMap);

        for (int i = 1; i <= SEATING_CAPACITY; i++) {
            Button button = new Button("Seat " + i);
            button.setId(Integer.toString(i));
            flowPane.getChildren().add(button);
            button.setStyle("-fx-background-color: blue; -fx-text-fill: white");
            button.setPrefHeight(20.0);
            button.setPrefWidth(120.0);

            if (customerDetails.containsValue(Integer.parseInt(button.getId()))) {
                button.setText("Booked");
                button.setStyle("-fx-background-color: red; -fx-text-fill: white");
            }
        }
        Stage primaryStage = new Stage();
        primaryStage.setTitle("View All Seats");
        showStage(primaryStage, flowPane);
    }

    private void deleteBookedSeats(HashMap<String, HashMap> customerDetailsByDateMap) {
        final HashMap customerDetails = getCustomerDetailsByDate(customerDetailsByDateMap);

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your name to cancel the booking : ");
        String name = sc.next();
        if (customerDetails.containsKey(name)) {
            customerDetails.remove(name);
            System.out.println("Successfully cancelled the booking");
        } else {
            System.out.println("Cannot find the customer " + name);
        }
    }

    private void findSeatByName(HashMap<String, HashMap> customerDetailsByDateMap) {
        final HashMap customerDetails = getCustomerDetailsByDate(customerDetailsByDateMap);

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your name to find the seat : ");
        String name = sc.next();
        if (customerDetails.containsKey(name)) {
            System.out.println("Your seat number is " + customerDetails.get(name));
        } else {
            System.out.println("Cannot find a seat for the customer " + name);
        }
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

    private void sortByName(HashMap<String, HashMap> customerDetailsByDateMap) {
        final HashMap customerDetails = getCustomerDetailsByDate(customerDetailsByDateMap);

        ArrayList<String> arr = new ArrayList<>();
        arr.addAll(customerDetails.keySet());

        // bubble sort implementation
        for (int j = 0; j < arr.size(); j++) {
            for (int i = j + 1; i < arr.size(); i++) {
                // swap if j is greater than i
                if ((arr.get(i)).compareToIgnoreCase(arr.get(j)) < 0) {
                    String temp = arr.get(j);
                    arr.set(j, arr.get(i));
                    arr.set(i, temp);
                }
            }
            System.out.println(arr.get(j) + " " + customerDetails.get(arr.get(j)));
        }
        System.out.println("Sorted the bookings by customer name");
    }

    private void loadFromFile(HashMap<String, HashMap> customerDetailsByDateMap) {
        try {
            Scanner scanner = new Scanner(new File("data.txt"));
            while (scanner.hasNext()) {
                String date = scanner.next();
                String name = scanner.next();
                int seat = scanner.nextInt();
                System.out.println(date + " " + name + " " + seat);
                HashMap<String, Integer> customerDetailsMap = new HashMap<String, Integer>();
                if (customerDetailsByDateMap.get(date) != null) {
                    customerDetailsMap = customerDetailsByDateMap.get(date);
                }
                customerDetailsMap.put(name, seat);
                customerDetailsByDateMap.put(date, customerDetailsMap);
            }
            System.out.println("Loaded data from file");
        } catch (Exception e) {
            System.out.println("Error - Cannot find a file to load data " + e);
        }
    }

    private void saveToFile(HashMap<String, HashMap> customerDetailsByDateMap) {
        try {
            FileWriter fw = new FileWriter("data.txt");
            for (Object date : customerDetailsByDateMap.keySet()) {
                for (Object details : customerDetailsByDateMap.get(date).keySet()) {
                    fw.write(date + " " + details + " " + customerDetailsByDateMap.get(date).get(details) + "\n");
                }
            }
            fw.close();
            System.out.println("Successfully saved data to the file");
        } catch (Exception b) {
            System.out.println("Error saving to file" + b);
        }
    }

    private HashMap getCustomerDetailsByDate(HashMap<String, HashMap> customerDetailsByDateMap) {
        Scanner date = new Scanner(System.in);
        System.out.println("Please select a date (ex: 2020-03-20): ");
        String selectedDate = date.next();

        HashMap customerDetailsMap = new HashMap<String, Integer>();
        if (customerDetailsByDateMap.get(selectedDate) != null) {
            customerDetailsMap = customerDetailsByDateMap.get(selectedDate);
        }
        return customerDetailsMap;
    }
}
