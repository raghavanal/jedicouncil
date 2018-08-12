package com.garudaone.web.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReturnsResultSet {

    private String symbol;
    @SerializedName("returns")
    private List<ReturnsCalculationModel> returns;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public  List<ReturnsCalculationModel> getReturns() {
        return returns;
    }

    public void setReturns( List<ReturnsCalculationModel> returns) {
        this.returns = returns;
    }
}
