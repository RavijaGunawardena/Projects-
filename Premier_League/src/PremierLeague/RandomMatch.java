package PremierLeague;

import java.io.Serializable;

public class RandomMatch implements Serializable {
    private FootballClub ranFirstTeam;
    private FootballClub ranSecondTeam;
    private int ranFirstTeamScore;
    private int ranSecondTeamScore;
    private String date;
    private static final long serialVersionUID = 3L;

    public FootballClub getRanFirstTeam() {
        return ranFirstTeam;
    }

    public void setRanFirstTeam(FootballClub ranFirstTeam) {
        this.ranFirstTeam = ranFirstTeam;
    }

    public FootballClub getRanSecondTeam() {
        return ranSecondTeam;
    }

    public void setRanSecondTeam(FootballClub ranSecondTeam) {
        this.ranSecondTeam = ranSecondTeam;
    }

    public int getFirstTeamScore() {
        return ranFirstTeamScore;
    }

    public void setFirstTeamScore(int firstTeamScore) {
        this.ranFirstTeamScore = firstTeamScore;
    }

    public int getSecondTeamScore() {
        return ranSecondTeamScore;
    }

    public void setSecondTeamScore(int secondTeamScore) {
        this.ranSecondTeamScore = secondTeamScore;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "RandomMatch{" +
                "ranFirstTeam=" + ranFirstTeam +
                ", ranSecondTeam=" + ranSecondTeam +
                ", firstTeamScore=" + ranFirstTeamScore +
                ", secondTeamScore=" + ranSecondTeamScore +
                ", date='" + date + '\'' +
                '}';
    }
}
