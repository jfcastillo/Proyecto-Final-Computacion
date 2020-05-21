package com.taller.castillo.felipe.dao;

import java.util.List;

import com.taller.castillo.felipe.model.TsscAdmin;


public interface TsscAdminDao {
	public void save(TsscAdmin admin);
	public TsscAdmin update(TsscAdmin admin);
	public void delete(TsscAdmin admin);
	public List<TsscAdmin> findAll();
	public TsscAdmin findById(long id);
}
