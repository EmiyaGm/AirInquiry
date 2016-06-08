package com.example.AirInquiry;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import com.example.AirInquiry.model.City;


import java.util.List;

/**
 * Created by user on 2016/6/2.
 */
public class CityListAdapter extends BaseAdapter {
    private Context context;
    private List<City> cities;

    public CityListAdapter(Context context, List<City> cities) {
        this.context = context;
        this.cities = cities;
    }

    @Override
    public int getCount() {
        return cities.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder {
        TextView nameTextView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView;
        ViewHolder holder;
        if (convertView == null) {
            //布局文件
            itemView = LayoutInflater.from(context).inflate(R.layout.city_list_item, null);
            //获取控件
            holder = new ViewHolder();
            holder.nameTextView = (TextView) itemView.findViewById(R.id.name_text);
            itemView.setTag(holder);
        } else {
            //重用
            itemView = convertView;
            holder = (ViewHolder) itemView.getTag();
        }
        //填写数据
        holder.nameTextView.setText(cities.get(position).getName());
        //返回视图
        return itemView;
    }
}
