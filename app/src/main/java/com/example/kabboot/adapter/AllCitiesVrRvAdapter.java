package com.example.kabboot.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kabboot.R;
import com.example.kabboot.data.model.getAllServiceDataResponce.SubCat;
import com.example.kabboot.data.model.getAllcitiesResponce.AllCity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AllCitiesVrRvAdapter extends RecyclerView.Adapter<AllCitiesVrRvAdapter.ViewHolder> {
    private final NavController navController;
    private final String mainServiceName;
    private final String subServiceName;
    private final List<SubCat> subCatDataList;


//    private final DialogAdapterCallback dialogAdapterCallback;

    private Context context;
    private Activity activity;
    private LinearLayoutManager lLayout;
    private int itemNum;
    private List<AllCity> cityArrayList = new ArrayList<>();
    private boolean filter =true;


    public AllCitiesVrRvAdapter(Context context, Activity activity,
                                List<AllCity> cityArrayList,
                                String subServiceName, List<SubCat> subCatDataList, String mainServiceName, NavController navController) {
        this.context = context;
        this.activity = activity;
//        this.clientRestaurantsDataList.clear();
        this.cityArrayList = cityArrayList;
//        this.cityArrayList.add(new AllCity( "All Cities"));
//        this.cityArrayList.addAll(cityArrayList);
        this.mainServiceName = mainServiceName;
        this.subCatDataList = subCatDataList;
        this.subServiceName = subServiceName;
        this.navController = navController;
//        this.dialogAdapterCallback = dialogAdapterCallback;
//        showToast(activity, "list=" + getHomeDisscoverGetHotelsDataItemsListData.get(0).getCity());

//        showToast(activity, String.valueOf(itemNum));
//        clientData = LoadUserData(activity);

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_view_fragment_all_cities_rv_vr_item,
                parent, false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        setData(holder, position);
        setAction(holder, position);

    }

    private void setData(ViewHolder holder, int position) {
        try {
//            final int itemType = getItemViewType(position);
            holder.position = position;

            AllCity allCity = cityArrayList.get(position);
//            if (allCity.getImage() != null) {
//                String categoryImage = "https://www.kabboot.com/uploads/cat/" + allCity.getImage().trim();
//                onLoadImageFromUrl2(holder.cardViewFragmentHomeServicesRvVrItemImg, categoryImage.trim(), context);
//            }
            holder.cardViewFragmentAllCitiesRvVrItemNameTv.setText(allCity.getCityName());
//            showToast(activity, String.valueOf(allCity.getImage()));


        } catch (Exception e) {

        }

    }


    private void setAction(final ViewHolder holder, final int position) {

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(position==0){
//                    filter=false;
//                }else {
//                    filter=true;
//                }
                Bundle bundle = new Bundle();
                bundle.putString("CityName", cityArrayList.get(position).getCityName());
                bundle.putBoolean("Filter", filter);
                bundle.putString("SubServiceName",subServiceName );
                bundle.putString("MainServiceName", mainServiceName);
                bundle.putSerializable("Object", (Serializable) subCatDataList);
                navController.navigate(R.id.action_vendorsCityListFragment_to_homeServicesEnterVenderDataFragment, bundle);


//                HomeCycleActivity navigationActivity = (HomeCycleActivity) activity;
//                navigationActivity.setNavigation("g");
            }
        });

    }


    @Override
    public int getItemCount() {

        return cityArrayList.size();

    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.card_view_fragment_all_cities_rv_vr_item_img)
        ImageView cardViewFragmentAllCitiesRvVrItemImg;
        @BindView(R.id.card_view_fragment_all_cities_rv_vr_item_name_tv)
        TextView cardViewFragmentAllCitiesRvVrItemNameTv;
        private View view;
        private int position;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);

        }
    }
}
