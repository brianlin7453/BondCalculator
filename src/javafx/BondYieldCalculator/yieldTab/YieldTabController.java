package javafx.BondYieldCalculator.yieldTab;
import java.text.DecimalFormat;

import javafx.BondYieldCalculator.AppMessageDialogSingleton;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
public class YieldTabController {

    @FXML
    private TextField CouponTextField;
    @FXML
    private TextField YearsTextField;
    @FXML
    private TextField FaceTextField;
    @FXML
    private TextField PriceTextField;
    @FXML
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
        long startTime = System.nanoTime();
        String message = "Invalid Input(s):";
        double coupon = 0;
        double years = 0;
        double face = 0;
        double price = 0;
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
            price = Double.parseDouble(PriceTextField.getText());
        } catch (Exception e) {
            isValid = false;
            message += "\n PRICE";
        }
        if (!isValid) {
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
            dialog.show("Invalid Input Message", message);
        } else {
            DecimalFormat decFor = new DecimalFormat("0.0000000");
            if(price == face){
                YieldTextField.setText(decFor.format(coupon));
                long endTime = System.nanoTime();
                long duration = (endTime - startTime);
                System.out.println(duration);
                return;
            }
            if(face > price){//Lower bound for YTM is coupon
                YieldTextField.setText(decFor.format(discountBondYieldCalc(coupon,years,face,price)));
            }
            else{//Upper bound for YTM is coupon when face < price
                YieldTextField.setText(decFor.format(premiumBondYieldCalc(coupon,years,face,price)));
            }
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println(duration);
    }

    //Bond calculation
    private double calculateBondPrice(double coupon, double years, double face, double rate){
        double resultPrice = 0.0;
        for (int year = 1; year <= years; year++) {
            double cashFlow = coupon * face;
            double rateRaisedByYears = Math.pow(1 + rate, year);
            resultPrice += cashFlow / rateRaisedByYears;
        }
        resultPrice += face / Math.pow(1 + rate, years);
        return resultPrice;
    }
    //Upper bound is coupon because price > face
    private double premiumBondYieldCalc(double coupon, double years, double face, double price){
        double YTM = coupon; //Start at coupon rate
        double incrementRate = -0.1; //We want to first go down because its a premiumbond
        for(int step=1;step<=8;step++){ // end at 8 because we want 10^-7 accuracy
            double bondPriceCalc = calculateBondPrice(coupon,years,face,YTM);
            if(step%2!=0){ //If the step is odd then we want the calculated price to be lower than the given price
                while(bondPriceCalc < price){
                    bondPriceCalc=calculateBondPrice(coupon,years,face,YTM);
                    YTM += incrementRate;//Adjust increment to get closer to price
                }
            }
            else{//If the step is odd then we want the calculated price to be higher than the given price
                while(bondPriceCalc > price){
                    bondPriceCalc=calculateBondPrice(coupon,years,face,YTM);
                    YTM += incrementRate;
                }
            }
            incrementRate=incrementRate/-10;
        }
        return YTM;
    }
    //LOWER bound is coupon because face > price
    private double discountBondYieldCalc(double coupon, double years, double face, double price){
        double YTM = coupon;    //Set lower bound to coupon rate
        double incrementRate = 0.1; //Increment in a postive direction first
        for(int step=1;step<=8;step++){ //Each step: divide incremate rate by -10 and to invert direction
            double bondPriceCalc = calculateBondPrice(coupon,years,face,YTM); //Calculate new bond price
            if(step%2==0){ //If step is even then we need to check if calculated bond price is lower than the price
                while(bondPriceCalc < price){ //Keep decrementing the calculated price until its lower than the price
                    bondPriceCalc=calculateBondPrice(coupon,years,face,YTM);
                    YTM += incrementRate; //Adjust increment rate
                }
            }
            else{
                while(bondPriceCalc > price){ //If step is odd then we need to check if price is higher
                    bondPriceCalc=calculateBondPrice(coupon,years,face,YTM);
                    YTM += incrementRate;
                }
            }
            incrementRate=incrementRate/-10;//At the end of each step we need to invert the increment rate and tunnel decimal accuracy
        }
        return YTM;
    }

}

