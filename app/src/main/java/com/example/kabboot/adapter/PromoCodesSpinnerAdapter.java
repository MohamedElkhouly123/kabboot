package com.example.kabboot.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.kabboot.R;
import com.example.kabboot.data.model.customerPromoCodesResponce.CustomerPromocode;
import com.example.kabboot.data.model.getAllcitiesResponce.AllCity;

import java.util.ArrayList;
import java.util.List;

public class PromoCodesSpinnerAdapter extends BaseAdapter {

    private Context context;
    private List<CustomerPromocode> generalResponseDataList = new ArrayList<>();
    private LayoutInflater inflter;
    public static int selectedId = 0;

    public PromoCodesSpinnerAdapter(Context applicationContext) {
        this.context = applicationContext;
        this.generalResponseDataList = generalResponseDataList;
        inflter = (LayoutInflater.from(applicationContext));
    }

    public void setData(List<CustomerPromocode> generalResponseDataList, String hint) {
        this.generalResponseDataList = new ArrayList<>();
        this.generalResponseDataList.add(new CustomerPromocode(0, hint));
        this.generalResponseDataList.add(new CustomerPromocode(1, "No Promo Codes"));
        this.generalResponseDataList.addAll(generalResponseDataList);
    }

    @Override
    public int getCount() {
        return generalResponseDataList.size();
    }

    @Override
    public Object getItem(int i) {
        return generalResponseDataList.get(i).getPromoCodePercent();
    }

    @Override
    public long getItemId(int i) {
        return generalResponseDataList.get(i).getPromoIdFk();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.simple_list_item_1, null);

        TextView names = (TextView) view.findViewById(R.id.txt_spinner);
//        names.setText(generalResponseDataList.get(i).getName());
        selectedId = generalResponseDataList.get(i).getPromoIdFk();
        if(i > 0) {
//            names.setTextColor(Color.parseColor("#000000"));
        }


        return view;
    }

    @Override
    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.simple_list_item_1, null);

        TextView text = (TextView) view.findViewById(R.id.txt_spinner);
        if (i == 1) {
            text.setText("No Promo Codes");
            text.setTextColor(Color.parseColor("#F68B20"));
        }
        if(i > 1) {

            text.setText("Promo Code "+generalResponseDataList.get(i).getPromoIdFk()+"   ( "+generalResponseDataList.get(i).getPromoCodePercent()+" % Discount )");
            text.setTextColor(Color.parseColor("#F68B20"));
        }
        selectedId = generalResponseDataList.get(i).getPromoIdFk();

        return view;

    }



}
