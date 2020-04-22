package com.tohsoft.airquality.forecast;

import android.annotation.SuppressLint;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

public class ForecastModel {
    private static final String TAG = "[ForecastModel] ";
    public static final int TYPE_AD = 3;
    public static final int TYPE_DATE = 1;
    public static final int TYPE_INFO = 2;
    public static final int TYPE_MAX_COUNT = 4;
    public static final int TYPE_RECORD = 0;
    boolean hasAQI = false;
    ArrayList<ForecastListItem> list = new ArrayList<>();
    int pday = -888;
    private ArrayList<ForecastRecord> records = null;

    public static abstract class ClickCallback {
        public abstract void onClick();
    }

    public ForecastModel() {

    }

    public void addSeparator(Date date) {
        this.list.add(new ForecastListItem(date));
    }

    public void addRecord(ForecastRecord record) {
        Date date = new Date(record.utime());
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int day = cal.get(Calendar.DATE);
        if (day != this.pday) {
            this.pday = day;
            addSeparator(date);
        }
        this.list.add(new ForecastListItem(record));
    }

    public void setup(String url, ClickCallback cb) {
        this.list.add(new ForecastListItem(url, cb));
        float mw = 1.0f;
        Iterator<ForecastListItem> it = this.list.iterator();
        while (it.hasNext()) {
            ForecastListItem r = it.next();
            if (r.isRecord()) {
                if (mw < r.record.mw) {
                    mw = r.record.mw;
                }
                this.hasAQI |= r.record.hasAQI;
            }
        }
        Iterator<ForecastListItem> it2 = this.list.iterator();
        while (it2.hasNext()) {
            ForecastListItem r2 = it2.next();
            if (r2.isRecord()) {
                r2.record.mw = mw;
            }
        }
    }

    public ArrayList<ForecastListItem> entries() {
        return this.list;
    }

    public boolean hasAQI() {
        return this.hasAQI;
    }

    public static ForecastModel decode(JSONObject forecast) {
        try {
            ForecastModel model2 = new ForecastModel();
            int ft = forecast.getInt("t");
            if (forecast.has("a")) {
                JSONObject aqi = forecast.getJSONObject("a");
                Iterator<String> keys = aqi.keys();
                while (keys.hasNext()) {
                    String p = keys.next();
                    JSONArray a = aqi.getJSONArray(p);
                    model2.add(p + ".low", Decoder.decode(a.getString(0), 3, ft, 3600));
                    if (a.length() > 1) {
                        model2.add(p + ".high", Decoder.decode(a.getString(1), 3, ft, 3600));
                    }
                }
            }
            JSONObject w = forecast.getJSONObject("w");
            Iterator<String> keys2 = w.keys();
            while (keys2.hasNext()) {
                String p2 = keys2.next();
                model2.add(p2, Decoder.decode(w.getString(p2), 1, ft, 3600));
            }
            return model2;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void add(String item, ArrayList<Pair<Integer, Double>> list2) {
        if (this.records == null) {
            this.records = new ArrayList<>();
            Iterator<Pair<Integer, Double>> it = list2.iterator();
            while (it.hasNext()) {
                ForecastRecord record = new ForecastRecord(it.next().getLeft().intValue());
                addRecord(record);
                this.records.add(record);
            }
        }
        Iterator<ForecastRecord> itr = this.records.iterator();
        ForecastRecord c = itr.next();
        ForecastRecord n = itr.next();
        Iterator<Pair<Integer, Double>> itr2 = list2.iterator();
        while (itr2.hasNext()) {
            Pair<Integer, Double> e = itr2.next();
            int t = e.getLeft().intValue();
            while (n != null && n.ut <= ((long) t)) {
                c = n;
                n = itr.hasNext() ? itr.next() : null;
            }
            if (c != null) {
                c.set(item, e.getRight().doubleValue());
            }
        }
    }

    public static class ForecastRecord {
        public int aqi1;
        public int aqi2;
        public ArrayList<Float> at = new ArrayList<>(3);
        public ArrayList<Float> awg = new ArrayList<>(3);
        public ArrayList<Float> aws = new ArrayList<>(3);
        public float h;
        public boolean hasAQI;
        public float mw;
        public float t;
        public long ut;
        public float wd;
        public float wg;
        public float ws;

        public ForecastRecord(int t2) {
            this.ut = (long) t2;
        }

        public long utime() {
            return this.ut * 1000;
        }

        public void set(String p, double v) {
            if (p.compareTo("ws") == 0) {
                this.ws = (float) v;
                this.mw = Math.max(this.mw, this.ws);
                this.aws.add(Float.valueOf(this.ws));
            } else if (p.compareTo("wg") == 0) {
                this.wg = (float) v;
                this.mw = Math.max(this.mw, this.wg);
                this.awg.add(Float.valueOf(this.wg));
            } else if (p.compareTo("t") == 0) {
                this.t = (float) v;
                this.at.add(Float.valueOf(this.t));
            } else if (p.compareTo("wd") == 0) {
                this.wd = (float) v;
            } else if (p.compareTo("h") == 0) {
                this.h = (float) v;
            } else if (p.compareTo("pm25.low") == 0) {
                this.aqi1 = (int) v;
                this.aqi2 = (int) v;
                this.hasAQI = true;
            } else if (p.compareTo("pm25.high") == 0) {
                this.aqi2 = (int) v;
                this.hasAQI = true;
            }
        }

        public void setMaxWind(float mw2) {
            this.mw = mw2;
        }

        public float ws() {
            return this.ws;
        }

        public float wg() {
            return this.wg;
        }

        public float wd() {
            return this.wd;
        }

        public float mw() {
            return this.mw;
        }

        public float t() {
            if (this.at.size() == 0) {
                return -273.0f;
            }
            if (this.at.size() == 1) {
                return this.t;
            }
            if (this.at.size() != 3) {
                return this.t;
            }
            float t1 = this.at.get(0).floatValue();
            float t2 = this.at.get(1).floatValue();
            float t3 = this.at.get(2).floatValue();
            if ((t1 <= t2 || t2 <= t3) && (t1 >= t2 || t2 >= t3)) {
                return t2;
            }
            return ((t1 + t2) + t3) / 3.0f;
        }

        @Override
        public String toString() {
            @SuppressLint("SimpleDateFormat") String main = new SimpleDateFormat("HH:mm").format(new Date(utime()));
            return main + "   " + "PM25 Low :" + aqi1 + "  " + "PM25 high :" + aqi2 + "  " + "Nhiệt độ :" + t;

        }

        public int getAqi1() {
            return this.aqi1;
        }

        public int getAqi2() {
            return this.aqi2;
        }
    }

    public class ForecastListItem {
        public ClickCallback callback;
        public Date date;
        public ForecastRecord record;
        int type = 0;
        public String url;

        ForecastListItem(ForecastRecord r) {
            this.record = r;
        }

        ForecastListItem(Date d) {
            this.date = d;
        }

        ForecastListItem(String _url, ClickCallback _callback) {
            this.callback = _callback;
            this.url = _url;
        }

        public int getType() {
            return this.type;
        }

        public boolean isRecord() {
            return this.type == 0;
        }
    }
}