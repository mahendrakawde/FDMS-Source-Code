package com.aldorsolutions.webfdms.locations.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.aldorassist.webfdms.dao.LocationOptionValueDAO;
import com.aldorassist.webfdms.dao.LocationDAO;
import com.aldorassist.webfdms.dao.LocationOptionsDAO;
import com.aldorassist.webfdms.model.CompanyOptionsDTO;
import com.aldorassist.webfdms.model.LocationDTO;
import com.aldorassist.webfdms.model.LocationOptionValueDTO;
import com.aldorassist.webfdms.model.LocationOptionsDTO;
import com.aldorsolutions.webfdms.admin.user.bean.UserManagerBean;
import com.aldorsolutions.webfdms.admin.user.model.UserLocaleDTO;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.company.bean.CompanyManagerBean;
import com.aldorsolutions.webfdms.company.model.CompanyDTO;
import com.aldorsolutions.webfdms.locations.form.LocationForm;
import com.aldorsolutions.webfdms.util.OptionsList;
import com.aldorsolutions.webfdms.util.SessionHelpers;
import org.apache.struts.util.LabelValueBean;

/**
 * 
 * @author drollins, CJongs
 */
public class ShowLocationForm extends Action {

	private Logger logger = Logger.getLogger(ShowLocationForm.class.getName());

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DbUserSession sessionUser = SessionHelpers.getUserSession(request);

		int locationIDL = 0;
		int companyIDL = 0;

		String locationID = request.getParameter("locationID");
		String companyID = request.getParameter("companyID");

		if (locationID != null) {
			try {
				locationIDL = Integer.parseInt(locationID);
			} catch (NumberFormatException e) {
				// unable to parse long from String
			}
		}

		if (companyID != null) {
			try {
				companyIDL = Integer.parseInt(companyID);
			} catch (NumberFormatException e) {
				// unable to parse long from String
			}
		}

		LocationForm form = new LocationForm();

		if (locationIDL > 0L) {
			try {
				LocationDAO locationDAO = new LocationDAO(companyIDL, 0);
				LocationDTO location = locationDAO.getLocation(locationIDL);
				if (location != null) {
					form.setLocationID((int) location.getLocationID());
					form.setName(location.getName());
					form.setAddr1(location.getAddr1());
					form.setAddr2(location.getAddr2());
					form.setAddr3(location.getAddr3());
					form.setApAcct(location.getApAcct());
					form.setArAcct(location.getArAcct());
					form.setCashAcct(location.getCashAcct());
					form.setCashBalance(getDouble(location.getCashBalance()));
					form.setCity(location.getCity());
					form.setCompanyNumber(getInt(location.getCompanyID()));
					form.setCounty(location.getCounty());
					form.setDiscountAcct(location.getDiscountAcct());
					form.setDiscountHandlingCode(location
							.getDiscountHandlingCode());
					form.setFinanceChargeAcct(location.getFinanceChargeAcct());
					form.setInactiveCode(location.getInactiveCode());
					form.setLicenseNumber(location.getLicenseNumber());
					form.setLocaleNumber(getInt(location.getLocaleID()));
					form.setManagerName(location.getManagerName());
					form.setNextCheckNumber(getInt(location
							.getNextCheckNumber()));
					form.setPackageVersion(location.getPackageVersion());
					form.setPhone(location.getPhone());
					form.setPhoneAlternate(location.getPhoneAlternate());
					form.setPreferenceGenericVitals(location
							.getPreferenceGenericVitals());
					form.setPriceListVersion(location.getPriceListVersion());
					form.setState(location.getState());
					form.setStdServiceCharge(getInt(location
							.getStdServiceCharge()));
					form.setUndepositedFundsAcct(location
							.getUndepositedFundsAcct());
					form.setUseUndepositedFundsAcct(location
							.isUseUndepositedFundsAcct());
					form.setZip(location.getZip());
					form.setOnetimeVendorCode(location.getOnetimeVendorCode());
					form.setFuneralSyncLocationId(location
							.getFuneralSyncLocationId());

				}
			} catch (Exception e) {
				logger.error("Error in perform() : ", e);
			}
		} else {
			form.setCompanyNumber(getInt(companyIDL));
		}

		request.setAttribute(mapping.getName(), form);

		ArrayList stateList = new ArrayList();

		// chai - comment this out it should read the state list form dbsession
		// not webfdmsdata1
		stateList = FdmsDb.getInstance().getStateList();

		// stateList =
		// FdmsDb.getInstance().getStateList(sessionUser.getDbLookup());

		HttpSession session = request.getSession();

		session.setAttribute("stateList", stateList);

		UserManagerBean userManagerBean = new UserManagerBean();

		CompanyDTO companyDTO = new CompanyManagerBean().getCompany(companyIDL);

		ArrayList locales = userManagerBean.getLocales(
				companyDTO.getDbLookup(), companyDTO.getSqlUser(), companyDTO
						.getSqlPass(), companyDTO.getCompanyID());

		ArrayList localeList = new ArrayList();
		// This trick is needed to make a Persistent[] into a DbSpeedData
		for (int i = 0; i < locales.size(); i++) {
			UserLocaleDTO userLocaleDTO = (UserLocaleDTO) locales.get(i);
			String listValue = userLocaleDTO.getLocaleId();
			String listLabel = userLocaleDTO.getName();
			localeList.add(new OptionsList(listValue, listLabel));
		}
		// added by haranesh patel
		LocationOptionsDAO locationOptionsDAO = new LocationOptionsDAO();
		ArrayList<LocationOptionsDTO> locationOptionsList = locationOptionsDAO
				.getLocationOptions();

		LocationOptionValueDAO loationOptionValueDAO = new LocationOptionValueDAO();
		ArrayList<LocationOptionValueDTO> locationOptionValueList = loationOptionValueDAO
				.getLocationOptionsValues(form.getCompanyNumber(), String
						.valueOf(form.getLocationID()));

		LabelValueBean[] locationOptions = new LabelValueBean[locationOptionsList
				.size()];

		for (int i = 0; i < locationOptionsList.size(); i++) {
			locationOptions[i] = new LabelValueBean(locationOptionsList.get(i)
					.getName(), String.valueOf(locationOptionsList.get(i)
					.getLocationOptionID()));
		}
		session.setAttribute("locationOptions", locationOptions);

		ArrayList<String> selectedLocation = new ArrayList<String>();


		for (int i = 0; i < locationOptionValueList.size(); i++) {
			if (locationOptionValueList.get(i).getValue() == 1) {
				selectedLocation.add(""
						+ locationOptionValueList.get(i)
								.getLocationOptionValue());

			}
		}

		String selectedoptions[] = new String[selectedLocation.size()];
		for (int i = 0; i < selectedLocation.size(); i++) {
			selectedoptions[i] = selectedLocation.get(i);
		}

		form.setSelectedLocationOptions(selectedoptions);

		session.setAttribute("ADMIN_LOCALES", localeList);

		return mapping.findForward("locationForm");
	}

	private String getInt(long value) {
		return (Long.toString(value));
	}

	private String getDouble(double value) {
		return (Double.toString(value));
	}

}
