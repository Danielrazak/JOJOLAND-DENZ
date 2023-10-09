package moody_milagro;
import java.util.*;

public class MilagroMan{
    private Menu menu; 
    private SalesRecord sale;  
    private MoodyBlues salesInfo;
    
    public MilagroMan(String restaurant){
        menu = new Menu(restaurant+" (Milagro Man Mode)");
        menu.copyMenu(new Menu(restaurant));
        sale = new SalesRecord(menu);
        sale.copySales(new SalesRecord(new Menu(restaurant)));
    }
    
    public void printMilagroMan(){
        boolean status = true;
        while(status){
        Scanner sc = new Scanner(System.in);
        System.out.println("=".repeat(200));
        System.out.println("Restaurant : "+menu.getRestaurant());
        System.out.println("[1] Modify Food Prices");
        System.out.println("[2] View Sales Information");
        System.out.println("[3] Exit Milagro Man");
        System.out.print("Select : ");
        int choice = sc.nextInt();
        sc.nextLine();
        System.out.println("=".repeat(200));
        switch(choice){
                case 1:
                    System.out.print("Enter food name : ");
                    String name = sc.nextLine();
                    System.out.print("Enter new price : ");
                    double newPrice = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Enter Start Day : ");
                    int startDay = sc.nextInt();
                    System.out.print("Enter End Day : ");
                    int endDay = sc.nextInt();
                    modifyFoodPrice(name,newPrice,startDay,endDay);
                    break;
                case 2:
                    salesInfo = new MoodyBlues(menu,sale);
                    salesInfo.printMoodyBlues();
                    break;
                case 3:
                    menu.resetFoodList(); // Reset to its original state
                    sale.resetSales();
                    status = false;
                    break;
                default: status = false;
            }
        }
    }

    public void modifyFoodPrice(String foodName, double newPrice, int startDay, int endDay) {
        menu.modifyMenu(foodName, newPrice);
        menu.printFoodList();
        for (int day = startDay; day <= endDay; day++) {
            sale.setSale(day, foodName, newPrice);
        }
    }
}