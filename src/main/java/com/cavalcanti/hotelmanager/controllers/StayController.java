package com.cavalcanti.hotelmanager.controllers;

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

import com.cavalcanti.hotelmanager.models.Guest;
import com.cavalcanti.hotelmanager.models.Stay;
import com.cavalcanti.hotelmanager.service.StayService;

@RestController
@RequestMapping("/stays")
public class StayController {
	
	StayService stayService;
	
	@Autowired
	public StayController(StayService stayService) {
		this.stayService = stayService;
	}
	
	@GetMapping
	public Iterable<Stay> getAllStays(){
		return stayService.getAllStays();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Stay> getStay(@PathVariable Integer id){
		Optional<Stay> stay = stayService.getStayById(id);
		if(stay.isPresent()) {
			return ResponseEntity.ok(stay.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/currentGuests")
	public Iterable<Guest> getCurrentGuests(){
		return stayService.getCurrentGuests();
	}
	
	@GetMapping("/formerGuests")
	public Iterable<Guest> getFormerGuests(){
		return stayService.getFormerGuests();
	}
	
	@PostMapping(value = "/checkIn", consumes = "application/json")
	public Stay checkIn(@Valid @RequestBody Stay stay) {	
		return stayService.checkIn(stay);

	}
	
	@PutMapping("/checkOut")
	public ResponseEntity<Stay> checkOut(
			@RequestParam(name = "stayId", required = true) Integer stayId,
			@RequestParam(name = "checkOutDateTime", required = true) String checkOutDateTime
		){
		
		Stay stay = stayService.checkOut(stayId, checkOutDateTime);
		
		if(stay != null) {
			return ResponseEntity.ok(stay);
		}else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@DeleteMapping("/delete/{guestCpf}")
	public ResponseEntity<Stay> deleteStay(@PathVariable Integer id) {
		stayService.deleteStay(id);
		return ResponseEntity.ok().build();

	}
}
