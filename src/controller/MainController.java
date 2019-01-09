package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import modell.Record;
import view.Constants;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MainController {
    @FXML private Button chose_path_button;
    @FXML private TextField path_field;
    @FXML private ListView<String> date_list_view;
    private List<Record> records;

    public void initialize(){
        this.records = new ArrayList<>();

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
            path_field.setText(file.getAbsolutePath());
            try {
                processFile(file);
                //TODO: show file dates in listview
                //TODO: show file prescriptions in tableview
            } catch (IOException e) {
                path_field.setText(Constants.error + ": " + Constants.file_not_found);
            }
        }else{
            path_field.setText(Constants.error + ": " + Constants.file_not_found);
        }
    }

    private void processFile(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));

        records.clear();
        String line;
        //skip the header
        reader.readLine();
        while ((line = reader.readLine()) != null){
            String [] data = line.split(Constants.default_deliminer);

            if(data.length == 4){
                //save data to object
                Record record = new Record(data[0], data[1], data[2], data[3]);
                records.add(record);

                System.out.println("added: " + record);
            }
        }
    }
}
