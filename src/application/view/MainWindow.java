package application.view;

import java.io.IOException;

import javafx.stage.Stage;

public interface MainWindow {
	void create(Stage stage) throws IOException;
	void close();
	Stage getStage();
	void setMainWindowController(MainWindowController mainWindowController);
	MainWindowController getMainWindowController();
	void setBoughtTripsController(BoughtTripsController boughtTripsController);
	BoughtTripsController getBoughtTripsController();
}
