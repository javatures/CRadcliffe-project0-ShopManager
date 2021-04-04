package ShopManagerV2;

public class Customer {
    int id;                 //Primary Key for Customer Table/Foreign Key for Car table and Job Table
    String fName;
    String lName;
    String phoneNumber;

    public Customer(int id , String fName , String lName , String phoneNumber){
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.phoneNumber = phoneNumber;
    }
}
