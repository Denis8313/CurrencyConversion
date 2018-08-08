package com.example.denis.currencyconversion;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private double inputValue;
    private String currencyFrom;
    private String currencyTo;
    private double resultConversionBuying;
    private double resultConversionSelling;

    private EditText editTextInputValue;
    private TextView textViewBuyingResult, textViewSellingResult, textViewToCurrencyStringB, textViewToCurrencyStringS;
    private Spinner spinnerFromCurrency, spinnerToCurrency;
    private Button buttonSubmitConversion;

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

        List entries = new ArrayList();
        entries.add("KN");
        entries.add("EUR");
        entries.add("USD");
        entries.add("JPN");

        ArrayAdapter spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, entries);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinnerFromCurrency.setAdapter(spinnerAdapter);
        spinnerToCurrency.setAdapter(spinnerAdapter);



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
                        resultConversionBuying = 7.5;
                        resultConversionSelling = 7.0;
                        textViewToCurrencyStringB.setText(currencyTo.toString());
                        textViewToCurrencyStringS.setText(currencyTo.toString());
                    } else if (currencyFrom.equals("KN") && currencyTo.equals("EUR")) {
                        resultConversionBuying = 1 / 7.5;
                        resultConversionSelling = 1 / 7.0;
                        textViewToCurrencyStringB.setText(currencyTo.toString());
                        textViewToCurrencyStringS.setText(currencyTo.toString());
                    } else if (currencyFrom == currencyTo){
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
}
