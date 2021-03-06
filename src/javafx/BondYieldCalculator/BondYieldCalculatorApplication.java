package javafx.BondYieldCalculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BondYieldCalculatorApplication extends Application {

    @Override
    public void start(Stage primaryStage)throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("bondyield_calculator.fxml"));

        //This is for the error message in case the user inputs non-numerical inputs
        AppMessageDialogSingleton messageDialog = AppMessageDialogSingleton.getSingleton();
        messageDialog.init(primaryStage);

        primaryStage.setTitle("Bond-Yield Calculator");
        primaryStage.setScene(new Scene(root,700,500));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    public static void main(String[]args){
        launch(args);
    }
}
