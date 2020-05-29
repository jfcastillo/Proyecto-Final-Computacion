package com.taller.castillo.felipe.business;

import com.taller.castillo.felipe.exception.*;
import com.taller.castillo.felipe.model.TsscAdmin;
import com.taller.castillo.felipe.model.TsscGame;
import com.taller.castillo.felipe.model.TsscStory;
import com.taller.castillo.felipe.model.TsscTopic;
import com.taller.castillo.felipe.service.TsscAdminService;
import com.taller.castillo.felipe.service.TsscGameService;
import com.taller.castillo.felipe.service.TsscStoryService;
import com.taller.castillo.felipe.service.TsscTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BusinessDelegateImp implements BusinessDelegate {

    @Autowired
    private TsscAdminService tsscAdminService;

    @Autowired
    private TsscGameService tsscGameService;

    @Autowired
    private TsscStoryService tsscStoryService;

    @Autowired
    private TsscTopicService tsscTopicService;

    @Override
    public TsscAdmin createAdmin(TsscAdmin admin) {
        return tsscAdminService.createAdmin(admin);
    }

    @Override
    public Optional<TsscAdmin> finAdmindById(long id) {
        return tsscAdminService.findById(id);
    }

    @Override
    public TsscGame createGame(TsscGame game) throws ZeroGroupSprintException {
        return tsscGameService.createGame(game);
    }

    @Override
    public TsscGame createGameWithTopic(TsscGame game, long idTopic) throws ZeroGroupSprintException, NullTopicException {
        return tsscGameService.createGameWithTopic(game, idTopic);
    }

    @Override
    public TsscGame createGameWithTopic2(TsscTopic topic) throws ZeroGroupSprintException, NullTopicException {
        return tsscGameService.createGameWithTopic2(topic);
    }

    @Override
    public TsscGame editGame(TsscGame game) throws EditException, ZeroGroupSprintException {
        return tsscGameService.editGame(game);
    }

    @Override
    public Iterable<TsscGame> findAllGames() {
        return tsscGameService.findAll();
    }

    @Override
    public Optional<TsscGame> findGameById(long id) {
        return tsscGameService.findById(id);
    }

    @Override
    public void deleteAllGames() {
        tsscGameService.deleteAll();
    }

    @Override
    public TsscStory createStory(TsscStory story, long idGame) throws StoryException, NullGameException {
        return tsscStoryService.createStory(story, idGame);
    }

    @Override
    public TsscStory editStory(TsscStory story) throws StoryException, EditException {
        return tsscStoryService.editStory(story);
    }

    @Override
    public Iterable<TsscStory> findAllStories() {
        return tsscStoryService.findAll();
    }

    @Override
    public Optional<TsscStory> findByStoryId(long id) {
        return tsscStoryService.findById(id);
    }

    @Override
    public TsscTopic createTopic(TsscTopic topic) throws ZeroGroupSprintException {
        return tsscTopicService.createTopic(topic);
    }

    @Override
    public TsscTopic editTopic(TsscTopic topic) throws EditException, ZeroGroupSprintException {
        return tsscTopicService.editTopic(topic);
    }

    @Override
    public TsscTopic getTopic(long id) throws ZeroGroupSprintException {
        return tsscTopicService.getTopic(id);
    }

    @Override
    public Iterable<TsscTopic> findAllTopics() {
        return tsscTopicService.findAll();
    }

    @Override
    public Optional<TsscTopic> findTopicById(long id) {
        return tsscTopicService.findById(id);
    }

    @Override
    public void deleteTopic(TsscTopic topic) {
        tsscTopicService.deleteTopic(topic);
    }

}