package com.aldorsolutions.webfdms.beans;

import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.Transaction;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.FormatNumber;
/**
 * DbServices - This class represents service related
 * information associated with one case.
 * Creation date: (11/26/2001 2:42:35 PM)
 * @author:
 */
public class DbServices extends com.aldorsolutions.webfdms.database.Persistent {
    static private final DbServicesPeer peer = new DbServicesPeer();
    
    private int	lSrvMainKey;
    private short	iSrvVersion;
    private String	cSrvOpenClose;
    //private BTRIEVE_DATE	DateModified;
    //private BTRIEVE_TIME	TimeModified;
    private String	cSrvDayOfWeek;	//  Converted from char[10]
    private String	cSrvTime;	//  Converted from char[15]
    private String	cSrvPlace;	//  Converted from char[50]
    private String	cSrvPlaceStreet;	//  Converted from char[30]
    private String	cSrvPlaceCity;	//  Converted from char[40]
    private String	cSrvPlaceState;	//  Converted from char[40]
    private String	cSrvPlacePhone;	//  Converted from char[40]
    private String	cSrvJewelryInst;	//  Converted from char[100]
    private String	cSrvMinister1;	//  Converted from char[50]
    private String	cSrvMinister1Email;	//  Converted from char[50]   
    private String	cSrvMinister2;	//  Converted from char[50]
    private String	cSrvMinister2Email;	//  Converted from char[50]      
    private String	cSrvChurch;	//  Converted from char[50]
    private String	cSrvChurchStreet;	//  Converted from char[30]
    private String	cSrvChurchPhone;	//  Converted from char[30]
    private String	cSrvChurchCitySt;	//  Converted from char[40]
    private String	cSrvOrganist;	//  Converted from char[50]
    private String	cSrvSoloist;	//  Converted from char[50]
    private String	cSrvSongs1;	//  Converted from char[150]
    private String	cSrvCem;	//  Converted from char[50]
    private String	cSrvCemStreet;	//  Converted from char[30]
    private String	cSrvCemCity;	//  Converted from char[30]
    private String	cSrvCemState;	//  Converted from char[15]
    private String	cSrvCemZip;	//  Converted from char[10]
    private String	cSrvCemCounty;	//  Converted from char[20]
    private String	cSrvCemSection;	//  Converted from char[30]
    private String	cSrvCemBlockNumber;	//  Converted from char[30]   
    private String	cSrvCemLot;	//  Converted from char[20]
    private String	cSrvCemGrave;	//  Converted from char[10]
    private String	cSrvCemSpace;	//  Converted from char[10]
    private String	cSrvTentEquipment;
    private String	cSrvSetSeal;
    //private String	cSrvVaultMfgr;	//  Converted from char[30]
    //private String	cSrvVaultName;	//  Converted from char[30]
    //private int		lSrvVaultUnit;
    //private String	cSrvCasketMfgr;	//  Converted from char[30]
    //private String	cSrvCasketName;	//  Converted from char[30]
    //private int		lSrvCasketUnit;
    private String	cSrvAutos;	//  Converted from char[150]
    private String	cSrvPickUpFamily;	//  Converted from char[50]
    private String	cSrvPickUpTime;	//  Converted from char[10]
    private short	iSrvCertifiedCopies;
    private String	cSrvCCSendTo;	//  Converted from char[80]
    private String	cSrvVisitation;	//  Converted from char[200]
    private String	cSrvSpecialService;	//  Converted from char[200]
    private String	cSrvRestoration;
    private String	cSrvHairStyle;	//  Converted from char[30]
    private String	cSrvDonations;	//  Converted from char[200]
    private String	cSrvCardStyle;	//  Converted from char[25]
    private short	iSrvCardCount;
    private String	cSrvMemorialStyle;	//  Converted from char[25]
    private short	iSrvMemorialCount;
    private String	cFlowerDescr;	//  Converted from char[200]
    private String	cFlowerSupplier;	//  Converted from char[200]
    private String	cDeliveryBox;
    private String	cPickupBox;
    //private String[]	cSrvPallBearer;	//  Converted from char[8][40]
    //private String[]	cSrvPallBearerStreet;	//  Converted from char[8][40]
    //private String[]	cSrvPallBearerCityst;	//  Converted from char[8][40]
    //private String[]	cSrvHonoraryBearer;	//  Converted from char[8][40]
    //private String[]	cSrvHonoraryStreet;	//  Converted from char[8][40]
    //private String[]	cSrvHonoraryCityst;	//  Converted from char[8][40]
    private String	cSrvSpecialInstructions;	//  Converted from char[200]
    private String	cSrvCremainsDisp;	//  Converted from char[80]
    private String	cSrvCemPhone;	//  Converted from char[20]
    private String	cSrvMinister1Addr;	//  Converted from char[30]
    private String	cSrvMinister1CitySt;	//  Converted from char[30]
    private String	cSrvMinister1Sal;	//  Converted from char[20]
    private String	cSrvMinister1Phone;	//  Converted from char[20]
    private String	cSrvMinister2Addr;	//  Converted from char[30]
    private String	cSrvMinister2CitySt;	//  Converted from char[30]
    private String	cSrvMinister2Sal;	//  Converted from char[20]
    private String	cSrvMinister2Phone;	//  Converted from char[20]
    private String	cSrvCemTime;	//  Converted from char[11]
    //private String	cSrvGraveSize;	//  Converted from char[25]
    //private String	cSrvGraveDigger;	//  Converted from char[25]
    //private String	cSrvStoneMason;	//  Converted from char[25]
    //private String	cSrvReopenGrave;	//  Converted from char[25]
    //private String	cSrvBodySize;	//  Converted from char[25]
    //private String	cSrvDressed;	//  Converted from char[25]
    //private String	cSrvHandles;	//  Converted from char[25]
    //private String	cSrvLining;	//  Converted from char[14]
    //private String	cObitNotice;	//  Converted from char[7800]
    private java.lang.String cSrvVaultMfgr;
    private java.lang.String cSrvVaultName;
    private int lSrvVaultCost;
    private java.lang.String cSrvCasketMfgr;
    private java.lang.String cSrvCasketName;
    private int lSrvCasketCost;
    private int memorialID;
    private String crematoryName;
    private String crematoryStreet;
    private String crematoryCity;
    private String crematoryState;
    private String crematoryZip;
    private String cremationDateOfService;
    private String addnlService;
    private String addnlServiceDate;
    private String addnlServiceTime;
    private String addnlServiceDay;
    
	/** Place for Additional Service */
	private String addnlServicePlace;
	
	/** Street for Additional Service */
	private String addnlServiceStreet;

	/** City for Additional Service */
	private String addnlServiceCity;
	
	/** Two char State ID for Additional Service */
	private String addnlServiceState;
	
	private String addnlServicePhone;
	
	/** Cemetery Vault */
	private String cemeteryVault;
	
	/** County of Cremation */
	private String crematoryCounty;
	
    
	/**
	 * @return Returns the addnlServiceDay.
	 */
	public String getAddnlServiceDay() {
		return addnlServiceDay;
	}
	/**
	 * @param addnlServiceDay The addnlServiceDay to set.
	 */
	public void setAddnlServiceDay(String addnlServiceDay) {
		this.addnlServiceDay = addnlServiceDay;
	}
	/**
	 * @return Returns the addnlService.
	 */
	public String getAddnlService() {
		return addnlService;
	}
	/**
	 * @param addnlService The addnlService to set.
	 */
	public void setAddnlService(String addnlService) {
		this.addnlService = addnlService;
	}
	/**
	 * @return Returns the addnlServiceDate.
	 */
	public String getAddnlServiceDate() {
		return addnlServiceDate;
	}
	/**
	 * @param addnlServiceDate The addnlServiceDate to set.
	 */
	public void setAddnlServiceDate(String addnlServiceDate) {
		this.addnlServiceDate = addnlServiceDate;
	}
	/**
	 * @return Returns the addnlServiceTime.
	 */
	public String getAddnlServiceTime() {
		return addnlServiceTime;
	}
	/**
	 * @param addnlServiceTime The addnlServiceTime to set.
	 */
	public void setAddnlServiceTime(String addnlServiceTime) {
		this.addnlServiceTime = addnlServiceTime;
	}
	/**
	 * @return Returns the peer.
	 */
	public static DbServicesPeer getPeer() {
		return peer;
	}
    public String getCDeliveryBox() {
        return cDeliveryBox;
    }
    public String getCFlowerDescr() {
        return cFlowerDescr;
    }
    public String getCFlowerSupplier() {
        return cFlowerSupplier;
    }
    public String getCPickupBox() {
        return cPickupBox;
    }
    public String getCSrvAutos() {
        return cSrvAutos;
    }
    public String getCSrvCardStyle() {
        return cSrvCardStyle;
    }
    /**
     * Insert the method's description here.
     * Creation date: (9/17/2002 4:32:40 PM)
     * @return java.lang.String
     */
    public java.lang.String getCSrvCasketMfgr() {
        return cSrvCasketMfgr;
    }
    /**
     * Insert the method's description here.
     * Creation date: (9/17/2002 4:32:58 PM)
     * @return java.lang.String
     */
    public java.lang.String getCSrvCasketName() {
        return cSrvCasketName;
    }
    public String getCSrvCCSendTo() {
        return cSrvCCSendTo;
    }
    public String getCSrvCem() {
        return cSrvCem;
    }
    public String getCSrvCemCity() {
        return cSrvCemCity;
    }
    public String getCSrvCemCounty() {
        return cSrvCemCounty;
    }
    public String getCSrvCemGrave() {
        return cSrvCemGrave;
    }
    public String getCSrvCemLot() {
        return cSrvCemLot;
    }
    public String getCSrvCemPhone() {
        return cSrvCemPhone;
    }
    public String getCSrvCemSection() {
        return cSrvCemSection;
    }
    public String getCSrvCemSpace() {
        return cSrvCemSpace;
    }
    public String getCSrvCemState() {
        return cSrvCemState;
    }
    public String getCSrvCemStreet() {
        return cSrvCemStreet;
    }
    public String getCSrvCemTime() {
        return cSrvCemTime;
    }
    public String getCSrvCemZip() {
        return cSrvCemZip;
    }
    public String getCSrvChurch() {
        return cSrvChurch;
    }
    public String getCSrvChurchCitySt() {
        return cSrvChurchCitySt;
    }
    public String getCSrvChurchStreet() {
        return cSrvChurchStreet;
    }
    public String getCSrvChurchPhone() {
        return cSrvChurchPhone;
    }
    public String getCSrvCremainsDisp() {
        return cSrvCremainsDisp;
    }
    public String getCSrvDayOfWeek() {
        return cSrvDayOfWeek;
    }
    public String getCSrvDonations() {
        return cSrvDonations;
    }
    public String getCSrvHairStyle() {
        return cSrvHairStyle;
    }
    public String getCSrvJewelryInst() {
        return cSrvJewelryInst;
    }
    public String getCSrvMemorialStyle() {
        return cSrvMemorialStyle;
    }
    public String getCSrvMinister1() {
        return cSrvMinister1;
    }
    
    public String getCSrvMinister1Email() {
        return cSrvMinister1Email;
    }    
    
    public String getCSrvMinister1Addr() {
        return cSrvMinister1Addr;
    }
    public String getCSrvMinister1CitySt() {
        return cSrvMinister1CitySt;
    }
    public String getCSrvMinister1Phone() {
        return cSrvMinister1Phone;
    }
    public String getCSrvMinister1Sal() {
        return cSrvMinister1Sal;
    }
    public String getCSrvMinister2() {
        return cSrvMinister2;
    }
    
    public String getCSrvMinister2Email() {
        return cSrvMinister2Email;
    }        
    
    public String getCSrvMinister2Addr() {
        return cSrvMinister2Addr;
    }
    public String getCSrvMinister2CitySt() {
        return cSrvMinister2CitySt;
    }
    public String getCSrvMinister2Phone() {
        return cSrvMinister2Phone;
    }
    public String getCSrvMinister2Sal() {
        return cSrvMinister2Sal;
    }
    public String getCSrvOpenClose() {
        return cSrvOpenClose;
    }
    public String getCSrvOrganist() {
        return cSrvOrganist;
    }
    public String getCSrvPickUpFamily() {
        return cSrvPickUpFamily;
    }
    public String getCSrvPickUpTime() {
        return cSrvPickUpTime;
    }
    public String getCSrvPlace() {
        return cSrvPlace;
    }
    public String getCSrvPlaceCity() {
        return cSrvPlaceCity;
    }
    public String getCSrvPlaceState() {
        return cSrvPlaceState;
    }
    public String getCSrvPlaceStreet() {
        return cSrvPlaceStreet;
    }
    public String getCSrvRestoration() {
        return cSrvRestoration;
    }
    public String getCSrvSetSeal() {
        return cSrvSetSeal;
    }
    public String getCSrvSoloist() {
        return cSrvSoloist;
    }
    public String getCSrvSongs1() {
        return cSrvSongs1;
    }
    public String getCSrvSpecialInstructions() {
        return cSrvSpecialInstructions;
    }
    public String getCSrvSpecialService() {
        return cSrvSpecialService;
    }
    public String getCSrvTentEquipment() {
        return cSrvTentEquipment;
    }
    public String getCSrvTime() {
        return cSrvTime;
    }
    /**
     * Insert the method's description here.
     * Creation date: (9/17/2002 4:31:31 PM)
     * @return java.lang.String
     */
    public java.lang.String getCSrvVaultMfgr() {
        return cSrvVaultMfgr;
    }
    /**
     * Insert the method's description here.
     * Creation date: (9/17/2002 4:31:48 PM)
     * @return java.lang.String
     */
    public java.lang.String getCSrvVaultName() {
        return cSrvVaultName;
    }
    public String getCSrvVisitation() {
        return cSrvVisitation;
    }
    public short getISrvCardCount() {
        return iSrvCardCount;
    }
    public short getISrvCertifiedCopies() {
        return iSrvCertifiedCopies;
    }
    public short getISrvMemorialCount() {
        return iSrvMemorialCount;
    }
    public short getISrvVersion() {
        return iSrvVersion;
    }
    /**
     * Insert the method's description here.
     * Creation date: (9/17/2002 4:33:20 PM)
     * @return int
     */
    public int getLSrvCasketCost() {
        return lSrvCasketCost;
    }
    public int getLSrvMainKey() {
        return lSrvMainKey;
    }
    /**
     * Insert the method's description here.
     * Creation date: (9/17/2002 4:32:15 PM)
     * @return int
     */
    public int getLSrvVaultCost() {
        return lSrvVaultCost;
    }
    /**
     * Insert the method's description here.
     * Creation date: (10/9/2002 1:09:54 PM)
     * @return int
     */
    public int getMemorialID() {
        return memorialID;
    }
    /**
     * getPersistentPeer method comment.
     */
    protected com.aldorsolutions.webfdms.database.PersistentPeer getPersistentPeer() {
        return peer;
    }
    /**
     * isLocked method comment.
     */
    public boolean isLocked() {
        return false;
    }
    /**
     * Move data from hashtable and copy into class variables
     * Peer object restores from database to hashtable.
     */
    public void restore(Transaction t, java.util.Hashtable data) throws PersistenceException {
        setId(					FormatNumber.parseInteger(data.get(DbServicesPeer.IDENTITY).toString()));
        lSrvMainKey				= FormatNumber.parseInteger((String)data.get(DbServicesPeer.CASE_ID).toString());
        iSrvVersion				= FormatNumber.parseShort((String)data.get(DbServicesPeer.FILEVERSION).toString());
        cDeliveryBox			= data.get(DbServicesPeer.FLOWERDELIVERY).toString();
        cFlowerDescr			= data.get(DbServicesPeer.FLOWERDESCR).toString();
        cFlowerSupplier			= data.get(DbServicesPeer.FLOWERSUPPLIER).toString();
        cPickupBox				= data.get(DbServicesPeer.FLOWERPICKUP).toString();
        cSrvAutos				= data.get(DbServicesPeer.AUTOS).toString();
        cSrvCardStyle			= data.get(DbServicesPeer.CARDSTYLE).toString();
        setCSrvCCSendTo(		(String)data.get(DbServicesPeer.CERTIFIEDSENDTO));
        setCSrvCem(				(String)data.get(DbServicesPeer.CEMETERYNAME));
        setCSrvCemCity(			(String)data.get(DbServicesPeer.CEMETERYCITY));
        setCSrvCemCounty(		(String)data.get(DbServicesPeer.CEMETERYCOUNTY));
        setCSrvCemGrave(		(String)data.get(DbServicesPeer.CEMETERYGRAVE));
        setCSrvCemLot(			(String)data.get(DbServicesPeer.CEMETERYLOT));
        setCSrvCemPhone(		(String)data.get(DbServicesPeer.CEMETERYPHONE));
        setCSrvCemSection(		(String)data.get(DbServicesPeer.CEMETERYSECTION));
        setCSrvCemBlockNumber(	(String)data.get(DbServicesPeer.CEMETERYBLOCKNUMBER));       
        setCSrvCemSpace(		(String)data.get(DbServicesPeer.CEMETERYSPACE));
        setCSrvCemState(		(String)data.get(DbServicesPeer.CEMETERYSTATE));
        setCSrvCemStreet(		(String)data.get(DbServicesPeer.CEMETERYADDR));
        setCSrvCemTime(			(String)data.get(DbServicesPeer.CEMETERYTIME));
        setCSrvCemZip(			(String)data.get(DbServicesPeer.CEMETERYZIP));
        setCSrvChurch(			(String)data.get(DbServicesPeer.CHURCH));
        setCSrvChurchCitySt(	(String)data.get(DbServicesPeer.CHURCHCITY));
        setCSrvChurchStreet(	(String)data.get(DbServicesPeer.CHURCHADDR));
        setCSrvChurchPhone(	(String)data.get(DbServicesPeer.CHURCHPHONE));
        setCSrvCremainsDisp(	(String)data.get(DbServicesPeer.CREMAINSDISP));
        setCSrvDayOfWeek(		(String)data.get(DbServicesPeer.DAYOFWEEK));
        setCSrvDonations(		(String)data.get(DbServicesPeer.DONATIONS));
        setCSrvHairStyle(		(String)data.get(DbServicesPeer.HAIRSTYLE));
        setCSrvJewelryInst(		(String)data.get(DbServicesPeer.JEWELRY));
        setCSrvMemorialStyle(	(String)data.get(DbServicesPeer.MEMORIALSTYLE));
        setCSrvMinister1(		(String)data.get(DbServicesPeer.MINISTER1));
        setCSrvMinister1Email(	(String)data.get(DbServicesPeer.MINISTER1EMAIL));  
        setCSrvMinister2Email(	(String)data.get(DbServicesPeer.MINISTER2EMAIL));                
        setCSrvMinister1Addr(	(String)data.get(DbServicesPeer.MINISTER1ADDR));
        setCSrvMinister1CitySt(	(String)data.get(DbServicesPeer.MINISTER1CITY));
        setCSrvMinister1Phone(	(String)data.get(DbServicesPeer.MINISTER1PHONE));
        setCSrvMinister1Sal(	(String)data.get(DbServicesPeer.MINISTER1SAL));
        setCSrvMinister2(		(String)data.get(DbServicesPeer.MINISTER2));
        setCSrvMinister2Addr(	(String)data.get(DbServicesPeer.MINISTER2ADDR));
        setCSrvMinister2CitySt(	(String)data.get(DbServicesPeer.MINISTER2CITY));
        setCSrvMinister2Phone(	(String)data.get(DbServicesPeer.MINISTER2PHONE));
        setCSrvMinister2Sal(	(String)data.get(DbServicesPeer.MINISTER2SAL));
        cSrvOpenClose			= data.get(DbServicesPeer.OPENCLOSEBOX).toString();
        setCSrvOrganist(		(String)data.get(DbServicesPeer.ORGANIST));
        setCSrvPickUpFamily(	(String)data.get(DbServicesPeer.PICKUPFAMILYAT));
        setCSrvPickUpTime(		(String)data.get(DbServicesPeer.PICKUPFAMTIME));
        setCSrvPlace(	 		(String)data.get(DbServicesPeer.PLACESERVICE));
        setCSrvPlaceCity(		(String)data.get(DbServicesPeer.PLACECITY));
        setCSrvPlaceState(		(String)data.get(DbServicesPeer.PLACESTATE));
        setCSrvPlacePhone(		(String)data.get(DbServicesPeer.PLACEPHONE));
        setCSrvPlaceStreet(		(String)data.get(DbServicesPeer.PLACESTREET));
        cSrvRestoration			= data.get(DbServicesPeer.RESTORATION).toString();
        cSrvSetSeal				= data.get(DbServicesPeer.SETSEAL).toString();
        setCSrvSoloist(			(String)data.get(DbServicesPeer.SOLOIST));
        setCSrvSongs1(				(String)data.get(DbServicesPeer.SONGS));
        setCSrvSpecialInstructions(	(String)data.get(DbServicesPeer.SPECIALINSTR));
        setCSrvSpecialService(		(String)data.get(DbServicesPeer.SPECIALSERVICES));
        cSrvTentEquipment		= data.get(DbServicesPeer.TENTEQUIPMENT).toString();
        setCSrvTime(				(String)data.get(DbServicesPeer.TIMEOFSERVICE));
        setCSrvVisitation(			(String)data.get(DbServicesPeer.VISITATION));
        setISrvCardCount(			FormatNumber.parseShort(data.get(DbServicesPeer.CARDCOUNT).toString()));
        setISrvCertifiedCopies(		FormatNumber.parseShort(data.get(DbServicesPeer.CERTIFIEDCOUNT).toString()));
        setISrvMemorialCount(		FormatNumber.parseShort(data.get(DbServicesPeer.MEMORIALCOUNT).toString()));
        cSrvVaultMfgr       = data.get(DbServicesPeer.VAULTMFGER).toString();
        cSrvVaultName       = data.get(DbServicesPeer.VAULTNAME).toString();
        lSrvVaultCost       = FormatNumber.parseInteger(data.get(DbServicesPeer.VAULTCOST).toString());
        cSrvCasketMfgr      = data.get(DbServicesPeer.CASKETMFGER).toString();
        cSrvCasketName      = data.get(DbServicesPeer.CASKETNAME).toString();
        lSrvCasketCost      = FormatNumber.parseInteger(data.get(DbServicesPeer.CASKETCOST).toString());
        memorialID          = FormatNumber.parseInteger(data.get(DbServicesPeer.MEMORIALID).toString());
        crematoryName       = data.get(DbServicesPeer.CREMATORYNAME).toString();
        crematoryStreet     = data.get(DbServicesPeer.CREMATORYSTREET).toString();
        crematoryCity       = data.get(DbServicesPeer.CREMATORYCITY).toString();
        crematoryState      = data.get(DbServicesPeer.CREMATORYSTATE).toString();
        crematoryZip        = data.get(DbServicesPeer.CREMATORYZIP).toString();
        crematoryCounty     = data.get(DbServicesPeer.CREMATORYCOUNTY).toString();
        cemeteryVault 		= data.get(DbServicesPeer.CEMETERYVAULT).toString();
        cremationDateOfService = data.get(DbServicesPeer.CREMATIONDATEOFSERVICE).toString();
        addnlService = data.get(DbServicesPeer.ADDNLSERVICE).toString();
        addnlServiceDate = data.get(DbServicesPeer.ADDNLSERVICEDATE).toString();
        addnlServiceTime = data.get(DbServicesPeer.ADDNLSERVICETIME).toString();
        addnlServiceDay = data.get(DbServicesPeer.ADDNLSERVICEDAY).toString();
        addnlServicePlace = data.get(DbServicesPeer.ADDNLSERVICEPLACE).toString();
        addnlServiceStreet = data.get(DbServicesPeer.ADDNLSERVICESTREET).toString();
        addnlServiceCity = data.get(DbServicesPeer.ADDNLSERVICECITY).toString();
        addnlServiceState = data.get(DbServicesPeer.ADDNLSERVICESTATE).toString(); 
        addnlServicePhone = data.get(DbServicesPeer.ADDNLSERVICEPHONE).toString();
    }
    public void setCDeliveryBox(String lcl_arg0) {
        if (lcl_arg0!= null && lcl_arg0.length()>0){
            cDeliveryBox = lcl_arg0.substring(0,1);
            modify();
        }
    }
    public void setCFlowerDescr(String lcl_arg0) {
        cFlowerDescr = lcl_arg0;
        modify();
    }
    public void setCFlowerSupplier(String lcl_arg0) {
        cFlowerSupplier = lcl_arg0;
        modify();
    }
    public void setCPickupBox(String lcl_arg0) {
        if (lcl_arg0!= null && lcl_arg0.length()>0){
            cPickupBox = lcl_arg0.substring(0,1);
            modify();
        }
    }
    public void setCSrvAutos(String lcl_arg0) {
        cSrvAutos = lcl_arg0;
        modify();
    }
    public void setCSrvCardStyle(String lcl_arg0) {
        cSrvCardStyle = lcl_arg0;
        modify();
    }
    /**
     * Insert the method's description here.
     * Creation date: (9/17/2002 4:32:40 PM)
     * @param newCSrvCasketMfgr java.lang.String
     */
    public void setCSrvCasketMfgr(java.lang.String newCSrvCasketMfgr) {
        cSrvCasketMfgr = newCSrvCasketMfgr;
        modify();
    }
    /**
     * Insert the method's description here.
     * Creation date: (9/17/2002 4:32:58 PM)
     * @param newCSrvCasketName java.lang.String
     */
    public void setCSrvCasketName(java.lang.String newCSrvCasketName) {
        cSrvCasketName = newCSrvCasketName;
        modify();
    }
    public void setCSrvCCSendTo(String lcl_arg0) {
        cSrvCCSendTo = lcl_arg0;
        modify();
    }
    public void setCSrvCem(String lcl_arg0) {
        cSrvCem = lcl_arg0;
        modify();
    }
    public void setCSrvCemCity(String lcl_arg0) {
        cSrvCemCity = lcl_arg0;
        modify();
    }
    public void setCSrvCemCounty(String lcl_arg0) {
        cSrvCemCounty = lcl_arg0;
        modify();
    }
    public void setCSrvCemGrave(String lcl_arg0) {
        cSrvCemGrave = lcl_arg0;
        modify();
    }
    public void setCSrvCemLot(String lcl_arg0) {
        cSrvCemLot = lcl_arg0;
        modify();
    }
    public void setCSrvCemPhone(String lcl_arg0) {
        cSrvCemPhone = lcl_arg0;
        modify();
    }
    public void setCSrvCemSection(String lcl_arg0) {
        cSrvCemSection = lcl_arg0;
        modify();
    }
    public void setCSrvCemSpace(String lcl_arg0) {
        cSrvCemSpace = lcl_arg0;
        modify();
    }
    public void setCSrvCemState(String lcl_arg0) {
        cSrvCemState = lcl_arg0;
        modify();
    }
    public void setCSrvCemStreet(String lcl_arg0) {
        cSrvCemStreet = lcl_arg0;
        modify();
    }
    public void setCSrvCemTime(String lcl_arg0) {
        cSrvCemTime = lcl_arg0;
        modify();
    }
    public void setCSrvCemZip(String lcl_arg0) {
        cSrvCemZip = lcl_arg0;
        modify();
    }
    public void setCSrvChurch(String lcl_arg0) {
        cSrvChurch = lcl_arg0;
        modify();
    }
    public void setCSrvChurchCitySt(String lcl_arg0) {
        cSrvChurchCitySt = lcl_arg0;
        modify();
    }
    public void setCSrvChurchStreet(String lcl_arg0) {
        cSrvChurchStreet = lcl_arg0;
        modify();
    }
    public void setCSrvChurchPhone(String lcl_arg0) {
        cSrvChurchPhone = lcl_arg0;
        modify();
    }
    public void setCSrvCremainsDisp(String lcl_arg0) {
        cSrvCremainsDisp = lcl_arg0;
        modify();
    }
    public void setCSrvDayOfWeek(String lcl_arg0) {
        cSrvDayOfWeek = lcl_arg0;
        modify();
    }
    public void setCSrvDonations(String lcl_arg0) {
        cSrvDonations = lcl_arg0;
        modify();
    }
    public void setCSrvHairStyle(String lcl_arg0) {
        cSrvHairStyle = lcl_arg0;
        modify();
    }
    public void setCSrvJewelryInst(String lcl_arg0) {
        cSrvJewelryInst = lcl_arg0;
        modify();
    }
    public void setCSrvMemorialStyle(String lcl_arg0) {
        cSrvMemorialStyle = lcl_arg0;
        modify();
    }
    public void setCSrvMinister1(String lcl_arg0) {
        cSrvMinister1 = lcl_arg0;
        modify();
    }
    
    public void setCSrvMinister1Email(String lcl_arg0) {
        cSrvMinister1Email = lcl_arg0;
        modify();
    }   
    
    public void setCSrvMinister1Addr(String lcl_arg0) {
        cSrvMinister1Addr = lcl_arg0;
        modify();
    }
    public void setCSrvMinister1CitySt(String lcl_arg0) {
        cSrvMinister1CitySt = lcl_arg0;
        modify();
    }
    public void setCSrvMinister1Phone(String lcl_arg0) {
        cSrvMinister1Phone = lcl_arg0;
        modify();
    }
    public void setCSrvMinister1Sal(String lcl_arg0) {
        cSrvMinister1Sal = lcl_arg0;
        modify();
    }
    public void setCSrvMinister2(String lcl_arg0) {
        cSrvMinister2 = lcl_arg0;
        modify();
    }
    
    public void setCSrvMinister2Email(String lcl_arg0) {
        cSrvMinister2Email = lcl_arg0;
        modify();
    }       
    
    public void setCSrvMinister2Addr(String lcl_arg0) {
        cSrvMinister2Addr = lcl_arg0;
        modify();
    }
    public void setCSrvMinister2CitySt(String lcl_arg0) {
        cSrvMinister2CitySt = lcl_arg0;
        modify();
    }
    public void setCSrvMinister2Phone(String lcl_arg0) {
        cSrvMinister2Phone = lcl_arg0;
        modify();
    }
    public void setCSrvMinister2Sal(String lcl_arg0) {
        cSrvMinister2Sal = lcl_arg0;
        modify();
    }
    public void setCSrvOpenClose(String lcl_arg0) {
        if (lcl_arg0!= null && lcl_arg0.length()>0){
            cSrvOpenClose = lcl_arg0.substring(0,1);
            modify();
        }
    }
    public void setCSrvOrganist(String lcl_arg0) {
        cSrvOrganist = lcl_arg0;
        modify();
    }
    public void setCSrvPickUpFamily(String lcl_arg0) {
        cSrvPickUpFamily = lcl_arg0;
        modify();
    }
    public void setCSrvPickUpTime(String lcl_arg0) {
        cSrvPickUpTime = lcl_arg0;
        modify();
    }
    public void setCSrvPlace(String lcl_arg0) {
        cSrvPlace = lcl_arg0;
        modify();
    }
    public void setCSrvPlaceCity(String lcl_arg0) {
        cSrvPlaceCity = lcl_arg0;
        modify();
    }
    public void setCSrvPlaceState(String lcl_arg0) {
        cSrvPlaceState = lcl_arg0;
        modify();
    }
    /**
	 * @return the cSrvPlacePhone
	 */
	public String getCSrvPlacePhone() {
		return cSrvPlacePhone;
	}
	/**
	 * @param srvPlacePhone the cSrvPlacePhone to set
	 */
	public void setCSrvPlacePhone(String lcl_arg0) {
		cSrvPlacePhone = lcl_arg0;
		modify();
	}
	public void setCSrvPlaceStreet(String lcl_arg0) {
        cSrvPlaceStreet = lcl_arg0;
        modify();
    }
    public void setCSrvRestoration(String lcl_arg0) {
        if (lcl_arg0!= null && lcl_arg0.length()>0){
            cSrvRestoration = lcl_arg0.substring(0,1);
            modify();
        }
    }
    public void setCSrvSetSeal(String lcl_arg0) {
        if (lcl_arg0!= null && lcl_arg0.length()>0){
            cSrvSetSeal = lcl_arg0.substring(0,1);
            modify();
        }
    }
    public void setCSrvSoloist(String lcl_arg0) {
        cSrvSoloist = lcl_arg0;
        modify();
    }
    public void setCSrvSongs1(String lcl_arg0) {
        cSrvSongs1 = lcl_arg0;
        modify();
    }
    public void setCSrvSpecialInstructions(String lcl_arg0) {
        modify();
        cSrvSpecialInstructions = lcl_arg0;
    }
    public void setCSrvSpecialService(String lcl_arg0) {
        cSrvSpecialService = lcl_arg0;
        modify();
    }
    public void setCSrvTentEquipment(String lcl_arg0) {
        if (lcl_arg0!= null && lcl_arg0.length()>0){
            cSrvTentEquipment = lcl_arg0.substring(0,1);
            modify();
        }
    }
    public void setCSrvTime(String lcl_arg0) {
        cSrvTime = lcl_arg0;
        modify();
    }
    /**
     * Insert the method's description here.
     * Creation date: (9/17/2002 4:31:31 PM)
     * @param newCSrvVaultMfgr java.lang.String
     */
    public void setCSrvVaultMfgr(java.lang.String newCSrvVaultMfgr) {
        cSrvVaultMfgr = newCSrvVaultMfgr;
        modify();}
    /**
     * Insert the method's description here.
     * Creation date: (9/17/2002 4:31:48 PM)
     * @param newCSrvVaultName java.lang.String
     */
    public void setCSrvVaultName(java.lang.String newCSrvVaultName) {
        cSrvVaultName = newCSrvVaultName;
        modify();
    }
    public void setCSrvVisitation(String lcl_arg0) {
        cSrvVisitation = lcl_arg0;
        modify();
    }
    /**
     * Get the ID key for this object from the hashtable.
     * DbUser objects can be accessed by "Name"
     * So, first see if "Name" is being used for restoring
     * or if ID# is being used.
     */
    public void setId(java.util.Hashtable h) {
        //if (h.containsKey(DbServicesPeer.NAME)){
        //	getPersistentPeer().restore(this, t);
        //}
        setId(((Integer)h.get(DbServicesPeer.IDENTITY)).intValue());
    }
    public void setISrvCardCount(short lcl_arg0) {
        modify();
        iSrvCardCount = lcl_arg0;
    }
    public void setISrvCertifiedCopies(short lcl_arg0) {
        iSrvCertifiedCopies = lcl_arg0;
        modify();
    }
    public void setISrvMemorialCount(short lcl_arg0) {
        iSrvMemorialCount = lcl_arg0;
        modify();
    }
    public void setISrvVersion(short lcl_arg0) {
        iSrvVersion = lcl_arg0;
        modify();
    }
    /**
     * Insert the method's description here.
     * Creation date: (9/17/2002 4:33:20 PM)
     * @param newLSrvCasketCost int
     */
    public void setLSrvCasketCost(int newLSrvCasketCost) {
        lSrvCasketCost = newLSrvCasketCost;
    }
    public void setLSrvMainKey(int lcl_arg0) {
        lSrvMainKey = lcl_arg0;
        setId(lcl_arg0);
    }
    /**
     * Insert the method's description here.
     * Creation date: (9/17/2002 4:32:15 PM)
     * @param newLSrvVaultCost int
     */
    public void setLSrvVaultCost(int newLSrvVaultCost) {
        lSrvVaultCost = newLSrvVaultCost;
    }
    /**
     * Insert the method's description here.
     * Creation date: (10/9/2002 1:09:54 PM)
     * @param newMemorialID int
     */
    public void setMemorialID(int newMemorialID) {
        memorialID = newMemorialID;
    }
    
    public void setCrematoryName(String crematoryName) {
        this.crematoryName = crematoryName;
    }
    
    public String getCrematoryName() {
        return crematoryName;
    }
    
    public void setCrematoryStreet(String crematoryStreet) {
        this.crematoryStreet = crematoryStreet;
    }
    
    public String getCrematoryStreet() {
        return crematoryStreet;
    }
    
    public void setCrematoryCity(String crematoryCity) {
        this.crematoryCity = crematoryCity;
    }
    
    public String getCrematoryCity() {
        return crematoryCity;
    }
    
    public void setCrematoryState(String crematoryState) {
        this.crematoryState = crematoryState;
    }
    
    public String getCrematoryState() {
        return crematoryState;
    }
    
    public void setCrematoryZip(String crematoryZip) {
        this.crematoryZip = crematoryZip;
    }
    
    public String getCrematoryZip() {
        return crematoryZip;
    }
    
    public void setCremationDateOfService(String cremationDateOfService) {
        this.cremationDateOfService = cremationDateOfService;
    }
    
    public String getCremationDateOfService() {
		return cremationDateOfService;
	}

	public String getAddnlServiceCity() {
		return addnlServiceCity;
	}

	public void setAddnlServiceCity(String addnlServiceCity) {
		this.addnlServiceCity = addnlServiceCity;
	}

	public String getAddnlServicePlace() {
		return addnlServicePlace;
	}

	public void setAddnlServicePlace(String addnlServicePlace) {
		this.addnlServicePlace = addnlServicePlace;
	}

	public String getAddnlServiceState() {
		return addnlServiceState;
	}

	public void setAddnlServiceState(String addnlServiceState) {
		this.addnlServiceState = addnlServiceState;
	}
	
	public String getAddnlServicePhone() {
		return addnlServicePhone;
	}

	public void setAddnlServicePhone(String addnlServicePhone) {
		this.addnlServicePhone = addnlServicePhone;
	}

	public String getAddnlServiceStreet() {
		return addnlServiceStreet;
	}

	public void setAddnlServiceStreet(String addnlServiceStreet) {
		this.addnlServiceStreet = addnlServiceStreet;
	}

	public String getCemeteryVault() {
		return cemeteryVault;
	}

	public void setCemeteryVault(String cemetaryVault) {
		this.cemeteryVault = cemetaryVault;
	}

	public String getCrematoryCounty() {
		return crematoryCounty;
	}

	public void setCrematoryCounty(String crematoryCounty) {
		this.crematoryCounty = crematoryCounty;
	}
    
	public String getFormatedServiceAddress(){
		StringBuffer address = new StringBuffer();
		
    	if(cSrvPlace != null && !"".equals(cSrvPlace)){
    		address.append(cSrvPlace + ", " );
    	}
       	if(cSrvPlaceStreet != null && !"".equals(cSrvPlaceStreet)){
    		address.append(cSrvPlaceStreet + ", " );
    	}
    	if(cSrvPlaceCity != null && !"".equals(cSrvPlaceCity)){
    		address.append(cSrvPlaceCity + ", " );
    	}
       	if(cSrvPlaceState != null && !"".equals(cSrvPlaceState)){
    		address.append(cSrvPlaceState );
    	}
    	
    	if(address.toString().endsWith(",")){
    		address.deleteCharAt(address.length()-1);
    	}
    	
    	return address.toString();
	}
	
	public String getFormatedServiceAddiAddress(){
		StringBuffer address = new StringBuffer();
		
    	if(addnlServicePlace != null && !"".equals(addnlServicePlace)){
    		address.append(addnlServicePlace + ", " );
    	}
       	if(addnlServiceStreet != null && !"".equals(addnlServiceStreet)){
    		address.append(addnlServiceStreet + ", " );
    	}
    	if(addnlServiceCity != null && !"".equals(addnlServiceCity)){
    		address.append(addnlServiceCity + ", " );
    	}
       	if(addnlServiceState != null && !"".equals(addnlServiceState)){
    		address.append(addnlServiceState );
    	}
    	if(address.toString().endsWith(",")){
    		address.deleteCharAt(address.length()-1);
    	}
    	
    	return address.toString();
	}
	
	public String getServiceTime(){
		return cSrvTime;
	}
	
	public String getAddiServiceFormatedDate(){
		return FormatDate.MDYtoMMDDYYYY(addnlServiceDate);
	}
	/**
	 * @return the cSrvCemBlockNumber
	 */
	public String getCSrvCemBlockNumber() {
		return cSrvCemBlockNumber;
	}
	/**
	 * @param srvCemBlockNumber the cSrvCemBlockNumber to set
	 */
	public void setCSrvCemBlockNumber(String srvCemBlockNumber) {
		cSrvCemBlockNumber = srvCemBlockNumber;
	}

	
}
