package com.tsoft.bot.frontend.models;

public class Cabin {

    String type;
    int n;
    int adults;
    int children;

    public Cabin(String type, int adults, int children) {
        this.type = type;
        this.n = adults + children ;
        this.adults = adults;
        this.children = children;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getAdults() {
        return adults;
    }

    public void setAdults(int adults) {
        this.adults = adults;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }
}
