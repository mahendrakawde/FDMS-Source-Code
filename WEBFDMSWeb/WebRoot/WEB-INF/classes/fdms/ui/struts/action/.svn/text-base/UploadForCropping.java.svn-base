package fdms.ui.struts.action;

import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.FileUploadForm;
import fdms.util.FileUtil;

public class UploadForCropping extends Action {
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		FormFile formFile = null;
        FileUploadForm fileUploadForm = (FileUploadForm) form;

        FileOutputStream fileOutputStream = null;
        
        request.setAttribute("callingId", request.getParameter("callingId"));
        
        fileUploadForm.setResizePercent(Integer.parseInt("100"));
        
        try {
        	formFile = fileUploadForm.getFile();

            //String relativeWebPath = "/uploads";
            String relativeWebPath = "fdms/";

            //String absoluteFilePath = getServlet().getServletContext().getRealPath(relativeWebPath);

            String absoluteFilePath = getServlet().getServletConfig().getServletContext().getRealPath(relativeWebPath);
            
            /*****/
            
            DbUserSession sessionUser = SessionHelpers.getUserSession(request);
			
			String relativePath = FileUtil.pathForFileUpload(sessionUser);
			
			/****/

            File dirName = new File(absoluteFilePath + "/" + relativePath);
            
            if(!dirName.exists()) {
            	dirName.mkdirs();
            }

            /*String absoluteFilePath = getServletContext().getRealPath(relativeWebPath);*/
            File uploadedFile = new File(dirName, formFile.getFileName());

            fileOutputStream = new FileOutputStream(uploadedFile);
            fileOutputStream.write(formFile.getFileData());

            // fileUploadForm.setFileName(formFile.getFileName());

            request.setAttribute("uploadedPath", relativeWebPath + relativePath + formFile.getFileName());
        } catch (Exception ex) {
        	ex.printStackTrace();
        } finally {
        	if(fileOutputStream != null) {
        		fileOutputStream.close();
        	}
        }
        
        if (formFile.getContentType().startsWith("image")) {
        	return mapping.findForward("showImageToCrop");
        }
        
		return null;
	}

}
