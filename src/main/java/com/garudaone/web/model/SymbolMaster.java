package com.garudaone.web.model;

import com.google.gson.annotations.SerializedName;

public class SymbolMaster {
    private String symbol;
    @SerializedName("symbols")
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
    