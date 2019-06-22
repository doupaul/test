package com.entities;

public class BasePage {
    private int pageNum;           //当前页码
    private int pageBegin;
    private int pageSize = 5;      //每页显示条数
    private int pageTotal;         //总页数
    private int recordTotal;       //总记录数

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(int pageTotal) {
        this.pageTotal = pageTotal;
    }

    public int getRecordTotal() {
        return recordTotal;
    }

    public void setRecordTotal(int recordTotal) {
        this.recordTotal = recordTotal;
    }

    public int getPageBegin() {
        return pageBegin;
    }

    public void setPageBegin(int pageBegin) {
        this.pageBegin = pageBegin;
    }

    public BasePage() {
    }

    public BasePage(int pageNum, int pageBegin, int pageSize, int pageTotal, int recordTotal) {
        this.pageNum = pageNum;
        this.pageBegin = pageBegin;
        this.pageSize = pageSize;
        this.pageTotal = pageTotal;
        this.recordTotal = recordTotal;
    }
}
