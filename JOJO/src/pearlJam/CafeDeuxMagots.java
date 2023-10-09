package pearlJam;
import java.io.*;  
import java.util.*;
public class CafeDeuxMagots {
    private static List<Customer> queue = new ArrayList<>();

    public void addCustomer(Customer customer) {
        queue.add(customer);
    }
    
    public Customer getOldestCustomer() {
        if (queue.isEmpty()) {
            return null;
        }
        Customer oldest = queue.get(0);
        for (Customer customer : queue) {
            if (customer.getAge() > oldest.getAge()) {
                oldest = customer;
            }
        }
        return oldest;
    }
    
    public Customer getYoungestCustomer() {
        if (queue.isEmpty()) {
            return null;
        }

        Customer youngest = queue.get(0);
        for (int i = 1; i < queue.size(); i++) {
            Customer customer = queue.get(i);
            if (customer.getAge() < youngest.getAge()) {
                youngest = customer;
            }
        }
        return youngest;
    }

    public void getNullAge(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null");
        }

        if (customer.getAge() == 0) {
            queue.add(customer);
        } else {
            boolean inserted = false;
            for (int i = 0; i < queue.size(); i++) {
                Customer c = queue.get(i);
                if (c.getAge() == 0 || c.getAge() > customer.getAge()) {
                    queue.add(i, customer);
                    inserted = true;
                    break;
                }
            }
            if (!inserted) {
                queue.add(customer);
            }
        }
    }


    public void serveCustomer(Customer customer) {
        this.queue.remove(customer);
    }

    public static void cafeDeuxMagots(int day) {
        System.out.println("Restaurant: Cafe Deux Magots\n");

        // Preprocessing
        List<String> orderList = new ArrayList<>();
        List<String> names = new ArrayList<>();
        List<Integer> ages = new ArrayList<>();
        List<String> genders = new ArrayList<>();

        String filePath = String.format("/Users/wenhaowong/Desktop/day%d_order_history.txt", day);
        String restaurantName = "Cafe Deux Magots";
        String encounteredNamesFilePath = "/Users/wenhaowong/Desktop/encountered_names.txt";
        Set<String> encounteredNames = readEncounteredNames(encounteredNamesFilePath);

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains(restaurantName)) {
                    String[] parts = line.split("\\s{2,}");
                    if (parts.length >= 5) {
                        String name = parts[0].trim();
                        String ageStr = parts[1].trim();
                        int age = 0;
                        if (!ageStr.equals("null")) {
                            age = (int) Double.parseDouble(ageStr);
                        } else {
                            ageStr = "N/A"; // Replace "null" with "N/A"
                        }
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

        CafeDeuxMagots cafe = new CafeDeuxMagots();
        for (int i = 0; i < names.size(); i++) {
            String customerName = names.get(i);
            int customerAge = ages.get(i);
            String customerGender = genders.get(i);
            String customerOrder = orderList.get(i);
            cafe.addCustomer(new Customer(customerName, customerAge, customerGender, customerOrder));
        }
        if(!orderList.isEmpty()) {
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
        int index = 0;
        while (!cafe.queue.isEmpty()) {
            Customer customer;
            if (index % 2 == 0) {
                customer = cafe.getOldestCustomer();
            } else {
                customer = cafe.getYoungestCustomer();
            }
            cafe.serveCustomer(customer);

            System.out.printf("\n%-23s %-15d %-15s %6s", customer.getName(), customer.getAge(), customer.getGender(), customer.getOrder());
            index++;
        }
        System.out.println();
        System.out.println("=".repeat(200));
        }

        writeEncounteredNames(encounteredNamesFilePath, encounteredNames);
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
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            for (String name : encounteredNames) {
                bw.write(name);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}