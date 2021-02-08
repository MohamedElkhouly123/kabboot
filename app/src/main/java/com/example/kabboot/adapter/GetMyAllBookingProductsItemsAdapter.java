package com.example.kabboot.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kabboot.R;
import com.example.kabboot.data.local.DataBase;
import com.example.kabboot.data.model.getAllproductsResponce.OrderProductsItemsListData;
import com.example.kabboot.data.model.getBookingProductsOrdersRequest.ProductsOrderDetail;
import com.example.kabboot.view.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class GetMyAllBookingProductsItemsAdapter extends RecyclerView.Adapter<GetMyAllBookingProductsItemsAdapter.ViewHolder> {



    //    private final TextView fragmentMyCartTotalItemsPriceTv;
    private BaseActivity activity;
    private Context context;
    private List<ProductsOrderDetail> productsOrderDetailList = new ArrayList<>();
    private NavController navController;
    private static boolean show = false;
    private List<OrderProductsItemsListData> orderItemsList = new ArrayList<>();
    private DataBase dataBase;
    private int i;
    private int quan;
    private int itemId;
    private int productTotalPrice = 0;
    private int allProductsTotalPrice = 0;


    public GetMyAllBookingProductsItemsAdapter(Context context, Activity activity, NavController navController, List<ProductsOrderDetail> productsOrderDetailList) {
        this.productsOrderDetailList.clear();
        this.activity = (BaseActivity) activity;
        this.context = context;
        this.navController = navController;
//        this.fragmentMyCartTotalItemsPriceTv = fragmentMyCartTotalItemsPriceTv;
        this.productsOrderDetailList = productsOrderDetailList;
        dataBase = DataBase.getInstance(context);

//        this.itemNum = itemNum;
//                showToast(activity, String.valueOf(itemNum));
//        showToast(activity, String.valueOf(getDisscoverGetHotelsItemsListData.size()));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.fragment_get_my_all_booking_products_items,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        setData(holder, position);
        setAction(holder, position);

    }

    private void setData(ViewHolder holder, int position) {
//        orderItemsList.add( new OrderProductsItemsListData(String.valueOf(allProducts.get(position).getItemId()), allProducts.get(position).getQuantity()));


        ProductsOrderDetail getAllProducts = productsOrderDetailList.get(position);
//        if (getAllProducts.getImage() != null) {
//            String productImage = "https://www.kabboot.com/uploads/product/" + getAllProducts.getImage().trim();
//            onLoadImageFromUrl2(holder.fragmentGetMyAllBookingProductsItemsImg, productImage.trim(), context);
//        }
//            holder.cardviewHzDiscoverItemRateImgHide.setVisibility(View.VISIBLE);
//        productTotalPrice = (int) ((int) Double.parseDouble(productsOrderDetailList.get(position).getProductPrice()) * Double.parseDouble(productsOrderDetailList.get(position).getQuantity()));
//        allProductsTotalPrice += productTotalPrice;
//        fragmentMyCartTotalItemsPriceTv.setText(allProductsTotalPrice + " EGP");
        holder.fragmentGetMyAllBookingProductsItemsNameTv.setText(getAllProducts.getProductName());
        quan = Integer.parseInt(productsOrderDetailList.get(position).getProductQty());
        holder.fragmentGetMyAllBookingProductsItemsNumTv.setText("(" + quan + ") Item");
        int totalPrice = (int) (Integer.parseInt(getAllProducts.getProductQty()) * Double.parseDouble(getAllProducts.getProductPrice()));
        holder.fragmentGetMyAllBookingProductsItemsPriceTv.setText(String.valueOf(totalPrice) + " EGP");
        holder.fragmentGetMyAllBookingProductsItemsVwndorNameTv.setText(getAllProducts.getVendorName());

//        showToast(activity, orderItemsList.get(position).getId());

    }

    private void setAction(ViewHolder holder, int position) {
        ProductsOrderDetail getAllProducts = productsOrderDetailList.get(position);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        return productsOrderDetailList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.fragment_get_my_all_booking_products_items_img)
        ImageView fragmentGetMyAllBookingProductsItemsImg;
        @BindView(R.id.fragment_get_my_all_booking_products_items_name_tv)
        TextView fragmentGetMyAllBookingProductsItemsNameTv;
        @BindView(R.id.fragment_get_my_all_booking_products_items_num_tv)
        TextView fragmentGetMyAllBookingProductsItemsNumTv;
        @BindView(R.id.fragment_get_my_all_booking_products_items_price_tv)
        TextView fragmentGetMyAllBookingProductsItemsPriceTv;
        @BindView(R.id.fragment_get_my_all_booking_products_items_vwndor_name_tv)
        TextView fragmentGetMyAllBookingProductsItemsVwndorNameTv;
        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
//            navController = Navigation.findNavController(activity, R.id.home_activity_fragment);

        }
    }


}
