package com.taller.castillo.felipe.service;

import java.util.Optional;

import com.taller.castillo.felipe.model.TsscAdmin;

public interface TsscAdminService {
	public TsscAdmin createAdmin(TsscAdmin admin);
	public Optional<TsscAdmin> findById(long id); 

}
