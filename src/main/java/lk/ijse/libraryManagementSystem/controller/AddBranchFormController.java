package libraryManagementSystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lk.ijse.libraryManagementSystem.bo.BranchBo;
import lk.ijse.libraryManagementSystem.bo.impl.BranchBoImpl;
import lk.ijse.libraryManagementSystem.dto.BranchDto;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AddBranchFormController {

    @FXML
    private Button addBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private Label labelAddNewBranch;

    @FXML
    private Label labelDate;

    @FXML
    private Label labelId;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtName;
    BranchBo branchBo= new BranchBoImpl();
    public void initialize() throws SQLException, IOException{
        generateNextId();
        labelDate.setText(String.valueOf(LocalDate.now()));
    }

    private void generateNextId() {
        String branchId = branchBo.generateNewBranchId();
        labelId.setText(branchId);
    }
    private boolean checkDuplicates() {
        List<BranchDto> branchDtos = branchBo.loadAllBranch();
        for (int i=0; i<branchDtos.size();i++){
            if(branchDtos.get(i).getId().equals(labelId.getText())){
                return true;
            }
        }
        return false;

    }
    @FXML
    void addBtnOnAction(ActionEvent event) throws IOException {
        boolean checked = checkDuplicates();
        if (checked){

            String addressText = txtAddress.getText();
            String contactText = txtContact.getText();
            String emailText = txtEmail.getText();
            String nameText = txtName.getText();
            String idText = labelId.getText();
            boolean saved = branchBo.updateBranch(new BranchDto(idText, nameText, addressText, contactText, emailText,new ArrayList<>(),new ArrayList<>()));
            if (saved){
                new Alert(Alert.AlertType.CONFIRMATION,"Successfully Updated").show();
            }
            else {
                new Alert(Alert.AlertType.ERROR,"Can not Update").show();
            }
        }
        else {
            String addressText = txtAddress.getText();
            String contactText = txtContact.getText();
            String emailText = txtEmail.getText();
            String nameText = txtName.getText();
            String idText = labelId.getText();
            boolean saved = branchBo.saveBranch(new BranchDto(idText, nameText, addressText, contactText, emailText,new ArrayList<>(),new ArrayList<>()));
            if (saved){
                new Alert(Alert.AlertType.CONFIRMATION,"Successfully Saved").show();
            }
            else {
                new Alert(Alert.AlertType.ERROR,"Can not Save").show();
            }
        }



    }

    @FXML
    void cancelBtnOnAction(ActionEvent event) {

    }
}
