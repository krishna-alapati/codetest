package com.expedia.codetest.view;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * This is the simple dto. Also acts as a Form bean. 
 * @author krishna.alapati
 *
 */
public class ForecastInfo {
	@NotNull
	@Min(10000) @Max(99999)
	private Integer zipcode;

	private String city;
	private String state;
	private Float temperature;
	private String errorDescription;

	public Integer getZipcode() {
		return zipcode;
	}

	public void setZipcode(Integer zipcode) {
		this.zipcode = zipcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Float getTemperature() {
		return temperature;
	}

	public void setTemperature(Float temperature) {
		this.temperature = temperature;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
	
	public String toString(){
		return new StringBuilder().append("[")
				.append("zipcode:").append(this.zipcode)
				.append(", City:").append(this.city)
				.append(", State:").append(this.state)
				.append(", Temperature:").append(this.temperature)
				.append("]").toString();
	}
}
