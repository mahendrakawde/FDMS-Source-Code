package fdms.ui.struts.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.list.TreeList;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.util.LabelValueBean;

import com.aldorsolutions.webfdms.beans.DbColumn;
import com.aldorsolutions.webfdms.beans.cache.MetaDataBaseCache;
import com.aldorsolutions.webfdms.beans.cache.MetaTablesCache;
import com.aldorsolutions.webfdms.company.bean.CompanyManagerBean;
import com.aldorsolutions.webfdms.company.model.CompanyDTO;
import com.aldorsolutions.webfdms.util.DAO;


/**
 * Shows DataBase Table Data Load page
 * 
 * @author Srini Kotha
 * 
 */
public class PreDataLoadAction extends Action {

	private Logger logger = Logger.getLogger(PreDataLoadAction.class.getName());

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
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
		ProcessDataLoadAction.loadTablesAndColumns(form);
		StringBuffer tableStr = new StringBuffer();
		populateJavaScriptArrays(tableStr, dataBaselist);
		form.set("metaArray", tableStr.toString());
		
		return mapping.findForward("showDataLoad");

	}
	/**
	 * Generates javascript arrays
	 * @param tableStr String containing client side arrays
	 * @param dataBaselist Database name.
	 */
	public void populateJavaScriptArrays(StringBuffer tableStr, List dataBaselist) {

		tableStr.append("var databaseNames = new Array();\n");
		tableStr.append("databaseNames[0] = new Array('');\n");
		tableStr.append("var tables = new Array();\n");
		tableStr.append("tables[0] = new Array('');\n");
		tableStr.append("var cols = new Array();\n");
		tableStr.append("cols[0] = new Array();\n");
		tableStr.append("cols[0][0] = new Array();\n");
		CompanyManagerBean bean = new CompanyManagerBean();
		

		for (int i = 0; dataBaselist != null && i < dataBaselist.size(); i++) {
			LabelValueBean dataBaseBean = (LabelValueBean) dataBaselist.get(i);
			CompanyDTO company = bean.getCompany(dataBaseBean.getLabel());
			
			tableStr.append("databaseNames[" + (i + 1) + "] = '" + dataBaseBean.getLabel() + "';\n");

			tableStr.append("tables[" + (i + 1) + "] = new Array();\n");
			//tableStr.append("tables[" + (i + 1) + "][0] = '';\n");
			tableStr.append("cols[" + (i + 1) + "] = new Array();\n");
			tableStr.append("cols[" + (i + 1) + "][0] = new Array('' );\n");

			List tables = MetaTablesCache.getInstance().getAllTables(dataBaseBean.getLabel(),
					company.getDbLookup());
			
			TreeList tableList = new TreeList (tables);

			for (int j = 0; tableList != null && j < tableList.size(); j++) {
				String table = (String) tableList.get(j);
				
				tableStr.append("tables[" + (i + 1) + "][" + (j) + "] = '" + table + "';\n");

				tableStr.append("cols[" + (i + 1) + "][" + (j) + "] = new Array();\n");
				//tableStr.append("cols[" + (i + 1) + "][" + (j) + "][0] = '';\n");

				List cols = MetaTablesCache.getInstance().getAllColumns(dataBaseBean.getLabel(),
						table, company.getDbLookup());
				
				for (int k = 0; cols != null && k < cols.size(); k++) {
					String col = ((DbColumn) cols.get(k)).getName();

					tableStr.append("cols[" + (i + 1) + "][" + (j) + "][" + (k) + "] = '"
							+ col + "';\n");

				}

			}

		}
		
	}
}
