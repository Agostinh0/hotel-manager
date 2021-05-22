package com.cavalcanti.hotelmanager.calculator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cavalcanti.hotelmanager.models.Guest;
import com.cavalcanti.hotelmanager.models.Stay;

@SpringBootTest(classes = {PriceCalculator.class})
@ExtendWith(SpringExtension.class)
public class PriceCalculatorTest {
	
	@Test
	public void shouldCalculateFinalValueByGivenDates() {
		//Creating scenario
		Guest guest = new Guest("547.542.810-71", "Matheus", "987654321");
		Stay stay = 
				new Stay(1, 201, guest, LocalDateTime.of(LocalDate.of(2021, 05, 21), LocalTime.of(15, 00)), false);
		stay.setCheckOutDateTime(LocalDateTime.of(LocalDate.of(2021, 05, 25), LocalTime.of(15, 00)));
		
		//Action
		Double finalValue = PriceCalculator.calculatePrice(stay);
		
		//Validation
		Assertions.assertEquals(540, finalValue);
		
	}
	
	@Test
	public void shouldChargeTheDailyValueEvenIfGuestStaysLessThanOneDay() {
		//Creating scenario
		Guest guest = new Guest("547.542.810-71", "Matheus", "987654321");
		Stay stay = 
				new Stay(1, 201, guest, LocalDateTime.of(LocalDate.of(2021, 05, 21), LocalTime.of(12, 00)), false);
		stay.setCheckOutDateTime(LocalDateTime.of(LocalDate.of(2021, 05, 21), LocalTime.of(16, 00)));
		
		//Action
		Double finalValue = PriceCalculator.calculatePrice(stay);
		
		//Validation
		Assertions.assertEquals(120, finalValue);
	}
	
	@Test
	public void shouldChargeTheDailyValuePlusExtraEvenIfGuestStaysLessThanOneDayAndLeavesAfterCheckOutDeadline() {
		//Creating scenario
		Guest guest = new Guest("547.542.810-71", "Matheus", "987654321");
		Stay stay = 
				new Stay(1, 201, guest, LocalDateTime.of(LocalDate.of(2021, 05, 21), LocalTime.of(12, 00)), false);
		stay.setCheckOutDateTime(LocalDateTime.of(LocalDate.of(2021, 05, 21), LocalTime.of(16, 40)));
		
		//Action
		Double finalValue = PriceCalculator.calculatePrice(stay);
		
		//Validation
		Assertions.assertEquals(270, finalValue);
	}
	
	@Test
	public void shouldChargeWithAnExtraDailyFeeForPostSixteenThirtyPMCheckOut() {
		//Creating scenario
		Guest guest = new Guest("547.542.810-71", "Matheus", "987654321");
		Stay stay = 
				new Stay(1, 201, guest, LocalDateTime.of(LocalDate.of(2021, 05, 21), LocalTime.of(15, 00)), false);
		stay.setCheckOutDateTime(LocalDateTime.of(LocalDate.of(2021, 05, 25), LocalTime.of(16, 31)));
		
		//Action
		Double finalValue = PriceCalculator.calculatePrice(stay);
		
		//Validation
		Assertions.assertEquals(660, finalValue);
		
}
}
