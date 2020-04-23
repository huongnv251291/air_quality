package com.tohsoft.airquality.data.models.breezometer;

public class Pollutant {

    /**
     * co : {"display_name":"CO","full_name":"Carbon monoxide","aqi_information":{"baqi":{"display_name":"BreezoMeter AQI","aqi":97,"aqi_display":"97","color":"#009E3A","category":"Excellent air quality"}},"concentration":{"value":402.04,"units":"ppb"},"sources_and_effects":{"sources":"Typically originates from incomplete combustion of carbon fuels, such as that which occurs in car engines and power plants.","effects":"When inhaled, carbon monoxide can prevent the blood from carrying oxygen. Exposure may cause dizziness, nausea and headaches. Exposure to extreme concentrations can lead to loss of consciousness."}}
     * no2 : {"display_name":"NO2","full_name":"Nitrogen dioxide","aqi_information":{"baqi":{"display_name":"BreezoMeter AQI","aqi":99,"aqi_display":"99","color":"#009E3A","category":"Excellent air quality"}},"concentration":{"value":2.23,"units":"ppb"},"sources_and_effects":{"sources":"Main sources are fuel burning processes, such as those used in industry and transportation.","effects":"Exposure may cause increased bronchial reactivity in patients with asthma, lung function decline in patients with COPD, and increased risk of respiratory infections, especially in young children."}}
     * o3 : {"display_name":"O3","full_name":"Ozone","aqi_information":{"baqi":{"display_name":"BreezoMeter AQI","aqi":30,"aqi_display":"30","color":"#FF8C00","category":"Low air quality"}},"concentration":{"value":98.33,"units":"ppb"},"sources_and_effects":{"sources":"Ozone is created in a chemical reaction between atmospheric oxygen, nitrogen oxides, carbon monoxide and organic compounds, in the presence of sunlight.","effects":"Ozone can irritate the airways and cause coughing, a burning sensation, wheezing and shortness of breath. Additionally, ozone is one of the major components of photochemical smog."}}
     * pm10 : {"display_name":"PM10","full_name":"Inhalable particulate matter (<10µm)","aqi_information":{"baqi":{"display_name":"BreezoMeter AQI","aqi":78,"aqi_display":"78","color":"#84CF33","category":"Good air quality"}},"concentration":{"value":25.02,"units":"ug/m3"},"sources_and_effects":{"sources":"Main sources are combustion processes (e.g. indoor heating, wildfires), mechanical processes (e.g. construction, mineral dust, agriculture) and biological particles (e.g. pollen, bacteria, mold).","effects":"Inhalable particles can penetrate into the lungs. Short term exposure can cause irritation of the airways, coughing, and aggravation of heart and lung diseases, expressed as difficulty breathing, heart attacks and even premature death."}}
     * pm25 : {"display_name":"PM2.5","full_name":"Fine particulate matter (<2.5µm)","aqi_information":{"baqi":{"display_name":"BreezoMeter AQI","aqi":51,"aqi_display":"51","color":"#FFFF00","category":"Moderate air quality"}},"concentration":{"value":33.4,"units":"ug/m3"},"sources_and_effects":{"sources":"Main sources are combustion processes (e.g. power plants, indoor heating, car exhausts, wildfires), mechanical processes (e.g. construction, mineral dust) and biological particles (e.g. bacteria, viruses).","effects":"Fine particles can penetrate into the lungs and bloodstream. Short term exposure can cause irritation of the airways, coughing and aggravation of heart and lung diseases, expressed as difficulty breathing, heart attacks and even premature death."}}
     * so2 : {"display_name":"SO2","full_name":"Sulfur dioxide","aqi_information":{"baqi":{"display_name":"BreezoMeter AQI","aqi":98,"aqi_display":"98","color":"#009E3A","category":"Excellent air quality"}},"concentration":{"value":2.53,"units":"ppb"},"sources_and_effects":{"sources":"Main sources are burning processes of sulfur-containing fuel in industry, transportation and power plants.","effects":"Exposure causes irritation of the respiratory tract, coughing and generates local inflammatory reactions. These in turn, may cause aggravation of lung diseases, even with short term exposure."}}
     */

    private CoBean co;
    private No2Bean no2;
    private O3Bean o3;
    private Pm10Bean pm10;
    private Pm25Bean pm25;
    private So2Bean so2;

    public CoBean getCo() {
        return co;
    }

    public void setCo(CoBean co) {
        this.co = co;
    }

    public No2Bean getNo2() {
        return no2;
    }

    public void setNo2(No2Bean no2) {
        this.no2 = no2;
    }

    public O3Bean getO3() {
        return o3;
    }

    public void setO3(O3Bean o3) {
        this.o3 = o3;
    }

    public Pm10Bean getPm10() {
        return pm10;
    }

    public void setPm10(Pm10Bean pm10) {
        this.pm10 = pm10;
    }

    public Pm25Bean getPm25() {
        return pm25;
    }

    public void setPm25(Pm25Bean pm25) {
        this.pm25 = pm25;
    }

    public So2Bean getSo2() {
        return so2;
    }

    public void setSo2(So2Bean so2) {
        this.so2 = so2;
    }

    public static class CoBean {
        /**
         * display_name : CO
         * full_name : Carbon monoxide
         * aqi_information : {"baqi":{"display_name":"BreezoMeter AQI","aqi":97,"aqi_display":"97","color":"#009E3A","category":"Excellent air quality"}}
         * concentration : {"value":402.04,"units":"ppb"}
         * sources_and_effects : {"sources":"Typically originates from incomplete combustion of carbon fuels, such as that which occurs in car engines and power plants.","effects":"When inhaled, carbon monoxide can prevent the blood from carrying oxygen. Exposure may cause dizziness, nausea and headaches. Exposure to extreme concentrations can lead to loss of consciousness."}
         */

        private String display_name;
        private String full_name;
        private AqiInformationBean aqi_information;
        private ConcentrationBean concentration;
        private SourcesAndEffectsBean sources_and_effects;

        public String getDisplay_name() {
            return display_name;
        }

        public void setDisplay_name(String display_name) {
            this.display_name = display_name;
        }

        public String getFull_name() {
            return full_name;
        }

        public void setFull_name(String full_name) {
            this.full_name = full_name;
        }

        public AqiInformationBean getAqi_information() {
            return aqi_information;
        }

        public void setAqi_information(AqiInformationBean aqi_information) {
            this.aqi_information = aqi_information;
        }

        public ConcentrationBean getConcentration() {
            return concentration;
        }

        public void setConcentration(ConcentrationBean concentration) {
            this.concentration = concentration;
        }

        public SourcesAndEffectsBean getSources_and_effects() {
            return sources_and_effects;
        }

        public void setSources_and_effects(SourcesAndEffectsBean sources_and_effects) {
            this.sources_and_effects = sources_and_effects;
        }

        public static class AqiInformationBean {
            /**
             * baqi : {"display_name":"BreezoMeter AQI","aqi":97,"aqi_display":"97","color":"#009E3A","category":"Excellent air quality"}
             */

            private BaqiBean baqi;

            public BaqiBean getBaqi() {
                return baqi;
            }

            public void setBaqi(BaqiBean baqi) {
                this.baqi = baqi;
            }

            public static class BaqiBean {
                /**
                 * display_name : BreezoMeter AQI
                 * aqi : 97
                 * aqi_display : 97
                 * color : #009E3A
                 * category : Excellent air quality
                 */

                private String display_name;
                private int aqi;
                private String aqi_display;
                private String color;
                private String category;

                public String getDisplay_name() {
                    return display_name;
                }

                public void setDisplay_name(String display_name) {
                    this.display_name = display_name;
                }

                public int getAqi() {
                    return aqi;
                }

                public void setAqi(int aqi) {
                    this.aqi = aqi;
                }

                public String getAqi_display() {
                    return aqi_display;
                }

                public void setAqi_display(String aqi_display) {
                    this.aqi_display = aqi_display;
                }

                public String getColor() {
                    return color;
                }

                public void setColor(String color) {
                    this.color = color;
                }

                public String getCategory() {
                    return category;
                }

                public void setCategory(String category) {
                    this.category = category;
                }
            }
        }

        public static class ConcentrationBean {
            /**
             * value : 402.04
             * units : ppb
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

        public static class SourcesAndEffectsBean {
            /**
             * sources : Typically originates from incomplete combustion of carbon fuels, such as that which occurs in car engines and power plants.
             * effects : When inhaled, carbon monoxide can prevent the blood from carrying oxygen. Exposure may cause dizziness, nausea and headaches. Exposure to extreme concentrations can lead to loss of consciousness.
             */

            private String sources;
            private String effects;

            public String getSources() {
                return sources;
            }

            public void setSources(String sources) {
                this.sources = sources;
            }

            public String getEffects() {
                return effects;
            }

            public void setEffects(String effects) {
                this.effects = effects;
            }
        }
    }

    public static class No2Bean {
        /**
         * display_name : NO2
         * full_name : Nitrogen dioxide
         * aqi_information : {"baqi":{"display_name":"BreezoMeter AQI","aqi":99,"aqi_display":"99","color":"#009E3A","category":"Excellent air quality"}}
         * concentration : {"value":2.23,"units":"ppb"}
         * sources_and_effects : {"sources":"Main sources are fuel burning processes, such as those used in industry and transportation.","effects":"Exposure may cause increased bronchial reactivity in patients with asthma, lung function decline in patients with COPD, and increased risk of respiratory infections, especially in young children."}
         */

        private String display_name;
        private String full_name;
        private AqiInformationBeanX aqi_information;
        private ConcentrationBeanX concentration;
        private SourcesAndEffectsBeanX sources_and_effects;

        public String getDisplay_name() {
            return display_name;
        }

        public void setDisplay_name(String display_name) {
            this.display_name = display_name;
        }

        public String getFull_name() {
            return full_name;
        }

        public void setFull_name(String full_name) {
            this.full_name = full_name;
        }

        public AqiInformationBeanX getAqi_information() {
            return aqi_information;
        }

        public void setAqi_information(AqiInformationBeanX aqi_information) {
            this.aqi_information = aqi_information;
        }

        public ConcentrationBeanX getConcentration() {
            return concentration;
        }

        public void setConcentration(ConcentrationBeanX concentration) {
            this.concentration = concentration;
        }

        public SourcesAndEffectsBeanX getSources_and_effects() {
            return sources_and_effects;
        }

        public void setSources_and_effects(SourcesAndEffectsBeanX sources_and_effects) {
            this.sources_and_effects = sources_and_effects;
        }

        public static class AqiInformationBeanX {
            /**
             * baqi : {"display_name":"BreezoMeter AQI","aqi":99,"aqi_display":"99","color":"#009E3A","category":"Excellent air quality"}
             */

            private BaqiBeanX baqi;

            public BaqiBeanX getBaqi() {
                return baqi;
            }

            public void setBaqi(BaqiBeanX baqi) {
                this.baqi = baqi;
            }

            public static class BaqiBeanX {
                /**
                 * display_name : BreezoMeter AQI
                 * aqi : 99
                 * aqi_display : 99
                 * color : #009E3A
                 * category : Excellent air quality
                 */

                private String display_name;
                private int aqi;
                private String aqi_display;
                private String color;
                private String category;

                public String getDisplay_name() {
                    return display_name;
                }

                public void setDisplay_name(String display_name) {
                    this.display_name = display_name;
                }

                public int getAqi() {
                    return aqi;
                }

                public void setAqi(int aqi) {
                    this.aqi = aqi;
                }

                public String getAqi_display() {
                    return aqi_display;
                }

                public void setAqi_display(String aqi_display) {
                    this.aqi_display = aqi_display;
                }

                public String getColor() {
                    return color;
                }

                public void setColor(String color) {
                    this.color = color;
                }

                public String getCategory() {
                    return category;
                }

                public void setCategory(String category) {
                    this.category = category;
                }
            }
        }

        public static class ConcentrationBeanX {
            /**
             * value : 2.23
             * units : ppb
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

        public static class SourcesAndEffectsBeanX {
            /**
             * sources : Main sources are fuel burning processes, such as those used in industry and transportation.
             * effects : Exposure may cause increased bronchial reactivity in patients with asthma, lung function decline in patients with COPD, and increased risk of respiratory infections, especially in young children.
             */

            private String sources;
            private String effects;

            public String getSources() {
                return sources;
            }

            public void setSources(String sources) {
                this.sources = sources;
            }

            public String getEffects() {
                return effects;
            }

            public void setEffects(String effects) {
                this.effects = effects;
            }
        }
    }

    public static class O3Bean {
        /**
         * display_name : O3
         * full_name : Ozone
         * aqi_information : {"baqi":{"display_name":"BreezoMeter AQI","aqi":30,"aqi_display":"30","color":"#FF8C00","category":"Low air quality"}}
         * concentration : {"value":98.33,"units":"ppb"}
         * sources_and_effects : {"sources":"Ozone is created in a chemical reaction between atmospheric oxygen, nitrogen oxides, carbon monoxide and organic compounds, in the presence of sunlight.","effects":"Ozone can irritate the airways and cause coughing, a burning sensation, wheezing and shortness of breath. Additionally, ozone is one of the major components of photochemical smog."}
         */

        private String display_name;
        private String full_name;
        private AqiInformationBeanXX aqi_information;
        private ConcentrationBeanXX concentration;
        private SourcesAndEffectsBeanXX sources_and_effects;

        public String getDisplay_name() {
            return display_name;
        }

        public void setDisplay_name(String display_name) {
            this.display_name = display_name;
        }

        public String getFull_name() {
            return full_name;
        }

        public void setFull_name(String full_name) {
            this.full_name = full_name;
        }

        public AqiInformationBeanXX getAqi_information() {
            return aqi_information;
        }

        public void setAqi_information(AqiInformationBeanXX aqi_information) {
            this.aqi_information = aqi_information;
        }

        public ConcentrationBeanXX getConcentration() {
            return concentration;
        }

        public void setConcentration(ConcentrationBeanXX concentration) {
            this.concentration = concentration;
        }

        public SourcesAndEffectsBeanXX getSources_and_effects() {
            return sources_and_effects;
        }

        public void setSources_and_effects(SourcesAndEffectsBeanXX sources_and_effects) {
            this.sources_and_effects = sources_and_effects;
        }

        public static class AqiInformationBeanXX {
            /**
             * baqi : {"display_name":"BreezoMeter AQI","aqi":30,"aqi_display":"30","color":"#FF8C00","category":"Low air quality"}
             */

            private BaqiBeanXX baqi;

            public BaqiBeanXX getBaqi() {
                return baqi;
            }

            public void setBaqi(BaqiBeanXX baqi) {
                this.baqi = baqi;
            }

            public static class BaqiBeanXX {
                /**
                 * display_name : BreezoMeter AQI
                 * aqi : 30
                 * aqi_display : 30
                 * color : #FF8C00
                 * category : Low air quality
                 */

                private String display_name;
                private int aqi;
                private String aqi_display;
                private String color;
                private String category;

                public String getDisplay_name() {
                    return display_name;
                }

                public void setDisplay_name(String display_name) {
                    this.display_name = display_name;
                }

                public int getAqi() {
                    return aqi;
                }

                public void setAqi(int aqi) {
                    this.aqi = aqi;
                }

                public String getAqi_display() {
                    return aqi_display;
                }

                public void setAqi_display(String aqi_display) {
                    this.aqi_display = aqi_display;
                }

                public String getColor() {
                    return color;
                }

                public void setColor(String color) {
                    this.color = color;
                }

                public String getCategory() {
                    return category;
                }

                public void setCategory(String category) {
                    this.category = category;
                }
            }
        }

        public static class ConcentrationBeanXX {
            /**
             * value : 98.33
             * units : ppb
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

        public static class SourcesAndEffectsBeanXX {
            /**
             * sources : Ozone is created in a chemical reaction between atmospheric oxygen, nitrogen oxides, carbon monoxide and organic compounds, in the presence of sunlight.
             * effects : Ozone can irritate the airways and cause coughing, a burning sensation, wheezing and shortness of breath. Additionally, ozone is one of the major components of photochemical smog.
             */

            private String sources;
            private String effects;

            public String getSources() {
                return sources;
            }

            public void setSources(String sources) {
                this.sources = sources;
            }

            public String getEffects() {
                return effects;
            }

            public void setEffects(String effects) {
                this.effects = effects;
            }
        }
    }

    public static class Pm10Bean {
        /**
         * display_name : PM10
         * full_name : Inhalable particulate matter (<10µm)
         * aqi_information : {"baqi":{"display_name":"BreezoMeter AQI","aqi":78,"aqi_display":"78","color":"#84CF33","category":"Good air quality"}}
         * concentration : {"value":25.02,"units":"ug/m3"}
         * sources_and_effects : {"sources":"Main sources are combustion processes (e.g. indoor heating, wildfires), mechanical processes (e.g. construction, mineral dust, agriculture) and biological particles (e.g. pollen, bacteria, mold).","effects":"Inhalable particles can penetrate into the lungs. Short term exposure can cause irritation of the airways, coughing, and aggravation of heart and lung diseases, expressed as difficulty breathing, heart attacks and even premature death."}
         */

        private String display_name;
        private String full_name;
        private AqiInformationBeanXXX aqi_information;
        private ConcentrationBeanXXX concentration;
        private SourcesAndEffectsBeanXXX sources_and_effects;

        public String getDisplay_name() {
            return display_name;
        }

        public void setDisplay_name(String display_name) {
            this.display_name = display_name;
        }

        public String getFull_name() {
            return full_name;
        }

        public void setFull_name(String full_name) {
            this.full_name = full_name;
        }

        public AqiInformationBeanXXX getAqi_information() {
            return aqi_information;
        }

        public void setAqi_information(AqiInformationBeanXXX aqi_information) {
            this.aqi_information = aqi_information;
        }

        public ConcentrationBeanXXX getConcentration() {
            return concentration;
        }

        public void setConcentration(ConcentrationBeanXXX concentration) {
            this.concentration = concentration;
        }

        public SourcesAndEffectsBeanXXX getSources_and_effects() {
            return sources_and_effects;
        }

        public void setSources_and_effects(SourcesAndEffectsBeanXXX sources_and_effects) {
            this.sources_and_effects = sources_and_effects;
        }

        public static class AqiInformationBeanXXX {
            /**
             * baqi : {"display_name":"BreezoMeter AQI","aqi":78,"aqi_display":"78","color":"#84CF33","category":"Good air quality"}
             */

            private BaqiBeanXXX baqi;

            public BaqiBeanXXX getBaqi() {
                return baqi;
            }

            public void setBaqi(BaqiBeanXXX baqi) {
                this.baqi = baqi;
            }

            public static class BaqiBeanXXX {
                /**
                 * display_name : BreezoMeter AQI
                 * aqi : 78
                 * aqi_display : 78
                 * color : #84CF33
                 * category : Good air quality
                 */

                private String display_name;
                private int aqi;
                private String aqi_display;
                private String color;
                private String category;

                public String getDisplay_name() {
                    return display_name;
                }

                public void setDisplay_name(String display_name) {
                    this.display_name = display_name;
                }

                public int getAqi() {
                    return aqi;
                }

                public void setAqi(int aqi) {
                    this.aqi = aqi;
                }

                public String getAqi_display() {
                    return aqi_display;
                }

                public void setAqi_display(String aqi_display) {
                    this.aqi_display = aqi_display;
                }

                public String getColor() {
                    return color;
                }

                public void setColor(String color) {
                    this.color = color;
                }

                public String getCategory() {
                    return category;
                }

                public void setCategory(String category) {
                    this.category = category;
                }
            }
        }

        public static class ConcentrationBeanXXX {
            /**
             * value : 25.02
             * units : ug/m3
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

        public static class SourcesAndEffectsBeanXXX {
            /**
             * sources : Main sources are combustion processes (e.g. indoor heating, wildfires), mechanical processes (e.g. construction, mineral dust, agriculture) and biological particles (e.g. pollen, bacteria, mold).
             * effects : Inhalable particles can penetrate into the lungs. Short term exposure can cause irritation of the airways, coughing, and aggravation of heart and lung diseases, expressed as difficulty breathing, heart attacks and even premature death.
             */

            private String sources;
            private String effects;

            public String getSources() {
                return sources;
            }

            public void setSources(String sources) {
                this.sources = sources;
            }

            public String getEffects() {
                return effects;
            }

            public void setEffects(String effects) {
                this.effects = effects;
            }
        }
    }

    public static class Pm25Bean {
        /**
         * display_name : PM2.5
         * full_name : Fine particulate matter (<2.5µm)
         * aqi_information : {"baqi":{"display_name":"BreezoMeter AQI","aqi":51,"aqi_display":"51","color":"#FFFF00","category":"Moderate air quality"}}
         * concentration : {"value":33.4,"units":"ug/m3"}
         * sources_and_effects : {"sources":"Main sources are combustion processes (e.g. power plants, indoor heating, car exhausts, wildfires), mechanical processes (e.g. construction, mineral dust) and biological particles (e.g. bacteria, viruses).","effects":"Fine particles can penetrate into the lungs and bloodstream. Short term exposure can cause irritation of the airways, coughing and aggravation of heart and lung diseases, expressed as difficulty breathing, heart attacks and even premature death."}
         */

        private String display_name;
        private String full_name;
        private AqiInformationBeanXXXX aqi_information;
        private ConcentrationBeanXXXX concentration;
        private SourcesAndEffectsBeanXXXX sources_and_effects;

        public String getDisplay_name() {
            return display_name;
        }

        public void setDisplay_name(String display_name) {
            this.display_name = display_name;
        }

        public String getFull_name() {
            return full_name;
        }

        public void setFull_name(String full_name) {
            this.full_name = full_name;
        }

        public AqiInformationBeanXXXX getAqi_information() {
            return aqi_information;
        }

        public void setAqi_information(AqiInformationBeanXXXX aqi_information) {
            this.aqi_information = aqi_information;
        }

        public ConcentrationBeanXXXX getConcentration() {
            return concentration;
        }

        public void setConcentration(ConcentrationBeanXXXX concentration) {
            this.concentration = concentration;
        }

        public SourcesAndEffectsBeanXXXX getSources_and_effects() {
            return sources_and_effects;
        }

        public void setSources_and_effects(SourcesAndEffectsBeanXXXX sources_and_effects) {
            this.sources_and_effects = sources_and_effects;
        }

        public static class AqiInformationBeanXXXX {
            /**
             * baqi : {"display_name":"BreezoMeter AQI","aqi":51,"aqi_display":"51","color":"#FFFF00","category":"Moderate air quality"}
             */

            private BaqiBeanXXXX baqi;

            public BaqiBeanXXXX getBaqi() {
                return baqi;
            }

            public void setBaqi(BaqiBeanXXXX baqi) {
                this.baqi = baqi;
            }

            public static class BaqiBeanXXXX {
                /**
                 * display_name : BreezoMeter AQI
                 * aqi : 51
                 * aqi_display : 51
                 * color : #FFFF00
                 * category : Moderate air quality
                 */

                private String display_name;
                private int aqi;
                private String aqi_display;
                private String color;
                private String category;

                public String getDisplay_name() {
                    return display_name;
                }

                public void setDisplay_name(String display_name) {
                    this.display_name = display_name;
                }

                public int getAqi() {
                    return aqi;
                }

                public void setAqi(int aqi) {
                    this.aqi = aqi;
                }

                public String getAqi_display() {
                    return aqi_display;
                }

                public void setAqi_display(String aqi_display) {
                    this.aqi_display = aqi_display;
                }

                public String getColor() {
                    return color;
                }

                public void setColor(String color) {
                    this.color = color;
                }

                public String getCategory() {
                    return category;
                }

                public void setCategory(String category) {
                    this.category = category;
                }
            }
        }

        public static class ConcentrationBeanXXXX {
            /**
             * value : 33.4
             * units : ug/m3
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

        public static class SourcesAndEffectsBeanXXXX {
            /**
             * sources : Main sources are combustion processes (e.g. power plants, indoor heating, car exhausts, wildfires), mechanical processes (e.g. construction, mineral dust) and biological particles (e.g. bacteria, viruses).
             * effects : Fine particles can penetrate into the lungs and bloodstream. Short term exposure can cause irritation of the airways, coughing and aggravation of heart and lung diseases, expressed as difficulty breathing, heart attacks and even premature death.
             */

            private String sources;
            private String effects;

            public String getSources() {
                return sources;
            }

            public void setSources(String sources) {
                this.sources = sources;
            }

            public String getEffects() {
                return effects;
            }

            public void setEffects(String effects) {
                this.effects = effects;
            }
        }
    }

    public static class So2Bean {
        /**
         * display_name : SO2
         * full_name : Sulfur dioxide
         * aqi_information : {"baqi":{"display_name":"BreezoMeter AQI","aqi":98,"aqi_display":"98","color":"#009E3A","category":"Excellent air quality"}}
         * concentration : {"value":2.53,"units":"ppb"}
         * sources_and_effects : {"sources":"Main sources are burning processes of sulfur-containing fuel in industry, transportation and power plants.","effects":"Exposure causes irritation of the respiratory tract, coughing and generates local inflammatory reactions. These in turn, may cause aggravation of lung diseases, even with short term exposure."}
         */

        private String display_name;
        private String full_name;
        private AqiInformationBeanXXXXX aqi_information;
        private ConcentrationBeanXXXXX concentration;
        private SourcesAndEffectsBeanXXXXX sources_and_effects;

        public String getDisplay_name() {
            return display_name;
        }

        public void setDisplay_name(String display_name) {
            this.display_name = display_name;
        }

        public String getFull_name() {
            return full_name;
        }

        public void setFull_name(String full_name) {
            this.full_name = full_name;
        }

        public AqiInformationBeanXXXXX getAqi_information() {
            return aqi_information;
        }

        public void setAqi_information(AqiInformationBeanXXXXX aqi_information) {
            this.aqi_information = aqi_information;
        }

        public ConcentrationBeanXXXXX getConcentration() {
            return concentration;
        }

        public void setConcentration(ConcentrationBeanXXXXX concentration) {
            this.concentration = concentration;
        }

        public SourcesAndEffectsBeanXXXXX getSources_and_effects() {
            return sources_and_effects;
        }

        public void setSources_and_effects(SourcesAndEffectsBeanXXXXX sources_and_effects) {
            this.sources_and_effects = sources_and_effects;
        }

        public static class AqiInformationBeanXXXXX {
            /**
             * baqi : {"display_name":"BreezoMeter AQI","aqi":98,"aqi_display":"98","color":"#009E3A","category":"Excellent air quality"}
             */

            private BaqiBeanXXXXX baqi;

            public BaqiBeanXXXXX getBaqi() {
                return baqi;
            }

            public void setBaqi(BaqiBeanXXXXX baqi) {
                this.baqi = baqi;
            }

            public static class BaqiBeanXXXXX {
                /**
                 * display_name : BreezoMeter AQI
                 * aqi : 98
                 * aqi_display : 98
                 * color : #009E3A
                 * category : Excellent air quality
                 */

                private String display_name;
                private int aqi;
                private String aqi_display;
                private String color;
                private String category;

                public String getDisplay_name() {
                    return display_name;
                }

                public void setDisplay_name(String display_name) {
                    this.display_name = display_name;
                }

                public int getAqi() {
                    return aqi;
                }

                public void setAqi(int aqi) {
                    this.aqi = aqi;
                }

                public String getAqi_display() {
                    return aqi_display;
                }

                public void setAqi_display(String aqi_display) {
                    this.aqi_display = aqi_display;
                }

                public String getColor() {
                    return color;
                }

                public void setColor(String color) {
                    this.color = color;
                }

                public String getCategory() {
                    return category;
                }

                public void setCategory(String category) {
                    this.category = category;
                }
            }
        }

        public static class ConcentrationBeanXXXXX {
            /**
             * value : 2.53
             * units : ppb
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

        public static class SourcesAndEffectsBeanXXXXX {
            /**
             * sources : Main sources are burning processes of sulfur-containing fuel in industry, transportation and power plants.
             * effects : Exposure causes irritation of the respiratory tract, coughing and generates local inflammatory reactions. These in turn, may cause aggravation of lung diseases, even with short term exposure.
             */

            private String sources;
            private String effects;

            public String getSources() {
                return sources;
            }

            public void setSources(String sources) {
                this.sources = sources;
            }

            public String getEffects() {
                return effects;
            }

            public void setEffects(String effects) {
                this.effects = effects;
            }
        }
    }
}
