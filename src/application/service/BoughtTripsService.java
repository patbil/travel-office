package application.service;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.TravelOfficeHelper;
import application.data.HistoryTrip;
import application.data.Hotel;
import application.service.database.SqlQuerys;
import application.view.MainWindow;

public class BoughtTripsService {
	private MainWindow mainWindow;
	private HotelService hotelService;
	
	public BoughtTripsService() {
		hotelService = new HotelService();
		mainWindow = TravelOfficeHelper.getInstance().getMainWindow();
	}
	
	public List<HistoryTrip> getBoughtTripsFromCurrentUser() {
		long idUser = mainWindow.getMainWindowController().getIdUser();
		List<HistoryTrip> historyTrips = new ArrayList<>();
		List<Hotel> hotels = hotelService.getHotels();
		
		try {
			ResultSet resultSet = TravelOfficeHelper.getInstance().getDatabase().executeQuery(SqlQuerys.getHistoryTrips(idUser));
			
			while (resultSet.next()) {
				HistoryTrip trip = new HistoryTrip();
				trip.setIdTrip(resultSet.getLong("id_trip"));
				trip.setFromCountry(resultSet.getString("from_country"));
				trip.setToCountry(resultSet.getString("to_country"));
				trip.setArrivalDateTime(resultSet.getString("arrival_dateTime").substring(0, 19));
				trip.setDepartureDateTime(resultSet.getString("departure_dateTime").substring(0, 19));
				trip.setDuration(resultSet.getShort("duration"));
				trip.setPrice(new BigDecimal(resultSet.getString("price")));
				trip.setSeats(resultSet.getShort("seats"));
				trip.setActive(resultSet.getBoolean("active"));
				trip.setPhotoUrl(resultSet.getString("photo_url"));
				long hotelId = resultSet.getLong("id_hotel");
				trip.setHotel(hotels.stream().filter(hotel -> hotel.getIdHotel() == hotelId).findAny().get());
				trip.setInsurance(resultSet.getShort("insurance"));
				trip.setPurchaseDateTime(resultSet.getString("purchase_dateTime").substring(0, 19));
				historyTrips.add(trip);
			}
		} catch (SQLException e) {
			System.out.println("Application error: " + e.getMessage());
		}
		
		return historyTrips;
	}
}
