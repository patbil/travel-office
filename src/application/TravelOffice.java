package application;
	
import javafx.application.Application;
import javafx.stage.Stage;


public class TravelOffice extends Application {		
	@Override
	public void start(Stage primaryStage) throws Exception {
		TravelOfficeHelper.getInstance().init(primaryStage);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void stop() {
		TravelOfficeHelper.getInstance().getDatabase().disconnectFromDatabase();
	}
}
