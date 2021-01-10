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
import androidx.recyclerview.widget.RecyclerView;

import com.example.kabboot.R;
import com.example.kabboot.data.model.getAllproductsResponce.AllProduct;
import com.example.kabboot.data.model.getAllproductsResponce.AllProductForRom;
import com.example.kabboot.view.activity.BaseActivity;
import com.example.kabboot.view.activity.HomeCycleActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.kabboot.utils.HelperMethod.onLoadImageFromUrl2;


public class GetAllProductsItemsInMyCartAdapter extends RecyclerView.Adapter<GetAllProductsItemsInMyCartAdapter.ViewHolder> {



    private BaseActivity activity;
    private Context context;
    private List<AllProductForRom> allProducts = new ArrayList<>();
    private NavController navController;
    private static boolean show = false;

    public GetAllProductsItemsInMyCartAdapter(Context context, Activity activity, NavController navController, List<AllProductForRom> allProducts) {
        allProducts.clear();
        this.activity = (BaseActivity) activity;
        this.context = context;
        this.navController = navController;
        this.allProducts = allProducts;
//        this.itemNum = itemNum;
//                showToast(activity, String.valueOf(itemNum));
//        showToast(activity, String.valueOf(getDisscoverGetHotelsItemsListData.size()));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.fragment_my_cart_item,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
    }

    private void setData(ViewHolder holder, int position) {

        AllProductForRom getAllProducts = allProducts.get(position);
//            holder.cardviewHzDiscoverItemRateImgHide.setVisibility(View.VISIBLE);
        holder.fragmentMyCartItemNameTv.setText(getAllProducts.getProductName());
        holder.fragmentMyCartItemItemsNumTv.setText(getAllProducts.getQuantity());
        int totalPrice = Integer.parseInt(getAllProducts.getQuantity() )* Integer.parseInt(getAllProducts.getProductPrice());
        holder.fragmentMyCartItemItemsPriceTv.setText(String.valueOf(totalPrice));
        if (getAllProducts.getImage() != null) {
            String productImage = "https://www.kabboot.com/uploads/product/" + getAllProducts.getImage().trim();
            onLoadImageFromUrl2(holder.fragmentMyCartItemImg, productImage.trim(), context);
        }
//        showToast(activity, String.valueOf(getDisscoverGetHotelsItemsListData.size()+ "  " +String.valueOf(itemNum)));

    }

    private void setAction(ViewHolder holder, int position) {

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
//                bundle.putString("MainServiceName", mainServiceName);
                bundle.putSerializable("Object", allProducts.get(position));
//                navController.navigate(R.id.action_allProductsFragment_to_productDetailsFragment, bundle);
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
        @BindView(R.id.fragment_my_cart_item_img)
        ImageView fragmentMyCartItemImg;
        @BindView(R.id.fragment_my_cart_item_name_tv)
        TextView fragmentMyCartItemNameTv;
        @BindView(R.id.fragment_my_cart_item_items_num_tv)
        TextView fragmentMyCartItemItemsNumTv;
        @BindView(R.id.fragment_my_cart_item_items_price_tv)
        TextView fragmentMyCartItemItemsPriceTv;
        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
//            navController = Navigation.findNavController(activity, R.id.home_activity_fragment);

        }
    }
}
