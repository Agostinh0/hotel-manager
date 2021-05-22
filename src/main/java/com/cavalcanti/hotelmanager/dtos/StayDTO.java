package com.cavalcanti.hotelmanager.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StayDTO {
	private Integer id;
	private Integer roomId;
	private GuestDTO guest;
	private LocalDateTime checkInDateTime;
	private LocalDateTime checkOutDateTime;
	private Double finalValue;
	private Boolean garageNeeded;
	
	public StayDTO(Integer id, Integer roomId, GuestDTO guest, LocalDateTime checkInDateTime, Boolean garageNeeded) {
		super();
		this.id = id;
		this.roomId = roomId;
		this.guest = guest;
		this.checkInDateTime = checkInDateTime;
		this.garageNeeded = garageNeeded;
	}
}
