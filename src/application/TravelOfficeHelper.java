package application;

import java.io.IOException;

import application.service.database.Database;
import application.view.MainWindowImpl;
import javafx.stage.Stage;

public class TravelOfficeHelper {
	private final String BASE_TITLE = "Travel Office "; 
	
	private static TravelOfficeHelper instance = null;
	private Database database;
	private MainWindowImpl mainWindow;
	
	private TravelOfficeHelper() {
		mainWindow = new MainWindowImpl();
	}
	
	public static TravelOfficeHelper getInstance() {
		if (instance == null) {
			instance = new TravelOfficeHelper();
		}
		
		return instance;
	}

	public void init(Stage stage) {
		try {
			stage.setTitle(BASE_TITLE + "anonymous user");
			new Database();
			database.connectToDatabase();
			database.createDatabase();
			database.createTables();
			mainWindow.create(stage);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Database getDatabase() {
		return database;
	}

	public void setDatabase(Database database) {
		this.database = database;
	}

	public MainWindowImpl getMainWindow() {
		return mainWindow;
	}

	public void setMainWindow(MainWindowImpl mainWindow) {
		this.mainWindow = mainWindow;
	}

	public String getBASE_TITLE() {
		return BASE_TITLE;
	}
}
