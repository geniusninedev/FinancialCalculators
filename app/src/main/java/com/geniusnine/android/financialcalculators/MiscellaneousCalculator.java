package com.geniusnine.android.financialcalculators;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MiscellaneousCalculator extends AppCompatActivity {
    ListView listviewmiscellaneouscategories;

    Context mcontext;
    String[] listview_names = {"Unit Converter", "Date Calculation", "Loan Analysis", "Rule of 78 Loan Calculator", "Commercial Loan Calculator", "Rule of 72 Calculator", "Tax Equivalent Yield Calculator", "Us inflation Calculator"
            , "Margin and Markup Calculator", "Fuel Calculator", "Salary Increase Calculator", "PaycheckTax Calculator(US)", "Net Distribution Calcultor", "Effective Rate Calculator", "Mutual Fund Fee Calculator", "Unit Price Compare Calculator"
            , "Financial Ratios"
    };

    Integer[] listview_images =
            {
                    R.drawable.c2, R.drawable.c1, R.drawable.c3, R.drawable.c4, R.drawable.c5, R.drawable.c1, R.drawable.c8, R.drawable.c7,
                    R.drawable.c9, R.drawable.c3, R.drawable.c1, R.drawable.c3, R.drawable.c2, R.drawable.c8, R.drawable.c7, R.drawable.c4,
                    R.drawable.c9

            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miscellaneous_calculator);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CustomList adapter = new CustomList(this, listview_names, listview_images);
        listviewmiscellaneouscategories = (ListView) findViewById(android.R.id.list);
        listviewmiscellaneouscategories.setAdapter(adapter);

    }
}