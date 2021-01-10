/*
 *
 *  MIT License
 *
 *  Copyright (c) 2017 Alibaba Group
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 *
 */

package com.example.kabboot.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.viewpager.widget.PagerAdapter;

import com.example.kabboot.R;

import java.util.List;

import static com.example.kabboot.utils.HelperMethod.onLoadImageFromUrl2;


/**
 * Created by mikeafc on 15/11/26.
 */
public class ProductDetailsUltraPagerAdapter extends PagerAdapter {
    private final List<String> allProductImages;
    private final Context context;
    private boolean isMultiScr;

    public ProductDetailsUltraPagerAdapter(boolean isMultiScr, List<String> allProductImages, Context context) {
        this.isMultiScr = isMultiScr;
        this.allProductImages=allProductImages;
        this.context = context;

    }

    @Override
    public int getCount() {
        return allProductImages.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(container.getContext()).inflate(R.layout.layout_child_home, null);
        //new LinearLayout(container.getContext());
        ImageView imv = (ImageView) linearLayout.findViewById(R.id.im);
//        TextView textView = (TextView) linearLayout.findViewById(R.id.pager_textview);
//        TextView textView2 = (TextView) linearLayout.findViewById(R.id.pager_textview2);
//        textView.setText(position + "");
        //imv.setImageResource(R.drawable.me);
        if (allProductImages.get(position) != null) {
            String productImage = "https://www.kabboot.com/uploads/product/" + allProductImages.get(position) .trim();
            onLoadImageFromUrl2(imv, productImage.trim(), context);
        }
        linearLayout.setId(R.id.item_id);
//        switch (position) {
//            case 0:
////                imv.setImageResource(R.drawable.category_my_losts2);
//                break;
//            case 1:
//                linearLayout.setBackgroundColor(Color.parseColor("#673AB7"));
//                break;
//            case 2:
////                imv.setImageResource(R.drawable.chale);
//                break;
//            case 3:
//                linearLayout.setBackgroundColor(Color.parseColor("#607D8B"));
//                break;
//            case 4:
////                imv.setImageResource(R.drawable.chale);
//                break;
////            case 5:
////                imv.setImageResource(R.drawable.me);
////                break;
//        }
        container.addView(linearLayout);
//        linearLayout.getLayoutParams().width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 180, container.getContext().getResources().getDisplayMetrics());
//        linearLayout.getLayoutParams().height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 400, container.getContext().getResources().getDisplayMetrics());
        return linearLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        LinearLayout view = (LinearLayout) object;
        container.removeView(view);
    }
}
