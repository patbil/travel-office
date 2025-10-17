package application.service.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.TravelOfficeHelper;

public class Database {
	private DatabaseConnection databaseConnection;
	private Connection connection;
	private Statement statement;
	
	private boolean connected;
	
	public Database() {
		databaseConnection = new DatabaseConnection();
		TravelOfficeHelper.getInstance().setDatabase(this);
	}
	
	public void connectToDatabase() {
		connected = databaseConnection.connect();
    }
	
	public void createDatabase() {
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            statement.executeUpdate("CREATE DATABASE TravelOffice");
        } catch (SQLException sqle) {
            System.err.println("SQL exception: " + sqle.getMessage());
        }
    }
	
	public void createTables() {
		try {
            statement.executeUpdate("USE TravelOffice");
            createHotelsTable();
            createTripsTable();
            createUsersTable();
            createBoughtTripsTable();
        } catch (SQLException ex) {
            System.out.println(String.format("Error during creating tables: %s", ex.getMessage()));
        }
	}
	
	public void disconnectFromDatabase() {
		connected = !databaseConnection.disconnect();
	}

	private void createTripsTable() throws SQLException {
		statement.executeUpdate("CREATE TABLE IF NOT EXISTS Trip"
		        + "(id_trip BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY, "
		        + "from_country VARCHAR(50), "
		        + "to_country VARCHAR(50), "
		        + "arrival_dateTime DATETIME(0), "
		        + "departure_dateTime DATETIME(0), "
		        + "duration TINYINT, "
		        + "price INT, "
		        + "free_seats TINYINT, "
		        + "active TINYINT, "
		        + "photo_url TEXT, "
		        + "id_hotel BIGINT UNSIGNED, "
		        + "FOREIGN KEY (id_hotel) REFERENCES Hotel(id_hotel)"
		        + ") CHARACTER SET = cp1250");
	}
	
	private void createUsersTable() throws SQLException {
		statement.executeUpdate("CREATE TABLE IF NOT EXISTS User"
		        + "(id_user BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY, "
		        + "user_name VARCHAR(50) UNIQUE, "
		        + "password TEXT"
		        + ") CHARACTER SET = cp1250");
	}
	
	private void createBoughtTripsTable() throws SQLException {
		statement.executeUpdate("CREATE TABLE IF NOT EXISTS Bought_trip"
		        + "(id_bought_trips BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY, "
		        + "id_user BIGINT UNSIGNED, "
		        + "id_trip BIGINT UNSIGNED, "
		        + "seats TINYINT UNSIGNED, "
		        + "insurance TINYINT UNSIGNED, "
		        + "purchase_dateTime DATETIME(0), "
		        + "FOREIGN KEY (id_user) REFERENCES User(id_user), "		        
		        + "FOREIGN KEY (id_trip) REFERENCES Trip(id_trip)"		        
		        + ") CHARACTER SET = cp1250");
	}
	
	private void createHotelsTable() throws SQLException {
		statement.executeUpdate("CREATE TABLE IF NOT EXISTS Hotel"
		        + "(id_hotel BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY, "
		        + "name VARCHAR(50), "
		        + "city VARCHAR(50), "
		        + "rating TINYINT"
		        + ") CHARACTER SET = cp1250");
	}
	
	public ResultSet executeQuery(String query) throws SQLException {
		return statement.executeQuery(query);
	}
	
	public int executeUpdate(String query) throws SQLException {
		return statement.executeUpdate(query);
	}
	
	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public Statement getStatement() {
		return statement;
	}

	public void setStatement(Statement statement) {
		this.statement = statement;
	}

	public boolean isConnected() {
		return connected;
	}

	public void setConnected(boolean connected) {
		this.connected = connected;
	}
}
