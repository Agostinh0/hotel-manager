package com.cavalcanti.hotelmanager.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cavalcanti.hotelmanager.models.Guest;
import com.cavalcanti.hotelmanager.repository.GuestRepository;
import com.cavalcanti.hotelmanager.service.GuestService;

@Service
public class GuestServiceImpl implements GuestService{

	@Autowired
	GuestRepository guestRepository;
	
	@Override
	public Iterable<Guest> getAllGuests() {
		return guestRepository.findAll();
	}

	@Override
	public Optional<Guest> getGuestByCpf(String cpf) {
		return guestRepository.findById(cpf);
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
	
}
