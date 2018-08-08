package com.example.denis.currencyconversion;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "http://hnbex.eu/";
    private TextView textViewBuyingResult, textViewSellingResult, textViewToCurrencyStringB, textViewToCurrencyStringS;
    private EditText editTextInputValue;
    private Spinner spinnerFromCurrency, spinnerToCurrency;
    private Button buttonSubmitConversion;

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private double inputValue;
    private String currencyFrom;
    private String currencyTo;
    private double resultConversionBuying;
    private double resultConversionSelling;
    private double getBuyingEuro;
    private double getSellingEuro;
    private double getBuyingUSD;
    private double getSellingUSD;
    private String getCurrency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextInputValue = (EditText) findViewById(R.id.editTextEnterAmount);
        spinnerFromCurrency = (Spinner) findViewById(R.id.spinnerFromCurrency);
        spinnerToCurrency = (Spinner) findViewById(R.id.spinnerToCurrency);
        textViewBuyingResult = (TextView) findViewById(R.id.textViewInputBuying);
        textViewSellingResult = (TextView) findViewById(R.id.textViewInputSelling);
        textViewToCurrencyStringB = (TextView) findViewById(R.id.textViewCurrencyBuying);
        textViewToCurrencyStringS = (TextView) findViewById(R.id.textViewCurrencySelling);

        buttonSubmitConversion = (Button) findViewById(R.id.buttonSubmitConversion);

        getRetrofitObject();
        updateUi();
    }

    public void updateUi() {

        buttonSubmitConversion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    inputValue = Double.valueOf(editTextInputValue.getText().toString());
                    currencyFrom = spinnerFromCurrency.getSelectedItem().toString();
                    currencyTo = spinnerToCurrency.getSelectedItem().toString();

                    if (currencyFrom.equals("EUR") && currencyTo.equals("KN")) {
                        resultConversionBuying = getBuyingEuro;
                        resultConversionSelling = getSellingEuro;
                        textViewToCurrencyStringB.setText(currencyTo.toString());
                        textViewToCurrencyStringS.setText(currencyTo.toString());
                    } else if (currencyFrom.equals("KN") && currencyTo.equals("EUR")) {
                        resultConversionBuying = 1 / getBuyingEuro;
                        resultConversionSelling = 1 / getSellingEuro;
                        textViewToCurrencyStringB.setText(currencyTo.toString());
                        textViewToCurrencyStringS.setText(currencyTo.toString());
                    } else if (currencyFrom.equals("USD") && currencyTo.equals("KN")){
                        resultConversionBuying = getBuyingUSD;
                        resultConversionSelling = getSellingUSD;
                        textViewToCurrencyStringB.setText(currencyTo.toString());
                        textViewToCurrencyStringS.setText(currencyTo.toString());
                    } else if (currencyFrom.equals("KN") && currencyTo.equals("USD")){
                        resultConversionBuying = 1/getBuyingUSD;
                        resultConversionSelling = 1/getSellingUSD;
                        textViewToCurrencyStringB.setText(currencyTo.toString());
                        textViewToCurrencyStringS.setText(currencyTo.toString());
                    } else if (currencyFrom.equals("EUR") && currencyTo.equals("USD")) {
                        resultConversionBuying = getBuyingEuro/getBuyingUSD;
                        resultConversionSelling = getSellingEuro/getSellingUSD;
                        textViewToCurrencyStringB.setText(currencyTo.toString());
                        textViewToCurrencyStringS.setText(currencyTo.toString());
                    } else if (currencyFrom.equals("USD") && currencyTo.equals("EUR")) {
                        resultConversionBuying = getBuyingUSD/getBuyingEuro;
                        resultConversionSelling = getSellingUSD/getSellingEuro;
                        textViewToCurrencyStringB.setText(currencyTo.toString());
                        textViewToCurrencyStringS.setText(currencyTo.toString());
                    }
                    else if (currencyFrom == currencyTo){
                        resultConversionBuying = 1;
                        resultConversionSelling = 1;
                        textViewToCurrencyStringB.setText(currencyTo.toString());
                        textViewToCurrencyStringS.setText(currencyTo.toString());
                    }


                textViewBuyingResult.setText(String.valueOf(inputValue*resultConversionBuying));
                textViewSellingResult.setText(String.valueOf(inputValue*resultConversionSelling));

                }catch (IllegalArgumentException exception){
                Log.e(LOG_TAG, "Unknown number", exception);
                Toast.makeText(getApplicationContext(), "Please enter the value", Toast.LENGTH_SHORT).show();
            }
            }
        });
    }

    void getRetrofitObject() {

        Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetDataService service = retrofit.create(GetDataService.class);
        Call<List<CurrencyConversion>> call = service.getCurrencyConversion();

        call.enqueue(new Callback<List<CurrencyConversion>>() {
            @Override
            public void onResponse(Call<List<CurrencyConversion>> call, Response<List<CurrencyConversion>> response) {
                try {

                    List<CurrencyConversion> currencyConversions = response.body();

                    for(int i = 0; i < currencyConversions.size(); i++) {
                        getCurrency = currencyConversions.get(i).getCurrencyCode();

                        if(getCurrency.equals("EUR")) {
                            getBuyingEuro = currencyConversions.get(i).getBuyingEuro();
                            getSellingEuro = currencyConversions.get(i).getSellingEuro();
                        } else if(getCurrency.equals("USD")) {
                            getBuyingUSD = currencyConversions.get(i).getBuyingEuro();
                            getSellingUSD = currencyConversions.get(i).getSellingEuro();
                        }
                    }
                } catch (Exception e) {
                    Log.d("onResponse", "There is an error");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<CurrencyConversion>> call, Throwable t) {
                Log.d("onFailure", t.toString());
            }
        });
    }
}
