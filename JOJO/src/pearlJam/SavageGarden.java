package pearlJam;
import java.io.*;
import java.util.*;
public class SavageGarden {
    private Queue<Customer> queue;
    private int day;

    public SavageGarden() {
        queue = new LinkedList<>();
        day = 1;
    }

    public void add(Customer customer) {
        queue.add(customer);
    }
    
    // specific rules
    public void serveCustomers() {
        while (!queue.isEmpty()) {
            int size = queue.size();
            int servedCustomers = 0;

            // iterate through customers in the queue
            for (int i = 0; i < size; i++) {
                Customer customer = queue.poll();
                int number = i + 1;

                // check if number matches the day number
                if (number == day) {
                    // customer is served
                    System.out.printf("\n%-23s %-15d %-15s %6s", customer.getName(), customer.getAge(), customer.getGender(), customer.getOrder());
                    servedCustomers++;
                } else {
                    // customer is not served yet, add them back to the queue
                    queue.add(customer);
                }
            }

            // if no customers were served, move to the next day
            if (servedCustomers == 0) {
                day++;
            }
        }
    }

    public static void savageGarden(int day) {
        SavageGarden cafe = new SavageGarden();
        System.out.println("Restaurant: Savage Garden\n");
        List<String> orderList = new ArrayList<>();
        List<String> names = new ArrayList<>();
        List<Integer> ages = new ArrayList<>();
        List<String> genders = new ArrayList<>();
        Set<String> encounteredNames = readEncounteredNames("/Users/wenhaowong/Desktop/encountered_names.txt");

        String filePath = String.format("/Users/wenhaowong/Desktop/day%d_order_history.txt", day);
        String restaurantName = "Savage Garden";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains(restaurantName)) {
                    String[] parts = line.split("\\s{2,}");
                    if (parts.length >= 5) {
                        String name = parts[0].trim();
                        int age = (int) Double.parseDouble(parts[1].trim());
                        String gender = parts[2].trim();
                        String foodOrder = parts[3].trim();

                        // Check if the name has already been encountered
                        if (encounteredNames.contains(name)) {
                            continue; // Ignore the line if the name is already encountered
                        }
                        encounteredNames.add(name);

                        names.add(name);
                        ages.add(age);
                        genders.add(gender);
                        orderList.add(foodOrder);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (orderList.isEmpty())
            System.out.println("No orders found or the customer has served.");

        for (int i = 0; i < names.size(); i++) {
            String customerName = names.get(i);
            int customerAge = ages.get(i);
            String customerGender = genders.get(i);
            String customerOrder = orderList.get(i);
            cafe.add(new Customer(customerName, customerAge, customerGender, customerOrder));
        }

        writeEncounteredNames("/Users/wenhaowong/Desktop/encountered_names.txt", encounteredNames);

        System.out.println("Waiting List");
        System.out.println("-+---------------------------------------------------------------------------------------+-");
        System.out.println("Name\t\t\tAge\t\tGender\t\tOrder");
        System.out.println("-+---------------------------------------------------------------------------------------+-\n");
        for (Customer customer : cafe.queue) {
            System.out.println(customer);
        }

        System.out.println("\nOrder Processing list");
        System.out.println("-+---------------------------------------------------------------------------------------+-");
        System.out.println("Name\t\t\tAge\t\tGender\t\tOrder");
        System.out.println("-+---------------------------------------------------------------------------------------+-");
        cafe.serveCustomers();
        System.out.println();
        System.out.println("=".repeat(200));
    }

    private static Set<String> readEncounteredNames(String filePath) {
        Set<String> encounteredNames = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                encounteredNames.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return encounteredNames;
    }

    private static void writeEncounteredNames(String filePath, Set<String> encounteredNames) {
        try (PrintWriter writer = new PrintWriter(filePath)) {
            for (String name : encounteredNames) {
                writer.println(name);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}