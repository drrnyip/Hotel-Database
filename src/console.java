import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.Console;
import java.io.PrintStream;

import static javafx.application.Application.STYLESHEET_CASPIAN;

/**
 * Created by Darren on 5/4/2016.
 */
public class console {

    public static void display() {

        Stage primaryStage = new Stage();
        primaryStage.setTitle("Borders");
        Group root = new Group();
        Scene scene = new Scene(root, 1280, 720, Color.WHITE);

        GridPane gridpane = new GridPane();
        gridpane.setPadding(new Insets(5));
        gridpane.setHgap(10);
        gridpane.setVgap(10);

        TextArea cssEditorFld = new TextArea();
        Systemout console  = new Systemout(cssEditorFld);
        PrintStream ps = new PrintStream(console, true);
        System.setOut(ps);
        System.setErr(ps);

        cssEditorFld.setPrefRowCount(50);
        cssEditorFld.setPrefColumnCount(100);
        cssEditorFld.setWrapText(true);
        cssEditorFld.setPrefWidth(1000);
        GridPane.setHalignment(cssEditorFld, HPos.CENTER);
        gridpane.add(cssEditorFld, 0, 1);


        root.getChildren().add(gridpane);
        scene.getStylesheets().add("capitan.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}
