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
            ResultSet results = stevejr.executeQuery("select * from jobs order by job_number");
            List<Job> jobs = new ArrayList<Job>();
            while(results.next()){
                //public Job(int customerID, String carID, String jobNumber, boolean invoiced, boolean paid, boolean taxable, ArrayList<Integer> items)
                jobs.add(new Job(results.getInt("customer_id"), 
                                results.getString("car_id"), 
                                results.getInt("job_number"), 
                                results.getBoolean("invoiced"), 
                                results.getBoolean("paid"), 
                                results.getBoolean("taxable")));
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
            PreparedStatement stevejr = steve.prepareStatement("Insert into jobs (customer_id , car_id , invoiced, paid , taxable) values(? , ? , ? , ? , ? )");
            stevejr.setInt(1, job.customerID);
            stevejr.setString(2, job.carVIN);
            stevejr.setBoolean(3, job.invoiced);
            stevejr.setBoolean(4, job.paid);
            stevejr.setBoolean(5, job.taxable);
            stevejr.executeUpdate();
            
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
    }

    @Override
    public void update(Job job, int key) {
        // TODO Auto-generated method stub
        String sql = "update jobs set ";
        try {
            switch (key){
                case 1:
                    sql = sql.concat("customer_id = " + job.customerID);
                    break;
                case 2:
                    sql = sql.concat("car_id = " + job.carVIN);
                    break;
                case 3:
                    sql = sql.concat("invoiced = " + job.invoiced);
                    break;
                case 4:
                    sql = sql.concat("paid = " + job.paid);
                    break;
                case 5:
                    sql = sql.concat("taxable = " + job.customerID);
                    break;
            }
            sql = sql.concat(" where job_number =" + job.jobNumber);
            PreparedStatement stevejr = steve.prepareStatement(sql);
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
            String sql = "select * from jobs where";

            
            switch (key){
                case 1:
                    sql = sql.concat(" customer_id = " + Integer.parseInt(value));
                    break;
                case 2:
                    sql = sql.concat(" car_id = " + value);
                    break;
                case 3:
                    sql = sql.concat(" job_number = " + Integer.parseInt(value));
                    break;
                case 4:
                    sql = sql.concat(" invoiced = " + Boolean.parseBoolean(value));
                    break;
                case 5:
                    sql = sql.concat(" paid = " + Boolean.parseBoolean(value));
                    break;
                case 6:
                    sql = sql.concat(" taxable = " + Boolean.parseBoolean(value));
                    break;
            }            
            PreparedStatement stevejr = steve.prepareStatement(sql);
            ResultSet results = stevejr.executeQuery();
            List<Job> jobs = new ArrayList<Job>();
            while(results.next()){
                //public Job(int customerID, String carID, String jobNumber, boolean invoiced, boolean paid, boolean taxable, ArrayList<Integer> items)
                jobs.add(new Job(results.getInt("customer_id"), 
                                results.getString("car_id"), 
                                results.getInt("job_number"), 
                                results.getBoolean("invoiced"), 
                                results.getBoolean("paid"), 
                                results.getBoolean("taxable")));
            }
            
            return jobs;
        } catch (SQLException e) {
            //TODO: handle exception
            e.printStackTrace();
        }
        return null;
    }
    public ResultSet customQuery(String sql) {
        PreparedStatement stevejr;
        try {
            stevejr = steve.prepareStatement(sql);
            return stevejr.executeQuery();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

}
