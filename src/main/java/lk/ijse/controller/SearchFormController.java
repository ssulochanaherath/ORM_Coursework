package lk.ijse.controller;

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
import lk.ijse.bo.custom.BookBo;
import lk.ijse.bo.custom.BorrowBookBo;
import lk.ijse.bo.custom.UserBo;
import lk.ijse.dto.BookDto;
import lk.ijse.dto.UserDto;
import lk.ijse.entity.User;

import java.io.IOException;
import java.sql.SQLException;

public class SearchFormController {
    @FXML
    private Label lblAuthor;

    @FXML
    private Label lblBookId;

    @FXML
    private Label lblBookName;

    @FXML
    private Label lblName;

    @FXML
    private Label lblStatus;

    @FXML
    private Label lblGenre;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtTitle;

    @FXML
    private TextField txtUsername;
    BorrowBookBo borrowBookBo = (BorrowBookBo) BoFactory.getBOFactory().getBo(BoFactory.BoTypes.BORROWEDBOOK);
    UserBo userBo = (UserBo) BoFactory.getBOFactory().getBo(BoFactory.BoTypes.USER);
    BookBo bookBo = (BookBo) BoFactory.getBOFactory().getBo(BoFactory.BoTypes.BOOK);

    @FXML
    void btnBorrowOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/confirm_card.fxml"));
        Parent rootNode = loader.load();

        Scene scene = new Scene(rootNode);

        Stage stage = new Stage();
        stage.setTitle("Book Worm");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void btnConfirmOnAction(ActionEvent event) {
        String title = txtTitle.getText();
        String userName = txtUsername.getText();

        try {
            BookDto book = bookBo.searchBook(title);
            UserDto userDto = userBo.getUserId(userName);

            if (userDto != null) {
                boolean success = borrowBookBo.placeBorrow(convertToUser(userDto), book);
                if (success) {
                    clearFields();
                    new Alert(Alert.AlertType.CONFIRMATION,"Book borrowed successfull").show();
                } else {
                    clearFields();
                    new Alert(Alert.AlertType.ERROR,"Book borrow Unsuccessfull").show();
                }
            } else {
                clearFields();
                new Alert(Alert.AlertType.ERROR,"Please log into Borrow a Book").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"An Error occurred when borrowing the book").show();
        }
    }

    private User convertToUser(UserDto userDto) {
        if(userDto == null){
            return null;
        }

        User user = new User();
        user.setuId(userDto.getuId());
        user.setUserName(userDto.getUserName());
        user.setPassword(userDto.getPassword());
        user.setConfirmPassword(userDto.getConfirmPassword());

        return user;
    }

    public void searchBookDetails(BookDto bookDto) {
        lblName.setText(bookDto.getTitle());
        lblBookId.setText(bookDto.getbId());
        lblBookName.setText(bookDto.getTitle());
        lblAuthor.setText(bookDto.getAuthor());
        lblGenre.setText(bookDto.getGenre());
        lblStatus.setText(bookDto.getStatus());
    }

    private void clearFields() {
        txtTitle.setText("");
        txtUsername.setText("");
    }
}
