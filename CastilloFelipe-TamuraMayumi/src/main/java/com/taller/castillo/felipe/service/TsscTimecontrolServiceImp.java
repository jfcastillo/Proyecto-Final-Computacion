package com.taller.castillo.felipe.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taller.castillo.felipe.dao.TsscGameDao;
import com.taller.castillo.felipe.dao.TsscTimeControlDao;
import com.taller.castillo.felipe.exception.EditException;
import com.taller.castillo.felipe.exception.NullGameException;
import com.taller.castillo.felipe.exception.ZeroGroupSprintException;
import com.taller.castillo.felipe.model.TsscGame;
import com.taller.castillo.felipe.model.TsscStory;
import com.taller.castillo.felipe.model.TsscTimecontrol;
@Service
public class TsscTimecontrolServiceImp implements TsscTimecontrolService{
	@Autowired
	private TsscGameService gameService;
	@Autowired
	private TsscGameDao gameDao;
	@Autowired
	private TsscTimeControlDao timecontrolDao;
	
	@Transactional
	@Override
	public TsscTimecontrol createTimecontrol(long idGame, TsscTimecontrol timecontrol) throws NullGameException{
		if (gameDao.findById(idGame)  == null) {
			throw new NullGameException("The game doesn't exist");
		}
		else {
			
			TsscGame game = gameDao.findById(idGame);			
			game.addTsscTimecontrol(timecontrol);
			
			timecontrol.setTsscGame(game);			
			
			
			try {
				gameService.editGame(game);
			} catch (EditException | ZeroGroupSprintException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}		
		timecontrolDao.save(timecontrol);
		return timecontrol;
	}
	@Transactional
	@Override
	public TsscTimecontrol editTimecontrol(TsscTimecontrol timecontrol) throws EditException {
		if (timecontrol == null) {
			throw new EditException("The timecontrol is null");
		}
		else if (timecontrolDao.findById(timecontrol.getId()) == null) {
			throw new EditException("The timecontrol doesn't exist");
		}
		else {
			TsscTimecontrol oldtimecontrol = timecontrolDao.findById(timecontrol.getId());
			timecontrol.setTsscGame(oldtimecontrol.getTsscGame());
			timecontrolDao.update(timecontrol);
			return timecontrol;
		}
		
	}
	@Transactional
	@Override
	public Iterable<TsscTimecontrol> findAll() {
		return timecontrolDao.findAll();
	}
	@Transactional
	@Override
	public TsscTimecontrol findById(long id) {
		return timecontrolDao.findById(id);
	}
	@Transactional
	@Override
	public void deleteAll() {
		timecontrolDao.deleteAll();
		
	}
	@Transactional
	@Override
	public Iterable<TsscTimecontrol> findByGameId(long id) {
		return timecontrolDao.findByGameId(id);
	}
	@Transactional
	@Override
	public void deleteTimecontrol(TsscTimecontrol timecontrol) {
		timecontrolDao.delete(timecontrol);
		
	}

}
