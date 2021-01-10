
package com.example.kabboot.data.model.getAllproductsResponce;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAllproductsResponce {

    @SerializedName("all_products")
    @Expose
    private List<AllProduct> allProducts = null;

    public List<AllProduct> getAllProducts() {
        return allProducts;
    }

    public void setAllProducts(List<AllProduct> allProducts) {
        this.allProducts = allProducts;
    }

}
