package com.entities;

public class Itab {
    private int iId;
    private String iName;

    public int getiId() {
        return iId;
    }

    public void setiId(int iId) {
        this.iId = iId;
    }

    public String getiName() {
        return iName;
    }

    public void setiName(String iName) {
        this.iName = iName;
    }

    public Itab() {
    }

    public Itab(String iName) {
        this.iName = iName;
    }
}
