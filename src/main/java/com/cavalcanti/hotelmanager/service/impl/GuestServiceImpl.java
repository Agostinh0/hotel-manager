package com.cavalcanti.hotelmanager.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cavalcanti.hotelmanager.dtos.GuestDTO;
import com.cavalcanti.hotelmanager.dtos.mappers.GuestDTOMapper;
import com.cavalcanti.hotelmanager.models.Guest;
import com.cavalcanti.hotelmanager.repository.GuestRepository;
import com.cavalcanti.hotelmanager.service.GuestService;

@Service
public class GuestServiceImpl implements GuestService{

	@Autowired
	GuestRepository guestRepository;
	
	@Override
	public Iterable<GuestDTO> getAllGuests() {
		Iterable<Guest> guests = guestRepository.findAll();
		List<GuestDTO> guestsDtos = new ArrayList<>();
		
		for(Guest guest : guests) {
			guestsDtos.add(GuestDTOMapper.fromEntityToDto(guest));
		}
		
		return guestsDtos;
	}

	@Override
	public Optional<GuestDTO> getGuestByCpf(String cpf) {
		Optional<Guest> guest = guestRepository.findById(cpf);
		if(guest.isPresent()) {
			Optional<GuestDTO> dto = Optional.of(
					GuestDTOMapper.fromEntityToDto(guest.get()));
			return dto;
		}else {
			return null;
		}
	}

	@Override
	public Guest saveGuest(Guest guest) {
		return guestRepository.save(guest);
	}

	@Override
	public void deleteGuest(String guestCpf) {
		guestRepository.deleteById(guestCpf);
	}

	@Override
	public Guest updateGuest(String cpf, String name, String phone) {	
		Optional<Guest> guest = guestRepository.findById(cpf);
		
		if(guest.isPresent()) {
			Guest updatedGuest = guest.get();
			
			updatedGuest.setName(name);
			updatedGuest.setPhone(phone);
			return guestRepository.save(updatedGuest);
		}else {
			return null;
		}
		
	}
	
	@Override
	public Optional<GuestDTO> getGuestByName(String name){
		Optional<Guest> guest = Optional.of(guestRepository.getGuestByName(name));
		if(guest.isPresent()) {
			Optional<GuestDTO> dto = Optional.of(
					GuestDTOMapper.fromEntityToDto(guest.get()));
			return dto;
		}else {
			return Optional.empty();
		}
	}

	@Override
	public Optional<GuestDTO> getGuestByPhone(String phone) {
		Optional<Guest> guest = Optional.of(guestRepository.getGuestByPhone(phone));
		if(guest.isPresent()) {
			Optional<GuestDTO> dto = Optional.of(
					GuestDTOMapper.fromEntityToDto(guest.get()));
			return dto;
		}else {
			return Optional.empty();
		}
	}
	
}
