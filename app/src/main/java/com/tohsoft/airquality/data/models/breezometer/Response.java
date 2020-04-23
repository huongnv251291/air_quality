package com.tohsoft.airquality.data.models.breezometer;

public class Response<T> {

    /**
     * metadata : null
     * data : {"datetime":"2020-04-20T05:00:00Z","data_available":true,"indexes":{"baqi":{"display_name":"BreezoMeter AQI","aqi":30,"aqi_display":"30","color":"#FF8C00","category":"Low air quality","dominant_pollutant":"o3"},"tha_pcd":{"display_name":"AQI (TH)","aqi":156,"aqi_display":"156","color":"#FFA200","category":"Unhealthy air quality","dominant_pollutant":"o3"}},"pollutants":{"co":{"display_name":"CO","full_name":"Carbon monoxide","aqi_information":{"baqi":{"display_name":"BreezoMeter AQI","aqi":97,"aqi_display":"97","color":"#009E3A","category":"Excellent air quality"}},"concentration":{"value":402.04,"units":"ppb"},"sources_and_effects":{"sources":"Typically originates from incomplete combustion of carbon fuels, such as that which occurs in car engines and power plants.","effects":"When inhaled, carbon monoxide can prevent the blood from carrying oxygen. Exposure may cause dizziness, nausea and headaches. Exposure to extreme concentrations can lead to loss of consciousness."}},"no2":{"display_name":"NO2","full_name":"Nitrogen dioxide","aqi_information":{"baqi":{"display_name":"BreezoMeter AQI","aqi":99,"aqi_display":"99","color":"#009E3A","category":"Excellent air quality"}},"concentration":{"value":2.23,"units":"ppb"},"sources_and_effects":{"sources":"Main sources are fuel burning processes, such as those used in industry and transportation.","effects":"Exposure may cause increased bronchial reactivity in patients with asthma, lung function decline in patients with COPD, and increased risk of respiratory infections, especially in young children."}},"o3":{"display_name":"O3","full_name":"Ozone","aqi_information":{"baqi":{"display_name":"BreezoMeter AQI","aqi":30,"aqi_display":"30","color":"#FF8C00","category":"Low air quality"}},"concentration":{"value":98.33,"units":"ppb"},"sources_and_effects":{"sources":"Ozone is created in a chemical reaction between atmospheric oxygen, nitrogen oxides, carbon monoxide and organic compounds, in the presence of sunlight.","effects":"Ozone can irritate the airways and cause coughing, a burning sensation, wheezing and shortness of breath. Additionally, ozone is one of the major components of photochemical smog."}},"pm10":{"display_name":"PM10","full_name":"Inhalable particulate matter (<10µm)","aqi_information":{"baqi":{"display_name":"BreezoMeter AQI","aqi":78,"aqi_display":"78","color":"#84CF33","category":"Good air quality"}},"concentration":{"value":25.02,"units":"ug/m3"},"sources_and_effects":{"sources":"Main sources are combustion processes (e.g. indoor heating, wildfires), mechanical processes (e.g. construction, mineral dust, agriculture) and biological particles (e.g. pollen, bacteria, mold).","effects":"Inhalable particles can penetrate into the lungs. Short term exposure can cause irritation of the airways, coughing, and aggravation of heart and lung diseases, expressed as difficulty breathing, heart attacks and even premature death."}},"pm25":{"display_name":"PM2.5","full_name":"Fine particulate matter (<2.5µm)","aqi_information":{"baqi":{"display_name":"BreezoMeter AQI","aqi":51,"aqi_display":"51","color":"#FFFF00","category":"Moderate air quality"}},"concentration":{"value":33.4,"units":"ug/m3"},"sources_and_effects":{"sources":"Main sources are combustion processes (e.g. power plants, indoor heating, car exhausts, wildfires), mechanical processes (e.g. construction, mineral dust) and biological particles (e.g. bacteria, viruses).","effects":"Fine particles can penetrate into the lungs and bloodstream. Short term exposure can cause irritation of the airways, coughing and aggravation of heart and lung diseases, expressed as difficulty breathing, heart attacks and even premature death."}},"so2":{"display_name":"SO2","full_name":"Sulfur dioxide","aqi_information":{"baqi":{"display_name":"BreezoMeter AQI","aqi":98,"aqi_display":"98","color":"#009E3A","category":"Excellent air quality"}},"concentration":{"value":2.53,"units":"ppb"},"sources_and_effects":{"sources":"Main sources are burning processes of sulfur-containing fuel in industry, transportation and power plants.","effects":"Exposure causes irritation of the respiratory tract, coughing and generates local inflammatory reactions. These in turn, may cause aggravation of lung diseases, even with short term exposure."}}},"health_recommendations":{"general_population":"Reduce the intensity of your outdoor activities or postpone them to the early morning when ozone levels tend to be lower. In addition, consider reducing the time you spend near busy roads, construction sites, open fires and other sources of smoke.","elderly":"Avoid intense activities outdoors or postpone them to the early morning when ozone levels tend to be lower. In addition, it is recommended to reduce the time you are near busy roads, construction sites, open fires and other sources of smoke. Staying indoors with an activated air filtration system would be best for your long term health.","lung_diseases":"Avoid intense activities outdoors or postpone them to the early morning when ozone levels tend to be lower. Keep relevant medication(s) available and consult a doctor with any questions. In addition, it is recommended to reduce the time you are near busy roads, open fires and other sources of smoke. Staying indoors with an activated air filtration system would be best for your long term health.","heart_diseases":"Reduce the intensity of your outdoor activities or postpone them to the early morning when ozone levels tend to be lower. Keep relevant medication(s) available and consult a doctor with any questions. It is recommended to limit the time you are near busy roads, construction sites, open fires and other sources of smoke. In addition, consider reducing the time you spend near industrial emission stacks. Staying indoors with an activated air filtration system would be best for your long term health.","active":"Avoid intense activities outdoors or postpone them to the early morning when ozone levels tend to be lower. In addition, it is recommended to reduce the time you are near busy roads, construction sites, open fires and other sources of smoke. Staying indoors with an activated air filtration system would be best for your long term health.","pregnant_women":"To keep you and your baby healthy, avoid intense activities outdoors or postpone them to the early morning when ozone levels tend to be lower. In addition, it is recommended to reduce the time you are near busy roads, construction sites, open fires and other sources of smoke. Staying indoors with an activated air filtration system would be best for your long term health.","children":"Avoid intense activities outdoors or postpone them to the early morning when ozone levels tend to be lower. In addition, it is recommended to reduce the time you are near busy roads, construction sites, open fires and other sources of smoke. Staying indoors with an activated air filtration system would be best for your long term health."}}
     * error : null
     */

    private String metadata;
    private T data;
    private String error;

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
