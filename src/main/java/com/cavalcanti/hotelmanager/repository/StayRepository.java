package com.cavalcanti.hotelmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cavalcanti.hotelmanager.models.Stay;

@Repository
public interface StayRepository extends PagingAndSortingRepository<Stay, Integer>{
	
	@Query(value = "SELECT DISTINCT guest_cpf FROM stay WHERE check_out_date_time IS NOT NULL", nativeQuery = true)
	List<String> getFormerGuestsCpfs();
	
	@Query(value = "SELECT DISTINCT guest_cpf FROM stay WHERE check_out_date_time IS NULL", nativeQuery = true)
	List<String> getCurrentGuestsCpfs();
}
