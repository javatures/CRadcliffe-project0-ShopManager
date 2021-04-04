package ShopManagerV2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ItemsSQL implements TheWay<Item> {
    Connection steve;
    public ItemsSQL(Connection stevesr){
        this.steve = stevesr;
    }
    @Override
    public void create(Item e) {
        // TODO Auto-generated method stub
        try {
            PreparedStatement stevejr = steve.prepareStatement("Insert into items (job_id , item_number , item_description, taxable , unit_cost, quantity) values( ? , ? , ? , ? , ? , ?)");
            stevejr.setInt(1, e.jobNumber);
            stevejr.setString(2, e.itemNumber);
            stevejr.setString(3, e.itemDescription);
            stevejr.setBoolean(4, e.taxable);
            stevejr.setFloat(5,e.cost);
            stevejr.setFloat(6, e.quantity);
            stevejr.executeUpdate();
            
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }        
    }

    @Override
    public void update(Item e, int key) {
        // TODO Auto-generated method stub
            try {
                PreparedStatement stevejr = steve.prepareStatement("update items set ? = ? where id = ?");
                switch (key){
                    case 1:
                        stevejr.setString(1, "job_id");
                        stevejr.setInt(2, e.jobNumber);
                        break;
                    case 2:
                        stevejr.setString(1, "item_number");
                        stevejr.setString(2, e.itemNumber);
                        break;
                    case 3:
                        stevejr.setString(1, "item_description");
                        stevejr.setString(2, e.itemDescription);
                        break;
                    case 4:
                        stevejr.setString(1, "taxable");
                        stevejr.setBoolean(2, e.taxable);
                        break;
                    case 5:
                        stevejr.setString(1, "unit_cost");
                        stevejr.setFloat(2, e.cost);
                        break;
                    case 6:
                        stevejr.setString(1, "quantity");
                        stevejr.setFloat(2, e.quantity);
                        break;
    
                }
                stevejr.setInt(3, e.id);
                stevejr.executeUpdate();
                
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
    }

    @Override
    public void delete(Item e) {
        // TODO Auto-generated method stub
        try {
            PreparedStatement stevejr = steve.prepareStatement("DELETE FROM items where id = " + e.id);
            stevejr.executeUpdate();
            
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
    }

    @Override
    public List<Item> getAll() {
        // TODO Auto-generated method stub
        try {
            Statement stevejr = steve.createStatement();
            ResultSet results = stevejr.executeQuery("select * from items");
            List<Item> items = new ArrayList<Item>();
            while(results.next()){
                //public Job(int customerID, String carID, String jobNumber, boolean invoiced, boolean paid, boolean taxable, ArrayList<Integer> items)
                items.add(new Item(results.getInt("id"), 
                results.getInt("job_id"), 
                results.getString("item_number"), 
                results.getString("item_description"), 
                results.getBoolean("taxable"), 
                results.getLong("unit_cost"),
                results.getLong("quantity")));
            }
            return items;
        } catch (SQLException e) {
            //TODO: handle exception
            e.printStackTrace();
        }
        return null;
    }
    public List<Item> getFiltered(String value, int key){
        try {
            PreparedStatement stevejr = steve.prepareStatement("select * from items where ? = ?");
            switch (key){
                case 1:
                    stevejr.setString(1, "id");
                    break;
                case 2:
                    stevejr.setString(1, "job_id");
                    break;
                case 3:
                    stevejr.setString(1, "item_number");
                    break;
                case 4:
                    stevejr.setString(1, "item_description");
                    break;
                case 5:
                    stevejr.setString(1, "taxable");
                    break;
                case 6:
                    stevejr.setString(1, "unit_cost");
                    break;
                case 7:
                    stevejr.setString(1, "quantity");
                    break;
            }            
            stevejr.setString(2, value);
            ResultSet results = stevejr.executeQuery();
            List<Item> items = new ArrayList<Item>();
            while(results.next()){
                //public Job(int customerID, String carID, String jobNumber, boolean invoiced, boolean paid, boolean taxable, ArrayList<Integer> items)
                items.add(new Item(results.getInt("id"), 
                results.getInt("job_id"), 
                results.getString("item_number"), 
                results.getString("item_description"), 
                results.getBoolean("taxable"), 
                results.getLong("unit_cost"),
                results.getLong("quantity")));
            }
            return items;
            
        } catch (SQLException e) {
            //TODO: handle exception
            e.printStackTrace();
        }
        return null;
    }
}
