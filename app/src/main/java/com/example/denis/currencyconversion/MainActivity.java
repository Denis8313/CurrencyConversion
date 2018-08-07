package com.example.denis.currencyconversion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private double inputValue;
    private String currencyFrom;
    private String currencyTo;
    private double resultConversionBuying;
    private double resultConversionSelling;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        updateUi();
    }

    public void updateUi() {

        final EditText editTextInputValue = (EditText) findViewById(R.id.editTextEnterAmount);
        final Spinner spinnerFromCurrency = (Spinner) findViewById(R.id.spinnerFromCurrency);
        final Spinner spinnerToCurrency = (Spinner) findViewById(R.id.spinnerToCurrency);
        final TextView textViewBuyingResult = (TextView) findViewById(R.id.textViewInputBuying);
        final TextView textViewSellingResult = (TextView) findViewById(R.id.textViewInputSelling);
        final TextView textViewToCurrencyStringB = (TextView) findViewById(R.id.textViewCurrencyBuying);
        final TextView textViewToCurrencyStringS = (TextView) findViewById(R.id.textViewCurrencySelling);

        Button buttonSubmitConversion = (Button) findViewById(R.id.buttonSubmitConversion);

        buttonSubmitConversion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                inputValue = Double.valueOf(editTextInputValue.getText().toString());
                currencyFrom = spinnerFromCurrency.getSelectedItem().toString();
                currencyTo = spinnerToCurrency.getSelectedItem().toString();

                if(currencyFrom.equals("EUR") && currencyTo.equals("KN")){
                    resultConversionBuying = 7.5;
                    resultConversionSelling = 7.0;
                    textViewToCurrencyStringB.setText(currencyTo.toString());
                    textViewToCurrencyStringS.setText(currencyTo.toString());
                }
                else if(currencyFrom.equals("KN") && currencyTo.equals("EUR")){
                    resultConversionBuying = 1/7.5;
                    resultConversionSelling = 1/7.0;
                    textViewToCurrencyStringB.setText(currencyTo.toString());
                    textViewToCurrencyStringS.setText(currencyTo.toString());
                }

                textViewBuyingResult.setText(String.valueOf(inputValue*resultConversionBuying));
                textViewSellingResult.setText(String.valueOf(inputValue*resultConversionSelling));

            }
        });
    }
}
