import PremierLeague.ConsoleApplication;
import PremierLeague.PlayedMatch;
import PremierLeague.PremierLeagueManager;
import PremierLeague.RandomMatch;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public class RandomDateOfBirth implements Comparator<RandomMatch> {

    static PremierLeagueManager man;
    static RandomMatch ram;

    private static ArrayList <String> date = new ArrayList<>();

    public static void main (String [] args){

    }

    @Override
    public int compare(RandomMatch o1, RandomMatch o2) {
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

        Date date1 = null;
        try {
            date1 = format.parse(o1.getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date date2 = null;
        try {
            date2 = format.parse(o2.getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (date1.compareTo(date2) <= 0) {
            System.out.println("earlier");
        }
        return 0;
    }
}