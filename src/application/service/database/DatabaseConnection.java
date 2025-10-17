package application.service.database;

import java.sql.DriverManager;
import java.sql.SQLException;

import application.TravelOfficeHelper;

public class DatabaseConnection {
	private final String SERVER_IP = "localhost";
	private final String SERVER_PORT = "3306";
	private final String LOGIN = "root";
	private final String PASSWORD = "root";
	
	private TravelOfficeHelper travelOfficeHelper = TravelOfficeHelper.getInstance();
	
	public boolean connect() {
		try {
            Class.forName("com.mysql.jdbc.Driver");
            travelOfficeHelper.getDatabase().setConnection(DriverManager.getConnection("jdbc:mysql://" +
            		SERVER_IP + ":" + SERVER_PORT,
                    LOGIN, PASSWORD));
        } catch (ClassNotFoundException cnfe) {
        	System.out.println("ClassNotFound exception: " + cnfe.getMessage());
        	return false;
        } catch (SQLException e) {
            System.out.println("SQL exception: " + e.getMessage());
            return false;
        }

        return true;
    }
	
	public boolean disconnect() {
		if (travelOfficeHelper.getDatabase().isConnected()) {
			try {
				travelOfficeHelper.getDatabase().getConnection().close();
				travelOfficeHelper.getDatabase().getStatement().close();
				
				travelOfficeHelper.getDatabase().setConnection(null);
				travelOfficeHelper.getDatabase().setStatement(null);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				return false;
			}
		}
		
		return true;
	}
}
