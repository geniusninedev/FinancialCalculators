
package com.geniusnine.android.financialcalculators;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.bitmap;

public class LoanReportChart extends AppCompatActivity implements OnChartValueSelectedListener {

    private PieChart mChart;
    private Typeface mTfRegular;
    private Typeface mTfLight;
    double  PrincipalAmount, ToatalInterest, TotalPayment, interestRate, loanPeriod, MonthlyPayment, AnnualPayment, monthlyRate;

    private double[] mParties;
    WebView webView;

    Bitmap bmScreen;

    Dialog screenDialog;
    static final int ID_SCREENDIALOG = 1;

    ImageView bmImage;
   // Button btnScreenDialog_OK;
  //  TextView TextOut;

    //View screen;
    LinearLayout ll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_loan_report);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       // screen = getWindow().getDecorView().getRootView();
        ll = (LinearLayout)findViewById(R.id.linear);

        //received value from LoanCalculator
        PrincipalAmount = getIntent().getExtras().getDouble("PrincipalAmount");
        ToatalInterest = getIntent().getExtras().getDouble("toatalInterest");
        TotalPayment = getIntent().getExtras().getDouble("totalPayment");
        interestRate = getIntent().getExtras().getDouble("interestRate");
        loanPeriod = getIntent().getExtras().getDouble("loanPeriod");
        MonthlyPayment = getIntent().getExtras().getDouble("Monthlypayment");
        AnnualPayment = getIntent().getExtras().getDouble("AnnualPayment");
        monthlyRate = getIntent().getExtras().getDouble("Rate");

        mParties = new double[]{PrincipalAmount, ToatalInterest};
        Toast.makeText(LoanReportChart.this, " PrincipalAmount" + PrincipalAmount, Toast.LENGTH_SHORT).show();
        //  mParties=strPrincipal;

        mChart = (PieChart) findViewById(R.id.chart1);
        mChart.setUsePercentValues(true);
        mChart.getDescription().setEnabled(false);
        mChart.setExtraOffsets(5, 10, 5, 5);

        mChart.setDragDecelerationFrictionCoef(0.95f);

        mChart.setCenterTextTypeface(mTfLight);
        mChart.setCenterText(generateCenterSpannableText());

        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColor(Color.WHITE);

        mChart.setTransparentCircleColor(Color.WHITE);
        mChart.setTransparentCircleAlpha(110);

        mChart.setHoleRadius(55f);
        mChart.setTransparentCircleRadius(61f);

        mChart.setDrawCenterText(true);

        mChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        mChart.setRotationEnabled(true);
        mChart.setHighlightPerTapEnabled(true);

        // mChart.setUnit(" €");
        // mChart.setDrawUnitsInChart(true);

        // add a selection listener
        mChart.setOnChartValueSelectedListener(this);

        setData(2, 100);


        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        // mChart.spin(2000, 0, 360);

        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        // entry label styling
        mChart.setEntryLabelColor(Color.BLACK);
        mChart.setEntryLabelTypeface(mTfRegular);
        mChart.setEntryLabelTextSize(12f);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.pie, menu);
        getMenuInflater().inflate(R.menu.menu_report, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //switch for full report from menu_report.xml
        switch (item.getItemId()) {
            case R.id.fullreport: {

                ll.setDrawingCacheEnabled(false);
                ll.setDrawingCacheEnabled(true);
                bmScreen = ll.getDrawingCache();
                Bitmap bmp=bmScreen;
                showDialog(ID_SCREENDIALOG);
                ByteArrayOutputStream bStream = new ByteArrayOutputStream();
                //Compress it before sending it to minimize the size and quality of bitmap.
                bmp.compress(Bitmap.CompressFormat.PNG, 100, bStream);
                byte[] byteArray = bStream.toByteArray();
                Intent i1 = new Intent(LoanReportChart.this, LoanFullReport.class);
               // i1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i1.putExtra("PrincipalAmount", PrincipalAmount);
                i1.putExtra("interestRate", interestRate);
                i1.putExtra("loanPeriod", loanPeriod);
                i1.putExtra("Monthlypayment", MonthlyPayment);
                i1.putExtra("TotalPayment", TotalPayment);
                i1.putExtra("toatalInterest", ToatalInterest);
                i1.putExtra("AnnualPayment", AnnualPayment);
                i1.putExtra("Rate", monthlyRate);
                i1.putExtra("bmp_Image", byteArray);
                startActivity(i1);




                break;
            }
        }

        //switch for multiple animation menu for pie.xml
        switch (item.getItemId()) {
            case R.id.actionToggleValues: {
                for (IDataSet<?> set : mChart.getData().getDataSets())
                    set.setDrawValues(!set.isDrawValuesEnabled());
                mChart.invalidate();
                break;
            }
            case R.id.actionToggleHole: {
                if (mChart.isDrawHoleEnabled())
                    mChart.setDrawHoleEnabled(false);
                else
                    mChart.setDrawHoleEnabled(true);
                mChart.invalidate();
                break;
            }
            case R.id.actionDrawCenter: {
                if (mChart.isDrawCenterTextEnabled())
                    mChart.setDrawCenterText(false);
                else
                    mChart.setDrawCenterText(true);
                mChart.invalidate();
                break;
            }
            case R.id.actionToggleXVals: {

                mChart.setDrawEntryLabels(!mChart.isDrawEntryLabelsEnabled());
                mChart.invalidate();
                break;
            }
            case R.id.actionSave: {
                // mChart.saveToGallery("title"+System.currentTimeMillis());
                mChart.saveToPath("title" + System.currentTimeMillis(), "");
                break;
            }
            case R.id.actionTogglePercent:
                mChart.setUsePercentValues(!mChart.isUsePercentValuesEnabled());
                mChart.invalidate();
                break;
            case R.id.animateX: {
                mChart.animateX(1400);
                break;
            }
            case R.id.animateY: {
                mChart.animateY(1400);
                break;
            }
            case R.id.animateXY: {
                mChart.animateXY(1400, 1400);
                break;
            }
            case R.id.actionToggleSpin: {
                mChart.spin(1000, mChart.getRotationAngle(), mChart.getRotationAngle() + 360, Easing.EasingOption
                        .EaseInCubic);
                break;
            }
        }
        return true;
    }



    private void setData(float range, int count) {

        float mult = range;

        List<PieEntry> entries = new ArrayList<>();
        Toast.makeText(this, "" + (float) ToatalInterest, Toast.LENGTH_LONG).show();


        entries.add(new PieEntry((float) ToatalInterest, "Interest-" + (new DecimalFormat("##.##").format(ToatalInterest))));
        entries.add(new PieEntry((float) PrincipalAmount, "Principal-" + (new DecimalFormat("##.##").format(PrincipalAmount))));


        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<Integer>();
        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.BLACK);
        data.setValueTypeface(mTfLight);

        mChart.setData(data);

        // undo all highlights
        mChart.highlightValues(null);

        mChart.invalidate();
    }

    private SpannableString generateCenterSpannableText() {

        SpannableString s = new SpannableString("Total Payment\n" + TotalPayment);
        s.setSpan(new RelativeSizeSpan(1.7f), 0, 14, 0);
        s.setSpan(new StyleSpan(Typeface.NORMAL), 14, s.length() - 18, 0);
        //s.setSpan(new ForegroundColorSpan(Color.GRAY), 14, s.length() - 15, 0);
        //s.setSpan(new RelativeSizeSpan(.8f), 14, s.length() - 15, 0);
        // s.setSpan(new StyleSpan(Typeface.ITALIC), s.length() - 14, s.length(), 0);
        //s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - 14, s.length(), 0);
        return s;
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

        if (e == null)
            return;
        Log.i("VAL SELECTED",
                "Value: " + e.getY() + ", index: " + h.getX()
                        + ", DataSet index: " + h.getDataSetIndex());
    }

    @Override
    public void onNothingSelected() {
        Log.i("PieChart", "nothing selected");
    }




/*    @Override
    protected Dialog onCreateDialog(int id) {
// TODO Auto-generated method stub

        screenDialog = null;
        switch(id){
            case(ID_SCREENDIALOG):
                screenDialog = new Dialog(this);
                screenDialog.setContentView(R.layout.dialog);
                bmImage = (ImageView)screenDialog.findViewById(R.id.image);
        }
        return screenDialog;
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
// TODO Auto-generated method stub
        switch(id){
            case(ID_SCREENDIALOG):
                dialog.setTitle("Captured Screen");
               // TextOut.setText(EditTextIn.getText().toString());
                bmImage.setImageBitmap(bmScreen);
                break;
        }
    }*/


}