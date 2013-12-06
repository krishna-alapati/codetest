package com.expedia.codetest.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.expedia.codetest.config.AppConfig;
import com.expedia.codetest.integration.client.WundergroundRestClient;
import com.expedia.codetest.view.ForecastInfo;

/**
 * Handles requests for the application.
 * @author krishna.alapati
 */
@Controller
public class WeatherForecastController {
	static{
		AnnotationConfigApplicationContext ctx =
			     new AnnotationConfigApplicationContext();
			 ctx.register(AppConfig.class);
			 ctx.refresh();
	}
	private static final Logger logger = LoggerFactory.getLogger(WeatherForecastController.class);
	
	@Autowired
	private WundergroundRestClient restClient;
	
	/**
	 * Selects the forecast view and render the forecast information when the zipcode is posted.
	 */
	@RequestMapping(value = "/forecast", method = {RequestMethod.POST})
	public String forecast(@Valid @ModelAttribute("forecastInfo") ForecastInfo forecastInfo, BindingResult result) {
		logger.info("Zipcode entered is {}", forecastInfo);
		if(result.hasErrors()) {
			return "forecast";
		}
		if(forecastInfo.getZipcode()!=null) {
			restClient.loadForecastInfo(forecastInfo);
		}
		
		return "forecast";
	}
	
	@RequestMapping(value = "/forecast", method = {RequestMethod.GET})
	public String forecast(Model model){
		model.addAttribute("forecastInfo", new ForecastInfo());
		return "forecast";
	}
	
}
