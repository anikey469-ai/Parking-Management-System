# 🚗 Parking Lot Manager

### A Java-Based Console Application Using Data Structures and File Handling

## 📌 Project Overview

**Parking Lot Manager** is a console-based Java application designed to manage a single-lane parking lot efficiently.

The application allows users to park vehicles, remove vehicles, search for vehicle information, and display the current parking status.

It uses fundamental Data Structures and Algorithms concepts such as Stack, Queue, Array, and HashMap, along with Java File Handling to maintain parking records even after the application is closed.

The parking lot has a fixed capacity of **3 vehicles**. When all slots are occupied, additional vehicles are automatically added to a waiting queue.

## 🎯 Project Objectives

- Develop a menu-driven parking management system using Java.
- Implement Stack for managing parked vehicles.
- Implement Queue for managing waiting vehicles.
- Use HashMap for efficient vehicle searching.
- Use File Handling to store and retrieve parking data.
- Prevent duplicate vehicle entries.
- Automatically assign available slots to waiting vehicles.

## ✨ Features

### 1. Park Vehicle
- Accepts vehicle registration numbers.
- Assigns an available parking slot.
- Adds vehicles to the waiting queue when parking is full.
- Prevents duplicate vehicle registration.

### 2. Exit Vehicle
- Allows the topmost parked vehicle to exit.
- Follows the LIFO principle of Stack.
- Automatically parks the next waiting vehicle when a slot becomes available.

### 3. Search Vehicle
- Searches vehicles using their registration numbers.
- Uses HashMap for efficient lookup.
- Displays the vehicle's current slot or waiting position.

### 4. Display Parking Status
- Displays all three parking slots.
- Shows occupied and empty slots.
- Displays the waiting queue.
- Shows total parked and waiting vehicles.

### 5. File Handling
- Saves parking information in `parking.txt`.
- Loads previously saved records when the application starts.
- Maintains vehicle information between application sessions.

## 🛠️ Technologies Used

| Technology | Purpose |
|---|---|
| Java | Core programming language |
| Stack | Managing parked vehicles |
| Queue | Managing waiting vehicles |
| Array | Representing parking slots |
| HashMap | Searching vehicle records |
| File Handling | Persistent data storage |
| Scanner | Taking user input |
| Exception Handling | Managing invalid inputs and file errors |

## 🧠 Data Structures Implemented

**1. Stack (LIFO)**

Stack follows Last In, First Out. The last vehicle parked must exit first because the parking lot has a single-lane arrangement.

**2. Queue (FIFO)**

Queue follows First In, First Out. The vehicle that enters the waiting queue first gets the next available parking slot.

**3. Array**

An array of size 3 represents the parking slots and stores vehicle registration numbers.

**4. HashMap**

HashMap stores vehicle registration numbers as keys and their current parking locations as values.

## 📂 Project Structure

```text
Parking Management System/
│
├── Main.java
├── parking.txt
└── README.md
```

- `Main.java` – Contains the complete Java application.
- `parking.txt` – Stores parked and waiting vehicle records. Created automatically.
- `README.md` – Project documentation.

## ⚙️ Installation and Execution

### Prerequisites

- Java Development Kit (JDK 8 or above)
- Visual Studio Code or any Java-supported IDE
- Java Extension Pack (for VS Code)

### Step 1: Clone the Repository

```bash
git clone YOUR_GITHUB_REPOSITORY_URL
```

### Step 2: Open the Project Folder

```bash
cd "Parking Management System"
```

### Step 3: Compile the Java Program

```bash
javac Main.java
```

### Step 4: Run the Application

```bash
java Main
```

## 🖥️ Application Menu

```text
===== PARKING LOT MANAGER =====

1. Park Vehicle
2. Exit Vehicle
3. Search Vehicle
4. Display Parking
5. Save and Exit

Enter your choice:
```

## 🧪 Sample Test Cases

| Test Case | Input / Scenario | Expected Result |
|---|---|---|
| Normal Input | Park CG04A1 | Vehicle parked in Slot 1 |
| Boundary Input | Park fourth vehicle | Added to waiting queue |
| Invalid Input | Search CG04Z9 | Vehicle not found |
| Exit Scenario | Remove top vehicle | Next waiting vehicle gets the free slot |

### Sample Output

```text
===== PARKING LOT MANAGER =====

1. Park Vehicle
2. Exit Vehicle
3. Search Vehicle
4. Display Parking
5. Save and Exit

Enter your choice: 1

Enter Vehicle Number: CG04A1

Vehicle CG04A1 parked. Slot: 1
```

### Parking Full Scenario

```text
Enter your choice: 1
Enter Vehicle Number: CG04D4

Lot full. Vehicle added to waiting queue.
Waiting Position: 1

## ⏱️ Time Complexity
| Operation | Average Time Complexity |
|---|---|
| Park Vehicle | O(1) |
| Exit Vehicle | O(n) |
| Search Vehicle | O(1) |
| Display Parking | O(n) |
| HashMap Lookup | O(1) |

*Note: These are average-case estimates for the main data structure operations. Some operations involve additional traversal or file I/O.*

## 🔮 Future Enhancements
- Add a graphical user interface (GUI).
- Introduce vehicle entry and exit timestamps.
- Implement parking fee calculation.
- Support multiple parking floors.
- Add an administrator login system.
- Generate daily parking reports.
- Integrate a database such as MySQL.

## Author
**Aniket Kumar**
B.Tech – Artificial Intelligence & Machine Learning
Interested in Java Development, Backend Development, Data Structures and Problem Solving.
