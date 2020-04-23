package com.tohsoft.airquality.data.models.breezometer;

public class HealthRecommend {

    /**
     * general_population : Reduce the intensity of your outdoor activities or postpone them to the early morning when ozone levels tend to be lower. In addition, consider reducing the time you spend near busy roads, construction sites, open fires and other sources of smoke.
     * elderly : Avoid intense activities outdoors or postpone them to the early morning when ozone levels tend to be lower. In addition, it is recommended to reduce the time you are near busy roads, construction sites, open fires and other sources of smoke. Staying indoors with an activated air filtration system would be best for your long term health.
     * lung_diseases : Avoid intense activities outdoors or postpone them to the early morning when ozone levels tend to be lower. Keep relevant medication(s) available and consult a doctor with any questions. In addition, it is recommended to reduce the time you are near busy roads, open fires and other sources of smoke. Staying indoors with an activated air filtration system would be best for your long term health.
     * heart_diseases : Reduce the intensity of your outdoor activities or postpone them to the early morning when ozone levels tend to be lower. Keep relevant medication(s) available and consult a doctor with any questions. It is recommended to limit the time you are near busy roads, construction sites, open fires and other sources of smoke. In addition, consider reducing the time you spend near industrial emission stacks. Staying indoors with an activated air filtration system would be best for your long term health.
     * active : Avoid intense activities outdoors or postpone them to the early morning when ozone levels tend to be lower. In addition, it is recommended to reduce the time you are near busy roads, construction sites, open fires and other sources of smoke. Staying indoors with an activated air filtration system would be best for your long term health.
     * pregnant_women : To keep you and your baby healthy, avoid intense activities outdoors or postpone them to the early morning when ozone levels tend to be lower. In addition, it is recommended to reduce the time you are near busy roads, construction sites, open fires and other sources of smoke. Staying indoors with an activated air filtration system would be best for your long term health.
     * children : Avoid intense activities outdoors or postpone them to the early morning when ozone levels tend to be lower. In addition, it is recommended to reduce the time you are near busy roads, construction sites, open fires and other sources of smoke. Staying indoors with an activated air filtration system would be best for your long term health.
     */

    private String general_population;
    private String elderly;
    private String lung_diseases;
    private String heart_diseases;
    private String active;
    private String pregnant_women;
    private String children;

    public String getGeneral_population() {
        return general_population;
    }

    public void setGeneral_population(String general_population) {
        this.general_population = general_population;
    }

    public String getElderly() {
        return elderly;
    }

    public void setElderly(String elderly) {
        this.elderly = elderly;
    }

    public String getLung_diseases() {
        return lung_diseases;
    }

    public void setLung_diseases(String lung_diseases) {
        this.lung_diseases = lung_diseases;
    }

    public String getHeart_diseases() {
        return heart_diseases;
    }

    public void setHeart_diseases(String heart_diseases) {
        this.heart_diseases = heart_diseases;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getPregnant_women() {
        return pregnant_women;
    }

    public void setPregnant_women(String pregnant_women) {
        this.pregnant_women = pregnant_women;
    }

    public String getChildren() {
        return children;
    }

    public void setChildren(String children) {
        this.children = children;
    }
}
