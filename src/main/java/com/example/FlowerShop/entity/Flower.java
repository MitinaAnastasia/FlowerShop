package com.example.FlowerShop.entity;

public class Flower {
    private Long flowerId;
    private String name;
    private double cost;
    private int height;
    private String description;
    private int periodInWater;
    private Long seasonIdFk;
    private Long providerIdFk;

    public Flower() {
    }

    public Flower(Long flowerId, String name, double cost, int height, String description, int periodInWater, Long seasonIdFk, Long providerIdFk) {
        this.flowerId = flowerId;
        this.name = name;
        this.cost = cost;
        this.height = height;
        this.description = description;
        this.periodInWater = periodInWater;
        this.seasonIdFk = seasonIdFk;
        this.providerIdFk = providerIdFk;
    }

    public Long getFlowerId() {
        return flowerId;
    }

    public void setFlowerId(Long flowerId) {
        this.flowerId = flowerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPeriodInWater() {
        return periodInWater;
    }

    public void setPeriodInWater(int periodInWater) {
        this.periodInWater = periodInWater;
    }

    public Long getSeasonIdFk() {
        return seasonIdFk;
    }

    public void setSeasonIdFk(Long seasonIdFk) {
        this.seasonIdFk = seasonIdFk;
    }

    public Long getProviderIdFk() {
        return providerIdFk;
    }

    public void setProviderIdFk(Long providerIdFk) {
        this.providerIdFk = providerIdFk;
    }

    @Override
    public String toString() {
        return "Flower{" +
                "flowerId=" + flowerId +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", height=" + height +
                ", description='" + description + '\'' +
                ", periodInWater=" + periodInWater +
                ", seasonIdFk=" + seasonIdFk +
                ", providerIdFk=" + providerIdFk +
                '}';
    }
}
