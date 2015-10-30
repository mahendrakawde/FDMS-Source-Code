/*
 * AlertVO.java
 *
 * Created on February 1, 2005, 7:57 AM
 */

package fdms.admin.alert.model;

/**
 * 
 * @author Guadalupe Garcia
 */
import java.io.Serializable;

public class AlertDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3500080389738143343L;

	private String messageType;

	private String message;

	private String viewable;

	private int seconds;

	private long endTime;

	/** Creates a new instance of AlertVO */
	public AlertDTO() {
	}

	public AlertDTO(String messageType, String message, String viewable, int seconds) {

		this.messageType = messageType;
		this.message = message;
		this.viewable = viewable;
		this.seconds = seconds;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setViewable(String viewable) {
		this.viewable = viewable;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public String getMessageType() {
		return messageType;
	}

	public String getMessage() {
		return message;
	}

	public String getViewable() {
		return viewable;
	}

	public int getSeconds() {
		return seconds;
	}

	public long getEndTime() {
		return endTime;
	}

}