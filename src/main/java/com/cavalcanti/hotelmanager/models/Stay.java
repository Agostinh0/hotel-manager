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
	
	public Stay() {}

	public Stay(Integer id, Integer roomId, Guest guest, LocalDateTime checkInDateTime, Boolean garageNeeded) {
		super();
		this.id = id;
		this.roomId = roomId;
		this.guest = guest;
		this.checkInDateTime = checkInDateTime;
		this.garageNeeded = garageNeeded;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	public LocalDateTime getCheckInDateTime() {
		return checkInDateTime;
	}

	public void setCheckInDateTime(LocalDateTime checkInDateTime) {
		this.checkInDateTime = checkInDateTime;
	}

	public LocalDateTime getCheckOutDateTime() {
		return checkOutDateTime;
	}

	public void setCheckOutDateTime(LocalDateTime checkOutTime) {
		this.checkOutDateTime = checkOutTime;
	}

	public Double getFinalValue() {
		return finalValue;
	}

	public void setFinalValue(Double finalValue) {
		this.finalValue = finalValue;
	}

	public Boolean getGarageNeeded() {
		return garageNeeded;
	}

	public void setGarageNeeded(Boolean garageNeeded) {
		this.garageNeeded = garageNeeded;
	}

}
