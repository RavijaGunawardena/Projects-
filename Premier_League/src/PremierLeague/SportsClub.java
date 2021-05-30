package PremierLeague;

import java.io.Serializable;
import java.util.Objects;

public class SportsClub implements Serializable {
    private static final long serialVersionUID = 2L;
    private String clubName;
    private String clubLocation;
    private String clubId;

    public SportsClub(String clubName, String clubLocation, String clubId) {
        this.clubName = clubName;
        this.clubLocation = clubLocation;
        this.clubId = clubId;

    }

    protected SportsClub() {}


    public String getClubName() {
        return clubName;
    }

//    public void setClubName(String clubName) {
//        this.clubName = clubName;
//    }
//
//    public String getClubLocation() {
//        return clubLocation;
//    }
//
//    public void setClubLocation(String clubLocation) {
//        this.clubLocation = clubLocation;
//    }
//
//    public String getClubId() {
//        return clubId;
//    }
//
//    public void setClubId(String clubId) {
//        this.clubId = clubId;
//    }

    @Override
    public String toString() {
        return "SportsClub{" +
                "clubName='" + clubName + '\'' +
                ", clubLocation='" + clubLocation + '\'' +
                ", clubId='" + clubId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SportsClub that = (SportsClub) o;
        return Objects.equals(clubName, that.clubName) &&
                Objects.equals(clubLocation, that.clubLocation) &&
                Objects.equals(clubId, that.clubId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clubName, clubLocation, clubId);
    }


}
