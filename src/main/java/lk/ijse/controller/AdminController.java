package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bo.BoFactory;
import lk.ijse.bo.custom.AdminBo;
import lk.ijse.dto.AdminDto;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class AdminController {
    @FXML
    private AnchorPane root;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private PasswordField txtConfirmPassword;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    AdminBo adminBo = (AdminBo) BoFactory.getBOFactory().getBo(BoFactory.BoTypes.ADMIN);

    @FXML
    void btnSIgnInOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/admin_login.fxml"));

        Scene scene = new Scene(root);

        Stage primaryStage =(Stage) this.rootNode.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Book Worm");
    }

    @FXML
    void btnSIgnUpAction(ActionEvent event) {
        if(validateAdmin()){
            String userName = txtUsername.getText();
            String password = txtPassword.getText();
            String confirmPassword = txtConfirmPassword.getText();

            var dto = new AdminDto(userName,password,confirmPassword);
            try {
                boolean isSaved = adminBo.saveAdmin(dto);

                if(isSaved){
                    clearFields();
                    new Alert(Alert.AlertType.CONFIRMATION,"Your have created an account successfully").show();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void clearFields() {
        txtUsername.setText("");
        txtPassword.setText("");
        txtConfirmPassword.setText("");
    }



    @FXML
    void btnHomeOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/main_window.fxml"));

        Scene scene = new Scene(rootNode);

        Stage primaryStage =(Stage) this.rootNode.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Book Worm");
    }

    public boolean validateAdmin(){
        String userName = txtUsername.getText();

        boolean isUserNameValidated = Pattern.matches("[A-Z][a-zA-Z\\s]+", userName);
        if (!isUserNameValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid UserName!").show();
            return false;
        }

        String password = txtPassword.getText();

        boolean isPasswordValidated = Pattern.matches("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}", password);
        if (!isPasswordValidated) {
            new Alert(Alert.AlertType.ERROR, "Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters").show();
            return false;
        }

        String confirmPassword = txtConfirmPassword.getText();

        boolean isconfirmPasswordValidated = Pattern.matches("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}", password);
        if (!isconfirmPasswordValidated) {
            new Alert(Alert.AlertType.ERROR, "Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters").show();
            return false;
        }

        return true;
    }

    @FXML
    void btnShowPassword(ActionEvent event) {

    }
}
