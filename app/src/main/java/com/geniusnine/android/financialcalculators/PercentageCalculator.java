package com.geniusnine.android.financialcalculators;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

public class PercentageCalculator extends AppCompatActivity  {
    EditText edittextpercentageX1,edittextpercentageY1, edittextpercentageX2,edittextpercentageY2,edittextpercentageX3,edittextpercentageY3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_percentage_calculator);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //declaration of layout tools
        edittextpercentageX1=(EditText) findViewById(R.id.edittextpercentageX1);
        edittextpercentageY1=(EditText) findViewById(R.id.edittextpercentageY1);
        edittextpercentageX2=(EditText) findViewById(R.id.edittextpercentageX2);
        edittextpercentageY2=(EditText) findViewById(R.id.edittextpercentageY2);
        edittextpercentageX3=(EditText) findViewById(R.id.edittextpercentageX3);
        edittextpercentageY3=(EditText) findViewById(R.id.edittextpercentageY3);

        edittextpercentageX1.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                double percentageX1=0;
                double percentageY1=0;
                 percentageX1=Integer.parseInt(edittextpercentageX1.getText().toString().trim());
                 percentageY1=Integer.parseInt(edittextpercentageY1.getText().toString().trim());
                try {
                double resultOne=(percentageX1/100)*percentageY1;
                Toast.makeText(PercentageCalculator.this, ""+resultOne, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {}



            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });




    }
}
