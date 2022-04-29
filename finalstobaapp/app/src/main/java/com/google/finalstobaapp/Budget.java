package com.google.finalstobaapp;

public class Budget {
    private String cat,nombudget;


    public Budget() {
    }

    public Budget(String nombudget, String cat) {
        this.nombudget = nombudget;
        this.cat = cat;
    }

    public String getNombudget() {
        return nombudget;
    }

    public void setNombudget(String nombudget) {
        this.nombudget = nombudget;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }
}
