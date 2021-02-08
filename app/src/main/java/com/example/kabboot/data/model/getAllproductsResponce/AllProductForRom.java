
package com.example.kabboot.data.model.getAllproductsResponce;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "clientNewOrder")
public class AllProductForRom implements Serializable {


    //    @PrimaryKey(autoGenerate = true)
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "itemId")
    private int itemId;

    @SerializedName("product_id")
    @Expose
    private Integer productId;
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
    private String updated;
    @SerializedName("hotdeals")
    @Expose
    private String hotdeals;
    @SerializedName("main_category_name")
    @Expose
    private String mainCategoryName;
    @SerializedName("vendor_name")
    @Expose
    private String vendorName;
    @SerializedName("product_qty")
    @Expose
    private String quantity;
    public AllProductForRom(Integer productId, String productName, String productPrice, String productCat, String vendorIdFk, String productSpecification, String productDesc, String image, String inStock, String hotdeals, String mainCategoryName, String vendorName, String quantity) {
//        this.itemId = itemId;
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productCat = productCat;
        this.vendorIdFk = vendorIdFk;
        this.productSpecification = productSpecification;
        this.productDesc = productDesc;
        this.image = image;
        this.inStock = inStock;
//        this.created = created;
        this.quantity = quantity;
        this.hotdeals = hotdeals;
        this.mainCategoryName = mainCategoryName;
        this.vendorName = vendorName;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
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

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
