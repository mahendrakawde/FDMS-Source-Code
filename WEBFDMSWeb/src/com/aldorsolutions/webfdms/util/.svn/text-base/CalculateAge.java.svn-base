package com.aldorsolutions.webfdms.util;

import java.util.GregorianCalendar;

public class CalculateAge {

	public static final int MONTHS = 1;
	public static final int YEARS = 2;
	public static final int DAYS = 3;
	
	private GregorianCalendar dateOfBirth;
	private GregorianCalendar dateOfDeath;

	public CalculateAge(GregorianCalendar dateOfBirth, GregorianCalendar dateOfDeath) {
		this.dateOfDeath = dateOfDeath; 
		this.dateOfBirth = dateOfBirth;
	}

	public int getAgeDifference(int type) {
		int monthDied;
		int monthBorn;
		int yearDied;
		int yearBorn;
		int dayDied;
		int dayBorn;
		int diffMonths = 0;
		int diffYears = 0;
		int diffDays = 0;

		//Months stored in GregorianCalendars are 1 less than actual value.
		//So the value of the month is incremented by 1
		monthBorn = dateOfBirth.get(GregorianCalendar.MONTH) + 1;
		monthDied = dateOfDeath.get(GregorianCalendar.MONTH) + 1;

		yearBorn = dateOfBirth.get(GregorianCalendar.YEAR);
		yearDied = dateOfDeath.get(GregorianCalendar.YEAR);

		dayBorn = dateOfBirth.get(GregorianCalendar.DAY_OF_MONTH);
		dayDied = dateOfDeath.get(GregorianCalendar.DAY_OF_MONTH);

		//Calculate difference in years
		diffYears = yearDied - yearBorn;

		//Adjust years by looking at months

		//Special Scen 1 - same month, no birthday yet (add 12 months)
		if (monthBorn == monthDied) {
			if (dayBorn > dayDied) {
				monthDied += 12;
				diffYears--;
			}
		}
		//Special Scen 2 - today's month earlier than month of date of birth
		else if (monthBorn > monthDied) {
			monthDied += 12;
			diffYears--;
			diffMonths = monthDied - monthBorn;
		}

		//After accounting for special scenarios get difference in months
		diffMonths = monthDied - monthBorn;

		//Adjust months by looking at days
		if (dayBorn > dayDied) {
			diffMonths--;

			dayDied += dateOfDeath.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);

			if (monthBorn == monthDied) {
				diffMonths = 0;
				diffYears--;
			}
		}

		//Find the difference in number of days
		diffDays = dayDied - dayBorn;

		if (type == CalculateAge.YEARS) {
			return diffYears;
		} else if (type == CalculateAge.MONTHS) {
			return diffMonths;
		} else if (type == CalculateAge.DAYS) {
			return diffDays;
		} else {
			return 0;
		}
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CalculateAge y = new CalculateAge(new GregorianCalendar(1970, 7, 7), new GregorianCalendar(2008, 7, 7));
		System.out.println("Years:" + y.getAgeDifference(CalculateAge.YEARS) + 
										   " Months:" + y.getAgeDifference(CalculateAge.MONTHS) +
										   " Days:" + y.getAgeDifference(CalculateAge.DAYS));
	}
}
