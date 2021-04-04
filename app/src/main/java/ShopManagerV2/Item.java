package ShopManagerV2;

public class Item {
    String itemNumber;      
    String itemDescription;
    boolean taxable;
    float cost;
    String itemID;          //Praimary key for Item table/foreign key for Job Table
    
    public Item(String itemNumber, String itemDescription, boolean taxable, float cost, String itemID) {
        this.itemNumber = itemNumber;
        this.itemDescription = itemDescription;
        this.taxable = taxable;
        this.cost = cost;
        this.itemID = itemID;
    }

}
