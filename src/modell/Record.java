package modell;

public class Record {
    //records id
    private static int id = 0;

    private String drugName;
    private String date;
    private String count;
    private String price;
    private int recordId;

    public Record(String date, String drugName, String count, String price){
        this.recordId = id++;

        this.count    = count;
        this.drugName = drugName;
        this.price    = price;
        this.date     = date;
    }

    public int getRecordId(){
        return recordId;
    }

    public String getDate(){
        return date;
    }

    public String getDrugName(){
        return drugName;
    }

    public String getCount(){
        return count;
    }

    public String  getPrice(){
        return price;
    }

    @Override
    public String toString() {
        return drugName + " | " + date + " | "  + count + " | " + price;
    }
}
