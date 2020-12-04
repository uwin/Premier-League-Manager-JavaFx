package Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Serialize implements Serializable {
	public void serialize(List<SportsClub> clubList,List<Match> matchList) {
		ArrayList<Object> data=new ArrayList<>();

		try(FileOutputStream fileOut = new FileOutputStream("data.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut)
		){  data.add(clubList);
			data.add(matchList);
			out.writeObject(data);

			System.out.println("----|");
			System.out.println("Data Saved");
			System.out.println("----|");

		} catch (IOException fe){
			  fe.printStackTrace();
		}
	}
	public ArrayList<Object> deserialize() {
		ArrayList <Object> deserialized = null;
		PremierLeagueManager club = PremierLeagueManager.getInstance();

		try(FileInputStream fileIn = new FileInputStream("data.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn)
		){
			deserialized = (ArrayList<Object>) in.readObject();


			System.out.println("----|");
			System.out.println("Data Loaded");
			System.out.println("----|");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("----|");
			System.out.println("Data not available to load");
			System.out.println("----|");
			return deserialized;
		} catch (Exception c) {
			c.printStackTrace();
		}
		return deserialized;
	}
}