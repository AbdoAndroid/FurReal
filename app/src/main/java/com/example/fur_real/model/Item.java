package com.example.fur_real.model;

public class Item {
    private String id,_name,price,category;
    private int image;


    //region constructors
    public Item(String _name, String price, String category, int image) {
        this._name = _name;
        this.price = price;
        this.image = image;
        this.category=category;
    }

    public Item(String id, String _name, String price, String category, int image) {
        this.id = id;
        this._name = _name;
        this.price = price;
        this.category = category;
        this.image = image;
    }

    //endregion

    //region setters and getter

    public String get_name() {
        return _name;
    }

    public String getPrice() {
        return price;
    }

    public int getImage() {
        return image;
    }

    public String getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    //endregion



}
