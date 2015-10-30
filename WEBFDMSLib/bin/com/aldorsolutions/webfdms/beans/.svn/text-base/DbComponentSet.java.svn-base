package com.aldorsolutions.webfdms.beans;


import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
/**
 * A DbComponentSet represents all Components or all for one vitals ID
 * The peer class provides the class name and SQL for restoring
 * Creation date: (11/26/2001 11:14:33 AM)
 * @author: 
 */
public class DbComponentSet extends com.aldorsolutions.webfdms.database.PersistentSet {
	final static DbComponentSetPeer peer= new DbComponentSetPeer();
	private com.aldorsolutions.webfdms.beans.DbComponent[] complist;
	private int vitalsid;
/**
 * Insert the method's description here.
 * Creation date: (5/23/2002 11:55:37 AM)
 */
public DbComponentSet() {}
/**
 * This constructor gets all of the components for a case.
 * Creation date: (5/22/2002 4:32:46 PM)
 * @param t com.aldorsolutions.webfdms.database.DatabaseTransaction
 * @param vitalsid int
 */
public DbComponentSet(com.aldorsolutions.webfdms.database.DatabaseTransaction t, int vitalsid) 
	throws PersistenceException{
	setVitalsid(vitalsid);
	
	java.util.Hashtable h = new java.util.Hashtable();

	h.put(DbComponentPeer.CASE_ID, new Integer(vitalsid));
	restore(t,h);
	com.aldorsolutions.webfdms.database.PersistentI[] obs = getPersistents();
	complist = new com.aldorsolutions.webfdms.beans.DbComponent[obs.length];
	// This trick is needed to make a Persistent[] into a Billto[]
	for(int i=0; i<obs.length; i++) {
		complist[i] = (com.aldorsolutions.webfdms.beans.DbComponent)obs[i];
	}
}
/**
 * Make ths sum of components for this case match the total sales amount
 * passed into this method. This is done by summing all of the components.
 * The difference between the sum and the case total is adjusted into
 * the default component.
 * The default component is added if it does not already exist.
 * Creation date: (5/23/2002 1:46:10 PM)
 * @param caseTotal int
 * @exception com.aldorsolutions.webfdms.database.PersistenceException The exception description.
 */
public void adjustSalesSumTo(DatabaseTransaction t, int caseTotal, String dbLookup) throws com.aldorsolutions.webfdms.database.PersistenceException {
	int compsum=0;
	compsum = getSumAllComponents();
	//AppLog.trace("adjustSalesSumTo. current sum:"+compsum+" adjust to:"+caseTotal);
	// get region for this case
	DbCase acase = FdmsDb.getInstance().getCase(t, getVitalsid());
	if (acase==null){
		throw new PersistenceException("adjustSalesSumTo: invalid vitals ID");
	}
	int region = acase.getLocale();
	DbComponent defcomp = getDefaultComponent(region, dbLookup);
	//AppLog.trace("adjustSalesSumTo. updated amount:"+(defcomp.getSaleAmt() + caseTotal - compsum));
	defcomp.setSaleAmt( defcomp.getSaleAmt() + caseTotal - compsum );
	t.addPersistent( defcomp );
}
/**
 * Return array of components for this case
 * Creation date: (5/22/2002 4:33:47 PM)
 * @return com.aldorsolutions.webfdms.beans.DbComponent[]
 */
public com.aldorsolutions.webfdms.beans.DbComponent[] getComplist() {
	return complist;
}
/**
 * Return the default payment component for this case.
 * If the case does not presently have this component, it is created.
 * The persistent IS NOT added to the transaction, thus, it must be
 * added manually with t.addPersistent() if being updated or inserted.
 * Creation date: (5/23/2002 2:53:54 PM)
 * @return com.aldorsolutions.webfdms.beans.DbComponent
 * @param param com.aldorsolutions.webfdms.database.DatabaseTransaction
 * @param region int
 * @exception com.aldorsolutions.webfdms.database.PersistenceException The exception description.
 */
public DbComponent getDefaultComponent(int region, String dbLookup) throws com.aldorsolutions.webfdms.database.PersistenceException {
	DbComponent caseDefaultComp = null;
	PaymentComponentAvailableSet compsAvailable = null;
	PaymentComponentAvailable	defComp = null;
	
	// Get components available
	compsAvailable = new PaymentComponentAvailableSet(region, dbLookup) ;
	defComp = compsAvailable.getDefaultComponent();
	if (defComp==null){
		throw new PersistenceException("Critical Error: No default payment component found.");
	}
	// Find the default component for this case, if it is already allocated
	if (complist!= null){
		for (int i=0; i<complist.length; i++){
			if (complist[i].getCode().equalsIgnoreCase( defComp.getCode()) ){
				caseDefaultComp = complist[i];
			}
		}
	}
	// If we don't yet have a default component for this case, then add one
	if (caseDefaultComp==null){
		caseDefaultComp = new DbComponent();
		caseDefaultComp.setNew();
		caseDefaultComp.setVitalsID(	getVitalsid());
		caseDefaultComp.setCode( 		defComp.getCode() );
		caseDefaultComp.setDescription( defComp.getDescription() );
		caseDefaultComp.setType(		defComp.getType() );
	}
	return caseDefaultComp;
}
/**
 * getPersistentSetPeer method comment.
 */
protected com.aldorsolutions.webfdms.database.PersistentSetPeer getPersistentSetPeer() {
	return peer;
}
/**
 * Return the sum of sales amouns for components for this case.
 * Creation date: (5/22/2002 4:30:44 PM)
 * @return int
 */
public int getSumAllComponents() {
	int sum=0;
	if (complist==null) return 0;
	for (int i=0; i<complist.length; i++){
		sum+=complist[i].getSaleAmt();
	}
	return sum;
}
/**
 * Insert the method's description here.
 * Creation date: (5/23/2002 2:43:46 PM)
 * @return int
 */
public int getVitalsid() {
	return vitalsid;
}
/**
 * Insert the method's description here.
 * Creation date: (5/23/2002 2:43:46 PM)
 * @param newM_vitalsid int
 */
protected void setVitalsid(int newvitalsid) {
	vitalsid = newvitalsid;
}
}
