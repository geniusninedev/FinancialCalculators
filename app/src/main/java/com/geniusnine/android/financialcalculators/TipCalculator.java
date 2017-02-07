package com.geniusnine.android.financialcalculators;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class TipCalculator extends AppCompatActivity {
    Spinner spinnerTax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calculator);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spinnerTax=(Spinner)findViewById(R.id.spinnertaxvalue);

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

     /*   //noinspection SimplifiableIfStatement
        if (id == R.id.action_transfer) {
            return true;
        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_export) {
            return true;
        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_import) {
            return true;
        }*/
        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_logout) {
            logout();
            return true;
        }*/
        //noinspection SimplifiableIfStatement
       /* if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }


}
