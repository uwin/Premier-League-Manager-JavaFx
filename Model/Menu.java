package Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;

public class Menu{
    PremierLeagueManager club = PremierLeagueManager.getInstance();

	public static void main(String [] args){
        Menu begin = new Menu();
        begin.load();
        begin.menuCli();
	}
    public void menuCli (){
        Scanner sc = new Scanner(System.in);
        System.out.println(" --------------------------------");
        System.out.println(" 1 | Create Football club");
        System.out.println(" 2 | Delete Football club");
        System.out.println(" 3 | Display statistics");
        System.out.println(" 4 | Display Premier league Table");
        System.out.println(" 5 | Played match");
        System.out.println(" 6 | Exit");
        System.out.println(" --------------------------------");
        System.out.println("Select Option");
        String selection;
        selection = sc.next();

        switch (selection){
            case "1":
                createClub();
                pause();
                break;
            case "2":
                deleteClub();
                pause();
                break;
            case "3":
                viewClub();
                pause();
                break;
            case "4":
                displayTable();
                pause();
                break;
            case "5":
                addMatch();
                pause();
                break;
            case "6":
                save();
                System.exit(0);
            default:
                menuCli();
                break;
        }
    }
    public void pause(){
	    Scanner sc = new Scanner(System.in);
	    System.out.print("\ncontinue... (press any key) ");
	    String in = sc.next();
	    if (!in.isEmpty()) menuCli();
    }

    public void createClub() {
        List <SportsClub> clubList = club.getClubList();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Name for Club");
        String name = sc.nextLine();

        for(SportsClub clubLoop: clubList){
            if (clubLoop.getName().equalsIgnoreCase(name)){
                System.out.println("the Club already exists");
                createClub();
                return;
            }
        }

        System.out.println("Enter Location for Club");
        String location = sc.nextLine();

        club.createClub(name,location);
        System.out.println("----|");
        System.out.println("Added club : "+ name);
        System.out.println("----|");
    }
    public void deleteClub() {
        List <SportsClub> clubList = club.getClubList();
        System.out.println("Select Name for Club");
        int selectedClub = SelectTeam();
        String Name = clubList.get(selectedClub).getName();

        club.deleteClub(selectedClub);
        System.out.println("----|");
        System.out.println("Deleted club : "+Name);
        System.out.println("----|");

    }
    public void viewClub() {
    	List <SportsClub> clubList = club.getClubList();

	    if(clubList.size()<=0){
            System.out.println("no clubs in league");
            pause();
		    return;
	    }
        System.out.println("Select Name for Club");
        club.viewClub(SelectTeam());

    }
    public void displayTable() {

        List <SportsClub> clubList=club.getClubList();
        if(clubList.size()<=0){
            System.out.println("no clubs in league");
            pause();
            return;
        }
//        TODO
//        Collections.sort(clubList,byPointCount());

        Collections.reverse(clubList);
        String sFormat = "|%1$-16s|%2$-10s|%3$-10s|%4$-10s|%5$-10s|%6$-10s|\n";
        System.out.format(sFormat,"Name","Points","Goals","Wins","Loss","Matches");
        for (SportsClub l: clubList){
            System.out.format(sFormat,l.getName(),l.getPointCount(),((FootballClub)l).getGoalsScored(),l.getWins(),l.getDefeat(),l.getMatchCount());
        }
    }
    public void addMatch() {

    	List<SportsClub> clubList = club.getClubList();
    	List<Match> matchList = club.getMatchList();

    	if(clubList.size()<=1){
            System.out.print("you need atleast 2 club to add a match");
            pause();
            return;
        }

    	Scanner sct = new Scanner(System.in);

        System.out.println("Enter date [DD/MM/YYYY]: ");
        String line = sct.nextLine();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate dateL;
        while(true){
        try {
            dateL = LocalDate.parse(line, dateFormat);
            break;
        } catch (Exception ex) {
            System.out.println("incorrect format, try again");
            line = sct.nextLine();
            }
        }


        System.out.println(" --------------------------------");
        System.out.println("select First club name");
        SportsClub oneClub = clubList.get(SelectTeam());
		
		System.out.println(" --------------------------------");
        System.out.println("select Second club name");
        SportsClub twoClub = clubList.get(SelectTeam());
        

        while (oneClub==twoClub){
        	System.out.println(" --------------------------------");
            System.out.println("| >|Select a different team|< |");
            twoClub = clubList.get(SelectTeam());

        }

        Scanner sc = new Scanner(System.in);

        System.out.println("enter Score for "+oneClub.getName());
        while (!sc.hasNextInt()){
            String  b = sc.next();
            System.out.println("enter in number");
        }
        int oneClubScore = sc.nextInt();


        System.out.println("enter Score for "+twoClub.getName());
        while (!sc.hasNextInt()){
            String  b = sc.next();
            System.out.println("enter in number");
        }
        int twoClubScore = sc.nextInt();

        club.addMatch(dateL,oneClub,oneClubScore,twoClub,twoClubScore);
        save();
    }
    public void save()  {
        club.save();
    }
    public void load() {
	    club.load();
    }

    public int SelectTeam (){
        ArrayList <String> tempMenu = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int i=0;

        List<SportsClub>  clubList=club.getClubList();
        for (SportsClub l: clubList){
            System.out.println(i +"| "+ l.getName());
            tempMenu.add(String.valueOf(i));
            i++;
        }
        String selectClub = sc.next();
        while(!tempMenu.contains(selectClub)){
        	System.out.println("wrong input");
            selectClub = sc.next();
            System.out.println(selectClub);
        }
	    return Integer.parseInt(selectClub);
    }

}