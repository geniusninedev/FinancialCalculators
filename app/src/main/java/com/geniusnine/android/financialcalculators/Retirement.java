package com.geniusnine.android.financialcalculators;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class Retirement extends AppCompatActivity {
    ListView listviewretiremantcategories;

    Context mcontext;
    String[] listview_names =  {"Retirement Planner","401k Contribution Calculator","Retirement Calculator","Retirement Saving Analysis","Retirement Income Analysis","Retirement Saving Calculator","Retirement Income Calculator","Traditional IRA vs Roth IRA ","Required Minimum Distribution","Social Security Estimator","Asset Allocation Calculator",
           };

    Integer[]  listview_images =
            {
                    R.drawable.c2, R.drawable.c1, R.drawable.c3, R.drawable.c4, R.drawable.c5, R.drawable.c1, R.drawable.c8, R.drawable.c7,
                    R.drawable.c9, R.drawable.c3, R.drawable.c1
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retirement);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CustomList adapter = new CustomList(this,listview_names, listview_images);
        listviewretiremantcategories=(ListView)findViewById(android.R.id.list);
        listviewretiremantcategories.setAdapter(adapter);

    }
}
