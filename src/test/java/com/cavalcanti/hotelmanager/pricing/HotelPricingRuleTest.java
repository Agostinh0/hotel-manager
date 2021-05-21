package com.cavalcanti.hotelmanager.pricing;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cavalcanti.hotelmanager.models.Guest;
import com.cavalcanti.hotelmanager.models.Stay;

@SpringBootTest(classes = {HotelPricingRule.class})
@ExtendWith(SpringExtension.class)
public class HotelPricingRuleTest {
	
	@Test
	public void shouldReturnTheNumberOfWeekendDaysSpentOnHotel() {
		
		//Creating scenario
		Guest guest = new Guest("547.542.810-71", "Matheus", "987654321");
		Stay stay = 
				new Stay(1, 201, guest, LocalDateTime.of(LocalDate.of(2021, 05, 21), LocalTime.of(15, 00)), false);
		stay.setCheckOutTime(LocalDateTime.of(LocalDate.of(2021, 05, 25), LocalTime.of(15, 00)));
		
		List<DayOfWeek> daysSpent = HotelPricingRule.getDaysOfTheWeekSpentOnHotel(stay);
		
		//Action
		int weekendDaysCount = HotelPricingRule.getNumberOfWeekendDays(daysSpent);
		
		//Validation
		Assertions.assertEquals(2, weekendDaysCount);
	}
	
	@Test
	public void shouldReturnTheListOfDaysSpentOnHotel() {
		//Creating scenario
		Guest guest = new Guest("547.542.810-71", "Matheus", "987654321");
		Stay stay = 
				new Stay(1, 201, guest, LocalDateTime.of(LocalDate.of(2021, 05, 21), LocalTime.of(15, 00)), false);
		stay.setCheckOutTime(LocalDateTime.of(LocalDate.of(2021, 05, 25), LocalTime.of(15, 00)));
		
		//Action
		List<DayOfWeek> daysSpent = HotelPricingRule.getDaysOfTheWeekSpentOnHotel(stay);
		
		//Validation
		Assertions.assertNotNull(daysSpent);
	}
	
	@Test
	public void shouldAddAnExtraDailyValueAfterSixteenAndThirtyPM() {
		//Creating scenario
		Guest guest = new Guest("547.542.810-71", "Matheus", "987654321");
		Stay stay = 
				new Stay(1, 201, guest, LocalDateTime.of(LocalDate.of(2021, 05, 21), LocalTime.of(15, 00)), false);
		stay.setCheckOutTime(LocalDateTime.of(LocalDate.of(2021, 05, 25), LocalTime.of(16, 31)));
		
		//Action
		List<DayOfWeek> daysSpent = HotelPricingRule.getDaysOfTheWeekSpentOnHotel(stay);
		
		//Validation
		Assertions.assertEquals(5, daysSpent.size());
	}
	
	@Test
	public void shouldReturn170AsDailyValueWithGarageFeeIncluded() {
		//Creating scenario
		Guest guest = new Guest("547.542.810-71", "Matheus", "987654321");
		Stay stay = 
				new Stay(1, 201, guest, LocalDateTime.of(LocalDate.of(2021, 05, 21), LocalTime.of(15, 00)), true);
		
		//Action
		Double value = HotelPricingRule.getDailyValueOnWeekend(stay);
		
		//Validation
		Assertions.assertEquals(170, value);
	}
	
	@Test
	public void shouldReturn150AsDailyValueWithoutGarageFee() {
		//Creating scenario
		Guest guest = new Guest("547.542.810-71", "Matheus", "987654321");
		Stay stay = 
				new Stay(1, 201, guest, LocalDateTime.of(LocalDate.of(2021, 05, 21), LocalTime.of(15, 00)), false);
		
		//Action
		Double value = HotelPricingRule.getDailyValueOnWeekend(stay);
		
		//Validation
		Assertions.assertEquals(150, value);
	}
	
	@Test
	public void shouldReturn135AsDailyValueWithGarageFeeIncluded() {
		//Creating scenario
		Guest guest = new Guest("547.542.810-71", "Matheus", "987654321");
		Stay stay = 
				new Stay(1, 201, guest, LocalDateTime.of(LocalDate.of(2021, 05, 21), LocalTime.of(15, 00)), true);
		
		//Action
		Double value = HotelPricingRule.getDailyValue(stay);
		
		//Validation
		Assertions.assertEquals(135, value);
	}
	
	@Test
	public void shouldReturn120AsDailyValueWithoutGarageFee() {
		//Creating scenario
		Guest guest = new Guest("547.542.810-71", "Matheus", "987654321");
		Stay stay = 
				new Stay(1, 201, guest, LocalDateTime.of(LocalDate.of(2021, 05, 21), LocalTime.of(15, 00)), false);
		
		//Action
		Double value = HotelPricingRule.getDailyValue(stay);
		
		//Validation
		Assertions.assertEquals(120, value);
	}
	
}
