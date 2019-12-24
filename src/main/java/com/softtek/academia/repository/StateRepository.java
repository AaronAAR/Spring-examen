package com.softtek.academia.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import com.softtek.academia.entity.State;

@Repository
public interface StateRepository extends CrudRepository<State, Integer> {
		
}
