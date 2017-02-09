package com.geniusnine.android.financialcalculators;

import android.app.ActionBar;
import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dev on 07-02-2017.
 */

public class tvm  extends AppCompatActivity {
    Spinner spinnerCompoundinglist;
     EditText edittextpresentvalue,edittextpayment,edittextfuturevalue,edittextannualrate,edittextperoids;
     Button buttonpresentvalue,buttonpayment,buttonfuturevalue,buttonannualrate,buttonperiods,buttonReset,buttonInstruction,buttonEmail,buttonHistory;
    RadioGroup radiogroupmode,radiogroupdecimaldigit;
    String stringpresentvalue,stringpayment,stringfuturevalue,stringannualrate,stringperoids;
    private RadioButton radioButtonmode,radioButtondecimaldigit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tvm_calculator);

        //back navigation on activity
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //declaration
        spinnerCompoundinglist=(Spinner)findViewById(R.id.spinnerCompounding);
        edittextpresentvalue=(EditText)findViewById(R.id.editTextPresent) ;
        edittextpayment=(EditText)findViewById(R.id.editTextPayment);
        edittextfuturevalue=(EditText) findViewById(R.id.editTextfuturevalue);
        edittextannualrate=(EditText)findViewById(R.id.editTextAnnualrate);
        edittextperoids=(EditText)findViewById(R.id.editTextperiods);
        buttonpresentvalue=(Button)findViewById(R.id.buttonPresent);
        buttonpayment=(Button)findViewById(R.id.buttonPayment);
        buttonfuturevalue=(Button)findViewById(R.id.buttonfuturevalue);
        buttonannualrate=(Button)findViewById(R.id.buttonAnnualrate);
        buttonperiods=(Button)findViewById(R.id.buttonperiods);
        buttonHistory=(Button)findViewById(R.id.buttonhistory);
        radiogroupmode=(RadioGroup) findViewById(R.id.radiogroupmode);
        radiogroupdecimaldigit=(RadioGroup) findViewById(R.id.radiogroupdecimaldigit);
        buttonReset=(Button)findViewById(R.id.buttonReset);
        buttonInstruction=(Button)findViewById(R.id.buttonInstruction);
        buttonEmail=(Button)findViewById(R.id.buttonemail);

        // Spinner Drop down elements
        List<String> compoudingvalue = new ArrayList<String>();
        compoudingvalue.add("Daily");
        compoudingvalue.add("Monthly");
        compoudingvalue.add("yearly");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(tvm.this, android.R.layout.simple_spinner_item, compoudingvalue);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinnerCompoundinglist.setAdapter(dataAdapter);

        //get edittextvalue and store in string
        stringpresentvalue=edittextpresentvalue.getText().toString();
        stringpayment=edittextpayment.getText().toString().trim();
        stringfuturevalue=edittextfuturevalue.getText().toString().trim();
        stringannualrate=edittextannualrate.getText().toString().trim();
        stringperoids=edittextperoids.getText().toString().trim();



        buttonHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //get edittextvalue and store in string
                stringpresentvalue=edittextpresentvalue.getText().toString();
                stringpayment=edittextpayment.getText().toString().trim();
                stringfuturevalue=edittextfuturevalue.getText().toString().trim();
                stringannualrate=edittextannualrate.getText().toString().trim();
                stringperoids=edittextperoids.getText().toString().trim();

                // get selected radio button from radioGroup
                int selectedIdmode = radiogroupmode.getCheckedRadioButtonId();
                int selectedIddecimaldigit = radiogroupdecimaldigit.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radioButtonmode = (RadioButton) findViewById(selectedIdmode);
                radioButtondecimaldigit = (RadioButton) findViewById(selectedIddecimaldigit);

                Toast.makeText(tvm.this, radioButtonmode.getText().toString(), Toast.LENGTH_SHORT).show();
                Toast.makeText(tvm.this, radioButtondecimaldigit.getText().toString(), Toast.LENGTH_SHORT).show();
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

/*    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
    }*/

}