package com.cavalcanti.hotelmanager.service;

import java.util.Optional;

import com.cavalcanti.hotelmanager.models.Guest;
import com.cavalcanti.hotelmanager.models.Stay;

public interface StayService {
	
	Optional<Stay> getStayById(int id);
	
	Iterable<Stay> getAllStays();
	
	Iterable<Guest> getCurrentGuests();
	
	Iterable<Guest> getFormerGuests();
	
	Stay checkIn(Stay stay);
	
	Stay checkOut(int stayId, String checkOutDateTime);
	
	void deleteStay(int id);
}
