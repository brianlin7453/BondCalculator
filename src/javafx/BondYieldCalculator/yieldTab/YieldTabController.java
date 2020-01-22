package javafx.BondYieldCalculator.yieldTab;
import java.text.DecimalFormat;
import javafx.BondYieldCalculator.AppMessageDialogSingleton;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
public class YieldTabController {    @FXML

    // The reference of inputText will be injected by the FXML loader
    private TextField CouponTextField;
    @FXML
    // The reference of inputText will be injected by the FXML loader
    private TextField YearsTextField;
    @FXML
    // The reference of inputText will be injected by the FXML loader
    private TextField FaceTextField;
    @FXML
    // The reference of inputText will be injected by the FXML loader
    private TextField PriceTextField;
    @FXML
    // The reference of inputText will be injected by the FXML loader
    private TextField YieldTextField;

    @FXML
    private void clearInputs(){
        CouponTextField.setText("");
        YearsTextField.setText("");
        FaceTextField.setText("");
        PriceTextField.setText("");
        YieldTextField.setText("");
    }
    @FXML
    private void calculateYield(){
        System.out.println("a");
    }
}
