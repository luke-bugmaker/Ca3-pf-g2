import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.Arrays;

public class CakeOrderingSystem {
    private ArrayList<Cake> cakes;
    private ArrayList<Customer> customers;
    private ArrayList<Order> orders;
    private Scanner scanner;

    public CakeOrderingSystem() {
        cakes = new ArrayList<>();
        customers = new ArrayList<>();
        orders = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;
        while (running) {
            displayMainMenu();
            int choice = getIntInput("Enter option: ");

            switch (choice) {
                case 1: manageCakes(); break;
                case 2: manageCustomers(); break;
                case 3: manageOrders(); break;
                case 4: generateReports(); break;
                case 0: running = false; break;
                default: System.out.println("Invalid option!");
            }
        }
    }

    private void displayMainMenu() {
        System.out.println("\n*** Main Menu ***");
        System.out.println("1. Manage Cakes");
        System.out.println("2. Manage Customers");
        System.out.println("3. Manage Orders");
        System.out.println("4. Generate Reports");
        System.out.println("0. Exit");
    }

    //manageCakes
    private void manageCakes() {
        while (true) {
            System.out.println("\n** Cake Menu **");
            System.out.println("1. Create Cake");
            System.out.println("2. Update Cake");
            System.out.println("3. Delete Cake");
            System.out.println("4. View all Cakes");
            System.out.println("5. Back to Main Menu");

            int choice = getIntInput("Enter option: ");

            switch (choice) {
                case 1: createCake(); break;
                case 2: updateCake(); break;
                case 3: deleteCake(); break;
                case 4: viewAllCakes(); break;
                case 5: return;
                default: System.out.println("Invalid option!");
            }
        }
    }

    private void createCake() {
        try {
            System.out.println("\nEnter Cake details:");

            // Get and validate the cake code
            String code = getStringInput("Enter Cake code (e.g., C001): ").toUpperCase(); // Convert input to uppercase
            if (!code.matches("C\\d{3}")) { // Validate format: "C" followed by 3 digits
                throw new IllegalArgumentException("Cake code must start with 'C' followed by 3 digits (e.g., C001)!");
            }
            if (cakes.stream().anyMatch(c -> c.getCakeCode().equals(code))) {
                throw new IllegalArgumentException("Cake code already exists!");
            }

            // Get the cake name
            String name = capitalizeEachWord(getStringInput("Enter Cake name: ").toUpperCase()); // Capitalize the name

            // Get the cake price
            double price = getDoubleInput("Enter Cake price: ");

            // Add the new cake to the list
            cakes.add(new Cake(code, name, price));
            System.out.println("Cake created successfully!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void updateCake() {
        try {
            // Get the cake code from the user
            String code = getStringInput("Enter Cake code to update: ").toUpperCase();

            // Check if the cake exists in the list
            Cake cakeToUpdate = cakes.stream()
                    .filter(c -> c.getCakeCode().equals(code)) // Find the cake with the given code
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Invalid Cake code!"));

            // Prompt the user for updated details
            String name = getStringInput("Update Cake name: ").toUpperCase();
            double price = getDoubleInput("Update Cake price: ");

            // Update the cake details
            cakeToUpdate.setCakeName(name);
            cakeToUpdate.setPrice(price);

            // Confirm the update
            System.out.println("Cake '" + code + "' updated!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void deleteCake(){
        try{
            String code = getStringInput("Enter Cake Code: ").toUpperCase();
            // Check if the cake exists in the list
            Cake cakeToDelete = cakes.stream()
                    .filter(c -> c.getCakeCode().equals(code)) // Find the cake with the given code
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException(
                            String.format("Cake Code %s doesn't exist!", code)));

            cakes.remove(cakeToDelete);
            System.out.printf("Cake %s deleted successfully", code);

        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void viewAllCakes(){
        try{
            if(cakes.isEmpty()){
                throw new IllegalArgumentException("There is no Cakes in the list");
            }
            cakes.forEach(cake -> System.out.println(cake.toString()));

        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    //manageCustomers
    private void manageCustomers(){
        while (true) {
            System.out.println("\n** Customer Menu **");
            System.out.println("1. Add Customer");
            System.out.println("2. Edit Customer");
            System.out.println("3. Remove Customer");
            System.out.println("4. View all Customers");
            System.out.println("5. Back to Main Menu");

            int choice = getIntInput("Enter option: ");

            switch (choice) {
                case 1: addCustomer(); break;
                case 2: editCustomer(); break;
                case 3: removeCustomer(); break;
                case 4: viewAllCustomer(); break;
                case 5: return;
                default: System.out.println("Invalid option!");
            }
        }

    }

    private void addCustomer(){
        try{
            System.out.println("\nEnter Customer Details:");

            int id = getIntInput("Enter Customer ID: ");
            if (!String.valueOf(id).matches("1\\d{3}")) { // "1" followed by exactly 3 digits
                throw new IllegalArgumentException("Customer ID must be a 4-digit number starting with '1' (e.g., 1001)!");
            }
            if (customers.stream().anyMatch(customer -> customer.getCustomerId() == id)) {
                throw new IllegalArgumentException("Customer ID " + id + " already exist!");
            }
            //Changing Customer Name to capitalized
            String name = capitalizeEachWord(getStringInput("Enter Customers Full name: "));

            int contactNumber = getIntInput("Enter Customer contact number: ");

            String Address = getStringInput("Enter Customer Address: ");

            customers.add(new Customer(id, name, contactNumber, Address));
            System.out.println("New Customer added!");

        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }


    }

    private void editCustomer(){
        try{
            int id = getIntInput("Enter Customer ID to edit");

            Customer customerToEdit = customers.stream()
                    .filter(customer -> customer.getCustomerId() == id)
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Invalid Customer ID"));

            //All information can be edited or kept as current
            String name = capitalizeEachWord(getStringInput("Edit Customers Full name (leave empty to keep current): "));
            String newName = name.isEmpty() ? customerToEdit.getFullName() : capitalizeEachWord(name);

            int contactNumber = getIntInput("Edit Customer contact number (or press -1 to keep current): ");
            int newContactNumber = (contactNumber == -1) ? customerToEdit.getContactNumber() : contactNumber;


            String Address = getStringInput("Edit Customer Address (leave empty to keep current): ");
            String newAddress = Address.isEmpty() ? customerToEdit.getAddress() : Address;

            customerToEdit.setFullName(newName);
            customerToEdit.setContactNumber(newContactNumber);
            customerToEdit.setAddress(newAddress);
            System.out.println(" Customer edited!");

        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void removeCustomer(){
        try{
            int id = getIntInput("Enter Customer ID to edit");

            Customer customerToRemove = customers.stream()
                    .filter(customer -> customer.getCustomerId() == id)
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Invalid Customer ID"));
            customers.remove(customerToRemove);
            System.out.printf("Customer %d removed", id);

        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }

    }

    private void viewAllCustomer(){
        try{
            if(customers.isEmpty()){
                throw new IllegalArgumentException("There is no Customers in the list");
            }
            customers.forEach(customer -> System.out.println(customer.toString()));

        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }

    }

    //manageOrders
    private void manageOrders(){
        while (true) {
            System.out.println("\n** Order Menu **");
            System.out.println("1. Make an Order");
            System.out.println("2. Cancel an Order");
            System.out.println("3. View All Orders");
            System.out.println("4. Back to Main Menu");

            int choice = getIntInput("Enter option: ");

            switch (choice) {
                case 1: makeOrder(); break;
                case 2: cancelOrder(); break;
                case 3: viewAllOrders(); break;
                case 4: return;
                default: System.out.println("Invalid option!");
            }
        }
    }

    private void makeOrder() {
        try {
            // Get the order date
            String orderDateInput = getStringInput("Enter Order date (dd/mm/yyyy): ");

            // Validate and parse the order date
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate orderDate;
            try {
                orderDate = LocalDate.parse(orderDateInput, formatter);
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Invalid date format! Use dd/mm/yyyy.");
            }

            // Get Customer ID
            int customerId = getIntInput("Enter Customer ID: ");

            // Validate if customer exists
            Customer customer = customers.stream()
                    .filter(c -> c.getCustomerId() == customerId)
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Invalid Customer ID"));

            // Get Cake Code
            String cakeCode = getStringInput("Enter Cake code: ").toUpperCase();

            // Validate if cake exists
            Cake cake = cakes.stream()
                    .filter(c -> c.getCakeCode().equals(cakeCode))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Invalid Cake Code"));

            // Generate a new Order ID (incremental string-based ID)
            String newOrderId = orders.isEmpty() ? "50001" : String.valueOf(Integer.parseInt(orders.getLast().getOrderId()) + 1);

            // Create and add the order
            Order newOrder = new Order(newOrderId, orderDate, cakeCode, customerId);
            orders.add(newOrder);

            // Output success message
            System.out.println("Cake '" + cake.getCakeCode() + "' ordered, Order ID is " + newOrderId);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void cancelOrder(){
        try{
            String orderId = getStringInput("Enter Order ID: ");

            // Validate if cake exists
            Order orderToCancel = orders.stream()
                    .filter(orders -> orders.getOrderId().equals(orderId))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Invalid Order ID"));
            orders.remove(orderToCancel);
            System.out.printf("Order %d has been canceled", orderId);

        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void viewAllOrders(){
        try{
            if(orders.isEmpty()){
                throw new IllegalArgumentException("There is no Orders in the list");
            }
            orders.forEach(order -> System.out.println(order.toString()));
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }

    }

    //Generate Reports
    private void generateReports(){
        while (true) {
            System.out.println("\n** Reports Menu **");
            System.out.println("1. Generate Orders report by Date");
            System.out.println("2. Generate Orders report by Cake");
            System.out.println("3. Generate Orders report by Customer");
            System.out.println("4. Generate total number of Orders");
            System.out.println("5. Back to Main Menu");

            int choice = getIntInput("Enter option: ");

            switch (choice) {
                case 1: generateOrderByDate(); break;
                case 2: generateOrdersByCake(); break;
                case 3: generateOrdersByCustomer(); break;
                case 4: viewAllReports(); break;
                case 5: return;
                default: System.out.println("Invalid option!");
            }
        }
    }

    private void generateOrderByDate(){
        try {
            String date = getStringInput("Enter Order date (dd/mm/yyyy): ");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate orderDate;

            try {
                orderDate = LocalDate.parse(date, formatter);
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Invalid date format! Use dd/mm/yyyy.");
            }

            // set to check if has an order same as the date
            boolean found = false;
            for (Order order : orders) {
                if (order.getOrderDate().equals(orderDate)) {
                    System.out.println(order);
                    found = true;
                    //when found a asking date, then change the found to ture
                }
            }
            if (!found) {
                System.out.println("No orders found for the given date: " + orderDate);
            }
        } catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    private void generateOrdersByCake(){
        try{
            // Get and validate the cake code
            String cakeCode = getStringInput("Enter Cake code (e.g., C001): ").toUpperCase();
            if (!cakeCode.matches("C\\d{3}")) { // Validate format: "C" followed by 3 digits
                throw new IllegalArgumentException("Cake code must start with 'C' followed by 3 digits (e.g., C001)!");
            }
            if (cakes.stream().noneMatch(c -> c.getCakeCode().equals(cakeCode))) {
                throw new IllegalArgumentException("Invalid Cake Code!");
            }
            for (Order order : orders) {
                if (order.getCakeCode().equals(cakeCode)) {
                    System.out.println(order);
                }
            }
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void generateOrdersByCustomer(){
        try{
            int id = getIntInput("Enter Customer ID: ");
            if (!String.valueOf(id).matches("1\\d{3}")) { // "1" followed by exactly 3 digits
                throw new IllegalArgumentException("Customer ID must be a 4-digit number starting with '1' (e.g., 1001)!");
            }
            if (customers.stream().noneMatch(customer -> customer.getCustomerId() == id)) {
                throw new IllegalArgumentException("Customer ID " + id + " does not exist!");
            }
            for (Order order : orders) {
                if (order.getCustomerId() == id) {
                    System.out.println(order);
                }
            }
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void viewAllReports(){
        int totalOrders = orders.size();

        // Display the result
        System.out.println("Total number of Orders: " + totalOrders);

    }

    // Helper methods for input validation
    private String getStringInput(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            throw new IllegalArgumentException("Input cannot be empty");
        }
        return input;
    }

    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number");
            }
        }
    }

    private double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number");
            }
        }
    }

    public String capitalizeEachWord(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return Arrays.stream(input.split("\\s+"))
                .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase())
                .collect(Collectors.joining(" "));
    }

    // Main method to run the application
    public static void main(String[] args) {
        CakeOrderingSystem system = new CakeOrderingSystem();
        System.out.println("Welcome to the Cake Ordering System");
        system.start();
        System.out.println("Thank you for using the Cake Ordering System!");
    }
}