package PremierLeague;

import java.io.Serializable;
import java.util.Date;

public class PlayedMatch implements Serializable {

    private FootballClub fistTeam;
    private FootballClub secondTeam;
    private int firstTeamScore;
    private int secondTeamScore;
    private String date;
    private static final long serialVersionUID = 3L;

//    public PlayedMatch(FootballClub fistTeam, FootballClub secondTeam, int firstTeamScore, int secondTeamScore, String date) {
//        this.fistTeam = fistTeam;
//        this.secondTeam = secondTeam;
//        this.firstTeamScore = firstTeamScore;
//        this.secondTeamScore = secondTeamScore;
//        this.date = date;

//    }

    public FootballClub getFistTeam() {
        return fistTeam;
    }

    public void setFistTeam(FootballClub fistTeam) {
        this.fistTeam = fistTeam;
    }

    public FootballClub getSecondTeam() {
        return secondTeam;
    }

    public void setSecondTeam(FootballClub secondTeam) {
        this.secondTeam = secondTeam;
    }

    public int getFirstTeamScore() {
        return firstTeamScore;
    }

    public void setFirstTeamScore(int firstTeamScore) {
        this.firstTeamScore = firstTeamScore;
    }

    public int getSecondTeamScore() {
        return secondTeamScore;
    }

    public void setSecondTeamScore(int secondTeamScore) {
        this.secondTeamScore = secondTeamScore;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "PlayedMatch{" +
                "fistTeam=" + fistTeam +
                ", secondTeam=" + secondTeam +
                ", firstTeamScore=" + firstTeamScore +
                ", secondTeamScore=" + secondTeamScore +
                ", date='" + date + '\'' +
                '}';
    }
}
