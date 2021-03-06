package com.cavalcanti.hotelmanager.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cavalcanti.hotelmanager.calculator.PriceCalculator;
import com.cavalcanti.hotelmanager.dtos.GuestDTO;
import com.cavalcanti.hotelmanager.dtos.StayDTO;
import com.cavalcanti.hotelmanager.dtos.mappers.GuestDTOMapper;
import com.cavalcanti.hotelmanager.dtos.mappers.StayDTOMapper;
import com.cavalcanti.hotelmanager.exceptions.InvalidCheckOutDateTimeException;
import com.cavalcanti.hotelmanager.exceptions.ResourceNotFoundException;
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
	public StayDTO getStayById(int id) {	
		Stay stay = stayRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Estadia com ID: " + id +
						" não encontrada!")
				);
		return StayDTOMapper.fromEntityToDto(stay);
	}

	@Override
	public Iterable<StayDTO> getAllStays() {
		
		Iterable<Stay> stays = stayRepository.findAll();
		List<StayDTO> dtos = new ArrayList<>();
		
		for(Stay stay : stays) {
			dtos.add(StayDTOMapper.fromEntityToDto(stay));
		}
		
		return dtos;
	}

	@Override
	public Iterable<GuestDTO> getCurrentGuests() {
		List<String> currentGuestsCpfs = stayRepository.getCurrentGuestsCpfs();
		List<GuestDTO> currentGuests = new ArrayList<>();
		
		for(String cpf : currentGuestsCpfs) {
			Optional<Guest> guest = guestRepository.findById(cpf);
			if(guest.isPresent()) {
				currentGuests.add(GuestDTOMapper.fromEntityToDto(guest.get()));
			}
		}
		
		return currentGuests;
	}

	@Override
	public Iterable<GuestDTO> getFormerGuests() {
		List<String> formerGuestsCpfs = stayRepository.getFormerGuestsCpfs();
		List<GuestDTO> formerGuests = new ArrayList<>();
		
		for(String cpf : formerGuestsCpfs) {
			Optional<Guest> guest = guestRepository.findById(cpf);
			if(guest.isPresent()) {
				formerGuests.add(GuestDTOMapper.fromEntityToDto(guest.get()));
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
	public StayDTO checkOut(int stayId, String checkOutDateTime) {
		Stay stay = stayRepository.findById(stayId).orElseThrow(
				() -> new ResourceNotFoundException("Estadia com Id: " + stayId +
						" não encontrado!")
				);
		
		Stay checkedOut = stay;
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
		LocalDateTime checkOutDateTimeFormatted = LocalDateTime.parse(checkOutDateTime, formatter);
		
		if(checkOutDateTimeFormatted.isAfter(checkedOut.getCheckInDateTime())) {
			checkedOut.setCheckOutDateTime(checkOutDateTimeFormatted);
			checkedOut.setFinalValue(PriceCalculator.calculatePrice(checkedOut));
			stayRepository.save(checkedOut);
			return StayDTOMapper.fromEntityToDto(checkedOut);
		}else {
			throw new InvalidCheckOutDateTimeException("Data e hora de check-out não podem ser " +
					" anteoriores à de check-in");
		}
	}

	@Override
	public void deleteStay(int id) {
		stayRepository.deleteById(id);	
	}
}
