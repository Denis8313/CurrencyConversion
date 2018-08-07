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

        Button buttonSubmitConversion = (Button) findViewById(R.id.buttonSubmitConversion);

        buttonSubmitConversion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                inputValue = Double.valueOf(editTextInputValue.getText().toString());
                currencyFrom = spinnerFromCurrency.getSelectedItem().toString();
                currencyTo = spinnerToCurrency.getSelectedItem().toString();

                textViewBuyingResult.setText(String.valueOf(inputValue*7.5));
                textViewSellingResult.setText(String.valueOf(inputValue*7.4));

            }
        });
    }
}
