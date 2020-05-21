package com.taller.castillo.felipe.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taller.castillo.felipe.dao.TsscGameDao;
import com.taller.castillo.felipe.dao.TsscTopicDao;
import com.taller.castillo.felipe.exception.EditException;
import com.taller.castillo.felipe.exception.NullTopicException;
import com.taller.castillo.felipe.exception.ZeroGroupSprintException;
import com.taller.castillo.felipe.model.TsscGame;
import com.taller.castillo.felipe.model.TsscTopic;
import com.taller.castillo.felipe.repository.TsscGameRepository;
import com.taller.castillo.felipe.repository.TsscTopicRepository;
@Service
public class TsscGameServiceImp implements TsscGameService{
	
	private TsscGameDao gameDao;
	private TsscTopicDao topicDao;
	
	@Autowired
	public TsscGameServiceImp(TsscGameDao gameDao, TsscTopicDao topicDao) {
		super();
		this.gameDao = gameDao;
		this.topicDao = topicDao;
	}
	@Transactional
	@Override
	public TsscGame createGame(TsscGame game) throws ZeroGroupSprintException {
		if (game.getNGroups() <= 0 || game.getNSprints() <= 0) {
			if (game.getNGroups() <= 0 && game.getNSprints() <= 0) {
				throw new ZeroGroupSprintException("The amount of groups and sprint are 0");
			}
			else if (game.getNGroups() <= 0) {
				throw new ZeroGroupSprintException("The amount of groups are  0");
			}
			else {
				throw new ZeroGroupSprintException("The amount of sprints are  0");
			}
		}
		else {
			gameDao.save(game);
			return game;
		}
		
	}
	
	@Transactional
	@Override
	public TsscGame createGameWithTopic(TsscGame game, long idTopic)
			throws ZeroGroupSprintException, NullTopicException {
		if (game.getNGroups() <= 0 || game.getNSprints() <= 0) {
			if (game.getNGroups() <= 0 && game.getNSprints() <= 0) {
				throw new ZeroGroupSprintException("The amount of groups and sprint are 0");
			}
			else if (game.getNGroups() <= 0) {
				throw new ZeroGroupSprintException("The amount of groups are  0");
			}
			else {
				throw new ZeroGroupSprintException("The amount of sprints are 0");
			}
		}
		else if(topicDao.findById(idTopic) == null) {
			throw new NullTopicException("The topic doesn't exist");
		}
	
		else {
			game.setTsscTopic(topicDao.findById(idTopic));
			gameDao.save(game);
			return game;
		}
		
	}
	
	@Transactional
	@Override
	public TsscGame editGame(TsscGame game) throws EditException, ZeroGroupSprintException{
		if (game == null) {
			throw new EditException("The game is null");
		} 
		else if(gameDao.findById(game.getId()) == null) {
			throw new EditException("The game doesn't exist");
		}
		else if (game.getNGroups() <= 0 || game.getNSprints() <= 0) {
			if (game.getNGroups() <= 0 && game.getNSprints() <= 0) {
				throw new ZeroGroupSprintException("The amount of groups and sprint are 0");
			}
			else if (game.getNGroups() <= 0) {
				throw new ZeroGroupSprintException("The amount of groups  0");
			}
			else {
				throw new ZeroGroupSprintException("The amount of sprints  0");
			}
		}
		else {
			gameDao.update(game);
			return game;
		}
		 
	}
	@Transactional
	@Override
	public TsscGame createGameWithTopic2(TsscTopic topic)
			throws ZeroGroupSprintException, NullTopicException {
		if (topic.getDefaultGroups() <= 0 || topic.getDefaultSprints() <= 0) {
			if (topic.getDefaultGroups() <= 0 && topic.getDefaultSprints() <= 0) {
				throw new ZeroGroupSprintException("The amount of groups and sprint are 0");
			}
			else if (topic.getDefaultGroups() <= 0) {
				throw new ZeroGroupSprintException("The amount of groups  0");
			}
			else {
				throw new ZeroGroupSprintException("The amount of sprints  0");
			}
		}		
	
		else {
			TsscGame game = new TsscGame();
			game.setTsscStories(topic.getTsscStories());
			game.setTsscTimecontrol(topic.getTsscTimecontrol());
			gameDao.save(game);
			return game;
		}
	}
	@Transactional
	@Override
	public Iterable<TsscGame> findAll() {
		return gameDao.findAll();
	}
	@Transactional
	@Override
	public Optional<TsscGame> findById(long id) {
		return Optional.of(gameDao.findById(id));
	}
	@Override
	public void deleteAll() {
		gameDao.deleteAll();		
	}

	
}
