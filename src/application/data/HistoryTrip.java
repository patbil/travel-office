package application.data;

public class HistoryTrip extends Trip {
	private short seats;
	private short insurance;
	private String purchaseDateTime;
	
	public short getSeats() {
		return seats;
	}

	public void setSeats(short seats) {
		this.seats = seats;
	}

	public short getInsurance() {
		return insurance;
	}

	public void setInsurance(short insurance) {
		this.insurance = insurance;
	}

	public String getPurchaseDateTime() {
		return purchaseDateTime;
	}

	public void setPurchaseDateTime(String purchaseDateTime) {
		this.purchaseDateTime = purchaseDateTime;
	}
}
