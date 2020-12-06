package Model;

import java.time.LocalDate;

public  interface LeagueManager{
	void createClub(String name, String location);
	void deleteClub(int index);
	void viewClub(int index);
	void displayTable();
	Match addMatch(LocalDate dateL, SportsClub oneClub, int oneClubScore, SportsClub twoClub, int twoClubScore);
	void save();
	void load();
}