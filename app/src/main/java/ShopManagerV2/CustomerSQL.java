package ShopManagerV2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerSQL implements TheWay<Customer>{
    Connection steve;
    public CustomerSQL(Connection stevesr){
        this.steve = stevesr;
    }

    @Override
    public List<Customer> getAll() {
        try {
            Statement stevejr = steve.createStatement();
            ResultSet results = stevejr.executeQuery("select * from customers order by id");
            List<Customer> customers = new ArrayList<Customer>();
            while(results.next()){
                customers.add(new Customer(results.getInt("id"), 
                                results.getString("fName"), 
                                results.getString("lName"), 
                                results.getString("phone_number") 
                                ));
            }
            return customers;
        } catch (SQLException e) {
            //TODO: handle exception
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void create(Customer cust) {
        try {
            PreparedStatement stevejr = steve.prepareStatement("Insert into customers (fName , lName , phone_number) values (? , ? , ? )");
            stevejr.setString(1, cust.fName);
            stevejr.setString(2, cust.lName);
            stevejr.setString(3, cust.phoneNumber);
            stevejr.executeUpdate();
            
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
        
    }

    @Override
    public void update(Customer cust, int key) {
        // TODO Auto-generated method stub
        try {
            PreparedStatement stevejr = steve.prepareStatement("update customers set ? = ? where id = ?");
            switch (key){
                case 1:
                    stevejr = steve.prepareStatement("update customers set fName=? where id=?");
                    stevejr.setString(1 , cust.fName);
                    break;
                case 2:
                    stevejr = steve.prepareStatement("update customers set lName=? where id=?");
                    stevejr.setString(1, cust.lName);
                    break;
                case 3:
                    stevejr= steve.prepareStatement("update customers set phone_number=? where id=?");
                    stevejr.setString(1, cust.phoneNumber);
                    break;
            }
            stevejr.setInt(2, cust.id);
            stevejr.executeUpdate();
            
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
    }

    @Override
    public void delete(Customer cust) {
        try {
            PreparedStatement stevejr = steve.prepareStatement("DELETE FROM customers where id = " + cust.id);
            stevejr.executeUpdate();
            
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        // TODO Auto-generated method stub
        
    }
    public List<Customer> getFiltered(String value, int key){
        try {
            PreparedStatement stevejr = steve.prepareStatement("select * from customers");
            switch (key){
                case 1:
                    stevejr = steve.prepareStatement("select * from customers where id=?");
                    stevejr.setInt(1, Integer.parseInt(value));
                    break;
                case 2:
                    stevejr = steve.prepareStatement("select * from customers where fName=?");
                    stevejr.setString(1, value);
                    break;
                case 3:
                    stevejr = steve.prepareStatement("select * from customers where lName=?");
                    stevejr.setString(1, value);
                    break;
                case 4:
                    stevejr = steve.prepareStatement("select * from customers where phone_number=?");
                    stevejr.setString(1, value);
                    break;
            }            
            ResultSet results = stevejr.executeQuery();
            
            List<Customer> custs = new ArrayList<Customer>();
            while(results.next()){
                custs.add(new Customer(results.getInt("id"), 
                results.getString("fName"), 
                results.getString("lName"), 
                results.getString("phone_number") 
                ));
            return custs;
            }
        } catch (SQLException e) {
            //TODO: handle exception
            e.printStackTrace();
        }
        return null;
    }

    public List<Customer> getFiltered(String[] values, int[] keys) {
        try {
            System.out.println("Function not finished");
            String sql = "";
            sql.concat("select * from customers where ");
            
            for(int k : keys){
            switch (k){
                case 1:
                    sql.concat("id = " + Integer.parseInt(values[k]) + " and ");
                    break;
                case 2:
                    sql.concat("fName = " + values[k] + " and ");
                    break;
                case 3:
                    sql.concat("lName = " + values[k] + " and ");
                    break;
                case 4:
                    sql.concat("phone_number = " + values[k] + " and ");
                    break;
                }
            }
            sql = sql.substring(0, sql.length()-5);
            PreparedStatement stevejr = steve.prepareStatement(sql);

            
            ResultSet results = stevejr.executeQuery();
            
            List<Customer> custs = new ArrayList<Customer>();
            while(results.next()){
                custs.add(new Customer(results.getInt("id"), 
                results.getString("fName"), 
                results.getString("lName"), 
                results.getString("phone_number") 
                ));
            return custs;
            }
        } catch (SQLException e) {
            //TODO: handle exception
            e.printStackTrace();
        }
        return null;
    }
    
        
}
