
package com.example.kabboot.data.model.getAllproductsResponce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OrderProductsItemsListData implements Serializable {

    public OrderProductsItemsListData(Integer id, String productQuantity) {
        this.id = id;
        this.productQuantity = productQuantity;
    }

    @SerializedName("product_id")
    @Expose
    private Integer id;
    @SerializedName("product_qty")
    @Expose
    private String productQuantity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(String productQuantity) {
        this.productQuantity = productQuantity;
    }






}
