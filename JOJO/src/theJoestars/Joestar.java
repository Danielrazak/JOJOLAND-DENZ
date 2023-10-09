package theJoestars;
import java.io.*; 
import java.util.*;
class Restaurant<E>{
    Random r = new Random();
    protected String name;
    protected String[] menu;
    protected int count=0;
    
    public Restaurant(String name,int size) {
        this.name = name;
        menu= new String[size]; }
    
    public void add(String amenu) {
        menu[count]=amenu;
        count++; }
    
    public String RandomFood() {
        int randomfood=r.nextInt(count);
        return menu[randomfood]; }
    
    public String food(int num) {
        return menu[num]; }}

public class Joestar {
	    ArrayList<String> listinfo = new ArrayList<>();

	    public void residentInformation(String currentLoc) {
	        try {
	            Scanner scan = new Scanner(new FileInputStream("/Users/wenhaowong/Desktop/DS_Assignment/merged_file.csv"));
	            scan.nextLine();
	            while (scan.hasNextLine()) {
	                String info = scan.nextLine();
	                String[] split = info.split(",");

	                if (info.contains(currentLoc)) {
	                    listinfo.add(info);
	                }
	            }
	        } catch (Exception e) {
	            System.out.println(e);
	        }
	    }

	    public void residentprofile(String name) {
	        System.out.println("=".repeat(200));
	        System.out.println(name + "'s Profile");

	        for (String info : listinfo) {
	            String[] split = info.split(",");
	            if (split[0].equals(name)) {
	                System.out.println("Name                  : " + (split[0].trim().isEmpty() ? "N/A" : split[0]));
	                System.out.println("Age                   : " + (split.length >= 2 && !split[1].trim().isEmpty() ? split[1] : "N/A"));
	                System.out.println("Gender                : " + (split.length >= 3 && !split[2].trim().isEmpty() ? split[2] : "N/A"));
	                System.out.println("Parents               : " + (split.length >= 5 && !split[4].trim().isEmpty() ? split[4] : "N/A"));
	                System.out.println("Stand                 : " + (split.length >= 6 && !split[5].trim().isEmpty() ? split[5] : "N/A"));
	                System.out.println("Destructive Power     : " + (split.length >= 7 && !split[6].trim().isEmpty() ? split[6] : "N/A"));
	                System.out.println("Speed                 : " + (split.length >= 8 && !split[7].trim().isEmpty() ? split[7] : "N/A"));
	                System.out.println("Range                 : " + (split.length >= 9 && !split[8].trim().isEmpty() ? split[8] : "N/A"));
	                System.out.println("Stamina               : " + (split.length >= 10 && !split[9].trim().isEmpty() ? split[9] : "N/A"));
	                System.out.println("Precision             : " + (split.length >= 11 && !split[10].trim().isEmpty() ? split[10] : "N/A"));
	                System.out.println("Development Potential : " + (split.length >= 12 && !split[11].trim().isEmpty() ? split[11] : "N/A"));
	                break;
	            }
	        }
	    }

	
    public void orderhistory(String name){ 	
    	System.out.println("Order History");
        Random ra = new Random();
        Restaurant [] restaurant=new Restaurant[5];
        restaurant[0]= new Restaurant<>("Jade Garden",5);
        restaurant[1]= new Restaurant<>("Cafe Deux Magots",5);
        restaurant[2]= new Restaurant<>("Trattoria Trussardi",4);
        restaurant[3]= new Restaurant<>("Libeccio",6);      
        restaurant[4]= new Restaurant<>("Savage Garden",6);
        
        restaurant[0].add("Braised Chicken in Black Bean Sauce"); 
        restaurant[0].add("Braised Goose Web with Vermicelli");  
        restaurant[0].add("Deep-fried Hiroshima Oysters"); 
        restaurant[0].add("Poached Tofu with Dried Shrimps"); 
        restaurant[0].add("Scrambled Egg White with Milk");
        
        restaurant[1].add("Sampling Matured Cheese Platter"); 
        restaurant[1].add("Spring Lobster Salad");  
        restaurant[1].add("Spring Organic Omelette"); 
        restaurant[1].add("Truffle-flavoured Poultry Supreme"); 
        restaurant[1].add("White Asparagus");
        
        restaurant[2].add("Caprese Salad"); 
        restaurant[2].add("Creme caramel");  
        restaurant[2].add("Lamb Chops with Apple Sauce"); 
        restaurant[2].add("Spaghetti alla Puttanesca"); 
        
        restaurant[3].add("Formaggio");
        restaurant[3].add("Ghiaccio"); 
        restaurant[3].add("Melone");  
        restaurant[3].add("Prosciutto and Pesci"); 
        restaurant[3].add("Risotto"); 
        restaurant[3].add("Zucchero and Sale");
        
        restaurant[4].add("Abbacchio’s Tea"); 
        restaurant[4].add("DIO’s Bread");  
        restaurant[4].add("Giorno’s Donuts"); 
        restaurant[4].add("Joseph’s Tequila"); 
        restaurant[4].add("Kakyoin’s Cherry");
        restaurant[4].add("Kakyoin’s Porridge"); 
        
        Restaurant[] restaurantS=new Restaurant[5];
        
        restaurantS[0]= new Restaurant<>("Jade Garden",5);
        restaurantS[1]= new Restaurant<>("Cafe Duex Magots",5);
        restaurantS[2]= new Restaurant<>("Trattoria Trussardi",4);
        restaurantS[3]= new Restaurant<>("Liberrio",6);      
        restaurantS[4]= new Restaurant<>(" Savage Garden",6);    
        
        restaurantS[0].add("Braised Chicken in Black Bean Sauce-$15.00"); 
        restaurantS[0].add("Braised Goose Web with Vermicelli-$21.00");  
        restaurantS[0].add("Deep-fried Hiroshima Oysters-$17.00"); 
        restaurantS[0].add("Poached Tofu with Dried Shrimps-$12.00"); 
        restaurantS[0].add("Scrambled Egg White with Milk-$10.00");
        
        restaurantS[1].add("Sampling Matured Cheese Platter-$23.00"); 
        restaurantS[1].add("Spring Lobster Salad-$35.00");  
        restaurantS[1].add("Spring Organic Omelette-$23.00"); 
        restaurantS[1].add("Truffle-flavoured Poultry Supreme-$34.00"); 
        restaurantS[1].add("White Asparagus-$26.00");
        
        restaurantS[2].add("Caprese Salad-$10.00"); 
        restaurantS[2].add("Creme caramel-$6.50");  
        restaurantS[2].add("Lamb Chops with Apple Sauce-$25.00"); 
        restaurantS[2].add("Spaghetti alla Puttanesca-$15.00"); 
        
        restaurantS[3].add("Formaggio-$12.50");
        restaurantS[3].add("Ghiaccio-$1.10"); 
        restaurantS[3].add("Melone-$5.20");  
        restaurantS[3].add("Prosciutto and Pesci-$20.23"); 
        restaurantS[3].add("Risotto-$13.14"); 
        restaurantS[3].add("Zucchero and Sale-$0.60");
        
        restaurantS[4].add("Abbacchio’s Tea -$1.00"); 
        restaurantS[4].add("DIO’s Bread-$36.14");  
        restaurantS[4].add("Giorno’s Donuts-$6.66"); 
        restaurantS[4].add("Joseph’s Tequila-$35.00"); 
        restaurantS[4].add("Kakyoin’s Cherry-$3.50");
        restaurantS[4].add("Kakyoin’s Porridge-$4.44"); 
       
        int sizefood=ra.nextInt(8)+1;
        int numres=0;
        double cost=0;
        String[] food;
        String Saturdayfood = "";
        String Saturdayres="";
        
        // special order preferences for 6 characters       
        if (name.equals("Jonathan Joestar")) {
        	String[] residentInfo = retrieveResidentInfo(name);
            food = new String[sizefood];
            System.out.printf("%-10s%-40s%-50s\n", "Day", "Food", "Restaurant");
            if (sizefood == 0) {
                System.out.println("No order history!");
            } else {
                for (int i = 0; i < sizefood; i++) {
                    numres = ra.nextInt(5);
                    food[i] = restaurant[numres].RandomFood();
                    
                    if (JonathanJoestar(i + 1, food)) {
                        System.out.printf("%-10d%-40s%-50s\n", i + 1, food[i], restaurant[numres].name);
                        
                        try {
                            String filePath = String.format("/Users/wenhaowong/Desktop/day%d_order_history.txt", i + 1);
                            FileWriter fileWriter = new FileWriter(filePath);
                            PrintWriter printWriter = new PrintWriter(fileWriter);                
                            if (residentInfo != null) {
                                String age = residentInfo[0];
                                String gender = residentInfo[1];
                                
                                printWriter.printf("%-20s%-10s%-10s%-40s%-50s\n", name, age, gender, food[i], restaurant[numres].name);
                            }
                            
                            printWriter.close();
                        } catch (IOException e) {
                            System.out.println("Error writing to file: " + e.getMessage());
                        }
                    } else {
                        i--; }}}}
                        
        else if (name.equals("Joseph Joestar")) {
            String[] residentInfo = retrieveResidentInfo(name);
            food = new String[sizefood];
            
            System.out.printf("%-10s%-40s%-50s\n", "Day", "Food", "Restaurant");
            if (sizefood == 0) {
                System.out.println("No order history!");
            } else {
                for (int i = 0; i < sizefood; i++) {
                    numres = ra.nextInt(5);
                    food[i] = restaurant[numres].RandomFood();
                    
                    if (JosephJoestar(i, food)) {
                        System.out.printf("%-10d%-40s%-50s\n", i + 1, food[i], restaurant[numres].name);
                        
                        try {
                            String filePath = String.format("/Users/wenhaowong/Desktop/day%d_order_history.txt", i + 1);
                            FileWriter fileWriter = new FileWriter(filePath);
                            PrintWriter printWriter = new PrintWriter(fileWriter);
                            if (residentInfo != null) {
                                String age = residentInfo[0];
                                String gender = residentInfo[1];
                                
                                printWriter.printf("%-20s%-10s%-10s%-40s%-50s\n", name, age, gender, food[i], restaurant[numres].name);
                            }
                            
                            printWriter.close();
                        } catch (IOException e) {
                            System.out.println("Error writing to file: " + e.getMessage());
                        }
                    } else {
                        i--; }}}}

        else if (name.equals("Jotaro Kujo")) {
            String[] residentInfo = retrieveResidentInfo(name);
            food = new String[sizefood];

            System.out.printf("%-10s%-40s%-50s\n", "Day", "Food", "Restaurant");
            if (sizefood == 0) {
                System.out.println("No order history!");
            } else {
                try {
                    int i = 1, j = 0, k = 0;
                    numres = ra.nextInt(5);

                    while (i <= sizefood) {
                        if ((i - 1) == 6) {
                            food[i - 1] = restaurant[numres].food(j);
                            System.out.printf("%-10d%-40s%-50s\n", i, food[i - 1], restaurant[numres].name);

                            // Generate the file path for the current day
                            String filePath = String.format("/Users/wenhaowong/Desktop/day%d_order_history.txt", i);

                            FileWriter fileWriter = new FileWriter(filePath, true);
                            PrintWriter printWriter = new PrintWriter(fileWriter);

                            printWriter.printf("%-10s%-40s%-50s\n", "Day", "Food", "Restaurant");
                            printWriter.printf("%-10d%-40s%-50s\n", i, food[i - 1], restaurant[numres].name);

                            if (residentInfo != null) {
                                String age = residentInfo[0];
                                String gender = residentInfo[1];

                                printWriter.printf("%-20s%-10s%-10s%-40s%-50s\n", name, age, gender, food[i - 1], restaurant[numres].name);
                            }

                            printWriter.close();

                            Saturdayres = restaurant[numres].name;
                            Saturdayfood = food[i - 1];

                            j++;
                            i++;
                            k++;
                        } else if (k < restaurant[numres].count) {
                            food[i - 1] = restaurant[numres].food(j);
                            System.out.printf("%-10d%-40s%-50s\n", i, food[i - 1], restaurant[numres].name);

                            // Generate the file path for the current day
                            String filePath = String.format("/Users/wenhaowong/Desktop/day%d_order_history.txt", i);

                            FileWriter fileWriter = new FileWriter(filePath, true);
                            PrintWriter printWriter = new PrintWriter(fileWriter);
                            if (residentInfo != null) {
                                String age = residentInfo[0];
                                String gender = residentInfo[1];

                                printWriter.printf("%-20s%-10s%-10s%-40s%-50s\n", name, age, gender, food[i - 1], restaurant[numres].name);
                            }

                            printWriter.close();

                            j++;
                            i++;
                            k++;
                        } else {
                            if (numres == 4) {
                                numres = 0;
                                k = 0;
                                j = 0;
                            } else {
                                numres++;
                                k = 0;
                                j = 0;
                            }
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Error writing to file: " + e.getMessage()); }}}

        else if (name.equals("Giorno Giovanna")) {
            String[] residentInfo = retrieveResidentInfo(name);
            food = new String[sizefood];
            
            System.out.printf("%-10s%-40s%-50s\n", "Day", "Food", "Restaurant");
            if (sizefood == 0) {
                System.out.println("No order history!");
            } else {
                try {
                    for (int i = 0; i < sizefood; i++) {
                        numres = ra.nextInt(5);
                        
                        if (i % 3 == 0 && i != 0) {
                            food[i] = restaurant[2].RandomFood();
                            System.out.printf("%-10d%-40s%-50s\n", i + 1, food[i], restaurant[2].name);
                        } else {
                            food[i] = restaurant[numres].RandomFood();
                            
                            if (restaurant[numres].name.equals("Trattoria Trussardi")) {
                                i--;
                                continue;
                            } else {
                                System.out.printf("%-10d%-40s%-50s\n", i + 1, food[i], restaurant[numres].name);
                            }
                        }
                        
                        // Generate the file path for the current day
                        String filePath = String.format("/Users/wenhaowong/Desktop/day%d_order_history.txt", i + 1);
                        
                        FileWriter fileWriter = new FileWriter(filePath, true);
                        PrintWriter printWriter = new PrintWriter(fileWriter);
                        if (residentInfo != null) {
                            String age = residentInfo[0];
                            String gender = residentInfo[1];
                            
                            printWriter.printf("%-20s%-10s%-10s%-40s%-50s\n", name, age, gender, food[i], restaurant[numres].name);
                        }
                        
                        printWriter.close();
                    }
                } catch (IOException e) {
                    System.out.println("Error writing to file: " + e.getMessage()); }}}
        
        else if (name.equals("Josuke Higashikata")) {
            String[] residentInfo = retrieveResidentInfo(name);
            food = new String[30];
            System.out.printf("%-10s%-40s%-50s\n", "Day", "Food", "Restaurant");
            double total = 0;
            int i = 0;
            double newTotal = 0;

            while (total <= 100.00) {
                numres = ra.nextInt(5);
                food[i] = restaurantS[numres].RandomFood();
                String[] splitFood = food[i].split("\\$");
                double priceDish = Double.parseDouble(splitFood[1]);
                total += priceDish;

                if (total <= 100.00) {
                    System.out.printf("%-10d%-40s%-50s\n", i + 1, food[i].split("\\$")[0].replace("-", ""), restaurantS[numres].name);

                    // Generate the file path for the current day
                    String filePath = String.format("/Users/wenhaowong/Desktop/day%d_order_history.txt", i + 1);

                    try {
                        FileWriter fileWriter = new FileWriter(filePath, true);
                        PrintWriter printWriter = new PrintWriter(fileWriter);

                        if (residentInfo != null) {
                            String age = residentInfo[0];
                            String gender = residentInfo[1];

                            printWriter.printf("%-20s%-10s%-10s%-30s%-20s\n", name, age, gender, food[i].split("\\$")[0].replace("-", ""), restaurantS[numres].name);
                        }

                        printWriter.close();
                    } catch (IOException e) {
                        System.out.println("Error writing to file: " + e.getMessage());
                    }
                }

                newTotal = total - priceDish;
                i++;
            }

            System.out.println("The total: " + newTotal);
        }

        else if (name.equals("Jolyne Cujoh")) {
            String[] residentInfo = retrieveResidentInfo(name);
            food = new String[sizefood];
            System.out.printf("%-10s%-40s%-50s\n", "Day", "Food", "Restaurant");
            if (sizefood == 0)
                System.out.println("No order history!");
            else {
                numres = 0;
                int i = 0;
                while (i < sizefood) {
                    if (i == 6) {
                        food[i] = Saturdayfood;
                        System.out.printf("%-10d%-40s%-50s\\n", i + 1, food[i], Saturdayres);
                        
                        // Generate the file path for the current day
                        String filePath = String.format("/Users/wenhaowong/Desktop/day%d_order_history.txt", i + 1);

                        try {
                            FileWriter fileWriter = new FileWriter(filePath, true);
                            PrintWriter printWriter = new PrintWriter(fileWriter);
                            if (residentInfo != null) {
                                String age = residentInfo[0];
                                String gender = residentInfo[1];

                                printWriter.printf("%-20s%-10s%-10s%-30s%-20s\n", name, age, gender, food[i], Saturdayres);
                            }

                            printWriter.close();
                        } catch (IOException e) {
                            System.out.println("Error writing to file: " + e.getMessage());
                        }
                        
                    } else {
                        int k = i;
                        food[i] = restaurant[numres].RandomFood();
                        for (int j = 0; j < i; j++) {
                            if (food[j].equals(food[i]))
                                i--;
                        }

                        if (k == i) {
                            System.out.printf("%-10s%-40s%-50s\n", i + 1, food[i], restaurant[numres].name);
                            
                            // Generate the file path for the current day
                            String filePath = String.format("/Users/wenhaowong/Desktop/day%d_order_history.txt", i + 1);

                            try {
                                FileWriter fileWriter = new FileWriter(filePath, true);
                                PrintWriter printWriter = new PrintWriter(fileWriter);
                                printWriter.printf("%-10d%-40s%-50s\n", i + 1, food[i], restaurant[numres].name);

                                if (residentInfo != null) {
                                    String age = residentInfo[0];
                                    String gender = residentInfo[1];

                                    printWriter.printf("%-20s%-10s%-10s%-30s%-20s\n", name, age, gender, food[i], restaurant[numres].name);
                                }

                                printWriter.close();
                            } catch (IOException e) {
                                System.out.println("Error writing to file: " + e.getMessage()); }}}

                    if (numres == 4) {
                        numres = 0;
                    }
                    numres++;
                    i++; }}}
        
            	// except Joestar
            	else {
            	    String[] residentInfo = retrieveResidentInfo(name);
            	    food = new String[sizefood];
            	    System.out.printf("%-10s%-40s%-50s\n", "Day", "Food", "Restaurant");
            	    if (sizefood == 0) {
            	        System.out.println("No order history!");
            	    } else {
            	        try {
            	            // Generate order history for each day
            	            for (int i = 0; i < sizefood; i++) {
            	                numres = ra.nextInt(5);
            	                food[i] = restaurant[numres].RandomFood();
            	                System.out.printf("%-10d%-40s%-50s\n", i + 1, food[i], restaurant[numres].name);
            	                
            	                // Generate the file path for the current day
            	                String filePath = String.format("/Users/wenhaowong/Desktop/day%d_order_history.txt", i + 1);
            	                
            	                FileWriter fileWriter = new FileWriter(filePath, true);
            	                PrintWriter printWriter = new PrintWriter(fileWriter);
            	                
            	                if (residentInfo != null) {
            	                    String age = residentInfo[0];
            	                    String gender = residentInfo[1];
            	                              	           
            	                    if (age.isEmpty()) {
            	                        age = "0";
            	                    }
            	                    
            	                    // Write the order history for the current day
            	                    printWriter.printf("%-20s%-10s%-10s%-40s%-50s\n", name, age, gender, food[i], restaurant[numres].name);
            	                }
            	                printWriter.close();
            	            }
            	        } catch (IOException e) {
            	            System.out.println("Error writing to file: " + e.getMessage()); }}}}
    
    public String[] retrieveResidentInfo(String name) {
        String age = ""; String gender = "";     
        List<String[]> residentInfoList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("/Users/wenhaowong/Desktop/DS_Assignment/merged_file.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] residentInfo = line.split(",");
                residentInfoList.add(residentInfo);
            }
            residentInfoList.remove(0);

            for (String[] residentInfo : residentInfoList) {
                String nameInList = residentInfo[0];
                age = residentInfo[1];
                gender = residentInfo[2];

                if (name.equals(nameInList)) {
                    return new String[]{age, gender};
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();}
        return null; }
    
	public boolean JonathanJoestar(int sizefood, String[] food) {
        int min=10,max=0;
            for(int j=0;j<sizefood;j++) {
                int count=0;
                for(int k=0;k<sizefood;k++) {
                    if(food[j].equals(food[k])) {
                        count++;}}
                max=Math.max(count, max);
                min=Math.min(min, count);}
            if((max-min)<=1) return true;
            else return false; }
	
    public boolean JosephJoestar(int sizefood, String[] food) {    
            for(int j=0;j<sizefood;j++) {         
                if(food[j].equals(food[sizefood]))
                    return false;}
            return true; }}