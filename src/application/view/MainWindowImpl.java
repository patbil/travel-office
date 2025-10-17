package application.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainWindowImpl implements MainWindow {
	private final int PREFERED_WIDTH = 800;
	private final int PREFERED_HEIGHT = 400;
	
	private Stage stage;
	private MainWindowController mainWindowController;
	private BoughtTripsController boughtTripsController;

	public void create(Stage stage) throws IOException {
		this.stage = stage;
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("mainWindow.fxml"));
		VBox mainPane = loader.load();
		
		Scene scene = new Scene(mainPane, mainPane.getPrefWidth(), mainPane.getMaxHeight());
		this.stage.setScene(scene);
		this.stage.show();
	}
	
	public void close() {
		stage.close();
	}
	
	public MainWindowController getMainWindowController() {
		return mainWindowController;
	}

	public void setMainWindowController(MainWindowController mainWindowController) {
		this.mainWindowController = mainWindowController;
	}

	public BoughtTripsController getBoughtTripsController() {
		return boughtTripsController;
	}

	public void setBoughtTripsController(BoughtTripsController boughtTripsController) {
		this.boughtTripsController = boughtTripsController;
	}

	public Stage getStage() {
		return stage;
	}
}
