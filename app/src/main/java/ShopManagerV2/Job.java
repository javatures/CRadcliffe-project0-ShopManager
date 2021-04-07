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
    
    
    public Job(int customerID, String carID, int jobNumber, boolean invoiced, boolean paid, boolean taxable,
            ArrayList<Integer> items) {
        this.customerID = customerID;
        this.carVIN = carID;
        this.jobNumber = jobNumber;
        this.invoiced = invoiced;
        this.paid = paid;
        this.taxable = taxable;
    }

    public Job(int custID, String carVIN, int jobNumber, boolean invoiced, boolean paid, boolean taxable) {
        this.customerID = custID;
        this.carVIN = carVIN;
        this.jobNumber = jobNumber;
        this.invoiced = invoiced;
        this.paid = paid;
        this.taxable = taxable;
    }

   
}
