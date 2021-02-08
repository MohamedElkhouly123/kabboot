package com.example.kabboot.utils;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.kabboot.R;
import com.example.kabboot.adapter.ProductCatSpinnerAdapter;
import com.example.kabboot.adapter.VendorsSpinnerAdapter;
import com.google.android.material.textfield.TextInputLayout;

import static com.example.kabboot.data.api.ApiClient.getApiClient;
import static com.example.kabboot.utils.GeneralRequest.getProductCategorySpinnerData;
import static com.example.kabboot.utils.GeneralRequest.getVendorSpinnerData;


public class OnLineStoteFilterSearchDialog extends DialogFragment {

    private String flightOrHotel;
    private SearchDialogCallback showSearchDialog;
    private ImageView cancelBtn;
    private TextView showPaddingPart;
    private LinearLayout filterBtn;
    //    private List<GeneralResposeData> pricingList = new ArrayList<GeneralResposeData>();
//    private List<GetAmenity2> amentiesList = new ArrayList<GetAmenity2>();
//    private SpinnerAdapter priceSpinnerAdapter;
    private int priceSelectedId1 = 0;
    private AdapterView.OnItemSelectedListener priceListener;

    //    private GetAmentiesAdapter getAmentiesAdapter;
    private TextInputLayout dialogSearchProductNameSearchTil;
    private TextInputLayout dialogSearchProductCategoryNameSearchPriceTil, dialogSearchProductVendorNameSearchPriceTil;
    private Spinner dialogSearchProductCategorySpId, dialogSearchProductVendorSpId;
    private String searchprice = "";
    private VendorsSpinnerAdapter vendorsSpinnerAdapter;
    private int vendorSelectedId = 0;
    private AdapterView.OnItemSelectedListener vendorSpinerListener;
    private ProductCatSpinnerAdapter productCatSpinnerAdapter;
    private int productNameSelectedId=0;
    private String productNameFilterValue;
    private String vendorNameFilterValue;
    private AdapterView.OnItemSelectedListener productCatSpinerListener;
    private String vendorIdValue="";
    private String productCatIdValue="";


    public OnLineStoteFilterSearchDialog() {
//        this.getTopUmarAndTophajjPackage=getTopUmarAndTophajjPackage;
    }

    public OnLineStoteFilterSearchDialog(SearchDialogCallback showSearchDialog, String flightOrHotel) {
        this.showSearchDialog = showSearchDialog;
        this.flightOrHotel = flightOrHotel;

    }

//    public static DialogFragment newInstance(View.OnClickListener listener) {
//        DialogFragment fragment = new DialogFragment();
//        return fragment;
//    }

//    String[] players={"Lionel Messi","Christiano Ronaldo","Neymar","Gareth Bale"};

    //    @SuppressLint("WrongViewCast")
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // TODO Auto-generated method stub
        View rootView = getActivity().getLayoutInflater().inflate(R.layout.dialog_store_filter_search_category, null);

        //SET TITLE DIALOG TITLE
//        getDialog().setTitle("Best Players In The World");
//        this.getDialog().setTitle("أختيار القسم العام");
//        this.getDialog().setCancelable(true);

        cancelBtn = (ImageView) rootView.findViewById(R.id.dialog_flight_search_category_close_btn);
        filterBtn = (LinearLayout) rootView.findViewById(R.id.dialog_store_filter_search_category_save_btn);
        dialogSearchProductNameSearchTil = (TextInputLayout) rootView.findViewById(R.id.dialog_store_filter_search_category_til_product_name);
        dialogSearchProductCategoryNameSearchPriceTil = (TextInputLayout) rootView.findViewById(R.id.dialog_store_filter_search_category_til_product_category_name);
        dialogSearchProductCategorySpId = (Spinner) rootView.findViewById(R.id.dialog_store_filter_search_category_product_category_name_sp);
        dialogSearchProductVendorNameSearchPriceTil = (TextInputLayout) rootView.findViewById(R.id.dialog_store_filter_search_category_til_vendor_name);
        dialogSearchProductVendorSpId = (Spinner) rootView.findViewById(R.id.dialog_store_filter_search_category_vendor_name_sp);
//        showPaddingPart=(TextView) rootView.findViewById(R.id.dialog_flight_search_category_padding_txt);

        SeekBar seekBar = (SeekBar) rootView.findViewById(R.id.dialog_flight_search_category_close_btn_seekBar);
        final TextView seekBarValue = (TextView) rootView.findViewById(R.id.dialog_flight_search_category_close_btn_price_tv);
        String status = "false";
        setSpinner();
        //////set the switch to ON
//        mySwitch.setChecked(false);

//////attach a listener to check for changes in state
//        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//
//                if(isChecked){
//                }else{
//                }
//
//            }
//        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub
                seekBarValue.setText(String.valueOf(progress + " EGP"));
                searchprice = String.valueOf(progress);
                if (progress == 0) {
                    searchprice = String.valueOf("");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });


//        RecyclerView dialogCategoriesRvRecyclerView = (RecyclerView) rootView.findViewById(R.id.dialog_day_by_day_and_evisa_more_rv_recycler_view);
//
//        lLayout = new LinearLayoutManager(getActivity());
//
//        dialogCategoriesRvRecyclerView.setLayoutManager(lLayout);
//
//         rcAdapter = new ShowDayByDayVrRvAdapter(getContext(), getActivity(), getTopUmarAndTophajjPackage.getUmarhDays());
//        dialogCategoriesRvRecyclerView.setAdapter(rcAdapter);
//        rcAdapter.notifyDataSetChanged();

        //BUTTON
        cancelBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                dismiss();

            }
        });

        filterBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                String searchName = dialogSearchProductNameSearchTil.getEditText().getText().toString();
                showSearchDialog.filterOnMethodCallback(searchName, searchprice,vendorIdValue,productCatIdValue);

                dismiss();

            }
        });
//        return rootView;
        return new AlertDialog.Builder(getActivity())
//                .setTitle("أختيار القسم العام")
                .setView(rootView)
//                .setPositiveButton(android.R.string.ok,
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int whichButton) {
//                                // do something
//                            }
//                        }
//                )
                .create();
    }


    private void setSpinner() {

        vendorsSpinnerAdapter = new VendorsSpinnerAdapter(getActivity());
        getVendorSpinnerData(getApiClient().clientGetAgetAllvendors(), vendorsSpinnerAdapter, "", dialogSearchProductVendorSpId,
                null);
        vendorSpinerListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                vendorSelectedId = i;


//                fragmentHomeServicesEnterVendorDataCityTv.setText(getString(R.string.Filters));
                if (vendorSelectedId > 0) {
                    vendorNameFilterValue = String.valueOf(vendorsSpinnerAdapter.getItem(i));
                    vendorIdValue =String.valueOf(vendorsSpinnerAdapter.getItemId(i));

                    if(vendorSelectedId==1){
                        vendorIdValue="";
                    }

                    dialogSearchProductVendorNameSearchPriceTil.getEditText().setText(vendorNameFilterValue);

//                    }
//                showToast(getActivity(), String.valueOf(priceSelectedId1));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        };
        dialogSearchProductVendorSpId.setOnItemSelectedListener(vendorSpinerListener);

        productCatSpinnerAdapter = new ProductCatSpinnerAdapter(getActivity());
        getProductCategorySpinnerData(getApiClient().getAllproductCategory(), productCatSpinnerAdapter, "", dialogSearchProductCategorySpId,
                null);
        productCatSpinerListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                productNameSelectedId = i;


//                fragmentHomeServicesEnterVendorDataCityTv.setText(getString(R.string.Filters));
                if (productNameSelectedId > 0) {
                    productNameFilterValue = String.valueOf(productCatSpinnerAdapter.getItem(i));
                    productCatIdValue =String.valueOf(vendorsSpinnerAdapter.getItemId(i));

                    if(productNameSelectedId==1){
                        productCatIdValue="";
                    }
                    dialogSearchProductCategoryNameSearchPriceTil.getEditText().setText(productNameFilterValue);

//                    }
//                showToast(getActivity(), String.valueOf(priceSelectedId1));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        };
        dialogSearchProductCategorySpId.setOnItemSelectedListener(productCatSpinerListener);

    }

}