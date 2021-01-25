package com.example.kabboot.utils;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;


import com.example.kabboot.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;



public class OnLineStoteMyCarSearchDialog extends DialogFragment {

    private String flightOrHotel;
    private SearchDialogCallback showSearchDialog;
    private ImageView cancelBtn ;
    private TextView showPaddingPart;
    private Button filterBtn;
//    private List<GeneralResposeData> pricingList = new ArrayList<GeneralResposeData>();
//    private List<GetAmenity2> amentiesList = new ArrayList<GetAmenity2>();
//    private SpinnerAdapter priceSpinnerAdapter;
    private int priceSelectedId1 = 0;
    private AdapterView.OnItemSelectedListener priceListener;

//    private GetAmentiesAdapter getAmentiesAdapter;
    private TextInputLayout tdialogFlightSearchCategorySearchTil;
    private TextInputLayout dialogFlightSearchCategorySearchPriceTil;
    private Spinner dialogFlightSearchCategorySpPrice;


    public OnLineStoteMyCarSearchDialog() {
//        this.getTopUmarAndTophajjPackage=getTopUmarAndTophajjPackage;
    }

    public OnLineStoteMyCarSearchDialog(SearchDialogCallback showSearchDialog, String flightOrHotel) {
        this.showSearchDialog=showSearchDialog;
        this.flightOrHotel=flightOrHotel;

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
        View rootView= getActivity().getLayoutInflater().inflate(R.layout.dialog_my_care_filter_search_category, null);

        //SET TITLE DIALOG TITLE
//        getDialog().setTitle("Best Players In The World");
//        this.getDialog().setTitle("أختيار القسم العام");
//        this.getDialog().setCancelable(true);

        cancelBtn=(ImageView) rootView.findViewById(R.id.dialog_flight_search_category_close_btn);
//        filterBtn=(Button) rootView.findViewById(R.id.dialog_flight_search_category_filter_now_btn);
//        tdialogFlightSearchCategorySearchTil=(TextInputLayout) rootView.findViewById(R.id.tdialog_flight_search_category_search_til);
//        dialogFlightSearchCategorySearchPriceTil=(TextInputLayout) rootView.findViewById(R.id.dialog_flight_search_category_search_price_til);
//        dialogFlightSearchCategorySpPrice=(Spinner) rootView.findViewById(R.id.dialog_flight_search_category_sp_price);
//        showPaddingPart=(TextView) rootView.findViewById(R.id.dialog_flight_search_category_padding_txt);


        setData();


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

                String searchKey=tdialogFlightSearchCategorySearchTil.getEditText().getText().toString();
                String typeSearch;
                int priceFrom=0,priceTo=0;
                if(priceSelectedId1==1){
                    priceFrom=500;
                }
                if(priceSelectedId1==2){
                    priceFrom=400;  priceTo=500;
                }
                if(priceSelectedId1==3){
                    priceFrom=300;  priceTo=400;
                }
                if(priceSelectedId1==4){
                    priceFrom=200;  priceTo=300;
                }
                if(priceSelectedId1==5){
                    priceFrom=1;  priceTo=200;
                }

//                if(validationLengthZero(tdialogFlightSearchCategorySearchTil, getString(R.string.invalid_search), 0)){ // return searchKey = null
//                    if (!validationLength(tdialogFlightSearchCategorySearchTil, getString(R.string.invalid_search), 3)) {
//                        onCreateErrorToast(getActivity(), getString(R.string.invalid_search));
//                        return;
//                    }
//                    if(priceSelectedId1==0){
//                        typeSearch="nameOnly";
//                    } else {
//                        if(priceSelectedId1==1){
//                            typeSearch="namePriceAbove";
//                        }
//                        else {
//                            typeSearch="namePrice";
//                        }
//                    }
//                }else {
////                    !validationLengthZero(tdialogFlightSearchCategorySearchTil, getString(R.string.invalid_search), 0)&&
//                    if (priceSelectedId1==0) {
//                        typeSearch="noFilter";
////                        onCreateErrorToast(getActivity(), getString(R.string.invalid_search2));
////                        return;
//                    } else  {
//                        if(priceSelectedId1==1){
//                            typeSearch="priceAbove";
//                        }
//                        else   {
//                            typeSearch="price";
//                        }
//                    }
//                }
//                if(typeSearch!=null){
//                showSearchDialog.filterOnMethodCallback(searchKey,priceFrom,priceTo,typeSearch, null);
//                }else {
//                    showToast(getActivity(), typeSearch);
//
//                }
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




    private void setData() {
        showPaddingPart.setVisibility(View.VISIBLE);
//        pricingList.add(new GeneralResposeData(0, " $500 - Above "));
//        pricingList.add(new GeneralResposeData(1, " $400 - $500 "));
//        pricingList.add(new GeneralResposeData(2, " $300 - $400 "));
//        pricingList.add(new GeneralResposeData(3, " $200 - $300 "));
//        pricingList.add(new GeneralResposeData(4, " $200 - Below "));
//        priceSpinnerAdapter = new SpinnerAdapter(getActivity());
//        priceSpinnerAdapter.setData(pricingList, "");
//        dialogFlightSearchCategorySpPrice.setAdapter(priceSpinnerAdapter);
        dialogFlightSearchCategorySpPrice.setSelection(priceSelectedId1);
        priceListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                priceSelectedId1 = i;
//                dialogFlightSearchCategorySearchPriceTil.getEditText().setText(String.valueOf(priceSpinnerAdapter.getItem(priceSelectedId1)));
//                showToast(getActivity(), String.valueOf(priceSelectedId1));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        };
        dialogFlightSearchCategorySpPrice.setOnItemSelectedListener(priceListener);
    }




}

