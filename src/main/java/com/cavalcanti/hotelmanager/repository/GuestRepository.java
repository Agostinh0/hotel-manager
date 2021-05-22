package com.cavalcanti.hotelmanager.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cavalcanti.hotelmanager.models.Guest;

@Repository
public interface GuestRepository extends PagingAndSortingRepository<Guest, String>{
	
	@Query(value = "SELECT * FROM guest WHERE guest.name = :name", nativeQuery = true)
	Guest getGuestByName(@Param("name") String name);
	
	@Query(value = "SELECT * FROM guest WHERE guest.phone = :phone", nativeQuery = true)
	Guest getGuestByPhone(@Param("phone") String phone);
}
