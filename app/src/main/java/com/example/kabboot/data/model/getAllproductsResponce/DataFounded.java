
package com.example.kabboot.data.model.getAllproductsResponce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DataFounded implements Serializable {

    @SerializedName("product_id")
    @Expose
    private String productId;
    @SerializedName("product_name")
    @Expose
    private String productName;
    @SerializedName("product_price")
    @Expose
    private String productPrice;
    @SerializedName("product_cat")
    @Expose
    private String productCat;
    @SerializedName("vendor_id_fk")
    @Expose
    private String vendorIdFk;
    @SerializedName("product_specification")
    @Expose
    private String productSpecification;
    @SerializedName("product_desc")
    @Expose
    private String productDesc;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("in_stock")
    @Expose
    private String inStock;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("updated")
    @Expose
    private Object updated;
    @SerializedName("hotdeals")
    @Expose
    private String hotdeals;
    @SerializedName("main_category_name")
    @Expose
    private String mainCategoryName;
    @SerializedName("vendor_name")
    @Expose
    private String vendorName;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductCat() {
        return productCat;
    }

    public void setProductCat(String productCat) {
        this.productCat = productCat;
    }

    public String getVendorIdFk() {
        return vendorIdFk;
    }

    public void setVendorIdFk(String vendorIdFk) {
        this.vendorIdFk = vendorIdFk;
    }

    public String getProductSpecification() {
        return productSpecification;
    }

    public void setProductSpecification(String productSpecification) {
        this.productSpecification = productSpecification;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getInStock() {
        return inStock;
    }

    public void setInStock(String inStock) {
        this.inStock = inStock;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Object getUpdated() {
        return updated;
    }

    public void setUpdated(Object updated) {
        this.updated = updated;
    }

    public String getHotdeals() {
        return hotdeals;
    }

    public void setHotdeals(String hotdeals) {
        this.hotdeals = hotdeals;
    }

    public String getMainCategoryName() {
        return mainCategoryName;
    }

    public void setMainCategoryName(String mainCategoryName) {
        this.mainCategoryName = mainCategoryName;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

}
