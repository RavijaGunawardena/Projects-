package PremierLeague;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import netscape.javascript.JSUtil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import static javafx.application.Application.launch;

public class ConsoleApplication extends Application {
//Creates Varibles
    static Label valueChange;
    static Label team1Details;
    static Label team2Details;

    static Label team1Wins;
    static Label team1Draws;
    static Label team1Points;
    static Label team1Scores;
    static Label team1MatchsPlayed;
    static Label team1Defeats;
    static Label team1ReceivdGoals;

    static Label team2Wins;
    static Label team2Draws;
    static Label team2Points;
    static Label team2Scores;
    static Label team2MatchsPlayed;
    static Label team2Defeats;
    static Label team2ReceivdGoals;
    static Label playedDate;

    static Button playMatch;
    static Stage stage = new Stage();
    static Stage stage1 = new Stage();
    static Stage stage2 = new Stage();
    static Stage stage3 = new Stage();
    static Stage stage4 = new Stage();
    static Stage stage5 = new Stage();
    static Stage stage6 = new Stage();
    static Stage stage7 = new Stage();
    static Stage stage8 = new Stage();

    static TextField textField;

    public static final ArrayList<RandomMatch> change = new ArrayList<>();

    public static void main(String[] args) {
        launch();
    }

    static final Scanner USER_INPUT = new Scanner(System.in);
    static LeagueManager manager = new PremierLeagueManager();

    @Override
    public void start(Stage stage) throws IOException {

        manager.loading();
        manager.loadingRandom();
        manager.loadingPlayedMatch();
        //Console menu
        System.out.println("<------------------------------Premier League Manager-------------------------------->");
        System.out.println(" ");
        menu:
        while (true) {
            System.out.println("1: \tCreate a new football club :");
            System.out.println("2: \tDelete an existing club :");
            System.out.println("3: \tDisplay clubs statistics :");
            System.out.println("4: \tDisplay the Premier League Table :");
            System.out.println("5: \tAdd a played match with its score and its date :");
            System.out.println("6: \tSave in a file :");
            System.out.println("7: \tDisplay Graphic Interface :");
            System.out.println("8: \tExit");

            String choice = USER_INPUT.next();

            switch (choice) {
                case "1":
                    addClub();
                    break;
                case "2":
                    deleteClub();
                    break;
                case "3":
                    manager.printStatistics();
                    break;
                case "4":
                    manager.displayPremierLeagueTable();
                    break;
                case "5":
                    manager.addMatch();
                    break;
                case "6":
                    save();
                    break;
                case "7":
                    loadGui();
                case "8":
                    System.out.println("Thank you for using our system ");
                    break menu;
                default:
                    System.out.println("Please enter a valid choice");

            }

        }

    }
    //To add a club to Premier League
    public static void addClub() {
        FootballClub sport;

        System.out.println("Enter the name of the club :");
        String clubName = USER_INPUT.next();
        System.out.println("Enter the location of the club :");
        String clubLocation = USER_INPUT.next();
        System.out.println("Enter the Id of the club :");
        String clubId = USER_INPUT.next();
        System.out.print("To add more details press 'Y' or press 'N' to if you wish to add later ");
        String details = USER_INPUT.next();
        //Additional details for club
        if (details.equalsIgnoreCase("Y")) {
            try {
                System.out.print("How many wins in the season? : ");
                int preWins = USER_INPUT.nextInt();
                System.out.print("How many draws in the season? : ");
                int preDraws = USER_INPUT.nextInt();
                System.out.print("How many defeats in the season? : ");
                int preDefeats = USER_INPUT.nextInt();
                System.out.print("How many goals received in the season? : ");
                int preGoals = USER_INPUT.nextInt();
                System.out.print("How many number of points club has? : ");
                int prePoints = USER_INPUT.nextInt();
                System.out.print("How many matches played in the season? : ");
                int preMatches = USER_INPUT.nextInt();
                System.out.print("How many goals scored in the season? : ");
                int preScored = USER_INPUT.nextInt();
                sport = new FootballClub(preWins, preDraws, preDefeats, preGoals, prePoints, clubName, clubLocation,
                        clubId, preMatches, preScored);
                manager.addClub(sport);
            } catch (InputMismatchException e) {
                System.out.println("invalid input. Please enter only integer values !");
                addClub();

            }

        } else if (details.equalsIgnoreCase("N")) {
            sport = new FootballClub(clubName, clubLocation, clubId);
            manager.addClub(sport);

        } else {
            System.out.println("Invalid input !");
            addClub();
        }
    }
    //To delete clubs from Premier League
    public static void deleteClub() {
        manager.clubsDisplay();
        System.out.println(" ");
        System.out.println("Enter Club Name to delete the Club : ");
        String name = USER_INPUT.next();
        System.out.println("Warning! Your going to delete the club " + name + " press Y to continue or N to abort");
        String deleteChoice = USER_INPUT.next();
        if (deleteChoice.equalsIgnoreCase("y")) {
            manager.deleteClub(name);
        } else if (deleteChoice.equalsIgnoreCase("N")) {
            System.out.println("You have abort the option\n");
            return;
        } else {
            System.out.println("Invalid choice. Please try again !\n");
            deleteClub();
        }
    }
    //Save all user inpput data to files
    public static void save() throws IOException {
        manager.save(PremierLeagueManager.footBallClub);
        manager.saveRandom(change);
        manager.savePlayedMatch(PremierLeagueManager.playedMatchList);
    }
    //To set background image for every flowpane
    public static void backGroundImage(FlowPane flowPane) throws FileNotFoundException {
        Image backgroundColor = new Image("1637809.jpg");
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true,
                true, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(backgroundColor, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);

        flowPane.setBackground(new Background(backgroundImage));

    }
    //Load main gui application
    public static void loadGui() throws FileNotFoundException {
        FlowPane flowPane = new FlowPane();
        stage.setTitle("Premier League Manager");

        flowPane.setHgap(10.0);
        flowPane.setVgap(10.0);

        StackPane pane = new StackPane();

        backGroundImage(flowPane);

        //Label

        Label heading = new Label(" Premier League 2020 ");
        heading.setStyle("-fx-font-size: 35; -fx-text-fill: #000000; -fx-font-weight: bold;-fx-background-color: #e9e6e6;");
        heading.setPrefWidth(368);
        FlowPane.setMargin(heading, new Insets(50, 120.0, 0, 146.0));

        //Button options

        Button option01 = new Button();
        option01.setText("Premier League Table");
        option01.setStyle("-fx-font-size: 15; -fx-text-fill: #fff; -fx-font-weight: bold; -fx-background-color: #6eaee8; ");
        option01.setPrefWidth(250.5);
        option01.setPrefHeight(20.0);
        FlowPane.setMargin(option01, new Insets(30, 200.0, 0, 200.0));

        Button option02 = new Button();
        option02.setText("Search From Date");
        option02.setStyle("-fx-font-size: 15; -fx-text-fill: #fff; -fx-font-weight: bold; -fx-background-color: #6eaee8;");
        option02.setPrefWidth(250.5);
        option02.setPrefHeight(20.0);
        FlowPane.setMargin(option02, new Insets(10, 200.0, 0, 200.0));

        Button option03 = new Button();
        option03.setText("All Playeed Matches");
        option03.setStyle("-fx-font-size: 15; -fx-text-fill: #fff; -fx-font-weight: bold; -fx-background-color: #6eaee8;");
        option03.setPrefWidth(250.5);
        option03.setPrefHeight(20.0);
        FlowPane.setMargin(option03, new Insets(10, 200.0, 0, 200.0));

        Button option04 = new Button();
        option04.setText("Randomly play Match");
        option04.setStyle("-fx-font-size: 15; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-background-color: #6eaee8;");
        option04.setPrefWidth(250.5);
        option04.setPrefHeight(20.0);
        FlowPane.setMargin(option04, new Insets(10, 200.0, 0, 200.0));

        flowPane.getChildren().addAll(pane, heading, option01, option02, option03, option04);

        //Option loads for other member types

        option01.setOnAction(event -> {
            try {
                stage.close();
                displayPointTable();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        option02.setOnAction(event -> {
            searchFromDate();
        });

        option03.setOnAction(event -> {
            try {
                stage.close();
                allRanMatchPlayed();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        option04.setOnAction(event -> {
            try {
                stage.close();
                stageRandomMatch();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Scene scene = new Scene(flowPane, 650, 650);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }
    //Labels for first stage
    public static void labelDetails(FlowPane flowPane) {
        Label heading1 = new Label("Club Name");
        heading1.setStyle("-fx-font-size: 11; -fx-text-fill: #6eaee8; -fx-font-weight: bold; -fx-background-color: #111;");
        heading1.setPrefWidth(100.0);
        heading1.setAlignment(Pos.CENTER);
        FlowPane.setMargin(heading1, new Insets(10, 0, 0, 15.0));

        Label heading2 = new Label("Points");
        heading2.setStyle("-fx-font-size: 11; -fx-text-fill: #6eaee8; -fx-font-weight: bold; -fx-background-color: #111;");
        heading2.setPrefWidth(100.0);
        heading2.setAlignment(Pos.CENTER);
        FlowPane.setMargin(heading2, new Insets(10, 0, 0, 24.0));

        Label heading3 = new Label("Wins");
        heading3.setStyle("-fx-font-size: 11; -fx-text-fill: #6eaee8; -fx-font-weight: bold; -fx-background-color: #111; -fx-text-align: center;");
        heading3.setPrefWidth(100.0);
        heading3.setAlignment(Pos.CENTER);
        FlowPane.setMargin(heading3, new Insets(10, 0, 0, 24.0));

        Label heading4 = new Label("Recieved Goals");
        heading4.setStyle("-fx-font-size: 11; -fx-text-fill: #6eaee8; -fx-font-weight: bold; -fx-background-color: #111;");
        heading4.setPrefWidth(100.0);
        heading4.setAlignment(Pos.CENTER);
        FlowPane.setMargin(heading4, new Insets(10, 0, 0, 24.0));

        Label heading5 = new Label("Defeats");
        heading5.setStyle("-fx-font-size: 11; -fx-text-fill: #6eaee8; -fx-font-weight: bold; -fx-background-color: #111; -fx-text-align: center;");
        heading5.setPrefWidth(100.0);
        heading5.setAlignment(Pos.CENTER);
        FlowPane.setMargin(heading5, new Insets(10, 0, 0, 24.0));

        Label heading6 = new Label("Matches Played");
        heading6.setStyle("-fx-font-size: 11; -fx-text-fill: #6eaee8; -fx-font-weight: bold; -fx-background-color: #111;");
        heading6.setPrefWidth(100.0);
        heading6.setAlignment(Pos.CENTER);
        FlowPane.setMargin(heading6, new Insets(10, 0, 0, 24.0));

        Label heading7 = new Label("Score Goals");
        heading7.setStyle("-fx-font-size: 11; -fx-text-fill: #6eaee8; -fx-font-weight: bold; -fx-background-color: #111; -fx-text-align: center;");
        heading7.setPrefWidth(100.0);
        heading7.setAlignment(Pos.CENTER);
        FlowPane.setMargin(heading7, new Insets(10, 0, 0, 24.0));

        Label heading8 = new Label("Draws");
        heading8.setStyle("-fx-font-size: 11; -fx-text-fill: #6eaee8; -fx-font-weight: bold; -fx-background-color: #111;");
        heading8.setPrefWidth(100.0);
        heading8.setAlignment(Pos.CENTER);
        FlowPane.setMargin(heading8, new Insets(10, 0, 0, 24.0));

        flowPane.getChildren().addAll(heading1, heading2, heading3, heading4, heading5, heading6, heading7, heading8);

    }
    //Labels for every stages after sorting
    public static void sortDetails(FlowPane flowPane) {
        for (FootballClub entry : PremierLeagueManager.footBallClub) {

            Label labelName = new Label(entry.getClubName());
            Label labelPoints = new Label(String.valueOf(entry.getPoints()));
            Label lebelWins = new Label(String.valueOf(entry.getWins()));
            Label labelGoals = new Label(String.valueOf(entry.getGoals()));
            Label lebelDefeats = new Label(String.valueOf(entry.getDefeats()));
            Label labelMatches = new Label(String.valueOf(entry.getMatches()));
            Label lebelScore = new Label(String.valueOf(entry.getScored()));
            Label lebelDraws = new Label(String.valueOf(entry.getDraws()));

            labelName.setStyle("-fx-font-size: 15; -fx-text-fill: #111; -fx-font-weight: bold; -fx-background-color: red;");
            labelName.setPrefWidth(100.0);
            labelName.setAlignment(Pos.CENTER);
            FlowPane.setMargin(labelName, new Insets(0, 0, 0, 15.0));

            labelPoints.setStyle("-fx-font-size: 15; -fx-text-fill: #111; -fx-font-weight: bold; -fx-background-color: red;");
            labelPoints.setPrefWidth(100.0);
            labelPoints.setAlignment(Pos.CENTER);
            FlowPane.setMargin(labelPoints, new Insets(0, 0, 0, 24.0));

            lebelWins.setStyle("-fx-font-size: 15; -fx-text-fill: #111; -fx-font-weight: bold; -fx-background-color: red;");
            lebelWins.setPrefWidth(100.0);
            lebelWins.setAlignment(Pos.CENTER);
            FlowPane.setMargin(lebelWins, new Insets(0, 0, 0, 24.0));

            labelGoals.setStyle("-fx-font-size: 15; -fx-text-fill: #111; -fx-font-weight: bold; -fx-background-color: red;");
            labelGoals.setPrefWidth(100.0);
            labelGoals.setAlignment(Pos.CENTER);
            FlowPane.setMargin(labelGoals, new Insets(0, 0, 0, 24.0));

            lebelDefeats.setStyle("-fx-font-size: 15; -fx-text-fill: #111; -fx-font-weight: bold; -fx-background-color: red;");
            lebelDefeats.setPrefWidth(100.0);
            lebelDefeats.setAlignment(Pos.CENTER);
            FlowPane.setMargin(lebelDefeats, new Insets(0, 0, 0, 24.0));

            labelMatches.setStyle("-fx-font-size: 15; -fx-text-fill: #111; -fx-font-weight: bold; -fx-background-color: red;");
            labelMatches.setPrefWidth(100.0);
            labelMatches.setAlignment(Pos.CENTER);
            FlowPane.setMargin(labelMatches, new Insets(0, 0, 0, 24.0));

            lebelScore.setStyle("-fx-font-size: 15; -fx-text-fill: #111; -fx-font-weight: bold; -fx-background-color: red;");
            lebelScore.setPrefWidth(100.0);
            lebelScore.setAlignment(Pos.CENTER);
            FlowPane.setMargin(lebelScore, new Insets(0, 0, 0, 24.0));

            lebelDraws.setStyle("-fx-font-size: 15; -fx-text-fill: #111; -fx-font-weight: bold; -fx-background-color: #ff0000;");
            lebelDraws.setPrefWidth(100.0);
            lebelDraws.setAlignment(Pos.CENTER);
            FlowPane.setMargin(lebelDraws, new Insets(0, 0, 0, 24.0));

            flowPane.getChildren().addAll(labelName, labelPoints, lebelWins, labelGoals, lebelDefeats, labelMatches,
                    lebelScore, lebelDraws);

        }

    }
    //Display sorted points table
    public static void displayPointTable() throws FileNotFoundException {
        stage1 = new Stage();
        FlowPane flowPane = new FlowPane();
        stage1.setTitle("Premier League Table");

        flowPane.setHgap(5.0);
        flowPane.setVgap(5.0);
        flowPane.setOrientation(Orientation.HORIZONTAL);

        backGroundImage(flowPane);

        Button sortWins = new Button();
        sortWins.setText("Sort According To Wins");
        sortWins.setStyle("-fx-font-size: 15; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-background-color: #6eaee8; -fx-cursor: hand;");
        sortWins.setPrefWidth(200.0);
        sortWins.setPrefHeight(18.0);
        FlowPane.setMargin(sortWins, new Insets(12, 110.0, 0, 15.0));

        flowPane.getChildren().add(sortWins);

        sortWins.setOnAction(event -> {
            stage1.close();
            try {
                winsSort();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });


        Button backToHome = new Button();
        backToHome.setText("Baack To Home");
        backToHome.setStyle("-fx-font-size: 15; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-background-color: #6eaee8; -fx-cursor: hand;");
        backToHome.setPrefWidth(150.0);
        backToHome.setPrefHeight(20.0);
        FlowPane.setMargin(backToHome, new Insets(12, 0.0, 0, 537));

        flowPane.getChildren().add(backToHome);

        labelDetails(flowPane);

        PremierLeagueManager.footBallClub.sort(new PointCompare());
        sortDetails(flowPane);

        backToHome.setOnAction(event -> {
                    stage1.close();
                    stage.show();
                }
        );

        Scene scene = new Scene(flowPane, 1040, 600);
        stage1.setResizable(false);
        stage1.setScene(scene);
        stage1.show();
    }
    //Display sorted wins table
    public static void winsSort() throws FileNotFoundException {
        stage2 = new Stage();
        FlowPane flowPane = new FlowPane();
        stage2.setTitle("Sorting According To Wins");

        flowPane.setHgap(5.0);
        flowPane.setVgap(5.0);
        flowPane.setOrientation(Orientation.HORIZONTAL);

        backGroundImage(flowPane);

        Button sortWins = new Button();
        sortWins.setText("Sort According To Goals");
        sortWins.setStyle("-fx-font-size: 15; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-background-color: #6eaee8; -fx-cursor: hand;");
        sortWins.setPrefWidth(220.0);
        sortWins.setPrefHeight(20.0);
        FlowPane.setMargin(sortWins, new Insets(12, 110.0, 0, 15.0));

        flowPane.getChildren().add(sortWins);

        sortWins.setOnAction(event -> {
                    stage2.close();
                    try {
                        goalSort();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
        );

        Button backToHome = new Button();
        backToHome.setText("Baack To Home");
        backToHome.setStyle("-fx-font-size: 15; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-background-color: #6eaee8; -fx-cursor: hand;");
        backToHome.setPrefWidth(150.0);
        backToHome.setPrefHeight(20.0);
        FlowPane.setMargin(backToHome, new Insets(12, 0.0, 0, 520));

        flowPane.getChildren().add(backToHome);

        labelDetails(flowPane);

        PremierLeagueManager.footBallClub.sort(new WinsCompare());
        sortDetails(flowPane);


        backToHome.setOnAction(event -> {
                    stage2.close();
                    stage.show();
                }
        );

        Scene scene = new Scene(flowPane, 1040, 600);
        stage2.setResizable(false);
        stage2.setScene(scene);
        stage2.show();
    }
    //Display sorted goals able
    public static void goalSort() throws FileNotFoundException {
        stage3 = new Stage();
        FlowPane flowPane = new FlowPane();
        stage3.setTitle("Premier League Table");

        flowPane.setHgap(5.0);
        flowPane.setVgap(5.0);
        flowPane.setOrientation(Orientation.HORIZONTAL);

        backGroundImage(flowPane);

        Button sortWins = new Button();
        sortWins.setText("Sort According To Wins");
        sortWins.setStyle("-fx-font-size: 15; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-background-color: #6eaee8; -fx-cursor: hand;");
        sortWins.setPrefWidth(220.0);
        sortWins.setPrefHeight(18.0);
        FlowPane.setMargin(sortWins, new Insets(12, 110.0, 0, 15.0));

        flowPane.getChildren().add(sortWins);

        sortWins.setOnAction(event -> {
                    stage3.close();
                    try {
                        winsSort();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
        );

        Button backToHome = new Button();
        backToHome.setText("Baack To Home");
        backToHome.setStyle("-fx-font-size: 15; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-background-color: #6eaee8; -fx-cursor: hand;");
        backToHome.setPrefWidth(150.0);
        backToHome.setPrefHeight(18.0);
        FlowPane.setMargin(backToHome, new Insets(12, 0.0, 0, 520));

        flowPane.getChildren().add(backToHome);

        labelDetails(flowPane);

        PremierLeagueManager.footBallClub.sort(new ScoreCompare());
        sortDetails(flowPane);


        backToHome.setOnAction(event -> {
                    stage3.close();
                    stage.show();
                }
        );

        Scene scene = new Scene(flowPane, 1040, 600);
        stage3.setResizable(false);
        stage3.setScene(scene);
        stage3.show();
    }
    //Stage for play random match
    public static void stageRandomMatch() throws IOException {
        stage4 = new Stage();
        FlowPane flowPane = new FlowPane();
        stage4.setTitle("Play Random Match");

        Image backgroundColor = new Image("690807.jpg");
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true,
                true, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(backgroundColor, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);

        flowPane.setBackground(new Background(backgroundImage));

        flowPane.setHgap(5.0);
        flowPane.setVgap(5.0);
        flowPane.setOrientation(Orientation.HORIZONTAL);

        Button backToHome = new Button();
        backToHome.setText("Back To Home");
        backToHome.setStyle("-fx-font-size: 15; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-background-color: #6eaee8; -fx-cursor: hand;");
        backToHome.setPrefWidth(150.0);
        backToHome.setPrefHeight(18.0);
        FlowPane.setMargin(backToHome, new Insets(12, 0, 0, 855));

        backToHome.setOnAction(event -> {
                    stage4.close();
                    stage.show();
                }
        );

        playMatch = new Button();
        playMatch.setText("Play a Random Match");
        playMatch.setStyle("-fx-font-size: 18; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-background-color: #f30000; -fx-cursor: hand;");
        playMatch.setPrefWidth(220.0);
        playMatch.setPrefHeight(48.0);
        FlowPane.setMargin(playMatch, new Insets(0, 0, 0, 425));

        flowPane.getChildren().addAll(backToHome, playMatch);

        playMatch.setOnAction(event -> {
                    try {
                        randomMatch(flowPane);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );

        Scene scene = new Scene(flowPane, 1040, 620);
        stage4.setResizable(false);
        stage4.setScene(scene);
        stage4.show();

    }
    //Labels and methods that used to play random matches
    public static void randomMatch(FlowPane flowPane) throws IOException {
        RandomMatch genMatch = new RandomMatch();
        Random rand = new Random();
        int rand1 = 0;
        int rand2 = 0;

        FootballClub selectRandomMatch1 = null;
        FootballClub selectRandomMatch2 = null;

        //Generate two ransoms teams
        int randomSelect = PremierLeagueManager.footBallClub.size();
        for (int i = 0; i < 2; i++) {
            rand1 = (int) (Math.random() * randomSelect);
            rand2 = (int) (Math.random() * randomSelect);
        }
        while (rand1 == rand2) {
            rand2 = (int) (Math.random() * randomSelect);
        }

        FootballClub selectRandom1 = PremierLeagueManager.footBallClub.get(rand1);
        FootballClub selectRandom2 = PremierLeagueManager.footBallClub.get(rand2);
        selectRandomMatch1 = selectRandom1;
        selectRandomMatch2 = selectRandom2;

        // Generate random integers in range 0 to 50 to get a random score
        int rand_int1 = rand.nextInt(15);
        int rand_int2 = rand.nextInt(15);

        int ranTeam1Scores = rand_int1;
        int ranTeam2Scores1 = rand_int2;

        //Genarate random dates for every random match
        GregorianCalendar dateRan = new GregorianCalendar();
        int year = randBetween(2020, 2021);
        dateRan.set(dateRan.YEAR, year);
        int dayOfYear = randBetween(1, dateRan.getActualMaximum(dateRan.DAY_OF_YEAR));
        dateRan.set(dateRan.DAY_OF_YEAR, dayOfYear);
        String ranDate = (dateRan.get(dateRan.MONTH) + 1) + "/" + dateRan.get(dateRan.DAY_OF_MONTH) + "/" + dateRan.get(dateRan.YEAR);

        //Get and set methos for each random match
        genMatch.setRanFirstTeam(selectRandomMatch1);
        genMatch.setRanSecondTeam(selectRandomMatch2);
        genMatch.setFirstTeamScore(ranTeam1Scores);
        genMatch.setSecondTeamScore(ranTeam2Scores1);
        genMatch.setDate(ranDate);
        change.add(genMatch);


        selectRandomMatch1.setScored(selectRandomMatch1.getScored() + rand_int1);
        selectRandomMatch2.setScored(selectRandomMatch2.getScored() + rand_int2);

        selectRandom1.setMatches(selectRandom1.getMatches() + 1);
        selectRandom2.setMatches(selectRandom2.getMatches() + 1);

        if (ranTeam1Scores > ranTeam2Scores1) {
            selectRandom1.setPoints(selectRandom1.getPoints() + 5);
            selectRandom1.setWins(selectRandom1.getWins() + 1);
            selectRandomMatch2.setDefeats(selectRandomMatch2.getDefeats() + 1);
        } else if (ranTeam1Scores < ranTeam2Scores1) {
            selectRandomMatch2.setPoints(selectRandomMatch2.getPoints() + 5);
            selectRandomMatch2.setWins(selectRandomMatch2.getWins() + 1);
            selectRandom1.setDefeats(selectRandom1.getDefeats() + 1);
        } else {
            selectRandom1.setPoints(selectRandom1.getPoints() + 1);
            selectRandomMatch2.setPoints(selectRandomMatch2.getPoints() + 1);
            selectRandom1.setDraws(selectRandom1.getDraws() + 1);
            selectRandomMatch2.setDraws(selectRandomMatch2.getDraws() + 1);
        }

        save();
        System.out.println(change);
        //Inserting and removing labels texts for next text
        flowPane.getChildren().removeAll(valueChange, team1Details, team1Points, team1Wins, team1Draws, team1MatchsPlayed,
                team1Defeats, team1ReceivdGoals, team1Scores);

        flowPane.getChildren().removeAll(team2Details, team2Points, team2Wins, team2Draws, team2MatchsPlayed,
                team2Defeats, team2ReceivdGoals, team2Scores, playedDate);

        valueChange = new Label("  " + genMatch.getRanFirstTeam().getClubName() + " Vs " + genMatch.getRanSecondTeam().getClubName());
        valueChange.setStyle("-fx-font-size: 50; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-background-color: #ff0707");
        valueChange.setPrefWidth(620);
        valueChange.setAlignment(Pos.CENTER);
        FlowPane.setMargin(valueChange, new Insets(105, 0, 0, 240));

        team1Details = new Label(genMatch.getRanFirstTeam().getClubName());
        team1Details.setStyle("-fx-font-size:25; -fx-text-fill: #ff0000; -fx-font-weight: bold; -fx-background-color: #ffffff");
        team1Details.setPrefWidth(200);
        team1Details.setAlignment(Pos.CENTER);
        FlowPane.setMargin(team1Details, new Insets(-110, 0, 0, 15));

        team2Details = new Label(genMatch.getRanSecondTeam().getClubName());
        team2Details.setStyle("-fx-font-size:25; -fx-text-fill: #ff0000; -fx-font-weight: bold; -fx-background-color: #ffffff");
        team2Details.setPrefWidth(200);
        team2Details.setAlignment(Pos.CENTER);
        FlowPane.setMargin(team2Details, new Insets(-445, 0, 0, 820));

        team1Points = new Label("Points - " + genMatch.getRanFirstTeam().getPoints());
        team1Points.setStyle("-fx-font-size:13; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-background-color: #000000");
        team1Points.setPrefWidth(200);
        team1Points.setAlignment(Pos.CENTER);
        FlowPane.setMargin(team1Points, new Insets(3, 0, 0, -170));

        team1Wins = new Label("Wins - " + genMatch.getRanFirstTeam().getWins());
        team1Wins.setStyle("-fx-font-size:13; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-background-color: #000000");
        team1Wins.setPrefWidth(200);
        team1Wins.setAlignment(Pos.CENTER);
        FlowPane.setMargin(team1Wins, new Insets(54, 0, 0, -170));

        team1Draws = new Label("Draws - " + genMatch.getRanFirstTeam().getDraws());
        team1Draws.setStyle("-fx-font-size:13; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-background-color: #000000");
        team1Draws.setPrefWidth(200);
        team1Draws.setAlignment(Pos.CENTER);
        FlowPane.setMargin(team1Draws, new Insets(105, 0, 0, -170));

        team1MatchsPlayed = new Label("  Matches Played - " + genMatch.getRanFirstTeam().getMatches());
        team1MatchsPlayed.setStyle("-fx-font-size:13; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-background-color: #000000");
        team1MatchsPlayed.setPrefWidth(200);
        team1MatchsPlayed.setAlignment(Pos.CENTER);
        FlowPane.setMargin(team1MatchsPlayed, new Insets(156, 0, 0, -155));

        team1Defeats = new Label("Defeats - " + genMatch.getRanFirstTeam().getDefeats());
        team1Defeats.setStyle("-fx-font-size:13; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-background-color: #000000");
        team1Defeats.setPrefWidth(200);
        team1Defeats.setAlignment(Pos.CENTER);
        FlowPane.setMargin(team1Defeats, new Insets(207, 0, 0, -170));

        team1ReceivdGoals = new Label("  Received Goals - " + genMatch.getRanFirstTeam().getDefeats());
        team1ReceivdGoals.setStyle("-fx-font-size:13; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-background-color: #000000");
        team1ReceivdGoals.setPrefWidth(200);
        team1ReceivdGoals.setAlignment(Pos.CENTER);
        FlowPane.setMargin(team1ReceivdGoals, new Insets(258, 0, 0, -170));

        team1Scores = new Label("Goals Scores - " + rand_int1);
        team1Scores.setStyle("-fx-font-size:13; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-background-color: #000000");
        team1Scores.setPrefWidth(200);
        team1Scores.setAlignment(Pos.CENTER);
        FlowPane.setMargin(team1Scores, new Insets(309, 0, 0, -170));

        team2Points = new Label( "Points - " + genMatch.getRanSecondTeam().getPoints() );
        team2Points.setStyle("-fx-font-size:13; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-background-color: #000000");
        team2Points.setPrefWidth(200);
        team2Points.setAlignment(Pos.CENTER);
        FlowPane.setMargin(team2Points, new Insets(-350, 0, 0, 790));

        team2Wins = new Label("Wins - " + genMatch.getRanSecondTeam().getWins());
        team2Wins.setStyle("-fx-font-size:13; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-background-color: #000000");
        team2Wins.setPrefWidth(200);
        team2Wins.setAlignment(Pos.CENTER);
        FlowPane.setMargin(team2Wins, new Insets(-310, 0, 0, 750));

        team2Draws = new Label("Draws - " + genMatch.getRanSecondTeam().getDraws());
        team2Draws.setStyle("-fx-font-size:13; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-background-color: #000000");
        team2Draws.setPrefWidth(200);
        team2Draws.setAlignment(Pos.CENTER);
        FlowPane.setMargin(team2Draws, new Insets(-270, 0, 0, 710));

        team2MatchsPlayed = new Label("  Matches Played - " + genMatch.getRanSecondTeam().getMatches());
        team2MatchsPlayed.setStyle("-fx-font-size:13; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-background-color: #000000");
        team2MatchsPlayed.setPrefWidth(200);
        team2MatchsPlayed.setAlignment(Pos.CENTER);
        FlowPane.setMargin(team2MatchsPlayed, new Insets(-225, 0, 0, 670));

        team2Defeats = new Label("Defeats - " + genMatch.getRanSecondTeam().getDefeats());
        team2Defeats.setStyle("-fx-font-size:13; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-background-color: #000000");
        team2Defeats.setPrefWidth(200);
        team2Defeats.setAlignment(Pos.CENTER);
        FlowPane.setMargin(team2Defeats, new Insets(-185, 0, 0, 630));

        team2ReceivdGoals = new Label("  Received Goals - " + genMatch.getRanSecondTeam().getDefeats());
        team2ReceivdGoals.setStyle("-fx-font-size:13; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-background-color: #000000");
        team2ReceivdGoals.setPrefWidth(200);
        team2ReceivdGoals.setAlignment(Pos.CENTER);
        FlowPane.setMargin(team2ReceivdGoals, new Insets(-145, 0, 0, 590));

        team2Scores = new Label("Goals Scores - " + rand_int2);
        team2Scores.setStyle("-fx-font-size:13; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-background-color: #000000");
        team2Scores.setPrefWidth(200);
        team2Scores.setAlignment(Pos.CENTER);
        FlowPane.setMargin(team2Scores, new Insets(-105, 0, 0, 550));

        playedDate = new Label(ranDate);
        playedDate.setStyle("-fx-font-size:22; -fx-text-fill: #f1eaea; -fx-font-weight: bold;");
        playedDate.setPrefWidth(160);
        playedDate.setAlignment(Pos.CENTER);
        FlowPane.setMargin(playedDate, new Insets(-1000, 500, 0, 20));


        flowPane.getChildren().addAll(valueChange, team1Details, team1Points, team1Wins, team1Draws, team1MatchsPlayed,
                team1Defeats, team1ReceivdGoals, team1Scores);

        flowPane.getChildren().addAll(team2Details, team2Points, team2Wins, team2Draws, team2MatchsPlayed,
                team2Defeats, team2ReceivdGoals, team2Scores, playedDate);
    }
    //Method for genarate random date
    public static int randBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }
    //Sort date
    public static void sortDate() {
        Collections.sort(PremierLeagueManager.playedMatchList, new matchDateCompare());
        for (PlayedMatch playDate : PremierLeagueManager.playedMatchList) {
            System.out.println(playDate.getDate());
        }

        Collections.sort(change, new ranMatchDateCompare());
        for (RandomMatch ranDate : change) {
            System.out.println(ranDate.getDate());
        }
    }
    //Display the stage of all Randomly played matches
    public static void allRanMatchPlayed() throws IOException {

        stage5 = new Stage();
        FlowPane flowPane = new FlowPane();
        stage5.setTitle("Play Random Match");


        Image backgroundColor = new Image("659010.jpg");
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true,
                true, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(backgroundColor, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);

        flowPane.setBackground(new Background(backgroundImage));

        flowPane.setHgap(5.0);
        flowPane.setVgap(5.0);
        flowPane.setOrientation(Orientation.HORIZONTAL);

        Button backToHome = new Button();
        backToHome.setText("Back To Home");
        backToHome.setStyle("-fx-font-size: 15; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-background-color: #6eaee8; -fx-cursor: hand;");
        backToHome.setPrefWidth(150.0);
        backToHome.setPrefHeight(18.0);
        FlowPane.setMargin(backToHome, new Insets(12, 0, 0, 340));

        backToHome.setOnAction(event -> {
                    stage5.close();
                    stage.show();
                }
        );


        Label heading1 = new Label("Randomly Generated Matches");
        heading1.setStyle("-fx-font-size: 25; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-background-color: #756868;");
        heading1.setPrefWidth(450);
        heading1.setAlignment(Pos.CENTER);
        FlowPane.setMargin(heading1, new Insets(10, 0, 0, 450.0));

//        flowPane.getChildren().add(heading1);

        Label firstMatch = new Label("Club Name");
        firstMatch.setStyle("-fx-font-size: 20; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-background-color: #756868;");
        firstMatch.setPrefWidth(250);
        firstMatch.setAlignment(Pos.CENTER);
        FlowPane.setMargin(firstMatch, new Insets(10, 0, 0, 450));

        Label date = new Label("Date");
        date.setStyle("-fx-font-size: 20; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-background-color: #756868;");
        date.setPrefWidth(140);
        date.setAlignment(Pos.CENTER);
        FlowPane.setMargin(date, new Insets(10, 0, 0, 55.0));

        Button playedPremMatch = new Button();
        playedPremMatch.setText("Premier League Played Match");
        playedPremMatch.setStyle("-fx-font-size: 15; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-background-color: #a90202; -fx-cursor: hand;");
        playedPremMatch.setPrefWidth(250);
        playedPremMatch.setPrefHeight(22);
        FlowPane.setMargin(playedPremMatch, new Insets(12, 0, 0, 558));

        flowPane.getChildren().addAll(playedPremMatch,backToHome,heading1,firstMatch,date);

        ranCompare(flowPane);


        playedPremMatch.setOnAction(event -> {
                    stage5.close();
                    try {
                        allPrePlayedMatch();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );



        Scene scene = new Scene(flowPane, 1100, 800);
        stage5.setResizable(false);
        stage5.setMaximized(true);
        stage5.setScene(scene);
        stage5.show();
    }
    //Display the stage of all Premier League played matches
    public static void allPrePlayedMatch() throws IOException {
        stage6 = new Stage();
        FlowPane flowPane = new FlowPane();
        stage6.setTitle("Play Random Match");


        Image backgroundColor = new Image("563012.jpg");
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true,
                true, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(backgroundColor, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);

        flowPane.setBackground(new Background(backgroundImage));

        flowPane.setHgap(5.0);
        flowPane.setVgap(5.0);
        flowPane.setOrientation(Orientation.HORIZONTAL);

        Button backToHome = new Button();
        backToHome.setText("Back To Home");
        backToHome.setStyle("-fx-font-size: 15; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-background-color: #6eaee8; -fx-cursor: hand;");
        backToHome.setPrefWidth(150.0);
        backToHome.setPrefHeight(18.0);
        FlowPane.setMargin(backToHome, new Insets(12, 0, 0, 340));

        backToHome.setOnAction(event -> {
                    stage6
                            .close();
                    stage.show();
                }
        );


        Label heading1 = new Label("Premier League Played Matches");
        heading1.setStyle("-fx-font-size: 25; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-background-color: #756868;");
        heading1.setPrefWidth(450);
        heading1.setAlignment(Pos.CENTER);
        FlowPane.setMargin(heading1, new Insets(10, 0, 0, 450.0));

//        flowPane.getChildren().add(heading1);

        Label firstMatch = new Label("Club Name");
        firstMatch.setStyle("-fx-font-size: 20; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-background-color: #756868;");
        firstMatch.setPrefWidth(250);
        firstMatch.setAlignment(Pos.CENTER);
        FlowPane.setMargin(firstMatch, new Insets(10, 0, 0, 450));

        Label date = new Label("Date");
        date.setStyle("-fx-font-size: 20; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-background-color: #756868;");
        date.setPrefWidth(140);
        date.setAlignment(Pos.CENTER);
        FlowPane.setMargin(date, new Insets(10, 0, 0, 55.0));

        Button randomMatch = new Button();
        randomMatch.setText("Randomly Genaated Matches");
        randomMatch.setStyle("-fx-font-size: 15; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-background-color: #a90202; -fx-cursor: hand;");
        randomMatch.setPrefWidth(250);
        randomMatch.setPrefHeight(22);
        FlowPane.setMargin(randomMatch, new Insets(12, 0, 0, 558));

        flowPane.getChildren().addAll(randomMatch,backToHome,heading1,firstMatch,date);

        matchCompare(flowPane);

        randomMatch.setOnAction(event -> {
                    stage6.close();
                    stage5.show();
                }
        );

        Scene scene = new Scene(flowPane, 1100, 800);
        stage6.setResizable(false);
        stage6.setMaximized(true);
        stage6.setScene(scene);
        stage6.show();
    }
    //Sort randomly genarated matches dates and display table
    public static void ranCompare(FlowPane flowPane) {

        change.sort(new ranMatchDateCompare());
        for (RandomMatch ran : change) {

            Label ranMatchFirst = new Label(ran.getRanFirstTeam().getClubName() + " Vs "  + ran.getRanSecondTeam().getClubName());
            Label ranMatchDate = new Label(ran.getDate());

            ranMatchFirst.setStyle("-fx-font-size: 18; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-background-color: #000000;");
            ranMatchFirst.setPrefWidth(250);
            ranMatchFirst.setAlignment(Pos.CENTER);
            FlowPane.setMargin(ranMatchFirst, new Insets(0, 0, 0, 450));

            ranMatchDate.setStyle("-fx-font-size: 18; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-background-color: #000000;");
            ranMatchDate.setPrefWidth(140);
            ranMatchDate.setAlignment(Pos.CENTER);
            FlowPane.setMargin(ranMatchDate, new Insets(0, 0, 0, 55));

            flowPane.getChildren().addAll(ranMatchFirst, ranMatchDate);
        }

    }
    //Sort premier league matches dates and display table
    public static void matchCompare(FlowPane flowPane) {
        PremierLeagueManager.playedMatchList.sort(new matchDateCompare());
        for (PlayedMatch play : PremierLeagueManager.playedMatchList) {

            Label ranMatchFirst = new Label(play.getFistTeam().getClubName() + " Vs "  + play.getSecondTeam().getClubName());
            Label ranMatchDate = new Label(play.getDate());

            ranMatchFirst.setStyle("-fx-font-size: 18; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-background-color: #000000;");
            ranMatchFirst.setPrefWidth(250);
            ranMatchFirst.setAlignment(Pos.CENTER);
            FlowPane.setMargin(ranMatchFirst, new Insets(0, 0, 0, 450));

            ranMatchDate.setStyle("-fx-font-size: 18; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-background-color: #000000;");
            ranMatchDate.setPrefWidth(140);
            ranMatchDate.setAlignment(Pos.CENTER);
            FlowPane.setMargin(ranMatchDate, new Insets(0, 0, 0, 55));

            flowPane.getChildren().addAll(ranMatchFirst, ranMatchDate);
        }

    }
    //Set bsckgroung imsge for flowpane 1
    public static void backGroundImageStage(FlowPane flowPane1) throws FileNotFoundException{

        Image backgroundColor = new Image("659054.jpg");
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true,
                true, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(backgroundColor, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);

        flowPane1.setBackground(new Background(backgroundImage));

    }

    public static void searchFromDate() {

        Stage primayStage = new Stage();
        FlowPane flowPane = new FlowPane();
        primayStage.setTitle("Play Random Match");

        Image backgroundColor = new Image("1637819.jpg");
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true,
                true, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(backgroundColor, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);

        flowPane.setBackground(new Background(backgroundImage));

        flowPane.setHgap(5.0);
        flowPane.setVgap(5.0);
        flowPane.setOrientation(Orientation.HORIZONTAL);

        Label label = new Label("Enter played Date (MM/DD/YYYY): ");
        label.setStyle("-fx-text-fill: #f34f4f; -fx-font-size: 16; -fx-font-weight: bold;");
        FlowPane.setMargin(label, new Insets(50, 0, 0, 190));

        textField = new TextField();
        textField.setStyle("-fx-text-fill: #f34f4f; -fx-font-size: 10;");
        FlowPane.setMargin(textField, new Insets(50, 0, 0, 0));

        Button search = new Button("Search");
        search.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 15; -fx-font-weight: bold; -fx-background-color: #a50404; -fx-cursor: hand");
        FlowPane.setMargin(search, new Insets(20.0, 400.0, 0, 300.0));

        Label Details = new Label();
        FlowPane.setMargin(search, new Insets(20.0,50,0,300.0));
        Details.setStyle("-fx-text-fill: #6eaee8; -fx-font-size: 15; -fx-font-weight: bold;");

        search.setOnAction(event -> {
                    String ID = textField.getText();

                    primayStage.close();
                    stage7 = new Stage();
                    FlowPane flowPane1 = new FlowPane();
                    stage7.setTitle("Display clubs ");

                    try {
                        backGroundImageStage(flowPane1);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    flowPane1.setHgap(5.0);
                    flowPane1.setVgap(5.0);
                    flowPane1.setOrientation(Orientation.HORIZONTAL);

                    Label heading1 = new Label("Match");
                    heading1.setStyle("-fx-font-size: 15; -fx-text-fill: #de6e6e; -fx-font-weight: bold; -fx-background-color: #d6caca;");
                    heading1.setPrefWidth(210.0);
                    heading1.setAlignment(Pos.CENTER);
                    FlowPane.setMargin(heading1, new Insets(10, 0, 0, 20.0));

                    Label heading2 = new Label("Score");
                    heading2.setStyle("-fx-font-size: 15; -fx-text-fill: #de6e6e; -fx-font-weight: bold; -fx-background-color: #d6caca;");
                    heading2.setPrefWidth(210.0);
                    heading2.setAlignment(Pos.CENTER);
                    FlowPane.setMargin(heading2, new Insets(10, 0, 0, 170.0));

                    flowPane1.getChildren().addAll(heading1, heading2);

                    Scene scene = new Scene(flowPane1, 650, 500);
                    stage7.setResizable(false);
                    stage7.setScene(scene);
                    stage7.show();

                    System.out.println(ID);
                    for (RandomMatch ran : change) {
                        if (ran.getDate().equals(ID)) {

                            Label clubNames = new Label(ran.getRanFirstTeam().getClubName() + " Vs " + ran.getRanSecondTeam().getClubName());
                            Label clubScores = new Label(ran.getRanFirstTeam().getScored() + " - " + ran.getRanSecondTeam().getScored());

                            clubNames.setStyle("-fx-font-size: 15; -fx-text-fill: #fdfdfd; -fx-font-weight: bold; -fx-background-color: red; ");
                            clubNames.setPrefWidth(210.0);
                            clubNames.setAlignment(Pos.CENTER);
                            FlowPane.setMargin(clubNames, new Insets(5, 0, 0, 20));

                            clubScores.setStyle("-fx-font-size: 15; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-background-color: red; ");
                            clubScores.setPrefWidth(210.0);
                            clubScores.setAlignment(Pos.CENTER);
                            FlowPane.setMargin(clubScores, new Insets(5, 0, 0, 170));

                            flowPane1.getChildren().addAll(clubNames, clubScores);
                        }
                    }
                    for (PlayedMatch play : PremierLeagueManager.playedMatchList) {
                        if (play.getDate().equals(ID)) {
                            Label ranClubNames = new Label(play.getFistTeam().getClubName() + " Vs " + play.getSecondTeam().getClubName());
                            Label ranClubScores = new Label(play.getFistTeam().getScored() + " - " + play.getSecondTeam().getScored());

                            System.out.println(play.getFistTeam().getClubName() + " Vs " + play.getSecondTeam().getClubName());

                            ranClubNames.setStyle("-fx-font-size: 15; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-background-color: red;");
                            ranClubNames.setPrefWidth(210.0);
                            ranClubNames.setAlignment(Pos.CENTER);
                            FlowPane.setMargin(ranClubNames, new Insets(5, 0, 0, 20));

                            ranClubScores.setStyle("-fx-font-size: 15; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-background-color: red;");
                            ranClubScores.setPrefWidth(210.0);
                            ranClubScores.setAlignment(Pos.CENTER);
                            FlowPane.setMargin(ranClubScores, new Insets(5, 0, 0, 170));
                            flowPane1.getChildren().addAll(ranClubNames, ranClubScores);

                        }
                    }
        }
        );

        flowPane.getChildren().addAll(label, textField, search,Details);

        Scene scene = new Scene(flowPane, 600, 300);
        primayStage.setResizable(false);
        primayStage.setScene(scene);
        primayStage.show();
    }

}


