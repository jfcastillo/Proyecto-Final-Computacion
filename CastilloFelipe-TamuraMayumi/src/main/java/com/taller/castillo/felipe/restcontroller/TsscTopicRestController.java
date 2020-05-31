package com.taller.castillo.felipe.restcontroller;

import com.taller.castillo.felipe.exception.EditException;
import com.taller.castillo.felipe.exception.ZeroGroupSprintException;
import com.taller.castillo.felipe.model.TsscTopic;
import com.taller.castillo.felipe.service.TsscTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class TsscTopicRestController {
    @Autowired
    private TsscTopicService tsscTopicService;

    @GetMapping(value = "/tssctopics")
    public Iterable<TsscTopic> findAll() {
        return tsscTopicService.findAll();
    }

    @PostMapping("/tssctopics")
    public TsscTopic createTopic(@RequestBody TsscTopic topic) {
        TsscTopic createdTopic = null;
        try {
            createdTopic = tsscTopicService.createTopic(topic);
        } catch (ZeroGroupSprintException e) {
            e.printStackTrace();
        }
        return createdTopic;
    }
    
    @GetMapping(value = "/tssctopics/{id}")
    public TsscTopic findById(@PathVariable long id){
        return tsscTopicService.findById(id).get();
    }
    
    @PostMapping("/tssctopics/edit")
    public TsscTopic editTopic(@RequestBody TsscTopic topic) {
        TsscTopic createdTopic = null;
        try {
            createdTopic = tsscTopicService.editTopic(topic);
        } catch (ZeroGroupSprintException | EditException e) {
            e.printStackTrace();
        }
        return createdTopic;
    }

    @DeleteMapping(value = "/tssctopics/{id}")
    public void deleteTopic(@PathVariable long id){
        Optional<TsscTopic> topic = tsscTopicService.findById(id);
        tsscTopicService.deleteTopic(topic.get());
    }
}
