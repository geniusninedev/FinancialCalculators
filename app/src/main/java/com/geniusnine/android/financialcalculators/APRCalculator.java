package com.geniusnine.android.financialcalculators;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class APRCalculator extends AppCompatActivity implements View.OnClickListener{
    EditText editTextAPRLoanAmount,editTextAPRextracost,editTextAPRInterestRate,editTextAPRLoanTermyear,editTextAPRLoanTermmonth;
    Button buttonAPRCalculate,buttonAPRReset;
    TextView textViewAPRMonthlyPayment,textViewAPRTotalPayment,textViewAPRTotalInterset;
    LinearLayout LinearlayoutAPRResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aprcalculator);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        LinearlayoutAPRResult=(LinearLayout)findViewById(R.id.LinearlayoutAPRResult) ;


        //declaration of layout tools
        editTextAPRLoanAmount = (EditText) findViewById(R.id.editTextAPRLoanAmount);
        editTextAPRextracost = (EditText) findViewById(R.id.editTextAPRextracost);
        editTextAPRInterestRate = (EditText) findViewById(R.id.editTextAPRInterestRate);
        editTextAPRLoanTermyear = (EditText) findViewById(R.id.editTextAPRLoanTermyear);
        editTextAPRLoanTermmonth = (EditText) findViewById(R.id.editTextAPRLoanTermmonth);
        buttonAPRCalculate=(Button)findViewById(R.id.buttonAPRCalculate);
        buttonAPRReset=(Button)findViewById(R.id.buttonAPRReset);
        textViewAPRMonthlyPayment=(TextView)findViewById(R.id.textViewAPRMonthlyPaymentAmount);
        textViewAPRTotalPayment=(TextView)findViewById(R.id.textViewAPRTotalPaymentAmount);
        textViewAPRTotalInterset=(TextView)findViewById(R.id.textViewAPRTotalIntersetAmount);
        buttonAPRCalculate.setOnClickListener(this);
        buttonAPRReset.setOnClickListener(this);
    }

    private void calculateAPR() {
        double APRLoanAmount,APRextracost,APRInterestRate,APRLoanTermyear,APRLoanTermmonth,APRTotalLoanAmount,TotalAPRMonths;
        double APRResult;
        APRLoanAmount = Integer.parseInt(editTextAPRLoanAmount.getText().toString());
        APRextracost =Integer.parseInt(editTextAPRextracost.getText().toString());
        APRInterestRate = Integer.parseInt(editTextAPRInterestRate.getText().toString());
        APRLoanTermyear = Integer.parseInt(editTextAPRLoanTermyear.getText().toString())*12;
        APRLoanTermmonth = Double.parseDouble(editTextAPRLoanTermmonth.getText().toString());
        TotalAPRMonths=APRLoanTermyear+APRLoanTermmonth;
        String strAPRLoanAmount = Double.toString((double) APRLoanAmount);
        String strAPRextracost = Double.toString((double) APRextracost);
        String strAPRInterestRate = Double.toString((double) APRInterestRate);
        String strAPRLoanTermyear = Double.toString((double) APRLoanTermyear);
       // String strAPRLoanTermmonth = Double.toString((double) APRLoanTermmonth);

        /*
        formula for APR using newton raphson method
        for rate calculation APR=((C+E)+r*(math.pow(1+((R/1200)),(n)))/(math.pow(1+r),n)-1)
         */

        APRTotalLoanAmount=APRLoanAmount+APRextracost;
        double totalinterestrate=APRInterestRate/1200;
        double MonthlyAPRPayment=((double) Math.round((((APRTotalLoanAmount * totalinterestrate) * Math.pow(1.0d + totalinterestrate, (double) TotalAPRMonths)) / (Math.pow(totalinterestrate + 1.0d, (double) TotalAPRMonths) - 1.0d)) * 100.0d)) / 100.0d;
       // double =(((APRTotalLoanAmount)*totalinterestrate*(Math.pow((1+(totalinterestrate)),APRLoanTermyear)))/((Math.pow((1+totalinterestrate),APRLoanTermyear))-1));
        double totalAPRPayment=MonthlyAPRPayment*TotalAPRMonths;
        double totalAPRinterest=totalAPRPayment-APRLoanAmount;

        double result1=(totalinterestrate)*(Math.pow((1+totalinterestrate),TotalAPRMonths));
        double result2=(Math.pow((1+totalinterestrate),TotalAPRMonths))-(1);
        double result3=MonthlyAPRPayment/APRLoanAmount;
        double finalresult=(((result1)/(result2))-(result3) * 100.0d) / 100.0d;



   //  double aprpercent=(((APRInterestRate/1200)*Math.pow((1+(APRInterestRate/1200)),TotalAPRMonths))/((Math.pow((1+(APRInterestRate/1200)),TotalAPRMonths))-1))-(MonthlyAPRPayment/APRLoanAmount);
       //double totalAPR= (((totalinterestrate)*(Math.pow((1+(totalinterestrate)),APRLoanTermyear))/((Math.pow((1+totalinterestrate),APRLoanTermyear))-1))-(totalMonthlyAPR/APRLoanAmount));
        //Toast.makeText( this,"zxy    "+xyz, Toast.LENGTH_SHORT).show();
        Toast.makeText( this,"mothlypayment    "+MonthlyAPRPayment, Toast.LENGTH_SHORT).show();
        Toast.makeText( this,"APRLoanAmount    "+APRLoanAmount, Toast.LENGTH_SHORT).show();
        Toast.makeText( this,"abc    "+finalresult, Toast.LENGTH_SHORT).show();
        Toast.makeText( this,"totalAPRPayment    "+totalAPRPayment, Toast.LENGTH_SHORT).show();
        Toast.makeText( this,"totalAPRinterest    "+totalAPRinterest, Toast.LENGTH_SHORT).show();

        LinearlayoutAPRResult.setVisibility(View.VISIBLE);
        textViewAPRMonthlyPayment.setText(new DecimalFormat("##.##").format(MonthlyAPRPayment));
        textViewAPRTotalPayment.setText(new DecimalFormat("##.##").format(totalAPRPayment));
        textViewAPRTotalInterset.setText(new DecimalFormat("##.##").format(totalAPRinterest));


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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonAPRCalculate:
                calculateAPR();

                break;
            case R.id.buttonAPRReset:
                editTextAPRLoanAmount.setText(null);
                editTextAPRextracost.setText(null);
                editTextAPRInterestRate.setText(null);
                editTextAPRLoanTermyear.setText(null);
                editTextAPRLoanTermmonth.setText(null);
                //  linearLayoutTwo.setVisibility(View.GONE);
                break;
        }

    }


}