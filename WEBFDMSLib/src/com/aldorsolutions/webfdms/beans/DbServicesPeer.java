package com.aldorsolutions.webfdms.beans;

import java.sql.Statement;

/**
 * DbServicesPeer supplies SQL for Persistent Class
 * Creation date: (11/14/2001 4:13:09 PM)
 * @author:
 */
public class DbServicesPeer extends com.aldorsolutions.webfdms.database.DatabasePeer {
    static public final int	   FILE_VERSION = 10;
    static public final String IDENTITY    	= "VitalsMasterKey";
    static public final String CASE_ID	 	= "VitalsMasterKey";
    static public final String FILEVERSION  = "FileVersion";
    static public final String OPENCLOSEBOX	= "OpenCloseBox";
    static public final String DAYOFWEEK 	= "DayOfWeek";
    static public final String TIMEOFSERVICE= "TimeOfService";
    static public final String PLACESERVICE	= "PlaceOfService";
    static public final String PLACESTREET	= "PlaceOfServStreet";
    static public final String PLACECITY	= "PlaceOfServCity";
    static public final String PLACESTATE	= "PlaceOfServState";
    static public final String PLACEPHONE = "PlaceOfServPhone"; 
    static public final String JEWELRY		= "JewelryInstructions";
    static public final String MINISTER1		= "Minister1";
    static public final String MINISTER1EMAIL		= "Minister1_Email";    
    static public final String MINISTER1ADDR	= "Minister1_addr";
    static public final String MINISTER1CITY	= "Minister1_citySt";
    static public final String MINISTER1SAL		= "Minister1_Salutation";
    static public final String MINISTER1PHONE	= "Minister1_Phone";
    static public final String MINISTER2		= "Minister2";
    static public final String MINISTER2EMAIL		= "Minister2_Email";     
    static public final String MINISTER2ADDR	= "Minister2_addr";
    static public final String MINISTER2CITY	="Minister2_citySt";
    static public final String MINISTER2SAL		="Minister2_Salutation";
    static public final String MINISTER2PHONE	="Minister2_Phone";
    static public final String CHURCH			="Church";
    static public final String CHURCHADDR		="ChurchStreet";
    static public final String CHURCHPHONE		="ChurchPhone";
    static public final String CHURCHCITY		="ChurchCityStZip";
    static public final String ORGANIST			="Organist";
    static public final String SOLOIST			="Soloist";
    static public final String SONGS			="Songs";
    static public final String CEMETERYNAME		="CemeteryName";
    static public final String CEMETERYADDR		="CemeteryStreet";
    static public final String CEMETERYCITY		="CemeteryCity";
    static public final String CEMETERYSTATE	="CemeteryState";
    static public final String CEMETERYZIP		="CemeteryZip";
    static public final String CEMETERYCOUNTY	="CemeteryCounty";
    static public final String CEMETERYSECTION	="CemeterySection";
    static public final String CEMETERYBLOCKNUMBER	="CemeteryBlockNumber";   
    static public final String CEMETERYLOT		="CemeteryLot";
    static public final String CEMETERYGRAVE	="CemeteryGrave";
    static public final String CEMETERYSPACE	="CemeterySpace";
    static public final String CEMETERYPHONE	="CemeteryPhone";
    static public final String CEMETERYTIME		="CemeteryTime";
    static public final String TENTEQUIPMENT	="TentEquipment";
    static public final String SETSEAL			="SetSeal";
    static public final String AUTOS			="Autos";
    static public final String PICKUPFAMILYAT	="PickUpFamily";
    static public final String PICKUPFAMTIME	="PickUpFamilyTime";
    static public final String CERTIFIEDCOUNT	="CertifiedCopies";
    static public final String CERTIFIEDSENDTO	="CertifiedsSendTo";
    static public final String VISITATION		="Visitation";
    static public final String SPECIALSERVICES	="SpecialService";
    static public final String RESTORATION		="Restoration";
    static public final String HAIRSTYLE		="HairStyle";
    static public final String DONATIONS		="Donations";
    static public final String CARDSTYLE		="CardStyle";
    static public final String CARDCOUNT		="CardCount";
    static public final String MEMORIALSTYLE	="MemorialStyle";
    static public final String MEMORIALCOUNT	="MemorialCount";
    static public final String FLOWERDESCR		="FlowerDescription";
    static public final String FLOWERSUPPLIER	="FlowerSupplier";
    static public final String FLOWERDELIVERY	="FlowerDelivery";
    static public final String FLOWERPICKUP		="FlowerPickup";
    static public final String SPECIALINSTR		="SpecialInstructions";
    static public final String CREMAINSDISP		="CremainsDisposition";
    static public final String DATEMODIFIED = "DateModified";
    static public final String TIMEMODIFIED = "TimeModified";
    static public final String VAULTMFGER	= "VaultManufacturer";
    static public final String VAULTNAME	= "VaultName";
    static public final String VAULTCOST	= "VaultCost";
    static public final String CASKETMFGER	= "CasketManufacturer";
    static public final String CASKETNAME	= "CasketName";
    static public final String CASKETCOST	= "CasketCost";
    static public final String MEMORIALID	= "memorialID";
    static public final String CREMATORYNAME = "CrematoryName";
    static public final String CREMATORYSTREET = "CrematoryStreet";
    static public final String CREMATORYCITY = "CrematoryCity";
    static public final String CREMATORYSTATE = "CrematoryState";
    static public final String CREMATORYZIP = "CrematoryZip";
    static public final String CREMATIONDATEOFSERVICE = "CremationDateOfService";
    static public final String ADDNLSERVICE = "AddnlService";
    static public final String ADDNLSERVICEDATE = "AddnlServiceDate";
    static public final String ADDNLSERVICETIME = "AddnlServiceTime";
    static public final String ADDNLSERVICEDAY = "AddnlServiceDay";
    static public final String ADDNLSERVICEPLACE  = "AddnlServicePlace";
    static public final String ADDNLSERVICESTREET = "AddnlServiceStreet";
    static public final String ADDNLSERVICECITY   = "AddnlServiceCity";
    static public final String ADDNLSERVICESTATE  = "AddnlServiceState";
    static public final String ADDNLSERVICEPHONE  = "AddnlServicePhone";
    static public final String CREMATORYCOUNTY = "CrematoryCounty";
    static public final String CEMETERYVAULT = "CemeteryVault";
    
    /**
     * getInsertSql method comment.
     */
    protected java.sql.PreparedStatement getInsertSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {
        java.sql.Connection connection = null;
        try {
            DbServices rec=(DbServices)p;
            connection = t.getConnection();
            java.sql.PreparedStatement pstmt = connection.prepareStatement(
                    "INSERT INTO servicedata ("+
                    FILEVERSION
                    +"," + DAYOFWEEK
                    +"," + TIMEOFSERVICE
                    +"," + PLACESERVICE
                    +"," + PLACESTREET
                    +"," + PLACECITY
                    +"," + JEWELRY
                    +"," + MINISTER1
                    +"," + MINISTER1ADDR
                    +"," + MINISTER1CITY
                    +"," + MINISTER1SAL
                    +"," + MINISTER1PHONE
                    +"," + MINISTER2
                    +"," + MINISTER2ADDR
                    +"," + MINISTER2CITY
                    +"," + MINISTER2SAL
                    +"," + MINISTER2PHONE
                    +"," + CHURCH
                    +"," + CHURCHADDR
                    +"," + CHURCHCITY
                    +"," + ORGANIST
                    +"," + SOLOIST
                    +"," + SONGS
                    +"," + CEMETERYNAME
                    +"," + CEMETERYADDR
                    +"," + CEMETERYCITY
                    +"," + CEMETERYSTATE
                    +"," + CEMETERYZIP
                    +"," + CEMETERYCOUNTY
                    +"," + CEMETERYSECTION
                    +"," + CEMETERYLOT
                    +"," + CEMETERYGRAVE
                    +"," + CEMETERYSPACE
                    +"," + CEMETERYPHONE
                    +"," + CEMETERYTIME
                    +"," + TENTEQUIPMENT
                    +"," + SETSEAL
                    +"," + OPENCLOSEBOX
                    +"," + AUTOS
                    +"," + PICKUPFAMILYAT
                    +"," + PICKUPFAMTIME
                    +"," + CERTIFIEDCOUNT
                    +"," + CERTIFIEDSENDTO
                    +"," + VISITATION
                    +"," + SPECIALSERVICES
                    +"," + RESTORATION
                    +"," + HAIRSTYLE
                    +"," + DONATIONS
                    +"," + CARDSTYLE
                    +"," + CARDCOUNT
                    +"," + MEMORIALSTYLE
                    +"," + MEMORIALCOUNT
                    +"," + FLOWERDESCR
                    +"," + FLOWERSUPPLIER
                    +"," + FLOWERDELIVERY
                    +"," + FLOWERPICKUP
                    +"," + SPECIALINSTR
                    +"," + CREMAINSDISP
                    +"," + VAULTMFGER
                    +"," + VAULTNAME
                    +"," + VAULTCOST
                    +"," + CASKETMFGER
                    +"," + CASKETNAME
                    +"," + CASKETCOST
                    +"," + MEMORIALID
                    +"," + CASE_ID
                    +"," + CREMATORYNAME
                    +"," + CREMATORYSTREET
                    +"," + CREMATORYCITY
                    +"," + CREMATORYSTATE
                    +"," + CREMATORYZIP
                    +"," + CREMATIONDATEOFSERVICE
					+"," + ADDNLSERVICE
					+"," + ADDNLSERVICEDATE
					+"," + ADDNLSERVICETIME
					+"," + ADDNLSERVICEDAY
					+"," + ADDNLSERVICEPLACE
					+"," + ADDNLSERVICESTREET
					+"," + ADDNLSERVICECITY
					+"," + ADDNLSERVICESTATE
					+"," + PLACESTATE
					+"," + CREMATORYCOUNTY
					+"," + CEMETERYVAULT
					+"," + MINISTER1EMAIL					
					+"," + MINISTER2EMAIL	
					+"," + CEMETERYBLOCKNUMBER
					+"," + PLACEPHONE
					+"," + ADDNLSERVICEPHONE
					+"," + CHURCHPHONE
                    +") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            pstmt.setShort(1, (short)FILE_VERSION);
            pstmt.setString(2, rec.getCSrvDayOfWeek());
            pstmt.setString(3, rec.getCSrvTime());
            pstmt.setString(4, rec.getCSrvPlace());
            pstmt.setString(5, rec.getCSrvPlaceStreet());
            pstmt.setString(6, rec.getCSrvPlaceCity());
            pstmt.setString(7, rec.getCSrvJewelryInst());
            pstmt.setString(8, rec.getCSrvMinister1());
            pstmt.setString(9, rec.getCSrvMinister1Addr());
            pstmt.setString(10,rec.getCSrvMinister1CitySt());
            pstmt.setString(11,rec.getCSrvMinister1Sal());
            pstmt.setString(12,rec.getCSrvMinister1Phone());
            pstmt.setString(13, rec.getCSrvMinister2() );
            pstmt.setString(14, rec.getCSrvMinister2Addr() );
            pstmt.setString(15, rec.getCSrvMinister2CitySt() );
            pstmt.setString(16, rec.getCSrvMinister2Sal() );
            pstmt.setString(17, rec.getCSrvMinister2Phone() );
            pstmt.setString(18, rec.getCSrvChurch() );
            pstmt.setString(19, rec.getCSrvChurchStreet() );
            pstmt.setString(20, rec.getCSrvChurchCitySt() );
            pstmt.setString(21, rec.getCSrvOrganist() );
            pstmt.setString(22, rec.getCSrvSoloist() );
            pstmt.setString(23, rec.getCSrvSongs1() );
            pstmt.setString(24, rec.getCSrvCem() );
            pstmt.setString(25, rec.getCSrvCemStreet() );
            pstmt.setString(26, rec.getCSrvCemCity() );
            pstmt.setString(27, rec.getCSrvCemState() );
            pstmt.setString(28, rec.getCSrvCemZip() );
            pstmt.setString(29, rec.getCSrvCemCounty() );
            pstmt.setString(30, rec.getCSrvCemSection() );
            pstmt.setString(31, rec.getCSrvCemLot() );
            pstmt.setString(32, rec.getCSrvCemGrave() );
            pstmt.setString(33, rec.getCSrvCemSpace() );
            pstmt.setString(34, rec.getCSrvCemPhone() );
            pstmt.setString(35, rec.getCSrvCemTime() );
            pstmt.setString(36, rec.getCSrvTentEquipment() );
            pstmt.setString(37, rec.getCSrvSetSeal()) ;
            pstmt.setString(38, rec.getCSrvOpenClose()) ;
            pstmt.setString(39, rec.getCSrvAutos() );
            pstmt.setString(40, rec.getCSrvPickUpFamily() );
            pstmt.setString(41, rec.getCSrvPickUpTime() );
            pstmt.setShort(42, rec.getISrvCertifiedCopies() );
            pstmt.setString(43, rec.getCSrvCCSendTo() );
            pstmt.setString(44, rec.getCSrvVisitation() );
            pstmt.setString(45, rec.getCSrvSpecialService() );
            pstmt.setString(46, rec.getCSrvRestoration() );
            pstmt.setString(47, rec.getCSrvHairStyle() );
            pstmt.setString(48, rec.getCSrvDonations() );
            pstmt.setString(49, rec.getCSrvCardStyle() );
            pstmt.setShort(50, rec.getISrvCardCount() );
            pstmt.setString(51, rec.getCSrvMemorialStyle() );
            pstmt.setShort(52, rec.getISrvMemorialCount() );
            pstmt.setString(53, rec.getCFlowerDescr() );
            pstmt.setString(54, rec.getCFlowerSupplier() );
            pstmt.setString(55, rec.getCDeliveryBox());
            pstmt.setString(56, rec.getCPickupBox() );
            pstmt.setString(57, rec.getCSrvSpecialInstructions() );
            pstmt.setString(58, rec.getCSrvCremainsDisp() );
            pstmt.setString(59, rec.getCSrvVaultMfgr());
            pstmt.setString(60, rec.getCSrvVaultName());
            pstmt.setInt(61, rec.getLSrvVaultCost());
            pstmt.setString(62, rec.getCSrvCasketMfgr());
            pstmt.setString(63, rec.getCSrvCasketName());
            pstmt.setInt(64, rec.getLSrvCasketCost());
            pstmt.setInt(65, rec.getMemorialID());
            pstmt.setInt   (66, rec.getLSrvMainKey());
            pstmt.setString(67, rec.getCrematoryName());
            pstmt.setString(68, rec.getCrematoryStreet());
            pstmt.setString(69, rec.getCrematoryCity());
            pstmt.setString(70, rec.getCrematoryState());
            pstmt.setString(71, rec.getCrematoryZip());
            pstmt.setString(72, rec.getCremationDateOfService());
            pstmt.setString(73, rec.getAddnlService());
            pstmt.setString(74, rec.getAddnlServiceDate());
            pstmt.setString(75, rec.getAddnlServiceTime());
            pstmt.setString(76, rec.getAddnlServiceDay());
            pstmt.setString(77, rec.getAddnlServicePlace());
            pstmt.setString(78, rec.getAddnlServiceStreet());
            pstmt.setString(79, rec.getAddnlServiceCity());
            pstmt.setString(80, rec.getAddnlServiceState());
            pstmt.setString(81, rec.getCSrvPlaceState());
            pstmt.setString(82, rec.getCrematoryCounty());
            pstmt.setString(83, rec.getCemeteryVault());
            pstmt.setString(84, rec.getCSrvMinister1Email());
            pstmt.setString(85, rec.getCSrvMinister2Email()); 
            pstmt.setString(86, rec.getCSrvCemBlockNumber() );
            pstmt.setString(87, rec.getCSrvPlacePhone() );
            pstmt.setString(88, rec.getAddnlServicePhone() );
            pstmt.setString(89, rec.getCSrvChurchPhone() );
            return pstmt;
        } catch (java.sql.SQLException e){
            throw new com.aldorsolutions.webfdms.database.PersistenceException("DbServicesPeer.Insert:"+e.getMessage(),e);
        }
    }
    /**
     * getRemoveSql method comment.
     */
    protected java.sql.PreparedStatement getRemoveSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {
        java.sql.PreparedStatement pstmt=null;
        java.sql.Connection connection = null;
        try {
            connection = t.getConnection();
            pstmt = connection.prepareStatement("DELETE FROM servicedata WHERE VitalsMasterKey=?");
            pstmt.setInt(1,p.getId());
            return pstmt;
        } catch (java.sql.SQLException e){
            throw new com.aldorsolutions.webfdms.database.PersistenceException("DbServicesPeer.Remove",e);
        }
    }
    /**
     * getRestoreSql method comment.
     */
    protected String getRestoreSql(com.aldorsolutions.webfdms.database.Persistent p) {
    	return  "SELECT"
            +" " + CASE_ID
            +"," + FILEVERSION
            +"," + DAYOFWEEK
            +"," + TIMEOFSERVICE
            +"," + PLACESERVICE
            +"," + PLACESTREET
            +"," + PLACECITY
            +"," + JEWELRY
            +"," + MINISTER1
            +"," + MINISTER1ADDR
            +"," + MINISTER1CITY
            +"," + MINISTER1SAL
            +"," + MINISTER1PHONE
            +"," + MINISTER2
            +"," + MINISTER2ADDR
            +"," + MINISTER2CITY
            +"," + MINISTER2SAL
            +"," + MINISTER2PHONE
            +"," + CHURCH
            +"," + CHURCHADDR
            +"," + CHURCHCITY
            +"," + ORGANIST
            +"," + SOLOIST
            +"," + SONGS
            +"," + CEMETERYNAME
            +"," + CEMETERYADDR
            +"," + CEMETERYCITY
            +"," + CEMETERYSTATE
            +"," + CEMETERYZIP
            +"," + CEMETERYCOUNTY
            +"," + CEMETERYSECTION
            +"," + CEMETERYLOT
            +"," + CEMETERYGRAVE
            +"," + CEMETERYSPACE
            +"," + CEMETERYPHONE
            +"," + CEMETERYTIME
            +"," + TENTEQUIPMENT
            +"," + SETSEAL
            +"," + OPENCLOSEBOX
            +"," + AUTOS
            +"," + PICKUPFAMILYAT
            +"," + PICKUPFAMTIME
            +"," + CERTIFIEDCOUNT
            +"," + CERTIFIEDSENDTO
            +"," + VISITATION
            +"," + SPECIALSERVICES
            +"," + RESTORATION
            +"," + HAIRSTYLE
            +"," + DONATIONS
            +"," + CARDSTYLE
            +"," + CARDCOUNT
            +"," + MEMORIALSTYLE
            +"," + MEMORIALCOUNT
            +"," + FLOWERDESCR
            +"," + FLOWERSUPPLIER
            +"," + FLOWERDELIVERY
            +"," + FLOWERPICKUP
            +"," + SPECIALINSTR
            +"," + CREMAINSDISP
            +"," + VAULTMFGER
            +"," + VAULTNAME
            +"," + VAULTCOST
            +"," + CASKETMFGER
            +"," + CASKETNAME
            +"," + CASKETCOST
            +"," + MEMORIALID
            +"," + CREMATORYNAME
            +"," + CREMATORYSTREET
            +"," + CREMATORYCITY
            +"," + CREMATORYSTATE
            +"," + CREMATORYZIP
            +"," + CREMATIONDATEOFSERVICE
			+"," + ADDNLSERVICE
			+"," + ADDNLSERVICEDATE
			+"," + ADDNLSERVICETIME
			+"," + ADDNLSERVICEDAY
			+"," + ADDNLSERVICEPLACE
			+"," + ADDNLSERVICESTREET
			+"," + ADDNLSERVICECITY
			+"," + ADDNLSERVICESTATE
			+"," + PLACESTATE
			+"," + CREMATORYCOUNTY
			+"," + CEMETERYVAULT
			+"," + MINISTER1EMAIL	
			+"," + MINISTER2EMAIL
			+"," + CEMETERYBLOCKNUMBER
			+"," + PLACEPHONE
			+"," + ADDNLSERVICEPHONE
			+"," + CHURCHPHONE
            +" from servicedata WHERE VitalsMasterKey = "
            + p.getId();
    	
                
    }
    /**
     * getUpdateSql method comment.
     */
    protected java.sql.PreparedStatement getUpdateSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {
        java.sql.Connection connection = null;
        try {
            DbServices rec=(DbServices)p;
            connection = t.getConnection();
            java.sql.PreparedStatement pstmt = connection.prepareStatement(
                    "UPDATE servicedata SET "+
                    FILEVERSION + "=?"
                    +"," + DAYOFWEEK + "=?"
                    +"," + TIMEOFSERVICE + "=?"
                    +"," + PLACESERVICE + "=?"
                    +"," + PLACESTREET + "=?"
                    +"," + PLACECITY + "=?"
                    +"," + JEWELRY + "=?"
                    +"," + MINISTER1 + "=?"
                    +"," + MINISTER1ADDR + "=?"
                    +"," + MINISTER1CITY + "=?"
                    +"," + MINISTER1SAL + "=?"
                    +"," + MINISTER1PHONE + "=?"
                    +"," + MINISTER2 + "=?"
                    +"," + MINISTER2ADDR + "=?"
                    +"," + MINISTER2CITY + "=?"
                    +"," + MINISTER2SAL + "=?"
                    +"," + MINISTER2PHONE + "=?"
                    +"," + CHURCH + "=?"
                    +"," + CHURCHADDR + "=?"
                    +"," + CHURCHCITY + "=?"
                    +"," + ORGANIST + "=?"
                    +"," + SOLOIST + "=?"
                    +"," + SONGS + "=?"
                    +"," + CEMETERYNAME + "=?"
                    +"," + CEMETERYADDR + "=?"
                    +"," + CEMETERYCITY + "=?"
                    +"," + CEMETERYSTATE + "=?"
                    +"," + CEMETERYZIP + "=?"
                    +"," + CEMETERYCOUNTY + "=?"
                    +"," + CEMETERYSECTION + "=?"
                    +"," + CEMETERYLOT + "=?"
                    +"," + CEMETERYGRAVE + "=?"
                    +"," + CEMETERYSPACE + "=?"
                    +"," + CEMETERYPHONE + "=?"
                    +"," + CEMETERYTIME + "=?"
                    +"," + TENTEQUIPMENT + "=?"
                    +"," + SETSEAL + "=?"
                    +"," + OPENCLOSEBOX + "=?"
                    +"," + AUTOS + "=?"
                    +"," + PICKUPFAMILYAT + "=?"
                    +"," + PICKUPFAMTIME + "=?"
                    +"," + CERTIFIEDCOUNT + "=?"
                    +"," + CERTIFIEDSENDTO + "=?"
                    +"," + VISITATION + "=?"
                    +"," + SPECIALSERVICES + "=?"
                    +"," + RESTORATION + "=?"
                    +"," + HAIRSTYLE + "=?"
                    +"," + DONATIONS + "=?"
                    +"," + CARDSTYLE + "=?"
                    +"," + CARDCOUNT + "=?"
                    +"," + MEMORIALSTYLE + "=?"
                    +"," + MEMORIALCOUNT + "=?"
                    +"," + FLOWERDESCR + "=?"
                    +"," + FLOWERSUPPLIER + "=?"
                    +"," + FLOWERDELIVERY + "=?"
                    +"," + FLOWERPICKUP + "=?"
                    +"," + SPECIALINSTR + "=?"
                    +"," + CREMAINSDISP + "=?"
                    +"," + VAULTMFGER + "=?"
                    +"," + VAULTNAME + "=?"
                    +"," + VAULTCOST + "=?"
                    +"," + CASKETMFGER + "=?"
                    +"," + CASKETNAME + "=?"
                    +"," + CASKETCOST + "=?"
                    +"," + MEMORIALID + "=?"
                    +"," + CREMATORYNAME + "=?"
                    +"," + CREMATORYSTREET + "=?"
                    +"," + CREMATORYCITY + "=?"
                    +"," + CREMATORYSTATE + "=?"
                    +"," + CREMATORYZIP + "=?"
                    +"," + CREMATIONDATEOFSERVICE + "=?"
					+"," + ADDNLSERVICE + "=?"
					+"," + ADDNLSERVICEDATE + "=?"
					+"," + ADDNLSERVICETIME + "=?"
					+"," + ADDNLSERVICEDAY + "=?"
					+"," + ADDNLSERVICEPLACE + "=?"
					+"," + ADDNLSERVICESTREET + "=?"
					+"," + ADDNLSERVICECITY + "=?"
					+"," + ADDNLSERVICESTATE + "=?"
					+"," + PLACESTATE + "=?"
					+"," + CREMATORYCOUNTY + "=?"
					+"," + CEMETERYVAULT + "=?"
					+"," + MINISTER1EMAIL + "=?"					
					+"," + MINISTER2EMAIL + "=?"
					+"," + CEMETERYBLOCKNUMBER + "=?"
					+"," + PLACEPHONE + "=?"
					+"," + ADDNLSERVICEPHONE + "=?"
					+"," + CHURCHPHONE + "=?"
                    + " WHERE VitalsMasterKey = ?");
            pstmt.setShort(1, (short)FILE_VERSION);
            pstmt.setString(2, rec.getCSrvDayOfWeek());
            pstmt.setString(3, rec.getCSrvTime());
            pstmt.setString(4, rec.getCSrvPlace());
            pstmt.setString(5, rec.getCSrvPlaceStreet());
            pstmt.setString(6, rec.getCSrvPlaceCity());
            pstmt.setString(7, rec.getCSrvJewelryInst());
            pstmt.setString(8, rec.getCSrvMinister1());
            pstmt.setString(9, rec.getCSrvMinister1Addr());
            pstmt.setString(10,rec.getCSrvMinister1CitySt());
            pstmt.setString(11,rec.getCSrvMinister1Sal());
            pstmt.setString(12,rec.getCSrvMinister1Phone());
            pstmt.setString(13, rec.getCSrvMinister2() );
            pstmt.setString(14, rec.getCSrvMinister2Addr() );
            pstmt.setString(15, rec.getCSrvMinister2CitySt() );
            pstmt.setString(16, rec.getCSrvMinister2Sal() );
            pstmt.setString(17, rec.getCSrvMinister2Phone() );
            pstmt.setString(18, rec.getCSrvChurch() );
            pstmt.setString(19, rec.getCSrvChurchStreet() );
            pstmt.setString(20, rec.getCSrvChurchCitySt() );
            pstmt.setString(21, rec.getCSrvOrganist() );
            pstmt.setString(22, rec.getCSrvSoloist() );
            pstmt.setString(23, rec.getCSrvSongs1() );
            pstmt.setString(24, rec.getCSrvCem() );
            pstmt.setString(25, rec.getCSrvCemStreet() );
            pstmt.setString(26, rec.getCSrvCemCity() );
            pstmt.setString(27, rec.getCSrvCemState() );
            pstmt.setString(28, rec.getCSrvCemZip() );
            pstmt.setString(29, rec.getCSrvCemCounty() );
            pstmt.setString(30, rec.getCSrvCemSection() );
            pstmt.setString(31, rec.getCSrvCemLot() );
            pstmt.setString(32, rec.getCSrvCemGrave() );
            pstmt.setString(33, rec.getCSrvCemSpace() );
            pstmt.setString(34, rec.getCSrvCemPhone() );
            pstmt.setString(35, rec.getCSrvCemTime() );
            pstmt.setString(36, rec.getCSrvTentEquipment());
            pstmt.setString(37, rec.getCSrvSetSeal() );
            pstmt.setString(38, rec.getCSrvOpenClose() );
            pstmt.setString(39, rec.getCSrvAutos() );
            pstmt.setString(40, rec.getCSrvPickUpFamily() );
            pstmt.setString(41, rec.getCSrvPickUpTime() );
            pstmt.setShort(42, rec.getISrvCertifiedCopies() );
            pstmt.setString(43, rec.getCSrvCCSendTo() );
            pstmt.setString(44, rec.getCSrvVisitation() );
            pstmt.setString(45, rec.getCSrvSpecialService() );
            pstmt.setString(46, rec.getCSrvRestoration() );
            pstmt.setString(47, rec.getCSrvHairStyle() );
            pstmt.setString(48, rec.getCSrvDonations() );
            pstmt.setString(49, rec.getCSrvCardStyle() );
            pstmt.setShort(50, rec.getISrvCardCount() );
            pstmt.setString(51, rec.getCSrvMemorialStyle() );
            pstmt.setShort(52, rec.getISrvMemorialCount() );
            pstmt.setString(53, rec.getCFlowerDescr() );
            pstmt.setString(54, rec.getCFlowerSupplier() );
            pstmt.setString(55, rec.getCDeliveryBox());
            pstmt.setString(56, rec.getCPickupBox());
            pstmt.setString(57, rec.getCSrvSpecialInstructions() );
            pstmt.setString(58, rec.getCSrvCremainsDisp() );
            pstmt.setString(59, rec.getCSrvVaultMfgr());
            pstmt.setString(60, rec.getCSrvVaultName());
            pstmt.setInt(61, rec.getLSrvVaultCost());
            pstmt.setString(62, rec.getCSrvCasketMfgr());
            pstmt.setString(63, rec.getCSrvCasketName());
            pstmt.setInt(64, rec.getLSrvCasketCost());
            pstmt.setInt(65, rec.getMemorialID());            
            pstmt.setString(66, rec.getCrematoryName());
            pstmt.setString(67, rec.getCrematoryStreet());
            pstmt.setString(68, rec.getCrematoryCity());
            pstmt.setString(69, rec.getCrematoryState());
            pstmt.setString(70, rec.getCrematoryZip());
            pstmt.setString(71, rec.getCremationDateOfService());
            pstmt.setString(72, rec.getAddnlService());
            pstmt.setString(73, rec.getAddnlServiceDate());
            pstmt.setString(74, rec.getAddnlServiceTime());
            pstmt.setString(75, rec.getAddnlServiceDay());
            pstmt.setString(76, rec.getAddnlServicePlace());
            pstmt.setString(77, rec.getAddnlServiceStreet());
            pstmt.setString(78, rec.getAddnlServiceCity());
            pstmt.setString(79, rec.getAddnlServiceState());
            pstmt.setString(80, rec.getCSrvPlaceState());
            pstmt.setString(81, rec.getCrematoryCounty());
            pstmt.setString(82, rec.getCemeteryVault());
            pstmt.setString(83, rec.getCSrvMinister1Email());
            pstmt.setString(84, rec.getCSrvMinister2Email());  
            pstmt.setString(85, rec.getCSrvCemBlockNumber() );
            pstmt.setString(86, rec.getCSrvPlacePhone());
            pstmt.setString(87, rec.getAddnlServicePhone());
            pstmt.setString(88, rec.getCSrvChurchPhone());
            pstmt.setInt(89,rec.getId());

            return pstmt;
        } catch (java.sql.SQLException e){
            throw new com.aldorsolutions.webfdms.database.PersistenceException("DbServicesPeer.Update:"+e.getMessage(),e);
        }
    }
}