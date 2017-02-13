package com.geniusnine.android.financialcalculators;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CompoundInterestCal extends AppCompatActivity {
    Spinner spinnerCompoundinglist;
    EditText editTextCompounPrincipal,editTextCompounMonthlyDeposite,editTextCompounPeroidMonth,editTextCompounAnnualrate;
    Button buttoncompoundCalculate,buttoncompoundReset;
    TextView textViewTotalPrincipalAmount,textViewInterestAmount,textViewMaturityValueAmount;
    LinearLayout layoutcompoundresult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compound_interest_cal);
        //back navigation on activity
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        layoutcompoundresult=(LinearLayout)(LinearLayout)this.findViewById(R.id.layoutcompoundresult);

        spinnerCompoundinglist=(Spinner)findViewById(R.id.spinnerinterestCompounding);
        editTextCompounPrincipal=(EditText)findViewById(R.id.editTextCompounPrincipal) ;
        editTextCompounMonthlyDeposite=(EditText)findViewById(R.id.editTextCompounMonthlyDeposite);
        editTextCompounPeroidMonth=(EditText) findViewById(R.id.editTextCompounPeroidMonth);
        editTextCompounAnnualrate=(EditText)findViewById(R.id.editTextCompounAnnualrate);
        buttoncompoundCalculate=(Button)findViewById(R.id.buttonCompoundCalculate);
        buttoncompoundReset=(Button)findViewById(R.id.buttonCompoundReset);

        textViewTotalPrincipalAmount=(TextView)findViewById(R.id.textViewTotalPrincipalAmount);
        textViewInterestAmount=(TextView)findViewById(R.id.textViewInterestAmount);
        textViewMaturityValueAmount=(TextView)findViewById(R.id.textViewMaturityValueAmount);
        // Spinner Drop down elements
        List<String> compoudingvalue = new ArrayList<String>();
        compoudingvalue.add("Annually");
        compoudingvalue.add("Semiannually");
        compoudingvalue.add("Monthly");
        compoudingvalue.add("Quarterly");
        compoudingvalue.add("No Compound");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(CompoundInterestCal.this, android.R.layout.simple_spinner_item, compoudingvalue);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinnerCompoundinglist.setAdapter(dataAdapter);

        buttoncompoundCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               layoutcompoundresult.setVisibility(LinearLayout.VISIBLE);
                double compoundInterest;
                double MonthlyDeposite=0.0;
                double PrincipalAmount = Integer.parseInt(editTextCompounPrincipal.getText().toString());
                double interestRate = (Integer.parseInt(editTextCompounAnnualrate.getText().toString()));
                 MonthlyDeposite = (Integer.parseInt(editTextCompounMonthlyDeposite.getText().toString()));
                double PeriodMonth = (Integer.parseInt(editTextCompounPeroidMonth.getText().toString()));
                String alertspinnertax = spinnerCompoundinglist.getSelectedItem().toString().trim();
                double totalPrincipalAmount=PrincipalAmount+(MonthlyDeposite * PeriodMonth);
                if(alertspinnertax== "Monthly")
                {
                     compoundInterest = totalPrincipalAmount * Math.pow((1 + (interestRate/12)/100),(12*PeriodMonth));
                    Toast.makeText(CompoundInterestCal.this, ""+compoundInterest, Toast.LENGTH_SHORT).show();
                }
                else if(alertspinnertax== "Quarterly")
                {
                     compoundInterest = totalPrincipalAmount * Math.pow((1 + (interestRate/4)/100),(4*PeriodMonth));
                    Toast.makeText(CompoundInterestCal.this, ""+compoundInterest, Toast.LENGTH_SHORT).show();

                }
                else if(alertspinnertax== "Semiannually")
                {
                     compoundInterest = totalPrincipalAmount * Math.pow((1 + (interestRate/2)/100),(2*PeriodMonth));
                    Toast.makeText(CompoundInterestCal.this, ""+compoundInterest, Toast.LENGTH_SHORT).show();
                }
                else if(alertspinnertax== "Annually")
                {

                     compoundInterest = totalPrincipalAmount * Math.pow((1 + interestRate/100),PeriodMonth);
                    Toast.makeText(CompoundInterestCal.this, ""+compoundInterest, Toast.LENGTH_SHORT).show();
                }
                else
                {
                     compoundInterest = totalPrincipalAmount * Math.pow((1 + interestRate/100),PeriodMonth);
                    Toast.makeText(CompoundInterestCal.this, ""+compoundInterest, Toast.LENGTH_SHORT).show();
                }
                textViewTotalPrincipalAmount.setText(new DecimalFormat("##.##").format(totalPrincipalAmount));
                textViewMaturityValueAmount.setText(new DecimalFormat("##.##").format(compoundInterest));

            }
        });
        buttoncompoundReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextCompounPrincipal.setText(null);
                editTextCompounMonthlyDeposite.setText(null);
                editTextCompounPeroidMonth.setText(null);
                editTextCompounAnnualrate.setText(null);
            }
        });


    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.advanced) {

            return true;
        }



        return super.onOptionsItemSelected(item);
    }


}

