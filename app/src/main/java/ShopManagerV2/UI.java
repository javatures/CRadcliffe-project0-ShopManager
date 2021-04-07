package ShopManagerV2;

public class UI{
    public static void buildMenu(int menu){
        

        switch(menu){
            //Main menu
            case 0:
                mainMenu();
                break;
            
            //customer Menu
            case 1:
                customerMenu();
                break;
            
            //Invoice Menu
            case 2:
                jobMenu();
                break;
            
            //Vehicle Menu
            case 3:
                vehicleMenu();
                break;
            
        }

    }
    // private static void inventoryMenu() {
    //     System.out.println("1) View All Inventory\n2) View Out of Stock Inventory\n3) View Inventory of specific Item\n4) Add Inventory\n5) Modify Inventory\n6) Return to Main Menu");
    // }
    private static void vehicleMenu() {
        System.out.println("1) View All Vehicles\n2) View specific Vehicle\n3) Add Vehicle\n4) Modify Vehicle\n5) Return to Main Menu");
    }
    private static void jobMenu() {
        System.out.println("1) View All Jobs\n2) View All Quotes\n3) View All Invoices\n4) View Unpaid Invoices\n5) View Job\n6) Start New Job\n7) Invoice Job\n8) Pay Job\n9) Return to Main Menu");
    }
    private static void customerMenu() {
        System.out.println("1) View All Customers\n2) View Customer Invoice History\n3) Add Customer\n4) Modify Customer\n5) View Customer Information\n6) Return to Main Menu");
    }
    private static void mainMenu(){
        System.out.println("Main Menu\n1) Customer Menu\n2) Invoice Menu\n3) Vehicle Menu\n4) Exit");

    }

}