package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bo.BoFactory;
import lk.ijse.bo.custom.UserBo;
import lk.ijse.dto.UserDto;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class ChangePasswordController {
    public JFXButton closeConfirmVisible;
    public JFXButton confrimPasswordVisiblebtn;
    @FXML
    private AnchorPane root;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TextField txtConfirmPassword;

    @FXML
    private Label txtName;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtVisibleConfirmPassword;

    @FXML
    private TextField txtVisiblePassword;

    @FXML
    private JFXButton passwordVisiblebtn;

    @FXML
    private JFXButton visibleCloseBtn;

    UserBo userBo = (UserBo) BoFactory.getBOFactory().getBo(BoFactory.BoTypes.USER);

    @FXML
    void btnBooksOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/user_book.fxml"));

        Scene scene = new Scene(root);

        Stage primaryStage =(Stage) this.rootNode.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Book Worm");
    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/user_dashboard.fxml"));

        Scene scene = new Scene(root);

        Stage primaryStage =(Stage) this.rootNode.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Book Worm");
    }

    @FXML
    void btnPasswordOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/change_password.fxml"));

        Scene scene = new Scene(root);

        Stage primaryStage =(Stage) this.rootNode.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Book Worm");
    }

    @FXML
    void btnSignOutOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/user_login.fxml"));

        Scene scene = new Scene(rootNode);

        Stage primaryStage =(Stage) this.rootNode.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Book Worm");
    }

    @FXML
    void btnUpdatePasswordOnAction(ActionEvent event) {
        if(validateUser()){
            String userName = txtUsername.getText();
            String password = txtPassword.getText();
            String confirmPassword = txtConfirmPassword.getText();

            UserDto userDto = new UserDto(userName,password,confirmPassword);

            try {
                boolean isUpdate = userBo.updateUser(userDto);
                if(isUpdate){
                    clearFields();
                    new Alert(Alert.AlertType.CONFIRMATION,"Password Changed Successfull").show();
                }else{
                    new Alert(Alert.AlertType.ERROR,"Password Change Unsuccessfull").show();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public boolean validateUser(){
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
    void btnPasswordVisible(ActionEvent event) {
        txtVisiblePassword.setVisible(true);
        passwordVisiblebtn.setVisible(false);
        txtPassword.setVisible(false);
        visibleCloseBtn.setVisible(true);
    }

    @FXML
    void btnVisibleClose(ActionEvent event) {
        txtPassword.setVisible(true);
        txtVisiblePassword.setVisible(false);
        passwordVisiblebtn.setVisible(true);
        visibleCloseBtn.setVisible(false);
    }

    private void clearFields() {
        txtUsername.setText("");
        txtPassword.setText("");
        txtConfirmPassword.setText("");
    }

    public void btnConfirmVisibleClose(ActionEvent actionEvent) {
        txtConfirmPassword.setVisible(true);
        confrimPasswordVisiblebtn.setVisible(true);
        txtVisibleConfirmPassword.setVisible(false);
        closeConfirmVisible.setVisible(false);
    }

    public void btnConfirmPasswordVisible(ActionEvent actionEvent) {
        txtConfirmPassword.setVisible(false);
        confrimPasswordVisiblebtn.setVisible(false);
        txtVisibleConfirmPassword.setVisible(true);
        closeConfirmVisible.setVisible(true);
    }
}
