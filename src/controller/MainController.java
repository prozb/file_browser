package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import modell.Record;
import view.Constants;

import java.io.*;
import java.util.*;

/**
 * @author p.rozbytskyi
 * @version 1.0.0
 * Class manages GUI
 */
public class MainController {
    @FXML private TableView<Record> prescription_table_view;
    @FXML private Label surgery_label;
    @FXML private Label prescription_label;
    @FXML private Button chose_path_button;
    @FXML private TextField path_field;
    @FXML private ListView<String> date_list_view;

    private List<Record> records;

    public void initialize(){
        this.records = new ArrayList<>();

        //setting to single selection mode
        date_list_view.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        date_list_view.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
        {
//            System.out.println("selected val: " + newValue);
            List<Record> choosen = new ArrayList<>();

            for(Record record : records){
                if(record.getDate().equals(newValue)){
                    choosen.add(record);
                }
            }

            showDataInTable(prescription_table_view, choosen);
        });
//        System.out.println("MainController initalization");
    }

    /**
     * Opening file dialog
     */
    public void openFileDialog() {
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
        SortedSet<String> dates = new TreeSet<>();
        listView.getItems().clear();
        dates.clear();
        for(Record record : records){
            dates.add(record.getDate());
        }
        listView.getItems().addAll(dates);
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
