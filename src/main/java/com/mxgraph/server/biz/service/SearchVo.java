package com.mxgraph.server.biz.service;


import java.io.Serializable;

/**
 * @author coral
 */
public class SearchVo implements Serializable {

    private String startDate;

    private String endDate;

    private int pageNumber;

    private int pageSize;

    private String cardType;

    private String number;

    private int start;

    public int getStart() {
        return (pageNumber-1)*pageSize;
    }

    public void setParam(){
        setStartDate(this.startDate+=" 00:00:00");
        setEndDate(this.endDate+=" 23:59:59");
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setStart(int start) {
        this.start = start;
    }
}
