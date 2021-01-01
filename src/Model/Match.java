package Model;

import java.io.Serializable;
import java.time.LocalDate;

public class Match implements Serializable {
	private LocalDate date;
	private FootballClub teamA;
	private int teamAScore;
	private String teamAName;
	private FootballClub teamB;
	private int teamBScore;
	private String teamBName;

	public Match(){ }

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public FootballClub getTeamA() {
		return teamA;
	}

	public void setTeamA(FootballClub teamA) {
		this.teamA = teamA;
	}
	public String getTeamAName() {
		return this.teamA.getName();
	}

	public void setTeamAName(String teamAName) {
		this.teamAName = teamAName;
	}

	public int getTeamAScore() {
		return teamAScore;
	}

	public void setTeamAScore(int teamAScore) {
		this.teamAScore = teamAScore;
	}

	public int getTeamBScore() {
		return teamBScore;
	}

	public void setTeamBScore(int teamBScore) {
		this.teamBScore = teamBScore;
	}

	public FootballClub getTeamB() {
		return teamB;
	}

	public void setTeamB(FootballClub teamB) {
		this.teamB = teamB;
	}
	public String getTeamBName() {
		return this.teamB.getName();
	}

	public void setTeamBName(String teamBName) {
		this.teamBName = teamBName;
	}

	@Override
	public String toString() {
		return  teamA.getName()+"<"+teamAScore+">"+" vs " + teamB.getName()+"<"+teamBScore +">"+"   "+date;
	}
}