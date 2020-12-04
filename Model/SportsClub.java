package Model;
import java.io.Serializable;

public abstract class  SportsClub implements Serializable{
	String name;
	String location;
	int wins;
	int draws;
	int defeat;
	int score;
	int pointCount;
	int matchCount;

	public SportsClub(){}

	public SportsClub(String name, String location, int wins, int draws, int defeat, int score, int pointCount, int matchCount) {
		this.name = name;
		this.location = location;
		this.wins = wins;
		this.draws = draws;
		this.defeat = defeat;
		this.score = score;
		this.pointCount = pointCount;
		this.matchCount = matchCount;
	}

	public String getName() { return name; }
	public void setName(String name) { this.name=name;	}

	public String getLocation() { return location; }
	public void setLocation(String location) { this.location=location;	}

	public int getWins() { return wins; }
	public void setWins(int wins) { this.wins = wins; }

	public int getDraws() {return draws;}
	public void setDraws(int draws) { this.draws = draws; }

	public int getDefeat() {return defeat;}
	public void setDefeat(int defeat) {	 this.defeat = defeat; }

	public int getScore() {return score;}
	public void setScore(int score) {this.score = score;}

	public int getPointCount() {return pointCount;}
	public void setPointCount(int pointCount) {		this.pointCount = pointCount; }

	public int getMatchCount() {return matchCount;}
	public void setMatchCount(int matchCount) {this.matchCount = matchCount;}

	@Override
    public String toString() {
       	return "  \nname          : " + name +
				" \nlocation      : " + location+
				" \nwins          : " + wins +
				" \ndraws         : " + draws +
				" \ndefeat        : " + defeat+
				" \nscore         : " + score +
				" \npointCount    : " + pointCount +
				" \nmatchCount    : " + matchCount;
			}
}	