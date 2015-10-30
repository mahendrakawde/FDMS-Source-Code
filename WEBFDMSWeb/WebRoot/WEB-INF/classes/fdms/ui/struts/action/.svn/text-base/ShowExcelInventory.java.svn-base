package fdms.ui.struts.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.util.LabelValueBean;

import com.aldorsolutions.webfdms.beans.cache.MetaDataBaseCache;
import com.aldorsolutions.webfdms.company.bean.CompanyManagerBean;
import com.aldorsolutions.webfdms.company.model.CompanyDTO;
import com.aldorsolutions.webfdms.util.DAO;

/**
 * Shows excel inventory data processing details
 * @author Srini Kotha
 *
 */
public class ShowExcelInventory extends Action{

	private Logger logger = Logger.getLogger(ShowExcelInventory.class.getName());

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.debug("Entered ShowExcelInventory Action");
		
		DynaActionForm form = (DynaActionForm) actionForm;
		
		CompanyManagerBean bean = new CompanyManagerBean();
		List companylist = MetaDataBaseCache.getInstance().getCache(null, DAO.DB_FDMSUSERS);
		List<LabelValueBean> dataBaselist = new ArrayList<LabelValueBean>();
		for (int i = 0; companylist != null && i < companylist.size(); i++) {
			String dataBaseName = bean.getDataBaseName(((CompanyDTO) companylist.get(i)).getDataURL());
			if("Y".equalsIgnoreCase(((CompanyDTO) companylist.get(i)).getDatabaseStatus())){
				dataBaselist.add(new LabelValueBean(dataBaseName, dataBaseName));
			}
		}
		form.set("dataBaseList", dataBaselist);
		
		
		return mapping.findForward("excelinventorydetails");
	}
}
