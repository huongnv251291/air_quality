package com.tohsoft.airquality.data.models.ambee;

import java.util.List;

/**
 * Created by Tho DD on 22-Apr-20.
 */
public class Data {

    /**
     * message : nearest places
     * page : 1
     * count : 1
     * stations : [{"Id":1848480,"NO2":17.07,"PM10":67.25,"PM25":34.48,"CO":1.4,"SO2":4.67,"OZONE":120.74,"AQI":216,"aqiInfo":"{\"category\": \"Very Unhealthy\", \"pollutant\": \"O3\", \"concentration\": 119}","updatedAt":"2020-04-16T09:24:45.000Z","countryCode":"CN","placeName":null,"postalCode":"137802","state":null,"city":null,"division":null,"lat":47.1575,"lng":120.256,"updateTs":"1566856340","distance":46.845412328413104}]
     */

    private String message;
    private int page;
    private int count;
    private List<StationsBean> stations;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<StationsBean> getStations() {
        return stations;
    }

    public void setStations(List<StationsBean> stations) {
        this.stations = stations;
    }

    public static class StationsBean {
        /**
         * Id : 1848480
         * NO2 : 17.07
         * PM10 : 67.25
         * PM25 : 34.48
         * CO : 1.4
         * SO2 : 4.67
         * OZONE : 120.74
         * AQI : 216
         * aqiInfo : {"category": "Very Unhealthy", "pollutant": "O3", "concentration": 119}
         * updatedAt : 2020-04-16T09:24:45.000Z
         * countryCode : CN
         * placeName : null
         * postalCode : 137802
         * state : null
         * city : null
         * division : null
         * lat : 47.1575
         * lng : 120.256
         * updateTs : 1566856340
         * distance : 46.845412328413104
         */

        private int Id;
        private double NO2;
        private double PM10;
        private double PM25;
        private double CO;
        private double SO2;
        private double OZONE;
        private int AQI;
        private String aqiInfo;
        private String updatedAt;
        private String countryCode;
        private String placeName;
        private String postalCode;
        private String state;
        private String city;
        private String division;
        private double lat;
        private double lng;
        private String updateTs;
        private double distance;

        public int getId() {
            return Id;
        }

        public void setId(int id) {
            Id = id;
        }

        public double getNO2() {
            return NO2;
        }

        public void setNO2(double NO2) {
            this.NO2 = NO2;
        }

        public double getPM10() {
            return PM10;
        }

        public void setPM10(double PM10) {
            this.PM10 = PM10;
        }

        public double getPM25() {
            return PM25;
        }

        public void setPM25(double PM25) {
            this.PM25 = PM25;
        }

        public double getCO() {
            return CO;
        }

        public void setCO(double CO) {
            this.CO = CO;
        }

        public double getSO2() {
            return SO2;
        }

        public void setSO2(double SO2) {
            this.SO2 = SO2;
        }

        public double getOZONE() {
            return OZONE;
        }

        public void setOZONE(double OZONE) {
            this.OZONE = OZONE;
        }

        public int getAQI() {
            return AQI;
        }

        public void setAQI(int AQI) {
            this.AQI = AQI;
        }

        public String getAqiInfo() {
            return aqiInfo;
        }

        public void setAqiInfo(String aqiInfo) {
            this.aqiInfo = aqiInfo;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getCountryCode() {
            return countryCode;
        }

        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
        }

        public String getPlaceName() {
            return placeName;
        }

        public void setPlaceName(String placeName) {
            this.placeName = placeName;
        }

        public String getPostalCode() {
            return postalCode;
        }

        public void setPostalCode(String postalCode) {
            this.postalCode = postalCode;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDivision() {
            return division;
        }

        public void setDivision(String division) {
            this.division = division;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }

        public String getUpdateTs() {
            return updateTs;
        }

        public void setUpdateTs(String updateTs) {
            this.updateTs = updateTs;
        }

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }
    }
}
