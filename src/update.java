import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.*;
import java.time.LocalDate;


public class update{

    public static void display(){

        Stage window = new Stage();
        window.resizableProperty().setValue(Boolean.FALSE);
        window.setTitle("Guest Database");

        Label label = new Label("Welcome to the Guest Database");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setHgap(12);
        grid.setVgap(10);

        //Labels & Textfields
        Label info = new Label("Search by Name, ID Number or Job Title");
        Label guestName = new Label("Guest Last Name (No spaces)");
        TextField guestInput = new TextField();
        guestInput.setPromptText("Name");
        Label guestID = new Label("Guest ID Number");
        TextField guestIDInput = new TextField();
        guestIDInput.setPromptText("ID Number");




        //Buttons
        Button searchguest = new Button("Search Last Name");
        searchguest.setOnAction(event -> {
            String inputA = guestInput.getText();
            for (int i = 0; i <inputA.length(); i++){
                if(Character.isLetter(inputA.charAt(i)) == false) {System.out.println("Error. Invalid input"); break;}
            }
            Connection conn1 = null;
            Statement stmt1 = null;
            ResultSet rs1 = null;

            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                String connURL = "jdbc:mysql://localhost:3306/hotel";
                String user = "root";
                String password = "toor";
                conn1 = DriverManager.getConnection(connURL,user,password);
                stmt1 = conn1.createStatement();
                rs1 = stmt1.executeQuery("Select * FROM guest WHERE '" + inputA + "' = last_name");
                boolean noEntry = true;

                while(rs1.next())
                {
                    int id = rs1.getInt("guest_id");
                    String fname = rs1.getString("first_name");
                    String lname = rs1.getString("last_name");
                    String contact = rs1.getString("contact_number");
                    System.out.println("ID: " + id + " | Name: " + lname + ", " + fname + " | Contact: " + contact);
                    noEntry = false;

                }
                if (noEntry == true) {System.out.println("No guest with this Name");}
                stmt1.close();
                conn1.close();

            }
            catch (Exception e){e.printStackTrace();}
        });
        Button searchguestID = new Button("Search ID");
        searchguestID.setOnAction(event -> {
            String inputB = guestIDInput.getText();
            for (int i = 0; i<inputB.length(); i++){
                if(Character.isDigit(inputB.charAt(i)) == false) {System.out.println("Error. Invalid input"); break;}
            }

            Connection conn1 = null;
            Statement stmt1 = null;
            ResultSet rs1 = null;

            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                String connURL = "jdbc:mysql://localhost:3306/hotel";
                String user = "root";
                String password = "toor";
                conn1 = DriverManager.getConnection(connURL,user,password);
                stmt1 = conn1.createStatement();
                rs1 = stmt1.executeQuery("Select * FROM guest WHERE guest_id = " + inputB + ";");
                boolean noEntry = true;

                while(rs1.next())
                {
                    int id = rs1.getInt("guest_id");
                    String fname = rs1.getString("first_name");
                    String lname = rs1.getString("last_name");
                    String contact = rs1.getString("contact_number");
                    System.out.println("ID: " + id + " | Name: " + lname + ", " + fname + " | Contact: " + contact);
                    noEntry = false;

                }
                if (noEntry == true) {System.out.println("No guest with this ID");}
                stmt1.close();
                conn1.close();

            }
            catch (Exception e){e.printStackTrace();}
        });

        Button titleSearch = new Button("Search Title");
        titleSearch.setOnAction(event -> {
            //choice box action here
        });

        Button displayguests = new Button("Display all guests");
        displayguests.setOnAction(event -> {
            Connection conn1 = null;
            Statement stmt1 = null;
            ResultSet rs1 = null;

            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                String connURL = "jdbc:mysql://localhost:3306/hotel";
                String user = "root";
                String password = "toor";
                conn1 = DriverManager.getConnection(connURL,user,password);
                stmt1 = conn1.createStatement();
                rs1 = stmt1.executeQuery("Select * FROM guest");

                while(rs1.next())
                {
                    int id = rs1.getInt("guest_id");
                    String fname = rs1.getString("first_name");
                    String lname = rs1.getString("last_name");
                    String contact = rs1.getString("contact_number");
                    System.out.println("ID: " + id + " | Name: " + lname + ", " + fname + " | Contact: " + contact);
                }
                stmt1.close();
                conn1.close();

            }
            catch (Exception e){e.printStackTrace();}
        });


        Button viewReservations = new Button("View All Reservations");
        viewReservations.setOnAction(event -> {

            try{
                Connection con = null;
                Connection con2 = null;
                Statement stmt = null;
                Statement stmt2 = null;
                ResultSet rs1 = null;
                ResultSet rs2 = null;


                String conURL = "jdbc:mysql://localhost:3306/hotel";
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                con = DriverManager.getConnection(conURL, "root", "toor");
                con2 = DriverManager.getConnection(conURL, "root", "toor");
                stmt = con.createStatement();
                stmt2 = con2.createStatement();
                rs1 = stmt.executeQuery("select * from guest natural join reservations;");

                while (rs1.next()){
                    int gid = rs1.getInt("guest_id");
                    String fname = rs1.getString("first_name");
                    String lname = rs1.getString("last_name");
                    String contact = rs1.getString("contact_number");
                    int roomID = rs1.getInt("room_id");
                    int reserveID = rs1.getInt("reservation_id");

                    rs2 = stmt2.executeQuery("select date from dates where reservation_id = " + reserveID);
                    Date start = null;
                    Date end = null;

                    while(rs2.next()){
                        if (start == null) {
                            start = rs2.getDate("date");
                        }
                        else {
                            end = rs2.getDate("date");
                        }
                    }

                    System.out.println("ID: " + gid + " | Name: " + lname + ", " + fname + " | Contact: " + contact);
                    System.out.println("Length of stay: " + start + " - " + end + " in Room " + roomID);
                    System.out.println("");
                }


            } catch (Exception g) {}


        });



        //Grid constraints
        grid.setConstraints(guestName,0,3);
        grid.setConstraints(guestInput,1,3);
        GridPane.setConstraints(searchguest,2,3);
        GridPane.setConstraints(guestID,0,4);
        GridPane.setConstraints(guestIDInput,1,4);
        GridPane.setConstraints(searchguestID,2,4);
        GridPane.setConstraints(info,0,0);
        GridPane.setConstraints(titleSearch,2,5);
        GridPane.setConstraints(displayguests,0,6);
        GridPane.setConstraints(viewReservations,1,6);


        grid.getChildren().addAll(guestInput,guestName,searchguest,guestID,guestIDInput,searchguestID, info,displayguests, viewReservations);
        Scene management = new Scene(grid, 800, 350);
        management.getStylesheets().add("capitan.css");
        window.setScene(management);
        window.showAndWait();

    }
}
