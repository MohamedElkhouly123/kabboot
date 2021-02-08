package com.example.kabboot.view.fragment.HomeCycle2.onLineStore;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.PagerAdapter;

import com.example.kabboot.R;
import com.example.kabboot.adapter.ProductDetailsUltraPagerAdapter;
import com.example.kabboot.data.local.DataBase;
import com.example.kabboot.data.model.getAllproductsResponce.AllProduct;
import com.example.kabboot.data.model.getAllproductsResponce.AllProductForRom;
import com.example.kabboot.view.fragment.BaSeFragment;
import com.tmall.ultraviewpager.UltraViewPager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.ContentValues.TAG;


public class ProductDetailsFragment extends BaSeFragment {

    @BindView(R.id.fragment_product_details_title_name_tv)
    TextView fragmentProductDetailsTitleNameTv;
    @BindView(R.id.fragment_product_details_sub_category_name_tv)
    TextView fragmentProductDetailsSubCategoryNameTv;
    @BindView(R.id.fragment_product_details_product_name_tv)
    TextView fragmentProductDetailsProductNameTv;
    @BindView(R.id.fragment_product_details_price_tv)
    TextView fragmentProductDetailsPriceTv;
    @BindView(R.id.fragment_product_details_description_tv)
    TextView fragmentProductDetailsDescriptionTv;
    @BindView(R.id.fragment_product_details_specifications_tv)
    TextView fragmentProductDetailsSpecificationsTv;
    @BindView(R.id.fragment_product_details_padding_tv)
    TextView fragmentProductDetailsPaddingTv;
    @BindView(R.id.fragment_product_details_items_number_tv)
    TextView fragmentProductDetailsItemsNumberTv;
    @BindView(R.id.fragment_product_details_items_number_total_price_tv)
    TextView fragmentProductDetailsItemsNumberTotalPriceTv;
    @BindView(R.id.fragment_product_details_items_rooms_num_tv)
    TextView fragmentProductDetailsItemsRoomsNumTv;
    @BindView(R.id.fragment_product_details_view_cart_btn)
    FrameLayout fragmentProductDetailsViewCartBtn;
    private NavController navController;
    @BindView(R.id.fragment_product_details_ultra_viewpager)
    UltraViewPager ultraViewPager;
    private int productItemsNum=0;
    private DataBase dataBase;
    private boolean first =true;
    private int productItemsPrice=0;
    private AllProduct productData ;
    private List<AllProductForRom> items= new ArrayList<>();
    private List<String> allProductImages = new ArrayList<>();
    private AllProductForRom allProductForRom;
    private String onSoreOrAllProducts;
    private String dealOrPromo;
    private boolean firstPress=true;

    public ProductDetailsFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        if (this.getArguments() != null) {
            onSoreOrAllProducts = this.getArguments().getString("OnSoreOrAllProducts");
            dealOrPromo = this.getArguments().getString("DealOrPromo");
            productData = (AllProduct) this.getArguments().getSerializable("Object");
        }
        View root = inflater.inflate(R.layout.fragment_product_details, container, false);

        ButterKnife.bind(this, root);
        navController = Navigation.findNavController(getActivity(), R.id.home_activity_fragment);
        setUpActivity();
         dataBase = DataBase.getInstance(getContext());
        setData();
        homeCycleActivity.setNavigation("g");
        // init all widgets in this activity
//        UltraViewPager ultraViewPager = (UltraViewPager)findViewById(R.id.ultra_viewpager);
        ultraViewPager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);
//initialize ProductDetailsUltraPagerAdapter，and add child view to UltraViewPager
        allProductImages.add(productData.getImage());
        if(allProductImages.size()!=0){
        PagerAdapter adapter = new ProductDetailsUltraPagerAdapter(false,allProductImages,getContext());
        ultraViewPager.setAdapter(adapter);
        }

//initialize built-in indicator
        ultraViewPager.initIndicator();
//set style of indicators
        ultraViewPager.getIndicator()
                .setOrientation(UltraViewPager.Orientation.HORIZONTAL)
                .setFocusColor(Color.parseColor("#FC3D04"))
                .setNormalColor(Color.WHITE)
                .setRadius((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics()));
//set the alignment
        ultraViewPager.getIndicator().setGravity(Gravity.CENTER | Gravity.BOTTOM);
        ultraViewPager.getIndicator().setMargin(0, 0, 0, 0);

//construct built-in indicator, and add it to  UltraViewPager
        ultraViewPager.getIndicator().build();

//set an infinite loop
        ultraViewPager.setInfiniteLoop(true);
//enable auto-scroll mode
        ultraViewPager.setAutoScroll(4000);

        return root;
    }

    private void room() {

//        LiveData<List<AllProductForRom>> items2;
//        String[] name = new String[10];
//                Executors.newSingleThreadExecutor().execute(() -> {
//
//                });
        Executors.newSingleThreadExecutor().execute(

                new Runnable() {
                    @Override
                    public void run() {
//                            orderItem = new OrderItem(item.getId(), Integer.parseInt(item.getRestaurantId()), Double.parseDouble(item.getPrice()),
//                                    quantity, "ahmed", note, item.getPhotoUrl(),
//                                    item.getName());
//                            roomDao.addItem(orderItem);
                         allProductForRom = new AllProductForRom(productData.getProductId(),productData.getProductName(),productData.getProductPrice(),productData.getProductCat(),productData.getVendorIdFk(),productData.getProductSpecification(),productData.getProductDesc(),productData.getImage(),productData.getInStock(),productData.getHotdeals(),productData.getMainCategoryName(), productData.getVendorName(),String.valueOf(productItemsNum));
                        dataBase.addNewOrderItemDao().deletAll();

//                                clientMakeNewOrderItemForRoo z z  s m.setCategoryId("2");
//                                allProductForRom.setQuantity(String.valueOf(productItemsNum));
//                                clientMakeNewOrderItemForRoom.setName("checolete2");
//                                Log.i(TAG, clientMakeNewOrderItemForRoom.getName()+"yes");

//                                dataBase.addNewOrderItemDao().add(clientMakeNewOrderItemForRoom);
//                                dataBase.addNewOrderItemDao().add(clientMakeNewOrderItemForRoom);

//                    DataBase.getInstance(getContext()).addNewOrderItemDao().update(clientMakeNewOrderItemForRoom);
//                        dataBase.addNewOrderItemDao().insert(allProductForRom);

//                        List<AllProductForRom> items = dataBase.addNewOrderItemDao().getAllItems();
//                        Log.i(TAG, items.get(0).getProductName()+" yes");
//                                Log.i(TAG, items.get(0).getProductName()+items.get(0).getQuantity()+"yes");
//
//                        for (int i = 0; i > items.size(); i++) {
//                            name[i] = items.get(i).getQuantity();
//                        }
                    }
                });



//        showToast(getActivity(), name[0] );
    }

    private void setData() {
        fragmentProductDetailsTitleNameTv.setText(productData.getMainCategoryName()+productData.getProductName());
        fragmentProductDetailsSubCategoryNameTv.setText(productData.getMainCategoryName());
        fragmentProductDetailsProductNameTv.setText(productData.getProductName());
        fragmentProductDetailsPriceTv.setText(productData.getProductPrice());
        fragmentProductDetailsDescriptionTv.setText(Html.fromHtml(productData.getProductDesc()));
        fragmentProductDetailsSpecificationsTv.setText(Html.fromHtml(productData.getProductSpecification()));
//        showToast(getActivity(), productData.getProductId()+"");
    }

    @Override
    public void onBack() {
//        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fragment,new SelectPaymentMethodFragment());
        if(onSoreOrAllProducts.equalsIgnoreCase("AllProduct")){
            Bundle bundle = new Bundle();
            bundle.putString("DealOrPromo", dealOrPromo);
            navController.navigate(R.id.action_productDetailsFragment_to_allProductsFragment,bundle);
        }
        if(onSoreOrAllProducts.equalsIgnoreCase("onStore")){
            navController.navigate(R.id.action_productDetailsFragment_to_navigation_online_store);
            homeCycleActivity.setNavigation("v");
        }
        roomAddItem();
    }

    private void roomAddAndGetItem() {
        Executors.newSingleThreadExecutor().execute(

                new Runnable() {
                    @Override
                    public void run() {
                if(productItemsNum>0) {
//                    dataBase.addNewOrderItemDao().deletAll();
                    items.clear();
                    allProductForRom = new AllProductForRom(productData.getProductId(), productData.getProductName(), productData.getProductPrice(), productData.getProductCat(), productData.getVendorIdFk(), productData.getProductSpecification(), productData.getProductDesc(), productData.getImage(), productData.getInStock(), productData.getHotdeals(), productData.getMainCategoryName(), productData.getVendorName(), String.valueOf(productItemsNum));
                    dataBase.addNewOrderItemDao().insert(allProductForRom);
                    items = dataBase.addNewOrderItemDao().getAllItems();
                    Log.i(TAG,items.get(0).getQuantity() +"  mohamed");
                    Bundle bundle = new Bundle();
                    bundle.putString("OnSoreOrAllProductsOrDetails", "details");
                    bundle.putString("OnSoreOrAllProducts", onSoreOrAllProducts);
                    bundle.putSerializable("Object", productData);
                    bundle.putSerializable("Object2", (Serializable) items);
                    bundle.putString("DealOrPromo", dealOrPromo);
                    navController.navigate(R.id.action_productDetailsFragment_to_myCartFragment,bundle);
                }
                    }
                });
    }

    private void roomAddItem() {
        if(productItemsNum>0) {

            Executors.newSingleThreadExecutor().execute(

                    new Runnable() {
                        @Override
                        public void run() {

                            allProductForRom = new AllProductForRom(productData.getProductId(), productData.getProductName(), productData.getProductPrice(), productData.getProductCat(), productData.getVendorIdFk(), productData.getProductSpecification(), productData.getProductDesc(), productData.getImage(), productData.getInStock(), productData.getHotdeals(), productData.getMainCategoryName(), productData.getVendorName(), String.valueOf(productItemsNum));
                            dataBase.addNewOrderItemDao().insert(allProductForRom);

                        }
                    });
        }
    }

    @SuppressLint("ResourceType")
    @OnClick({R.id.fragment_product_details_back_img, R.id.fragment_product_details_view_cart_btn, R.id.fragment_product_details_items_minus_item_btn, R.id.fragment_product_details_items_plus_item_btn, R.id.fragment_product_details_items_add_to_card_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment_product_details_back_img:
                onBack();
                break;
            case R.id.fragment_product_details_view_cart_btn:
                if(firstPress) {
                    roomAddAndGetItem();
                    firstPress=false;
                }
                break;
            case R.id.fragment_product_details_items_minus_item_btn:
                if(productItemsNum>=0){
                    productItemsNum--;
                    productItemsPrice= (int) (productItemsNum * Double.parseDouble(productData.getProductPrice()));
                    fragmentProductDetailsItemsNumberTotalPriceTv.setText("EGP "+String.valueOf(productItemsPrice));
                    fragmentProductDetailsItemsNumberTv.setText("("+productItemsNum+") Item");
                    fragmentProductDetailsItemsRoomsNumTv.setText(String.valueOf(productItemsNum));
                    if(productItemsNum==0){
                        first=true;
                        fragmentProductDetailsViewCartBtn.setVisibility(View.GONE);
                        fragmentProductDetailsPaddingTv.setVisibility(View.GONE);
                    }

                }
                break;
            case R.id.fragment_product_details_items_plus_item_btn:
                if(productItemsNum==0){
                    first=false;
                }
                    productItemsNum++;
                productItemsPrice= (int) (productItemsNum * Double.parseDouble(productData.getProductPrice()));
                fragmentProductDetailsItemsNumberTotalPriceTv.setText("EGP "+String.valueOf(productItemsPrice));
                fragmentProductDetailsViewCartBtn.setVisibility(View.VISIBLE);
                fragmentProductDetailsPaddingTv.setVisibility(View.VISIBLE);
                fragmentProductDetailsItemsNumberTv.setText("("+productItemsNum+") Item");
                fragmentProductDetailsItemsRoomsNumTv.setText(String.valueOf(productItemsNum));
                break;
            case R.id.fragment_product_details_items_add_to_card_tv:
                if(first){
                    productItemsNum++;
                    productItemsPrice= (int) (productItemsNum * Double.parseDouble(productData.getProductPrice()));
                    fragmentProductDetailsItemsNumberTotalPriceTv.setText("EGP "+String.valueOf(productItemsPrice));
                    fragmentProductDetailsViewCartBtn.setVisibility(View.VISIBLE);
                    fragmentProductDetailsPaddingTv.setVisibility(View.VISIBLE);
                    fragmentProductDetailsItemsNumberTv.setText("("+productItemsNum+") Item");
                    fragmentProductDetailsItemsRoomsNumTv.setText(String.valueOf(productItemsNum));
                    first=false;
                }
                break;
        }
    }


}