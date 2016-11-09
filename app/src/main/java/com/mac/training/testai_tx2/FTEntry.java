package com.mac.training.testai_tx2;

/**
 * Created by Fernando on 11/7/2016.
 */

public class FTEntry {
    private String key;
    private String name;
    private String content;
    private String image;
    private String parentKey;
    private int rank;
    private String kW1;
    private String kW2;
    private String kW3;
    private String kW4;

    public FTEntry() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getkW1() {
        return kW1;
    }

    public void setkW1(String kW1) {
        this.kW1 = kW1;
    }

    public String getkW2() {
        return kW2;
    }

    public void setkW2(String kW2) {
        this.kW2 = kW2;
    }

    public String getkW3() {
        return kW3;
    }

    public void setkW3(String kW3) {
        this.kW3 = kW3;
    }

    public String getkW4() {
        return kW4;
    }

    public void setkW4(String kW4) {
        this.kW4 = kW4;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentKey() {
        return parentKey;
    }

    public void setParentKey(String parentKey) {
        this.parentKey = parentKey;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
