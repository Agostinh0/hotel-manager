package com.cavalcanti.hotelmanager.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cavalcanti.hotelmanager.dtos.GuestDTO;
import com.cavalcanti.hotelmanager.dtos.mappers.GuestDTOMapper;
import com.cavalcanti.hotelmanager.exceptions.ResourceNotFoundException;
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
	public GuestDTO getGuestByCpf(String cpf) {
		Guest guest = guestRepository.findById(cpf).orElseThrow(
				() -> new ResourceNotFoundException("Hóspede com CPF: " + cpf +
						" não encontrado!")
				);
		return GuestDTOMapper.fromEntityToDto(guest);
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
		Guest guest = guestRepository.findById(cpf).orElseThrow(
				() -> new ResourceNotFoundException("Hóspede com CPF: " + cpf +
						" não encontrado!")
				);
		
		Guest updatedGuest = guest;
		
		updatedGuest.setName(name);
		updatedGuest.setPhone(phone);
		
		return guestRepository.save(updatedGuest);
		
	}
	
	@Override
	public GuestDTO getGuestByName(String name){
		Guest guest = guestRepository.getGuestByName(name);
		if(guest != null) {
			return GuestDTOMapper.fromEntityToDto(guest);
		}else {
			throw new ResourceNotFoundException("Hóspede com nome: " + name +
						" não encontrado!");
		}
	}

	@Override
	public GuestDTO getGuestByPhone(String phone) {
		Guest guest = guestRepository.getGuestByPhone(phone);
		if(guest != null) {
			return GuestDTOMapper.fromEntityToDto(guest);
		}else {
			throw new ResourceNotFoundException("Hóspede com telefone: " + phone +
					" não encontrado!");
		}
	}
	
}
