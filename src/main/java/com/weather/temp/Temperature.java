package com.weather.temp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="temp")
public class Temperature {

	@Id
	private String date;
	
	Double temp_min,temp_max,temp;

	public Temperature() {	}

	public Temperature(String date, Double temp_min, Double temp_max, Double temp) {
		super();
		this.date = date;
		this.temp_min = temp_min;
		this.temp_max = temp_max;
		this.temp = temp;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Double getTemp_min() {
		return temp_min;
	}

	public void setTemp_min(Double temp_min) {
		this.temp_min = temp_min;
	}

	public Double getTemp_max() {
		return temp_max;
	}

	public void setTemp_max(Double temp_max) {
		this.temp_max = temp_max;
	}

	public Double getTemp() {
		return temp;
	}

	public void setTemp(Double temp) {
		this.temp = temp;
	}
}
