package at.htl.xam.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class WordController implements Initializable {

    // make variables

   //@FXML
   //private TableView<Word> tableView;
    //@FXML
    //private TableColumn<Word, String> germanWordColumn;
    @FXML
    private TextField englishWord;
    @FXML
    private TextField germanWord;
    @FXML
    private Button addButton;
    @FXML
    private Button switchButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // configure tableView



        //read Words from DB



        // set EventListeners



        // make Column editable


    }

    private void addWord(){

    }

    private void removeWord(){

    }

    private void validateTextFields(){

    }

    private void showInvalidInputAlert(String headerText, String contentText){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Invalid Input");
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    public void saveWord(){

    }
}
