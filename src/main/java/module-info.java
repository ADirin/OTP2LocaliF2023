module com.example.otp2localdemo1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.otp2localdemo1 to javafx.fxml;
    exports com.example.otp2localdemo1;
}