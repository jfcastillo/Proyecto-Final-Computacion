package com.taller.castillo.felipe.service;

import com.taller.castillo.felipe.exception.EditException;
import com.taller.castillo.felipe.exception.NullGameException;
import com.taller.castillo.felipe.model.TsscTimecontrol;

public interface TsscTimecontrolService {
	
	public TsscTimecontrol createTimecontrol(long idGame, TsscTimecontrol timecontrol)throws NullGameException;
	public TsscTimecontrol editTimecontrol(TsscTimecontrol timecontrol) throws EditException;
	public Iterable<TsscTimecontrol> findAll();
	public TsscTimecontrol findById(long id);
	public void deleteAll();
	public Iterable<TsscTimecontrol> findByGameId(long id);
	public void deleteTimecontrol(TsscTimecontrol timecontrol);
}
