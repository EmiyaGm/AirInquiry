package com.example.AirInquiry;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.AirInquiry.model.Position;

import java.util.List;

/**
 * Created by gm on 2016/6/5.
 */
public class PositionAdapter extends BaseAdapter {
    private Context context;
    private List<Position> positionList;

    public PositionAdapter(Context context,List<Position> positionList) {
        this.context = context;
        this.positionList= positionList;
    }

    @Override
    public int getCount() {
        return positionList.size();
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
        TextView position_nameView;
        TextView primary_pollutantView;
        TextView aqiView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView;
        ViewHolder holder;
        if (convertView == null) {
            //布局文件
            itemView = LayoutInflater.from(context).inflate(R.layout.main_list_item, null);
            //获取控件
            holder = new ViewHolder();
            holder.position_nameView = (TextView) itemView.findViewById(R.id.position_name);
            holder.primary_pollutantView = (TextView) itemView.findViewById(R.id.primary_pollutant);
            holder.aqiView = (TextView) itemView.findViewById(R.id.aqi);
            itemView.setTag(holder);
        } else {
            //重用
            itemView = convertView;
            holder = (ViewHolder) itemView.getTag();
        }
        //填写数据
        holder.position_nameView.setText(positionList.get(position).getPosition_name());
        holder.primary_pollutantView.setText(positionList.get(position).getPrimary_pollutant());
        holder.aqiView.setText(positionList.get(position).getAqi());
        //返回视图
        return itemView;
    }
}
