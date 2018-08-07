package com.example.denis.currencyconversion;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {

    @GET("api/v1/rates/daily/?date=YYYY-MM-DD")
    Call<List<CurrencyConversion>> getCurrencyConversion();

}
