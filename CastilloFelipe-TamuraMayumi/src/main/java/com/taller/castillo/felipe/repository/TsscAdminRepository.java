package com.taller.castillo.felipe.repository;

import org.springframework.data.repository.CrudRepository;
import com.taller.castillo.felipe.model.TsscAdmin;

public interface TsscAdminRepository extends CrudRepository<TsscAdmin, Long> {
	TsscAdmin findByUserName(String username);

}
