package com.softtek.academia.service;

import java.util.List;

import com.softtek.academia.entity.State;

public interface StateService {
	public List<State> getAllStates();
	public State getStateById(Integer state_id);
	public boolean saveState(State state);
	public boolean deleteStateById(Integer state_id);
}
