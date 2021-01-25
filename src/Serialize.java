import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Serialize implements Serializable {
	public void serialize(List<SportsClub> clubList,List<Match> matchList) {
//		ArrayList<Object> data=new ArrayList<>();

		try(FileOutputStream fileOut = new FileOutputStream("clubData.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut)
		){
			for (SportsClub sc: clubList) {
				out.writeObject(sc);
			}

			System.out.println("----|");
			System.out.println("Club Data Saved");

		} catch (IOException fe){
			System.out.println("#############ERROR###########");
			System.out.println("IOException");
//			  fe.printStackTrace();
		}
		try(FileOutputStream fileOut = new FileOutputStream("matchData.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut)
		){
			for (Match mt: matchList) {
				out.writeObject(mt);
			}

			System.out.println("Match Data Saved");
			System.out.println("----|");

		} catch (IOException fe){
			System.out.println("#############ERROR###########");
			System.out.println("IOException");
//			fe.printStackTrace();
		}

	}
	public ArrayList<ArrayList> deserialize() {
		ArrayList <ArrayList> deserialized = new ArrayList<>();
		ArrayList <SportsClub> clubData = new ArrayList<>();
		ArrayList <Match> matchData = new ArrayList<>();

		try(FileInputStream fileIn = new FileInputStream("clubData.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn)
		){

			while(in.available()>=0){
				clubData.add((SportsClub) in.readObject());
			}

		} catch (FileNotFoundException e) {
//			e.printStackTrace();
			System.out.println("----|");
			System.out.println("Data not available to load");
			System.out.println("----|");
			return deserialized;
		} catch (EOFException eo) {
//			end of file
		}catch (Exception c) {
//			c.printStackTrace();
			System.out.println("#############ERROR");
			System.out.println(c.toString());

		}
		System.out.println("----|");
		System.out.println("Club Data Loaded");

		try(FileInputStream fileIn = new FileInputStream("matchData.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn)
		){
			while(in.available()>=0){
				matchData.add((Match) in.readObject());
			}

		} catch (FileNotFoundException e) {
//			e.printStackTrace();
			System.out.println("----|");
			System.out.println("Data not available to load");
			System.out.println("----|");
			return deserialized;
		} catch (EOFException eo) {
//			end of file
		} catch (Exception c) {
			System.out.println(c.toString());
//			c.printStackTrace();
			System.out.println("#############ERROR");
			System.out.println(c.toString());
		}
		System.out.println("Match Data Loaded");
		System.out.println("----|");

		deserialized.add(clubData);
		deserialized.add(matchData);
		return deserialized;
	}
}