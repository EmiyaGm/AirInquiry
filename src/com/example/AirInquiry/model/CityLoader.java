package com.example.AirInquiry.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gm on 2016/6/4.
 */
public class CityLoader {
    private  List cities = new ArrayList<City>();
    public List<City> load(InputStream is) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        String line;
        while ((line = reader.readLine()) != null) {
            parse(line);
        }
        reader.close();
        return cities;
    }

    private void parse(String line) {
        String[] fields = line.split(",");
        if (fields.length < 4) {
            City c = new City(fields[1],fields[0]);
            cities.add(c);
        }
    }



}
