package com.cavalcanti.hotelmanager.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cavalcanti.hotelmanager.calculator.PriceCalculator;
import com.cavalcanti.hotelmanager.models.Guest;
import com.cavalcanti.hotelmanager.models.Stay;
import com.cavalcanti.hotelmanager.repository.GuestRepository;
import com.cavalcanti.hotelmanager.repository.StayRepository;

@RestController
@RequestMapping("/stays")
public class StayController {
	
	@Autowired
	StayRepository stayRepository;
	
	@Autowired
	GuestRepository guestRepository;
	
	@GetMapping
	public Iterable<Stay> getAllStays(){
		return stayRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Stay> getStay(@PathVariable Integer id){
		Optional<Stay> stay = stayRepository.findById(id);
		if(stay.isPresent()) {
			return ResponseEntity.ok(stay.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/currentGuests")
	public Iterable<Guest> getCurrentGuests(){
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
	
	@GetMapping("/formerGuests")
	public Iterable<Guest> getFormerGuests(){
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
	
	@PostMapping(value = "/checkIn", consumes = "application/json")
	public Stay checkIn(@Valid @RequestBody Stay stay) {	
		stay.setCheckOutDateTime(null);
		stay.setFinalValue(null);
		
		return stayRepository.save(stay);

	}
	
	@PutMapping("/checkOut")
	public ResponseEntity<Stay> checkOut(
			@RequestParam(name = "stayId", required = true) Integer stayId,
			@RequestParam(name = "checkOutDateTime", required = true) String checkOutDateTime
		){
		Optional<Stay> stay = stayRepository.findById(stayId);
		
		if(stay.isPresent()) {
			Stay checkedOut = stay.get();
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
			LocalDateTime checkOutDateTimeFormatted = LocalDateTime.parse(checkOutDateTime, formatter);
			
			if(checkOutDateTimeFormatted.isAfter(checkedOut.getCheckInDateTime())) {
				checkedOut.setCheckOutDateTime(checkOutDateTimeFormatted);
				checkedOut.setFinalValue(PriceCalculator.calculatePrice(checkedOut));
				stayRepository.save(checkedOut);
				return ResponseEntity.ok(checkedOut);
			}else {
				return ResponseEntity.badRequest().build();
			}
		}else {
			return ResponseEntity.notFound().build();
		}	
	}
	
	@DeleteMapping("/delete/{guestCpf}")
	public ResponseEntity<Stay> deleteStay(@PathVariable Integer id) {
		stayRepository.deleteById(id);
		return ResponseEntity.ok().build();

	}
}
