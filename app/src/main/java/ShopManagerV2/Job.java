package ShopManagerV2;

import java.io.Reader;
import java.util.ArrayList;

public class Job {
    int customerID;    //Froeign Key to Customer Table
    String carVIN;           //Foreign Key to Car Table
    int jobNumber;   //Primary Key for Job Table
    boolean invoiced;
    boolean paid;
    boolean taxable;
    ArrayList<Integer> items;   //foreign keys to Item Table
    
    public Job(int customerID, String carID, int jobNumber, boolean invoiced, boolean paid, boolean taxable,
            ArrayList<Integer> items) {
        this.customerID = customerID;
        this.carVIN = carID;
        this.jobNumber = jobNumber;
        this.invoiced = invoiced;
        this.paid = paid;
        this.taxable = taxable;
        this.items = items;
    }

    public Job(int custID, String carVIN, int jobNumber, boolean invoiced, boolean paid, boolean taxable, String items) {
        this.customerID = custID;
        this.carVIN = carVIN;
        this.jobNumber = jobNumber;
        this.invoiced = invoiced;
        this.paid = paid;
        this.taxable = taxable;
        String[] itemssplit = items.split(" ");
        for(String item : itemssplit){
            this.items.add(Integer.parseInt(item));
        }        
    }

    public String prepareItemsForSQL() {
        String SQLString = new String();
        for(int item : items){
            SQLString.concat(Integer.toString(item) + " ");
        }
        if(!SQLString.isBlank()){
            SQLString.stripTrailing();
        }
        
        
        return SQLString;
    }
}
