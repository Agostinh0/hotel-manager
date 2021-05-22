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
	public Optional<StayDTO> getStayById(int id) {	
		Optional<Stay> optionalStay = stayRepository.findById(id);
		if(optionalStay.isPresent()) {
			Stay stay = optionalStay.get();
			
			Optional<StayDTO> dto = Optional.of(
					new StayDTO(stay.getId(),
							stay.getRoomId(),
							new GuestDTO(stay.getGuest().getCpf(),
										 stay.getGuest().getName(),
										 stay.getGuest().getPhone()),
							stay.getCheckInDateTime(),
							stay.getCheckOutDateTime(),
							stay.getFinalValue(),
							stay.getGarageNeeded()));
			return dto;
		}else {
			return null;
		}
	}

	@Override
	public Iterable<StayDTO> getAllStays() {
		
		Iterable<Stay> stays = stayRepository.findAll();
		List<StayDTO> dtos = new ArrayList<>();
		
		for(Stay stay : stays) {
			dtos.add(new StayDTO(stay.getId(),
					stay.getRoomId(),
					new GuestDTO(stay.getGuest().getCpf(),
								 stay.getGuest().getName(),
								 stay.getGuest().getPhone()),
					stay.getCheckInDateTime(),
					stay.getCheckOutDateTime(),
					stay.getFinalValue(),
					stay.getGarageNeeded()));
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
		Optional<Stay> stay = stayRepository.findById(stayId);
		
		if(stay.isPresent()) {
			Stay checkedOut = stay.get();
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
			LocalDateTime checkOutDateTimeFormatted = LocalDateTime.parse(checkOutDateTime, formatter);
			
			if(checkOutDateTimeFormatted.isAfter(checkedOut.getCheckInDateTime())) {
				checkedOut.setCheckOutDateTime(checkOutDateTimeFormatted);
				checkedOut.setFinalValue(PriceCalculator.calculatePrice(checkedOut));
				stayRepository.save(checkedOut);
				return StayDTOMapper.fromEntityToDto(checkedOut);
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
