package Model;

import java.util.Comparator;
public class FootballClub extends SportsClub {
	private int goalsReceived;
	private int goalsScored;

	public FootballClub(){}

	public FootballClub(String name, String location) {
		this.name = name;
		this.location = location;
	}

	public FootballClub(int goalsReceived, int goalsScored) {
		this.goalsReceived = goalsReceived;
		this.goalsScored = goalsScored;
	}

	public int getGoalsReceived() {
		return goalsReceived;
	}
	public void setGoalsReceived(int goalsReceived) {
		this.goalsReceived = goalsReceived;
	}
	public int getGoalsScored() {
		return goalsScored;
	}
	public void setGoalsScored(int goalsScored) {
		this.goalsScored = goalsScored;
	}

	@Override
	public String toString() {
		return  " \n"+super.toString()+
				" \ngoalsReceived : " + goalsReceived +
				" \ngoalsScored   : " + goalsScored;
	}
}