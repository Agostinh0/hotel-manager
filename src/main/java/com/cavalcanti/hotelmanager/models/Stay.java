package com.cavalcanti.hotelmanager.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Stay {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@Column(name = "room_id")
	private Integer roomId;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	private Guest guest;
	
	@NotNull
	@Column(name = "check_in_date_time")
	private LocalDateTime checkInDateTime;
	
	@Column(name = "check_out_date_time")
	private LocalDateTime checkOutDateTime;
	
	@Column(name = "final_value")
	private Double finalValue;
	
	@NotNull
	@Column(name = "garage_needed")
	private Boolean garageNeeded;

	public Stay(Integer id, Integer roomId, Guest guest, LocalDateTime checkInDateTime, Boolean garageNeeded) {
		super();
		this.id = id;
		this.roomId = roomId;
		this.guest = guest;
		this.checkInDateTime = checkInDateTime;
		this.garageNeeded = garageNeeded;
	}
}
