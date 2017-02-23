package com.geniusnine.android.financialcalculators;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;


import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

@SuppressLint("SetJavaScriptEnabled")

public class LoanFullReport extends AppCompatActivity {
    WebView webView;
    ArrayList f2157n;
    ArrayList f2158o;
    private Context f2159p;
    private LinearLayout f2160q;
    private String f2161r;
    private WebView f2162s;

    double PrincipalAmount, ToatalInterest, TotalPayment, interestRate, loanPeriod, MonthlyPayment, AnnualPayment, monthlyRate;
    TextView textViewInputLoanAmount, textViewInputInterest, textViewInputLoanPeriod, textViewResultMonthlyPayment, textViewResultTotalPayment, textViewResultTotalInterest, textViewResultAnnualPayment;
    RecyclerView recyclerViewAmortizationfullreport;
    AmortizationTable amortizationTable;
    int i = 1;
    ImageView imageviewpiechart;
    double InterestpaidforMonth, AmountofPrincipalPaid, RemainingPrincipalAmount;
    ArrayList<Integer> ArrayListamortizationID = new ArrayList<Integer>();
    ArrayList<Double> ArrayListamortizationAmount = new ArrayList<Double>();
    ArrayList<Double> ArrayListamortizationInterest = new ArrayList<Double>();
    ArrayList<Double> ArrayListamortizationPrincipal = new ArrayList<Double>();
    ArrayList<Double> ArrayListamortizationRemainingBalance = new ArrayList<Double>();

    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_full_report);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


       /* webView = (WebView) findViewById(R.id.webviewfullreport);
        webView.addJavascriptInterface(new WebAppInterface(), "Android");*/

        imageviewpiechart = (ImageView) findViewById(R.id.imageViewPiechart);
        recyclerViewAmortizationfullreport = (RecyclerView) findViewById(R.id.recyclerViewAmortizationfullreport);
        recyclerViewAmortizationfullreport.setHasFixedSize(true);
        recyclerViewAmortizationfullreport.setLayoutManager(new GridLayoutManager(this, 1));

      /*  webView = (WebView)findViewById(R.id.web);
        webView.addJavascriptInterface(new WebAppInterface(), "Android");

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/chart.html");
        webView.loadUrl("javascript:capture()");*/


        //get values from Intent Activity LoanReportChart
        PrincipalAmount = getIntent().getExtras().getDouble("PrincipalAmount");
        ToatalInterest = getIntent().getExtras().getDouble("toatalInterest");
        TotalPayment = getIntent().getExtras().getDouble("TotalPayment");
        interestRate = getIntent().getExtras().getDouble("interestRate");
        loanPeriod = getIntent().getExtras().getDouble("loanPeriod");
        MonthlyPayment = getIntent().getExtras().getDouble("Monthlypayment");
        AnnualPayment = getIntent().getExtras().getDouble("AnnualPayment");
        monthlyRate = getIntent().getExtras().getDouble("Rate");

        byte[] byteArray = getIntent().getByteArrayExtra("bmp_Image");
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        // Bitmap bmp = BitmapFactory.decodeByteArray( getIntent().getByteArrayExtra("bmp_Image"),0,getIntent().getByteArrayExtra("byteArray").length);
        imageviewpiechart.setImageBitmap(bmp);

        //get value through intent from Loancalculator activity

       /* //get value through intent from Loancalculator activity
        monthlypayment = getIntent().getExtras().getDouble("Monthlypayment");
        monthlyRate = getIntent().getExtras().getDouble("Rate");
        loanAmount = getIntent().getExtras().getDouble("PrincipalAmount");
        loanPeriod = getIntent().getExtras().getDouble("loanPeriod");*/


        Toast.makeText(LoanFullReport.this, " interest " + AnnualPayment, Toast.LENGTH_SHORT).show();

        // design tools declaration
        textViewInputLoanAmount = (TextView) findViewById(R.id.textViewLoanAmountfullreport);
        textViewInputInterest = (TextView) findViewById(R.id.textViewinterestratepercentfullreport);
        textViewInputLoanPeriod = (TextView) findViewById(R.id.textViewloantermpercentfullreport);
        textViewResultMonthlyPayment = (TextView) findViewById(R.id.textViewLoanMonthlypaymentfullreport);
        textViewResultTotalPayment = (TextView) findViewById(R.id.textViewtotalpaymentamountfullreport);
        textViewResultTotalInterest = (TextView) findViewById(R.id.textViewtotalinterestpercentresultfullreport);
        textViewResultAnnualPayment = (TextView) findViewById(R.id.textViewtotalAnnualpaymentresultfullreport);

        //setting value to textview
        textViewInputLoanAmount.setText(new DecimalFormat("##.##").format(PrincipalAmount));
        textViewInputInterest.setText(new DecimalFormat("##.##").format(interestRate) + "%");
        textViewInputLoanPeriod.setText(new DecimalFormat("##.##").format(loanPeriod));
        textViewResultMonthlyPayment.setText(new DecimalFormat("##.##").format(MonthlyPayment));
        textViewResultTotalPayment.setText(new DecimalFormat("##.##").format(TotalPayment));
        textViewResultTotalInterest.setText(new DecimalFormat("##.##").format(ToatalInterest));
        textViewResultAnnualPayment.setText(new DecimalFormat("##.##").format(AnnualPayment));

        for (i = 1; i <= loanPeriod; i++) {
            InterestpaidforMonth = PrincipalAmount * monthlyRate;
            AmountofPrincipalPaid = MonthlyPayment - InterestpaidforMonth;
            RemainingPrincipalAmount = PrincipalAmount - AmountofPrincipalPaid;
            PrincipalAmount = RemainingPrincipalAmount;
            // Toast.makeText(this, "New loan amount" + loanAmount, Toast.LENGTH_LONG).show();
            Log.d("InterestpaidforMonth11", String.valueOf(InterestpaidforMonth));
            Log.d("AmountofPrincipalPaid11", String.valueOf(AmountofPrincipalPaid));
            Log.d("RemainingPrincipalAmount11", String.valueOf(RemainingPrincipalAmount));
            //  Toast.makeText(LoanFullReport.this, " InterestpaidforMonth  " + InterestpaidforMonth + "  \n AmountofPrincipalPaid " + AmountofPrincipalPaid + "\n RemainingPrincipalAmount " + RemainingPrincipalAmount, Toast.LENGTH_SHORT).show();
            ArrayListamortizationID.add(i);
            ArrayListamortizationAmount.add(MonthlyPayment);
            ArrayListamortizationInterest.add(InterestpaidforMonth);
            ArrayListamortizationPrincipal.add(AmountofPrincipalPaid);
            ArrayListamortizationRemainingBalance.add(RemainingPrincipalAmount);
        }
        //last month
        AmountofPrincipalPaid = PrincipalAmount;
        InterestpaidforMonth = PrincipalAmount * monthlyRate;
        MonthlyPayment = (double) Math.round(AmountofPrincipalPaid + InterestpaidforMonth);
        RemainingPrincipalAmount = 0.0;
        // Toast.makeText(LoanFullReport.this, " AmountofPrincipalPaid  " + AmountofPrincipalPaid + "  \n InterestpaidforMonth " + InterestpaidforMonth + "\n monthlypayment " + MonthlyPayment + "\n RemainingPrincipalAmount " + RemainingPrincipalAmount, Toast.LENGTH_SHORT).show();

        amortizationTable = new AmortizationTable(LoanFullReport.this, ArrayListamortizationID, ArrayListamortizationAmount, ArrayListamortizationInterest, ArrayListamortizationPrincipal, ArrayListamortizationRemainingBalance);
        recyclerViewAmortizationfullreport.setAdapter(amortizationTable);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fullreport, menu);
       /* if (Build.VERSION.SDK_INT > 18) {
            menu.add(0, 1, 0, "Print").setShowAsAction(2);
        }
        menu.add(0, 0, 0, "E-Mail").setShowAsAction(2);
        return true;
*/
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.fullreportemail) {
            boolean z;
            Exception exception;
            PdfDocument pdfDocument = new PdfDocument();
            try {
                File file = new File(getExternalCacheDir().getPath() + "/report.pdf");
                if (file.exists()) {
                    file.delete();
                }
                File file2 = new File(getExternalCacheDir().getPath());
                if (!file2.exists()) {
                    file2.mkdir();
                }
                for (int i = 0; i <5; i++) {
                    View view = (View) this.f2157n.get(i);
                    PdfDocument.Page startPage = pdfDocument.startPage(new PdfDocument.PageInfo.Builder(595, 842, i).create());
                    Canvas canvas = startPage.getCanvas();
                    canvas.setDensity(160);
                    view.draw(canvas);
                    pdfDocument.finishPage(startPage);
                }
                pdfDocument.writeTo(new FileOutputStream(file));
                try {
                    Intent intent = new Intent("android.intent.action.SEND_MULTIPLE");
                    intent.setType("text/plain");
                    intent.putExtra("android.intent.extra.EMAIL", new String[]{""});
                    intent.putExtra("android.intent.extra.SUBJECT", getIntent().getStringExtra("title"));
                    intent.putExtra("android.intent.extra.TEXT", getIntent().getStringExtra("myBodyText"));
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(Uri.fromFile(new File(getExternalCacheDir().getPath() + "/report.pdf")));
                    intent.putParcelableArrayListExtra("android.intent.extra.STREAM", arrayList);
                    startActivity(Intent.createChooser(intent, "Send mail..."));
                    return true;
                } catch (Exception e) {
                    Exception exception2 = e;
                    z = true;
                    exception = exception2;
                    exception.printStackTrace();
                    return z;
                }
            } catch (Exception e2) {
                exception = e2;
                z = false;
                exception.printStackTrace();
                return z;
            }
        }
        if (id == R.id.fullreportprint) {

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
