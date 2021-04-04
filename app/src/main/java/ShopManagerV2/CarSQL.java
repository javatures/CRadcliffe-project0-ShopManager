package ShopManagerV2;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CarSQL  implements TheWay<Car>{
    Connection steve;
    public CarSQL(Connection stevesr){
        this.steve = stevesr;
    }

    @Override
    public List<Car> getAll() {
        try {
            Statement stevejr = steve.createStatement();
            ResultSet results = stevejr.executeQuery("select * from cars");
            List<Car> cars = new ArrayList<Car>();
            while(results.next()){
                cars.add(new Car(results.getInt("owner_id"), 
                                results.getString("year"), 
                                results.getString("make"), 
                                results.getString("model"), 
                                results.getString("vin"), 
                                results.getString("license_plate_state"), 
                                results.getString("license_plate_number")));
            }
            return cars;
        } catch (SQLException e) {
            //TODO: handle exception
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void create(Car car) {
        try {
            PreparedStatement stevejr = steve.prepareStatement("Insert into cars (? , ? , ? , ? , ? , ? , ?)");
            stevejr.setInt(1, car.OwnerID);
            stevejr.setString(2, car.year);
            stevejr.setString(3, car.make);
            stevejr.setString(4, car.model);
            stevejr.setString(5, car.vin);
            stevejr.setString(6, car.state);
            stevejr.setString(7, car.licensePlate);
            stevejr.executeUpdate();
            
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
        
    }

    @Override
    public void update(Car car, int key) {
        // TODO Auto-generated method stub
        try {
            PreparedStatement stevejr = steve.prepareStatement("update cars set ? = ? where vin = ?");
            switch (key){
                case 1:
                    stevejr.setString(1, "owner_id");
                    stevejr.setInt(2, car.OwnerID);
                    break;
                case 2:
                    stevejr.setString(1, "year");
                    stevejr.setString(2, car.year);
                    break;
                case 3:
                    stevejr.setString(1, "make");
                    stevejr.setString(2, car.make);
                    break;
                case 4:
                    stevejr.setString(1, "model");
                    stevejr.setString(2, car.model);
                    break;
                case 5:
                    stevejr.setString(1, "license_plate_state");
                    stevejr.setString(2, car.state);
                    break;
                case 6:
                    stevejr.setString(1, "license_plate_number");
                    stevejr.setString(2, car.licensePlate);
                    break;

            }
            stevejr.setString(3, car.vin);
            stevejr.executeUpdate();
            
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
    }

    @Override
    public void delete(Car car) {
        try {
            PreparedStatement stevejr = steve.prepareStatement("DELETE FROM cars where vin = " + car.vin);
            stevejr.executeUpdate();
            
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        // TODO Auto-generated method stub
        
    }
    public List<Car> getFiltered(String value, int key){
        try {
            PreparedStatement stevejr = steve.prepareStatement("select * from cars where ? = ?");
            switch (key){
                case 1:
                    stevejr.setString(1, "owner_id");
                    break;
                case 2:
                    stevejr.setString(1, "year");
                    break;
                case 3:
                    stevejr.setString(1, "make");
                    break;
                case 4:
                    stevejr.setString(1, "model");
                    break;
                case 5:
                    stevejr.setString(1, "license_plate_state");
                    break;
                case 6:
                    stevejr.setString(1, "license_plate_number");
                    break;
                case 7:
                    stevejr.setString(1, "vin");
                    break;
            }            
            stevejr.setString(2, value);
            ResultSet results = stevejr.executeQuery();
            
            List<Car> cars = new ArrayList<Car>();
            while(results.next()){
                cars.add(new Car(results.getInt("owner_id"), 
                                results.getString("year"), 
                                results.getString("make"), 
                                results.getString("model"), 
                                results.getString("vin"), 
                                results.getString("license_plate_state"), 
                                results.getString("license_plate_number")));
            }
            return cars;
        } catch (SQLException e) {
            //TODO: handle exception
            e.printStackTrace();
        }
        return null;
    }
    
    
}
