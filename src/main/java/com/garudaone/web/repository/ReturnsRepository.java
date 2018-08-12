package com.garudaone.web.repository;

import com.garudaone.web.model.ReturnsCalculationModel;
import com.garudaone.web.model.ReturnsResultSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class ReturnsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private DataSource dataSource;
    private String sql = "CALL sp_calculate_eod_returns(?,?,?)";
    public  static final Logger logger = LoggerFactory.getLogger(ReturnsRepository.class);

    public ReturnsResultSet getReturnCalculation(String symbol,String fromDate, String toDate)
    {
        logger.info("Setting the datasource");
        jdbcTemplate.setDataSource(dataSource);
        logger.info("Calling the Stored Procedure and returning a resultset");
        List<ReturnsCalculationModel> returncalc = new ArrayList<ReturnsCalculationModel>();
       ReturnsResultSet returnsResultSet = new ReturnsResultSet();
        List<Map<String,Object>> rows = jdbcTemplate.queryForList(sql, new Object[] { symbol, fromDate,toDate });
        logger.info("Resultset Generated");
        for(Map row :rows)
        {
            ReturnsCalculationModel model = new ReturnsCalculationModel();
            model.setDate((Date)row.get("Date"));
            model.setClosePrice((Double) row.get("close"));
            model.setVolume((Integer) row.get("volume"));
            model.setOpenInterest((Integer)row.get("openInterest"));
            model.setPrevClose((Double)row.get("price_prev"));
            model.setReturns((Double)row.get("Returns"));
            model.setOiChange((BigDecimal)row.get("OIChange"));
            model.setVolumeChange((BigDecimal)row.get("VolumeChange"));
            returncalc.add(model);
        }
        returnsResultSet.setSymbol(symbol);
        returnsResultSet.setReturns(returncalc);
    return returnsResultSet;
    }

}
