
package com.example.kabboot.data.model.saveStoreOrdersRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderItemList {

    @SerializedName("product_id")
    @Expose
    private Integer productId;
    @SerializedName("product_qty")
    @Expose
    private String productQty;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductQty() {
        return productQty;
    }

    public void setProductQty(String productQty) {
        this.productQty = productQty;
    }

}
