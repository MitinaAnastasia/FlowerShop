package com.example.FlowerShop.entity;

public class Additive {
    private Long additiveId;
    private String additiveName;

    public Additive() {
    }

    public Additive(Long additiveId, String additiveName) {
        this.additiveId = additiveId;
        this.additiveName = additiveName;
    }

    public Long getAdditiveId() {
        return additiveId;
    }

    public void setAdditiveId(Long additiveId) {
        this.additiveId = additiveId;
    }

    public String getAdditiveName() {
        return additiveName;
    }

    public void setAdditiveName(String additiveName) {
        this.additiveName = additiveName;
    }

    @Override
    public String toString() {
        return "Additive{" +
                "additiveId=" + additiveId +
                ", additiveName='" + additiveName + '\'' +
                '}';
    }
}
