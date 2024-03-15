package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bo.BoFactory;
import lk.ijse.bo.custom.BorrowBookBo;
import lk.ijse.dto.BorrowedBooksDto;
import lk.ijse.dto.tm.BorrowedBooksTm;
import lk.ijse.entity.Book;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

public class BorrowedBooksController implements Initializable {
    @FXML
    private TableColumn<BorrowedBooksDto, Book> colBookId;

    @FXML
    private TableColumn<BorrowedBooksDto, LocalDateTime> colBorrowedDate;

    @FXML
    private TableColumn<BorrowedBooksDto, LocalDateTime> colDueDate;


    @FXML
    private TableColumn<BorrowedBooksTm, JFXButton> colReturn;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<BorrowedBooksTm> tableBorrowedBooks;

    @FXML
    private Label txtName;

    @FXML
    private AnchorPane rootNode;

    BorrowBookBo borrowBookBo = (BorrowBookBo) BoFactory.getBOFactory().getBo(BoFactory.BoTypes.BORROWEDBOOK);

    @FXML
    void btnBooksOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/user_book.fxml"));

        Scene scene = new Scene(root);

        Stage primaryStage =(Stage) this.rootNode.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Book Worm");
    }

    @FXML
    void btnBorrowedOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/borrowed_books.fxml"));

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableListener();
        setCellValueFactory();
        loadAllBorrowedBooks();
    }

    private void tableListener() {
        tableBorrowedBooks.getSelectionModel().selectedItemProperty().addListener((observable, oldValued, newValue) -> {
            setData(newValue);
        });
    }

    private void setData(BorrowedBooksTm newValue) {
    }

    private void loadAllBorrowedBooks(){
        ObservableList<BorrowedBooksTm> obList = FXCollections.observableArrayList();

        List<BorrowedBooksDto> dtoList = borrowBookBo.getAllBorrowedBooks();

        for(BorrowedBooksDto dto : dtoList){
            JFXButton btnReturn = new JFXButton("Return");

            btnReturn.setStyle("-fx-background-color: #e67e22; -fx-text-fill: white; -fx-font-weight: bold;");
            btnReturn.setCursor(Cursor.HAND);
            colReturn.setStyle("-fx-alignment: CENTER;");
            btnReturn.setMaxWidth(100.0);

            obList.add(new BorrowedBooksTm(
                    dto.getBorrowedDate(),
                    dto.getReturnDate(),
                    dto.getBookId(),
                    btnReturn
            ));
        }
        tableBorrowedBooks.setItems(obList);
        tableBorrowedBooks.refresh();
    }

    private void setCellValueFactory() {
        colBorrowedDate.setCellValueFactory(new PropertyValueFactory<>("borrowedDate"));
        colDueDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        colBookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        colReturn.setCellValueFactory(new PropertyValueFactory<>("btnReturn"));
    }
}
