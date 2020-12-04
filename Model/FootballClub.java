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
class SortbyPointGoals implements Comparator<SportsClub>
{
	public int compare(SportsClub a, SportsClub b)
	{ if (a.getPointCount()==b.getPointCount()){
		return ((FootballClub)(a)).getGoalsScored()-((FootballClub)(b)).getGoalsScored();
	}else return a.getPointCount()-b.getPointCount();
	}
}
class SortbyGoals implements Comparator<SportsClub>
{
	public int compare(SportsClub a, SportsClub b)
	{ return ((FootballClub)(a)).getGoalsScored()-((FootballClub)(b)).getGoalsScored(); }
}
class SortbyWins implements Comparator<SportsClub>
{
	public int compare(SportsClub a, SportsClub b)
	{ return a.getWins()-b.getWins(); }
}
class SortByPoints implements Comparator<SportsClub>
{
	public int compare(SportsClub a, SportsClub b)
	{ return a.getPointCount()-b.getPointCount(); }
}