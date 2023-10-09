package heavenDoor;
import java.util.*;
import theJoestars.*;
import theWorld_hermitPurple.*;
import java.io.*;
public  class HeavenDoor {
    public static void printHeavenDoor(String area) {
        Scanner sc = new Scanner(System.in);
        defaultMap hermitPurple = new defaultMap();
        Joestar joe = new Joestar();
                   
        // call HeavenDoor() method
        // print out the corresponding location residents list
        System.out.println("=".repeat(200));
        System.out.println("Residents' information in " + area);     
        residentInformation(area);
        joe.residentInformation(area);
        boolean status = true;
        
        while(status) {
            System.out.println("\n[1] View Resident's Profile");
            System.out.println("[2] Sort");
            System.out.println("[3] Exit\n");
            System.out.print("Select:");
            int chosen=sc.nextInt();
            sc.nextLine();
            if(chosen == 1){           	
                System.out.print("Enter the resident's name:");
                String name=sc.nextLine();
                joe.residentprofile(name);
                System.out.println();
                joe.orderhistory(name);
                
            }  
            else if(chosen == 2) {
            	sortResident(area);
            }
            else if(chosen == 3) {
            	status = false;
            	break;
            }
       }
 }

    public static void residentInformation(String area) {
        try {
            Scanner inputStream = new Scanner(new FileInputStream("/Users/wenhaowong/Desktop/DS_Assignment/merged_file.csv"));
    
            System.out.println("+----+----------------------+-------+--------+-------------------------+--------------------+----------+----------+----------+-----------+-----------------------+");
            System.out.println("| No | Name                 |  Age  | Gender | Stand                   | Destructive Power  | Speed    | Range    | Stamina  | Precision | Development Potential |");
            System.out.println("+----+----------------------+-------+--------+-------------------------+--------------------+----------+----------+----------+-----------+-----------------------+");
    
            int rowNum = 1; // Initialize row number to 1
            boolean matchFound = false; // Flag to track if a matching row is found
    
            while (inputStream.hasNextLine()) {
                String data = inputStream.nextLine();
                String[] values = data.split(",");
    
                if (values.length >= 12) {
                    for (int i = 0; i < values.length; i++) {
                        if (values[i] == null || values[i].isEmpty()) {
                            values[i] = "N/A";
                        } else if (values[i].equalsIgnoreCase("infinity")) {
                            values[i] = "∞";
                        }
                    }
    
                    if (area.equalsIgnoreCase(values[3])) {
                        if (!matchFound) {
                            matchFound = true;
                        } else {
                            rowNum++; // Increment row number for subsequent matching rows
                        }
    
                        System.out.printf("|%-4s|%-22s|%-7s|%-8s|%-25s|%-20s|%-10s|%-10s|%-10s|%-11s|%-23s|\n",
                                rowNum, values[0], values[1], values[2], values[5], values[6], values[7], values[8],
                                values[9], values[10], values[11]);
                                System.out.println("+----+----------------------+-------+--------+-------------------------+--------------------+----------+----------+----------+-----------+-----------------------+");
                    }
                } else {
                    // Handle the case where values.length is less than 12
                    String[] updatedValues = new String[12];
                    System.arraycopy(values, 0, updatedValues, 0, values.length);
    
                    for (int i = values.length; i < 12; i++) {
                        updatedValues[i] = "N/A";
                    }
    
                    values = updatedValues;
    
                    if (area.equalsIgnoreCase(values[3])) {
                        if (!matchFound) {
                            matchFound = true;
                        } else {
                            rowNum++; // Increment row number for subsequent matching rows
                        }
    
                        System.out.printf("|%-4s|%-22s|%-7s|%-8s|%-25s|%-20s|%-10s|%-10s|%-10s|%-11s|%-23s|\n",
                                rowNum, values[0], values[1], values[2], values[5], values[6], values[7], values[8],
                                values[9], values[10], values[11]);
                                System.out.println("+----+----------------------+-------+--------+-------------------------+--------------------+----------+----------+----------+-----------+-----------------------+");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    

    public static void sortResident(String area) {
        try {
            Scanner inputStream = new Scanner(new FileInputStream("/Users/wenhaowong/Desktop/DS_Assignment/merged_file.csv"));
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter the column to sort by (Stamina, Age, Precision): ");
            String sortBy = sc.nextLine();
            System.out.print("Enter the sorting order (ASC or DESC): ");
            String sortOrder = sc.nextLine();
            List<String[]> residents = new ArrayList<>();
            int rowNum = 1;
            boolean matchFound = false;
            
            while (inputStream.hasNextLine()) {
                String data = inputStream.nextLine();
                String[] values = data.split(",");
                
                if (values.length >= 12) {
                    for (int i = 0; i < values.length; i++) {
                        if (values[i].isEmpty()) {
                            values[i] = "N/A";
                        }
                    }
                    
                    if (area.equalsIgnoreCase(values[3])) {
                        if (!matchFound) {
                            matchFound = true;
                        } else {
                            rowNum++;
                        }
                        
                        residents.add(new String[] {
                                String.valueOf(rowNum), values[0], values[1], values[2], values[5], values[6], values[7],
                                values[8], values[9], values[10], values[11]
                        });
                        
                        rowNum++;
                    }
                } else {
                    String[] updatedValues = new String[12];
                    System.arraycopy(values, 0, updatedValues, 0, values.length);
                    
                    for (int i = values.length; i < 12; i++) {
                        updatedValues[i] = "N/A";
                    }
                    
                    values = updatedValues;
                    
                    if (area.equalsIgnoreCase(values[3])) {
                        if (!matchFound) {
                            matchFound = true;
                        } else {
                            rowNum++;
                        }
                        
                        residents.add(new String[] {
                                String.valueOf(rowNum), values[0], values[1], values[2], values[5], values[6], values[7],
                                values[8], values[9], values[10], values[11]
                        });
                        
                        rowNum++;
                    }
                }
            }
            
            final int sortColumnIndex;
            switch (sortBy.toLowerCase()) {
                case "name":
                    sortColumnIndex = 1;
                    break;
                case "gender":
                    sortColumnIndex = 3;
                    break;
                case "stand":
                    sortColumnIndex = 4;
                    break;
                case "destructive power":
                    sortColumnIndex = 5;
                    break;
                case "speed":
                    sortColumnIndex = 6;
                    break;
                case "range":
                    sortColumnIndex = 7;
                    break;
                case "stamina":
                    sortColumnIndex = 8;
                    break;
                case "precision":
                    sortColumnIndex = 9;
                    break;
                case "age":
                    sortColumnIndex = 2;
                    break;
                case "development potential":
                    sortColumnIndex = 10;
                    break;
                default:
                    System.out.println("Invalid column selected");
                    return;
            }
            
            Comparator<String[]> comparator = (a, b) -> {
                int result;
                if (sortOrder.equalsIgnoreCase("asc")) {
                    result = a[sortColumnIndex].compareTo(b[sortColumnIndex]);
                } else if (sortOrder.equalsIgnoreCase("desc")) {
                    result = b[sortColumnIndex].compareTo(a[sortColumnIndex]);
                } else {
                    System.out.println("Invalid sorting order selected");
                    return 0;
                }
                return result;
            };
            
            residents.sort(comparator);
            
            System.out.println("+----+----------------------+-------+--------+-------------------------+--------------------+----------+----------+----------+-----------+-----------------------+");
            System.out.println("| No | Name                 |  Age  | Gender | Stand                   | Destructive Power  | Speed    | Range    | Stamina  | Precision | Development Potential |");
            System.out.println("+----+----------------------+-------+--------+-------------------------+--------------------+----------+----------+----------+-----------+-----------------------+");
            
            for (String[] resident : residents) {
                System.out.printf("|%-4s|%-22s|%-7s|%-8s|%-25s|%-20s|%-10s|%-10s|%-10s|%-11s|%-23s|\n",
                        resident[0], resident[1], resident[2], resident[3], resident[4], resident[5], resident[6],
                        resident[7], resident[8], resident[9], resident[10]);
                System.out.println("+----+----------------------+-------+--------+-------------------------+--------------------+----------+----------+----------+-----------+-----------------------+");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}