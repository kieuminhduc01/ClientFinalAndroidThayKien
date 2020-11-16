package com.example.prmfinal.Client.model;

public class Product {
    private String id;
    private String name;
    private float costOfGoodSold;
    private float salePrice;
    private String imgUrl;
    private String Descriptionl;
    private int quantiy;

    public Product() {
    }

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

    public float getCostOfGoodSold() {
        return costOfGoodSold;
    }

    public void setCostOfGoodSold(float costOfGoodSold) {
        this.costOfGoodSold = costOfGoodSold;
    }

    public float getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(float salePrice) {
        this.salePrice = salePrice;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDescriptionl() {
        return Descriptionl;
    }

    public void setDescriptionl(String descriptionl) {
        Descriptionl = descriptionl;
    }

    public int getQuantiy() {
        return quantiy;
    }

    public void setQuantiy(int quantiy) {
        this.quantiy = quantiy;
    }
}
