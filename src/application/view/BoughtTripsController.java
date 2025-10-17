package application.view;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.TravelOfficeHelper;
import application.data.HistoryTrip;
import application.service.BoughtTripsService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BoughtTripsController implements Initializable {
	private MainWindow mainWindow;
	private BoughtTripsService boughtTripsService;
	private List<HistoryTrip> historyTrips;
	private int selectedTripIndex;
	
	@FXML
    private Button closeButton;

    @FXML
    private ImageView tripImage;

    @FXML
    private Label purchaseDateTimeLabel;

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
    private Label seatsLabel;
    
    @FXML
    private Label insuranceLabel;

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
    
    public BoughtTripsController() {
		mainWindow = TravelOfficeHelper.getInstance().getMainWindow();
		boughtTripsService = new BoughtTripsService();
		historyTrips = new ArrayList<>();
	}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	mainWindow.setBoughtTripsController(this);
    }
    
    @FXML
    void close(ActionEvent event) {
    	mainWindow.getMainWindowController().getHistoryStage().close();
    }

    @FXML
    void showNextTrip(ActionEvent event) {
    	selectedTripIndex++;
 		if (selectedTripIndex > historyTrips.size() - 1) {
 			selectedTripIndex = 0;
 		}
 		showTrip();
    }

    @FXML
    void showPreviousTrip(ActionEvent event) {
    	selectedTripIndex--;
 		if (selectedTripIndex < 0) {
 			selectedTripIndex = (short) (historyTrips.size() - 1);
 		}
 		
 		showTrip();
    }
    
    public void getHistoryTrips() {
    	historyTrips = boughtTripsService.getBoughtTripsFromCurrentUser();
    	showTrip();
    }
    
 	private void showTrip() {
 		if (historyTrips.size() > 0) {
 			HistoryTrip trip = historyTrips.get(selectedTripIndex);
 			tripImage.setImage(new Image(trip.getPhotoUrl()));
 			fromCountryLabel.setText(trip.getFromCountry());
 			toCountryLabel.setText(trip.getToCountry());
 			dateArrivalLabel.setText(trip.getArrivalDateTime().toString());
 			dateDepartureLabel.setText(trip.getDepartureDateTime().toString());
 			durationLabel.setText(String.valueOf(trip.getDuration()));
 			priceLabel.setText(trip.getPrice().divide(new BigDecimal(100)).setScale(2, RoundingMode.HALF_DOWN).toString() + " z�");
 			seatsLabel.setText(String.valueOf(trip.getSeats()));
 			hotelNameLabel.setText(trip.getHotel().getName());
 			hotelCityLabel.setText(trip.getHotel().getCity());
 			hotelRatingLabel.setText(String.valueOf(trip.getHotel().getRting()));
 			insuranceLabel.setText(trip.getInsurance() > 0 ? "wykupione" : "brak");
 			purchaseDateTimeLabel.setText(trip.getPurchaseDateTime());
 		} else {
 			tripImage.setImage(null);
 			fromCountryLabel.setText("");
 			toCountryLabel.setText("");
 			dateArrivalLabel.setText("");
 			dateDepartureLabel.setText("");
 			durationLabel.setText("");
 			priceLabel.setText("");
 			seatsLabel.setText("");
 			hotelNameLabel.setText("");
 			hotelCityLabel.setText("");
 			hotelRatingLabel.setText("");
 		}
 	}
}
