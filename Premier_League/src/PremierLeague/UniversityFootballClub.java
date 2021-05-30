package PremierLeague;

import java.io.Serializable;
import java.util.Objects;

public class UniversityFootballClub extends FootballClub implements Serializable {
    private String uniName;
//Create a constructor
    public UniversityFootballClub(String uniName, int wins, int draws, int defeats, int goals, int points, String clubName,
                                  String clubLocation, String clubId , int matches, int scored) {
        super(wins, draws, defeats, goals, points, clubName,clubLocation, clubId, matches, scored);

        this.uniName = uniName;

    }

    public String getUniName() {
        return uniName;
    }

    public void setUniName(String uniName) {
        this.uniName = uniName;
    }

    //Make data readable manner
    @Override
    public String toString() {
        return "UniversityFootballClub{" + super.toString() +
                "uniName='" + uniName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UniversityFootballClub that = (UniversityFootballClub) o;
        return Objects.equals(uniName, that.uniName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), uniName);
    }
}
