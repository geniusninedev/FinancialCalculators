package com.geniusnine.android.financialcalculators;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class BondCalculator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bond_calculator);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bond, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.ytc) {

            return true;
        }
        if (id == R.id.ytm) {

            return true;
        }
        if (id == R.id.duration) {

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

