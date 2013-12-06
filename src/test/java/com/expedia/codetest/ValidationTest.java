package com.expedia.codetest;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import org.junit.Assert;
import org.junit.Test;

import com.expedia.codetest.view.ForecastInfo;

public class ValidationTest {

	@Test
	public void emptyZipcode() {
		ForecastInfo info = new ForecastInfo();
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Set<ConstraintViolation<ForecastInfo>> result = factory.getValidator().validate(info);
		Assert.assertTrue(result.size() ==1);
		System.out.println(result);
	}
	
	@Test
	public void validZipcode() {
		ForecastInfo info = new ForecastInfo();
		info.setZipcode(12345);
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Set<ConstraintViolation<ForecastInfo>> result = factory.getValidator().validate(info);
		Assert.assertTrue(result.size() ==0);
		System.out.println(result);
	}
	
	

}
