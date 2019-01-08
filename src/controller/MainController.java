package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;

public class MainController {
    @FXML private Button chose_path_button;
    @FXML private TextField path_field;
    @FXML private ListView<String> date_list_view;

    public void initialize(){
        System.out.println("MainController ");
        date_list_view.getItems().addAll("LOL", "LKE", "EASY", "EASY", "EASY", "EASY");
        date_list_view.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    public void handleButtonClick(ActionEvent actionEvent) {
        System.out.println("button clicked");
    }
}
