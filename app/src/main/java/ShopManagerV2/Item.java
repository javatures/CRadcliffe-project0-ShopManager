package ShopManagerV2;

public class Item {
    int id;
    int jobNumber;
    String itemNumber;
    String itemDescription;
    boolean taxable;
    float cost;
    float quantity;

    public Item(int id, int jobNumber, String itemNumber, String itemDescription, boolean taxable, float cost , float quantity) {
        this.id = id;
        this.jobNumber = jobNumber;
        this.itemNumber = itemNumber;
        this.itemDescription = itemDescription;
        this.taxable = taxable;
        this.cost = cost;
        this.quantity = quantity;
    }
    


}
