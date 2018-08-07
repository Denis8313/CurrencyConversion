package com.example.denis.currencyconversion;

import com.google.gson.annotations.SerializedName;

public class CurrencyConversion {

    @SerializedName("currency_code")
    public String currencyCode;

    @SerializedName("buying_rate")
    public double buyingEuro;
    @SerializedName("selling_rate")
    public double sellingEuro;

    public CurrencyConversion(String currencyCode, double buyingEuro, double sellingEuro) {
        this.currencyCode = currencyCode;
        this.buyingEuro = buyingEuro;
        this.sellingEuro = sellingEuro;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public double getBuyingEuro() {
        return buyingEuro;
    }

    public double getSellingEuro() {
        return sellingEuro;
    }

    public void setCurrencyCode(String currencyCode){
        this.currencyCode = currencyCode;
    }

    public void setBuyingEuro(double buyingEuro) {
        this.buyingEuro = buyingEuro;
    }

    public void setSellingEuro(double sellingEuro) {
        this.sellingEuro = sellingEuro;
    }
}
