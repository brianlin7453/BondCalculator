package javafx.BondYieldCalculator.bondTab;
import java.text.DecimalFormat;

import javafx.BondYieldCalculator.AppMessageDialogSingleton;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
public class BondTabController {
    @FXML
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
    private TextField RateTextField;
    @FXML
    // The reference of inputText will be injected by the FXML loader
    private TextField BondPriceTextField;

    @FXML
    private void clearInputs(){
        CouponTextField.setText("");
        YearsTextField.setText("");
        FaceTextField.setText("");
        RateTextField.setText("");
        BondPriceTextField.setText("");
    }

    @FXML
    private void calculateBondPrice() {
        String message = "Invalid Input(s):";
        double coupon = 0;
        double years = 0;
        double face = 0;
        double rate = 0;
        boolean isValid = true;
        try{
            coupon = Double.parseDouble(CouponTextField.getText());
        }
        catch (Exception e) {
            isValid = false;
            message += "\n COUPON";
        }
        try {
            years = Double.parseDouble(YearsTextField.getText());
        } catch (Exception e) {
            isValid = false;
            message += "\n YEARS";
        }
        try {
            face = Double.parseDouble(FaceTextField.getText());
        } catch (Exception e) {
            isValid = false;
            message += "\n FACE";
        }
        try {
            rate = Double.parseDouble(RateTextField.getText());
        } catch (Exception e) {
            isValid = false;
            message += "\n RATE";
        }
        if (!isValid) {
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
            dialog.show("Invalid Input Message", message);
        } else {
            double resultPrice = 0.0;
            for (int year = 1; year <= years; year++) {
                double cashFlow = coupon * face;
                double rateRaisedByYears = Math.pow(1 + rate, year);
                resultPrice += cashFlow / rateRaisedByYears;
            }
            resultPrice += face / Math.pow(1 + rate, years);
            DecimalFormat decFor = new DecimalFormat("0.0000000");
            BondPriceTextField.setText(decFor.format(resultPrice));
        }
    }
}
