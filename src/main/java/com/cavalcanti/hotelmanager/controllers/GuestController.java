package com.cavalcanti.hotelmanager.controllers;

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

import com.cavalcanti.hotelmanager.dtos.GuestDTO;
import com.cavalcanti.hotelmanager.dtos.mappers.GuestDTOMapper;
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
	public Iterable<GuestDTO> getAllGuests(){
		return guestService.getAllGuests();
	}
	
	@GetMapping("/cpf/{guestCpf}")
	public ResponseEntity<GuestDTO> getGuestByCpf(@PathVariable String guestCpf){
		GuestDTO guest = guestService.getGuestByCpf(guestCpf);
		return ResponseEntity.ok(guest);
	}
	
	@GetMapping("/phone/{guestPhone}")
	public ResponseEntity<GuestDTO> getGuestByPhone(@PathVariable String guestPhone){
		GuestDTO guest = guestService.getGuestByPhone(guestPhone);
		return ResponseEntity.ok(guest);
	}
	
	@GetMapping("/name/{guestName}")
	public ResponseEntity<GuestDTO> getGuestByName(@PathVariable String guestName){
		GuestDTO guest = guestService.getGuestByName(guestName);
		return ResponseEntity.ok(guest);

	}
	
	@PostMapping("/save")
	public ResponseEntity<GuestDTO> saveGuest(@Valid @RequestBody GuestDTO dto) throws MethodArgumentNotValidException{
		Guest guest = guestService.saveGuest(GuestDTOMapper.fromDtoToEntity(dto));
		
		return ResponseEntity.ok(GuestDTOMapper.fromEntityToDto(guest));
	}
	
	@DeleteMapping("/delete/{guestCpf}")
	public ResponseEntity<Guest> deleteGuest(@PathVariable String guestCpf) {
		guestService.deleteGuest(guestCpf);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/update")
	public ResponseEntity<GuestDTO> updateGuest(
			@RequestParam(name = "cpf", required = true) String cpf,
			@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "phone", required = false) String phone
			
		){
		Guest guest = guestService.updateGuest(cpf, name, phone);
		
		return ResponseEntity.ok(GuestDTOMapper.fromEntityToDto(guest));
	}
}
