import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Objects;

public class query{

    static LocalDate startOfStay;
    static LocalDate endOfStay;
    static ComboBox<String> roomTypes = new ComboBox<>();
    static String typeChoice;
    static Button createReservation;
    static LocalDate temp;

    static String fname;
    static String lname;
    static String contactInfo;
    static int roomChoice;
    static boolean confirmClicked = false;

    static String first = new String();
    static String last = new String();
    static String number = new String();
    static int id = 0;
    static int reservationID;
    static ArrayList<String> goodRooms = new ArrayList<>();




    public static void display(){

        Stage window = new Stage();
        GridPane grid = new GridPane();
        window.resizableProperty().setValue(Boolean.FALSE);
        window.setTitle("Check Room Availability");

        Button searchAvailability = new Button();
        createReservation = new Button("Confirm Room & Dates");
        createReservation.setVisible(false);

        searchAvailability.setText("Search");

        final DatePicker startDate = new DatePicker();
        startDate.setPromptText("Start of stay");

        final DatePicker endDate = new DatePicker();
        endDate.setPromptText("End of stay");

        roomTypes.setDisable(false);
        roomTypes.setPromptText("1. Select Room Type");
        roomTypes.getItems().removeAll(roomTypes.getItems());
        roomTypes.getItems().addAll("All","Suite", "Deluxe", "Economy");
        roomTypes.setValue("All");

        startDate.setOnAction(e -> {
            startOfStay = startDate.getValue();
            System.out.println("Selected date: " + startOfStay);
            endDate.setValue(startOfStay.plus(1, ChronoUnit.DAYS));
        });

        endDate.setOnAction(event -> {
            if (endDate.getValue().isBefore(startOfStay)){System.err.println("Invalid Date"); endDate.setValue(startOfStay.plus(1, ChronoUnit.DAYS)); return;}
                endOfStay = endDate.getValue();


        });



        //Button action
            searchAvailability.setOnAction(event -> {

                goodRooms.clear();



                confirmClicked = true;

                if (endDate.getValue() == null || startDate.getValue() == null){System.err.println("Please Select Dates"); return;}

                if (endDate.getValue().isBefore(startOfStay)){System.err.println("Invalid Date"); return;}

                    boolean noEntry = true;

                ComboBox<String> availableRooms = new ComboBox<>();
                availableRooms.setPromptText("2. Available Rooms");
                grid.setConstraints(availableRooms,1,2);
                grid.getChildren().add(availableRooms);
                startOfStay = startDate.getValue();
                endOfStay = endDate.getValue();

                typeChoice = roomTypes.getValue().toString();

                try {

                    Connection conn1 = null;
                    Statement stmt1 = null;
                    ResultSet rs1 = null;

                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    String connURL = "jdbc:mysql://localhost:3306/hotel";
                    String user = "root";
                    String password = "toor";

                    conn1 = DriverManager.getConnection(connURL,user,password);
                    stmt1 = conn1.createStatement();

                    int count = countDays(startOfStay,endOfStay);
                    fullRooms(count, startOfStay, endOfStay);

                    stmt1.close();
                    conn1.close();

                    boolean suite = false;
                    boolean deluxe = false;
                    boolean economy = false;
                    boolean all = false;

                    if (typeChoice.equals("Suite")) {suite = true;}
                    else if (typeChoice.equals("Deluxe")) { deluxe = true;}
                    else if (typeChoice.equals("Economy")) { economy = true;}
                    else if (typeChoice.equals("All")) { all = true;}

                    if (suite == true) {
                        if (checkGoodRooms(320) == true){
                            availableRooms.getItems().add("320");
                        }
                        if (checkGoodRooms(319) == true){
                            availableRooms.getItems().add("319");
                        }
                    }

                        else if (deluxe == true) {
                            if (checkGoodRooms(318) == true){
                                availableRooms.getItems().add("318");
                            }
                            if (checkGoodRooms(317) == true){
                                availableRooms.getItems().add("317");
                            }
                            if (checkGoodRooms(316) == true){
                                availableRooms.getItems().add("316");
                            }

                            if (checkGoodRooms(315) == true){
                                availableRooms.getItems().add("315");
                            }
                            if (checkGoodRooms(314) == true){
                                availableRooms.getItems().add("314");
                            }
                            if (checkGoodRooms(313) == true){
                                availableRooms.getItems().add("313");
                            }
                        }

                        else if (economy == true) {
                            if (checkGoodRooms(312) == true){
                                availableRooms.getItems().add("312");
                            }
                            if (checkGoodRooms(311) == true){
                                availableRooms.getItems().add("311");
                            }
                            if (checkGoodRooms(310) == true){
                                availableRooms.getItems().add("310");
                            }
                            if (checkGoodRooms(309) == true){
                                availableRooms.getItems().add("309");
                            }
                            if (checkGoodRooms(308) == true){
                                availableRooms.getItems().add("308");
                            }
                            if (checkGoodRooms(307) == true){
                                availableRooms.getItems().add("307");
                            }
                            if (checkGoodRooms(306) == true){
                                availableRooms.getItems().add("306");
                            }
                            if (checkGoodRooms(305) == true){
                                availableRooms.getItems().add("305");
                            }
                            if (checkGoodRooms(304) == true){
                                availableRooms.getItems().add("304");
                            }
                            if (checkGoodRooms(303) == true){
                                availableRooms.getItems().add("303");
                            }
                            if (checkGoodRooms(302) == true){
                                availableRooms.getItems().add("302");
                            }
                            if (checkGoodRooms(301) == true){
                                availableRooms.getItems().add("301");
                            }
                        }

                        else if (all == true) {
                            for (int f = 301; f <=320; f++){
                                if (checkGoodRooms(f) == true){
                                    availableRooms.getItems().add(Integer.toString(f));
                                }
                            }
                        }

                        noEntry = false;

                    if (noEntry == true) {System.out.println("No rooms available");}

                } catch (Exception f){};


                availableRooms.setVisible(true);

                createReservation.setVisible(true);


                //Reserve Function
                createReservation.setOnAction(e -> {
                    if (availableRooms.getValue() == null) {System.out.println("Please select Room"); return; }
                    else {
                        availableRooms.setDisable(true);
                        startDate.setDisable(true);
                        endDate.setDisable(true);
                        System.out.println("Room & Date reserved");
                        roomTypes.setDisable(true);
                        searchAvailability.setDisable(true);
                        roomChoice = Integer.parseInt(availableRooms.getValue());

                    }
                    createReservation.setDisable(true);
                });

            });

        grid.setPadding(new Insets(10,10,10,10));
        grid.setHgap(12);
        grid.setVgap(10);

        //Labels & Textfields
        Label info = new Label("1. Please select stay duration.");


        //Buttons



        //Grid constraints
        grid.setConstraints(info,0,0);
        grid.setConstraints(startDate,0,1);
        grid.setConstraints(roomTypes,0,2);
        grid.setConstraints(searchAvailability,0,3);
        grid.setConstraints(createReservation,0,4);
        grid.setConstraints(endDate,1,1);


        //Guests Information

        Label infoGuests1 = new Label("2. Please enter Guest Information.");
        Label infoGuests2 = new Label("For existing guests, search by Name");
        TextField firstName = new TextField();
        firstName.setPromptText("First name");
        TextField lastName = new TextField();
        lastName.setPromptText("Last name");
        Text guestID = new Text();
        guestID.setVisible(false);
        TextField contactInformation = new TextField();
        contactInformation.setPromptText("Contact number");
        TextField lastNameSearch = new TextField();
        lastNameSearch.setPromptText("Last Name");
        Label foundGuest = new Label();
        foundGuest.setVisible(false);


        //Buttons
        Button searchGuest = new Button("Search Guest");
        Button confirmOldGuest = new Button("Confirm Reservation");
        confirmOldGuest.setVisible(false);



        //Search guest button function
        searchGuest.setOnAction(event -> {
            lname = lastNameSearch.getText();

            try{
                Connection con = null;
                Statement stmt = null;
                ResultSet rs = null;
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                String user = "root";
                String password = "toor";
                String conURL = "jdbc:mysql://localhost:3306/hotel";

                con = DriverManager.getConnection(conURL, user, password);
                stmt = con.createStatement();

                rs = stmt.executeQuery("select * from guest where '" + lname + "' = last_name;");



                while (rs.next()){
                    first = rs.getString("first_name");
                    last = rs.getString("last_name");
                    number = rs.getString("contact_number");
                    id = rs.getInt("guest_id");
                    System.out.println(first + " " + last + " | Contact: " + number + " | ID: " + id);
                    foundGuest.setText(first + " " + last + " | Contact: " + number + " | ID: " + id);
                    foundGuest.setVisible(true);
                }

                stmt.close();

                confirmOldGuest.setVisible(true);
                confirmOldGuest.setOnAction(event1 -> {
                    checkInOldGuest(first,last,id,roomChoice,number);
                    window.close();
                });


            } catch (Exception g){};


        });

        Button createGuest = new Button("New Guest");

        //Create guest button function
        createGuest.setOnAction(event -> {

            if (confirmClicked == false) {System.out.println("Please confirm rooms first");} else {
                fname = firstName.getText();
                lname = lastName.getText();
                contactInfo = contactInformation.getText();

                try {


                    Connection conn1 = null;
                    Statement stmt1 = null;
                    ResultSet rs1 = null;
                    Connection conn2 = null;

                    //Guest counter
                    int count = 0;


                    Class.forName("com.mysql.jdbc.Driver").newInstance();

                    String connURL = "jdbc:mysql://localhost:3306/hotel";

                    String user = "root";
                    String password = "toor";

                    conn1 = DriverManager.getConnection(connURL, user, password);
                    conn2 = DriverManager.getConnection(connURL, user, password);
                    stmt1 = conn1.createStatement();

                    rs1 = stmt1.executeQuery("select * from guest");


                    while (rs1.next() == true) {
                        count = count + 1;
                    }

                    //Reservation ID
                    rs1 = stmt1.executeQuery("select * from reservations");

                    int reserveCount = 0;

                    while (rs1.next() == true) {
                        reserveCount = reserveCount + 1;
                    }

                    reservationID = 10 + reserveCount;

                    //Guest variables
                    int guestNumber = 1000 + count;

                    PreparedStatement statement = conn2.prepareStatement(
                            "insert into guest " + "(guest_id, first_name, last_name, contact_number)" + " values " + " (?,?,?,?);");

                    statement.setInt(1, guestNumber);
                    statement.setString(2, fname);
                    statement.setString(3, lname);
                    statement.setString(4, contactInfo);

                    PreparedStatement roomStatement = conn1.prepareStatement(
                            "insert into reservations values " + "(?,?,?);"
                    );

                    roomStatement.setInt(1, roomChoice);
                    roomStatement.setInt(2, guestNumber);
                    roomStatement.setInt(3, reserveCount + 10);
                    roomStatement.executeUpdate();
                    roomStatement.close();


                    System.out.println("Reservation table updated");

                    statement.executeUpdate();
                    statement.close();


                    //Dates table update;


                    LocalDate temp = startOfStay;

                    do {
                        PreparedStatement dateStatement = conn1.prepareStatement(
                                "insert into dates values" + "(?,?)"
                        );
                        dateStatement.setDate(1, java.sql.Date.valueOf(temp));
                        dateStatement.setInt(2, reserveCount + 10);
                        dateStatement.executeUpdate();
                        temp = temp.plusDays(1);
                    } while (temp.isBefore(endOfStay.plusDays(1)) == true);


                    //String update = "insert into guest values(" + guestNumber + ", \"" + fname + "\", \"" + lname + "\", \"" + contactInfo + \");";
                    //stmt1.executeQuery(update);

                } catch (Exception g){};

                createGuest.setDisable(true);
                window.close();
            } //end of if statement
        });



        //Grid constraints
        grid.setConstraints(infoGuests1,0,5);
        grid.setConstraints(infoGuests2,0,6);
        grid.setConstraints(firstName,0,7);
        grid.setConstraints(lastName,1,7);
        grid.setConstraints(contactInformation,2,7);
        grid.setConstraints(guestID,2,7);
        grid.setConstraints(searchGuest,2,9);
        grid.setConstraints(createGuest,0,8);
        grid.setConstraints(lastNameSearch,1,9);
        grid.setConstraints(foundGuest,0,9);
        grid.setConstraints(confirmOldGuest,1,10);



        //Grid children and set scene
        grid.getChildren().addAll(info, startDate, roomTypes, searchAvailability, createReservation,endDate);
        grid.getChildren().addAll(infoGuests1,infoGuests2,firstName,lastName,guestID,searchGuest,createGuest,contactInformation, lastNameSearch, foundGuest,confirmOldGuest);
        grid.setAlignment(Pos.CENTER);
        Scene query = new Scene(grid, 1000, 600);
        query.getStylesheets().add("capitan.css");
        window.setScene(query);
        window.showAndWait();

    }

    private static void checkInOldGuest(String first, String last, int id, int roomID, String contact){
        if (confirmClicked == false) {System.out.println("Please confirm rooms first");} else {

        try {
            Connection conn1 = null;
            Statement stmt1 = null;
            ResultSet rs1 = null;
            Connection conn3 = null;


            //Guest counter
            int count = 0;


            Class.forName("com.mysql.jdbc.Driver").newInstance();

            String connURL = "jdbc:mysql://localhost:3306/hotel";

            String user = "root";
            String password = "toor";

            conn1 = DriverManager.getConnection(connURL, user, password);
            stmt1 = conn1.createStatement();

            rs1 = stmt1.executeQuery("select * from reservations");

            int reserveCount = 0;

            while (rs1.next() == true) {
                reserveCount = reserveCount + 1;
            }

            PreparedStatement roomStatement = conn1.prepareStatement(
                    "insert into reservations values " + "(?,?,?);"
            );

            roomStatement.setInt(1, roomID);
            roomStatement.setInt(2, id);
            roomStatement.setInt(3, reserveCount + 1000);
            roomStatement.executeUpdate();
            roomStatement.close();

            System.out.println("Reservation table updated");

            //Dates table update;

            LocalDate temp = startOfStay;

            do {
                PreparedStatement dateStatement = conn1.prepareStatement(
                        "insert into dates values" + "(?,?)"
                );
                dateStatement.setDate(1, java.sql.Date.valueOf(temp));
                dateStatement.setInt(2, reserveCount + 1000);
                dateStatement.executeUpdate();
                temp = temp.plusDays(1);
            } while (temp.isBefore(endOfStay.plusDays(1)) == true);

            System.out.println("Dates table updated");

            //String update = "insert into guest values(" + guestNumber + ", \"" + fname + "\", \"" + lname + "\", \"" + contactInfo + \");";
            //stmt1.executeQuery(update);

        } catch (Exception g){ g.printStackTrace();}

            createReservation.setDisable(true);
    } //end of if statement
    }

    private static int countDays (LocalDate start, LocalDate end){
        int count = 0;
        LocalDate temp = start;
        while(temp.isBefore(end) == true) {
            count = count + 1;
            temp = temp.plusDays(1);
        }

        return count;
    }

    private static void fullRooms(int count, LocalDate start, LocalDate end){

        try{

            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String conURL = "jdbc:mysql://localhost:3306/hotel";
            String root = "root";
            String toor = "toor";
            con = DriverManager.getConnection(conURL, root, toor);
            stmt = con.createStatement();
            temp = start;

            for (int i = 0; i <= count + 1; i++) {
                System.out.println("Count: " + count);
                rs = stmt.executeQuery("select * from dates natural join reservations where date = '" + temp + "';");
                while(rs.next()){
                    String roomID = Integer.toString(rs.getInt("room_id"));
                    boolean dup = false;


                    if (goodRooms.size() == 0){
                        goodRooms.add(roomID);
                        System.out.println("First added: " + roomID);
                    }

                    else {
                        for (int j = 0; j <= goodRooms.size() -1; j++){
                            if (roomID.equals(goodRooms.get(j)) == true){
                                System.out.println("Size: " + j);
                                dup = true;
                            }
                        }
                        if (dup == false) {
                            goodRooms.add(roomID);
                            System.out.println("ROOMS: " + roomID);
                        }
                    }
                }
                temp = temp.plusDays(1);
            }

        } catch (Exception g) {};


    }

    private static boolean checkGoodRooms(int room) {
        String room2 = Integer.toString(room);
        boolean taken = true;
        for (int i = 0; i<= goodRooms.size() - 1; i++){
            String test = goodRooms.get(i).toString();
            if (room2.equals(test) == true) {
                taken = false;
                return taken;
            }
        }
        return taken;
    }

}
