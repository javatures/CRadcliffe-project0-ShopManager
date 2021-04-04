package ShopManagerV2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JobSQL implements TheWay<Job> {
    Connection steve;
    public JobSQL(Connection stevesr){
        this.steve = stevesr;
    }
    @Override
    public List<Job> getAll() {
        // TODO Auto-generated method stub
        try {
            Statement stevejr = steve.createStatement();
            ResultSet results = stevejr.executeQuery("select * from jobs");
            List<Job> jobs = new ArrayList<Job>();
            while(results.next()){
                //public Job(int customerID, String carID, String jobNumber, boolean invoiced, boolean paid, boolean taxable, ArrayList<Integer> items)
                jobs.add(new Job(results.getInt("customer_id"), 
                                results.getString("car_id"), 
                                results.getInt("job_number"), 
                                results.getBoolean("invoiced"), 
                                results.getBoolean("paid"), 
                                results.getBoolean("taxable"), 
                                results.getString("items")));
            }
            return jobs;
        } catch (SQLException e) {
            //TODO: handle exception
            e.printStackTrace();
        }
        return null;
    }
    

    @Override
    public void create(Job job) {
        // TODO Auto-generated method stub
        try {
            PreparedStatement stevejr = steve.prepareStatement("Insert into jobs (customer_id , car_id , invoiced, paid , taxable, items) values(? , ? , ? , ? , ? , ? )");
            stevejr.setInt(1, job.customerID);
            stevejr.setString(2, job.carVIN);
            stevejr.setBoolean(3, job.invoiced);
            stevejr.setBoolean(4, job.paid);
            stevejr.setBoolean(5, job.taxable);
            stevejr.setString(6, job.prepareItemsForSQL());
            stevejr.executeUpdate();
            
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
    }

    @Override
    public void update(Job job, int key) {
        // TODO Auto-generated method stub
        try {
            PreparedStatement stevejr = steve.prepareStatement("update jobs set ? = ? where job_number = ?");
            switch (key){
                case 1:
                    stevejr.setString(1, "customer_id");
                    stevejr.setInt(2, job.customerID);
                    break;
                case 2:
                    stevejr.setString(1, "car_id");
                    stevejr.setString(2, job.carVIN);
                    break;
                case 3:
                    stevejr.setString(1, "invoiced");
                    stevejr.setBoolean(2, job.invoiced);
                    break;
                case 4:
                    stevejr.setString(1, "paid");
                    stevejr.setBoolean(2, job.paid);
                    break;
                case 5:
                    stevejr.setString(1, "taxable");
                    stevejr.setBoolean(2, job.taxable);
                    break;
                case 6:
                    stevejr.setString(1, "items");
                    stevejr.setString(2, job.prepareItemsForSQL());
                    break;

            }
            stevejr.setInt(3, job.jobNumber);
            stevejr.executeUpdate();
            
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
    }

    @Override
    public void delete(Job e) {
        try {
            PreparedStatement stevejr = steve.prepareStatement("DELETE FROM jobs where job_number = " + e.jobNumber);
            stevejr.executeUpdate();
            
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        // TODO Auto-generated method stub
        


    }


    public List<Job> getFiltered(String value, int key){
        try {
            PreparedStatement stevejr = steve.prepareStatement("select * from jobs where ? = ?");
            switch (key){
                case 1:
                    stevejr.setString(1, "customer_id");
                    break;
                case 2:
                    stevejr.setString(1, "car_id");
                    break;
                case 3:
                    stevejr.setString(1, "job_number");
                    break;
                case 4:
                    stevejr.setString(1, "invoiced");
                    break;
                case 5:
                    stevejr.setString(1, "paid");
                    break;
                case 6:
                    stevejr.setString(1, "taxable");
                    break;
                case 7:
                    stevejr.setString(1, "items");
                    break;
            }            
            stevejr.setString(2, value);
            ResultSet results = stevejr.executeQuery();
            List<Job> jobs = new ArrayList<Job>();
            while(results.next()){
                //public Job(int customerID, String carID, String jobNumber, boolean invoiced, boolean paid, boolean taxable, ArrayList<Integer> items)
                jobs.add(new Job(results.getInt("customer_id"), 
                                results.getString("car_id"), 
                                results.getInt("job_number"), 
                                results.getBoolean("invoiced"), 
                                results.getBoolean("paid"), 
                                results.getBoolean("taxable"), 
                                results.getString("items")));
            }
            
            return jobs;
        } catch (SQLException e) {
            //TODO: handle exception
            e.printStackTrace();
        }
        return null;
    }

}
