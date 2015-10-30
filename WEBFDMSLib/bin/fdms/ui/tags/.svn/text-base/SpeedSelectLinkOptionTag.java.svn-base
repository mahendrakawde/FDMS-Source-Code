package fdms.ui.tags;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

public class SpeedSelectLinkOptionTag extends TagSupport
  {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2053313057174501561L;
	private String mFeatures = null;
    private String mScript = null;
    private String mTarget = null;
    private String mText = null;
    private String mUrl = null;

    public String getFeatures()
      { return mFeatures; }

    public void setFeatures(String features)
      { mFeatures = features; }

    public String getScript()
      { return mScript; }

    public void setScript(String script)
      { mScript = script; }

    public String getTarget()
      { return mTarget; }

    public void setTarget(String target)
      { mTarget = target; }

    public String getText()
      { return mText; }

    public void setText(String text)
      { mText = text; }

    public String getUrl()
      { return mUrl; }

    public void setUrl(String url)
      { mUrl = url; }

    public int doStartTag() throws JspException
      {
        Tag parent = getParent();
        if ((parent != null) && (parent instanceof SpeedSelectTag))
          {
            SpeedSelectTag parentSpeedSelect = (SpeedSelectTag)parent;
            if (parentSpeedSelect.getValue("linkOptions") == null)
              parentSpeedSelect.setValue("linkOptions", new ArrayList());
            List linkOptions = (List)parentSpeedSelect.getValue("linkOptions");
            if (linkOptions != null)
              {
                Hashtable linkOptionHash = new Hashtable(5);
                if (getFeatures() != null)
                  linkOptionHash.put("features", getFeatures());
                if (getScript() != null)
                  linkOptionHash.put("script", getScript());
                if (getTarget() != null)
                  linkOptionHash.put("target", getTarget());
                if (getText() != null)
                  linkOptionHash.put("text", getText());
                if (getUrl() != null)
                  linkOptionHash.put("url", getUrl());
                linkOptions.add(linkOptionHash);
              }
          }
        return SKIP_BODY;
      }

    public void release()
      {
        mFeatures = null;
        mScript = null;
        mTarget = null;
        mText = null;
        mUrl = null;

        super.release();
      }
  }
