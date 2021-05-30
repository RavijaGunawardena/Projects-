package PremierLeague;

import javafx.scene.control.Label;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;


public class FootballClub extends SportsClub implements Serializable {
    private int wins;
    private int draws;
    private int defeats;
    private int goals;
    private int points;
    private int matches;
    private int scored;

    public FootballClub(int wins, int draws, int defeats, int goals, int points, String clubName, String clubLocation,
                        String clubId, int matches, int scored) {
        super(clubName, clubLocation, clubId);

        this.wins = wins;
        this.draws = draws;
        this.defeats = defeats;
        this.goals = goals;
        this.points = points;
        this.scored = scored;
        this.matches = matches;
    }

    public FootballClub(String clubName, String clubLocation, String clubId) {
        super(clubName , clubLocation , clubId);

    }


    public FootballClub(int ranWins, int ranGoals, int ranScores, int ranDefeats, int ranDraws, int ranPoints,
                        int ranMatches) {
    }



    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public int getDefeats() {
        return defeats;
    }

    public void setDefeats(int defeats) {
        this.defeats = defeats;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getMatches() {
        return matches;

    }

    public void setMatches(int matches) {
        this.matches = matches;

    }

    public int getScored() {
        return scored;
    }

    public void setScored(int scored) {
        this.scored = scored;
    }

    @Override
    public String toString() {
        return "FootballClub{" + super.toString() +
                "wins=" + wins +
                ", draws=" + draws +
                ", defeats=" + defeats +
                ", goals=" + goals +
                ", points=" + points +
                ", matches=" + matches +
                ", scored=" + scored +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FootballClub that = (FootballClub) o;
        return wins == that.wins &&
                draws == that.draws &&
                defeats == that.defeats &&
                goals == that.goals &&
                points == that.points &&
                matches == that.matches &&
                scored == that.scored;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), wins, draws, defeats, goals, points, matches, scored);
    }

}