package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import view.Constants;

import java.io.File;

public class MainController {
    @FXML private Button chose_path_button;
    @FXML private TextField path_field;
    @FXML private ListView<String> date_list_view;

    public void initialize(){
        System.out.println("MainController initalization");
        date_list_view.getItems().addAll("LOL", "LKE", "EASY", "EASY", "EASY", "EASY");
        date_list_view.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    public void openFileDialog(ActionEvent actionEvent) {
        System.out.println("FileDialog opened");

        //creating extention filter
        FileChooser.ExtensionFilter extensionFilter =
                new FileChooser.ExtensionFilter(".csv files", "*.csv");
        //creating file chooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(extensionFilter);
        fileChooser.setTitle("Choose csv file");
        //receiving file
        File file = fileChooser.showOpenDialog(chose_path_button.getScene().getWindow());
        //handling file
        if(file != null){
            //TODO: handle file
            //TODO: show file dates in listview
            //TODO: show file name and path in textfield
            //TODO: show file prescriptions in tableview
        }else{
            path_field.setText(Constants.error + ": " + Constants.file_not_found);
        }
    }
}
