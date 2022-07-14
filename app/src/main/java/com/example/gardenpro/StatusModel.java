package com.example.gardenpro;

public class StatusModel {
    private String soilMoisture,temperature, humidity, waterLevel, waterTemperature, motorStatus, fishStatus;


    public StatusModel(String soilMoisture, String temperature, String humidity, String waterLevel, String waterTemperature, String motorStatus, String fishStatus) {
        this.soilMoisture = soilMoisture;
        this.temperature = temperature;
        this.humidity = humidity;
        this.waterLevel = waterLevel;
        this.waterTemperature = waterTemperature;
        this.motorStatus = motorStatus;
        this.fishStatus = fishStatus;
    }

    public StatusModel() {
    }

    public String getSoilMoisture() {
        return soilMoisture;
    }

    public void setSoilMoisture(String soilMoisture) {
        this.soilMoisture = soilMoisture;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWaterLevel() {
        return waterLevel;
    }

    public void setWaterLevel(String waterLevel) {
        this.waterLevel = waterLevel;
    }

    public String getWaterTemperature() {
        return waterTemperature;
    }

    public void setWaterTemperature(String waterTemperature) {
        this.waterTemperature = waterTemperature;
    }

    public String getMotorStatus() {
        return motorStatus;
    }

    public void setMotorStatus(String motorStatus) {
        this.motorStatus = motorStatus;
    }

    public String getFishStatus() {
        return fishStatus;
    }

    public void setFishStatus(String fishStatus) {
        this.fishStatus = fishStatus;
    }
}
