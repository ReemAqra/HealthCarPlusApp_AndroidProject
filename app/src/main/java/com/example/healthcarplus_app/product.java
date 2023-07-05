package com.example.healthcarplus_app;

public class product {

    private String pImage;
    private String pName;
    private String pPrice;
    private String pDescription;
    private String pNumber;

    public String getpImage() {
        return pImage;
    }

    public void setpImage(String pImage) {
        this.pImage = pImage;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpPrice() {
        return pPrice;
    }

    public void setpPrice(String pPrice) {
        this.pPrice = pPrice;
    }

    public String getpDescription() {
        return pDescription;
    }

    public void setpDescription(String pDescription) {
        this.pDescription = pDescription;
    }

    public String getpNumber() {
        return pNumber;
    }

    public void setpNumber(String pNumber) {
        this.pNumber = pNumber;
    }

    public product(String pImage, String pName, String pPrice, String pDescription, String pNumber) {
        this.pImage = pImage;
        this.pName = pName;
        this.pPrice = pPrice;
        this.pDescription = pDescription;
        this.pNumber = pNumber;
    }
}

