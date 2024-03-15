package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bo.BoFactory;
import lk.ijse.bo.custom.AdminBo;
import lk.ijse.bo.custom.BranchBo;
import lk.ijse.dto.AdminDto;
import lk.ijse.dto.BranchDto;
import lk.ijse.dto.tm.BranchTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class BranchController implements Initializable {
    @FXML
    private ComboBox<String> cmbAdmin;

    @FXML
    private ComboBox<String> cmbStatus;

    @FXML
    private TableColumn<BranchDto, String> colAddress;

    @FXML
    private TableColumn<BranchDto, String> colbNumber;

    @FXML
    private TableColumn<BranchDto, String> colbId;

    @FXML
    private TableColumn<BranchDto, String> colStatus;

    @FXML
    private TableColumn<BranchDto, String> colAdminId;

    @FXML
    private AnchorPane root;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TableView<BranchTm> tableBranch;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtBookNumber;

    @FXML
    private TextField txtId;

    @FXML
    private Label txtName;
    BranchBo branchBo = (BranchBo) BoFactory.getBOFactory().getBo(BoFactory.BoTypes.BRANCH);
    AdminBo adminBo = (AdminBo) BoFactory.getBOFactory().getBo(BoFactory.BoTypes.ADMIN);
    @FXML
    void btnBooksOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/books_form.fxml"));

        Scene scene = new Scene(root);

        Stage primaryStage =(Stage) this.rootNode.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Book Worm");
    }

    @FXML
    void btnBranchesOnAction(ActionEvent event) {

    }

    @FXML
    void btnCloseOnAction(ActionEvent event) {
        String id = txtId.getText();

        try {
            boolean isDeleted = branchBo.deleteBranch(id);

            if(isDeleted){
                clearFields();
                loadAllBranches();
                new Alert(Alert.AlertType.CONFIRMATION,"Branch Deleted").show();
            }else{
                new Alert(Alert.AlertType.ERROR,"Branch Deleting Unsuccessfull").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/admin_dashboard.fxml"));

        Scene scene = new Scene(root);

        Stage primaryStage =(Stage) this.rootNode.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Book Worm");
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        if(validateBranch()){
            String id = txtId.getText();
            String address = txtAddress.getText();
            String noBooks = txtBookNumber.getText();
            String status = cmbStatus.getValue();
            String adminId = cmbAdmin.getValue();

            var dto = new BranchDto(id,address,noBooks,status,adminId);

            try {
                boolean isSaved = branchBo.saveBranch(dto);
                if(isSaved){
                    clearFields();
                    loadAllBranches();
                    new Alert(Alert.AlertType.CONFIRMATION,"Branch Saved").show();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String id = txtId.getText();

        BranchDto dto = branchBo.search(id);

        if(dto != null){
            txtAddress.setText(dto.getAddress());
            txtBookNumber.setText(dto.getbNumber());
            cmbStatus.setValue(dto.getStatus());
            cmbAdmin.setValue(dto.getAdminId());
            cmbAdmin.setValue(dto.getAdminId());
        }
    }

    @FXML
    void btnSignOutOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/admin_login.fxml"));

        Scene scene = new Scene(rootNode);

        Stage primaryStage =(Stage) this.rootNode.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Book Worm");
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        if(validateBranch()){
            String id = txtId.getText();
            String address = txtAddress.getText();
            String bNumber = txtBookNumber.getText();
            String status = cmbStatus.getValue();
            String adminId = cmbAdmin.getValue();

            BranchDto branchDto = new BranchDto(id,address,bNumber,status,adminId);

            try {
                boolean isUpdated = branchBo.updateBranch(branchDto);
                if(isUpdated){
                    clearFields();
                    loadAllBranches();
                    new Alert(Alert.AlertType.CONFIRMATION,"Branch Updated").show();
                }else{
                    new Alert(Alert.AlertType.ERROR,"Branch Update Unsuccessfull").show();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void btnUsersOnAction(ActionEvent event) {

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadAllAdminId();
        loadStatus();
        loadAllBranches();
        setCellValueFactory();
        tableListener();
    }

    private void loadAllAdminId(){
        ObservableList<String> obList = FXCollections.observableArrayList();

        List<AdminDto> idList = adminBo.getAllAdmin();

        for(AdminDto adminDto : idList){
            obList.add(adminDto.getAdminId());
        }
        cmbAdmin.setItems(obList);
    }

    public boolean validateBranch(){
        String id = txtId.getText();

        boolean isIdValidated = Pattern.matches("B-[0-9]{3,}", id);
        if (!isIdValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid ID!").show();
            return false;
        }

        String address = txtAddress.getText();

        boolean isAddressValidated = Pattern.matches("[a-z][a-zA-Z\\s]+", address);
        if (!isAddressValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid Address!").show();
            return false;
        }

        String noBooks = txtBookNumber.getText();

        boolean isNoBooksValidated = Pattern.matches("\\d*", noBooks);
        if (!isNoBooksValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid Book Number!").show();
            return false;
        }

        String status = cmbStatus.getValue();

        boolean isStatusValidated = Pattern.matches("Open|Close", status);
        if (!isStatusValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid Status!").show();
            return false;
        }

        String adminId = cmbAdmin.getValue();

        boolean isAdminIdValidated = Pattern.matches("A-[0-9]{3,}", adminId);
        if (!isAdminIdValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid Admin ID!").show();
            return false;
        }

        return true;
    }

    private void loadStatus() {
        List<String> status = Arrays.asList("Open", "Close");
        ObservableList<String> statusList = FXCollections.observableArrayList(status);
        cmbStatus.setItems(statusList);
    }

    private void clearFields() {
        txtId.setText("");
        txtAddress.setText("");
        txtBookNumber.setText("");
        cmbAdmin.setValue("");
        cmbStatus.setValue("");
    }

    private void tableListener() {
        tableBranch.getSelectionModel().selectedItemProperty().addListener((observable, oldValued, newValue) -> {
            setData(newValue);
        });
    }

    private void setData(Object newValue) {
    }

    private void loadAllBranches(){
        ObservableList<BranchTm> obList = FXCollections.observableArrayList();

        List<BranchDto> dtoList = branchBo.getAllBranches();

        for(BranchDto dto : dtoList){
            obList.add(new BranchTm(
                    dto.getbId(),
                    dto.getAddress(),
                    dto.getbNumber(),
                    dto.getStatus(),
                    dto.getAdminId()
            ));
        }
        tableBranch.setItems(obList);
        tableBranch.refresh();
    }

    private void setCellValueFactory(){
        colbId.setCellValueFactory(new PropertyValueFactory<>("bId"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colbNumber.setCellValueFactory(new PropertyValueFactory<>("bNumber"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colAdminId.setCellValueFactory(new PropertyValueFactory<>("adminId"));
    }
}
