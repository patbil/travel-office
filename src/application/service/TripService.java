package application.service;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import application.TravelOfficeHelper;
import application.data.Hotel;
import application.data.Trip;
import application.service.database.SqlQuerys;

public class TripService {
	private HotelService hotelService;

	public TripService() {
		hotelService = new HotelService();
	}

	public List<Trip> getAllTrips(String fromCountry, String toCountry, LocalDate fromDate, LocalDate toDate) {
		List<Trip> trips = new ArrayList<>();

		try {
			String sqlQuery = SqlQuerys.getActiveTripsBetweenDates(fromCountry, toCountry, fromDate, toDate);

			trips = getTrips(sqlQuery);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return trips;
	}

	public List<Trip> getActiveTrips(String fromCountry, String toCountry, LocalDate fromDate, LocalDate toDate) {
		List<Trip> trips = new ArrayList<>();

		try {
			String sqlQuery = SqlQuerys.getActiveTripsBetweenDates(fromCountry, toCountry, fromDate, toDate);

			trips = getTrips(sqlQuery);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("Finished getting trips.");
		return trips;
	}

	private List<Trip> getTrips(String sqlQuery) throws SQLException {
		List<Trip> trips = new ArrayList<Trip>();
		List<Hotel> hotels = hotelService.getHotels();

		ResultSet resultSet = TravelOfficeHelper.getInstance().getDatabase().executeQuery(sqlQuery);

		while (resultSet.next()) {
			Trip trip = new Trip();
			trip.setIdTrip(resultSet.getLong("id_trip"));
			trip.setFromCountry(resultSet.getString("from_country"));
			trip.setToCountry(resultSet.getString("to_country"));
			trip.setArrivalDateTime(resultSet.getString("arrival_dateTime"));
			trip.setDepartureDateTime(resultSet.getString("departure_dateTime"));
			trip.setDuration(resultSet.getShort("duration"));
			trip.setPrice(new BigDecimal(resultSet.getString("price")));
			trip.setFreeSeats(resultSet.getShort("free_seats"));
			trip.setActive(resultSet.getBoolean("active"));
			trip.setPhotoUrl(resultSet.getString("photo_url"));
			long hotelId = resultSet.getLong("id_hotel");
			trip.setHotel(hotels.stream().filter(hotel -> hotel.getIdHotel() == hotelId).findAny().get());
			trips.add(trip);
		}
		return trips;
	}
}
