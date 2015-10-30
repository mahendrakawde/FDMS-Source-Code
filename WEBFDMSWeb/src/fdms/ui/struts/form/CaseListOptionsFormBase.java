package fdms.ui.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class CaseListOptionsFormBase extends ActionForm {
  
  /**
	 * 
	 */
	private static final long serialVersionUID = -558057258172532398L;
private int x ;  
  private String submitbutton ;  
  private String orderby ;  
  private String perScreen ;  
  private int y ;
  private boolean displayPreneed;
  private boolean displayVoided;
  
  	public String getOrderby() {
		return orderby;
	}

	public String getPerScreen() {
		return perScreen;
	}

	public String getSubmitbutton() {
		return submitbutton;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setOrderby(String in) {
		orderby = in;
	}

	public void setPerScreen(String in) {
		perScreen = in;
	}

	public void setSubmitbutton(String in) {
		submitbutton = in;
	}

	public void setX(int in) {
		x = in;
	}

	public void setY(int in) {
		y = in;
	}

	public boolean getDisplayPreneed() {
		return displayPreneed;
	}

	public void setDisplayPreneed(boolean displayPreneed) {
		this.displayPreneed = displayPreneed;
	}
	
	public boolean getDisplayVoided() {
		return displayVoided;
	}
	
	public void setDisplayVoided(boolean displayVoided) {
		this.displayVoided = displayVoided;
	}
	
	  public void reset(ActionMapping actionMapping, HttpServletRequest request) {
		  displayPreneed = false;		  
		  displayVoided = false;
	  }

  
}
