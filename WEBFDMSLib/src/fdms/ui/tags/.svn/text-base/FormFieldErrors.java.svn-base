/*
 * FormFieldErrors.java
 *
 * Created on January 3, 2005, 4:18 PM
 */

package fdms.ui.tags;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class FormFieldErrors extends BodyTagSupport {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1308490984685319391L;
	protected boolean hasErrors = false;
    private String form;
    
    public void setForm(String form) {
        this.form = form;
    }
    
    public int doStartTag() throws JspException {
        ArrayList errors = (ArrayList) pageContext.getRequest().getAttribute("formErrors");
        
        StringBuffer html = new StringBuffer();
        html.append("<script type=\"text/javascript\">\n");
        html.append("function formErrors() {\n");
        html.append("\twith (document." + form + ") {\n");
            
        if (errors != null && errors.size() > 0) {
            hasErrors = true;
            Iterator it = errors.iterator();           
            
            while (it.hasNext()) {
                String input = (String) it.next();
                html.append("\t\tif (" + input + ".type) {\n");
                html.append("\t\t\t" + input + ".style.backgroundColor='#FFFF00';\n");
                html.append("\t\t\t" + input + ".style.borderWidth='1px';\n");
                html.append("\t\t\t" + input + ".style.borderColor='#666666';\n");
                html.append("\t\t}\n");
                html.append("\t\tif (document.getElementById('_" + input + "'))\n");
                html.append("\t\t\tdocument.getElementById('_" + input + "').style.color='#FF0000';\n");
            }                      
        }
        
        html.append("\t}\n");
        html.append("}\n");
        html.append("</script>\n");
        
        try {
            pageContext.getOut().print(html.toString());         
        } catch (IOException e) {
            System.err.println("An Error has occurred in FormFieldErrors");
        }
        
        return SKIP_BODY;
    }
    
}
