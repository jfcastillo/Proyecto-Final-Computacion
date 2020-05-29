package com.taller.castillo.felipe.business;

import com.taller.castillo.felipe.exception.*;
import com.taller.castillo.felipe.model.TsscAdmin;
import com.taller.castillo.felipe.model.TsscGame;
import com.taller.castillo.felipe.model.TsscStory;
import com.taller.castillo.felipe.model.TsscTopic;

import java.util.Optional;

public interface BusinessDelegate {

    //AdminService
    public TsscAdmin createAdmin(TsscAdmin admin);
    public Optional<TsscAdmin> finAdmindById(long id);

    //GameService
    public TsscGame createGame(TsscGame game) throws ZeroGroupSprintException;
    public TsscGame createGameWithTopic(TsscGame game, long idTopic) throws ZeroGroupSprintException, NullTopicException;
    public TsscGame createGameWithTopic2(TsscTopic topic) throws ZeroGroupSprintException, NullTopicException;
    public TsscGame editGame(TsscGame game) throws EditException, ZeroGroupSprintException;
    public Iterable<TsscGame> findAllGames();
    public Optional<TsscGame> findGameById(long id);
    public void deleteAllGames();

    //StoryService
    public TsscStory createStory(TsscStory story, long idGame) throws StoryException, NullGameException;
    public TsscStory editStory(TsscStory story) throws StoryException, EditException;
    public Iterable<TsscStory> findAllStories();
    public Optional<TsscStory> findByStoryId(long id);

    //TopicService
    public TsscTopic createTopic(TsscTopic topic) throws ZeroGroupSprintException;
    public TsscTopic editTopic(TsscTopic topic) throws EditException, ZeroGroupSprintException;
    public TsscTopic getTopic(long id) throws ZeroGroupSprintException;
    public Iterable<TsscTopic> findAllTopics();
    public Optional<TsscTopic> findTopicById(long id);
    public void deleteTopic(TsscTopic topic);
}
