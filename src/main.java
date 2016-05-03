import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class main extends Application {

    static boolean result;

    public static void main(String [] args){launch(args);};

    @Override
    public void start(Stage primaryStage) throws Exception {

        setUserAgentStylesheet(STYLESHEET_CASPIAN);
        primaryStage.resizableProperty().setValue(Boolean.FALSE);

        //hotel photo
        Image hotel = new Image("hotel_cartoon.png");
        ImageView hotel2 = new ImageView(hotel);


        //exit request
//        primaryStage.setOnCloseRequest(e -> {
//            e.consume();
//            confirmExit();
//            if (result == true) {primaryStage.close();}
//        });

        primaryStage.setTitle("Hotel Database");
        Button button1 = new Button();
        button1.setText("Check In Guest");
        button1.setOnAction(event -> {
            query.display();
        });

        Button button2 = new Button();
        button2.setText("Check Guest Database");
        button2.setOnAction(event -> {
            update.display();
        });

        Button button3 = new Button();
        button3.setText("Management");
        button3.setOnAction(event -> {
            management.display();
        });

        //primary layout
        VBox layout = new VBox();
        layout.setSpacing(15);
        layout.setPadding(new Insets(15, 12, 15, 12));
        layout.setAlignment(Pos.CENTER);

        //put all elements into layout here
        layout.getChildren().addAll(hotel2,button1,button2,button3);

        //Scene

        Scene scene = new Scene(layout, 300, 450);
        scene.getStylesheets().add("capitan.css");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private boolean confirmExit(){
        Stage exitWindow = new Stage();
        exitWindow.resizableProperty().setValue(Boolean.FALSE);
        exitWindow.setTitle("Confirm Exit?");
        VBox exitLayout = new VBox();
        Label exitLabel = new Label("Are you sure you want to exit?  ");
        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");
        HBox buttonBox = new HBox();

        exitWindow.setOnCloseRequest(e -> {
            e.consume();
            result = false;
            exitWindow.close();
        });

        yesButton.setOnAction(e -> {
            result = true;
            exitWindow.close();
        });

        noButton.setOnAction(e -> {
            result = false;
            exitWindow.close();
        });

        buttonBox.getChildren().addAll(yesButton,noButton);
        buttonBox.setAlignment(Pos.CENTER);
        exitLayout.getChildren().addAll(exitLabel,buttonBox);
        exitLayout.setAlignment(Pos.CENTER);
        Scene exitScene = new Scene(exitLayout, 300, 150);
        exitWindow.setScene(exitScene);
        exitScene.getStylesheets().add("capitan.css");
        exitWindow.initModality(Modality.APPLICATION_MODAL);
        exitWindow.showAndWait();

        if (result == true) {return true;} else {return false;}
    }
}
