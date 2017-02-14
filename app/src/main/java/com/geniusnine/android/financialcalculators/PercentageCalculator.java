package com.geniusnine.android.financialcalculators;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PercentageCalculator extends AppCompatActivity implements TextWatcher,View.OnClickListener{
    EditText edittextpercentageX1,edittextpercentageY1, edittextpercentageX2,edittextpercentageY2,edittextpercentageX3,edittextpercentageY3;
    TextView textViewvalueX1,textViewvalueY1,textViewAns1,textViewvalueX2,textViewvalueY2,textViewAns2,textViewvalueX3,textViewvalueY3,textViewAns3;
    LinearLayout linearLayoutOne,linearLayoutTwo,linearLayoutThree;
    Button buttonResetOne,buttonResetTwo,buttonResetThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_percentage_calculator);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Linearlayout declaration
        linearLayoutOne=(LinearLayout)findViewById(R.id.percentageOnelayout) ;
        linearLayoutTwo=(LinearLayout)findViewById(R.id.percentageTwolayout) ;
        linearLayoutThree=(LinearLayout)findViewById(R.id.percentagethirdlayout) ;

        //declaration of layout tools
        edittextpercentageX1 = (EditText) findViewById(R.id.edittextpercentageX1);
        edittextpercentageY1 = (EditText) findViewById(R.id.edittextpercentageY1);
        edittextpercentageX2 = (EditText) findViewById(R.id.edittextpercentageX2);
        edittextpercentageY2 = (EditText) findViewById(R.id.edittextpercentageY2);
        edittextpercentageX3 = (EditText) findViewById(R.id.edittextpercentageX3);
        edittextpercentageY3 = (EditText) findViewById(R.id.edittextpercentageY3);
        textViewvalueX1=(TextView)findViewById(R.id.textViewvaluex1);
        textViewvalueY1=(TextView)findViewById(R.id.textViewvaluey1);
        textViewAns1=(TextView)findViewById(R.id.textViewAnswer1);
        textViewvalueX2=(TextView)findViewById(R.id.textViewvaluex2);
        textViewvalueY2=(TextView)findViewById(R.id.textViewvaluey2);
        textViewAns2=(TextView)findViewById(R.id.textViewAnswer2);
        textViewvalueX3=(TextView)findViewById(R.id.textViewvaluex3);
        textViewvalueY3=(TextView)findViewById(R.id.textViewvaluey3);
        textViewAns3=(TextView)findViewById(R.id.textViewAnswer3);
        buttonResetOne=(Button)findViewById(R.id.buttonPerecentReset1);
        buttonResetTwo=(Button)findViewById(R.id.buttonPerecentReset2);
        buttonResetThree=(Button)findViewById(R.id.buttonPerecentReset3);

        edittextpercentageX1.addTextChangedListener(this);
        edittextpercentageY1.addTextChangedListener(this);
        edittextpercentageX2.addTextChangedListener(this);
        edittextpercentageY2.addTextChangedListener(this);
        edittextpercentageX3.addTextChangedListener(this);
        edittextpercentageY3.addTextChangedListener(this);

        buttonResetOne.setOnClickListener(this);
        buttonResetTwo.setOnClickListener(this);
        buttonResetThree.setOnClickListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        double percentageX1, percentageY1, percentageX2, percentageY2, percentageX3, percentageY3;
        double resultOne, resultTwo, resultThree;
        try {
            percentageX1 = Double.parseDouble(edittextpercentageX1.getText().toString());
            percentageY1 = Double.parseDouble(edittextpercentageY1.getText().toString());
            String strperecentagex1 = Double.toString((double) percentageX1);
            String strperecentagey1 = Double.toString((double) percentageY1);
            linearLayoutOne.setVisibility(View.VISIBLE);
            resultOne = (percentageX1 / 100) * percentageY1;
            String strresultOne = Double.toString((double) resultOne);
            textViewvalueX1.setText(strperecentagex1);
            textViewvalueY1.setText(strperecentagey1);
            textViewAns1.setText(strresultOne);

        } catch (NumberFormatException e) {
            resultOne = 0;

        }

        //calculation for 3 and 4 edittext
        try {
            percentageX2 = Double.parseDouble(edittextpercentageX2.getText().toString());
            percentageY2 = Double.parseDouble(edittextpercentageY2.getText().toString());
            String strperecentagex2 = Double.toString((double) percentageX2);
            String strperecentagey2 = Double.toString((double) percentageY2);
            linearLayoutTwo.setVisibility(View.VISIBLE);
            resultTwo = (percentageX2 / percentageY2) * 100;
            textViewvalueX2.setText(strperecentagex2);
            textViewvalueY2.setText(strperecentagey2);
            String strresultTwo = Double.toString((double) resultTwo);
            textViewAns2.setText(strresultTwo);
        } catch (NumberFormatException e) {
            resultTwo = 0;
        }

        //calculation for 5 and 6 edittext
        try {
            percentageX3 = Double.parseDouble(edittextpercentageX3.getText().toString());
            percentageY3 = Double.parseDouble(edittextpercentageY3.getText().toString());
            String strperecentagex3 = Double.toString((double) percentageX3);
            String strperecentagey3 = Double.toString((double) percentageY3);
            linearLayoutThree.setVisibility(View.VISIBLE);
            textViewvalueX3.setText(strperecentagex3);
            textViewvalueY3.setText(strperecentagey3);
            resultThree = ((percentageY3 - percentageX3) / percentageX3) * 100;
            String strresultThree = Double.toString((double) resultThree);
            textViewAns3.setText(strresultThree);
        } catch (NumberFormatException e) {
            resultThree = 0;
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.buttonPerecentReset1:
                edittextpercentageX1.setText(null);
                edittextpercentageY1.setText(null);
                linearLayoutOne.setVisibility(View.GONE);
                break;
            case R.id.buttonPerecentReset2:
                edittextpercentageX2.setText(null);
                edittextpercentageY2.setText(null);
                linearLayoutTwo.setVisibility(View.GONE);
                break;
            case R.id.buttonPerecentReset3:
                edittextpercentageX3.setText(null);
                edittextpercentageY3.setText(null);
                linearLayoutThree.setVisibility(View.GONE);
                break;

        }

    }
}
