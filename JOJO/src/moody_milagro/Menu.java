package moody_milagro;
import java.io.*; 
import java.util.*;

// Menu class to view, add , modify and remove menu
public class Menu {
    Random ran = new Random();
    private String restaurant; // restaurant's name
    private ArrayList<Food> foodList; // list of menu
    private ArrayList<Food> defaultFoodList;
    
    public Menu(String restaurant) {
        this.restaurant = restaurant;
        if (defaultFoodList == null) { // when no menu created (first time)
            defaultFoodList = defaultMenu(restaurant);
        }
        foodList = new ArrayList<>(defaultFoodList);
    }
    
    public Menu(String restaurant,ArrayList<Food> foodList) {
        this.restaurant = restaurant; 
        this.foodList = foodList; 
    }
    
    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public ArrayList<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(ArrayList<Food> foodList) {
        this.foodList = foodList;
    }
    private ArrayList<Food> defaultMenu(String restaurant){
        ArrayList<Food> menu = new ArrayList<>();
        if(restaurant.equals("Jade Garden")||restaurant.equals("Jade Garden (Milagro Man Mode)")){
                menu.add(new Food("Braised Chicken in Black Bean Sauce",15.00));
                menu.add(new Food("Braised Goose Web with Vermicelli",21.00));
                menu.add(new Food("Deep-fried Hiroshima Oysters",17.00));
                menu.add(new Food("Poached Tofu with Dried Shrimps",12.00));
                menu.add(new Food("Scrambled Egg White with Milk",10.00));
        }
        else if(restaurant.equals("Cafe Deux Magots")||restaurant.equals("Cafe Deux Magots (Milagro Man Mode)")){
                menu.add(new Food("Sampling Matured Cheese Platter",23.00));
                menu.add(new Food("Spring Lobster Salad",35.00));
                menu.add(new Food("Spring Organic Omelette",23.00));
                menu.add(new Food("Truffle-flavoured Poultry Supreme",34.00));
                menu.add(new Food("White Asparagus",26.00));
        }
        else if(restaurant.equals("Trattoria Trussardi")||restaurant.equals("Trattoria Trussardi (Milagro Man Mode)")){
                menu.add(new Food("Caprese Salad",10.00));
                menu.add(new Food("Creme caramel",6.50));
                menu.add(new Food("Lamb Chops with Apple Sauce",25.00));
                menu.add(new Food("Spaghetti alla Puttanesca",15.00));
        }
        else if(restaurant.equals("Libeccio")||restaurant.equals("Libeccio (Milagro Man Mode)")){
                menu.add(new Food("Formaggio",12.50));
                menu.add(new Food("Ghiaccio",1.01));
                menu.add(new Food("Melone",5.20));
                menu.add(new Food("Prosciutto and Pesci",20.23));
                menu.add(new Food("Risotto",20.23));
                menu.add(new Food("Zucchero and Sale",0.60));
        }
        else if(restaurant.equals("Savage Garden")||restaurant.equals("Savage Garden (Milagro Man Mode)")){
                menu.add(new Food("Abbacchio’s Tea",1.00));
                menu.add(new Food("DIO’s Bread",36.14));
                menu.add(new Food("Giorno’s Donuts",6.66));
                menu.add(new Food("Joseph’s Tequila",35.00));
                menu.add(new Food("Kakyoin’s Cherry",3.50));
                menu.add(new Food("Kakyoin’s Cherry",4.44));
        } else
                System.out.println("No menu for this restaurant");
        return menu;
    }
    
    public void addMenu(String name,double price){
        Food newFood = new Food(name,price);
        foodList.add(newFood);
        System.out.println("=".repeat(200));
        System.out.println("Menu item '"+name+"' added successfully");
        System.out.println("=".repeat(200));
        printMenu();
    }
    
    public void removeMenu(String name) {
        int index = -1;
        for (int i = 0; i < foodList.size(); i++) {
            Food food = foodList.get(i);
            if (food.getName().equals(name)) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            foodList.remove(index);
            System.out.println("=".repeat(200));
            System.out.println("Menu item '" + name + "' removed successfully.");
            System.out.println("=".repeat(200));
        } else {
        	System.out.println("=".repeat(200));
            System.out.println("Menu item '" + name + "' not found.");
            System.out.println("=".repeat(200));
        }
        printMenu();
    }
    
    public void modifyMenu(String name, double newPrice) {
        boolean found = false;
        for (Food food : foodList) {
            if (food.getName().equals(name)) {
                food.setPrice(newPrice);
                found = true;
                break;
            }
        }
        if (found) {
        	System.out.println("=".repeat(200));
            System.out.println("Menu item '" + name + "' price modified successfully.");
            System.out.println("=".repeat(200));
        } else {
        	System.out.println("=".repeat(200));
            System.out.println("Menu item '" + name + "' not found.");
            System.out.println("=".repeat(200));
        }
    }

    
    
    public void printMenu(){
    	System.out.println("=".repeat(200));
        System.out.println(getRestaurant()+"'s Menu");
        printFoodList();
        System.out.println("\n[1] Add Food");
        System.out.println("[2] Modify Food Price");
        System.out.println("[3] Remove Food");
        System.out.println("[4] Exit View Menu");
        Scanner sc = new Scanner(System.in);
        System.out.print("Select : ");
        int choice = sc.nextInt();
        sc.nextLine();
        switch(choice){
            case 1: 
                System.out.print("Enter new food name to add : ");
                String newName = sc.nextLine();
                System.out.print("Enter new food price : ");
                double newPrice = sc.nextDouble();
                addMenu(newName,newPrice);
            break;
            case 2: 
                System.out.print("Enter food name to modify : ");
                String name = sc.nextLine();
                System.out.print("Enter new food price : ");
                newPrice = sc.nextDouble();
                modifyMenu(name,newPrice);
                printMenu();
            break;
            case 3: 
                System.out.print("Enter food name to remove : ");
                name = sc.nextLine();
                removeMenu(name);
            break;
            default: 
        }
    }
    
    public void printFoodList(){
        for(Food food : foodList){
            food.printFoodInfo();
        }
    }
    
    public String randomFood(){
            String randomFoodName = null;
            if (!foodList.isEmpty()) {
                int randomFood = ran.nextInt(foodList.size());
                randomFoodName = foodList.get(randomFood).getName();
            } else {
                System.out.println("No food items available.");
            }
            return randomFoodName;
    }
        
    public double getPrice(String name){
            for(Food food : foodList){
                if(name.equals(food.getName()))
                    return food.getPrice();
            }
            return -1.0;
    }
    
    public String food(int num){
        return foodList.get(num).getName();
    }
    
    public void copyMenu(Menu menu){
        foodList = new ArrayList<>(menu.getFoodList());
    }
    
        // Method to set the food list back to the default menu
    public void resetFoodList() {
        foodList = new ArrayList<>(defaultFoodList);
    }
 }
