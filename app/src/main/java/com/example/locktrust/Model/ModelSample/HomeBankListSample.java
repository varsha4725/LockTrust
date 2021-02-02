package com.example.locktrust.Model.ModelSample;

public class HomeBankListSample {
    private int image;
    private String  Name;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public HomeBankListSample(int image, String name) {
        this.image = image;
        this.Name = name;
    }
}
