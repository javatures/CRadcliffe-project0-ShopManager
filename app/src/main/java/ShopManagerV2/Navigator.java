package ShopManagerV2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Navigator {
    Scanner input;
    Connection server;
    double taxrate;
    public Navigator(Scanner input , Connection server) {
        this.input = input;
        this.server = server;
        taxrate = 8.75;
    }
    

    public int submenu(int menu){
        int submenu = Integer.parseInt(input.nextLine());
        switch(menu){
            case 1:
                switch(submenu){
                    case 1:
                        printCustomerList();
                        break;
                    case 2:

                        printCustomerInvoices();
                        break;
                    case 3:
                        addCustomer();
                        break;
                    case 4:
                        updateCustomer();
                        break;
                    case 5:
                        viewCustomerInformation();
                        break;
                    case 6:
                        return 0;
                    }
                System.out.println("\nPress Enter to Return to Customer Menu:");
                input.nextLine();
                break;
            case 2:
                switch(submenu){
                    case 1:
                        viewAllJobs();
                        break;
                    case 2:
                        viewQuoted();
                        break;
                    case 3:
                        viewInvoiced();
                        break;
                    case 4:
                        viewUnpiad();
                        break;
                    case 5:
                        viewJob();
                        break;
                    case 6:
                        startJob();
                        break;
                    case 7:
                        invoiceJob();
                        break;
                    case 8:
                        markPayed();
                        break;
                    case 9:
                        System.out.println("Returning to Main Menu");
                        return 0;

                }
                System.out.println("\nPress Enter to Return to Invoice Menu:");
                input.nextLine();
                break;
            case(3):
                switch(submenu){
                    case 1:
                        viewAllVehicles();
                        break;
                    case 2:
                        viewVehicle();
                        break;
                    case 3:
                        addVehicle();
                        break;
                    case 4:
                        modifyVehicle();
                        break;
                    default:
                        System.out.println("Returning to Main Menu");
                        return 0;
                }
            System.out.println("\nPress Enter to Return to Vehicle Menu:");
            input.nextLine();
            break;

        }
        return menu;
    }


    private void modifyVehicle() {
        CarSQL server = new CarSQL(this.server);
        List<Car> cars = server.getAll();
        for( int i = 0 ; i < cars.size() ; i++){
            System.out.println(i + 1 + ") " + cars.get(i).state + " " + cars.get(i).licensePlate + " " + cars.get(i).year + " " + cars.get(i).make + " " + cars.get(i).model);
        }
        System.out.println("Select Vehicle Number:");
        int id =Integer.parseInt(input.nextLine());
        try {
            ResultSet response  = server.customQuery("select * from cars full join customers on owner_id = id where vin = \'" + cars.get(id - 1).vin + "\'");
            if(response.next()){
            System.out.println("Vehicle owner: " + response.getString("fName") + " " + response.getString("lName"));
            System.out.println("Year/Make/Model: " + response.getString("year") + " " + response.getString("make") + " " + response.getString("model"));
            System.out.println("VIN: " + response.getString("vin") );
            System.out.println("License: " + response.getString("license_plate_state") + " " + response.getString("license_plate_number"));
            System.out.println("\n1) Transfer Ownership\n2) Correct Year\n3) Correct Make\n4) Correct Model\n5) Correct VIN\n6) Change Licence Plate\n7) Cancel\n\nEnter Option");            
            int ans = Integer.parseInt(input.nextLine());
            
            switch(ans){
                case 1:
                    printCustomerList();
                    System.out.println("Select Customer to Transfer Vehicle to:");
                    ans = Integer.parseInt(input.nextLine());
                    server.customUpdate("update cars set owner_id = " + ans + " where vin = \'" + response.getString("vin") + "\'");
                    break;

                case 2:
                    System.out.println("enter correct Year:");
                    String year = input.nextLine();
                    server.customUpdate("update cars set year = \'" + year +  "\' where vin = \'" + response.getString("vin") + "\'");
                    break;
                
                case 3:
                    System.out.println("enter correct Make:");
                    String make = input.nextLine();
                    server.customUpdate("update cars set make = \'" + make + "\' where vin = \'" + response.getString("vin") + "\'");
                    break;
                
                case 4:
                    System.out.println("enter correct Model:");
                    String model = input.nextLine();
                    server.customUpdate("update cars set model = \'" + model + "\' where vin = \'" + response.getString("vin") + "\'");
                    break;
                
                case 5:
                    System.out.println("enter correct VIN:");
                    String vin = input.nextLine();
                    server.customUpdate("update cars set vin = \'" + vin + "\' where vin = \'" + response.getString("vin") + "\'");
                    break;

                case 6:
                    System.out.println("enter correct state:");
                    String state = input.nextLine();
                    System.out.println("Enter Correct License Number");
                    String number = input.nextLine();
                    server.customUpdate("update cars set license_plate_state = \'" + state + "\', license_plate_number = \'" + number + "\' where vin = \'" + response.getString("vin") + "\'");
                    break;    
                
                default:
                    break;
            }
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



        //TODO: finish
    }


    private void addVehicle() {
        printCustomerList();
        System.out.println("Enter Customer Number. If Customer does not appear, please add them from the Customer Menu");
        String ans = input.nextLine();
        CustomerSQL cList = new CustomerSQL(this.server);
        List<Customer> custs = cList.getFiltered(ans, 1);
        System.out.println("Enter Vehicle Year:");
        String year =  input.nextLine();
        System.out.println("Enter Vehicle Make:");
        String make = input.nextLine();
        System.out.println("Enter Vehicle Model:");
        String model =  input.nextLine();
        System.out.println("Enter VIN:");
        String vin = input.nextLine();
        System.out.println("Enter License Plate State:");
        String lpS = input.nextLine();
        System.out.println("Enter License Plate Number:");
        String lpN = input.nextLine();
        CarSQL carList = new CarSQL(this.server);
        carList.create(new Car(custs.get(0).id, year, make, model, vin, lpS, lpN));

    }


    private void viewVehicle() {
        CarSQL server = new CarSQL(this.server);
        List<Car> cars = server.getAll();
        for( int i = 0 ; i < cars.size() ; i++){
            System.out.println(i +1 + ") " + cars.get(i).state + " " + cars.get(i).licensePlate + " " + cars.get(i).year + " " + cars.get(i).make + " " + cars.get(i).model);
        }
        System.out.println("Select Vehicle Number:");
        int id =Integer.parseInt(input.nextLine());
        cars = server.getFiltered(cars.get(id-1).vin , 5);
        try {
            ResultSet response  = server.customQuery("select * from cars full join customers on owner_id = id");
            if(response.next()){
            System.out.println("Vehicle owner: " + response.getString("fName") + " " + response.getString("lName"));
            System.out.println("Year/Make/Model: " + response.getString("year") + " " + response.getString("make") + " " + response.getString("model"));
            System.out.println("VIN: " + response.getString("vin") );
            System.out.println("License: " + response.getString("license_plate_state") + " " + response.getString("license_plate_number"));
            printInvoiceList(response.getString("vin") , 1);            
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
 
        

    }


    private void printInvoiceList(String string, int i) throws SQLException {
        System.out.println("Invoice List:");
        CarSQL server = new CarSQL(this.server);
        ResultSet response = server.customQuery("select * from cars full join jobs on vin=car_id");

        while(response.next()){
            
            System.out.println(response.getInt("job_number"));
        }
        

        
    }


    private void viewAllVehicles() {

        CarSQL server = new CarSQL(this.server);
        String sql = "Select * from cars full join customers on id=owner_id order by owner_id";
       
        int id = -1;
        try {
            ResultSet results = server.customQuery(sql);
            while(results.next()){
                if(results.getString("year") != null){
                    if(id != results.getInt("owner_id")){
                        System.out.println(results.getInt("owner_id") + ") " + results.getString("fName") + " " + results.getString("lName"));
                        id = results.getInt("owner_id");
                    }
                    System.out.println("\n\t" + results.getString("year") + " " + results.getString("make") + " " + results.getString("model"));
                    System.out.println("\tVIN: " + results.getString("vin"));
                    System.out.println("\tLicense Plate: " + results.getString("license_plate_state") + " " + results.getString("license_plate_number") + "\n");
                }
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    private void markPayed() {
        viewUnpiad();
        System.out.println("Enter Invoice number:");
        int inv = Integer.parseInt(input.nextLine());
        JobSQL server = new JobSQL(this.server);
        List<Job> jobs = server.getFiltered(Integer.toString(inv), 3);
        Job job = jobs.get(0);
        job.paid = true;
        server.update(job, 4);
    }


    private void invoiceJob() {
        viewQuoted();
        System.out.println("Enter Invoice number:");
        int inv = Integer.parseInt(input.nextLine());
        JobSQL server = new JobSQL(this.server);
        List<Job> jobs = server.getFiltered(Integer.toString(inv), 3);
        Job job = jobs.get(0);
        job.invoiced = true;
        server.update(job, 3);
    }


    private void startJob() {
        printCustomerList();
        System.out.println("\nSelect Customer to Assign Job to:");
        int cust =Integer.parseInt(input.nextLine());
        CarSQL carsList = new CarSQL(this.server);
        List<Car> garage = carsList.getFiltered(Integer.toString(cust), 1);
        for(int i = 0 ; i < garage.size() ; i++){
            if(garage.get(i).vin != null){
                System.out.println((i+1) + ") " + garage.get(i).toString());
            }            
        }
        System.out.println("Select Vehicle to Work on:");
        int vehicle = Integer.parseInt(input.nextLine());
        Car car = garage.get(vehicle-1);
        boolean more = true;
        ArrayList<Item> items = new ArrayList<Item>();

        while(more){
            System.out.println("Enter Item Number:");
            String itemNumber = input.nextLine();
            System.out.println("Enter Item Description:");
            String itemDescription = input.nextLine();
            System.out.println("Is this Item Taxable? (Y/N)" );
            Boolean taxable = "Y".equalsIgnoreCase(input.nextLine());
            System.out.println("Enter Item Unit Cost:");
            float cost = Float.parseFloat(input.nextLine());
            System.out.println("Enter Item Quantity:");
            float quantity = Float.parseFloat(input.nextLine());
            items.add(new Item(0, 0, itemNumber, itemDescription, taxable, cost, quantity));
            float subtotal = 0;
            for(int i = 0 ; i < items.size() ; i++){
                System.out.println((i+1) + ") Quantity: " + items.get(i).quantity + "\tCost: " + items.get(i).cost + "\tItem Number: " + items.get(i).itemNumber);
                subtotal += (items.get(i).cost * items.get(i).quantity);  
            }
            System.out.println("Current Sub Total: " +  subtotal);
            System.out.println("\nWould you like to add more Items? (Y/N)");
            more = "Y".equalsIgnoreCase(input.nextLine());
        }
        System.out.println("Is this Job Taxable? (Y/N)");
        boolean taxable = "Y".equalsIgnoreCase(input.nextLine());
        System.out.println("Would you like to Save this job? (Y/N)");
        Boolean save = "Y".equalsIgnoreCase(input.nextLine());
        if(save){
            JobSQL jobList = new JobSQL(this.server);
            jobList.create(new Job(cust, car.vin, 0 , false, false, taxable));
            ResultSet jobnumber = jobList.customQuery("select job_number from jobs order by job_number desc limit 1");
            try {
                jobnumber.next();
                for(Item item : items){
                
                    item.jobNumber = jobnumber.getInt("job_number");
                    ItemsSQL itemList = new ItemsSQL(this.server);
                    itemList.create(item);
                }
            } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }
        }
    }
        


    private void viewJob() {
        viewAllJobs();
        System.out.println("Enter Invoice number:");
        int inv = Integer.parseInt(input.nextLine());
        JobSQL server = new JobSQL(this.server);
        List<Job> jobs = server.getFiltered(Integer.toString(inv), 3);
        Job job = jobs.get(0);

        ResultSet response = server.customQuery("select * from customers where id = " + job.customerID + "order by id");
        try {
            while(response.next()){
                System.out.println("Customer: " + response.getString("fName") + " " + response.getString("lName"));
                System.out.println("Phone Number: " + response.getString("phone_number"));
            }
            response = server.customQuery("select * from cars where vin = \'" + job.carVIN + "\'");
            while(response.next()){
                System.out.println("\nYear/Make/Model: " + response.getString("year") + " " + response.getString("make") + " " + response.getString("model"));
                System.out.println("VIN: " + response.getString("vin"));
                System.out.println("License Plate: " + response.getString("license_plate_state") + " " + response.getString("license_plate_number"));
            }
            response = server.customQuery("select * from items where job_id = " + job.jobNumber + " order by id");
            float taxable = 0;
            float nontaxable = 0;
            Float temp;
            DecimalFormat sigfig = new DecimalFormat("#.00");
            while(response.next()){
                temp = response.getFloat("unit_cost") * response.getFloat("quantity");
                System.out.println("\tExtened Price: " + sigfig.format(temp) + 
                                    "\tQuantity: " + response.getFloat("quantity") + 
                                    "\tUnit Cost: " + sigfig.format(response.getFloat("unit_cost")) +
                                    "\t\tItem Number: " + response.getString("item_number") +
                                    "\tDescription: " + response.getString("item_description"));
                if(response.getBoolean("taxable"))
                    taxable += temp;
                else
                    nontaxable += temp;
            }
            System.out.println("Subtotal: " +sigfig.format((taxable + nontaxable)));
            System.out.println("Tax: " + sigfig.format((taxable * (taxrate / 100.0))));
            System.out.println("Grand Total: "+ sigfig.format(nontaxable + (taxable * (1 + taxrate/100.0))) + "\n\n");


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        

    }


    private void viewUnpiad() {
        JobSQL server = new JobSQL(this.server);
        ResultSet jobs = server.customQuery("select * from jobs full join customers on id = customer_id order by id");
        try {
            int id = 0;
            while(jobs.next()){
                
                if(jobs.getString("id") != null){

                    if(jobs.getInt("customer_id") != id){
                        id = jobs.getInt("customer_id");
                        System.out.println(jobs.getInt("id") + ") " + jobs.getString("fName") + " " + jobs.getString("lName"));
                    }
                    if(!jobs.getBoolean("paid") & jobs.getBoolean("invoiced"))
                        System.out.println("\t" + jobs.getInt("job_number") + ") Invoiced: " + ( jobs.getBoolean("invoiced") ? "Yes" : "No") + " Paid: " + (jobs.getBoolean("paid") ? "Yes" : "No"));

                }
                
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    private void viewInvoiced() {
        JobSQL server = new JobSQL(this.server);
        ResultSet jobs = server.customQuery("select * from jobs full join customers on id = customer_id order by id");
        try {
            int id = 0;
            while(jobs.next()){
                
                if(jobs.getString("id") != null){

                    if(jobs.getInt("customer_id") != id){
                        id = jobs.getInt("customer_id");
                        System.out.println(jobs.getInt("id") + ") " + jobs.getString("fName") + " " + jobs.getString("lName"));
                    }
                    if(jobs.getBoolean("invoiced"))
                        System.out.println("\t" + jobs.getInt("job_number") + ") Invoiced: " + ( jobs.getBoolean("invoiced") ? "Yes" : "No") + " Paid: " + (jobs.getBoolean("paid") ? "Yes" : "No"));

                }
                
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    private void viewQuoted() {
        JobSQL server = new JobSQL(this.server);
        ResultSet jobs = server.customQuery("select * from jobs full join customers on id = customer_id order by id");
        try {
            int id = 0;
            while(jobs.next()){
                
                if(jobs.getString("id") != null){

                    if(jobs.getInt("customer_id") != id){
                        id = jobs.getInt("customer_id");
                        System.out.println(jobs.getInt("id") + ") " + jobs.getString("fName") + " " + jobs.getString("lName"));
                    }
                    if(!jobs.getBoolean("invoiced"))
                        System.out.println("\t" + jobs.getInt("job_number") + ") Invoiced: " + ( jobs.getBoolean("invoiced") ? "Yes" : "No") + " Paid: " + (jobs.getBoolean("paid") ? "Yes" : "No"));

                }
                
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    private void viewAllJobs() {
        JobSQL server = new JobSQL(this.server);
        ResultSet jobs = server.customQuery("select * from jobs full join customers on id = customer_id order by id , job_number");
        try {
            int id = 0;
            while(jobs.next()){
                
                if(jobs.getString("id") != null){
                    if(jobs.getInt("customer_id") != id){
                        id = jobs.getInt("customer_id");
                        System.out.println(jobs.getInt("id") + ") " + jobs.getString("fName") + " " + jobs.getString("lName"));
                    }
                    System.out.println("\t" + jobs.getInt("job_number") + ") Invoiced: " + ( jobs.getBoolean("invoiced") ? "Yes" : "No") + " Paid: " + (jobs.getBoolean("paid") ? "Yes" : "No"));

                }
                
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //TODO Finish
    }


    private void viewCustomerInformation() {
        System.out.println("Enter Customer First Name:");
        String fName = input.nextLine();
        CustomerSQL server = new CustomerSQL(this.server);
        List<Customer> custs = server.getFiltered(fName, 2);
        if(custs == null){
            System.out.println("Customer not found. Please try again.");
            return;
        }
        while(custs.size() != 1){
            int i = 1;
            for(Customer cust : custs){
                System.out.println(i++ + ") " + cust.fName + " " + cust.lName);
            }
            System.out.println("Enter Customer Last Name: ");
            String lName = input.nextLine();
            String values[] = {fName , lName};
            int keys[] = {2, 3};
            custs = server.getFiltered(values, keys);

        }
        custs.get(0).printCustomerInfo();
    }


    private void updateCustomer() {
        System.out.println("Enter Customer First Name:");
        String fName = input.nextLine();
        CustomerSQL server = new CustomerSQL(this.server);
        List<Customer> custs = server.getFiltered(fName, 2);
        if(custs == null){
            System.out.println("Customer not found. Please try again.");
            return;
        }
        while(custs.size() != 1){
            int i = 1;
            for(Customer cust : custs){
                System.out.println(i++ + ") " + cust.fName + " " + cust.lName);
            }
            System.out.println("Enter Customer Last Name: ");
            String lName = input.nextLine();
            String values[] = {fName , lName};
            int keys[] = {2, 3};
            custs = server.getFiltered(values, keys);

        }
        custs.get(0).printCustomerInfo();

        System.out.println("1) First Name\n2) Last Name\n3)Phone Number\n4)Delete Customer\nSelect information to update:");
        int update = Integer.parseInt(input.nextLine());
        System.out.println("Enter Updated Information:");
        switch(update){
            case 1:
                String nFName = input.nextLine();
                custs.get(0).fName = nFName;
                break;
            case 2: 
                String nLName = input.nextLine();
                custs.get(0).lName = nLName;
                break;
            case 3:
                String nPN = input.nextLine();
                custs.get(0).phoneNumber = nPN;
                break;
            case 4:
                System.out.println("Are you sure you want to delete customer:");
                custs.get(0).printCustomerInfo();
                System.out.println("Y/N?");
                String ans = input.nextLine();
                if(ans.equalsIgnoreCase("y"))
                    server.delete(custs.get(0));
                else
                    System.out.println("Customer Delete Canceled");
                return;
                
        }
        server.update(custs.get(0), update);




    }


    private void addCustomer() {
        CustomerSQL newCust = new CustomerSQL(server);
        System.out.println("Enter Customer First Name:");
        String fName = input.nextLine();
        System.out.println("Enter Customer Last Name:");
        String lName = input.nextLine();
        System.out.println("Enter Customer Phone Number");
        String phoneNumber = input.nextLine();
        newCust.create(new Customer(0, fName, lName, phoneNumber));
        
    }


    private void printCustomerInvoices() {
        System.out.println("Enter Customer First Name:");
        String fName = input.nextLine();
        CustomerSQL server = new CustomerSQL(this.server);
        List<Customer> custs = server.getFiltered(fName, 2);
        while(custs.size() != 1){
            int i = 1;
            for(Customer cust : custs){
                System.out.println(i++ + ") " + cust.fName + " " + cust.lName);
            }
            System.out.println("Enter Customer Last Name: ");
            String lName = input.nextLine();
            String values[] = {fName , lName};
            int keys[] = {2, 3};
            custs = server.getFiltered(values, keys);

        }
        custs.get(0).printCustomerInfo();
        JobSQL server2 = new JobSQL(this.server);
        List<Job> jobs = server2.getFiltered(Integer.toString(custs.get(0).id), 1);
        if(jobs != null)
            for(Job job : jobs){
                System.out.println(job.jobNumber + ": Invoiced: " + job.invoiced + " Paid: " + job.paid + " Taxable: " + job.taxable);
            }
    }


    private void printUnpaid() {
        System.out.println("TODO: finish printing customers with unpiad invoices");
        


    }


    private void printCustomerList() {
        CustomerSQL server = new CustomerSQL(this.server);
        List<Customer> custs = server.getAll();
        for(Customer cust : custs){
            System.out.println(cust.id + ") " + cust.fName + " " + cust.lName);
        }
    }
}
