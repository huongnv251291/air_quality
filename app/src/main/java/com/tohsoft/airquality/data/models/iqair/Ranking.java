package com.tohsoft.airquality.data.models.iqair;

/**
 * Created by Tho DD on 22-Apr-20.
 */
public class Ranking {

    /**
     * city : Chiang Mai
     * state : Chiang Mai
     * country : Thailand
     * ranking : {"current_aqi":131,"current_aqi_cn":66,"updated":"2020-04-22T08:00:00.000Z"}
     */

    private String city;
    private String state;
    private String country;
    private RankingBean ranking;

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

    public RankingBean getRanking() {
        return ranking;
    }

    public void setRanking(RankingBean ranking) {
        this.ranking = ranking;
    }

    public static class RankingBean {
        /**
         * current_aqi : 131
         * current_aqi_cn : 66
         * updated : 2020-04-22T08:00:00.000Z
         */

        private int current_aqi;
        private int current_aqi_cn;
        private String updated;

        public int getCurrent_aqi() {
            return current_aqi;
        }

        public void setCurrent_aqi(int current_aqi) {
            this.current_aqi = current_aqi;
        }

        public int getCurrent_aqi_cn() {
            return current_aqi_cn;
        }

        public void setCurrent_aqi_cn(int current_aqi_cn) {
            this.current_aqi_cn = current_aqi_cn;
        }

        public String getUpdated() {
            return updated;
        }

        public void setUpdated(String updated) {
            this.updated = updated;
        }
    }
}
