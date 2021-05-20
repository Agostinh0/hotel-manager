package com.cavalcanti.hotelmanager.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cavalcanti.hotelmanager.models.Stay;

@Repository
public interface StayRepository extends PagingAndSortingRepository<Stay, Integer>{

}
