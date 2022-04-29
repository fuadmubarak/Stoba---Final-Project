package com.google.finalstobaapp;

public class Item {
    private String name, cat, desc, amount, barcode;

    public Item() {
    }

    public Item(String name, String cat, String desc, String amount, String barcode) {
        this.name = name;
        this.cat = cat;
        this.desc = desc;
        this.amount = amount;
        this.barcode = barcode; }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
}