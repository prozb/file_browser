package modell;

import javafx.beans.property.SimpleStringProperty;

/**
 * @author p.rozbytskyi
 * @version 1.0.0
 * Class describes object model
 */
public class Record {
    //records id
    private static int id = 0;
    private int recordId;

    private final SimpleStringProperty date     = new SimpleStringProperty("");
    private final SimpleStringProperty drugName = new SimpleStringProperty("");
    private final SimpleStringProperty count    = new SimpleStringProperty("");
    private final SimpleStringProperty price    = new SimpleStringProperty("");

    public Record(){
        this("", "", "", "");
    }
    public Record(String date, String drugName, String count, String price){
        this.recordId = id++;

        setCount(count);
        setDrugName(drugName);
        setPrice(price);
        setDate(date);
    }

    public int getRecordId(){
        return recordId;
    }

    public String getDate(){
        return  date.get();
    }

    public String getDrugName(){
        return drugName.get();
    }

    public String getCount(){
        return count.get();
    }

    public String  getPrice(){
        return price.get();
    }

    @Override
    public String toString() {
        return drugName + " | " + date + " | "  + count + " | " + price;
    }

    public void setDrugName(String drugName) {
        this.drugName.set(drugName);
    }

    public void setCount(String count) {
        this.count.set(count);
    }

    public void setPrice(String price) {
        this.price.set(price);
    }

    public void setDate(String date) {
        this.date.set(date);
    }
}
