/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ShopManagerV2;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class App {
    public String getGreeting() {
        return "Welcome to the Shop Manager System Version 2.0";
    }
    

    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/ShopManager";
        String username = "ShopManager";
        String password = "7SpotPontiac";
        Scanner input = new Scanner(System.in);

        try{
            Connection steve = DriverManager.getConnection(url, username, password);
            Navigator navi = new Navigator(input , steve);

       
            int menu = 0;
            System.out.println(new App().getGreeting());

            System.out.println("Building user interface...");

            while(menu != 4){
            UI.buildMenu(menu);
            if(menu == 0)
                menu = Integer.parseInt(input.nextLine());
            else
                menu = navi.submenu(menu);
        
    
            }
        }catch(SQLException e){
            e.printStackTrace();
    }
        

    }
}
