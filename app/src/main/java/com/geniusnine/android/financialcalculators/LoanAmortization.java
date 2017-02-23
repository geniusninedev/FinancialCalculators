package com.geniusnine.android.financialcalculators;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LoanAmortization extends AppCompatActivity {
    RecyclerView  recyclerViewAmortization;
    AmortizationAdapter amortizationAdapter;
    int i=1;
    double InterestpaidforMonth,AmountofPrincipalPaid,RemainingPrincipalAmount,monthlypayment,monthlyRate,loanAmount,loanPeriod;
    ArrayList<Integer>  ArrayListamortizationID = new ArrayList<Integer>();
    ArrayList<Double> ArrayListamortizationAmount = new ArrayList<Double>();
    ArrayList<Double>  ArrayListamortizationInterest = new ArrayList<Double>();
    ArrayList<Double>  ArrayListamortizationPrincipal = new ArrayList<Double>();
    ArrayList<Double>  ArrayListamortizationRemainingBalance = new ArrayList<Double>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_amortization);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        recyclerViewAmortization=(RecyclerView) findViewById(R.id.recyclerViewAmortization);
        recyclerViewAmortization.setHasFixedSize(true);
        recyclerViewAmortization.setLayoutManager(new GridLayoutManager(this,1));



        //get value through intent from Loancalculator activity
        monthlypayment = getIntent().getExtras().getDouble("Monthlypayment");
        monthlyRate = getIntent().getExtras().getDouble("Rate");
         loanAmount = getIntent().getExtras().getDouble("loanAmount");
         loanPeriod = getIntent().getExtras().getDouble("loanPeriod");
        //String strmonthlypayment

      //  Toast.makeText(LoanAmortization.this, " monthlypayment"+monthlypayment+"  \n Rate "+monthlyRate+"\n loanAmount "+loanAmount, Toast.LENGTH_SHORT).show();
      loanAmortizationCalcualtion();



    }

    private void loanAmortizationCalcualtion() {

        for(i=1;i<=loanPeriod;i++) {
            InterestpaidforMonth = loanAmount * monthlyRate;
            AmountofPrincipalPaid = monthlypayment - InterestpaidforMonth;
            RemainingPrincipalAmount = loanAmount - AmountofPrincipalPaid;
            loanAmount = RemainingPrincipalAmount;
            // Toast.makeText(this, "New loan amount" + loanAmount, Toast.LENGTH_LONG).show();
            Log.d("InterestpaidforMonth", String.valueOf(InterestpaidforMonth));
            Log.d("AmountofPrincipalPaid", String.valueOf(AmountofPrincipalPaid));
            Log.d("RemainingPrincipalAmount", String.valueOf(RemainingPrincipalAmount));
            // Toast.makeText(LoanAmortization.this, " InterestpaidforMonth  " + InterestpaidforMonth + "  \n AmountofPrincipalPaid " + AmountofPrincipalPaid + "\n RemainingPrincipalAmount " + RemainingPrincipalAmount, Toast.LENGTH_SHORT).show();
            ArrayListamortizationID.add(i);
            ArrayListamortizationAmount.add(monthlypayment);
            ArrayListamortizationInterest.add(InterestpaidforMonth);
            ArrayListamortizationPrincipal.add(AmountofPrincipalPaid);
            ArrayListamortizationRemainingBalance.add(RemainingPrincipalAmount);
        }
        //last month
        AmountofPrincipalPaid = loanAmount;
        InterestpaidforMonth = loanAmount * monthlyRate;
        monthlypayment=(double) Math.round(AmountofPrincipalPaid+InterestpaidforMonth);
        RemainingPrincipalAmount=0.0;
        //  Toast.makeText(LoanAmortization.this, " AmountofPrincipalPaid  " + AmountofPrincipalPaid + "  \n InterestpaidforMonth " + InterestpaidforMonth + "\n monthlypayment " + monthlypayment + "\n RemainingPrincipalAmount " + RemainingPrincipalAmount,Toast.LENGTH_SHORT).show();


        // String strAmortizationLoanId = Double.toString((double) i);
        // String strAmortizationLoanAmount = Double.toString((double) monthlypayment);
        //String strAmortizationLoanInterest = Double.toString((double) InterestpaidforMonth);
        //String strAmortizationLoanPrincipal = Double.toString((double) AmountofPrincipalPaid);
        // String strAmortizationLoanRemainingBalance = Double.toString((double) RemainingPrincipalAmount);
        // Toast.makeText(LoanAmortization.this, " AmountofPrincipalPaid  " + strAPRLoanAmount,Toast.LENGTH_LONG).show();
    /*    ArrayListamortizationID.add(i);
        ArrayListamortizationAmount.add(monthlypayment);
        ArrayListamortizationInterest.add(InterestpaidforMonth);
        ArrayListamortizationPrincipal.add(AmountofPrincipalPaid);
        ArrayListamortizationRemainingBalance.add(RemainingPrincipalAmount);
*/
        amortizationAdapter = new AmortizationAdapter(LoanAmortization.this,ArrayListamortizationID, ArrayListamortizationAmount, ArrayListamortizationInterest, ArrayListamortizationPrincipal, ArrayListamortizationRemainingBalance);
        recyclerViewAmortization.setAdapter(amortizationAdapter);




    }
}
