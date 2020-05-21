package com.taller.castillo.felipe.dao;

import java.util.List;

import com.taller.castillo.felipe.model.TsscStory;
import com.taller.castillo.felipe.model.TsscTimecontrol;

public interface TsscTimeControlDao {
	public void save(TsscTimecontrol timeControl);
	public TsscTimecontrol update(TsscTimecontrol timeControl);
	public void delete(TsscTimecontrol timeControl);
	public List<TsscTimecontrol> findAll();
	public TsscTimecontrol findById(long id);
	public void deleteAll();

}
