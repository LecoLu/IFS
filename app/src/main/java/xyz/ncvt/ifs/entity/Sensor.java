package xyz.ncvt.ifs.entity;

public class Sensor {

    private int co2,airHumidity,airTemperature,soilMoisturs,soilTemperature,light;


    public Sensor() {

    }
    public int getCo2() {
        return co2;
    }

    public void setCo2(int co2) {
        this.co2 = co2;
    }

    public int getAirHumidity() {
        return airHumidity;
    }

    public void setAirHumidity(int airHumidity) {
        this.airHumidity = airHumidity;
    }

    public int getAirTemperature() {
        return airTemperature;
    }

    public void setAirTemperature(int airTemperature) {
        this.airTemperature = airTemperature;
    }

    public int getSoilMoisturs() {
        return soilMoisturs;
    }

    public void setSoilMoisturs(int soilMoisturs) {
        this.soilMoisturs = soilMoisturs;
    }

    public int getSoilTemperature() {
        return soilTemperature;
    }

    public void setSoilTemperature(int soilTemperature) {
        this.soilTemperature = soilTemperature;
    }

    public int getLight() {
        return light;
    }

    public void setLight(int light) {
        this.light = light;
    }


}
