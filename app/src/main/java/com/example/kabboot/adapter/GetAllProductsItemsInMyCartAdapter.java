package com.example.kabboot.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kabboot.R;
import com.example.kabboot.data.local.DataBase;
import com.example.kabboot.data.model.getAllproductsResponce.AllProductForRom;
import com.example.kabboot.data.model.getAllproductsResponce.OrderProductsItemsListData;
import com.example.kabboot.utils.MyCartAdapterCallback;
import com.example.kabboot.view.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.kabboot.utils.HelperMethod.onLoadImageFromUrl2;


public class GetAllProductsItemsInMyCartAdapter extends RecyclerView.Adapter<GetAllProductsItemsInMyCartAdapter.ViewHolder> {


    private final TextView fragmentMyCartTotalItemsPriceTv;
    private BaseActivity activity;
    private Context context;
    private List<AllProductForRom> allProducts = new ArrayList<>();
    private NavController navController;
    private static boolean show = false;
    private List<OrderProductsItemsListData> orderItemsList = new ArrayList<>();
    private DataBase dataBase;
    private int i;
    private int quan;
    private int itemId;
    private int productTotalPrice=0;
    private int allProductsTotalPrice=0;
    private MyCartAdapterCallback myCartAdapterCallback;

    public GetAllProductsItemsInMyCartAdapter(Context context, Activity activity, MyCartAdapterCallback myCartAdapterCallback,TextView fragmentMyCartTotalItemsPriceTv, NavController navController, List<AllProductForRom> allProducts) {
        this.allProducts.clear();
        this.activity = (BaseActivity) activity;
        this.context = context;
        this.navController = navController;
        this.myCartAdapterCallback = myCartAdapterCallback;
        this.fragmentMyCartTotalItemsPriceTv = fragmentMyCartTotalItemsPriceTv;
        this.allProducts = allProducts;
        dataBase = DataBase.getInstance(context);

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
        if (allProducts.size() > 0) {

            setData(holder, position);
            setAction(holder, position);
        }
    }

    private void setData(ViewHolder holder, int position) {
//        orderItemsList.add( new OrderProductsItemsListData(String.valueOf(allProducts.get(position).getItemId()), allProducts.get(position).getQuantity()));


        AllProductForRom getAllProducts = allProducts.get(position);
        if (getAllProducts.getImage() != null) {
            String productImage = "https://www.kabboot.com/uploads/product/" + getAllProducts.getImage().trim();
            onLoadImageFromUrl2(holder.fragmentMyCartItemImg, productImage.trim(), context);
        }
//            holder.cardviewHzDiscoverItemRateImgHide.setVisibility(View.VISIBLE);
        productTotalPrice = (int) ((int) Double.parseDouble(allProducts.get(position).getProductPrice()) * Double.parseDouble(allProducts.get(position).getQuantity()));
        allProductsTotalPrice += productTotalPrice;
        fragmentMyCartTotalItemsPriceTv.setText(allProductsTotalPrice+" EGP");
        holder.fragmentMyCartItemNameTv.setText(getAllProducts.getProductName());
        quan= Integer.parseInt(allProducts.get(position).getQuantity());
        holder.fragmentMyCartItemItemsNumTv.setText("(" + quan + ") Item");
        int totalPrice = (int) (Integer.parseInt(getAllProducts.getQuantity()) * Double.parseDouble(getAllProducts.getProductPrice()));
        holder.fragmentMyCartItemItemsPriceTv.setText(String.valueOf(totalPrice) + " EGP");

//        showToast(activity, orderItemsList.get(position).getId());

    }

    private void setAction(ViewHolder holder, int position) {
        AllProductForRom getAllProducts = allProducts.get(position);
        holder.fragmentMyCartItemAddItemQuanImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position >= 0 && allProducts.size() > 0) {
                    Executors.newSingleThreadExecutor().execute(
                            new Runnable() {
                        @Override
                        public void run() {
//                                        dataBase.addNewOrderItemDao().delete(allProducts.get(position).getItemId());
                            dataBase.addNewOrderItemDao().update(String.valueOf(quan),allProducts.get(position).getItemId());

                        }

                    });
                    quan++;
                    holder.fragmentMyCartItemItemsNumTv.setText("(" + quan + ") Item");
                    int totalPrice = (int) (quan * Double.parseDouble(getAllProducts.getProductPrice()));
                    holder.fragmentMyCartItemItemsPriceTv.setText(String.valueOf(totalPrice) + " EGP");
                    allProductsTotalPrice += Double.parseDouble(getAllProducts.getProductPrice());
                    fragmentMyCartTotalItemsPriceTv.setText(allProductsTotalPrice+" EGP");
                    myCartAdapterCallback.onMethodCallback(allProductsTotalPrice);

                }
            }
        });
        holder.fragmentMyCartItemMinusNumImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position >= 0 && allProducts.size() > 0) {
                    if(quan>1){
                        Executors.newSingleThreadExecutor().execute(

                                new Runnable() {
                                    @Override
                                    public void run() {
//                                        dataBase.addNewOrderItemDao().delete(allProducts.get(position).getItemId());
                                        dataBase.addNewOrderItemDao().update(String.valueOf(quan),allProducts.get(position).getItemId());

                                    }

                                });
                        quan--;
                    holder.fragmentMyCartItemItemsNumTv.setText("(" + quan + ") Item");
                    int totalPrice = (int) (quan * Double.parseDouble(getAllProducts.getProductPrice()));
                    holder.fragmentMyCartItemItemsPriceTv.setText(String.valueOf(totalPrice) + " EGP");
                        allProductsTotalPrice -= Double.parseDouble(getAllProducts.getProductPrice());
                        fragmentMyCartTotalItemsPriceTv.setText(allProductsTotalPrice+" EGP");
                        myCartAdapterCallback.onMethodCallback(allProductsTotalPrice);
                    }else {
                        if (getItemCount() > position) {
                            showDeleteDialog(position);
                        }
                    }
                }
            }
        });
        holder.fragmentMyCartItemDeleteItemsImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position >= 0 && allProducts.size() > 0) {
                    if (getItemCount() > position) {
                        showDeleteDialog(position);
                    }
                }
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
        @BindView(R.id.fragment_my_cart_item_delete_items_img)
        ImageView fragmentMyCartItemDeleteItemsImg;
        @BindView(R.id.fragment_my_cart_item_minus_num_img)
        ImageView fragmentMyCartItemMinusNumImg;
        @BindView(R.id.fragment_my_cart_item_add_item_quan_img)
        ImageView fragmentMyCartItemAddItemQuanImg;
        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
//            navController = Navigation.findNavController(activity, R.id.home_activity_fragment);

        }
    }

    private void showDeleteDialog(int position) {
        try {
//                final View view = activity.getLayoutInflater().inflate(R.layout.dialog_restaurant_add_category, null);
//            alertDialog = new AlertDialog.Builder(HomeFragment.this).create();
            AlertDialog alertDialog;
            alertDialog = new AlertDialog.Builder(activity).create();
//            alertDialog.setTitle("Delete");
            alertDialog.setMessage("Are You Sure From Delete ?");
            alertDialog.setCancelable(false);

            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Submit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    itemId=allProducts.get(position).getItemId();
                    if (allProducts.size() > 0) {
                        if (getItemCount() > position) {
//                            notifyItemChanged(position);
                            allProducts.remove(position);
                            notifyItemRemoved(position);


                            notifyItemRangeChanged(position, allProducts.size());
                            notifyDataSetChanged();
                            Executors.newSingleThreadExecutor().execute(

                                    new Runnable() {
                                        @Override
                                        public void run() {
//                                        dataBase.addNewOrderItemDao().delete(allProducts.get(position).getItemId());
                                            dataBase.addNewOrderItemDao().deleteById(itemId);

                                        }

                                    });
                            if(allProducts.size()==0){
                                navController.navigate(R.id.action_myCartFragment_to_navigation_online_store);

                            }
                        }

                    }


//                        notify();
//                    notifyItemRemoved(position);



                }

            });


            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    alertDialog.dismiss();
                }
            });

//                alertDialog.setView(view);
//            alertDialog.setOnShowListener( new DialogInterface.OnShowListener() {
//                @SuppressLint("ResourceAsColor")
//                @Override
//                public void onShow(DialogInterface arg0) {
//                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(R.color.pink);
//                    alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(R.color.pink);
//
//                }
//            });

            alertDialog.show();

        } catch (Exception e) {

        }
    }


}
