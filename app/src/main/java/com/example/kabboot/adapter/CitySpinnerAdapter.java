package com.example.kabboot.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.kabboot.R;
import com.example.kabboot.data.model.getAllServiceDataResponce.SubCat;
import com.example.kabboot.data.model.getAllcitiesResponce.AllCity;

import java.util.ArrayList;
import java.util.List;

public class CitySpinnerAdapter extends BaseAdapter {

    private Context context;
    private List<AllCity> generalResponseDataList = new ArrayList<>();
    private LayoutInflater inflter;
    public static int selectedId = 0;

    public CitySpinnerAdapter(Context applicationContext) {
        this.context = applicationContext;
        this.generalResponseDataList = generalResponseDataList;
        inflter = (LayoutInflater.from(applicationContext));
    }

    public void setData(List<AllCity> generalResponseDataList, String hint) {
        this.generalResponseDataList = new ArrayList<>();
        this.generalResponseDataList.add(new AllCity(0, hint));
        this.generalResponseDataList.add(new AllCity(1, "All Cities"));
        this.generalResponseDataList.addAll(generalResponseDataList);
    }

    @Override
    public int getCount() {
        return generalResponseDataList.size();
    }

    @Override
    public Object getItem(int i) {
        return generalResponseDataList.get(i).getCityName();
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.simple_list_item_1, null);

        TextView names = (TextView) view.findViewById(R.id.txt_spinner);
//        names.setText(generalResponseDataList.get(i).getName());
        selectedId = generalResponseDataList.get(i).getCityId();
        if(i > 0) {
//            names.setTextColor(Color.parseColor("#000000"));
        }


        return view;
    }

    @Override
    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.simple_list_item_1, null);

        TextView text = (TextView) view.findViewById(R.id.txt_spinner);
        if(i > 0) {

            text.setText(generalResponseDataList.get(i).getCityName());
            text.setTextColor(Color.parseColor("#F68B20"));
        }
        selectedId = generalResponseDataList.get(i).getCityId();

        return view;

    }



}
