package com.expedia.codetest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.expedia.codetest.integration.client.WundergroundRestClient;
import com.expedia.codetest.view.ForecastInfo;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml")
public class WundergroundRestlClientTest {
	
	
	@Autowired WundergroundRestClient client;
	
	@Test
	public void invalidZipcode() {
		ForecastInfo forecastInfo = new ForecastInfo();
		forecastInfo.setZipcode(12);
		client.loadForecastInfo(forecastInfo);
		Assert.assertNotNull(forecastInfo.getErrorDescription());
	}
	
	@Test
	public void validZipcode() {
		ForecastInfo forecastInfo = new ForecastInfo();
		forecastInfo.setZipcode(12121);
		client.loadForecastInfo(forecastInfo);
		Assert.assertNotNull(forecastInfo.getCity());
		Assert.assertNotNull(forecastInfo.getState());
		Assert.assertNotNull(forecastInfo.getTemperature());
		Assert.assertTrue("Melrose".equals(forecastInfo.getCity()));
		Assert.assertTrue("NY".equals(forecastInfo.getState()));
		Assert.assertNull(forecastInfo.getErrorDescription());
		System.out.println(forecastInfo);
	}
}
