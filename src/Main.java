import javafx.application.Application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;



public class Main extends Application{

    Button button;
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception{
        stage.setTitle("Bond-Yield Calculator"); //Setting title to the Stage

        TabPane tabPane = new TabPane();
        tabPane.setMinSize(700,500);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        Tab bondTab = new Tab();
        bondTab.setText("Bond Calculator");
        Tab yieldTab = new Tab();
        yieldTab.setText("Yield Calculator");

        GridPane BondGridPane = getBondPane();
        GridPane YieldGridPane = getYieldPane();

        bondTab.setContent(BondGridPane);
        yieldTab.setContent(YieldGridPane);
        tabPane.getTabs().add(bondTab);
        tabPane.getTabs().add(yieldTab);

        Scene scene = new Scene(tabPane); //Creating a scene object
        stage.setScene(scene); //Adding scene to the stage
        stage.show(); //Displaying the contents of the stage
    }
    private static GridPane getBondPane(){
        GridPane BondGridPane = new GridPane();
        BondGridPane.setPadding(new Insets(10, 10, 10, 10));
        //Setting the vertical and horizontal gaps between the columns
        BondGridPane.setVgap(5);
        BondGridPane.setHgap(5);
        //Setting the Grid alignment
        BondGridPane.setAlignment(Pos.CENTER);

        Text BondCalcText = new Text("Bond Calculator");
        BondCalcText.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        Text CouponText = new Text("Coupon (enter decimal value)");
        Text YearsText = new Text("Years");
        Text FaceText = new Text("Face");
        Text RateText = new Text("Rate");
        Text BondResultText = new Text("Bond Price");

        TextField couponTextField = new TextField();
        TextField yearsTextField = new TextField();
        TextField FaceTextField = new TextField();
        TextField RateTextField = new TextField();
        TextField BondResultTextField = new TextField();

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        Button submitButton = new Button("Calculate");
        submitButton.setPrefSize(100, 20);
        Button clearButton = new Button("Clear");
        clearButton.setPrefSize(100, 20);
        hbox.getChildren().addAll(submitButton, clearButton);

        //Arranging all the nodes in the grid
        BondGridPane.add(BondCalcText, 0, 0);
        BondGridPane.add(CouponText, 0, 1);
        BondGridPane.add(couponTextField, 0,2);
        BondGridPane.add(YearsText, 0, 3);
        BondGridPane.add(yearsTextField, 0, 4);
        BondGridPane.add(FaceText, 0, 5);
        BondGridPane.add(FaceTextField, 0, 6);
        BondGridPane.add(RateText, 0, 7);
        BondGridPane.add(RateTextField, 0, 8);
        BondGridPane.add(hbox,0,9);
        BondGridPane.add(BondResultText,0,10);
        BondGridPane.add(BondResultTextField,0,11);

        return BondGridPane;
    }
    private static GridPane getYieldPane(){
        GridPane YieldgridPane = new GridPane();
        YieldgridPane.setPadding(new Insets(10, 10, 10, 10));
        //Setting the vertical and horizontal gaps between the columns
        YieldgridPane.setVgap(5);
        YieldgridPane.setHgap(5);
        //Setting the Grid alignment
        YieldgridPane.setAlignment(Pos.CENTER);

        Text YieldCalcText = new Text("Yield Calculator");
        YieldCalcText.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        Text CouponText = new Text("Coupon (enter decimal value)");
        Text YearsText = new Text("Years");
        Text FaceText = new Text("Face");
        Text PriceText = new Text("Price");
        Text BondResultText = new Text("Yield");

        TextField couponTextField = new TextField();
        TextField yearsTextField = new TextField();
        TextField FaceTextField = new TextField();
        TextField PriceTextField = new TextField();
        TextField YieldResultTextField = new TextField();

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        Button submitButton = new Button("Calculate");
        submitButton.setPrefSize(100, 20);
        Button clearButton = new Button("Clear");
        clearButton.setPrefSize(100, 20);
        hbox.getChildren().addAll(submitButton, clearButton);

        //Arranging all the nodes in the grid
        YieldgridPane.add(YieldCalcText, 0, 0);
        YieldgridPane.add(CouponText, 0, 1);
        YieldgridPane.add(couponTextField, 0,2);
        YieldgridPane.add(YearsText, 0, 3);
        YieldgridPane.add(yearsTextField, 0, 4);
        YieldgridPane.add(FaceText, 0, 5);
        YieldgridPane.add(FaceTextField, 0, 6);
        YieldgridPane.add(PriceText, 0, 7);
        YieldgridPane.add(PriceTextField, 0, 8);
        YieldgridPane.add(hbox,0,9);
        YieldgridPane.add(BondResultText,0,10);
        YieldgridPane.add(YieldResultTextField,0,11);

        return YieldgridPane;
    }
}
