package com.geniusnine.android.financialcalculators;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Dev on 18-01-2017.
 */

public class AmortizationAdapter extends RecyclerView.Adapter<AmortizationAdapter.ListViewHolder> {

    Context context;
    LayoutInflater inflater;
    ArrayList<Integer> amortizationID;
    ArrayList<Double> amortizationAmount;
    ArrayList<Double> amortizationInterest;
    ArrayList<Double> amortizationPrincipal;
    ArrayList<Double> amortizationRemainingBalance;

    private ArrayList<Bitmap> bitmaps;
    byte[] categoryimage;



    public AmortizationAdapter(Context context2, ArrayList<Integer> id, ArrayList<Double> amount,  ArrayList<Double> interest, ArrayList<Double> principal, ArrayList<Double> remainingbalance) {
        super();
        this.context = context2;
        this.amortizationID = id;
        this.amortizationAmount =  amount;
        this.amortizationInterest = interest;
        this.amortizationPrincipal =  principal;
        this.amortizationRemainingBalance =  remainingbalance;
        //this.bitmaps=bitm;
        // this.listener = context;
        inflater = LayoutInflater.from(context);


    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View convertView = inflater.inflate(R.layout.list_amortization_item, parent, false);
        ListViewHolder viewHolder = new ListViewHolder(convertView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {

        //textViewMonthlyPayment.setText(new DecimalFormat("##.##").format(monthlyPayment));
        //holder.tv_name.setText(categoryName.get(position));
        holder.textViewID.setText(new DecimalFormat("##.##").format(amortizationID.get(position)));
      holder.textViewAmount.setText(new DecimalFormat("##.##").format(amortizationAmount.get(position)));
         holder.textViewInterest.setText(new DecimalFormat("##.##").format(amortizationInterest.get(position)));
        holder.textViewPrincipal.setText(new DecimalFormat("##.##").format(amortizationPrincipal.get(position)));
      holder.textViewRemainingbalance.setText(new DecimalFormat("##.##").format(amortizationRemainingBalance.get(position)));


      // final String stringCategory=holder.tv_name.getText().toString().trim();
      /*  holder.iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent i1=new Intent(context, DoctorListActivity.class);
                i1.putExtra("CategoryName",stringCategory);
                context.startActivity(i1);
            }
        });*/
       // animate(holder);
    }

    @Override
    public int getItemCount() {
        return amortizationID.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        TextView textViewAmount,textViewInterest,textViewPrincipal,textViewRemainingbalance,textViewID;
        ImageView iv_delete;

        public ListViewHolder(View itemView) {
            super(itemView);
            textViewID = (TextView) itemView.findViewById(R.id.AmortizationId);
            textViewAmount = (TextView) itemView.findViewById(R.id.AmortizationAmount);
            textViewInterest = (TextView) itemView.findViewById(R.id.AmortizationInterest);
            textViewPrincipal = (TextView) itemView.findViewById(R.id.AmortizationPrincipal);
            textViewRemainingbalance = (TextView) itemView.findViewById(R.id.AmortizationRemainingBalance);
         //   textViewPrincipal = (ImageView) itemView.findViewById(R.id.imageViewcategory);

        }
    }
  /*  public void animate(RecyclerView.ViewHolder viewHolder) {
        final Animation animAnticipateOvershoot = AnimationUtils.loadAnimation(context, R.anim.bounce_interpolator);
        viewHolder.itemView.setAnimation(animAnticipateOvershoot);
    }*/
}
