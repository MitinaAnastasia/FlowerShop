package com.example.FlowerShop.entity;

public class Season {
    private Long seasonId;
    private String seasonName;

    public Season() {
    }

    public Season(Long seasonId, String seasonName) {
        this.seasonId = seasonId;
        this.seasonName = seasonName;
    }

    public Long getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(Long seasonId) {
        this.seasonId = seasonId;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public void setSeasonName(String seasonName) {
        this.seasonName = seasonName;
    }

    @Override
    public String toString() {
        return "Season{" +
                "seasonId=" + seasonId +
                ", seasonName='" + seasonName + '\'' +
                '}';
    }
}
