package com.tohsoft.airquality.data.models;

import com.tohsoft.airquality.forecast.Decoder;
import com.tohsoft.airquality.forecast.Pair;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;

public class GraphModel {
    private ArrayList<Pair<Integer, Double>> _data;
    private Date _date;
    private int _hoffset;
    private int _hstart;
    private String _polID;


    public static GraphModel get(JSONObject aqiData, JSONObject graphData) {
        long hourOffset = 0;
        try {
            long utime = aqiData.getLong("time");
            if (aqiData.has("xtime")) {
                hourOffset = (utime - aqiData.getLong("xtime")) / 3600;
                if (hourOffset < 0) {
                    hourOffset = 0;
                }
            }
            String polID = graphData.getString("n");
            String data = graphData.getString("d");
            return new GraphModel(polID, Decoder.decode(data), new Date((3600 + utime) * 1000), (int) hourOffset);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public GraphModel(String polID, ArrayList<Pair<Integer, Double>> data, Date date, int hoffset) {
        this._data = data;
        this._hoffset = hoffset;
        this._polID = polID;
        this._date = date;
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        this._hstart = calendar.get(Calendar.HOUR_OF_DAY);
    }

    public String polID() {
        return this._polID;
    }

    public int duration() {
        int d = 0;
        if (this._data == null) {
            return 0;
        }
        Iterator<Pair<Integer, Double>> it = this._data.iterator();
        while (it.hasNext()) {
            d = it.next().getLeft();
        }
        return d;
    }

    public ArrayList<Pair<Integer, Double>> data() {
        return this._data;
    }

    public int hOffset() {
        return this._hoffset;
    }

    public int hStart() {
        return this._hstart;
    }

    public String getDay(int hour) {
        Date d = new Date();
        d.setTime(this._date.getTime() - ((long) (3600000 * hour)));
        return new SimpleDateFormat("EEE").format(d);
    }
}