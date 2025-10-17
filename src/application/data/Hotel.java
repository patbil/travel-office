package application.data;

public class Hotel {
	private long idHotel;
	private String name;
	private short rting;
	private String city;
	
	public Hotel() {}
	
	public Hotel(long idHotel, String name, short rting, String city) {
		this.idHotel = idHotel;
		this.name = name;
		this.rting = rting;
		this.city = city;
	}
	
	public long getIdHotel() {
		return idHotel;
	}

	public void setIdHotel(long idHotel) {
		this.idHotel = idHotel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public short getRting() {
		return rting;
	}

	public void setRting(short rting) {
		this.rting = rting;
	}

	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
}
