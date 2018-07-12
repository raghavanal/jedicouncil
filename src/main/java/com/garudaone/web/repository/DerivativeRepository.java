package com.garudaone.web.repository;

import com.garudaone.web.WebOneController;
import com.garudaone.web.model.DerivativeReturnModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class DerivativeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private DataSource dataSource;

    private String sql = "SELECT * FROM t_nse_derivatives WHERE symbol = ?";
    public  static final Logger logger = LoggerFactory.getLogger(DerivativeRepository.class);

    public List<DerivativeReturnModel> findSymbol(String symbol)
    {
        logger.info("Before jdbctemplate");
        jdbcTemplate.setDataSource(dataSource);
        logger.info("After jdbctemplate");

        List<DerivativeReturnModel> returnModels = new ArrayList<DerivativeReturnModel>();
        List<Map<String,Object>> rows = jdbcTemplate.queryForList(sql, new Object[] { symbol });
        for (Map row : rows)
        {
            DerivativeReturnModel model = new DerivativeReturnModel();
            model.setSymbol((String)row.get("Symbol"));
            logger.info("Symbol :"+ symbol);
            model.setTradeDate((Date)row.get("Date"));
            model.setOpenPrice((Double)row.get("open"));
            model.setHighPrice((Double)row.get("high"));
            model.setLowPrice((Double)row.get("low"));
            model.setClosePrice((Double)row.get("close"));
            model.setVolume((Integer)row.get("Volume"));
            model.setOpenInterest((Integer)row.get("OpenInterest"));
            returnModels.add(model);
        }
        return  returnModels;
    }
}
