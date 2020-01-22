package javafx.BondYieldCalculator;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Modality;

public class AppMessageDialogSingleton extends Stage {
    static AppMessageDialogSingleton singleton = null;

    VBox messagePane;
    Scene messageScene;
    Label messageLabel;
    Button closeButton;


    private AppMessageDialogSingleton() {}

    public static AppMessageDialogSingleton getSingleton() {
        if (singleton == null)
            singleton = new AppMessageDialogSingleton();
        return singleton;
    }

    public void init(Stage owner) {
        initModality(Modality.WINDOW_MODAL);
        initOwner(owner);

        // LABEL TO DISPLAY THE CUSTOM MESSAGE
        messageLabel = new Label();

        // CLOSE BUTTON
        closeButton = new Button("Close");
        closeButton.setOnAction(e->{ AppMessageDialogSingleton.this.close(); });

        // WE'LL PUT EVERYTHING HERE
        messagePane = new VBox();
        messagePane.setAlignment(Pos.CENTER);
        messagePane.getChildren().add(messageLabel);
        messagePane.getChildren().add(closeButton);

        // MAKE IT LOOK NICE
        messagePane.setPadding(new Insets(80, 60, 80, 60));
        messagePane.setSpacing(20);

        // AND PUT IT IN THE WINDOW
        messageScene = new Scene(messagePane);
        this.setScene(messageScene);
    }

    public void show(String title, String message) {
        this.setResizable(false);
        // SET THE DIALOG TITLE BAR TITLE
        setTitle(title);

        // SET THE MESSAGE TO DISPLAY TO THE USER
        messageLabel.setText(message);

        // AND OPEN UP THIS DIALOG, MAKING SURE THE APPLICATION
        // WAITS FOR IT TO BE RESOLVED BEFORE LETTING THE USER
        // DO MORE WORK.
        showAndWait();
    }
}
