package com.aldorsolutions.fdms.to.vitals;

import java.util.Date;

import com.aldorsolutions.fdms.to.common.Address;

public class CertifyingPhysician {
	private String name;
	private Address address = Address.getDefault();
	private String phone;
	private String title = "";
	private DatesAttended datesAttended;
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}

	private boolean viewedTheBody;
	
	public CertifyingPhysician() {}
	
	
	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isViewedTheBody() {
		return viewedTheBody;
	}

	public void setViewedTheBody(boolean viewedTheBody) {
		this.viewedTheBody = viewedTheBody;
	}
	
	public boolean hasDatesAttended() {
		return datesAttended != null;
	}

	public void setDatesAttended(Date fromDate, Date toDate, Date lastDate) {
		this.datesAttended = new DatesAttended(fromDate, toDate, lastDate);
	}

	public void clearDatesAttended() {
		this.datesAttended = null;
	}

	public Date getAttendedFromDate() {
		return datesAttended != null ? datesAttended.fromDate : null;
	}

	public Date getAttendedToDate() {
		return datesAttended != null ? datesAttended.toDate : null;
	}

	public Date getAttendedLastDate() {
		return datesAttended != null ? datesAttended.lastDate : null;
	}

	private class DatesAttended{
		Date fromDate;
		Date toDate;
		Date lastDate;
		
		DatesAttended(Date fromDate, Date toDate, Date lastDate) {
			super();
			this.fromDate = fromDate;
			this.toDate = toDate;
			this.lastDate = lastDate;
		}
	}
}
