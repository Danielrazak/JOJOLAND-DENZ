package pearlJam;
import java.io.*;
import java.util.*;

public class TrattoriaTrussardi {
    private List<Customer> queue;

    public TrattoriaTrussardi() {
        queue = new ArrayList<>();
    }

    public void add(Customer customer) {
        queue.add(customer);
    }

    public void serveCustomers() {
        boolean serveYoungestMan = true;
        boolean serveOldestWoman = true;

        while (!queue.isEmpty()) {
            Customer customerToServe = null;

            // Serve the youngest man
            if (serveYoungestMan) {
                customerToServe = getYoungestMaleCustomer();
                if (customerToServe != null) {
                    System.out.printf("\n%-23s %-15d %-15s %6s", customerToServe.getName(), customerToServe.getAge(), customerToServe.getGender(), customerToServe.getOrder());
                    queue.remove(customerToServe);
                }
                serveYoungestMan = false;
            }
            // Serve the oldest woman
            else if (serveOldestWoman) {
                customerToServe = getOldestFemaleCustomer();
                if (customerToServe != null) {
                    System.out.printf("\n%-23s %-15d %-15s %6s", customerToServe.getName(), customerToServe.getAge(), customerToServe.getGender(), customerToServe.getOrder());
                    queue.remove(customerToServe);
                }
                serveOldestWoman = false;
            }
            // Serve the oldest man
            else {
                customerToServe = getOldestMaleCustomer();
                if (customerToServe != null) {
                    System.out.printf("\n%-23s %-15d %-15s %6s", customerToServe.getName(), customerToServe.getAge(), customerToServe.getGender(), customerToServe.getOrder());
                    queue.remove(customerToServe);
                }

                // If there are no more men, serve the youngest woman
                if (customerToServe == null && !queue.isEmpty()) {
                    customerToServe = getYoungestFemaleCustomer();
                    if (customerToServe != null) {
                        System.out.printf("\n%-23s %-15d %-15s %6s", customerToServe.getName(), customerToServe.getAge(), customerToServe.getGender(), customerToServe.getOrder());
                        queue.remove(customerToServe);
                    }
                }

                // Reset the gender flags for the next turn
                serveYoungestMan = true;
                serveOldestWoman = true;
            }
        }
    }

    public Customer getYoungestMaleCustomer() {
        Customer youngestMale = null;
        for (Customer customer : queue) {
            if (customer.getGender().equals("Male") && (youngestMale == null || customer.getAge() < youngestMale.getAge())) {
                youngestMale = customer;
            }
        }
        return youngestMale;
    }

    public Customer getOldestFemaleCustomer() {
        Customer oldestFemale = null;
        for (Customer customer : queue) {
            if (customer.getGender().equals("Female") && (oldestFemale == null || customer.getAge() > oldestFemale.getAge())) {
                oldestFemale = customer;
            }
        }
        return oldestFemale;
    }

    public Customer getOldestMaleCustomer() {
        Customer oldestMale = null;
        for (Customer customer : queue) {
            if (customer.getGender().equals("Male") && (oldestMale == null || customer.getAge() > oldestMale.getAge())) {
                oldestMale = customer;
            }
        }
        return oldestMale;
    }

    public Customer getYoungestFemaleCustomer() {
        Customer youngestFemale = null;
        for (Customer customer : queue) {
            if (customer.getGender().equals("Female") && (youngestFemale == null || customer.getAge() < youngestFemale.getAge())) {
                youngestFemale = customer;
            }
        }
        return youngestFemale;
    }

    public static void trattoriaTrussardi(int day) {
        TrattoriaTrussardi cafe = new TrattoriaTrussardi();
        System.out.println("Restaurant: Trattoria Trussardi\n");
        List<String> orderList = new ArrayList<>();
        List<String> names = new ArrayList<>();
        List<Integer> ages = new ArrayList<>();
        List<String> genders = new ArrayList<>();
        Set<String> encounteredNames = readEncounteredNames("/Users/wenhaowong/Desktop/encountered_names.txt");

        String filePath = String.format("/Users/wenhaowong/Desktop/day%d_order_history.txt", day);
        String restaurantName = "Trattoria Trussardi";
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