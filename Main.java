
import java.io.*;
import java.util.*;

public class Main {

    static final int CAPACITY = 3;
    static final String FILE_NAME = "parking.txt";

    static Stack<String> parked = new Stack<>();
    static Queue<String> waiting = new LinkedList<>();
    static String[] slots = new String[CAPACITY];

    static HashMap<String, String> vehicleMap = new HashMap<>();

    static Scanner sc = new Scanner(System.in);

    // Update vehicle locations in HashMap
    static void updateMap() {
        vehicleMap.clear();

        for (int i = 0; i < CAPACITY; i++) {
            if (slots[i] != null) {
                vehicleMap.put(slots[i], "Slot " + (i + 1));
            }
        }

        int position = 1;
        for (String vehicle : waiting) {
            vehicleMap.put(vehicle, "Waiting Position " + position);
            position++;
        }
    }

    // Park vehicle
    static void parkVehicle() {
        System.out.print("Enter Vehicle Number: ");
        String vehicle = sc.nextLine().trim().toUpperCase();

        if (vehicle.isEmpty()) {
            System.out.println("Invalid vehicle number!");
            return;
        }

        updateMap();

        if (vehicleMap.containsKey(vehicle)) {
            System.out.println("Vehicle already registered!");
            return;
        }

        if (parked.size() < CAPACITY) {
            int slot = parked.size();

            parked.push(vehicle);
            slots[slot] = vehicle;

            System.out.println("Vehicle " + vehicle +
                    " parked. Slot: " + (slot + 1));
        } else {
            waiting.offer(vehicle);

            System.out.println("Lot full. Vehicle added to waiting queue.");
            System.out.println("Waiting Position: " + waiting.size());
        }

        updateMap();
        saveData();
    }

    // Exit vehicle
    static void exitVehicle() {
        if (parked.isEmpty()) {
            System.out.println("Parking lot is empty!");
            return;
        }

        System.out.print("Enter Vehicle Number: ");
        String vehicle = sc.nextLine().trim().toUpperCase();

        if (!parked.contains(vehicle)) {
            if (waiting.contains(vehicle)) {
                System.out.println("Vehicle is in waiting queue, not parked.");
            } else {
                System.out.println("Vehicle not found!");
            }
            return;
        }

        // Stack follows LIFO
        if (!parked.peek().equalsIgnoreCase(vehicle)) {
            System.out.println("Vehicle cannot exit!");
            System.out.println("First remove the vehicles parked above it.");
            System.out.println("Top vehicle: " + parked.peek());
            return;
        }

        parked.pop();

        int freedSlot = parked.size();
        slots[freedSlot] = null;

        System.out.println("Vehicle " + vehicle + " exited.");

        // Automatically park next waiting vehicle
        if (!waiting.isEmpty()) {
            String nextVehicle = waiting.poll();

            parked.push(nextVehicle);
            slots[freedSlot] = nextVehicle;

            System.out.println(nextVehicle +
                    " parked from queue. Slot: " + (freedSlot + 1));
        }

        updateMap();
        saveData();
    }

    // Search vehicle using HashMap
    static void searchVehicle() {
        System.out.print("Enter Vehicle Number: ");
        String vehicle = sc.nextLine().trim().toUpperCase();

        updateMap();

        if (vehicleMap.containsKey(vehicle)) {
            System.out.println("Vehicle " + vehicle + " found.");
            System.out.println("Location: " + vehicleMap.get(vehicle));
        } else {
            System.out.println("Vehicle " + vehicle + " not found.");
        }
    }

    // Display parking lot
    static void displayParking() {
        System.out.println("\n===== PARKING LOT STATUS =====");

        for (int i = 0; i < CAPACITY; i++) {
            if (slots[i] == null) {
                System.out.println("Slot " + (i + 1) + ": Empty");
            } else {
                System.out.println("Slot " + (i + 1) + ": " + slots[i]);
            }
        }

        System.out.println("\nWaiting Queue:");

        if (waiting.isEmpty()) {
            System.out.println("No vehicles waiting.");
        } else {
            int position = 1;

            for (String vehicle : waiting) {
                System.out.println(position + ". " + vehicle);
                position++;
            }
        }

        System.out.println("\nTotal Parked: " + parked.size());
        System.out.println("Total Waiting: " + waiting.size());
    }

    // Save data into file
    static void saveData() {
        try (PrintWriter writer =
                     new PrintWriter(new FileWriter(FILE_NAME))) {

            for (String vehicle : parked) {
                writer.println("PARKED:" + vehicle);
            }

            for (String vehicle : waiting) {
                writer.println("WAITING:" + vehicle);
            }

        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    // Load data from file
    static void loadData() {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                if (line.startsWith("PARKED:")) {
                    String vehicle = line.substring(7);

                    if (!vehicle.isEmpty() && !vehicleMap.containsKey(vehicle)) {
                        if (parked.size() < CAPACITY) {
                            parked.push(vehicle);
                            slots[parked.size() - 1] = vehicle;
                        } else {
                            waiting.offer(vehicle);
                        }
                    }

                } else if (line.startsWith("WAITING:")) {
                    String vehicle = line.substring(8);

                    if (!vehicle.isEmpty() && !vehicleMap.containsKey(vehicle)) {
                        waiting.offer(vehicle);
                    }
                }

                updateMap();
            }

            System.out.println("Previous parking data loaded.");

        } catch (IOException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }

        updateMap();
    }

    // Main method
    public static void main(String[] args) {

        loadData();

        while (true) {

            System.out.println("\n===== PARKING LOT MANAGER =====");
            System.out.println("1. Park Vehicle");
            System.out.println("2. Exit Vehicle");
            System.out.println("3. Search Vehicle");
            System.out.println("4. Display Parking");
            System.out.println("5. Save and Exit");

            System.out.print("Enter your choice: ");

            String input = sc.nextLine();

            int choice;

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid option!");
                continue;
            }

            switch (choice) {

                case 1:
                    parkVehicle();
                    break;

                case 2:
                    exitVehicle();
                    break;

                case 3:
                    searchVehicle();
                    break;

                case 4:
                    displayParking();
                    break;

                case 5:
                    saveData();
                    System.out.println("Data saved successfully.");
                    System.out.println("Thank you for using Parking Lot Manager!");
                    return;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
