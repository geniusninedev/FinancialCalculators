package com.geniusnine.android.financialcalculators;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ROI extends AppCompatActivity {
    final Calendar cal = Calendar.getInstance();
    public Calendar calender;
    private int day;
    private int month;
    private int year;
    TextView textViewcurrentdate,textViewAppointmentDate;
    ImageButton todate,fromdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roi);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        todate=(ImageButton)findViewById(R.id.calenderto);
        fromdate=(ImageButton)findViewById(R.id.calenderfrom);
        todate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendermethod();


            }
        });
        fromdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calendermethod();

            }
        });


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