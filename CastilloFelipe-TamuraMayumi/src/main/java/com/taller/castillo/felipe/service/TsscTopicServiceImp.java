package com.taller.castillo.felipe.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taller.castillo.felipe.dao.TsscTopicDao;
import com.taller.castillo.felipe.exception.EditException;
import com.taller.castillo.felipe.exception.ZeroGroupSprintException;
import com.taller.castillo.felipe.model.TsscTopic;
import com.taller.castillo.felipe.repository.TsscTopicRepository;

@Service
public class TsscTopicServiceImp implements TsscTopicService{
	
	private TsscTopicDao topicDao;	
	
	@Autowired
	public TsscTopicServiceImp(TsscTopicDao topicRepository) {
		super();
		this.topicDao = topicRepository;
	}
	@Transactional
	@Override
	public TsscTopic createTopic(TsscTopic topic)  throws ZeroGroupSprintException {
		
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
			topicDao.save(topic);
			return topic;
		}
		
		
	}
	@Transactional
	@Override
	public TsscTopic editTopic(TsscTopic topic) throws EditException, ZeroGroupSprintException{
		
		if (topic == null) {
			throw new EditException("The topic doesn't exist");
		} 
		else if (topic.getDefaultGroups() <= 0 || topic.getDefaultSprints() <= 0) {
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
			long idTopic = topic.getId();
			TsscTopic newTopic = getTopic(idTopic);
			newTopic.setDefaultGroups(topic.getDefaultGroups());
			newTopic.setDefaultSprints(topic.getDefaultSprints());
			newTopic.setDescription(topic.getDescription());
			newTopic.setGroupPrefix(topic.getGroupPrefix());
			newTopic.setName(topic.getName());
			topicDao.save(newTopic);
			return newTopic;
		}	
		
	}

	@Transactional
	@Override
	public TsscTopic getTopic(long id) throws ZeroGroupSprintException {
		TsscTopic topic = null; 
		if (topicDao.findById(id) == null) {
			throw new ZeroGroupSprintException("No existe esa orden");
		}
		else {
			topic = topicDao.findById(id);
		}

		return topic;
	}
	@Transactional
	@Override
	public Iterable<TsscTopic> findAll() {
		return topicDao.findAll();
	}

	@Transactional
	@Override
	public Optional<TsscTopic> findById(long id) {
		return Optional.of(topicDao.findById(id));
	}

	@Transactional
	@Override
	public void deleteTopic(TsscTopic topic) {
		// TODO Auto-generated method stub
		topicDao.delete(topic);;
	}
	
	

}
