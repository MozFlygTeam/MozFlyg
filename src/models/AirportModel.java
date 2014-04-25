package models;

public class AirportModel
{
	private int id;
	private String airportName;
	private String cityName;
	
	public int getId() {
		return id;
	}
	public String getAirportName() {
		return airportName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	//En kommentar
}
