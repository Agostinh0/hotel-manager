package com.cavalcanti.hotelmanager.calculator;

import java.time.DayOfWeek;
import java.util.List;

import com.cavalcanti.hotelmanager.models.Stay;

public class PriceCalculator {
	
	public static Double calculatePrice(Stay stay) {
		
		DayOfWeekRule rule = new DayOfWeekRule();
		
		List<DayOfWeek> daysSpentOnHotel = rule.getStayDaysOfTheWeek(stay);
		int weekendDaysCount = rule.getNumberOfWeekendDays(daysSpentOnHotel);
		
		return (double) ((150 * weekendDaysCount) + (120 * (daysSpentOnHotel.size() - weekendDaysCount)));
	}

}
