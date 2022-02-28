package com.app.cms.model;

public class Service {

    private String id;
    private String name;
    private double priceRate;
    private String category;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPriceRate() {
        return priceRate;
    }

    public void setPriceRate(double priceRate) {
        this.priceRate = priceRate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
