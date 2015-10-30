/*
 * ShowMain.java
 *
 * Created on February 1, 2005, 11:57 AM
 */

package fdms.admin.user.action;

/**
 * 
 * @author Guadalupe Garcia
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.aldorsolutions.webfdms.admin.user.bean.UserManagerBean;
import com.aldorsolutions.webfdms.admin.user.dao.UserDAO;
import com.aldorsolutions.webfdms.admin.user.model.UserDTO;
import com.aldorsolutions.webfdms.common.Constants;
import com.aldorsolutions.webfdms.common.DTOComparator;

public class ShowUsers extends Action {

	private Logger logger = Logger.getLogger(ShowMain.class.getName());

	String type = null;

	String method = null;

	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String totalUsers = "0";
		ArrayList<UserDTO> users = null;

		try {

			users = new UserManagerBean().getUsers(UserDAO.ACTIVE);

		} catch (Exception e) {
			logger.error("Exception in perform() : ", e);
		}

		UserDTO[] usersArray = users.toArray(new UserDTO[users.size()]);
		method = request.getParameter("method");
		if (method == null) {
			method = "name";
		}

		type = request.getParameter("type");
		if (type == null) {
			type = Constants.ASCE_SORT_ORDER;
		}
		DTOComparator comparator = new DTOComparator(method, type);
		Arrays.sort(usersArray, comparator);

		ArrayList<UserDTO> userList = new ArrayList<UserDTO>();
		for (int i = 0; i < usersArray.length; i++) {
			userList.add(usersArray[i]);
		}
		if (users != null && users.size() > 0) {
			request.setAttribute("users", userList);
			totalUsers = Integer.toString(userList.size());
		}
		request.setAttribute("totalUsers", totalUsers);

		if (Constants.ASCE_SORT_ORDER.equals(type)) {
			type = Constants.DESC_SORT_ORDER;
		} else {
			type = Constants.ASCE_SORT_ORDER;
		}
		request.setAttribute("type", type);
		request.setAttribute("method", method);
		return mapping.findForward("users");
	}
}
