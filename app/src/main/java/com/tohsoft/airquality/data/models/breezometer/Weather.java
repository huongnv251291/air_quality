package com.tohsoft.airquality.data.models.breezometer;

/**
 * Created by Tho DD on 22-Apr-20.
 */
public class Weather {

    /**
     * datetime : 2020-04-22T04:00:00Z
     * is_day_time : true
     * icon_code : 10
     * weather_text : Mixed with some thunderstorm clouds possible
     * temperature : {"value":37.31,"units":"C"}
     * feels_like_temperature : {"value":41.59,"units":"C"}
     * relative_humidity : 40
     * precipitation : {"precipitation_probability":9,"total_precipitation":{"value":0,"units":"mm"}}
     * wind : {"speed":{"value":10.404,"units":"km/h"},"direction":209}
     * wind_gust : {"value":18.036,"units":"km/h"}
     * pressure : {"value":998.98,"units":"mb"}
     * visibility : {"value":24,"units":"km"}
     * dew_point : {"value":21.25,"units":"C"}
     * cloud_cover : 9
     */

    private String datetime;
    private boolean is_day_time;
    private int icon_code;
    private String weather_text;
    private Value temperature;
    private Value feels_like_temperature;
    private int relative_humidity;
    private Precipitation precipitation;
    private Wind wind;
    private Value wind_gust;
    private Value pressure;
    private Value visibility;
    private Value dew_point;
    private int cloud_cover;

    public static class Value {

        /**
         * value : 0.0
         * units : mm
         */

        private double value;
        private String units;

        public double getValue() {
            return value;
        }

        public void setValue(double value) {
            this.value = value;
        }

        public String getUnits() {
            return units;
        }

        public void setUnits(String units) {
            this.units = units;
        }
    }

    public static class Wind {

        /**
         * speed : {"value":10.404,"units":"km/h"}
         * direction : 209
         */

        private Value speed;
        private int direction;

        public Value getSpeed() {
            return speed;
        }

        public void setSpeed(Value speed) {
            this.speed = speed;
        }

        public int getDirection() {
            return direction;
        }

        public void setDirection(int direction) {
            this.direction = direction;
        }
    }

    public static class Precipitation {

        /**
         * precipitation_probability : 9
         * total_precipitation : {"value":0,"units":"mm"}
         */

        private int precipitation_probability;
        private Value total_precipitation;

        public int getPrecipitation_probability() {
            return precipitation_probability;
        }

        public void setPrecipitation_probability(int precipitation_probability) {
            this.precipitation_probability = precipitation_probability;
        }

        public Value getTotal_precipitation() {
            return total_precipitation;
        }

        public void setTotal_precipitation(Value total_precipitation) {
            this.total_precipitation = total_precipitation;
        }
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public boolean isIs_day_time() {
        return is_day_time;
    }

    public void setIs_day_time(boolean is_day_time) {
        this.is_day_time = is_day_time;
    }

    public int getIcon_code() {
        return icon_code;
    }

    public void setIcon_code(int icon_code) {
        this.icon_code = icon_code;
    }

    public String getWeather_text() {
        return weather_text;
    }

    public void setWeather_text(String weather_text) {
        this.weather_text = weather_text;
    }

    public Value getTemperature() {
        return temperature;
    }

    public void setTemperature(Value temperature) {
        this.temperature = temperature;
    }

    public Value getFeels_like_temperature() {
        return feels_like_temperature;
    }

    public void setFeels_like_temperature(Value feels_like_temperature) {
        this.feels_like_temperature = feels_like_temperature;
    }

    public int getRelative_humidity() {
        return relative_humidity;
    }

    public void setRelative_humidity(int relative_humidity) {
        this.relative_humidity = relative_humidity;
    }

    public Precipitation getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(Precipitation precipitation) {
        this.precipitation = precipitation;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Value getWind_gust() {
        return wind_gust;
    }

    public void setWind_gust(Value wind_gust) {
        this.wind_gust = wind_gust;
    }

    public Value getPressure() {
        return pressure;
    }

    public void setPressure(Value pressure) {
        this.pressure = pressure;
    }

    public Value getVisibility() {
        return visibility;
    }

    public void setVisibility(Value visibility) {
        this.visibility = visibility;
    }

    public Value getDew_point() {
        return dew_point;
    }

    public void setDew_point(Value dew_point) {
        this.dew_point = dew_point;
    }

    public int getCloud_cover() {
        return cloud_cover;
    }

    public void setCloud_cover(int cloud_cover) {
        this.cloud_cover = cloud_cover;
    }
}
