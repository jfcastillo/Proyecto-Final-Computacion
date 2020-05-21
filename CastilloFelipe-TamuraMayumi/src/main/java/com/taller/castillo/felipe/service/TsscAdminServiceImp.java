package com.taller.castillo.felipe.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.taller.castillo.felipe.model.TsscAdmin;
import com.taller.castillo.felipe.repository.TsscAdminRepository;

@Service
public class TsscAdminServiceImp implements TsscAdminService{
	
	@Autowired
	private TsscAdminRepository adminRepository;

	@Override
	public TsscAdmin createAdmin(TsscAdmin admin) {
		return adminRepository.save(admin);
	}

	@Override
	public Optional<TsscAdmin> findById(long id) {		
		return adminRepository.findById(id);
	}

}
