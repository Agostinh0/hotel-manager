package com.cavalcanti.hotelmanager.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cavalcanti.hotelmanager.models.Guest;

@Repository
public interface GuestRepository extends PagingAndSortingRepository<Guest, String>{

}
