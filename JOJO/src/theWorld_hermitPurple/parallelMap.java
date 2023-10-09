package theWorld_hermitPurple;
import java.util.*;  
import java.io.*; 
import bitesDust.*;
import dirtyDeeds.*;
import goldenSpirit.*;
import heavenDoor.HeavenDoor;
import moody_milagro.*;
import pearlJam.*;
import superFly.*;
import theJoestars.*;
public class parallelMap {
    public Scanner scanner = new Scanner(System.in);
    public UndirectedGraph<String, Integer> parallelMap = new UndirectedGraph<>();
    public String currentLocation ="Town Hall";
    public Stack<String> history;
    public Stack<String> forwardHistory;
    public int currentDay;


    public parallelMap(){
        // Adding all cities as a vertex inside the defaultMap
        String[] cities = {"Town Hall", "Morioh Grand Hotel", "Trattoria Trussardi", "Green Dolphin Street Prison",
                            "Angelo Rock", "DIO's Mansion","Vineyard", "Savage Garden", "Polnareff Land", "Cafe Deux Magots",
                            "Jade Garden","San Giorgio Maggiore", "Libeccio", "Joestar Mansion"};
        for (String i : cities) 
        	parallelMap.addVertex(i);
        
        // San Giorgio Maggiore as origin
        parallelMap.addEdge("San Giorgio Maggiore", "Joestar Mansion", 5);
        parallelMap.addEdge("San Giorgio Maggiore", "Savage Garden", 6);
        
        // Joestar Mansion as origin
        parallelMap.addEdge("Joestar Mansion", "Trattoria Trussardi", 5);
        parallelMap.addEdge("Joestar Mansion", "Morioh Grand Hotel", 4);
        parallelMap.addEdge("Joestar Mansion", "Jade Garden", 3);

        // Savage Garden as origin
        parallelMap.addEdge("Savage Gardenn", "Jade Garden", 4);
        parallelMap.addEdge("Savage Garden", "Cafe Deux Magots", 5);

        // Jade Garden as origin
        parallelMap.addEdge("Jade Garden", "Cafe Deux Magots", 3);

        // Cafe Deux Magots as origin
        parallelMap.addEdge("Cafe Deux Magots", "Polnareff Land", 2);
        parallelMap.addEdge("Cafe Deux Magots", "Town Hall", 4);
        parallelMap.addEdge("Cafe Deux Magots", "Morioh Grand Hotel", 6);

        // Trattoria Trussardi as origin
        parallelMap.addEdge("Trattoria Trussardi", "Town Hall", 6);
        parallelMap.addEdge("Trattoria Trussardi", "Angelo Rock", 3);
        parallelMap.addEdge("Trattoria Trussardi", "DIO's Mansion", 4);

        // Town Hall as origin
        parallelMap.addEdge("Town Hall", "Vineyard", 3);
        parallelMap.addEdge("Town Hall", "Libeccio", 2);
     
        // Libeccio as origin
        parallelMap.addEdge("Libeccio", "Vineyard", 3);     

        // Angelo Rock as origin
        parallelMap.addEdge("Angelo Rock", "DIO's Mansion", 1);
        parallelMap.addEdge("Angelo Rock", "Green Dolphin Street Prison", 8);
        
        // DIO's Mansion as origin
        parallelMap.addEdge("DIO's Mansion", "Green Dolphin Street Prison", 6);
        

        currentLocation = "Town Hall";
        history = new Stack<>();
        forwardHistory = new Stack<>();
        currentDay = 1;
    }

	public void move(String destination) {
        int distance = parallelMap.getEdgeWeight(currentLocation, destination);
        if (distance == -1) {
            System.out.println("Error: " + destination + " is not adjacent to " + currentLocation);
            return;
        }
        history.push(currentLocation);
        forwardHistory.clear();
        currentLocation = destination;
        System.out.println("Moved to " + destination);
    }

    public void moveBack() {
        if (history.isEmpty()) {
            System.out.println("Error: no history to go back to");
            return;
        }
        forwardHistory.push(currentLocation);
        currentLocation = history.pop();
        System.out.println("Moved back to " + currentLocation);
    }

    public void moveForward() {
        if (forwardHistory.isEmpty()) {
            System.out.println("Error: no forward history to go to");
            return;
        }
        history.push(currentLocation);
        currentLocation = forwardHistory.pop();
        System.out.println("Moved forward to " + currentLocation);
    }

    public void moveTownHall() {
        history.push(currentLocation);
        forwardHistory.clear();
        currentLocation = "Town Hall";
        System.out.println("Moved to Town Hall");
    }

    public void advanceDay() {
        currentDay++;
        currentLocation = "Town Hall";
        history.clear();
        forwardHistory.clear();
        System.out.println("Advanced to Day " + currentDay);
    }

    public ArrayList<String> getNeighbours(){
        return(parallelMap.getNeighboursWithIndex(currentLocation));      
    }
    
    public static void saveGame(int currentDay) {
        try {
            File file = new File("/Users/wenhaowong/Desktop/saved_game.txt");
            FileWriter writer = new FileWriter(file);
            writer.write(String.valueOf(currentDay));
            writer.close();
            System.out.println("Game saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving the game.");
        }
    }
    
    private static boolean isValidLocation(String location) {
        String[] validLocations = {
            "Town Hall", "Morioh Grand Hotel", "Trattoria Trussardi", "Green Dolphin Street Prison",
            "Angelo Rock", "DIO's Mansion","Vineyard", "Savage Garden", "Polnareff Land", "Cafe Deux Magots",
            "Jade Garden", "San Giorgio Maggiore", "Libeccio", "Joestar Mansion"
        };
        for (String validLocation : validLocations) {
            if (location.equals(validLocation)) {
                return true;
            }
        }
        return false;
    }
    
    public static void start(int savedDay) {
		Joestar joe = new Joestar();
		parallelMap game = new parallelMap();
		game.currentDay = savedDay;
		System.out.println("=".repeat(200));
		System.out.println("It’s Day "+ game.currentDay + " of our journey in JOJOLands! ");
		while (true) {
			System.out.println("=".repeat(200));
			System.out.println("Current location: " + game.currentLocation);       
            switch(game.currentLocation){            
                case "Town Hall":
	                System.out.println("[1] Move to: \n" + game.getNeighbours());
	                System.out.println("""
	                		[2] Advance to Next Day
	                		[3] Save Game
	                		[4] Exit
	                		""");       
                break;                         
                case "Trattoria Trussardi":
                	System.out.println("[1] Move to: " + game.getNeighbours());
	                System.out.println("""
		                	[2] View Waiting List and Order Processing List
	                		[3] View Menu
	                		[4] View Sales Information
	                		[5] Milagro Man
	                		[6] Back                		
	                		[7] Back to Town Hall
	                		[8] Forward
	                		""");
                break;
                case "Jade Garden":
                	System.out.println("[1] Move to: " + game.getNeighbours() );
                	 System.out.println("""
                 	 		[2] View Waiting List and Order Processing List
                 	 		[3] View Menu
                 	 		[4] View Sales Information
                 	 		[5] Milagro Man
                 	 		[6] Back
                 	 		[7] Back to Town Hall
                 	 		[8] Forward
                 	 		""");
                break;
                case "Cafe Deux Magots":
                	System.out.println("[1] Move to: " + game.getNeighbours() );
                	System.out.println("""
                [2] View Waiting List and Order Processing List
                [3] View Menu
                [4] View Sales Information
                [5] Milagro Man
                [6] Back
                [7] Back to Town Hall 
                [8] Forward	 		
                			""");               	
                break;
                case "Libeccio":
                    System.out.println("[1] Move to: " + game.getNeighbours());
                    System.out.println("""
                	[2] View Waiting List and Order Processing List
                 [3] View Menu
                 [4] View Sales Information
                 [5] Milagro Man
                	[6] Back
                	[7] Back to Town Hall
                	[8] Forward
                	 		"""); 
                break;  
                case "Savage Garden":
                    System.out.println("[1] Move to: " + game.getNeighbours());
                    System.out.println("""
                	[2] View Waiting List and Order Processing List
                 [3] View Menu
                 [4] View Sales Information
                 [5] Milagro Man  
                 [6] Back            	 		
                	[7] Back to Town Hall   
                	[8] Forward       
                	 		""");          
                break;             
                case "Morioh Grand Hotel":
	                System.out.println("[1] Move to: " + game.getNeighbours());
	                System.out.println("""
	                		[2] View Resident Information
	                		[3] The Hand
	                		[4] Back
	                		[5] Forward
	                		[6] Back to Town Hall
	                		""");
                break;
                case "Angelo Rock":
                	System.out.println("[1] Move to: " + game.getNeighbours());
                	System.out.println("""
                			[2] View Resident Information
                			[3] Red Hot Chili Pepper
                			[4] Back
                			[5] Forward
                			[6] Back to Town Hall
                			[7] Another One Bites the Dust 
                	 		""");       
                break;
                case "Polnareff Land":
                    System.out.println("[1] Move to: " + game.getNeighbours());
                    System.out.println("""
                	 		[2] View Resident Information
                	 		[3] Back
                	 		[4] Back to Town Hall
                	 		[5] Forward
                	 		""");          
                break;
                case "Joestar Mansion":
                    System.out.println("[1] Move to: " + game.getNeighbours());
                    System.out.println("""
                	 		[2] View Resident Information
                	 		[3] Back
                	 		[4] Back to Town Hall
                	 		[5] Forward
                	 		[6] The Golden Spirit
                	 		""");          
                break;
                case "Vineyard":
                    System.out.println("[1] Move to: " + game.getNeighbours());
                    System.out.println("""
                	 		[2] View Resident Information
                	 		[3] Back
                	 		[4] Back to Town Hall
                	 		[5] Forward
                	 		""");          
                break;        
                case "DIO's Mansion":
                    System.out.println("[1] Move to: " + game.getNeighbours());
                    System.out.println("""
                	 		[2] View Resident Information
                	 		[3] Back
                	 		[4] Back to Town Hall
                	 		[5] Forward
                	 		""");          
                break; 
                case "Green Dolphin Street Prison":
                	System.out.println("[1] Move to: " + game.getNeighbours());
                    System.out.println("""
                	 		[2] View Resident Information
                	 		[3] Back
                	 		[4] Dirty Deeds Done Dirt Cheap
                	 		[5] Back to Town Hall
                	 		[6] Forward
                	 		""");
                break;  
                case "San Giorgio Maggiore":
                    System.out.println("[1] Move to: " + game.getNeighbours());
                    System.out.println("""
                	 		[2] View Resident Information
                	 		[3] Back
                	 		[4] Back to Town Hall
                	 		[5] Forward
                	 		""");          
                break;  
                default:
                	System.out.println("Invalid");
                	break;
            }

            System.out.print("Select: ");
            String choice = game.scanner.nextLine();
            choice = choice.trim();
            // scanner.nextLine(); // consume newline
            switch (choice) {
            case "1":
                System.out.print("Location: ");
                String choiceDes = game.scanner.nextLine();
                while (!isValidLocation(choiceDes)) {
                    System.out.println("Invalid location. Please try again.");
                    System.out.print("Location: ");
                    choiceDes = game.scanner.nextLine();
                }
                game.move(choiceDes);
                break;
                
                case "2":
                    if (game.currentLocation.equals("Town Hall")) {
                        game.advanceDay();  break;             		
                    } else if (game.currentLocation.equals("Trattoria Trussardi")) {              
                        TrattoriaTrussardi.trattoriaTrussardi(game.currentDay );               		
                    } else if(game.currentLocation.equals("Jade Garden")) {
                        JadeGarden.jadeGarden(game.currentDay ); 	break;	
                    } else if(game.currentLocation.equals("Cafe Deux Magots")) {               	
                        CafeDeuxMagots.cafeDeuxMagots(game.currentDay );  break;	               		
                    } else if(game.currentLocation.equals("Libeccio")) {              	
                        Libeccio.libeccio(game.currentDay );  break;              		
                    } else if(game.currentLocation.equals("Savage Garden")) {               	
                        SavageGarden.savageGarden(game.currentDay );   break;	              		
                    } else if(game.currentLocation.equals("Morioh Grand Hotel")) {                 
                        HeavenDoor.printHeavenDoor(game.currentLocation); break;               	           			       		
                    } else if(game.currentLocation.equals("Polnareff Land")) {             	
                        HeavenDoor.printHeavenDoor(game.currentLocation);   break;          
                    } else if(game.currentLocation.equals("Joestar Mansion")) {               	
                        HeavenDoor.printHeavenDoor(game.currentLocation); break;         		
                    } else if(game.currentLocation.equals("Angelo Rock")) {              
                        HeavenDoor.printHeavenDoor(game.currentLocation); break;           	
                    } else if(game.currentLocation.equals("Vineyard")) {              
                        HeavenDoor.printHeavenDoor(game.currentLocation);  break;        
                    } else if(game.currentLocation.equals("DIO's Mansion")) {              
                        HeavenDoor.printHeavenDoor(game.currentLocation); break;      
                    } else if(game.currentLocation.equals("Green Dolphin Street Prison")) {              
                        HeavenDoor.printHeavenDoor(game.currentLocation); break;      
                    } else if(game.currentLocation.equals("San Giorgio Maggiore")) {              
                        HeavenDoor.printHeavenDoor(game.currentLocation);break; 
                    } 

                case "3":
                    if (game.currentLocation.equals("Town Hall")) {
                        saveGame(game.currentDay);break;
                    } else if (game.currentLocation.equals("Morioh Grand Hotel")) {
                        TheHand theHand = new TheHand();
                        theHand.printTheHand();
                    } else if (game.currentLocation.equals("Angelo Rock")) {
                        RedHotChiliPepper redhot = new RedHotChiliPepper();
                        redhot.redHot();
                    } else if (game.currentLocation.equals("Jade Garden")) {
                        Menu menu = new Menu(game.currentLocation);
                        menu.printMenu();
                        break;
                    } else if (game.currentLocation.equals("Cafe Deux Magots")) {
                        Menu menu = new Menu(game.currentLocation);
                        menu.printMenu();
                        break;
                    } else if (game.currentLocation.equals("Savage Garden")) {
                        Menu menu = new Menu(game.currentLocation);
                        menu.printMenu();
                        break;
                    } else if (game.currentLocation.equals("Libeccio")) {
                        Menu menu = new Menu(game.currentLocation);
                        menu.printMenu();
                        break;
                    } else if (game.currentLocation.equals("Trattoria Trussardi")) {
                        Menu menu = new Menu(game.currentLocation);
                        menu.printMenu();
                        break;     
                    } else {
                        game.moveBack();
                    }
                    break;
                    
                case "4":
                    if (game.currentLocation.equals("Town Hall")) {
                        System.out.println("Goodbye!");
                        System.exit(0);
                        break;
                    } else if (game.currentLocation.equals("Morioh Grand Hotel") || game.currentLocation.equals("Angelo Rock")) {
                        game.moveBack();
                    } else if (game.currentLocation.equals("Trattoria Trussardi")) {
                        Menu menu = new Menu(game.currentLocation);
                        SalesRecord record = new SalesRecord(menu);
                        MoodyBlues salesInfo = new MoodyBlues(game.currentLocation);
                        salesInfo.printMoodyBlues();     
                    } else if (game.currentLocation.equals("Jade Garden")) {
                        Menu menu = new Menu(game.currentLocation);
                        SalesRecord record = new SalesRecord(menu);
                        MoodyBlues salesInfo = new MoodyBlues(game.currentLocation);
                        salesInfo.printMoodyBlues();       
                    } else if (game.currentLocation.equals("Savage Garden")) {
                        Menu menu = new Menu(game.currentLocation);
                        SalesRecord record = new SalesRecord(menu);
                        MoodyBlues salesInfo = new MoodyBlues(game.currentLocation);
                        salesInfo.printMoodyBlues();       
                    } else if (game.currentLocation.equals("Cafe Deux Magots")) {
                        Menu menu = new Menu(game.currentLocation);
                        SalesRecord record = new SalesRecord(menu);
                        MoodyBlues salesInfo = new MoodyBlues(game.currentLocation);
                        salesInfo.printMoodyBlues();       
                    } else if (game.currentLocation.equals("Libeccio")) {
                        Menu menu = new Menu(game.currentLocation);
                        SalesRecord record = new SalesRecord(menu);
                        MoodyBlues salesInfo = new MoodyBlues(game.currentLocation);
                        salesInfo.printMoodyBlues();   
                    } else if (game.currentLocation.equals("Green Dolphin Street Prison")) {
                        Dirtydeeds.printDirtyDeeds();
                        break;
                    } else {
                        game.moveTownHall();
                    }
                    break;

                case "5":
                    if (game.currentLocation.equals("Morioh Grand Hotel") || game.currentLocation.equals("Angelo Rock") || game.currentLocation.equals("San Giorgio Maggiore") || game.currentLocation.equals("DIO's Mansion") || game.currentLocation.equals("Vineyard")
                            || game.currentLocation.equals("Joestar Mansion") || game.currentLocation.equals("Polnareff Land")) {
                        game.moveForward();                
                    } else if (game.currentLocation.equals("Trattoria Trussardi")) {               
                        // milagro man
                        Menu menu = new Menu(game.currentLocation);
                        SalesRecord record = new SalesRecord(menu);
                        MilagroMan milagroMan = new MilagroMan(game.currentLocation);
                        milagroMan.printMilagroMan();
                    } else if (game.currentLocation.equals("Jade Garden")) {
                        Menu menu = new Menu(game.currentLocation);
                        SalesRecord record = new SalesRecord(menu);
                        MilagroMan milagroMan = new MilagroMan(game.currentLocation);
                        milagroMan.printMilagroMan();
                    } else if (game.currentLocation.equals("Savage Garden")) {
                        Menu menu = new Menu(game.currentLocation);
                        SalesRecord record = new SalesRecord(menu);
                        MilagroMan milagroMan = new MilagroMan(game.currentLocation);
                        milagroMan.printMilagroMan();
                    } else if (game.currentLocation.equals("Libeccio")) {
                        Menu menu = new Menu(game.currentLocation);
                        SalesRecord record = new SalesRecord(menu);
                        MilagroMan milagroMan = new MilagroMan(game.currentLocation);
                        milagroMan.printMilagroMan();
                    } else if (game.currentLocation.equals("Cafe Deux Magots")) {
                        Menu menu = new Menu(game.currentLocation);
                        SalesRecord record = new SalesRecord(menu);
                        MilagroMan milagroMan = new MilagroMan(game.currentLocation);
                        milagroMan.printMilagroMan();             
                    } else {
                        game.moveTownHall();
                    }
                    break;

                case "6":
                    if (game.currentLocation.equals("Morioh Grand Hotel") || game.currentLocation.equals("Angelo Rock")) {
                        game.moveTownHall();
                    } else if (game.currentLocation.equals("Trattoria Trussardi") || game.currentLocation.equals("Jade Garden") || game.currentLocation.equals("Savage Garden") 
                            || game.currentLocation.equals("Libeccio") || game.currentLocation.equals("Cafe Deux Magots")) {
                        game.moveBack();
                    } else if (game.currentLocation.equals("Green Dolphin Street Prison")) {
                        game.moveForward();
                    } else if (game.currentLocation.equals("Joestar Mansion")) {
                        TheGoldenSpirit goldenSpirit = new TheGoldenSpirit();
                        goldenSpirit.printGoldenSpirit();
                    } else {
                        System.out.println("Invalid choice.");
                        break;
                    }
                    break;

                case "7":
                    if (game.currentLocation.equals("Trattoria Trussardi")) {
                        game.moveTownHall();
                    } else if(game.currentLocation.equals("Jade Garden")) {
                        game.moveTownHall();
                    } else if(game.currentLocation.equals("Cafe Deux Magots")) {
                        game.moveTownHall();
                    } else if(game.currentLocation.equals("Savage Garden")) {
                        game.moveTownHall();
                    } else if(game.currentLocation.equals("Libeccio")) {
                        game.moveTownHall();
                    } else if(game.currentLocation.equals("Angelo Rock")) {
                        BiteTheDusts.bite();
                        break;
                    } else {
                        System.out.println("Invalid");
                    }
                   break;
                
                case "8":          
                    if (game.currentLocation.equals("Trattoria Trussardi") || game.currentLocation.equals("Jade Garden") || game.currentLocation.equals("Cafe Deux Magots") || game.currentLocation.equals("Libeccio") || game.currentLocation.equals("Savage Garden")) {
                        game.moveForward();
                    } break;
                default: System.out.println("Invalid choice");
                		 break;
            }
		}
	}
}