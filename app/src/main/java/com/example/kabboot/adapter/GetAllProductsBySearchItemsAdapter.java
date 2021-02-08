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
import androidx.recyclerview.widget.RecyclerView;

import com.example.kabboot.R;
import com.example.kabboot.data.model.getAllproductsResponce.DataFounded;
import com.example.kabboot.view.activity.BaseActivity;
import com.example.kabboot.view.activity.HomeCycleActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.kabboot.utils.HelperMethod.onLoadImageFromUrl2;


public class GetAllProductsBySearchItemsAdapter extends RecyclerView.Adapter<GetAllProductsBySearchItemsAdapter.ViewHolder> {


    private final String onSoreOrAllProducts;
    private final String dealOrPromo;
    private BaseActivity activity;
    private Context context;
    private List<DataFounded> allProducts = new ArrayList<>();
    private NavController navController;
    private static boolean show = false;

    public GetAllProductsBySearchItemsAdapter(Context context, Activity activity, String dealOrPromo, String onSoreOrAllProducts, NavController navController, List<DataFounded> allProducts) {
        allProducts.clear();
        this.activity = (BaseActivity) activity;
        this.context = context;
        this.navController = navController;
        this.allProducts = allProducts;
        this.onSoreOrAllProducts =onSoreOrAllProducts;
        this.dealOrPromo =dealOrPromo;
//        this.itemNum = itemNum;
//                showToast(activity, String.valueOf(itemNum));
//        showToast(activity, String.valueOf(getDisscoverGetHotelsItemsListData.size()));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.card_view_fragment_on_line_store_main_rv2_hz_item,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
    }

    private void setData(ViewHolder holder, int position) {

        DataFounded getAllProducts = allProducts.get(position);
        if (getAllProducts.getImage() != null) {
            String productImage = "https://www.kabboot.com/uploads/product/" + getAllProducts.getImage().trim();
            onLoadImageFromUrl2(holder.cardViewFragmentOnLineStoreMainRv2HzItemProductImg, productImage.trim(), context);
        }
//            holder.cardviewHzDiscoverItemRateImgHide.setVisibility(View.VISIBLE);
        holder.cardViewFragmentOnLineStoreMainRv2HzItemProductNameTv.setText(getAllProducts.getProductName());
        holder.cardViewFragmentOnLineStoreMainRv2HzItemProductPriceTv.setText(getAllProducts.getProductPrice() + " EGP");

//        showToast(activity, String.valueOf(getDisscoverGetHotelsItemsListData.size()+ "  " +String.valueOf(itemNum)));

    }

    private void setAction(ViewHolder holder, int position) {

        holder.cardViewFragmentOnLineStoreMainRv2HzItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("OnSoreOrAllProducts", onSoreOrAllProducts);
                bundle.putSerializable("Object", allProducts.get(position));
                if(onSoreOrAllProducts.equalsIgnoreCase("AllProduct")){
                    bundle.putString("DealOrPromo", dealOrPromo);
                    navController.navigate(R.id.action_allProductsFragment_to_productDetailsFragment, bundle);
                }
                if(onSoreOrAllProducts.equalsIgnoreCase("onStore")){
                    navController.navigate(R.id.action_navigation_online_store_to_productDetailsFragment, bundle);
                }
                HomeCycleActivity navigationActivity = (HomeCycleActivity) activity;
                navigationActivity.setNavigation("g");
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        }
        return 1;
    }

    @Override
    public int getItemCount() {
        return allProducts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.card_view_fragment_on_line_store_main_rv2_hz_item_product_img)
        ImageView cardViewFragmentOnLineStoreMainRv2HzItemProductImg;
        @BindView(R.id.card_view_fragment_on_line_store_main_rv2_hz_item_product_name_tv)
        TextView cardViewFragmentOnLineStoreMainRv2HzItemProductNameTv;
        @BindView(R.id.card_view_fragment_on_line_store_main_rv2_hz_item_product_price_tv)
        TextView cardViewFragmentOnLineStoreMainRv2HzItemProductPriceTv;
        @BindView(R.id.card_view_fragment_on_line_store_main_rv2_hz_item_btn)
        CardView cardViewFragmentOnLineStoreMainRv2HzItemBtn;
        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
//            navController = Navigation.findNavController(activity, R.id.home_activity_fragment);

        }
    }
}
