package com.cavalcanti.hotelmanager.dtos.mappers;

import com.cavalcanti.hotelmanager.dtos.StayDTO;
import com.cavalcanti.hotelmanager.models.Stay;

public class StayDTOMapper {
	
	public static StayDTO fromEntityToDto(Stay stay) {
		return new StayDTO(stay.getId(), stay.getRoomId(),
				GuestDTOMapper.fromEntityToDto(stay.getGuest()), stay.getCheckInDateTime(),
				stay.getCheckOutDateTime(), stay.getFinalValue(),
				stay.getGarageNeeded());
	}
	
	public static Stay fromDtoToEntity(StayDTO dto) {
			return new Stay(dto.getId(), 
					dto.getRoomId(), 
					GuestDTOMapper.fromDtoToEntity(dto.getGuest()), 
					dto.getCheckInDateTime(), 
					dto.getGarageNeeded());
		}
	}
