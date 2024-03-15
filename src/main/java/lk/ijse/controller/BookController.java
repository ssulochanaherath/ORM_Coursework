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
import lk.ijse.bo.custom.BookBo;
import lk.ijse.dto.BookDto;
import lk.ijse.dto.tm.BookTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class BookController implements Initializable {
    @FXML
    private ComboBox<String> cmbGenre;

    @FXML
    private ComboBox<String> cmbStatus;

    @FXML
    private TableColumn<BookDto, String> colAuthor;

    @FXML
    private TableColumn<BookDto, String> colGenre;

    @FXML
    private TableColumn<BookDto, String> colStatus;

    @FXML
    private TableColumn<BookDto, String> colTitle;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<BookTm> tableBooks;

    @FXML
    private TextField txtAuthor;

    @FXML
    private Label txtName;

    @FXML
    private TextField txtTitle;

    @FXML
    private AnchorPane rootNode;

    BookBo bookBo = (BookBo) BoFactory.getBOFactory().getBo(BoFactory.BoTypes.BOOK);

    @FXML
    void btnBooksOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/books_form.fxml"));

        Scene scene = new Scene(root);

        Stage primaryStage =(Stage) this.rootNode.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Book Worm");
    }

    @FXML
    void btnBranchesOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/branch_form.fxml"));

        Scene scene = new Scene(root);

        Stage primaryStage =(Stage) this.rootNode.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Book Worm");
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String title = txtTitle.getText();

        try {
            boolean isDeleted = bookBo.deleteBook(title);

            if(isDeleted){
                clearFields();
                loadAllBooks();
                new Alert(Alert.AlertType.CONFIRMATION,"Book Deleted").show();
            }else{
                new Alert(Alert.AlertType.ERROR,"Book Deleting Unsuccessfull").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        if(validateBooks()){
            String title = txtTitle.getText();
            String author = txtAuthor.getText();
            String genre = cmbGenre.getValue();
            String status = cmbStatus.getValue();

            var dto = new BookDto(title,author,genre,status);

            try {
                boolean isSaved = bookBo.saveBook(dto);

                if(isSaved){
                    clearFields();
                    loadAllBooks();
                    new Alert(Alert.AlertType.CONFIRMATION,"Book Saved Successfully").show();
                }else{
                    new Alert(Alert.AlertType.ERROR,"Book Save Unsuccessfull").show();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String title = txtTitle.getText();

        BookDto bookDto = bookBo.existsBook(title);

        if(bookDto != null){
            txtAuthor.setText(bookDto.getAuthor());
            cmbGenre.setValue(bookDto.getGenre());
            cmbStatus.setValue(bookDto.getStatus());
        }else {
            new Alert(Alert.AlertType.ERROR,"Book Not Found").show();
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
        if(validateBooks()){
            String title = txtTitle.getText();
            String author =  txtAuthor.getText();
            String genre = cmbGenre.getValue();
            String status = cmbStatus.getValue();

            BookDto bookDto = new BookDto(title,author,genre,status);

            try {
                boolean isUpdate = bookBo.updateBook(bookDto);

                if(isUpdate){
                    clearFields();
                    loadAllBooks();
                    new Alert(Alert.AlertType.CONFIRMATION,"Book Updated").show();
                }else{
                    new Alert(Alert.AlertType.ERROR,"Book Update Unsuccessfull").show();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void btnUsersOnAction(ActionEvent event) {

    }

    public void btnDashboardOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/admin_dashboard.fxml"));

        Scene scene = new Scene(root);

        Stage primaryStage =(Stage) this.rootNode.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Book Worm");
    }

    public boolean validateBooks(){
        String title = txtTitle.getText();

        boolean isTitleValidated = Pattern.matches("[a-z][a-zA-Z\\s]+", title);
        if (!isTitleValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid Title!").show();
            return false;
        }

        String author = txtAuthor.getText();

        boolean isAuthorValidated = Pattern.matches("[a-z][a-zA-Z\\s]+", title);
        if (!isAuthorValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid Author!").show();
            return false;
        }

        String genre = cmbGenre.getValue();

        boolean isGenreValidated = Pattern.matches("Adventure|Novel|Thriller|Fiction|Historical|Fantasy|Poetry", genre);
        if (!isGenreValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid Genre!").show();
            return false;
        }

        String status = cmbStatus.getValue();

        boolean isStatusValidated = Pattern.matches("Available|Unavailable", status);
        if (!isStatusValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid Status!").show();
            return false;
        }

        return true;
    }

    private void loadGenre() {
        List<String> genre = Arrays.asList("Adventure", "Novel", "Thriller", "Fiction", "Historical", "Fantasy", "Poetry");
        ObservableList<String> genreList = FXCollections.observableArrayList(genre);
        cmbGenre.setItems(genreList);
    }

    private void loadStatus() {
        List<String> status = Arrays.asList("Available", "Unavailable");
        ObservableList<String> statusList = FXCollections.observableArrayList(status);
        cmbStatus.setItems(statusList);
    }

    private void clearFields() {
        txtTitle.setText("");
        txtAuthor.setText("");
        cmbGenre.setValue("");
        cmbStatus.setValue("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadGenre();
        loadStatus();
        tableListener();
        loadAllBooks();
        setCellValueFactory();
    }

    private void tableListener() {
        tableBooks.getSelectionModel().selectedItemProperty().addListener((observable, oldValued, newValue) -> {
            setData(newValue);
        });
    }

    private void setData(Object newValue) {
    }

    private void loadAllBooks(){
        ObservableList<BookTm> obList = FXCollections.observableArrayList();

        List<BookDto> dtoList = bookBo.getAllBooks();

        for(BookDto dto : dtoList){
            obList.add(new BookTm(
                    dto.getbId(),
                    dto.getTitle(),
                    dto.getAuthor(),
                    dto.getGenre(),
                    dto.getStatus()
            ));
        }
        tableBooks.setItems(obList);
        tableBooks.refresh();
    }

    private void setCellValueFactory(){
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }
}
