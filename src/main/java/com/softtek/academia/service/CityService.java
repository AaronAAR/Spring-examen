package com.softtek.academia.service;

import java.util.List;

import com.softtek.academia.entity.City;

public interface CityService {
	public List<City> getAllCities();
	public City getCityById(Integer city_id);
	public boolean saveCity(City city);
	public boolean deletedCityById(Integer city_id);
}
