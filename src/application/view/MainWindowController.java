package application.view;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeSet;

import application.TravelOfficeHelper;
import application.data.Trip;
import application.service.BuyTripService;
import application.service.TripService;
import application.service.database.SqlQuerys;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainWindowController implements Initializable {
	private MainWindow mainWindow;
	private TripService tripService;
	private BuyTripService buyTripService;
	private List<Trip> trips;
	private short selectedTripIndex = 0;
	private boolean tripIsSelected;
	private long idUser;
	private Stage loginStage;
	private Stage historyStage;
	
	@FXML
    private MenuItem loginMenuItem;

    @FXML
    private MenuItem historyMenuItem;
    
    @FXML
    private MenuItem exitMenuItem;

    @FXML
    private ComboBox<String> fromCountryChoice;

    @FXML
    private ComboBox<String> toCountryChoice;
    
    @FXML
    private Spinner<Integer> seatsSpinner;

    @FXML
    private CheckBox insuranceCheckBox;

    @FXML
    private DatePicker fromDate;

    @FXML
    private DatePicker toDate;

    @FXML
    private Button buyTripButton;

    @FXML
    private ImageView tripImage;

    @FXML
    private Label fromCountryLabel;

    @FXML
    private Label toCountryLabel;

    @FXML
    private Label dateArrivalLabel;

    @FXML
    private Label dateDepartureLabel;

    @FXML
    private Label durationLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label freeSeatsLabel;
    
    @FXML
    private Label hotelNameLabel;

    @FXML
    private Label hotelCityLabel;

    @FXML
    private Label hotelRatingLabel;

    @FXML
    private Button previousTrip;

    @FXML
    private Button nextTrip;
    
    public MainWindowController() throws IOException {
		mainWindow = TravelOfficeHelper.getInstance().getMainWindow();
		tripService = new TripService();
		buyTripService = new BuyTripService();
	}

    @Override
	public void initialize(URL location, ResourceBundle resources) {
		mainWindow.setMainWindowController(this);
		getCountriesFromDatabase();
		setBehaviorOfComponents();
		fromDate.setValue(LocalDate.now());
		toDate.setValue(LocalDate.now().plusMonths(4));
		trips = tripService.getActiveTrips(fromCountryChoice.getValue(), toCountryChoice.getValue(), fromDate.getValue(), toDate.getValue());
		showTrip();
		seatsSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100));
	}
    
    @FXML
    public void login() throws IOException {
    	if (loginMenuItem.getText().equals("Zaloguj")) {
    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        	VBox loginWindow = fxmlLoader.load();
        	
        	Scene scene = new Scene(loginWindow);
        	loginStage = new Stage();
        	loginStage.setScene(scene);
        	loginStage.show();
    	} else {
    		mainWindow.getStage().setTitle(TravelOfficeHelper.getInstance().getBASE_TITLE() + "anonimowy u�ytkownik");
    		loginMenuItem.setText("Zaloguj");
    		historyMenuItem.setDisable(true);
    	}
    }
    
	@FXML
 	public void showNextTrip() {
 		selectedTripIndex++;
 		if (selectedTripIndex > trips.size() - 1) {
 			selectedTripIndex = 0;
 		}
 		showTrip();
 	}
 	
	@FXML
 	public void showPreviousTrip() {
 		selectedTripIndex--;
 		if (selectedTripIndex < 0) {
 			selectedTripIndex = (short) (trips.size() - 1);
 		}
 		
 		showTrip();
 	}
    
    @FXML
    public void showHistory() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("historyWindow.fxml"));
			VBox mainPane = loader.load();
			
			Scene scene = new Scene(mainPane, mainPane.getPrefWidth(), mainPane.getMaxHeight());
			historyStage = new Stage();
			historyStage.setScene(scene);
			historyStage.show();
			mainWindow.getBoughtTripsController().getHistoryTrips();
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
    }
    
    @FXML
    public void buyTrip() {
		if (loginMenuItem.getText().equals("Zaloguj")) {
			Alert loginErrorAlert = new Alert(AlertType.ERROR, "Przed zakupieniem wycieczki musisz si� zalogowa�.", ButtonType.OK);
			loginErrorAlert.setHeaderText(null);
			loginErrorAlert.show();
			return;
		}
		
		if (!tripIsSelected) {
			Alert loginErrorAlert = new Alert(AlertType.WARNING, "Brak dost�pnych ofert wycieczek. "
					+ System.lineSeparator()
					+ "Zmie� kryteria wyszukiwania i spr�buj ponownie.", ButtonType.OK);
			loginErrorAlert.setHeaderText(null);
			loginErrorAlert.show();
			return;
		}
		
		if (buyTripService.buyTrip(idUser, 
				trips.get(selectedTripIndex).getIdTrip(), 
				trips.get(selectedTripIndex).getFreeSeats(), 
				seatsSpinner.getValue().shortValue(), 
				insuranceCheckBox.isSelected() ? (short) 150 : (short) 0) > 0) {
			Alert tripBoughtAlert = new Alert(AlertType.INFORMATION, "Wycieczka zosta�a zakupiona", ButtonType.OK);
	    	tripBoughtAlert.setHeaderText(null);
	    	tripBoughtAlert.show();
		}
    	
    	trips = tripService.getAllTrips(fromCountryChoice.getValue(), toCountryChoice.getValue(), fromDate.getValue(), toDate.getValue());
    	showTrip();
    }
    
    @FXML
    public void close(ActionEvent event) {
    	mainWindow.close();
    }

	private void getCountriesFromDatabase() {
		try {
			Set<String> countriesSet = new TreeSet<>();
			
			TravelOfficeHelper travelOfficeHelper = TravelOfficeHelper.getInstance();
			
			ResultSet resultSet = travelOfficeHelper.getDatabase().executeQuery(SqlQuerys.getFromCountrySet());
			while (resultSet.next()) {
				countriesSet.add(resultSet.getString(1));
			}
			
			resultSet = TravelOfficeHelper.getInstance().getDatabase().executeQuery(SqlQuerys.getToCountrySet());
			while (resultSet.next()) {
				countriesSet.add(resultSet.getString(1));
			}
			
			countriesSet.stream().forEach(country -> {
				fromCountryChoice.getItems().add(country);
				toCountryChoice.getItems().add(country);
			});
			
			if (countriesSet.size() > 0) {
				fromCountryChoice.setValue(fromCountryChoice.getItems().get(0));
				toCountryChoice.setValue(toCountryChoice.getItems().get(0));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void setBehaviorOfComponents() {
		fromCountryChoice.setOnAction(action -> {
			trips = tripService.getActiveTrips(fromCountryChoice.getValue(), toCountryChoice.getValue(), fromDate.getValue(), toDate.getValue());
			selectedTripIndex = 0;
			showTrip();
		});
		
		toCountryChoice.setOnAction(action -> {
			trips = tripService.getActiveTrips(fromCountryChoice.getValue(), toCountryChoice.getValue(), fromDate.getValue(), toDate.getValue());
			selectedTripIndex = 0;
			showTrip();
		});
		
		fromDate.setOnAction(action -> {
			trips = tripService.getActiveTrips(fromCountryChoice.getValue(), toCountryChoice.getValue(), fromDate.getValue(), toDate.getValue());
			selectedTripIndex = 0;
			showTrip();
		});
		
		toDate.setOnAction(action -> {
			trips = tripService.getActiveTrips(fromCountryChoice.getValue(), toCountryChoice.getValue(), fromDate.getValue(), toDate.getValue());
			selectedTripIndex = 0;
			showTrip();
		});
	}
	
	private void showTrip() {
		if (trips.size() > 0) {
			Trip trip = trips.get(selectedTripIndex);
			tripImage.setImage(new Image(trip.getPhotoUrl()));
			fromCountryLabel.setText(trip.getFromCountry());
			toCountryLabel.setText(trip.getToCountry());
			dateArrivalLabel.setText(trip.getArrivalDateTime().toString().substring(0, 19));
			dateDepartureLabel.setText(trip.getDepartureDateTime().toString().substring(0, 19));
			durationLabel.setText(String.valueOf(trip.getDuration()));
			priceLabel.setText(trip.getPrice().divide(new BigDecimal(100)).setScale(2, RoundingMode.HALF_DOWN).toString() + " z�");
			freeSeatsLabel.setText(String.valueOf(trip.getFreeSeats()));
			hotelNameLabel.setText(trip.getHotel().getName());
			hotelCityLabel.setText(trip.getHotel().getCity());
			hotelRatingLabel.setText(String.valueOf(trip.getHotel().getRting()));
			seatsSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, trip.getFreeSeats()));
			tripIsSelected = true;
		} else {
			tripImage.setImage(null);
			fromCountryLabel.setText("");
			toCountryLabel.setText("");
			dateArrivalLabel.setText("");
			dateDepartureLabel.setText("");
			durationLabel.setText("");
			priceLabel.setText("");
			freeSeatsLabel.setText("");
			hotelNameLabel.setText("");
			hotelCityLabel.setText("");
			hotelRatingLabel.setText("");
			seatsSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100));
			tripIsSelected = false;
		}
	}

	public MenuItem getLoginMenuItem() {
		return loginMenuItem;
	}

	public MenuItem getHistoryMenuItem() {
		return historyMenuItem;
	}

	public MenuItem getExitMenuItem() {
		return exitMenuItem;
	}
	
	public Button getBuyTripButton() {
		return buyTripButton;
	}

	public ComboBox<String> getFromCountryChoice() {
		return fromCountryChoice;
	}

	public ComboBox<String> getToCountryChoice() {
		return toCountryChoice;
	}

	public boolean isTripIsSelected() {
		return tripIsSelected;
	}

	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

	public Stage getLoginStage() {
		return loginStage;
	}

	public Stage getHistoryStage() {
		return historyStage;
	}
}
