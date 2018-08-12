package com.garudaone.web.repository;

import com.garudaone.web.WebOneController;
import com.garudaone.web.model.DerivativeReturnModel;
import com.garudaone.web.model.SymbolMaster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.*;

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
        jdbcTemplate.setDataSource(dataSource);
        List<DerivativeReturnModel> returnModels = new ArrayList<DerivativeReturnModel>();
        List<Map<String,Object>> rows = jdbcTemplate.queryForList(sql, new Object[] { symbol });
        for (Map row : rows)
        {
            DerivativeReturnModel model = new DerivativeReturnModel();
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

    @Cacheable("symbolmaster")
    public List<SymbolMaster> getSymbolMaster()
    {

        jdbcTemplate.setDataSource(dataSource);
  //      String symbolsql = "SELECT distinct regexp_substr(symbol,'^[A-Z\\.\\&\\/]+') symbol from t_nse_derivatives";
        String symbolsql = "SELECT distinct symbol from t_nse_derivatives";
        List<Map<String,Object>> symbolrows = jdbcTemplate.queryForList(symbolsql);
        List<SymbolMaster> result = new ArrayList<SymbolMaster>();
        for(Map row : symbolrows) {
            SymbolMaster master = new SymbolMaster();
            master.setSymbol((String) row.get("symbol"));
            result.add(master);
        }
        return  result;
    }

    public ArrayList getContractsforSymbol(String symbol)
    {
        String symbolsql = "SELECT distinct symbol from t_nse_derivatives where symbol like '"+symbol+"%'";
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("symbol",symbol + "%");
        ArrayList result = new ArrayList();

        List<Map<String,Object>> rows = jdbcTemplate.queryForList(symbolsql);
        for(Map row : rows) {
            SymbolMaster master = new SymbolMaster();
            master.setSymbol((String) row.get("symbol"));
             result.add((String) row.get("symbol"));
        }
        return  result;

    }
}
