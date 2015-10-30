package fdms.ui.tags;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

public class SpeedSelectTargetFieldTag extends TagSupport
  {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2628385554751978617L;
	private Integer mColumn = null;
    private String mProperty = null;
    
    public String getColumn()
    {
      String columnStr = null;
      if (mColumn != null)
        columnStr = mColumn.toString();
      return columnStr;
    }
    
    public void setColumn(String column)
    {
    	try {
			mColumn = new Integer(column);
			if (mColumn.intValue() < 1)
				mColumn = null;
    	} catch (NumberFormatException ex) {
          mColumn = null;
        }
    }
  	
    public String getProperty()
      { return mProperty; }

    public void setProperty(String property)
      { mProperty = property; }

    public int doStartTag() throws JspException {
    	SpeedSelectTag sst = (SpeedSelectTag)this.getParent();
        Tag parent = getParent();
        if ((parent != null) && (parent instanceof SpeedSelectTag))
          {
            SpeedSelectTag parentSpeedSelect = (SpeedSelectTag)parent;
            if (parentSpeedSelect.getValue("targetFields") == null)
              parentSpeedSelect.setValue("targetFields", new ArrayList());
            List targetFields = (List)parentSpeedSelect.getValue("targetFields");
            if (targetFields != null)
              {
                Hashtable targetFieldHash = new Hashtable(2);
                if (getColumn() != null)
                  targetFieldHash.put("column", getColumn());
                if (getProperty() != null) {
                	String indexId = sst.getIndexId();
                	if ((indexId != null) && (indexId.length() > 0))
                		targetFieldHash.put("property", getProperty() + "[" + indexId + "]");
                	else
                		targetFieldHash.put("property", getProperty());
                }
                targetFields.add(targetFieldHash);
              }
          }
        return SKIP_BODY;
      }

    public void release()
      {
        mColumn = null;
        mProperty = null;
        
        super.release();
      }
  }
