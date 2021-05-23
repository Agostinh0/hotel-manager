package com.cavalcanti.hotelmanager.controllers.exceptions;

import java.io.Serializable;
import java.time.Instant;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class StandardError implements Serializable{
	
	private static final long serialVersionUID = 7739464004869772800L;
	
	private Instant timestamp;
	private Integer status;
	private String error;
	private String message;
	private String path;
}
