package com.geniusnine.android.financialcalculators;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class StockCalculator extends AppCompatActivity {
    ListView listviewstockcategories;

    Context mcontext;
    String[] listview_names = {"Stock Return and capital Gain Tax", "Constant Growth Stock", " Non-Constant Growth Stock", "CAPM Calculator", "Expected Return Calculator", "Holding Peroid Return Calculator", "WACC Calculator", "Black-Scholes Option Calculator"
    };

    Integer[] listview_images =
            {
                    R.drawable.c2, R.drawable.c1, R.drawable.c3, R.drawable.c4, R.drawable.c5, R.drawable.c1, R.drawable.c8, R.drawable.c7,


            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_calculator);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CustomList adapter = new CustomList(this, listview_names, listview_images);
        listviewstockcategories = (ListView) findViewById(android.R.id.list);
        listviewstockcategories.setAdapter(adapter);

    }
}