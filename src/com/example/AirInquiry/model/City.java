package com.example.AirInquiry.model;

/**
 * 城市
 * Created by user on 2016/6/2.
 */
public class City {
    private String name;
    private String code;

    @Override
    public String toString() {
        return name;
    }

    public City(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }
}
