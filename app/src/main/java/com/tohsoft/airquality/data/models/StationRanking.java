
package com.tohsoft.airquality.data.models;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class StationRanking {

    @SerializedName("a")
    private String mA;
    @SerializedName("g")
    private List<Double> mG;
    @SerializedName("n")
    private String mN;
    @SerializedName("t")
    private String mT;
    @SerializedName("u")
    private String mU;
    @SerializedName("x")
    private String mX;

    public String getA() {
        return mA;
    }

    public void setA(String a) {
        mA = a;
    }

    public List<Double> getG() {
        return mG;
    }

    public void setG(List<Double> g) {
        mG = g;
    }

    public String getN() {
        return mN;
    }

    public void setN(String n) {
        mN = n;
    }

    public String getT() {
        return mT;
    }

    public void setT(String t) {
        mT = t;
    }

    public String getU() {
        return mU;
    }

    public void setU(String u) {
        mU = u;
    }

    public String getX() {
        return mX;
    }

    public void setX(String x) {
        mX = x;
    }

    @Override
    public String toString() {
        return "AQI: " + mA + "\n" +
                "Location: " + mG +
                "Address: " + mN + "\n" ;

    }
}
