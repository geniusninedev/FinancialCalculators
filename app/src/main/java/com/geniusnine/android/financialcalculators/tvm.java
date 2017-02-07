package com.geniusnine.android.financialcalculators;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dev on 07-02-2017.
 */

public class tvm  extends AppCompatActivity {
    Spinner spinnerCompoundinglist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tvm_calculator);

        //back navigation on activity
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spinnerCompoundinglist=(Spinner)findViewById(R.id.spinnerCompounding);

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
      /*   //noinspection SimplifiableIfStatement
        if (id == R.id.action_chart) {
            Toast.makeText(getApplication(),"Pia Chart Clicked clicked",Toast.LENGTH_LONG).show();
            return true;
        }*/
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