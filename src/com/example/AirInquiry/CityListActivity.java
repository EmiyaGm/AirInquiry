package com.example.AirInquiry;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.example.AirInquiry.model.City;
import com.example.AirInquiry.model.CityLoader;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by user on 2016/6/2.
 */
public class CityListActivity extends Activity {

    private ListView cityListView;        //城市列表
    private List<City> cities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_list_activity);
        //列表
        cityListView = (ListView) findViewById(R.id.city_list);
        //读取资源
        InputStream is = getResources().openRawResource(R.raw.city_pinyin);
        try {
            //省份列表
            cities = new CityLoader().load(is);
            //准备适配器
            cityListView.setAdapter(new CityListAdapter(this, cities));
        } catch (IOException e) {
            e.printStackTrace();
        }
        cityListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showCity(position);
            }
        });
    }

    private void showCity(int position) {
        Intent intent = new Intent(this, MyActivity.class);
        intent.putExtra("com.examlpe.AirInquiry.cityCode", cities.get(position).getCode());
        intent.putExtra("com.examlpe.AirInquiry.cityName", cities.get(position).getName());
        startActivity(intent);
        CityListActivity.this.finish();

    }
}
