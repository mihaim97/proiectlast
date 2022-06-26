package com.ubb.ro.proiect1.dto.sessiongrade;

public class StatisticsDTO {

    private int totalStudents;

    private int totalPass;

    private int totalFail;

    private float promoRate;

    public StatisticsDTO() {
    }

    public StatisticsDTO(int totalStudents, int totalPass, int totalFail) {
        this.totalStudents = totalStudents;
        this.totalPass = totalPass;
        this.totalFail = totalFail;
    }

    public int getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(int totalStudents) {
        this.totalStudents = totalStudents;
    }

    public int getTotalPass() {
        return totalPass;
    }

    public void setTotalPass(int totalPass) {
        this.totalPass = totalPass;
    }

    public int getTotalFail() {
        return totalFail;
    }

    public void setTotalFail(int totalFail) {
        this.totalFail = totalFail;
    }

    public float getPromoRate() {
        return promoRate;
    }

    public void setPromoRate(float promoRate) {
        this.promoRate = promoRate;
    }
}
