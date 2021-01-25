package com.example.kabboot.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.kabboot.R;
import com.example.kabboot.data.model.GeneralResposeData;
import com.example.kabboot.data.model.getAllServiceDataResponce.SubCat;

import java.util.ArrayList;
import java.util.List;

public class SpinnerAdapter extends BaseAdapter {

    private Context context;
    private List<SubCat> generalResponseDataList = new ArrayList<>();
    private LayoutInflater inflter;
    public static int selectedId = 0;

    public SpinnerAdapter(Context applicationContext) {
        this.context = applicationContext;
        this.generalResponseDataList = generalResponseDataList;
        inflter = (LayoutInflater.from(applicationContext));
    }

    public void setData(List<SubCat> generalResponseDataList, String hint) {
        this.generalResponseDataList = new ArrayList<>();
        this.generalResponseDataList.add(new SubCat(0, hint));
        this.generalResponseDataList.addAll(generalResponseDataList);
    }

    @Override
    public int getCount() {
        return generalResponseDataList.size();
    }

    @Override
    public Object getItem(int i) {
        return generalResponseDataList.get(i).getCategoryName();
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
        selectedId = generalResponseDataList.get(i).getCategoryId();
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

            text.setText(generalResponseDataList.get(i).getCategoryName());
            text.setTextColor(Color.parseColor("#F68B20"));
        }
        selectedId = generalResponseDataList.get(i).getCategoryId();

        return view;

    }



}
