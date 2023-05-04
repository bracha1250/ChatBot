package com.handson.chatbot.controller;

import java.io.IOException;
import java.util.HashMap;

import com.handson.chatbot.service.AmazonService;
import com.handson.chatbot.service.AppliancesconnectionService;
import com.handson.chatbot.service.JokesEervice;
import com.handson.chatbot.service.SearchAllService;
import com.handson.chatbot.service.WalmartService;
import com.handson.chatbot.service.ZabiloService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Service
@RestController
@RequestMapping("/bot")
public class BotController {
  @Autowired
  ZabiloService zabiloService;
  @Autowired
  AppliancesconnectionService appliancesconnectionService;
  @Autowired
  WalmartService walmartService;
  @Autowired
  AmazonService amazonService;
  @Autowired
  JokesEervice jokesEervice;

  @Autowired
  SearchAllService searchAllService;

  @RequestMapping(value = "/all", method = RequestMethod.GET)
  public ResponseEntity<?> getAll(@RequestParam String keyword) throws Exception {
    return new ResponseEntity<>(searchAllService.search(keyword), HttpStatus.OK);
  }

  @RequestMapping(value = "/Zabilo", method = RequestMethod.GET)
  public ResponseEntity<?> getZabilo(@RequestParam String keyword) throws Exception {
    return new ResponseEntity<>(zabiloService.search(keyword), HttpStatus.OK);
  }

  @RequestMapping(value = "/appliancesconnection", method = RequestMethod.GET)
  public ResponseEntity<?> getAppliancesconnection(@RequestParam String keyword) throws IOException {
    return new ResponseEntity<>(appliancesconnectionService.search(keyword), HttpStatus.OK);
  }

  @RequestMapping(value = "/walmartService", method = RequestMethod.GET)
  public ResponseEntity<?> getWalmart(@RequestParam String keyword) throws Exception {
    return new ResponseEntity<>(walmartService.search(keyword), HttpStatus.OK);
  }

  @RequestMapping(value = "/amazon", method = RequestMethod.GET)
  public ResponseEntity<?> getProduct(@RequestParam String keyword) throws IOException {
    return new ResponseEntity<>(amazonService.searchProducts(keyword), HttpStatus.OK);
  }

  @RequestMapping(value = "/jokes", method = RequestMethod.GET)
  public ResponseEntity<?> getJokes(@RequestParam String keyword) throws
  IOException {
  return new ResponseEntity<>(jokesEervice.getJokesValue(keyword),
  HttpStatus.OK);
  }
  @RequestMapping(value = "", method = { RequestMethod.POST })
  public ResponseEntity<?> getBotResponse(@RequestBody BotQuery query) throws Exception {
    HashMap<String, String> params = query.getQueryResult().getParameters();
    String res = "Not found";
if (params.containsKey("subject")) {
            res = jokesEervice.getJokesValue(params.get("subject"));}
    if (params.containsKey("product")) {

      res = searchAllService.search(params.get("product"));
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
