package com.example.FlowerShop.entity;

public class Provider {
    private Long providerId;
    private String providerName;
    private String providerAddress;
    private String providerPhone;

    public Provider() {
    }

    public Provider(Long providerId, String providerName, String providerAddress, String providerPhone) {
        this.providerId = providerId;
        this.providerName = providerName;
        this.providerAddress = providerAddress;
        this.providerPhone = providerPhone;
    }

    public Long getProviderId() {
        return providerId;
    }

    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getProviderAddress() {
        return providerAddress;
    }

    public void setProviderAddress(String providerAddress) {
        this.providerAddress = providerAddress;
    }

    public String getProviderPhone() {
        return providerPhone;
    }

    public void setProviderPhone(String providerPhone) {
        this.providerPhone = providerPhone;
    }

    @Override
    public String toString() {
        return "Provider{" +
                "providerId=" + providerId +
                ", providerName='" + providerName + '\'' +
                ", providerAddress='" + providerAddress + '\'' +
                ", providerPhone='" + providerPhone + '\'' +
                '}';
    }
}
