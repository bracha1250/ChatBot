package com.handson.chatbot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchAllService {
    @Autowired
    AppliancesconnectionService appliancesconnectionService;

    @Autowired
    ZabiloService zabiloService;
    @Autowired
    WalmartService walmartService;

    List<BaseSearchService> searches;

    @PostConstruct
    public void init() {
        searches = new ArrayList<>();
        searches.add(walmartService);
        searches.add(appliancesconnectionService);
        searches.add(zabiloService);

    }

    public String search(String keyword) throws Exception {
        List<String> results = new ArrayList<>();
        for (BaseSearchService s : searches) {
            results.add(s.search(keyword));
        }
        return results.stream().collect(Collectors.joining("\n"));
    }
}
