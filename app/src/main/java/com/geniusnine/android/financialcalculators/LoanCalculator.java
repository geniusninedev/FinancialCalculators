package com.geniusnine.android.financialcalculators;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class LoanCalculator extends AppCompatActivity implements View.OnClickListener{
    EditText edittextLaonAmount,edittextInterestRate, edittextloanYears,edittextLoanMonths,edittextExtraPayment,edittextPropertytax,edittextInsurance,edittextPMI,edittextPropertyPrice,editTextalertpropertyprice,edittextalertdownpayment;
    Button buttonLoanCalculate,butttonLoanAdvanced,butttonLoanBasic,buttonLoanCalcvalue,buttonalertok,buttonloanReset,buttonLoanEmail,buttonLoanReport,buttonLoanAortization;
    TextView textViewMonthlyPayment,textViewTotalPayment,textViewTotalInterest,textViewAnnualPayment;
    LinearLayout advancedlayout,layoutDisplayResult;
    Spinner spinneralerttaxtype;
    double alerttoatalLoanAmount;
    double monthlyPayment,r,loanAmount,loanPeriod,totalPayment,toatalInterest,interestRate,AnnualPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_calculator);
        //back navigation on activity
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        advancedlayout=(LinearLayout)this.findViewById(R.id.advancedlayout);
        layoutDisplayResult=(LinearLayout)this.findViewById(R.id.layoutDisplayResult);


        //edittext initalization
        edittextLaonAmount=(EditText) findViewById(R.id.editTextLoanAmount);
        edittextInterestRate=(EditText) findViewById(R.id.editTextLoanInterestRate);
        edittextloanYears=(EditText) findViewById(R.id.editTextLoanyears);
        edittextLoanMonths=(EditText) findViewById(R.id.editTextloanmonths);
        edittextExtraPayment=(EditText) findViewById(R.id.editTextLoanextraPayment);
        edittextPropertytax=(EditText) findViewById(R.id.editTextPropertyTax);
        edittextInsurance=(EditText) findViewById(R.id.editTextInsurance);
        edittextPMI=(EditText) findViewById(R.id.editTextPMI);
        edittextPropertyPrice=(EditText) findViewById(R.id.editTextPropertyprice);
        textViewMonthlyPayment=(TextView) findViewById(R.id.textViewMonthlyPaymentAmount);
        textViewTotalPayment=(TextView) findViewById(R.id.textViewTotalPaymentAmount);
        textViewTotalInterest=(TextView) findViewById(R.id.textViewTotalInterestAmount);
        textViewAnnualPayment=(TextView) findViewById(R.id.textViewAnnualPaymentAmount);

        buttonLoanCalcvalue=(Button)findViewById(R.id.buttonLoanCalcvalue);
        buttonLoanCalculate=(Button)findViewById(R.id.buttonLoanCalculate);
        butttonLoanBasic=(Button)findViewById(R.id.buttonLoanBasic);
        butttonLoanAdvanced=(Button)findViewById(R.id.buttonLoanAdvance);
        buttonloanReset =(Button)findViewById(R.id.buttonLoanReset);
        buttonLoanAortization=(Button)findViewById(R.id.buttonLoanAmortization);
        buttonLoanEmail=(Button)findViewById(R.id.buttonLoanEmail);
        buttonLoanReport=(Button)findViewById(R.id.buttonLoanReport);


        butttonLoanBasic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                advancedlayout.setVisibility(LinearLayout.GONE);
                butttonLoanAdvanced.setVisibility(View.VISIBLE);
                butttonLoanBasic.setVisibility(View.GONE);
            }
        });

        butttonLoanAdvanced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                advancedlayout.setVisibility(LinearLayout.VISIBLE);
                butttonLoanBasic.setVisibility(View.VISIBLE);
                butttonLoanAdvanced.setVisibility(View.GONE);
            }
        });
        buttonLoanCalcvalue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View alertLayout = inflater.inflate(R.layout.dialog_calc_value, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LoanCalculator.this);
                editTextalertpropertyprice = (EditText)alertLayout. findViewById(R.id.editextalertpropertyprice);
                edittextalertdownpayment=(EditText)alertLayout. findViewById(R.id.edittextalertdownpayment);
                spinneralerttaxtype=(Spinner) alertLayout.findViewById(R.id.spinnertaxtype);

                List<String> timings = new ArrayList<String>();
                timings.add("Amount");
                timings.add("Percent");
                // Creating adapter for spinner
                ArrayAdapter<String> Adapter = new ArrayAdapter<String>(LoanCalculator.this, android.R.layout.simple_spinner_item, timings);

                // Drop down layout style - list view with radio button
                Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                // attaching data adapter to spinner
                spinneralerttaxtype.setAdapter(Adapter);

                // this is set the view from XML inside AlertDialog
                alertDialogBuilder.setView(alertLayout);
                alertDialogBuilder.setPositiveButton("ok",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                double  alertpropertyprice  = Integer.parseInt(editTextalertpropertyprice.getText().toString());
                                double alertdownpayment  = Integer.parseInt(edittextalertdownpayment.getText().toString());
                                 String alertspinnertax = spinneralerttaxtype.getSelectedItem().toString().trim();
                                Toast.makeText(LoanCalculator.this, ""+alertspinnertax, Toast.LENGTH_SHORT).show();
                                if(alertspinnertax=="Amount")
                                {
                                    alerttoatalLoanAmount=alertpropertyprice-alertdownpayment;

                                }
                                else
                                {
                                   double alertLoanAmount=(double) ((alertpropertyprice) * alertdownpayment)/100;
                                    alerttoatalLoanAmount=alertpropertyprice-alertLoanAmount;

                                }
                                edittextLaonAmount.setText(new DecimalFormat("##.##").format(alerttoatalLoanAmount));

                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }
        });
        buttonLoanCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double loanPeriodMonth=1;
                layoutDisplayResult.setVisibility(LinearLayout.VISIBLE);
                loanAmount = Integer.parseInt(edittextLaonAmount.getText().toString());
                 interestRate = (Integer.parseInt(edittextInterestRate.getText().toString()));
                double loanPeriodyearsToMonth = (Integer.parseInt(edittextloanYears.getText().toString()))*12;
                //(int)Double.parseDouble(row.get(2));
                 loanPeriodMonth = (Integer.parseInt(edittextLoanMonths.getText().toString()));
                 loanPeriod=loanPeriodyearsToMonth+loanPeriodMonth;
                 r = interestRate/1200;
               // Toast.makeText(LoanCalculator.this, ""+loanPeriod, Toast.LENGTH_SHORT).show();
                    // monthly  and total,interst,Annual payment
                    double r1 = Math.pow(r+1,loanPeriod);
                   monthlyPayment = (double) ((r+(r/(r1-1))) * loanAmount);
                   totalPayment = monthlyPayment * loanPeriod;
                   toatalInterest=totalPayment-loanAmount;
                   AnnualPayment = monthlyPayment * 12;
                Toast.makeText(LoanCalculator.this, " interest "+r, Toast.LENGTH_SHORT).show();

                 Toast.makeText(LoanCalculator.this, ""+toatalInterest, Toast.LENGTH_SHORT).show();

                //setting value to textview
                textViewMonthlyPayment.setText(new DecimalFormat("##.##").format(monthlyPayment));
                textViewTotalPayment.setText(new DecimalFormat("##.##").format(totalPayment));
                textViewTotalInterest.setText(new DecimalFormat("##.##").format(toatalInterest));
                textViewAnnualPayment.setText(new DecimalFormat("##.##").format(AnnualPayment));
            }
        });

        buttonloanReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edittextLaonAmount.setText(null);
                edittextInterestRate.setText(null);
                edittextloanYears.setText(null);
                edittextLoanMonths.setText(null);
                edittextExtraPayment.setText(null);
                edittextInsurance.setText(null);
                edittextPMI.setText(null);
                edittextPropertytax.setText(null);
                edittextPropertyPrice.setText(null);
            }
        });

        buttonLoanAortization.setOnClickListener(this);
        buttonLoanReport.setOnClickListener(this);



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_loan, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.more) {

            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case  R.id.buttonLoanAmortization:
                    Intent i1=new Intent(LoanCalculator.this,LoanAmortization.class);
                i1.putExtra("Monthlypayment",monthlyPayment);
                i1.putExtra("Rate",r);
                i1.putExtra("loanAmount",loanAmount);
                i1.putExtra("loanPeriod",loanPeriod);
                startActivity(i1);
                break;
            case  R.id.buttonLoanReport:
                Intent i2=new Intent(LoanCalculator.this,LoanReportChart.class);
                i2.putExtra("PrincipalAmount",loanAmount);
                i2.putExtra("interestRate",interestRate);
                i2.putExtra("Rate",r);
                i2.putExtra("loanPeriod",loanPeriod);
                i2.putExtra("Monthlypayment",monthlyPayment);
                i2.putExtra("totalPayment",totalPayment);
                i2.putExtra("toatalInterest",toatalInterest);
                i2.putExtra("AnnualPayment",AnnualPayment);
                startActivity(i2);
        }
    }
}

