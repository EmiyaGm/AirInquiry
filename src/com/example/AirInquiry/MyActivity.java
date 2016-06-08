package com.example.AirInquiry;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import com.example.AirInquiry.model.Air;
import com.example.AirInquiry.model.AirQueryService;
import com.example.AirInquiry.model.Position;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyActivity extends Activity {
    private String cityCode = "changshu";
    private TextView quality;
    private TextView primary_pollutant;
    private TextView time_point;
    private TextView city;
    private ImageButton refresh;
    private String cityName="常熟";
    private ListView mainList;
    private TextView aqi;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        quality = (TextView)findViewById(R.id.quality_textView);
        primary_pollutant = (TextView)findViewById(R.id.primary_pollutant_textView);
        time_point = (TextView)findViewById(R.id.time_point_textView);
        aqi = (TextView)findViewById(R.id.api_textView);
        city = (TextView)findViewById(R.id.city_textView);
        refresh=(ImageButton)findViewById(R.id.refresh_imageButton);
        cityName = getIntent().getStringExtra("com.examlpe.AirInquiry.cityName");
        cityCode = getIntent().getStringExtra("com.examlpe.AirInquiry.cityCode");
        mainList = (ListView)findViewById(R.id.main_listView);
        if(cityCode==null){
            cityCode = "changshu";
        }
        if(cityName==null){
            cityName="常熟";
        }
        city.setText(cityName);
        //查询天气
        //queryWeather();
        QueryWeather queryWeather = new QueryWeather();
        queryWeather.execute();
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QueryWeather queryWeather = new QueryWeather();
                queryWeather.execute();
            }
        });
    }

    private class QueryWeather extends AsyncTask<String,String,List<Air>> {
        @Override
        protected List<Air> doInBackground(String... params) {
            List<Air> airList = new ArrayList<Air>();
            try {
                airList = new AirQueryService().query(cityCode);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return airList;
        }

        @Override
        protected void onPostExecute(List<Air> airList) {
            setupView(airList);
        }
    }

    private void setupView(List<Air> airList) {
        List<Position> pList = new ArrayList<Position>();
        for(int i = 0;i<airList.size()-1;i++){
            Position p = new Position();
            p.setPosition_name(airList.get(i).getPosition_name());
            p.setAqi(airList.get(i).getAqi());
            p.setPrimary_pollutant(airList.get(i).getPrimary_pollutant());
            pList.add(p);
            mainList.setAdapter(new PositionAdapter(this,pList));
        }
        quality.setText(airList.get(airList.size()-1).getQuality());
        time_point.setText(airList.get(airList.size()-1).getTime_point());
        primary_pollutant.setText(airList.get(airList.size()-1).getPrimary_pollutant());
        aqi.setText(airList.get(airList.size()-1).getAqi());
    }

    /**
     * 显示菜单
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    /**
     * 选中菜单项
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.city_item:
                showCities();
                return true;
            case R.id.about_item:               //关于
                showAbout();
                return true;
            default:
                return false;
        }
    }

    /**
     * 显示关于
     */
    private void showAbout() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.about)
                .setMessage(R.string.about_message)
                .setPositiveButton(android.R.string.ok, null)
                .create()
                .show();
    }
    private void showCities(){
        Intent intent=new Intent(MyActivity.this,CityListActivity.class);
        startActivity(intent);
        MyActivity.this.finish();
    }
}
