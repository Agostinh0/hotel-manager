package com.cavalcanti.hotelmanager.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cavalcanti.hotelmanager.calculator.PriceCalculator;
import com.cavalcanti.hotelmanager.models.Guest;
import com.cavalcanti.hotelmanager.models.Stay;
import com.cavalcanti.hotelmanager.repository.GuestRepository;
import com.cavalcanti.hotelmanager.repository.StayRepository;
import com.cavalcanti.hotelmanager.service.StayService;

@Service
public class StayServiceImpl implements StayService{
	
	@Autowired
	StayRepository stayRepository;
	
	@Autowired
	GuestRepository guestRepository;
	
	@Override
	public Optional<Stay> getStayById(int id) {
		return stayRepository.findById(id);
	}

	@Override
	public Iterable<Stay> getAllStays() {
		return stayRepository.findAll();
	}

	@Override
	public Iterable<Guest> getCurrentGuests() {
		List<String> currentGuestsCpfs = stayRepository.getCurrentGuestsCpfs();
		List<Guest> currentGuests = new ArrayList<>();
		
		for(String cpf : currentGuestsCpfs) {
			Optional<Guest> guest = guestRepository.findById(cpf);
			if(guest.isPresent()) {
				currentGuests.add(guest.get());
			}
		}
		
		return currentGuests;
	}

	@Override
	public Iterable<Guest> getFormerGuests() {
		List<String> formerGuestsCpfs = stayRepository.getFormerGuestsCpfs();
		List<Guest> formerGuests = new ArrayList<>();
		
		for(String cpf : formerGuestsCpfs) {
			Optional<Guest> guest = guestRepository.findById(cpf);
			if(guest.isPresent()) {
				formerGuests.add(guest.get());
			}
		}
		
		return formerGuests;
	}

	@Override
	public Stay checkIn(Stay stay) {
		stay.setCheckOutDateTime(null);
		stay.setFinalValue(null);
		
		return stayRepository.save(stay);
	}

	@Override
	public Stay checkOut(int stayId, String checkOutDateTime) {
		Optional<Stay> stay = stayRepository.findById(stayId);
		
		if(stay.isPresent()) {
			Stay checkedOut = stay.get();
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
			LocalDateTime checkOutDateTimeFormatted = LocalDateTime.parse(checkOutDateTime, formatter);
			
			if(checkOutDateTimeFormatted.isAfter(checkedOut.getCheckInDateTime())) {
				checkedOut.setCheckOutDateTime(checkOutDateTimeFormatted);
				checkedOut.setFinalValue(PriceCalculator.calculatePrice(checkedOut));
				stayRepository.save(checkedOut);
				return checkedOut;
			}else {
				return null;
			}
		}else {
			return null;
		}
	}

	@Override
	public void deleteStay(int id) {
		stayRepository.deleteById(id);	
	}
}
