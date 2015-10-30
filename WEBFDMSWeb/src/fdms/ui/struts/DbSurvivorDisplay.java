package fdms.ui.struts;


/**
 *
 * @author  Mark
 * @version 
 */
public class DbSurvivorDisplay extends java.lang.Object {

	private java.lang.String display;
	
	private java.lang.String id;
	
	private com.aldorsolutions.webfdms.beans.DbSurvivor dbSurvivor;
	
	/** Creates new DbSurvivorDisplay */
	public DbSurvivorDisplay() {
	}
	public DbSurvivorDisplay(com.aldorsolutions.webfdms.beans.DbSurvivor dbSurvivor) {
		this.dbSurvivor = dbSurvivor;
		setDisplay();
		setId();
	}
	public String getDisplay() {
		return display;
		
	}
	public String getId() {
		return id;
	}
	private void setDisplay() {
		StringBuffer sb = new StringBuffer();
		sb.append(dbSurvivor.getCSurvivorLName());
		sb.append(", ");
		sb.append(dbSurvivor.getCSurvivorFName());
		sb.append(" ");
		sb.append(dbSurvivor.getCSurvivorMName());
		sb.append(", ");
		sb.append(dbSurvivor.getCSurvivorRelated());
		if (dbSurvivor.getCSurvivorVoided().equalsIgnoreCase("V")){
			sb.append("(VOIDED contract)");
		}
		this.display = sb.toString();
	}
	private void setId() {
		int i = dbSurvivor.getLSurvivorMainKey();
		this.id = Integer.toString(i);
		
		
	}
}
