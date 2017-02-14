package com.geniusnine.android.financialcalculators;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ROI extends AppCompatActivity implements View.OnClickListener {
    final Calendar cal = Calendar.getInstance();
    public Calendar calender;
    private int day;
    private int month;
    private int year;
    TextView textViewcurrentdate,textViewAppointmentDate,textViewGainOrLoss,textViewROIAmount,textViewROIAnnualAmount;
    ImageButton todate,fromdate;
    EditText editTextOriginalInvestment,editTextEndingInvestment,editTextROIyear,editTextROIMonth;
    Button buttonROICalculate,buttonROIReset;
    LinearLayout linearlayoutROIResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roi);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        linearlayoutROIResult=(LinearLayout)findViewById(R.id.linearlayoutROIResult) ;

        //declaration of designing fields
        editTextOriginalInvestment=(EditText)findViewById(R.id.editTextOriginalInvestment);
        editTextEndingInvestment=(EditText)findViewById(R.id.editTexEndingInvestment);
        editTextROIyear=(EditText)findViewById(R.id.editTextROIyear);
        editTextROIMonth=(EditText)findViewById(R.id.editTextROIMonth);
        textViewGainOrLoss=(TextView)findViewById(R.id.textViewGainorLossAmount);
        textViewROIAmount=(TextView)findViewById(R.id.textViewROIAmount);
        textViewROIAnnualAmount=(TextView)findViewById(R.id.textViewROIAnnualAmount);
        buttonROICalculate=(Button)findViewById(R.id.buttonROICalculate);
        buttonROIReset=(Button)findViewById(R.id.buttonROIReset);
        buttonROICalculate.setOnClickListener(this);
        buttonROIReset.setOnClickListener(this);


        todate=(ImageButton)findViewById(R.id.calenderto);
        fromdate=(ImageButton)findViewById(R.id.calenderfrom);
        todate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {calendermethod();


            }
        });
        fromdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calendermethod();

            }
        });


    }
    private void ROICalculate() {
        double  InvestmentROI;
        double MonthlyDeposite=0.0;
        double OriginalInvestment =  Double.parseDouble(editTextOriginalInvestment.getText().toString());
        double EndingInvestment =  Double.parseDouble(editTextEndingInvestment.getText().toString());
        double ROIyear =  Double.parseDouble(editTextROIyear.getText().toString());
        //double ROIMonth = (Integer.parseInt(editTextROIMonth.getText().toString()));
      //  double ROIPeriod=ROIyear+ROIMonth;

        //ROI Investment formula return on investment
       // ( Investment revenue - Investment cost)/Investment cost

        try {
        double InvestmentRevenue=EndingInvestment-OriginalInvestment;
        InvestmentROI=(InvestmentRevenue/OriginalInvestment)*100;
           double annualroi=InvestmentROI/ROIyear;

            String strInvestmentRevenue = Double.toString((double) InvestmentRevenue);
            String strInvestmentROI = Double.toString((double) InvestmentROI);
            String strannualroi = Double.toString((double) annualroi);
            linearlayoutROIResult.setVisibility(View.VISIBLE);
            textViewGainOrLoss.setText(strInvestmentRevenue);
            textViewROIAmount.setText(strInvestmentROI);
            textViewROIAnnualAmount.setText(strannualroi);

          /*  double r = InvestmentROI/1200;
            double r1 = Math.pow(r+1,ROIPeriod);
            double monthlyPayment = (double) ((r+(r/(r1-1))) * OriginalInvestment);
           // double totalPayment = monthlyPayment * ROIPeriod;
*/
       // Toast.makeText(this,"Investment"+monthlyPayment,Toast.LENGTH_LONG).show();
        } catch (NumberFormatException e) {
            InvestmentROI = 0;
        }


    }

    private void calendermethod()
    {
        // Process to get Current Date
        calender = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);
        // Launch Date Picker Dialog
        DatePickerDialog dpd = new DatePickerDialog(ROI.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // Display Selected date in textbox
                        // textViewAppointmentDate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);

                    }
                }, year, month, day);
        dpd.show();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.home) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.buttonROICalculate:
                ROICalculate();
                break;
            case R.id.buttonROIReset:
                editTextOriginalInvestment.setText(null);
                editTextEndingInvestment.setText(null);
                editTextROIyear.setText(null);
                linearlayoutROIResult.setVisibility(View.GONE);

        }
    }


}