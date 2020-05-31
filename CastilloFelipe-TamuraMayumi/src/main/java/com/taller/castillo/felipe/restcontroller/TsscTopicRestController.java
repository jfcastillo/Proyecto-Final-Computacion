package com.taller.castillo.felipe.restcontroller;

import com.taller.castillo.felipe.model.TsscTopic;
import com.taller.castillo.felipe.service.TsscTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class TsscTopicRestController {
    @Autowired
    private TsscTopicService tsscTopicService;

    @GetMapping(value = "/tssctopics")
    public Iterable<TsscTopic> findAll(){
        return tsscTopicService.findAll();
    }
}
