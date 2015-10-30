package fdms.ui.struts.form;

import org.apache.struts.action.ActionForm;


public class CemListFormBase extends ActionForm
  {
    private String mIndexInCaseList;
    private String mJumpTo;
    private String mRequestType;
    private String mSortOrderId;
    private int mVitalsId;
    private int mX;
    private int mY;
    private String userLocationId;
    private String userLocaleId;

    public String getIndexInCaseList()
      { return mIndexInCaseList;  }

    public void setIndexInCaseList(String indexInCaseList)
      { mIndexInCaseList = indexInCaseList; }

    public String getJumpTo()
      { return mJumpTo; }

    public void setJumpTo(String jumpTo)
      { mJumpTo = jumpTo; }

    public String getRequestType()
      { return mRequestType; }

    public void setRequestType(String requestType)
      { mRequestType = requestType; }

    public String getSortOrderId()
      { return mSortOrderId; }

    public void setSortOrderId(String sortOrderId)
      { mSortOrderId = sortOrderId; }

    public int getVitalsId()
      { return mVitalsId; }

    public void setVitalsId(int vitalsId)
      { mVitalsId = vitalsId; }

    public int getX()
      { return mX; }

    public void setX(int x)
      { mX = x; }

    public int getY()
      { return mY; }

    public void setY(int y)
      { mY = y; }
    
    public void setUserLocationId(String userLocationId) {
        this.userLocationId = userLocationId;
    }
    
    public String getUserLocationId() {
        return userLocationId;
    }

	/**
	 * @return Returns the userLocaleId.
	 */
	public String getUserLocaleId() {
		return userLocaleId;
	}

	/**
	 * @param userLocaleId The userLocaleId to set.
	 */
	public void setUserLocaleId(String userLocaleId) {
		this.userLocaleId = userLocaleId;
	}
    
  }
