package com.cavalcanti.hotelmanager.service;

import java.util.Optional;

import com.cavalcanti.hotelmanager.dtos.GuestDTO;
import com.cavalcanti.hotelmanager.dtos.StayDTO;
import com.cavalcanti.hotelmanager.models.Stay;

public interface StayService {
	
	Optional<StayDTO> getStayById(int id);
	
	Iterable<StayDTO> getAllStays();
	
	Iterable<GuestDTO> getCurrentGuests();
	
	Iterable<GuestDTO> getFormerGuests();
	
	Stay checkIn(Stay stay);
	
	StayDTO checkOut(int stayId, String checkOutDateTime);
	
	void deleteStay(int id);
}
