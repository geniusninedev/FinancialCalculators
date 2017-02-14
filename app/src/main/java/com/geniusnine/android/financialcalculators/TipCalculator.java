package com.geniusnine.android.financialcalculators;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TipCalculator extends AppCompatActivity {
    private EditText totalBillAmount;
    private SeekBar tipPercent;
    private SeekBar tipsNumberOfPeople;
    private TextView totalAmountToBePaid;
    private TextView totalAmountOfTipsToBePaid;
    private TextView tipsPerPerson;
    private Button calculateTips;
    private int tipPercentValue = 0;
    private int tipsForNumberOfPeople = 0;
    private TextView tipPercentLabel;
    private TextView splitNumberLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calculator);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        totalBillAmount = (EditText)findViewById(R.id.bill_value);
        tipPercent = (SeekBar)findViewById(R.id.seekBar);
        tipsNumberOfPeople = (SeekBar)findViewById(R.id.seekBar_one);

        totalAmountToBePaid = (TextView)findViewById(R.id.total_to_pay_result);
        totalAmountOfTipsToBePaid = (TextView)findViewById(R.id.total_tip_result);
        tipsPerPerson = (TextView)findViewById(R.id.tip_per_person_result);
        tipPercentLabel = (TextView)findViewById(R.id.tip_percent);
        splitNumberLabel = (TextView)findViewById(R.id.split_number);
       /* spinnerTax=(Spinner)findViewById(R.id.spinnertaxvalue);

        // Spinner Drop down elements
        List<String> taxvalue = new ArrayList<String>();
        taxvalue.add("Tax%");
        taxvalue.add("Tax amt");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, taxvalue);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinnerTax.setAdapter(dataAdapter);
*/tipPercent.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tipPercentValue = progress;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tipPercentLabel.setText("Tip Percent : " + seekBar.getProgress());
            }
        });
        tipsNumberOfPeople.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tipsForNumberOfPeople = progress;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                splitNumberLabel.setText("Split Number : " + seekBar.getProgress());
            }
        });
        calculateTips = (Button) findViewById(R.id.calculate_tips);
        calculateTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalBillAmount.getText().toString().equals("") || totalBillAmount.getText().toString().isEmpty()) {
                    Toast.makeText(TipCalculator.this, "All Input field must be filled", Toast.LENGTH_LONG).show();
                    return;
                }
                double totalBillInput = Double.parseDouble(totalBillAmount.getText().toString());
                if (tipPercentValue == 0 || tipsForNumberOfPeople == 0) {
                    Toast.makeText(TipCalculator.this, "Set values for Tip percent and split number", Toast.LENGTH_LONG).show();
                    return;
                }
                double percentageOfTip = (totalBillInput * tipPercentValue) / 100;
                double totalAmountForTheBill = totalBillInput + percentageOfTip;
                double tipPerEachPerson = percentageOfTip / tipsForNumberOfPeople;
                totalAmountToBePaid.setText(removeTrailingZero(String.valueOf(String.format("%.2f", totalAmountForTheBill))));
                totalAmountOfTipsToBePaid.setText(removeTrailingZero(String.valueOf(String.format("%.2f", percentageOfTip))));
                tipsPerPerson.setText(removeTrailingZero(String.valueOf(String.format("%.2f", tipPerEachPerson))));
            }
        });

    }
    public String removeTrailingZero(String formattingInput) {
        if (!formattingInput.contains(".")) {
            return formattingInput;
        }
        int dotPosition = formattingInput.indexOf(".");
        String newValue = formattingInput.substring(dotPosition, formattingInput.length());
        if (newValue.startsWith(".0")) {
            return formattingInput.substring(0, dotPosition);
        }
        return formattingInput;
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


}
