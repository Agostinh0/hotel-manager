package com.cavalcanti.hotelmanager.calculator;

import java.time.DayOfWeek;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.cavalcanti.hotelmanager.models.Stay;

public class DayOfWeekRule {
	
	public int getNumberOfWeekendDays(List<DayOfWeek> days) {
		return Collections.frequency(days, DayOfWeek.SATURDAY)
				+ Collections.frequency(days, DayOfWeek.SUNDAY);
	}
	
	public List<DayOfWeek> getStayDaysOfTheWeek(Stay stay){
		
		long stayDuration = 
			Duration.between(stay.getCheckInDateTime(), stay.getCheckOutTime()).toDays();
		
		return IntStream.iterate(0, i -> i + 1)
			.limit(stayDuration)
			.mapToObj(i -> stay.getCheckInDateTime().plusDays(i).getDayOfWeek())
			.collect(Collectors.toList());
	}
}
