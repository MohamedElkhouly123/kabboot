
package com.example.kabboot.data.model.customerPromoCodesResponce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerPromocode {

    @SerializedName("promo_id_fk")
    @Expose
    private int promoIdFk;
    @SerializedName("user_id_fk")
    @Expose
    private String userIdFk;
    @SerializedName("promo_code_name")
    @Expose
    private String promoCodeName;
    @SerializedName("promo_code_percent")
    @Expose
    private String promoCodePercent;

    public CustomerPromocode(int id, String hint) {
        this.promoIdFk=id;
        this.promoCodeName=hint;

    }

    public int getPromoIdFk() {
        return promoIdFk;
    }

    public void setPromoIdFk(int promoIdFk) {
        this.promoIdFk = promoIdFk;
    }

    public String getUserIdFk() {
        return userIdFk;
    }

    public void setUserIdFk(String userIdFk) {
        this.userIdFk = userIdFk;
    }

    public String getPromoCodeName() {
        return promoCodeName;
    }

    public void setPromoCodeName(String promoCodeName) {
        this.promoCodeName = promoCodeName;
    }

    public String getPromoCodePercent() {
        return promoCodePercent;
    }

    public void setPromoCodePercent(String promoCodePercent) {
        this.promoCodePercent = promoCodePercent;
    }

}
