package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import modell.Record;
import view.Constants;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class MainController {
    @FXML public TableView prescription_table_view;
    @FXML public Label surgery_label;
    @FXML public Label prescription_label;
    @FXML private Button chose_path_button;
    @FXML private TextField path_field;
    @FXML private ListView<String> date_list_view;

    private List<Record> records;

    public void initialize(){
        this.records = new ArrayList<>();

        //setting to single selection mode
        date_list_view.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        date_list_view.setOnMouseClicked(event ->
        {
            int index = date_list_view.getSelectionModel().getSelectedIndex();
            System.out.println("selected: " + index);

            Record selected      = records.get(index);
            List<Record> choosen = new ArrayList<>();

            for(Record record : records){
                if(record.getDate().equals(selected.getDate())){
                    choosen.add(record);
                }
            }

            showDataInTable(prescription_table_view, choosen);
        });
        System.out.println("MainController initalization");
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
                showDataInListView(date_list_view, records);

                prescription_label.setVisible(true);
                surgery_label.setVisible(true);
                prescription_table_view.setVisible(true);
            } catch (IOException e) {
                path_field.setText(Constants.error + ": " + Constants.file_not_found);
            }
        }else{
            path_field.setText(Constants.choose_file);
        }
    }

    private void showDataInTable(TableView<Record> prescription_table_view, List<Record> records){
        ObservableList<Record> data = prescription_table_view.getItems();
        data.clear();

        if(records != null){
            for(Record record : records) {
                addRecordTableView(record);
            }
        }
    }
    /**
     * Shows records in list view
     * @param listView list view to show records
     * @param records records to show
     */
    private void showDataInListView(ListView<String> listView, List<Record> records){
        for(Record record : records){
            listView.getItems().add(record.getDate());
        }
    }

    /**
     * Processing / parsing opened file and saving data records in records array list
     * @param file opened file
     * @throws IOException
     */
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
            }
        }
    }

    private void addRecordTableView(Record record){
        ObservableList<Record> data = prescription_table_view.getItems();
        data.add(record);
    }
}
