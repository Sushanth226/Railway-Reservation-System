# Railway Reservation System (Core Java)

This is a realistic, core Java-based Railway Reservation System built without any external frameworks (like Spring Boot) or databases. It is designed to demonstrate strong fundamental Java skills, particularly Object-Oriented Programming, the Collections Framework, Exception Handling, File I/O, and Serialization.

## 🚀 Features

### Passenger Management
* Register new passengers.
* Auto-generation of unique Passenger IDs.

### Train Management
* Add new trains to the system (Train Number, Name, Route, Capacity, Price).
* View all available trains.
* Search for specific trains using their Train Number.

### Ticket Management
* Book a ticket by linking a Passenger ID and Train Number.
* Auto-generate a unique PNR for every booking.
* Automatically allocate the next available seat and deduct from the train's total capacity.
* Cancel tickets and automatically restore seat availability on the train.
* View and search for booked tickets.

### Data Persistence (Serialization)
* The system does **not** use a SQL database. 
* It uses **Java Serialization** to save the state of all HashMaps into local `.dat` files (`passengers.dat`, `trains.dat`, `tickets.dat`).
* Data is automatically loaded into memory when the application starts and saved to disk after every modification.

## 🛠️ Technology Stack
* **Language:** Java 21 (Core Java)
* **Build Tool:** Maven (Project structure only, no external dependencies)
* **Data Structures:** HashMaps, ArrayLists
* **Storage:** Local File System (Java `ObjectOutputStream` / `ObjectInputStream`)

## 🏗️ Project Architecture
The project follows a standard layered architecture:
* **UI Layer:** `RailwayReservationApp.java` handles all user inputs via the terminal console.
* **Service Layer:** Interfaces (`BookingService`, `TrainService`, `PassengerService`) and their Implementations contain the business logic.
* **Model Layer:** POJOs (`Passenger`, `Train`, `Ticket`) act as data carriers.
* **Exception Layer:** Custom Exceptions (`TrainNotFoundException`, `InvalidPNRException`) enforce strict business rules.
* **Utility Layer:** `FileUtil.java` handles all File I/O operations cleanly.

## 💻 How to Run (Without Maven)

If you just want to run the application directly from the terminal (Windows PowerShell):

1. **Create the output directory:**
   ```powershell
   mkdir -Force target/classes
   ```
2. **Compile the source code:**
   ```powershell
   Get-ChildItem -Path src\main\java -Filter *.java -Recurse | Select-Object -ExpandProperty FullName > sources.txt
   javac -d target/classes @sources.txt
   ```
3. **Run the Application:**
   ```powershell
   java -cp target/classes com.railway.main.RailwayReservationApp
   ```

## 🧠 Core OOP Concepts Demonstrated
* **Encapsulation:** Private fields in models accessed via Getters and Setters.
* **Abstraction:** Relying on interfaces (e.g., `BookingService`) rather than concrete implementations in the UI layer.
* **Composition:** The `Ticket` object "has-a" `Passenger` and "has-a" `Train`.
* **Polymorphism:** Method overriding (e.g., `toString()`).
