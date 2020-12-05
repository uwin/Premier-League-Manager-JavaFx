package Model;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class GuiJavaFx extends Application {
    Serialize load = new Serialize();
    ArrayList <Object> deserialized = load.deserialize();
    List<SportsClub> clublistData = (List<SportsClub>) deserialized.get(0);
    List<Match> matchlistData = (List<Match>) deserialized.get(1);

    @Override
    public void start(Stage primaryStage) {
        Stage window = new Stage();
        window.setTitle("Premier League");
        AnchorPane first = new AnchorPane();
        Scene addViewFirst = new Scene(first, 680, 450);
        addViewFirst.getStylesheets().add("style.css");
        window.setScene(addViewFirst);

        Label title = new Label("Club Details");
        title.setStyle("-fx-font: 20 arial;");
        AnchorPane.setTopAnchor(title,20d);
        AnchorPane.setLeftAnchor(title, 20d);

        TableView<SportsClub> clubTable = table();
        AnchorPane.setLeftAnchor(clubTable,20d);
        AnchorPane.setTopAnchor(clubTable,50d);
        AnchorPane.setBottomAnchor(clubTable,80d);

        Button sortGoals = new Button("Goals");
        sortGoals.setOnAction(event -> {
            Collections.sort(clublistData, new SortbyGoals());
            clubTable.setItems(dataToTable(clublistData));
        });
        AnchorPane.setLeftAnchor(sortGoals,20d);
        AnchorPane.setBottomAnchor(sortGoals,20d);

        Button sortWins = new Button("Wins");
        sortWins.setOnAction(event -> {
            Collections.sort(clublistData, new SortbyWins());
            clubTable.setItems(dataToTable(clublistData));
        });
        AnchorPane.setLeftAnchor(sortWins,90d);
        AnchorPane.setBottomAnchor(sortWins,20d);

        Button sortPoints = new Button("Reset");
        sortPoints.setOnAction(event -> {
            Collections.sort(clublistData, new SortByPoints());
            clubTable.setItems(dataToTable(clublistData));
        });
        AnchorPane.setLeftAnchor(sortPoints,160d);
        AnchorPane.setBottomAnchor(sortPoints,20d);

        Button genMatch = new Button("Add Match");
        genMatch.setOnAction(event -> {
            genarateMatch();
            Collections.sort(clublistData, new SortbyGoals());
            clubTable.setItems(dataToTable(clublistData));
        });
        AnchorPane.setRightAnchor(genMatch,20d);
        AnchorPane.setBottomAnchor(genMatch,20d);

        first.getChildren().addAll(clubTable,title,sortGoals,sortWins,sortPoints,genMatch);
        window.showAndWait();
    }

    public ObservableList<SportsClub> dataToTable(List<SportsClub> clubList){
        ObservableList<SportsClub> table = FXCollections.observableArrayList();
        table.addAll(clubList);
        return table;
    }

    public TableView<SportsClub> table(){
        Collections.sort(clublistData, new SortByPoints());

        TableView<SportsClub> clubTable = new TableView<>();
        TableColumn<SportsClub,String> clubName = new TableColumn<>("Club");
        clubName.setMinWidth(80);
        clubName.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<SportsClub,String> clubLoc = new TableColumn<>("Location");
        clubLoc.setMinWidth(80);
        clubLoc.setCellValueFactory(new PropertyValueFactory<>("location"));

        TableColumn<SportsClub,String> clubPoints = new TableColumn<>("Points");
        clubPoints.setMinWidth(80);
        clubPoints.setCellValueFactory(new PropertyValueFactory<>("pointCount"));

        TableColumn<SportsClub,String> clubGoals = new TableColumn<>("Goals");
        clubGoals.setMinWidth(80);
        clubGoals.setCellValueFactory(new PropertyValueFactory<>("goalsScored"));

        TableColumn<SportsClub,String> clubWins = new TableColumn<>("Wins");
        clubWins.setMinWidth(80);
        clubWins.setCellValueFactory(new PropertyValueFactory<>("wins"));

        TableColumn<SportsClub,String> clubLoss = new TableColumn<>("Loss");
        clubLoss.setMinWidth(80);
        clubLoss.setCellValueFactory(new PropertyValueFactory<>("defeat"));

        TableColumn<SportsClub,String> clubDraw = new TableColumn<>("Draw");
        clubDraw.setMinWidth(80);
        clubDraw.setCellValueFactory(new PropertyValueFactory<>("draws"));

        TableColumn<SportsClub,String> clubMatch = new TableColumn<>("Matches");
        clubMatch.setMinWidth(80);
        clubMatch.setCellValueFactory(new PropertyValueFactory<>("matchCount"));

        clubTable.setItems(dataToTable(clublistData));
        clubTable.getColumns().add(clubName);
        clubTable.getColumns().add(clubLoc);
        clubTable.getColumns().add(clubPoints);
        clubTable.getColumns().add(clubGoals);
        clubTable.getColumns().add(clubWins);
        clubTable.getColumns().add(clubLoss);
        clubTable.getColumns().add(clubDraw);
        clubTable.getColumns().add(clubMatch);

        return clubTable;
    }

    public int generateScore(){
        return (int) (Math.random() * ((20 - 1) + 1)) + 1;
    }
    public int generateTeam(){
        return (int) (Math.random() * (clublistData.size() - 1)+1);
    }

    public void genarateMatch() {
        PremierLeagueManager club = PremierLeagueManager.getInstance();

//            date
        LocalDate start = LocalDate.of(2020, Month.JANUARY, 1);
        long days = ChronoUnit.DAYS.between(start, LocalDate.now());
        LocalDate randomDate = start.plusDays(new Random().nextInt((int) days + 1));
//            score
        int oneClubScore = generateScore();
        int twoClubScore = generateScore();
//            team
        SportsClub oneClub = clublistData.get(generateTeam());
        SportsClub twoClub = clublistData.get(generateTeam());
        while (oneClub==twoClub){
//            System.out.println("repeat");
            twoClub = clublistData.get(generateTeam());
        }
        club.addMatch(randomDate, oneClub, oneClubScore, twoClub, twoClubScore);
        load.serialize(clublistData,matchlistData);

        Alert a = new Alert(Alert.AlertType.WARNING);
        a.setHeaderText("New Match ");
        a.setContentText("Team A :"+oneClub.getName()+
                        "\nTeam A Score :"+oneClubScore+
                        "\nTeam B :"+twoClub.getName()+
                        "\nTeam B Score :"+twoClubScore+
                        "\nDate : "+randomDate);
        a.showAndWait();
    }
}
