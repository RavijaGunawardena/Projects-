package PremierLeague;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import static PremierLeague.ConsoleApplication.USER_INPUT;
import static PremierLeague.ConsoleApplication.change;

public class PremierLeagueManager implements LeagueManager{
    final static public int MAX_CLUBS = 20;
    private int numberOfClubs = MAX_CLUBS;
    //Array lists
    public static final ArrayList<FootballClub> footBallClub = new ArrayList<>();
    public static final ArrayList<PlayedMatch> playedMatchList = new ArrayList<>();
    static final DateTimeFormatter send_date = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    //Add club into premier league
    public void addClub(FootballClub sport) {

        for (FootballClub footballClub : footBallClub) {
            if (sport.getClubName().equals(footballClub.getClubName())) {
                System.out.println("Club is already added !\n");
                return;
            }
        }
        if (footBallClub.size() == numberOfClubs) {
            System.out.println("Can't add more clubs to Premier League");

        }
        else {
            footBallClub.add(sport);
            numberOfClubs -= 1;
            System.out.println(sport.getClubName() + " added Successfully");
            System.out.println("Available free space is " + numberOfClubs + "\n");
        }
    }
    //Delete clubs from premier league
    public void deleteClub(String clubName) {
        boolean found = false;

        for (FootballClub sport : footBallClub) {
            if (sport.getClubName().equals(clubName)) {
                found = true;
                footBallClub.remove(sport);
                System.out.print(clubName + " has successfully deleted from the Premier League\n");
                System.out.println(" ");
                numberOfClubs += 1;
                break;
            }
        }
        if (!found) {

            System.out.println("Invalid club Name.\n");
        }

    }
    //Display club statisticcs
    public void printStatistics() {

        clubsDisplay();
        System.out.println(" ");
        Scanner clubName = new Scanner(System.in);
        System.out.println("Enter club name: ");
        String name = clubName.next();
        System.out.println(" ");
        for (FootballClub club : footBallClub) {
            if (club.getClubName().equals(name)) {
                System.out.println("------------------------------------------------------------------------------------");
                System.out.println("| Wins | Draws | Defeats | Goals Received | Goals Scored | Points | Matched Played |");
                System.out.println("------------------------------------------------------------------------------------");
                System.out.println("|" + club.getWins() + "     | " + club.getDraws() + "     | " + club.getDefeats() + " "
                        + "      | " + club.getGoals() + "              | " + club.getScored() + ""
                        + "            | " + club.getPoints() + "      | " + club.getMatches() + "              |");
                System.out.println("------------------------------------------------------------------------------------");
                System.out.println(" ");
                return;
            }

        }

        System.out.println("Cant find a club called " + name);
    }
    //Display premier league table
    @Override
    public void displayPremierLeagueTable() {
        footBallClub.sort(new DetailsCompare());

        String alignFormat = "| %-15s | %-4d | %-4d |%n";

        System.out.println("---------------------------------");
        System.out.format("| Club Name       |Points|Scores|%n");
        System.out.println("---------------------------------");
        for (FootballClub footballClub : footBallClub) {
            System.out.format(alignFormat, footballClub.getClubName(), footballClub.getPoints(),
                    footballClub.getScored());
        }

        System.out.println("---------------------------------");

    }
    //Shows available clubs in premier league
    public void clubsDisplay () {
        System.out.println(" ");
        System.out.println("Available clubs in Premier League :");
        for (FootballClub footballClub : footBallClub) {
            System.out.print(footballClub.getClubName() + " | ");
        }

    }
    //Load data to array list
    public void loading () {
        try {
            // Reading the object from a file
            FileInputStream file = new FileInputStream("FootBall.txt");
            ObjectInputStream in = new ObjectInputStream(file);
            FootballClub object1 = null;
            // Method for deserialization of object
            int count2 = 1;
            while (true) {
                try {
                    FootballClub premMatch = (FootballClub) in.readObject();
                    footBallClub.add(premMatch );
                    count2++;
                    numberOfClubs = numberOfClubs-count2;
                }catch (Exception e) {
                    break;
                }
            }
            in.close();
            file.close();
        } catch (IOException ex) {
            System.out.println("File has being loaded successfully!");
        }
    }
    //Save all data into a file
    public void save (ArrayList <FootballClub> footBallClub) {
        try {
            // Saving of object in a file
            FileOutputStream file = new FileOutputStream("FootBall.txt");
            ObjectOutputStream out = new ObjectOutputStream(file);
            for (SportsClub in : footBallClub) {
                out.writeObject(in);
            }
            out.close();
            file.close();
        }
        catch (IOException ex) {
            System.out.println("IOException is caught");
        }
        System.out.println("** Saved successfully **");
    }
    //Load random played matches to array list
    public void loadingRandom () {
        try {
            // Reading the object from a file
            FileInputStream file = new FileInputStream("Random.txt");
            ObjectInputStream in = new ObjectInputStream(file);
            RandomMatch object1 = null;
            // Method for deserialization of object
//            for (int i = 0; i < 50; i++) {
//                object1 = (RandomMatch) in.readObject();
//                change.add(object1);
//            }
            int count = 0;
            while (true) {
                try {
                    RandomMatch ranMatch = (RandomMatch) in.readObject();
                    change.add(ranMatch );
                    count++;
                }catch (Exception e) {
                    break;
                }
            }
            in.close();
            file.close();
        } catch (IOException ex) {
            System.out.println("File has being loaded successfully!");
        }
    }
    //Save all random played matches data into a file
    public void saveRandom (ArrayList <RandomMatch> change) {
        try {
            // Saving of object in a file
            FileOutputStream file = new FileOutputStream("Random.txt");
            ObjectOutputStream out = new ObjectOutputStream(file);
            for (RandomMatch in : change) {
                out.writeObject(in);
            }
            out.close();
            file.close();
        }
        catch (IOException ex) {
            System.out.println("IOException is caught");
        }
    }
    //Load premier league played matches to array list
    public void loadingPlayedMatch () {
        try {
            // Reading the object from a file
            FileInputStream file = new FileInputStream("Played_Match.txt");
            ObjectInputStream in = new ObjectInputStream(file);
            PlayedMatch object1 = null;
            // Method for deserialization of object
            int count1 = 0;
            while (true) {
                try {
                    PlayedMatch playMatch = (PlayedMatch) in.readObject();
                    playedMatchList.add(playMatch );
                    count1++;
                }catch (Exception e) {
                    break;
                }
            }
            in.close();
            file.close();
        } catch (IOException ex) {
            System.out.println("File has being loaded successfully!");
        }
    }
    //Save all premier league played matches data into a file
    public void savePlayedMatch (ArrayList <PlayedMatch> playedMatchList) {
        try {
            // Saving of object in a file
            FileOutputStream file = new FileOutputStream("Played_Match.txt");
            ObjectOutputStream out = new ObjectOutputStream(file);
            for (PlayedMatch in : playedMatchList) {
                out.writeObject(in);
            }
            out.close();
            file.close();
        }
        catch (IOException ex) {
            System.out.println("IOException is caught");
        }
    }
    //Add a played match
    public void addMatch () {
        PlayedMatch newMatch = new PlayedMatch();
        clubsDisplay();
        System.out.println(" ");
        System.out.println("\nPlease choose the Home Team to update :");
        String homeTeam = USER_INPUT.next();
        FootballClub home = null;
        for (FootballClub club : footBallClub) {
            if (club.getClubName().equals(homeTeam))
                home = club;
        }
        if (home == null) {
            System.out.println("Invalid Club Name !");
            return;
        }
        System.out.println("Please choose the Away Team to update :");
        String awayTeam = USER_INPUT.next();
        if (homeTeam.equals(awayTeam)) {
            System.out.println("Home Team and Away Team are both same. Please choose a different Away Team\n");
            return;
        }
        FootballClub away = null;
        for (FootballClub club : footBallClub) {
            if (club.getClubName().equals(awayTeam))
                away = club;
        }
        if (away == null) {
            System.out.println("Club Name Is Not Valid !");
            return;
        }
        String date;
        while (true) {
            System.out.println("Please enter the date (mm/dd/yyyy)");
            date = USER_INPUT.next();

            try {
                LocalDate.parse(date, send_date);
                break;
            } catch (DateTimeParseException e) {
                System.out.println(date + " is a not valid Date! Please insert a valid format ");
            }
        }

        System.out.println("Enter the Home club score :");
        int homeGoals = USER_INPUT.nextInt();
        System.out.println("Enter the Away club score :");
        int awayGoals = USER_INPUT.nextInt();

        newMatch.setFistTeam(home);
        newMatch.setSecondTeam(away);
        newMatch.setFirstTeamScore(homeGoals);
        newMatch.setSecondTeamScore(awayGoals);
        newMatch.setDate(date);
        playedMatchList.add(newMatch);

        for(PlayedMatch play : playedMatchList){
            System.out.println(playedMatchList);
            System.out.println("Date - " + play.getDate());
        }

        home.setScored(home.getScored() + homeGoals);
        away.setScored(away.getScored() + awayGoals);
        home.setGoals(home.getGoals() + awayGoals);
        away.setGoals(away.getGoals() + homeGoals);
        home.setMatches(home.getMatches() + 1);
        away.setMatches(away.getMatches() + 1);
        if (homeGoals > awayGoals) {
            home.setPoints(home.getPoints() + 5);
            home.setWins(home.getWins() + 1);
            away.setDefeats(away.getDefeats() + 1);
            System.out.println("Successfully Update the Premier League Table\n");
        } else if (homeGoals < awayGoals) {
            away.setPoints(away.getPoints() + 5);
            away.setWins(away.getWins() + 1);
            home.setDefeats(home.getDefeats() + 1);
            System.out.println("Successfully Update the Premier League Table\n");
        } else {
            home.setPoints(home.getPoints() + 1);
            away.setPoints(away.getPoints() + 1);
            home.setDraws(home.getDraws() + 1);
            away.setDraws(away.getDraws() + 1);
            System.out.println("Successfully Update the Premier League Table\n");

        }

        try {
            ConsoleApplication.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
