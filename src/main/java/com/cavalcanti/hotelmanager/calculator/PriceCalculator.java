package com.cavalcanti.hotelmanager.calculator;

import java.time.DayOfWeek;
import java.util.List;

import com.cavalcanti.hotelmanager.models.Stay;
import com.cavalcanti.hotelmanager.pricing.HotelPricingRule;

public class PriceCalculator {
	
	public static Double calculatePrice(Stay stay) {
		
		List<DayOfWeek> daysSpentOnHotel = HotelPricingRule.getDaysOfTheWeekSpentOnHotel(stay);
		int numberOfWeekendDays = HotelPricingRule.getNumberOfWeekendDays(daysSpentOnHotel);
		
		return (double) ((HotelPricingRule.getDailyValueOnWeekend(stay) * numberOfWeekendDays) 
				+ (HotelPricingRule.getDailyValue(stay) * (daysSpentOnHotel.size() - numberOfWeekendDays)));
	}

}
