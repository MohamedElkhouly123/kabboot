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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.kabboot.utils.HelperMethod.onLoadImageFromUrl2;


public class HomeServicesSubCategoryVrRvAdapter extends RecyclerView.Adapter<HomeServicesSubCategoryVrRvAdapter.ViewHolder> {
    private final NavController navController;
    private final String mainServiceName;


//    private final DialogAdapterCallback dialogAdapterCallback;

    private Context context;
    private Activity activity;
    private LinearLayoutManager lLayout;
    private int itemNum;
    private List<SubCat> subCats = new ArrayList<>();


    public HomeServicesSubCategoryVrRvAdapter(Context context, Activity activity,
                                              List<SubCat> subCats,
                                              String mainServiceName, NavController navController) {
        this.context = context;
        this.activity = activity;
//        this.clientRestaurantsDataList.clear();
        this.subCats = subCats;
        this.mainServiceName = mainServiceName;
        this.navController = navController;
//        this.dialogAdapterCallback = dialogAdapterCallback;
//        showToast(activity, "list=" + getHomeDisscoverGetHotelsDataItemsListData.get(0).getCity());

//        showToast(activity, String.valueOf(itemNum));
//        clientData = LoadUserData(activity);

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_view_fragment_home_services_rv_vr_item,
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

            SubCat subCat = subCats.get(position);
            holder.cardViewFragmentHomeServicesRvVrItemNameTv.setText(subCat.getCategoryName());
//            showToast(activity, String.valueOf(subCat.getImage()));
            if (subCat.getImage() != null) {
                String categoryImage = "https://www.kabboot.com/uploads/cat/" + subCat.getImage().trim();
                onLoadImageFromUrl2(holder.cardViewFragmentHomeServicesRvVrItemImg, categoryImage.trim(), context);
            }

        } catch (Exception e) {

        }

    }


    private void setAction(final ViewHolder holder, final int position) {

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("MainServiceName", mainServiceName);
                bundle.putSerializable("Object", (Serializable) subCats);
                navController.navigate(R.id.action_homeOnSiteServicesFragment_to_homeServicesEnterVenderDataFragment,bundle);
//                HomeCycleActivity navigationActivity = (HomeCycleActivity) activity;
//                navigationActivity.setNavigation("g");
            }
        });

    }


    @Override
    public int getItemCount() {

        return subCats.size();

    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.card_view_fragment_home_services_rv_vr_item_img)
        ImageView cardViewFragmentHomeServicesRvVrItemImg;
        @BindView(R.id.card_view_fragment_home_services_rv_vr_item_name_tv)
        TextView cardViewFragmentHomeServicesRvVrItemNameTv;
        private View view;
        private int position;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);

        }
    }
}
