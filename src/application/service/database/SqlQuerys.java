package application.service.database;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class SqlQuerys {
	
	public static String getUserPasswordFromUsername(String username) {
		return "select id_user, password from user where user_name = '" + username + "';";
	}
	
	public static String getFromCountrySet() {
		return "select from_country from trip group by from_country;";
	}
	
	public static String getToCountrySet() {
		return "select to_country from trip group by to_country;";
	}
	
	public static String getActiveTripsBetweenDates(String fromCountry, String toCountry, LocalDate fromDate, LocalDate toDate) {
		return "select * from trip \r\n" + 
				"where from_country = '" + fromCountry + "' \r\n" + 
				"and to_country = '" + toCountry + "' \r\n" + 
				"and arrival_dateTime > '" + fromDate + "' \r\n" + 
				"and arrival_dateTime < '" + toDate + "' \r\n" + 
				"and active = 1;";
	}
	
	public static String getAllTrips() {
		return "select * from trip;";
	}
	
	public static String getHotels() {
		return "select * from hotel;";
	}
	
	public static String buyTrip(long idUser, long idTrip, short seats, short insurance, String purchaseDateTime) {
		return String.format("insert into bought_trip (id_user, id_trip, seats, insurance, purchase_dateTime) values(%d, %d, %d, %d, '%s');", idUser, idTrip, seats, insurance, purchaseDateTime);
	}
	
	public static String updateFreeSeats(long idTrip, short seats) {
		return String.format("update trip set free_seats = %d where id_trip = %d;", seats, idTrip);
	}
	
	
	public static String getHistoryTrips(long idUser) {
		return String.format("select * \r\n" + 
				"	from bought_trip bt\r\n" + 
				"		left join trip t on bt.id_trip = t.id_trip\r\n" + 
				"	where bt.id_user = %d;", idUser);
	}
}
