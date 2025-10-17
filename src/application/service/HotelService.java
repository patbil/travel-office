package application.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.TravelOfficeHelper;
import application.data.Hotel;
import application.service.database.SqlQuerys;

public class HotelService {

	public List<Hotel> getHotels() {
		List<Hotel> hotels = new ArrayList<>();

		try {
			ResultSet resultSet = TravelOfficeHelper.getInstance().getDatabase().executeQuery(SqlQuerys.getHotels());

			while (resultSet.next()) {
				Hotel hotel = new Hotel(resultSet.getLong("id_hotel"), resultSet.getString("name"),
						resultSet.getShort("rating"), resultSet.getString("city"));
				hotels.add(hotel);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return hotels;
	}
}
