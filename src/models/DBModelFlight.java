package models;

import java.sql.Date;

public class DBModelFlight {
	private int id;
	private DBModelAirport departingFrom;
	private DBModelAirport arrivingTo;
	private Date timeDeparting;
	private double price;
	
	public DBModelAirport getDepartingFrom() {
		return departingFrom;
	}
	public DBModelAirport getArrivingTo() {
		return arrivingTo;
	}
	public Date getTimeDeparting() {
		return timeDeparting;
	}
	public double getPrice() {
		return price;
	}
	public void setDepartingFrom(DBModelAirport departingFrom) {
		this.departingFrom = departingFrom;
	}
	public void setArrivingTo(DBModelAirport arrivingTo) {
		this.arrivingTo = arrivingTo;
	}
	public void setTimeDeparting(Date timeDeparting) {
		this.timeDeparting = timeDeparting;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
