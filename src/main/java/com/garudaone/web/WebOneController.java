package com.garudaone.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.garudaone.web.model.DerivativeReturnModel;
import com.garudaone.web.repository.DerivativeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WebOneController {

    public  static final Logger logger = LoggerFactory.getLogger(WebOneController.class);
    @Autowired
    public DerivativeRepository repo;

    @RequestMapping("/healthcheck")
    public String healthCheck()
    {
        final String AppCheck = "Application is up and running healthy";
        logger.info("Health Check is OK");
        return AppCheck;

    }
    @RequestMapping(value =  "/findStock/{symbol}",produces = "application/json")
    public String returnSymbol(@PathVariable("symbol") String symbol)
    {
        logger.info("Inside DerivativeReturm");
         List<DerivativeReturnModel> reslist =   repo.findSymbol(symbol);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String result = "";
        try {
             result = objectMapper.writeValueAsString(reslist);
        }
        catch (JsonProcessingException e)
        {
            e.printStackTrace();
        }
        return  result;
    }
}
