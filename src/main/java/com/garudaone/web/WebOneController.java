package com.garudaone.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.garudaone.web.model.DerivativeReturnModel;
import com.garudaone.web.model.ReturnsCalculationModel;
import com.garudaone.web.model.ReturnsResultSet;
import com.garudaone.web.model.SymbolMaster;
import com.garudaone.web.repository.DerivativeRepository;
import com.garudaone.web.repository.ReturnsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class WebOneController {

    public  static final Logger logger = LoggerFactory.getLogger(WebOneController.class);
    @Autowired
    public DerivativeRepository repo;
    @Autowired
    public ReturnsRepository returnsrepo;

    @CrossOrigin
    @RequestMapping("/healthcheck")
    public String healthCheck()
    {
        final String AppCheck = "Application is up and running healthy";
        logger.info("Health Check is OK");
        return AppCheck;

    }
    @CrossOrigin
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
    @CrossOrigin
    @RequestMapping(value =  "/getReturns/{symbol}/{fromDate}/{toDate}",produces = "application/json")
    public String getReturns(@PathVariable("symbol") String symbol, @PathVariable("fromDate") String fromDate, @PathVariable("toDate") String toDate) {
        logger.info("Inside Returns Controller");
        ReturnsResultSet mod = returnsrepo.getReturnCalculation(symbol,fromDate,toDate);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String result_returns = "";
        try {
            result_returns = objectMapper.writeValueAsString(mod);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result_returns;
    }
    @CrossOrigin
    @RequestMapping(value = "/getSymbolMaster",produces = "application/json")
    public String getSymbolmaster()
    {
        logger.info("Inside SymbolMaster");
        List<SymbolMaster> returnsymbols = repo.getSymbolMaster();
        ObjectMapper symbolmapper = new ObjectMapper();
        symbolmapper.enable(SerializationFeature.INDENT_OUTPUT);
        String returnsymboljson = "";
        try{
            returnsymboljson = symbolmapper.writeValueAsString(returnsymbols);
        }
        catch (JsonProcessingException e)
        {
            logger.error(e.getLocalizedMessage());
        }
        return  returnsymboljson;
    }

    @CrossOrigin
    @RequestMapping(value = "/getContract/{symbol}",produces = "application/json")
    public String getContract(@PathVariable("symbol") String symbol)
    {
        ArrayList returnsymbols = repo.getContractsforSymbol(symbol);
        ObjectMapper symbolmapper = new ObjectMapper();
        symbolmapper.enable(SerializationFeature.INDENT_OUTPUT);
        String returnsymboljson = "";
        try{
            returnsymboljson = symbolmapper.writeValueAsString(returnsymbols);
        }
        catch (JsonProcessingException e)
        {
            logger.error(e.getLocalizedMessage());
        }
        return  returnsymboljson;
    }
}
