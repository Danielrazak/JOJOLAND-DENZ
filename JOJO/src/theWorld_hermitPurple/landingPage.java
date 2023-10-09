package theWorld_hermitPurple;
import java.io.*;  
import java.util.*;
public class landingPage {
	public int currentDay;
	public String currentLocation ="Town Hall";
	
	 public static int loadSavedDay() {
	        try {
	            File file = new File("/Users/wenhaowong/Desktop/saved_game.txt");
	            Scanner scanner = new Scanner(file);
	            int savedDay = scanner.nextInt();
	            scanner.close();
	            return savedDay;
	        } catch (FileNotFoundException e) {
	            return -1; // Return -1 if saved_game.txt doesn't exist
	        }
	    }
	 
	public static void main(String[] args) {
		Scanner sc= new Scanner (System.in);	
		System.out.println("""
				Welcome, to the fantastical realm of JOJOLands.\n
				[1] Start Game
				[2] Load Game
				[3] Exit
				""");
		System.out.println("=".repeat(200));
		System.out.print("Select: "); String selection = sc.next();
		while(!selection.equals("1") && !selection.equals("2") && !selection.equals("3")) {
			System.out.print("Select: "); selection = sc.next();
		}
		switch(selection) {
			case "1": System.out.println("""
				\nSelect a map: 
				[1] Default Map
				[2] Parallel Map
				[3] Alternate Map
				""");
				System.out.print("\nSelect: "); String mapSelect =sc.next();
				while(!mapSelect.equals("1") && !mapSelect.equals("2") && !mapSelect.equals("3")) {
					System.out.print("Select: "); mapSelect = sc.next();
				}
				if(mapSelect.equals("1")) {					
			        defaultMap.start(1);
				}
				else if(mapSelect.equals("2")) {					
					parallelMap.start(1);
				}
				else if(mapSelect.equals("3")) {					
					alternateMap.start(1);
				}
				break;
				
		 case "2":
			 int savedDay = loadSavedDay(); // Load the saved day from file
	            if (savedDay != -1) {
	            	System.out.println("""
	        				\nSelect a map: 
	        				[1] Default Map
	        				[2] Parallel Map
	        				[3] Alternate Map
	        				""");
	            	System.out.print("Select the previous saved map: "); String savedSelect = sc.next();
	            	if(savedSelect.equals("1")) {
	            		defaultMap.start(savedDay); // Pass the saved day to the map() method
	            	}
	            	else if(savedSelect.equals("2")) {
	            		parallelMap.start(savedDay);
	            	}
	            	else if(savedSelect.equals("3")) {
	            		alternateMap.start(savedDay);
	            	}
	            } else {
	                System.out.println("No saved game found.");
	            }
             break;
             
		case "3": System.out.println("Goodbye!");
				System.exit(0);
		}
	}
}