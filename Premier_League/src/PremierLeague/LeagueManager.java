package PremierLeague;

import java.util.ArrayList;

public interface LeagueManager {
    void addClub(FootballClub sport);
    void deleteClub(String clubId);
    void printStatistics();
    void clubsDisplay();
    void save (ArrayList <FootballClub> footBallClub);
    void displayPremierLeagueTable();
    void addMatch();
    void loading();
    void saveRandom (ArrayList <RandomMatch> change);
    void loadingRandom ();
    void savePlayedMatch (ArrayList <PlayedMatch> playedMatchList);
    void loadingPlayedMatch ();
}
