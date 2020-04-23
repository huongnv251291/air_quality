package com.tohsoft.airquality.data.models.iqair;

import java.util.List;

public class Data {

    /**
     * city : Seoul
     * state : Seoul
     * country : South Korea
     * location : {"type":"Point","coordinates":[126.975961,37.564639]}
     * current : {"weather":{"ts":"2020-04-20T08:00:00.000Z","tp":11,"pr":1008,"hu":66,"ws":4.6,"wd":210,"ic":"04d"},"pollution":{"ts":"2020-04-20T08:00:00.000Z","aqius":68,"mainus":"p2","aqicn":39,"maincn":"p1"}}
     */

    private String city;
    private String state;
    private String country;
    private Location location;
    private Current current;
    private List<Current.Weather> forecasts;
    private List<Current.Weather> forecasts_daily;
    private History history;
    private Unit units;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    public List<Current.Weather> getForecasts() {
        return forecasts;
    }

    public void setForecasts(List<Current.Weather> forecasts) {
        this.forecasts = forecasts;
    }

    public List<Current.Weather> getForecasts_daily() {
        return forecasts_daily;
    }

    public void setForecasts_daily(List<Current.Weather> forecasts_daily) {
        this.forecasts_daily = forecasts_daily;
    }

    public History getHistory() {
        return history;
    }

    public void setHistory(History history) {
        this.history = history;
    }

    public Unit getUnits() {
        return units;
    }

    public void setUnits(Unit units) {
        this.units = units;
    }

    public static class Location {
        /**
         * type : Point
         * coordinates : [126.975961,37.564639]
         */

        private String type;
        private List<Double> coordinates;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<Double> getCoordinates() {
            return coordinates;
        }

        public void setCoordinates(List<Double> coordinates) {
            this.coordinates = coordinates;
        }
    }

    public static class Current {
        /**
         * weather : {"ts":"2020-04-20T08:00:00.000Z","tp":11,"pr":1008,"hu":66,"ws":4.6,"wd":210,"ic":"04d"}
         * pollution : {"ts":"2020-04-20T08:00:00.000Z","aqius":68,"mainus":"p2","aqicn":39,"maincn":"p1"}
         */

        private Weather weather;
        private Pollution pollution;
        /**
         * conc : 7.11
         * aqius : 30
         * aqicn : 11
         * ts : 2020-04-21T09:00:00.000Z
         * tp : 15
         * tp_min : 15
         * pr : 1016
         * hu : 66
         * ws : 1
         * wd : 157
         * ic : 01n
         */

        private double conc;
        private int aqius;
        private int aqicn;
        private String ts;
        private int tp;
        private int tp_min;
        private int pr;
        private int hu;
        private int ws;
        private int wd;
        private String ic;

        public Weather getWeather() {
            return weather;
        }

        public void setWeather(Weather weather) {
            this.weather = weather;
        }

        public Pollution getPollution() {
            return pollution;
        }

        public void setPollution(Pollution pollution) {
            this.pollution = pollution;
        }

        public double getConc() {
            return conc;
        }

        public void setConc(double conc) {
            this.conc = conc;
        }

        public int getAqius() {
            return aqius;
        }

        public void setAqius(int aqius) {
            this.aqius = aqius;
        }

        public int getAqicn() {
            return aqicn;
        }

        public void setAqicn(int aqicn) {
            this.aqicn = aqicn;
        }

        public String getTs() {
            return ts;
        }

        public void setTs(String ts) {
            this.ts = ts;
        }

        public int getTp() {
            return tp;
        }

        public void setTp(int tp) {
            this.tp = tp;
        }

        public int getTp_min() {
            return tp_min;
        }

        public void setTp_min(int tp_min) {
            this.tp_min = tp_min;
        }

        public int getPr() {
            return pr;
        }

        public void setPr(int pr) {
            this.pr = pr;
        }

        public int getHu() {
            return hu;
        }

        public void setHu(int hu) {
            this.hu = hu;
        }

        public int getWs() {
            return ws;
        }

        public void setWs(int ws) {
            this.ws = ws;
        }

        public int getWd() {
            return wd;
        }

        public void setWd(int wd) {
            this.wd = wd;
        }

        public String getIc() {
            return ic;
        }

        public void setIc(String ic) {
            this.ic = ic;
        }

        public static class Weather {
            /**
             * ts : 2020-04-20T08:00:00.000Z
             * tp : 11
             * pr : 1008
             * hu : 66
             * ws : 4.6
             * wd : 210
             * ic : 04d
             */

            private String ts;
            private int tp;
            private int pr;
            private int hu;
            private double ws;
            private int wd;
            private String ic;
            private Double conc;
            private int aqius;
            private int aqicn;

            public String getTs() {
                return ts;
            }

            public void setTs(String ts) {
                this.ts = ts;
            }

            public int getTp() {
                return tp;
            }

            public void setTp(int tp) {
                this.tp = tp;
            }

            public int getPr() {
                return pr;
            }

            public void setPr(int pr) {
                this.pr = pr;
            }

            public int getHu() {
                return hu;
            }

            public void setHu(int hu) {
                this.hu = hu;
            }

            public double getWs() {
                return ws;
            }

            public void setWs(double ws) {
                this.ws = ws;
            }

            public int getWd() {
                return wd;
            }

            public void setWd(int wd) {
                this.wd = wd;
            }

            public String getIc() {
                return ic;
            }

            public void setIc(String ic) {
                this.ic = ic;
            }

            public Double getConc() {
                return conc;
            }

            public void setConc(Double conc) {
                this.conc = conc;
            }

            public int getAqius() {
                return aqius;
            }

            public void setAqius(int aqius) {
                this.aqius = aqius;
            }

            public int getAqicn() {
                return aqicn;
            }

            public void setAqicn(int aqicn) {
                this.aqicn = aqicn;
            }
        }

        public static class Pollution {
            /**
             * ts : 2020-04-20T08:00:00.000Z
             * aqius : 68
             * mainus : p2
             * aqicn : 39
             * maincn : p1
             */

            private String ts;
            private int aqius;
            private String mainus;
            private int aqicn;
            private String maincn;
            private PollutionDetail s2;
            private PollutionDetail n2;
            private PollutionDetail o3;
            private PollutionDetail co;
            private PollutionDetail p2;
            private PollutionDetail p1;

            public String getTs() {
                return ts;
            }

            public void setTs(String ts) {
                this.ts = ts;
            }

            public int getAqius() {
                return aqius;
            }

            public void setAqius(int aqius) {
                this.aqius = aqius;
            }

            public String getMainus() {
                return mainus;
            }

            public void setMainus(String mainus) {
                this.mainus = mainus;
            }

            public int getAqicn() {
                return aqicn;
            }

            public void setAqicn(int aqicn) {
                this.aqicn = aqicn;
            }

            public String getMaincn() {
                return maincn;
            }

            public void setMaincn(String maincn) {
                this.maincn = maincn;
            }

            public PollutionDetail getS2() {
                return s2;
            }

            public void setS2(PollutionDetail s2) {
                this.s2 = s2;
            }

            public PollutionDetail getN2() {
                return n2;
            }

            public void setN2(PollutionDetail n2) {
                this.n2 = n2;
            }

            public PollutionDetail getO3() {
                return o3;
            }

            public void setO3(PollutionDetail o3) {
                this.o3 = o3;
            }

            public PollutionDetail getCo() {
                return co;
            }

            public void setCo(PollutionDetail co) {
                this.co = co;
            }

            public PollutionDetail getP2() {
                return p2;
            }

            public void setP2(PollutionDetail p2) {
                this.p2 = p2;
            }

            public PollutionDetail getP1() {
                return p1;
            }

            public void setP1(PollutionDetail p1) {
                this.p1 = p1;
            }
        }

    }

    public static class History {
        /**
         * weather : {"ts":"2020-04-20T08:00:00.000Z","tp":11,"pr":1008,"hu":66,"ws":4.6,"wd":210,"ic":"04d"}
         * pollution : {"ts":"2020-04-20T08:00:00.000Z","aqius":68,"mainus":"p2","aqicn":39,"maincn":"p1"}
         */

        private List<Current.Weather> weather;
        private List<Current.Pollution> pollution;

        /**
         * conc : 7.11
         * aqius : 30
         * aqicn : 11
         * ts : 2020-04-21T09:00:00.000Z
         * tp : 15
         * tp_min : 15
         * pr : 1016
         * hu : 66
         * ws : 1
         * wd : 157
         * ic : 01n
         */

        public List<Current.Weather> getWeather() {
            return weather;
        }

        public void setWeather(List<Current.Weather> weather) {
            this.weather = weather;
        }

        public List<Current.Pollution> getPollution() {
            return pollution;
        }

        public void setPollution(List<Current.Pollution> pollution) {
            this.pollution = pollution;
        }
    }

    public static class Unit {

        /**
         * s2 : ugm3
         * n2 : ugm3
         * o3 : ugm3
         * co : ugm3
         * p2 : ugm3
         * p1 : ugm3
         */

        private String s2;
        private String n2;
        private String o3;
        private String co;
        private String p2;
        private String p1;

        public String getS2() {
            return s2;
        }

        public void setS2(String s2) {
            this.s2 = s2;
        }

        public String getN2() {
            return n2;
        }

        public void setN2(String n2) {
            this.n2 = n2;
        }

        public String getO3() {
            return o3;
        }

        public void setO3(String o3) {
            this.o3 = o3;
        }

        public String getCo() {
            return co;
        }

        public void setCo(String co) {
            this.co = co;
        }

        public String getP2() {
            return p2;
        }

        public void setP2(String p2) {
            this.p2 = p2;
        }

        public String getP1() {
            return p1;
        }

        public void setP1(String p1) {
            this.p1 = p1;
        }
    }

    public static class PollutionDetail {

        /**
         * conc : 5.8
         * aqius : 24
         * aqicn : 8
         */

        private double conc;
        private int aqius;
        private int aqicn;

        public double getConc() {
            return conc;
        }

        public void setConc(double conc) {
            this.conc = conc;
        }

        public int getAqius() {
            return aqius;
        }

        public void setAqius(int aqius) {
            this.aqius = aqius;
        }

        public int getAqicn() {
            return aqicn;
        }

        public void setAqicn(int aqicn) {
            this.aqicn = aqicn;
        }
    }
}
