package com.ubb.ro.proiect1.service.classEntity;

public class ClassEntityDTOPercentage {

    public int id;

    private float percentage;

    public ClassEntityDTOPercentage(int id, float percentage) {
        this.id = id;
        this.percentage = percentage;
    }

    public ClassEntityDTOPercentage() {
        this.id = id;
        this.percentage = percentage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }
}
