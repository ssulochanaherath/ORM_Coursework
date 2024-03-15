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
import java.util.regex.Pattern;

public class AdminLoginController {

    @FXML
    private AnchorPane root;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;
    AdminBo adminBo = (AdminBo) BoFactory.getBOFactory().getBo(BoFactory.BoTypes.ADMIN);
    MainAdminController mainAdminController = new MainAdminController();
    @FXML
    void btnSignInOnAction(ActionEvent event) {
        if(validateAdmin()){
            String userName = txtUsername.getText();
            String password = txtPassword.getText();

            AdminDto adminDto = adminBo.adminSignIn(userName,password);
            if(adminDto != null){
                try {
                    clearFields();
                    new Alert(Alert.AlertType.CONFIRMATION,"Welcome Admin").show();
                    openWindow();
                    mainAdminController.setUserName(userName);
                } catch (IOException e) {
                    clearFields();
                    new Alert(Alert.AlertType.ERROR,"Username or Password Incorrect").show();
                }
            }
        }

    }

    @FXML
    void signUpChangeOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/admin_register.fxml"));

        Scene scene = new Scene(root);

        Stage primaryStage =(Stage) this.rootNode.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Book Worm");
    }

    @FXML
    void btnHomeOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/main_window.fxml"));

        Scene scene = new Scene(rootNode);

        Stage primaryStage =(Stage) this.rootNode.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Book Worm");
    }

    private void clearFields() {
        txtUsername.setText("");
        txtPassword.setText("");
    }

    public void openWindow() throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/admin_dashboard.fxml"));

        Scene scene = new Scene(rootNode);
        Stage primaryStage =(Stage) this.rootNode.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Book Worm");
    }

    public boolean validateAdmin(){
        String userName = txtUsername.getText();

        boolean isUserNameValidated = Pattern.matches("[A-Z][a-zA-Z\\s]+", userName);
        if (!isUserNameValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid UserName or Password!").show();
            return false;
        }

        String password = txtPassword.getText();

        boolean isPasswordValidated = Pattern.matches("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}", password);
        if (!isPasswordValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid Username or Password").show();
            return false;
        }

        return true;
    }
}
