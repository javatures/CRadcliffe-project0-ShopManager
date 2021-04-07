package ShopManagerV2;

public class Car {
    int OwnerID;            //Foreign Key to Customer Table
    
    String year;
    String make;
    String model;
    String vin;             //unique to each car
    String licensePlate;    //unique to each car
    String state;

    public Car(int cID, String year, String make, String model, String vin, String lpST, String lpN) {
        this.OwnerID = cID;
        this.year = year;
        this.make = make;
        this.model = model;
        this.vin = vin;
        this.state = lpST;
        this.licensePlate = lpN;
    }

    @Override
    public String toString() {
        return year +  " " + make + " " + model + " " + state + " " + licensePlate;
    }

 

}
