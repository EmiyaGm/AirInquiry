package com.example.AirInquiry.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 天气查询服务
 * Created by user on 2016/6/2.
 */
public class AirQueryService {
    private static final String BASE_URL =
            "http://www.pm25.in/api/querys/only_aqi.json?city=CITY_NAME&token=5j1znBVAsnSf5xQyNQyq";
    //private Air air;
    private List<Air> airList;


    /**
     * 查询城市天气
     * @param city 城市中文名称
     * @return 天气Weather
     * @throws IOException
     */
    public List<Air> query(String city) throws IOException, JSONException {
        //生成实际的url
        String url = BASE_URL.replaceAll("CITY_NAME", city);
        //连接
        HttpURLConnection c = (HttpURLConnection) new URL(url).openConnection();
        //读数据
        InputStream is = c.getInputStream();
        //jSON文本
        String jsonText = new TextReader().readText(is, "UTF-8");

        airList = parse(jsonText);

        return airList;
    }

    /**
     * 解析
     * @param jsonText JSON文本
     * @return 天气对象
     */
    private List<Air> parse(String jsonText) throws JSONException {
        List<Air> al = new ArrayList<Air>();
        JSONArray resultsArray = new JSONArray(jsonText);
        //JSONObject result0 = resultsArray.getJSONObject(0);
        //城市名称
        for(int i = 0 ;i<resultsArray.length();i++){
            Air air = new Air();
            air.setArea(resultsArray.getJSONObject(i).getString("area"));
            air.setAqi(resultsArray.getJSONObject(i).getString("aqi"));
            air.setPosition_name(resultsArray.getJSONObject(i).getString("position_name"));
            air.setPrimary_pollutant(resultsArray.getJSONObject(i).getString("primary_pollutant"));
            air.setQuality(resultsArray.getJSONObject(i).getString("quality"));
            air.setTime_point(resultsArray.getJSONObject(i).getString("time_point"));
            al.add(air);
        }
        return al;
    }


}
