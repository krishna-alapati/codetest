package com.expedia.codetest.integration.client;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.expedia.codetest.view.ForecastInfo;

/**
 * Rest client for the wunderground api
 * 
 * @author krishna.alapati
 * 
 */

public class WundergroundRestClient {
	
	/**endpoint url*/
	private String restUrl;
	
	@Autowired
	RestTemplate restTemplate;
	/**
	 * The client constructor with the endpoint.
	 * @param restUrl
	 */
	public WundergroundRestClient(String restUrl) {
		this.restUrl = restUrl;
	}

	/**
	 * Loads the forecast info by invoking the Wunderground API.
	 * @param forecastInfo
	 */
	public void loadForecastInfo(ForecastInfo forecastInfo) {
		Map<String, String> variables = new HashMap<String, String>();
		variables.put("zipcode", forecastInfo.getZipcode().toString());
		String responseJson = restTemplate.getForObject(restUrl, String.class,
				variables);
		Map<String, String> foreCastMap = parseJSON(responseJson);
		if(!foreCastMap.containsKey("error")) {
			forecastInfo.setCity(foreCastMap.get("city"));
			forecastInfo.setState(foreCastMap.get("state"));
			forecastInfo.setTemperature(Float.valueOf(foreCastMap.get("temp")));
		} else {
			forecastInfo.setErrorDescription(foreCastMap.get("error"));
		}
	}

	/**
	 * This method parse the wunderground json.
	 * @param responseJson
	 */
	private Map<String, String> parseJSON(String responseJson) {
		JSONParser parser = new JSONParser();
		Map<String, String> foreCastMap = new HashMap<String, String>();
		try {
			JSONObject json = (JSONObject) parser.parse(responseJson);
			JSONObject currObservation = ((JSONObject) json
					.get("current_observation"));
			if (currObservation != null) {
				JSONObject displayLocation = (JSONObject) currObservation
						.get("display_location");
				foreCastMap.put("city", (String) displayLocation.get("city"));
				foreCastMap.put("state", (String) displayLocation.get("state"));
				foreCastMap.put("temp",
						String.valueOf(currObservation.get("temp_f")));
			} else {
				//no current observation
				JSONObject errorJson = (JSONObject)((JSONObject)json.get("response")).get("error");
				foreCastMap.put("error", (String)errorJson.get("description"));
			}
		} catch (ParseException e) {
			foreCastMap.put("error", String.format("Error while parsing the json %s. %s", responseJson, e.getMessage()));
		}
		return foreCastMap;
	}
}
