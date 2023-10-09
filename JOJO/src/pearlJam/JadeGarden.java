package pearlJam;

import java.io.*;
import java.util.*;

public class JadeGarden {
	public static void jadeGarden(int day) {
	    System.out.println("Restaurant: Jade Garden\n");
	    List<String> orderList = new ArrayList<>();
	    List<String> names = new ArrayList<>();
	    List<Integer> ages = new ArrayList<>();
	    List<String> genders = new ArrayList<>();

	    String filePath = String.format("/Users/wenhaowong/Desktop/day%d_order_history.txt", day);
	    String restaurantName = "Jade Garden";
	    Set<String> processedCustomers = new HashSet<>();

	    String outputFilePath = "/Users/wenhaowong/Desktop/encountered_names.txt";
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath, true))) {
	        BufferedReader br = new BufferedReader(new FileReader(filePath));
	        String line;
	        while ((line = br.readLine()) != null) {
	            if (line.contains(restaurantName)) {
	                String[] parts = line.split("\\s{2,}");
	                if (parts.length >= 5) {
	                    String name = parts[0].trim();
	                    int age = (int) Double.parseDouble(parts[1].trim());
	                    String gender = parts[2].trim();
	                    String foodOrder = parts[3].trim();

	                    // Read the encountered names file and check if the name has already been encountered before
	                    boolean skipLine = false;
	                    try (BufferedReader reader = new BufferedReader(new FileReader(outputFilePath))) {
	                        String encounteredLine;
	                        while ((encounteredLine = reader.readLine()) != null) {
	                            if (encounteredLine.trim().equals(name)) {
	                                skipLine = true;
	                                break;
	                            }
	                        }
	                    } catch (IOException e) {
	                        e.printStackTrace();
	                    }

	                    if (skipLine) {
	                        continue; // Ignore the line if the name is already encountered in the encountered names file
	                    }

	                    // Write the name to the output file
	                    writer.write(name);
	                    writer.newLine();

	                    String customerKey = name + age + gender + foodOrder;
	                    if (!processedCustomers.contains(customerKey)) {
	                        processedCustomers.add(customerKey);
	                        if (age == 0) {
	                            age = 0;
	                        }
	                        names.add(name);
	                        ages.add(age);
	                        genders.add(gender);
	                        orderList.add(foodOrder);
	                    }
	                }
	            }
	        }
	        br.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }


        Queue<Customer> customers = new ArrayDeque<>();

        if (orderList.isEmpty()) {
            System.out.println("No orders found or the customer has served.");
        }

        for (int i = 0; i < names.size(); i++) {
            String customerName = names.get(i);
            int customerAge = ages.get(i);
            String customerGender = genders.get(i);
            String customerOrder = orderList.get(i);

            customers.add(new Customer(customerName, customerAge, customerGender, customerOrder));
        }

        if (!orderList.isEmpty()) {
        // waiting list
        System.out.println("Waiting list");
        System.out.println("-+---------------------------------------------------------------------------------------+-");
        System.out.println("Name\t\t\tAge\t\tGender\t\tOrder");
        System.out.println("-+---------------------------------------------------------------------------------------+-");
        for (Customer customer : customers) {
            System.out.printf("%-23s %-15d %-15s %s\n", customer.getName(), customer.getAge(), customer.getGender(), customer.getOrder());
        }

        // order processing list
        System.out.println("\nOrder Processing list");
        System.out.println("-+---------------------------------------------------------------------------------------+-");
        System.out.println("Name\t\t\tAge\t\tGender\t\tOrder");
        System.out.println("-+---------------------------------------------------------------------------------------+-");

        LinkedList<Customer> customerList = new LinkedList<>(customers);

        while (!customerList.isEmpty()) {
            Customer firstCustomer = customerList.poll();
            Customer lastCustomer = customerList.pollLast();

            if (firstCustomer != null) {
                System.out.printf("%-23s %-15d %-15s %s\n", firstCustomer.getName(), firstCustomer.getAge(), firstCustomer.getGender(), firstCustomer.getOrder());
            }

            if (lastCustomer != null) {
                System.out.printf("%-23s %-15d %-15s %s\n", lastCustomer.getName(), lastCustomer.getAge(), lastCustomer.getGender(), lastCustomer.getOrder());
            }
        }
        System.out.println("=".repeat(200));
    }
	}
}

class Customerss implements Comparable<Customerss> {
    private String name;
    private int age;
    private String gender;
    private String order;

    public Customerss(String name, int age, String gender, String order) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getOrder() {
        return order;
    }

    @Override
    public String toString() {
        return String.format("%-23s %-15d %-15s %5s", name, age, gender, order);
    }

    @Override
    public int compareTo(Customerss other) {
        // Compare customers based on their arrival time
        // Implement your comparison logic here
        // Return a negative value if this customer arrived before the other customer
        // Return a positive value if this customer arrived after the other customer
        // Return 0 if both customers arrived at the same time

        // Example implementation based on customer age
        return Integer.compare(this.age, other.getAge());
    }
}
