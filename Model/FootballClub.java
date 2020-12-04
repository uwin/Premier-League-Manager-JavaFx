package Model;

public class FootballClub extends SportsClub implements Comparable<SportsClub> {
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
	public int compareTo(SportsClub fc) {
		if (this.pointCount==fc.getPointCount()) {
			return this.goalsScored-((FootballClub)(fc)).getGoalsScored();
		}else return this.pointCount-fc.getPointCount();
	}
	@Override
	public String toString() {
		return  " \n"+super.toString()+
				" \ngoalsReceived : " + goalsReceived +
				" \ngoalsScored   : " + goalsScored;
	}
}