# Travel Office Management System

A desktop application for managing a travel agency, built with JavaFX and MySQL. This project was developed as part of the "Java Programming" course at university.

## Authors

- **Patryk Bilski**
- **Michał Pacierz**

This project was completed as a 2-person team assignment.

## Description

Travel Office is a JavaFX-based desktop application that allows users to:
- Browse available trips and hotels
- View trip details including dates, prices, and availability
- Purchase trips with real-time seat tracking
- View purchase history
- Manage travel bookings through an intuitive GUI

## Technologies

- **Java** with JavaFX for GUI
- **MySQL** for database management
- **Eclipse IDE** (recommended)

## ⚙️ Prerequisites

Before running this project, ensure you have:

- **Java JDK 11+**
- **Eclipse IDE** (recommended)
- **JavaFX SDK 11.0.2** (recommended version)
- **MySQL Connector/J 5.1.49** (recommended version)
- **MySQL Server** installed and running

## Installation

### 1. Download Required Libraries

Download the following libraries:
- [JavaFX SDK 11.0.2](https://gluonhq.com/products/javafx/)
- [MySQL Connector/J 5.1.49](https://dev.mysql.com/downloads/connector/j/)

Extract JavaFX SDK to a known location (e.g., `C:\javafx-sdk-11\`).

### 2. Import Project into Eclipse

1. Open Eclipse IDE
2. Go to **File → Import → Existing Projects into Workspace**
3. Select the project directory
4. Click **Finish**

### 3. Configure JavaFX Library

1. Right-click on the project → **Properties**
2. Select **Java Build Path** → **Libraries** tab
3. Click on **Modulepath** → **Add External JARs...**
4. Navigate to your JavaFX SDK folder (e.g., `C:\javafx-sdk-11\lib`)
5. Select **all .jar files** in the lib directory
6. Click **Apply**

### 4. Configure MySQL Connector

1. Still in **Libraries** tab → **Modulepath** (or **Classpath**)
2. Click **Add External JARs...**
3. Navigate to your MySQL Connector location (e.g., `C:\mysql-connector-java-5.1.49.jar`)
4. Select the jar file
5. Click **Apply** and **OK**

### 5. Configure Run Configuration

1. Right-click on the project → **Run As → Run Configurations...**
2. Select **Java Application** → create a new configuration
3. Set **Main class** to: `application.TravelOffice`
4. Go to the **Arguments** tab
5. In **VM arguments** field, add:
   ```
   --module-path C:\javafx-sdk-11\lib --add-modules javafx.controls,javafx.fxml
   ```
   *(Adjust the path to match your JavaFX SDK location)*
6. Go to the **Dependencies** tab (or **Classpath** in older Eclipse versions)
7. Ensure all external libraries (JavaFX and MySQL Connector) are added
8. Click **Apply**

### 6. Setup Database

You have two options for setting up the database:

#### Option A: Automatic Setup (Application Creates Database)

1. Start your MySQL server
2. Run the application - it will automatically create the database schema on first run
3. After the application creates the database, you need to manually populate it with initial data
4. Execute the SQL script provided in `sqlScript.sql` to insert data:
   - Open MySQL Workbench or your preferred MySQL client
   - Connect to your MySQL server
   - Execute only the INSERT statements from `sqlScript.sql`

#### Option B: Manual Setup (Recommended)

1. Start your MySQL server
2. Open MySQL Workbench or your preferred MySQL client
3. Connect to your MySQL server
4. Execute the complete `sqlScript.sql` file:
   - This will create the database `TravelOffice`
   - Create all required tables (Hotel, Trip, User, Bought_trip)
   - Populate tables with initial data
5. Run the application

**Note:** Make sure to configure your database connection details in the application source code if needed (default credentials are typically: `localhost`, `root`, `root`).

## Running the Application

1. Open the project in Eclipse
2. Right-click on `TravelOffice.java`
3. Select **Run As → Java Application**
4. The application window should appear

**Default login credentials:**
- Username: `admin`
- Password: `admin`

## Project Structure

```
travel-office/
├── src/
│   └── application/
│       ├── data/              # Data models (Trip, Hotel, HistoryTrip)
│       ├── service/           # Business logic and database services
│       │   └── database/      # Database connection and queries
│       ├── view/              # FXML files and controllers
│       ├── TravelOffice.java  # Main application class
│       └── TravelOfficeHelper.java
├── sqlScript.sql              # Initial database data
└── README.md
```

## Troubleshooting

### "Error: JavaFX runtime components are missing"
- Ensure VM arguments are correctly set in Run Configuration
- Verify JavaFX SDK path is correct

### "ClassNotFoundException: com.mysql.jdbc.Driver"
- Ensure MySQL Connector jar is added to the project's build path
- Check that the library is in Modulepath or Classpath

### Database Connection Failed
- Verify MySQL server is running
- Check database credentials in the source code
- Ensure MySQL Connector version is compatible

## License

This project was created for educational purposes as part of university coursework.

## Academic Context

**Course:** Java Programming  
**Institution:** [Your University Name]  
**Year:** 2020

---

*This project demonstrates object-oriented programming principles, GUI development with JavaFX, and database integration with MySQL.*