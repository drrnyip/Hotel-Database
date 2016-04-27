import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class management{

    public static void display(){

        Stage window = new Stage();
        window.resizableProperty().setValue(Boolean.FALSE);
        window.setTitle("Management Database");

        Label label = new Label("Welcome to the Management Page");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setHgap(12);
        grid.setVgap(10);

        //Labels & Textfields
        Label info = new Label("Search by Name, ID Number or Job Title");
        Label employeeName = new Label("Employee Last Name (No spaces)");
        TextField employeeInput = new TextField();
        employeeInput.setPromptText("Name");
        Label employeeID = new Label("Employee ID Number");
        TextField employeeIDInput = new TextField();
        employeeIDInput.setPromptText("ID Number");
        Label jobTitle = new Label("Employee Job Title");
        ComboBox<String> jobChoice = new ComboBox<>();

        //Job title choicebox settings
        jobChoice.getItems().addAll("Manager", "Cleaner", "Porter", "Clerk");
        jobChoice.setPromptText("Job Title");



        //Buttons
        Button searchEmployee = new Button("Search Last Name");
        searchEmployee.setOnAction(event -> {
            String inputA = employeeInput.getText();
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
                rs1 = stmt1.executeQuery("Select * FROM staff WHERE '" + inputA + "' = staff_lastName");
                boolean noEntry = true;

                while(rs1.next())
                {
                    int id = rs1.getInt("staff_id");
                    String fname = rs1.getString("staff_firstName");
                    String lname = rs1.getString("staff_lastName");
                    double salary = rs1.getDouble("salary");
                    String shift = rs1.getString("shift");
                    System.out.println("ID: " + id + " | Name: " + lname + ", " + fname + " | Salary: " + salary + " | Shift: " + shift);
                    noEntry = false;

                }
                if (noEntry == true) {System.out.println("No employee with this Name");}
                stmt1.close();
                conn1.close();

            }
            catch (Exception e){e.printStackTrace();}
        });
        Button searchEmployeeID = new Button("Search ID");
        searchEmployeeID.setOnAction(event -> {
            String inputB = employeeIDInput.getText();
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
                rs1 = stmt1.executeQuery("Select * FROM staff WHERE staff_id = " + inputB + ";");
                boolean noEntry = true;

                while(rs1.next())
                {
                    int id = rs1.getInt("staff_id");
                    String fname = rs1.getString("staff_firstName");
                    String lname = rs1.getString("staff_lastName");
                    double salary = rs1.getDouble("salary");
                    String shift = rs1.getString("shift");
                    System.out.println("ID: " + id + " | Name: " + lname + ", " + fname + " | Salary: " + salary + " | Shift: " + shift);
                    noEntry = false;

                }
                if (noEntry == true) {System.out.println("No employee with this ID");}
                stmt1.close();
                conn1.close();

            }
            catch (Exception e){e.printStackTrace();}
        });

        Button titleSearch = new Button("Search Title");
        titleSearch.setOnAction(event -> {
            //choice box action here
        });

        Button displayEmployees = new Button("Display all Employees");
        displayEmployees.setOnAction(event -> {
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
                rs1 = stmt1.executeQuery("Select * FROM staff");

                while(rs1.next())
                {
                    int id = rs1.getInt("staff_id");
                    String fname = rs1.getString("staff_firstName");
                    String lname = rs1.getString("staff_lastName");
                    double salary = rs1.getDouble("salary");
                    String shift = rs1.getString("shift");

                    System.out.println("ID: " + id + " | Name: " + lname + ", " + fname + " | Salary: " + salary + " | Shift: " + shift);
                }
                stmt1.close();
                conn1.close();

            }
            catch (Exception e){e.printStackTrace();}
        });

        //Grid constraints
        grid.setConstraints(employeeName,0,3);
        grid.setConstraints(employeeInput,1,3);
        GridPane.setConstraints(searchEmployee,2,3);
        GridPane.setConstraints(employeeID,0,4);
        GridPane.setConstraints(employeeIDInput,1,4);
        GridPane.setConstraints(searchEmployeeID,2,4);
        GridPane.setConstraints(info,0,0);
        GridPane.setConstraints(jobTitle,0,5);
        GridPane.setConstraints(jobChoice,1,5);
        GridPane.setConstraints(titleSearch,2,5);
        GridPane.setConstraints(displayEmployees,0,6);


        grid.getChildren().addAll(employeeInput,employeeName,searchEmployee,employeeID,employeeIDInput,searchEmployeeID, info,displayEmployees);
        Scene management = new Scene(grid, 800, 350);
        management.getStylesheets().add("capitan.css");
        window.setScene(management);
        window.showAndWait();

    }
}
