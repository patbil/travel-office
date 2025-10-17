package application.data;

import java.math.BigDecimal;

public class Trip {
	private long idTrip;
	private String fromCountry;
	private String toCountry;
	private String arrivalDateTime;
	private String departureDateTime;
	private short duration;
	private Hotel hotel;
	private BigDecimal price;
	private short freeSeats;
	private boolean active;
	private String photoUrl;
	
	public long getIdTrip() {
		return idTrip;
	}
	
	public void setIdTrip(long idTrip) {
		this.idTrip = idTrip;
	}
	
	public String getFromCountry() {
		return fromCountry;
	}
	
	public void setFromCountry(String fromCountry) {
		this.fromCountry = fromCountry;
	}
	
	public String getToCountry() {
		return toCountry;
	}
	
	public void setToCountry(String toCountry) {
		this.toCountry = toCountry;
	}
	
	public String getArrivalDateTime() {
		return arrivalDateTime;
	}
	
	public void setArrivalDateTime(String arrivalDateTime) {
		this.arrivalDateTime = arrivalDateTime;
	}
	
	public String getDepartureDateTime() {
		return departureDateTime;
	}
	
	public void setDepartureDateTime(String departureDateTime) {
		this.departureDateTime = departureDateTime;
	}
	
	public short getDuration() {
		return duration;
	}
	
	public void setDuration(short duration) {
		this.duration = duration;
	}
	
	public Hotel getHotel() {
		return hotel;
	}
	
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public short getFreeSeats() {
		return freeSeats;
	}
	
	public void setFreeSeats(short freeSeats) {
		this.freeSeats = freeSeats;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public String getPhotoUrl() {
		return photoUrl;
	}
	
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	
	@Override
	public String toString() {
		return "Trip [idTrip=" + idTrip + ", fromCountry=" + fromCountry + ", toCountry=" + toCountry
				+ ", arrivalDateTime=" + arrivalDateTime + ", departureDateTime=" + departureDateTime + ", duration="
				+ duration + ", hotel=" + hotel + ", price=" + price + ", freeSeats=" + freeSeats + ", active=" + active
				+ ", photoUrl=" + photoUrl + "]";
	}
	
	
}
