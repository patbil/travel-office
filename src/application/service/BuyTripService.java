package application.service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import application.TravelOfficeHelper;
import application.service.database.SqlQuerys;

public class BuyTripService {
	private TravelOfficeHelper travelOfficeHelper;

	public BuyTripService() {
		travelOfficeHelper = TravelOfficeHelper.getInstance();
	}

	public int buyTrip(long idUser, long idTrip, short freeSeats, short seats, short insurance) {
		try {
			DateTimeFormatter purchaseFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			travelOfficeHelper.getDatabase().executeUpdate(
					SqlQuerys.buyTrip(idUser, idTrip, seats, insurance, LocalDateTime.now().format(purchaseFormat)));
			travelOfficeHelper.getDatabase()
					.executeUpdate(SqlQuerys.updateFreeSeats(idTrip, (short) (freeSeats - seats)));
			return 1;
		} catch (SQLException e) {
			System.out.println("B��d po��czenia z baz� danych: " + e.getMessage());
			return 0;
		}
	}
}
