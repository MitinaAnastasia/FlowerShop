package com.example.FlowerShop.entity;

public class AdditiveFlower {
    private Long additiveIdFk;
    private Long flowerIdFk;

    public AdditiveFlower() {
    }

    public AdditiveFlower(Long additiveIdFk, Long flowerIdFk) {
        this.additiveIdFk = additiveIdFk;
        this.flowerIdFk = flowerIdFk;
    }

    public Long getAdditiveIdFk() {
        return additiveIdFk;
    }

    public void setAdditiveIdFk(Long additiveIdFk) {
        this.additiveIdFk = additiveIdFk;
    }

    public Long getFlowerIdFk() {
        return flowerIdFk;
    }

    public void setFlowerIdFk(Long flowerIdFk) {
        this.flowerIdFk = flowerIdFk;
    }

    @Override
    public String toString() {
        return "AdditiveFlower{" +
                "additiveIdFk=" + additiveIdFk +
                ", flowerIdFk=" + flowerIdFk +
                '}';
    }
}
