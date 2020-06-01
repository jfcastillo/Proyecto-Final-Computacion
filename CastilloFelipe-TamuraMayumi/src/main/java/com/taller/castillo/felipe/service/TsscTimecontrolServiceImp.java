package com.taller.castillo.felipe.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taller.castillo.felipe.dao.TsscTimeControlDao;
import com.taller.castillo.felipe.exception.EditException;
import com.taller.castillo.felipe.model.TsscTimecontrol;
@Service
public class TsscTimecontrolServiceImp implements TsscTimecontrolService{

	@Autowired
	private TsscTimeControlDao timecontrolDao;
	
	@Override
	public TsscTimecontrol createTimecontrol(TsscTimecontrol timecontrol) {
		timecontrolDao.save(timecontrol);
		return timecontrol;
	}

	@Override
	public TsscTimecontrol editTimecontrol(TsscTimecontrol timecontrol) throws EditException {
		if (timecontrol == null) {
			throw new EditException("The timecontrol is null");
		}
		else if (timecontrolDao.findById(timecontrol.getId()) == null) {
			throw new EditException("The timecontrol doesn't exist");
		}
		else {
			timecontrolDao.update(timecontrol);
		}
		return timecontrol;
	}

	@Override
	public Iterable<TsscTimecontrol> findAll() {
		return timecontrolDao.findAll();
	}

	@Override
	public TsscTimecontrol findById(long id) {
		return timecontrolDao.findById(id);
	}

	@Override
	public void deleteAll() {
		timecontrolDao.deleteAll();
		
	}

	@Override
	public Iterable<TsscTimecontrol> findByGameId(long id) {
		return timecontrolDao.findByGameId(id);
	}

}
