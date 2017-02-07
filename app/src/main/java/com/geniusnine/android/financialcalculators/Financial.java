package com.geniusnine.android.financialcalculators;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class Financial extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {




    ListView listviewcategories;

    Context mcontext;
    String[] listview_names =  {"TVM Calculator","Curreency Converter","Loan Calculator","Compound Interest Calculator","Credit Interest Calculator","Retirement/401K Calculator","Tip Calculator","Calculator","APR Calculator","ROI Calculator","Auto Loan Calculator",
            "Credit Card Minimum Payment","Discount and Tax Calculator","IRR NPV Calculator","Percentage Calculator","Bond Calculator","Stock Calculator","Miscellaneous Calculation"};

    Integer[]  listview_images =
            {
                    R.drawable.tvm,R.drawable.currency,R.drawable.loan,R.drawable.compound,R.drawable.creditinterest,R.drawable.retire,R.drawable.tip,R.drawable.calculator,R.drawable.apr,R.drawable.roi,R.drawable.auto,R.drawable.creditcard,R.drawable.discount
                    ,R.drawable.irr,R.drawable.percentage,R.drawable.bond,R.drawable.stock,R.drawable.miscellaneous

            };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        CustomList adapter = new CustomList(this,listview_names, listview_images);
        listviewcategories=(ListView)findViewById(android.R.id.list);

        listviewcategories.setAdapter(adapter);
      //  setSupportActionBar(toolbar);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

       /* //Initializing Views
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        loadCategoryData();*/

        listviewcategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int Itemposition = position;

                if (position == 0) {
                    Intent myIntent = new Intent(view.getContext(), tvm.class);
                    startActivityForResult(myIntent, 0);
                }
               if(position==1)
                {
                    Intent myIntent=new Intent(view.getContext(),CurrencyConverter.class);
                    startActivityForResult(myIntent,1);
                }
                if(position==2)
                {
                    Intent myIntent=new Intent(view.getContext(),LoanCalculator.class);
                    startActivityForResult(myIntent,2);

                }
               if(position==3)
                {
                    Intent myIntent=new Intent(view.getContext(),CompoundInterestCal.class);
                    startActivityForResult(myIntent,3);
                }
                if(position==4)
                {
                    Intent myIntent=new Intent(view.getContext(),CreditCardPayoff.class);
                    startActivityForResult(myIntent,4);
                }
              /*  if(position==5)
                {
                    Intent myIntent=new Intent(view.getContext(),TipCalculator.class);
                    startActivityForResult(myIntent,5);
                }*/
                if(position==6)
                {
                    Intent myIntent=new Intent(view.getContext(),TipCalculator.class);
                    startActivityForResult(myIntent,6);
                }
                /*if(position==7)
                {
                    Intent myIntent=new Intent(view.getContext(),Metri.class);
                    startActivityForResult(myIntent,7);
                }*/
                if(position==8)
                {
                    Intent myIntent=new Intent(view.getContext(),APRCalculator.class);
                    startActivityForResult(myIntent,8);
                }
                if(position==9)
                {
                    Intent myIntent=new Intent(view.getContext(),ROI.class);
                    startActivityForResult(myIntent,9);
                }
                if(position==10)
                {
                    Intent myIntent=new Intent(view.getContext(),AutoLoanCalculator.class);
                    startActivityForResult(myIntent,10);
                }
                if(position==11)
                {
                    Intent myIntent=new Intent(view.getContext(),CreditCardMiniumPayment.class);
                    startActivityForResult(myIntent,11);
                }
                if(position==12)
                {
                    Intent myIntent=new Intent(view.getContext(),DiscountTaxCalculator.class);
                    startActivityForResult(myIntent,12);
                }
                if(position==13)
                {
                    Intent myIntent=new Intent(view.getContext(),IrrNpvCalculator.class);
                    startActivityForResult(myIntent,13);
                }
                if(position==14)
                {
                    Intent myIntent=new Intent(view.getContext(),PercentageCalculator.class);
                    startActivityForResult(myIntent,14);
                }
                if(position==14)
                {
                    Intent myIntent=new Intent(view.getContext(),BondCalculator.class);
                    startActivityForResult(myIntent,14);
                }
                if(position==15)
                {
                    Intent myIntent=new Intent(view.getContext(),StockCalculator.class);
                    startActivityForResult(myIntent,15);
                }
                if(position==16)
                {
                    Intent myIntent=new Intent(view.getContext(),MiscellaneousCalculator.class);
                    startActivityForResult(myIntent,16);
                }
               /* if(position==7)
                {
                    Intent myIntent=new Intent(view.getContext(),Metri.class);
                    startActivityForResult(myIntent,7);
                }
                if(position==8)
                {
                    Intent myIntent=new Intent(view.getContext(),Bhaktii.class);
                    startActivityForResult(myIntent,8);
                }
                if(position==9)
                {
                    Intent myIntent=new Intent(view.getContext(),Balgeete.class);
                    startActivityForResult(myIntent,8);
                }  if(position==10)
            {
                Intent myIntent=new Intent(view.getContext(),Itar.class);
                startActivityForResult(myIntent,8);
            }
            }*/
            }
        });


    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.financial, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_all_cat) {
            // Handle the camera action
        } else if (id == R.id.nav_edit) {

        } else if (id == R.id.nav_setting) {

        } else if (id == R.id.nav_about) {

        } else if (id == R.id.nav_finacial_cal) {

        } else if (id == R.id.nav_expense_manager) {
        }
        else if (id == R.id.nav_stock) {
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
