package com.cavalcanti.hotelmanager.pricing;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.cavalcanti.hotelmanager.models.Stay;
import com.cavalcanti.hotelmanager.pricing.constants.HotelPricingRuleConstants;

public class HotelPricingRule {
	
	public static int getNumberOfWeekendDays(List<DayOfWeek> days) {
		return Collections.frequency(days, DayOfWeek.SATURDAY)
				+ Collections.frequency(days, DayOfWeek.SUNDAY);
	}
	
	public static List<DayOfWeek> getDaysOfTheWeekSpentOnHotel(Stay stay){
		
		long stayDuration = 
			Duration.between(stay.getCheckInDateTime(), stay.getCheckOutTime()).toDays();
		
		List<DayOfWeek> daysSpentOnHotel = IntStream.iterate(0, i -> i + 1)
			.limit(stayDuration)
			.mapToObj(i -> stay.getCheckInDateTime().plusDays(i).getDayOfWeek())
			.collect(Collectors.toList());
		
		LocalTime checkOutTime = 
				LocalTime.of(stay.getCheckOutTime().getHour(), stay.getCheckOutTime().getMinute());
		
		if(checkOutTime.isAfter(LocalTime.of(HotelPricingRuleConstants.DAILY_LIMIT_HOUR
				, HotelPricingRuleConstants.DAILY_LIMIT_MINUTE))) {
			daysSpentOnHotel
				.add(daysSpentOnHotel.get(daysSpentOnHotel.size() - 1).plus(1));
		}
		
		return daysSpentOnHotel;
	}
	
	public static Double getDailyValueOnWeekend(Stay stay) {
		if(stay.getGarageNeeded()) {
			return (double) HotelPricingRuleConstants.DAILY_VALUE_ON_WEEKEND_WITH_GARAGE_FEE;
		}else {
			return (double) HotelPricingRuleConstants.DAILY_VALUE_ON_WEEKEND_WITHOUT_GARAGE_FEE;
		}
	}
	
	public static Double getDailyValue(Stay stay) {
		if(stay.getGarageNeeded()) {
			return (double) HotelPricingRuleConstants.DAILY_VALUE_WITH_GARAGE_FEE;
		}else {
			return (double) HotelPricingRuleConstants.DAILY_VALUE_WITHOUT_GARAGE_FEE;
		}
	}
}
