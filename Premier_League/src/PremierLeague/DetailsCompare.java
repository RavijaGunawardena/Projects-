package PremierLeague;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;

//Compare points and gal difference
public class DetailsCompare implements Comparator<FootballClub>{
    @Override
    public int compare(FootballClub o1, FootballClub o2) {
        if(o1.getPoints() > o2.getPoints())
            return -1;
        else if (o1.getPoints() < o2.getPoints())
            return 1;
        else {
            int goalDif1 = o1.getScored()-o1.getGoals();
            int goalDif2 = o2.getScored()-o2.getGoals();
            if(goalDif1 > goalDif2)
                return -1;
            else
            if(goalDif1 < goalDif2)
                return 1;
            return 0;
        }
    }
}
//Compare points for GUI purposes
class PointCompare implements Comparator<FootballClub> {
    @Override
    public int compare(FootballClub o1, FootballClub o2) {
        if (o1.getPoints() > o2.getPoints())
            return -1;
        else if (o1.getPoints() < o2.getPoints())
            return 1;

        return 0;
    }
}
//Compare scores for GUI purposes
class ScoreCompare implements Comparator<FootballClub> {
    @Override
    public int compare(FootballClub o1, FootballClub o2) {
        int goalDif1 = o1.getScored()-o1.getGoals();
        int goalDif2 = o2.getScored()-o2.getGoals();
        if(goalDif1 > goalDif2)
            return -1;
        else
        if(goalDif1 < goalDif2)
            return 1;
        return 0;
    }
}
//Compare wins for GUI purposes
class WinsCompare implements Comparator<FootballClub> {
    @Override
    public int compare(FootballClub o1, FootballClub o2) {
        if(o1.getWins() > o2.getWins())
            return -1;
        else
        if(o1.getWins() < o2.getWins())
            return 1;
        return 0;
    }
}
//Compare Played matches for GUI purposes
class matchDateCompare implements Comparator<PlayedMatch> {
    DateFormat matchFormat = new SimpleDateFormat("MM/dd/yyyy");
    @Override
    public int compare(PlayedMatch o1, PlayedMatch o2) {
        try {
            return matchFormat.parse(o1.getDate()).compareTo(matchFormat.parse(o2.getDate()));
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
//Compare Random matches for GUI purposes
class ranMatchDateCompare implements Comparator<RandomMatch> {
    DateFormat ranFormat= new SimpleDateFormat("MM/dd/yyyy");
    @Override
    public int compare(RandomMatch o1, RandomMatch o2) {
        try {
            return ranFormat.parse(o1.getDate()).compareTo(ranFormat.parse(o2.getDate()));
        }
        catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }
}



