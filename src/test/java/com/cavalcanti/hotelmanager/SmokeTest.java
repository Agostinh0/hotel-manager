package com.cavalcanti.hotelmanager;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cavalcanti.hotelmanager.controllers.GuestController;
import com.cavalcanti.hotelmanager.controllers.StayController;

@SpringBootTest
public class SmokeTest {
	
	@Autowired
	private GuestController guestController;
	
	@Autowired
	private StayController stayController;
	
	@Test
	void contextLoads() {
		Assertions.assertThat(guestController).isNotNull();
		Assertions.assertThat(stayController).isNotNull();
	}
}
