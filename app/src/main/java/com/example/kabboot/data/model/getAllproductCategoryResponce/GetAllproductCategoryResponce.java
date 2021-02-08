
package com.example.kabboot.data.model.getAllproductCategoryResponce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetAllproductCategoryResponce {

    @SerializedName("product_category")
    @Expose
    private List<ProductCategory> productCategory = null;

    public List<ProductCategory> getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(List<ProductCategory> productCategory) {
        this.productCategory = productCategory;
    }

}
