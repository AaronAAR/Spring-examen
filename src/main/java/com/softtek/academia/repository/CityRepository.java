package com.softtek.academia.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import com.softtek.academia.entity.City;

@Repository
public interface CityRepository extends CrudRepository<City, Integer> {
	
}