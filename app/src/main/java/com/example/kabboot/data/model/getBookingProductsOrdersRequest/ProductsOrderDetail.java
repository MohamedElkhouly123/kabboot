
package com.example.kabboot.data.model.getBookingProductsOrdersRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProductsOrderDetail implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("order_id_fk")
    @Expose
    private String orderIdFk;
    @SerializedName("product_id")
    @Expose
    private String productId;
    @SerializedName("product_qty")
    @Expose
    private String productQty;
    @SerializedName("product_price")
    @Expose
    private String productPrice;
    @SerializedName("vendor_id_fk")
    @Expose
    private String vendorIdFk;
    @SerializedName("category_name")
    @Expose
    private String categoryName;
    @SerializedName("vendor_name")
    @Expose
    private String vendorName;
    @SerializedName("product_name")
    @Expose
    private String productName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderIdFk() {
        return orderIdFk;
    }

    public void setOrderIdFk(String orderIdFk) {
        this.orderIdFk = orderIdFk;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductQty() {
        return productQty;
    }

    public void setProductQty(String productQty) {
        this.productQty = productQty;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getVendorIdFk() {
        return vendorIdFk;
    }

    public void setVendorIdFk(String vendorIdFk) {
        this.vendorIdFk = vendorIdFk;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

}
