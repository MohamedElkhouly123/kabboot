
package com.example.kabboot.data.model.customerPromoCodesResponce;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerPromoCodesResponce {

    @SerializedName("customer_promocodes")
    @Expose
    private List<CustomerPromocode> customerPromocodes = null;

    public List<CustomerPromocode> getCustomerPromocodes() {
        return customerPromocodes;
    }

    public void setCustomerPromocodes(List<CustomerPromocode> customerPromocodes) {
        this.customerPromocodes = customerPromocodes;
    }

}
