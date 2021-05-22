package com.cavalcanti.hotelmanager.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
import com.cavalcanti.hotelmanager.service.GuestService;

@RestController
@RequestMapping("/guests")
public class GuestController {
	
	GuestService guestService;
	
	@Autowired
	public GuestController(GuestService guestService){
		this.guestService = guestService;
	}
	
	@GetMapping
	public Iterable<Guest> getAllGuests(){
		return guestService.getAllGuests();
	}
	
	@GetMapping("/{guestCpf}")
	public ResponseEntity<Guest> getGuest(@PathVariable String guestCpf){
		Optional<Guest> guest = guestService.getGuestByCpf(guestCpf);
		if(guest.isPresent()) {
			return ResponseEntity.ok(guest.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/save")
	public Guest saveGuest(@Valid @RequestBody Guest guest) throws MethodArgumentNotValidException{
		return guestService.saveGuest(guest);
	}
	
	@DeleteMapping("/delete/{guestCpf}")
	public ResponseEntity<Guest> deleteGuest(@PathVariable String guestCpf) {
		guestService.deleteGuest(guestCpf);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/update")
	public ResponseEntity<Guest> updateGuest(
			@RequestParam(name = "cpf", required = true) String cpf,
			@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "phone", required = false) String phone
			
		){
		Guest guest = guestService.updateGuest(cpf, name, phone);
		
		if(guest != null) {
			return ResponseEntity.ok(guest);
		}else {
			return ResponseEntity.notFound().build();
		}	
	}
}
