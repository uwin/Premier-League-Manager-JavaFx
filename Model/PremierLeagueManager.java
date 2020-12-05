package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class PremierLeagueManager implements LeagueManager{

	static List <SportsClub> clubList= new ArrayList<>();
	static List <Match> matchList = new ArrayList<>();

	private static PremierLeagueManager instance= null;

	private PremierLeagueManager(){}
	public static PremierLeagueManager getInstance(){
		if (instance== null){
			synchronized (PremierLeagueManager.class){
				if(instance==null)instance = new PremierLeagueManager();
			}
		}return instance;
	}

	public static void main(String[]args) {}

	@Override
	public void createClub(String name, String location) {
		SportsClub newClub = new FootballClub(name,location);
		clubList.add(newClub);
	}

	@Override
	public void deleteClub(int index) {
		clubList.remove(index);
	}
	@Override
	public void viewClub(int index) {
		SportsClub clubP = clubList.get(index);
        System.out.println(clubP);
	}
	@Override
	public void displayTable() { }
	@Override
	public void addMatch(LocalDate dateL, SportsClub oneClub, int oneClubScore, SportsClub twoClub, int twoClubScore) {

		oneClub.setMatchCount(oneClub.getMatchCount()+1);
		twoClub.setMatchCount(twoClub.getMatchCount()+1);

		oneClub.setScore(oneClub.getScore()+oneClubScore);
		twoClub.setScore(twoClub.getScore()+twoClubScore);

		if (oneClubScore >twoClubScore) {
			oneClub.setWins(oneClub.getWins()+1);
			oneClub.setPointCount(oneClub.getPointCount()+3);
			twoClub.setDefeat(twoClub.getDefeat()+1);
		}else if (twoClubScore > oneClubScore){
			twoClub.setWins(twoClub.getWins()+1);
			twoClub.setPointCount(twoClub.getPointCount()+3);
			oneClub.setDefeat(oneClub.getDefeat()+1);
		}else {
			oneClub.setDraws(oneClub.getDraws()+1);
			twoClub.setDraws(twoClub.getDraws()+1);
			oneClub.setPointCount(oneClub.getPointCount()+1);
			twoClub.setPointCount(twoClub.getPointCount()+1);
		}

		((FootballClub) oneClub).setGoalsScored(((FootballClub) oneClub).getGoalsScored()+oneClubScore);
		((FootballClub) twoClub).setGoalsReceived(((FootballClub) twoClub).getGoalsReceived()+oneClubScore);

		((FootballClub) twoClub).setGoalsScored(((FootballClub) twoClub).getGoalsScored()+twoClubScore);
		((FootballClub) oneClub).setGoalsReceived(((FootballClub) oneClub).getGoalsReceived()+twoClubScore);

		Match match = new Match();
		match.setDate(dateL);
		match.setTeamA((FootballClub) oneClub);
		match.setTeamB((FootballClub) twoClub);
		match.setTeamAScore(oneClubScore);
		match.setTeamBScore(twoClubScore);
		matchList.add(match);
		System.out.println("match = " + match);
	}
	@Override
	public void save() {
		Serialize save = new Serialize();
		save.serialize(clubList,matchList);
	}
	@Override
	public void load() {
		Serialize load = new Serialize();
		ArrayList <Object> deserialized = load.deserialize();

		if (deserialized!=null) {
			setClubList((List<SportsClub>) deserialized.get(0));
			setMatchList((List<Match>) deserialized.get(1));
		}
	}

	public List<SportsClub> getClubList() {
		return clubList;
	}
	public List<Match> getMatchList() { return matchList; }

	public static void setClubList(List<SportsClub> clubList) {
		PremierLeagueManager.clubList = clubList;
	}

	public static void setMatchList(List<Match> matchList) {
		PremierLeagueManager.matchList = matchList;
	}
}