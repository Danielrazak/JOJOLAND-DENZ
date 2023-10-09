package moody_milagro;
import java.util.*;

public class MoodyBlues{
    private Menu menu;
    private SalesRecord sale;
    
    public MoodyBlues(String restaurant){
        menu = new Menu(restaurant);
        sale = new SalesRecord(menu);
    }
    
    public MoodyBlues(Menu menu, SalesRecord sale){
        this.menu = menu;
        this.sale = sale;
    }
    
    // A method to print option available in MoodyBlues(Sales Information)
    public void printMoodyBlues(){
        Scanner sc = new Scanner(System.in);
        boolean status = true;
        while(status){
        	System.out.println("=".repeat(200));
            System.out.println("Restaurant: " + menu.getRestaurant());
            System.out.println("Sales Information");
            System.out.println("[1] View Sales");
            System.out.println("[2] View Aggregated Information");
            System.out.println("\t[A] Minimum Sales");
            System.out.println("\t[B] Maximum Sales");
            System.out.println("\t[C] Top k Highest Sales");
            System.out.println("\t[D] Total and Average Sales");
            System.out.println("[3] Exit");
            System.out.print("Select : ");
            String choice = sc.nextLine();
            
            switch(choice){
                case "1":
                    viewSales();
                    break;
                case "2A":
                    viewMinSales();
                    break;
                case "2B":
                    viewMaxSales();
                    break;
                case "2C":
                    viewTopHighestSales();
                    break;
                case "2D":
                    viewTotalAverageSales();
                    break;
                case "3":
                    status = false;
                    break; // exit view sales info
                default:  
            }
        }    
    }
    
    public double getTotalSales(int day){
        double totalSales = 0;
        for(Food food : menu.getFoodList()){
            double totalPrice = sale.getSale(day, food.getName());
            totalSales+=totalPrice;
        }
        return totalSales;
    }
    
    // A method to print sales on selected day
    public void viewSales(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Day : ");
        int day = sc.nextInt();
        double totalSales = 0;
        System.out.println("=".repeat(200));
        System.out.println("Restaurant : "+ menu.getRestaurant());
        System.out.println("Day "+ day+" Sales");
        System.out.println("+-------------------------------------+----------+-------------+");
        System.out.println("| Food                                | Quantity | Total Price |");
        System.out.println("+-------------------------------------+----------+-------------+");
        
        for(Food food : menu.getFoodList()){
            String n = food.getName();
            double p = food.getPrice();
            int q = sale.getQuantity(day,n);
            double totalPrice = sale.getSale(day, food.getName());
            if(q!=0)
            System.out.printf("| %-35s | %5d    |   $%-6.2f   |\n",n,q,totalPrice);
        }
        
        System.out.println("+-------------------------------------+----------+-------------+");
        System.out.printf("|                                   Total Sales  |   $%-6.2f   |\n",getTotalSales(day));
        System.out.println("+-------------------------------------+----------+-------------+");
        System.out.println();
        System.out.println("=".repeat(200));
    }
    
    
    
    // A method to print minimum sales 
    private void viewMinSales() {
    	System.out.println("=".repeat(200));
        double min = 99999.00;
        int dayMin = 0;
        double curr;
        int i=1;
        while(getTotalSales(i)>0){
            curr = getTotalSales(i);
            if (curr<min){
                min = curr ;
                dayMin = i ;
            }
            i++;
        }
        System.out.println("Minimum sales Day "+dayMin);
        System.out.printf("Total Sales : $%3.2f\n",min);
        System.out.println("=".repeat(200));
    }
    // A method to print maximum sales
    private void viewMaxSales() {
    	System.out.println("=".repeat(200));
    	double max = -1.00;
        int dayMax = 0;
        double curr;
        int i=1;
        while(getTotalSales(i)>0){
            curr = getTotalSales(i);
            if (curr>max){
                max = curr ;
                dayMax = i ;
            }
            i++;
        }
        System.out.println("Maximum sales Day "+dayMax);
        System.out.printf("Total Sales : $%3.2f\n",max);
        System.out.println("=".repeat(200));
    }
    
    // A method to print top k highest selling dishes
    private void viewTopHighestSales() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter k value : ");
        int k = sc.nextInt();
        int i = 0;
        System.out.println("=".repeat(200));
        ArrayList<String> topSellingDishes = sale.getTopSellingDishes(k);
        // Print the sorted list
        System.out.println("Top "+ k +" highest selling dishes in "+menu.getRestaurant());
        System.out.println("");
        for (String dish : topSellingDishes) {
            System.out.println(dish);
        }
        System.out.println("=".repeat(200));
        }

    private void viewTotalAverageSales() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Start Day : ");
        int startDay = sc.nextInt();
        System.out.print("Enter End Day : ");
        int endDay = sc.nextInt();
        System.out.println("=".repeat(200));        
        System.out.println("Restaurant : "+ menu.getRestaurant());
        System.out.println("Total and Average Sales (Day "+startDay+" - "+endDay+")");
        System.out.println("+-------------------------------------+----------+-------------+");
        System.out.println("| Food                                | Quantity | Total Price |");
        System.out.println("+-------------------------------------+----------+-------------+");
        
        double totalSales = 0;
        for(Food food : menu.getFoodList()){
            String n = food.getName();
            int q = sale.getQuantityInRange(startDay,endDay,n);
            double totalPrice = sale.getSaleInRange(startDay, endDay, n);
            totalSales+=totalPrice;
            if(q!=0)
            System.out.printf("| %-35s | %5d    |   $%-6.2f   |\n",n,q,totalPrice);
        }
        
        double averageSales = totalSales/(endDay-startDay+1);
        
        System.out.println("+-------------------------------------+----------+-------------+");
        System.out.printf("|                                   Total Sales  |   $%-6.2f   |\n",totalSales);
        System.out.printf("|                                 Average Sales  |   $%-6.2f   |\n",averageSales);
        System.out.println("+-------------------------------------+----------+-------------+");
        System.out.println("=".repeat(200));
        }   
}