package com.example.FlowerShop.entity;

public class Bouquet {
    private Long bouquetId;
    private String bouquetName;
    private int numberOfFlowers;
    private double cost;
    private Long flowerIdFk;

    public Bouquet() {
    }

    public Bouquet(Long bouquetId, String bouquetName, int numberOfFlowers, double cost, Long flowerIdFk) {
        this.bouquetId = bouquetId;
        this.bouquetName = bouquetName;
        this.numberOfFlowers = numberOfFlowers;
        this.cost = cost;
        this.flowerIdFk = flowerIdFk;
    }

    public Long getBouquetId() {
        return bouquetId;
    }

    public void setBouquetId(Long bouquetId) {
        this.bouquetId = bouquetId;
    }

    public String getBouquetName() {
        return bouquetName;
    }

    public void setBouquetName(String bouquetName) {
        this.bouquetName = bouquetName;
    }

    public int getNumberOfFlowers() {
        return numberOfFlowers;
    }

    public void setNumberOfFlowers(int numberOfFlowers) {
        this.numberOfFlowers = numberOfFlowers;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Long getFlowerIdFk() {
        return flowerIdFk;
    }

    public void setFlowerIdFk(Long flowerIdFk) {
        this.flowerIdFk = flowerIdFk;
    }

    @Override
    public String toString() {
        return "Bouquet{" +
                "bouquetId=" + bouquetId +
                ", bouquetName='" + bouquetName + '\'' +
                ", numberOfFlowers=" + numberOfFlowers +
                ", cost=" + cost +
                ", flowerIdFk=" + flowerIdFk +
                '}';
    }
}
