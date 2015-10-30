package com.aldorsolutions.webfdms.util;

/**
 * Calculate future value of a lump sum amount (Principal)
 * given amount of each loan payment, interest rate, number of periods per year, time in years.
 * Creation date: (1/13/2003 5:40:35 PM)
 * @author: 
 */
public class FutureValue {
	private double futureValue;
	public double principal;
	private double interestRate;
	private double numberPeriodsPerYear;
	private double timeInYears;
/**
 * Amortization constructor comment.
 */
public FutureValue(double principal, double r, double n, double t) {
	super();
	this.principal = principal;
	interestRate = r/100.0;
	numberPeriodsPerYear = n;
	timeInYears = t;
	recalc();
}
/**
 * Insert the method's description here.
 * Creation date: (1/13/2003 7:25:05 PM)
 * @return double
 */
public int getFutureValue() {
	return round(futureValue);
}
/**
 * Insert the method's description here.
 * Creation date: (1/13/2003 7:26:23 PM)
 * @return double
 */
public double getInterestRate() {
	return interestRate;
}
/**
 * Insert the method's description here.
 * Creation date: (1/13/2003 7:26:59 PM)
 * @return double
 */
public double getNumberPeriodsPerYear() {
	return numberPeriodsPerYear;
}
/**
 * Insert the method's description here.
 * Creation date: (1/13/2003 7:27:15 PM)
 * @return double
 */
public double getTimeInYears() {
	return timeInYears;
}
/**
 * Calculate payment amount.
 * Creation date: (1/13/2003 7:31:05 PM)
 */
private void recalc() {
	double P = principal;
	double r = interestRate;
	double n = numberPeriodsPerYear;
	double t = timeInYears;
//	paymentAmount = principal * ( (interestRate / numberPeriodsPerYear) / (1 - Math.pow(1+(interestRate / numberPeriodsPerYear),-numberPeriodsPerYear * timeInYears)));
	//paymentAmount = P * ((r/n)/(1-Math.pow(1+(r/n),(-n*t))));
	//presentValue = R * ((1-Math.pow((1+(r/n)),(-n*t)))/(r/n));
	futureValue = P * Math.pow((1+(r/n)), (n*t));
}
/**
 * Insert the method's description here.
 * Creation date: (1/13/2003 9:31:21 PM)
 * @return int
 * @param x double
 */
public int round(double x) {
	return (int)((x*100)+.5);
}
/**
 * Insert the method's description here.
 * Creation date: (1/13/2003 7:26:23 PM)
 * @param newInterestRate double
 */
public void setInterestRate(double newInterestRate) {
	interestRate = newInterestRate;
	recalc();
}
/**
 * Insert the method's description here.
 * Creation date: (1/13/2003 7:26:59 PM)
 * @param newNumberPeriodsPerYear double
 */
public void setNumberPeriodsPerYear(double newNumberPeriodsPerYear) {
	numberPeriodsPerYear = newNumberPeriodsPerYear;
	recalc();
}
/**
 * Insert the method's description here.
 * Creation date: (1/13/2003 7:27:15 PM)
 * @param newTimeInYears double
 */
public void setTimeInYears(double newTimeInYears) {
	timeInYears = newTimeInYears;
	recalc();
}
}
