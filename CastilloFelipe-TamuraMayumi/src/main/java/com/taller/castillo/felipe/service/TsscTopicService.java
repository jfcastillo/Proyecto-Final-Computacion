package com.taller.castillo.felipe.service;
import java.util.Optional;

import com.taller.castillo.felipe.exception.EditException;
import com.taller.castillo.felipe.exception.ZeroGroupSprintException;
import com.taller.castillo.felipe.model.TsscTopic;

public interface TsscTopicService {
	public TsscTopic createTopic(TsscTopic topic) throws ZeroGroupSprintException;
	public TsscTopic editTopic(TsscTopic topic) throws EditException, ZeroGroupSprintException;
	public TsscTopic getTopic(long id) throws ZeroGroupSprintException;
	public Iterable<TsscTopic> findAll();
	public Optional<TsscTopic> findById(long id);
	public void deleteTopic(TsscTopic topic);

}
