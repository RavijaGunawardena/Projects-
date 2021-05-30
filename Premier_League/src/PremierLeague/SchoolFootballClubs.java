package PremierLeague;

import java.io.Serializable;
import java.util.Objects;

public class SchoolFootballClubs extends FootballClub implements Serializable {
    private String schName;

    public SchoolFootballClubs(String schlName, int wins, int draws, int defeats, int goals, int points, String clubName,
                               String clubLocation, String clubId , int matches, int scored) {
        super(wins, draws, defeats, goals, points, clubName, clubLocation, clubId,matches, scored);

        this.schName = schlName;
    }

    public String getSchName() {
        return schName;
    }

    public void setSchName(String schName) {
        this.schName = schName;
    }

    @Override
    public String toString() {
        return "SchoolFootballClubs{" + super.toString() +
                "schName='" + schName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SchoolFootballClubs that = (SchoolFootballClubs) o;
        return Objects.equals(schName, that.schName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), schName);
    }
}
