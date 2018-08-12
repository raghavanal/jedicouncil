package com.garudaone.web.model;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.Date;

public class ReturnsCalculationModel {

    @SerializedName("resultset")
    private Date date;
    private Double closePrice;
    private int Volume;
    private int openInterest;
    private Double prevClose;
    private Double Returns;
    private BigDecimal oiChange;
    private BigDecimal volumeChange;


    public BigDecimal getOiChange() {
        return oiChange;
    }

    public void setOiChange(BigDecimal oiChange) {
        this.oiChange = oiChange;
    }

    public BigDecimal getVolumeChange() {
        return volumeChange;
    }

    public void setVolumeChange(BigDecimal volumeChange) {
        this.volumeChange = volumeChange;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(Double closePrice) {
        this.closePrice = closePrice;
    }

    public int getVolume() {
        return Volume;
    }

    public void setVolume(int volume) {
        Volume = volume;
    }

    public int getOpenInterest() {
        return openInterest;
    }

    public void setOpenInterest(int openInterest) {
        this.openInterest = openInterest;
    }

    public Double getPrevClose() {
        return prevClose;
    }

    public void setPrevClose(Double prevClose) {
        this.prevClose = prevClose;
    }

    public Double getReturns() {
        return Returns;
    }

    public void setReturns(Double returns) {
        Returns = returns;
    }

}
