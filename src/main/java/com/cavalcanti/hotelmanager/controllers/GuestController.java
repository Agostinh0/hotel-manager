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
import com.cavalcanti.hotelmanager.repository.GuestRepository;

@RestController
@RequestMapping("/guests")
public class GuestController {
	
	@Autowired
	GuestRepository guestRepository;
	
	@GetMapping
	public Iterable<Guest> getAllGuests(){
		return guestRepository.findAll();
	}
	
	@GetMapping("/{guestCpf}")
	public ResponseEntity<Guest> getGuest(@PathVariable String guestCpf){
		Optional<Guest> guest = guestRepository.findById(guestCpf);
		if(guest.isPresent()) {
			return ResponseEntity.ok(guest.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/save")
	public Guest saveGuest(@Valid @RequestBody Guest guest) throws MethodArgumentNotValidException{
		return guestRepository.save(guest);
	}
	
	@DeleteMapping("/delete/{guestCpf}")
	public ResponseEntity<Guest> deleteGuest(@PathVariable String guestCpf) {
		guestRepository.deleteById(guestCpf);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/update")
	public ResponseEntity<Guest> checkOut(
			@RequestParam(name = "cpf", required = true) String cpf,
			@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "phone", required = false) String phone
			
		){
		Optional<Guest> guest = guestRepository.findById(cpf);
		
		if(guest.isPresent()) {
			Guest updatedGuest = guest.get();
			
			updatedGuest.setName(name);
			updatedGuest.setPhone(phone);
			guestRepository.save(updatedGuest);
			return ResponseEntity.ok(updatedGuest);
		}else {
			return ResponseEntity.notFound().build();
		}	
	}
}
