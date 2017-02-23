package com.geniusnine.android.financialcalculators;

/**
 * Created by Dev on 23-02-2017.
 */

public class CalculatorCode {

    private double loanAmount;
    private double intrestRate;
    private int month;

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public double getIntrestRate() {
        return intrestRate;
    }

    public void setIntrestRate(double intrestRate) {
        this.intrestRate = intrestRate;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public CalculatorCode(double loanAmount, double intrestRate, int month) {
        this.loanAmount = loanAmount;
        this.intrestRate = intrestRate;
        this.month = month;


    }

    public double calculateEMI(){
        double r1 = Math.pow(intrestRate+1,month);
        double r = intrestRate/1200;
        double monthlyPayment = (double) ((r+(r/(r1-1))) * loanAmount);
        return monthlyPayment;
    }
}
