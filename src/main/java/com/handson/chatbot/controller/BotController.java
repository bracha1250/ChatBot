package com.handson.chatbot.controller;

import com.handson.chatbot.service.AmazonService;
import com.handson.chatbot.service.JokesEervice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.JarURLConnection;
import java.util.HashMap;

@Service
@RestController
@RequestMapping("/bot")
public class BotController {

    @Autowired
      AmazonService amazonService;
    @Autowired
JokesEervice jokesEervice;
    @RequestMapping(value = "/amazon", method = RequestMethod.GET)
    public ResponseEntity<?> getProduct(@RequestParam String keyword) throws IOException {
        return new ResponseEntity<>(amazonService.searchProducts(keyword), HttpStatus.OK);
    }

    @RequestMapping(value = "/jokes", method = RequestMethod.GET)
    public ResponseEntity<?> getJokes(@RequestParam String keyword) throws IOException {
        return new ResponseEntity<>(jokesEervice.getJokesValue(keyword), HttpStatus.OK);
    }
    @RequestMapping(value = "", method = { RequestMethod.POST})
    public ResponseEntity<?> getBotResponse(@RequestBody BotQuery query) throws IOException {
        HashMap<String, String> params = query.getQueryResult().getParameters();
        String res = "Not found";
        if (params.containsKey("subject")) {
            res = jokesEervice.getJokesValue(params.get("subject"));
        } else if (params.containsKey("product")) {
            res = amazonService.searchProducts(params.get("product"));
        }
        return new ResponseEntity<>(BotResponse.of(res), HttpStatus.OK);
    }


    static class BotQuery {
        QueryResult queryResult;

        public QueryResult getQueryResult() {
            return queryResult;
        }
    }

    static class QueryResult {
        HashMap<String, String> parameters;

        public HashMap<String, String> getParameters() {
            return parameters;
        }
    }

    static class BotResponse {
        String fulfillmentText;
        String source = "BOT";

        public String getFulfillmentText() {
            return fulfillmentText;
        }

        public String getSource() {
            return source;
        }

        public static BotResponse of(String fulfillmentText) {
            BotResponse res = new BotResponse();
            res.fulfillmentText = fulfillmentText;
            return res;
        }
    }
}
