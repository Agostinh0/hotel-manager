package com.cavalcanti.hotelmanager.controllers;

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

import com.cavalcanti.hotelmanager.dtos.GuestDTO;
import com.cavalcanti.hotelmanager.dtos.StayDTO;
import com.cavalcanti.hotelmanager.dtos.mappers.StayDTOMapper;
import com.cavalcanti.hotelmanager.exceptions.ResourceNotFoundException;
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
	public Iterable<StayDTO> getAllStays(){
		return stayService.getAllStays();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<StayDTO> getStay(@PathVariable Integer id){
		StayDTO stay = stayService.getStayById(id);
		return ResponseEntity.ok(stay);

	}
	
	@GetMapping("/currentGuests")
	public Iterable<GuestDTO> getCurrentGuests(){
		return stayService.getCurrentGuests();
	}
	
	@GetMapping("/formerGuests")
	public Iterable<GuestDTO> getFormerGuests(){
		return stayService.getFormerGuests();
	}
	
	@PostMapping(value = "/checkIn", consumes = "application/json")
	public StayDTO checkIn(@RequestBody StayDTO dto) {	
		Stay checkIn = stayService.checkIn(StayDTOMapper.fromDtoToEntity(dto));
		if(checkIn.getGuest() != null) {
			return StayDTOMapper.fromEntityToDto(checkIn);
		}else {
			throw new ResourceNotFoundException("Hóspede não encontrado!");
		}
	}
	
	@PutMapping("/checkOut")
	public ResponseEntity<StayDTO> checkOut(
			@RequestParam(name = "stayId", required = true) Integer stayId,
			@RequestParam(name = "checkOutDateTime", required = true) String checkOutDateTime
		){
		
		StayDTO dto = stayService.checkOut(stayId, checkOutDateTime);
		
		if(dto != null) {
			return ResponseEntity.ok(dto);
		}else {
			throw new ResourceNotFoundException("Estadia não encontrada!");
		}
	}
	
	@DeleteMapping("/delete/{guestCpf}")
	public ResponseEntity<Stay> deleteStay(@PathVariable Integer id) {
		stayService.deleteStay(id);
		return ResponseEntity.ok().build();

	}
}
