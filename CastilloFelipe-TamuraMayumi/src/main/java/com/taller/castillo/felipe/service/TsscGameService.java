package com.taller.castillo.felipe.service;

import java.util.Optional;

import com.taller.castillo.felipe.exception.EditException;
import com.taller.castillo.felipe.exception.NullTopicException;
import com.taller.castillo.felipe.exception.ZeroGroupSprintException;
import com.taller.castillo.felipe.model.TsscGame;
import com.taller.castillo.felipe.model.TsscTopic;

public interface TsscGameService {
	public TsscGame createGame(TsscGame game) throws ZeroGroupSprintException;
	public TsscGame createGameWithTopic(TsscGame game, long idTopic) throws ZeroGroupSprintException, NullTopicException;
	public TsscGame createGameWithTopic2(TsscTopic topic) throws ZeroGroupSprintException, NullTopicException;
	public TsscGame editGame(TsscGame game) throws EditException, ZeroGroupSprintException;
	public Iterable<TsscGame> findAll();
	public Optional<TsscGame> findById(long id);
	public void deleteAll();
	//public TsscGame addTopicToGame();
}
