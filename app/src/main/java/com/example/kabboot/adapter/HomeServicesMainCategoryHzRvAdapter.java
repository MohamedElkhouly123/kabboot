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
import androidx.cardview.widget.CardView;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kabboot.R;
import com.example.kabboot.data.model.getAllServiceDataResponce.MainCat;
import com.example.kabboot.data.model.getAllServiceDataResponce.SubCat;
import com.example.kabboot.view.activity.HomeCycleActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.kabboot.utils.HelperMethod.onLoadImageFromUrl;


public class HomeServicesMainCategoryHzRvAdapter extends RecyclerView.Adapter<HomeServicesMainCategoryHzRvAdapter.ViewHolder> {
    private final NavController navController;



//    private final DialogAdapterCallback dialogAdapterCallback;

    private Context context;
    private Activity activity;
    private LinearLayoutManager lLayout;
    private int itemNum;
    private List<MainCat> getAllServiceData = new ArrayList<>();
    private List<SubCat> subCats = new ArrayList<>();


    public HomeServicesMainCategoryHzRvAdapter(Context context, Activity activity,
                                               List<MainCat> getAllServiceDataResponces,
                                               NavController navController) {
        this.context = context;
        this.activity = activity;
//        this.clientRestaurantsDataList.clear();
        this.getAllServiceData = getAllServiceDataResponces;
        this.navController = navController;
//        this.dialogAdapterCallback = dialogAdapterCallback;
//        showToast(activity, "list=" + getHomeDisscoverGetHotelsDataItemsListData.get(0).getCity());

//        showToast(activity, String.valueOf(itemNum));
//        clientData = LoadUserData(activity);

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_view_fragment_home_services_main_rv2_hz_item,
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

            MainCat getAllServiceDataResponce = getAllServiceData.get(position);
            if(position==0){
                holder.cardViewFragmentHomeServicesMainRv2HzItemImg.setImageResource(R.drawable.on_road);
            }
//            showToast(activity, String.valueOf(position));
            if (getAllServiceDataResponce.getImage() != null) {
                String categoryImage = "https://www.kabboot.com/uploads/cat/" + getAllServiceDataResponce.getImage().trim();
                onLoadImageFromUrl(holder.cardViewFragmentHomeServicesMainRv2HzItemImg, categoryImage.trim(), context);
            }
            holder.cardViewFragmentHomeServicesMainRv2HzItemNameTv.setText(getAllServiceDataResponce.getCategoryName());

        } catch (Exception e) {

        }

    }


    private void setAction(final ViewHolder holder, final int position) {

        holder.cardViewFragmentHomeServicesMainRv2HzItemLyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("MainServiceName", getAllServiceData.get(position).getCategoryName());
                subCats = getAllServiceData.get(position).getSubCat();
                bundle.putSerializable("Object", (Serializable) subCats);
                navController.navigate(R.id.action_navigation_services_to_homeOnSiteServicesFragment, bundle);
                HomeCycleActivity navigationActivity = (HomeCycleActivity) activity;
                navigationActivity.setNavigation("g");
            }
        });

    }


    @Override
    public int getItemCount() {

        return getAllServiceData.size();

    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.card_view_fragment_home_services_main_rv2_hz_item_img)
        ImageView cardViewFragmentHomeServicesMainRv2HzItemImg;
        @BindView(R.id.card_view_fragment_home_services_main_rv2_hz_item_name_tv)
        TextView cardViewFragmentHomeServicesMainRv2HzItemNameTv;
        @BindView(R.id.card_view_fragment_home_services_main_rv2_hz_item_ly_btn)
        CardView cardViewFragmentHomeServicesMainRv2HzItemLyBtn;
        private View view;
        private int position;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);

        }
    }
}
