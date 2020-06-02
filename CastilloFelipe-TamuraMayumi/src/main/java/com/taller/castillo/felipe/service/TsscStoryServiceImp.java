package com.taller.castillo.felipe.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taller.castillo.felipe.dao.TsscGameDao;
import com.taller.castillo.felipe.dao.TsscStoryDao;
import com.taller.castillo.felipe.exception.EditException;
import com.taller.castillo.felipe.exception.NullGameException;
import com.taller.castillo.felipe.exception.StoryException;
import com.taller.castillo.felipe.exception.ZeroGroupSprintException;
import com.taller.castillo.felipe.model.TsscGame;
import com.taller.castillo.felipe.model.TsscStory;
import com.taller.castillo.felipe.repository.TsscGameRepository;
import com.taller.castillo.felipe.repository.TsscStoryRepository;

@Service
public class TsscStoryServiceImp implements TsscStoryService{
	@Autowired
	private TsscStoryDao storyDao;
	@Autowired
	private TsscGameDao gameDao;
	@Autowired
	private TsscGameService gameService;
		
	
	@Transactional
	@Override
	public TsscStory createStory(TsscStory story, long idGame) throws StoryException, NullGameException {
		
		 if (gameDao.findById(idGame)  == null) {
			throw new NullGameException("The game doesn't exist");

		} else if (story.getBusinessValue().compareTo(new BigDecimal(0)) == 0
				|| story.getBusinessValue().compareTo(new BigDecimal(0)) == -1) {
			throw new StoryException("The business value is 0 or less");

		} else if (story.getInitialSprint().compareTo(new BigDecimal(0)) == 0
				|| story.getInitialSprint().compareTo(new BigDecimal(0)) == -1) {
			throw new StoryException("The initial sprint is 0 or less");

		} else if (story.getPriority().compareTo(new BigDecimal(0)) == 0
				|| story.getPriority().compareTo(new BigDecimal(0)) == -1) {
			throw new StoryException("The priority is 0 or less");

		}
		else {
			TsscGame game = gameDao.findById(idGame);
			story.setTsscGame(game);
			
			game.addTsscStory(story);
			try {
				gameService.editGame(game);
			} catch (EditException | ZeroGroupSprintException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			storyDao.save(story);
			return story;
		}
		
	}
	@Transactional
	@Override
	public TsscStory createStory(TsscStory story) throws StoryException, NullGameException {
		if (gameDao.findById(story.getTsscGame().getId())  == null) {
			throw new NullGameException("The game doesn't exist");

		} else if (story.getBusinessValue().compareTo(new BigDecimal(0)) == 0
				|| story.getBusinessValue().compareTo(new BigDecimal(0)) == -1) {
			throw new StoryException("The business value is 0 or less");

		} else if (story.getInitialSprint().compareTo(new BigDecimal(0)) == 0
				|| story.getInitialSprint().compareTo(new BigDecimal(0)) == -1) {
			throw new StoryException("The initial sprint is 0 or less");

		} else if (story.getPriority().compareTo(new BigDecimal(0)) == 0
				|| story.getPriority().compareTo(new BigDecimal(0)) == -1) {
			throw new StoryException("The priority is 0 or less");

		}
		else {
			TsscGame game = gameDao.findById(story.getTsscGame().getId());
			story.setTsscGame(game);
			
			game.addTsscStory(story);
			try {
				gameService.editGame(game);
			} catch (EditException | ZeroGroupSprintException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			storyDao.save(story);
			return story;
		}
	}
	@Transactional
	@Override
	public TsscStory editStory(TsscStory story) throws StoryException, EditException {
		if (storyDao.findById(story.getId()) == null) {
			throw new EditException("The story doesn't exist");
		}
		else if (story.getBusinessValue().compareTo(new BigDecimal(0)) == 0
				|| story.getBusinessValue().compareTo(new BigDecimal(0)) == -1) {
			throw new StoryException("The business value is 0 or less");

		} else if (story.getInitialSprint().compareTo(new BigDecimal(0)) == 0
				|| story.getInitialSprint().compareTo(new BigDecimal(0)) == -1) {
			throw new StoryException("The initial sprint is 0 or less");

		} else if (story.getPriority().compareTo(new BigDecimal(0)) == 0
				|| story.getPriority().compareTo(new BigDecimal(0)) == -1) {
			throw new StoryException("The priority is 0 or less");

		}
		else{
			TsscStory oldStory = storyDao.findById(story.getId());
			story.setTsscGame(oldStory.getTsscGame());
			story = storyDao.update(story);
			return story;
		}
	}
	@Transactional
	@Override
	public Iterable<TsscStory> findAll() {
		return storyDao.findAll();
	}

	@Transactional
	@Override
	public Optional<TsscStory> findById(long id) {
		return Optional.of(storyDao.findById(id));
	}

	@Transactional
	@Override
	public Iterable<TsscStory> findByGameId(long id) {
		return storyDao.findByGameId(id);
	}
	
	@Transactional
	@Override
	public void deleteStory(TsscStory story) {
		storyDao.delete(story);
	}
	

}