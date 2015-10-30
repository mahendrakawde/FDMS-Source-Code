package com.aldorsolutions.webfdms.beans;

/**
 * DbCaseSetPeer provides the runtime class name for the DbCase objects
 * it is responsible for as well as the SQL for restoring them
 * Creation date: (11/1/2001 11:16:20 AM)
 * @author:
 */
public class DbCaseSetPeer extends com.aldorsolutions.webfdms.database.DatabaseSetPeer {
    
    /**
     * At this time, there is only one Case class to be restored.
     * Later, we may have an at-need and pre-need class and this method
     * would determine which to return.
     */
    public String getPersistentClass(java.util.Hashtable h) {
        return "com.aldorsolutions.webfdms.beans.DbCase";
    }
    
    /**
     * Provides the proper SQL SELECT statement based on the values
     * that come from a database row stored in the Hashtable.  If the
     * Hashtable is null, then all Cases are retrieved.
     * If a LOCATION-REGION is passed, then all accounts for that REGION
     * are provided.
     * @param h a Hashtable containing the selection criteria
     * @return the SELECT SQL to get the cases from the database with
     */
    public String getSql(java.util.Hashtable h) {

        try {
            return myGetSql(h);
        } catch (Exception e) {
            
            if( h == null ) { // If no query criteria are specified
                return
                        "SELECT   VitalsMasterKey,"+
                        "CaseCode,"+
                        "ContractCode,"+
                        "DeceasedFirstName,"+
                        "DeceasedLastName,"+
                        "VoidedCode,"+
                        "VoidedFromContract,"+
                        "VoidedToContract,"+
                        "SaleDateKey,"+
                        "DeathDateKey,"+
                        "ServiceDateKey,"+
                        //            "Place of service,"+
                        //            "Total Paid To-Date,"+
                        //            "Total Charges,"+
                        "LocaleNumber,"+
                        "ChapelNumber,"+
                        "ChapelLocation,"+
                        "PNPreneedStatus, "+
                        "Active, "+
                        "VoidedDate " +
                        "from vitalstats "+
						"where vitalstats.Active != 0";
                //            "WHERE vitalstats.VitalsMasterKey = ServiceData.VitalsMasterKey "+
                //            "AND  vitalstats.VitalsMasterKey = FinancialData.VitalsMasterKey ";
             } else {
                String tmp =
                        "SELECT vitalstats.VitalsMasterKey,"+
                        "CaseCode,"+
                        "ContractCode,"+
                        "DeceasedFirstName,"+
                        "DeceasedLastName,"+
                        "VoidedCode,"+
                        "VoidedFromContract,"+
                        "VoidedToContract,"+
                        "SaleDateKey,"+
                        "DeathDateKey,"+
                        "ServiceDateKey,"+
                        //            "Place of service,"+
                        //            "Total Paid To-Date,"+
                        //            "Total Charges,"+
                        "LocaleNumber,"+
                        "ChapelNumber,"+
                        "ChapelLocation,"+
                        "PNPreneedStatus, "+
                        "Active, "+
                        "VoidedDate " +
                        "from vitalstats "+
						"where vitalstats.Active != 0";
                
                if (h.containsKey(DbCasePeer.CHAPELNUMBER)) {
                    tmp+= " and vitalstats." + DbCasePeer.CHAPELNUMBER
                            + " = " + h.get(DbCasePeer.CHAPELNUMBER).toString();							
                }
    /*
    if( h.containsKey(DbCasePeer.LOCALE) ) {
      tmp+= " WHERE vitalstats.LocaleNumber = "+h.get(DbCasePeer.LOCALE).toString();
    }
     */
                
                if( h.containsKey(DbCasePeer.CASECODE) ) {
                    tmp+= " ORDER BY "+DbCasePeer.CASECODE;
                }
                if( h.containsKey(DbCasePeer.CONTRACTCODE) ) {
                    tmp+= " ORDER BY "+DbCasePeer.CONTRACTCODE;
                }
                if( h.containsKey(DbCasePeer.CASE_ID) ) {
                    tmp+= " ORDER BY "+DbCasePeer.CASE_ID;
                }
                if( h.containsKey(DbCasePeer.LAST_NAME) ) {
                    tmp+= " ORDER BY "+DbCasePeer.LAST_NAME;
                }
                if( h.containsKey(DbCasePeer.SALEDATE) ) {
                    tmp+= " ORDER BY "+DbCasePeer.SALEDATE+" DESC";
                }
                if( h.containsKey(DbCasePeer.DEATHDATE) ) {
                    tmp+= " ORDER BY "+DbCasePeer.DEATHDATE+" DESC";
                }
                
                tmp += " LIMIT 50";
                
                //AppLog.trace("DbCaseSetPeer params: "+h);
                //AppLog.trace("DbCaseSetPeer SQL: "+tmp);
                return tmp ;
                
            }
            
        } //catch
    }
    /**
     * Provides the proper SQL SELECT statement based on the values
     * that come from a database row stored in the Hashtable.  If the
     * Hashtable is null, then all Cases are retrieved.
     * If a LOCATION-REGION is passed, then all accounts for that REGION
     * are provided.
     * @param h a Hashtable containing the selection criteria
     * @return the SELECT SQL to get the cases from the database with
     */
    public String myGetSql(java.util.Hashtable h) {
        
        //AppLog.trace("Running DbCaseSetPeer.myGetSql.");
        
        String colList = new String("SELECT " +"VitalsMasterKey, " +"CaseCode," +"ContractCode," +
                "DeceasedFirstName," +"DeceasedLastName," +"VoidedCode," +
                "VoidedFromContract," +"VoidedToContract," +"SaleDateKey," +
                "DeathDateKey," +"ServiceDateKey," +"LocaleNumber," +"ChapelNumber," +
                "ChapelLocation," +"PNPreneedStatus,"+"Active, "+"VoidedDate ");
        String tableStmt = new String(" FROM vitalstats");
        String whereClause = new String(" WHERE vitalstats.Active != 0"); // active cases should be 1 but test that they are not 0 to account for possible null values
        String orderByClause = "";
        String orderByExpr = null;
        String queryOrder = "";
        String keyValue = "";
        String keyVitalsId = "";
        boolean keyInclusive;
        
        // If no query criteria are specified, just return the default.
        if (h == null) {
            //AppLog.trace("Hashtable is null.");
            return colList +" " +tableStmt +" " +whereClause +" " +orderByClause;
        }
        
        // Addition filters
  /*
  if (h.containsKey(DbCasePeer.LOCALE)) {
     whereClause = whereClause + " and vitalstats.LocaleNumber = " +h.get(DbCasePeer.LOCALE).toString();
  }
   */
        
        if (h.containsKey(DbCasePeer.CHAPELNUMBER)) {
            whereClause += " AND vitalstats." + DbCasePeer.CHAPELNUMBER
                    + " = " + h.get(DbCasePeer.CHAPELNUMBER).toString();
        }
        
        // Get sort order
        if (h.containsKey("sortOrderId")) {
            //AppLog.trace("orderby found in hashtable.");
            orderByExpr = h.get("sortOrderId").toString();
            if (orderByExpr.equals("DeceasedFullName"))
                orderByExpr = "concat(DeceasedLastName, ', ', DeceasedFirstName)";
            
            orderByClause = " ORDER BY " +orderByExpr;
        } else {
            //AppLog.trace("NO orderby found in hashtable.");
        }
        
        // Get the direction
        if (h.containsKey("queryOrder")) {
            //AppLog.trace("direction found in hashtable.");
            queryOrder = h.get("queryOrder").toString();
        } else {
            queryOrder = "";
            //AppLog.trace("NO direction found in hashtable.");
        }
        
        if ( orderByClause != null && orderByClause.length() > 0 ) {
        	orderByClause = orderByClause + " " + queryOrder + ", VitalsMasterKey " + queryOrder;
        }
        
        if (h.containsKey("keyVitalsId"))
            keyVitalsId = h.get("keyVitalsId").toString();
        
        if (h.containsKey("keyInclusive"))
            keyInclusive = ((Boolean)h.get("keyInclusive")).booleanValue();
        else
            keyInclusive = true;
        
        if (h.containsKey("keyValue")) {
            keyValue = h.get("keyValue").toString();
            
            if (queryOrder.equals("asc") && keyInclusive)
                whereClause += " AND " + orderByExpr + " >= '" + keyValue + "'";
            else if (queryOrder.equals("asc"))
                whereClause += " AND (" + orderByExpr + " > '" + keyValue + "' OR (" + orderByExpr + " = '" + keyValue + "' AND VitalsMasterKey > " + keyVitalsId + "))";
            else if (queryOrder.equals("desc") && keyInclusive)
                whereClause += " AND " + orderByExpr + " <= '" + keyValue + "'";
            else if (queryOrder.equals("desc"))
                whereClause += " AND (" + orderByExpr + " < '" + keyValue + "' OR (" + orderByExpr + " = '" + keyValue + "' AND VitalsMasterKey < " + keyVitalsId + "))";
        } else {
            //AppLog.trace("NO keyValue found in hashtable.");
        }
        
        if ((!h.containsKey("sortOrderId")) && (!h.containsKey("queryOrder")) && (!h.containsKey("keyValue"))) {
            if (h.containsKey(DbCasePeer.CONTRACTCODE))
                whereClause = whereClause +" and " +DbCasePeer.CONTRACTCODE + " = '" +h.get(DbCasePeer.CONTRACTCODE) +"'";
            if (h.containsKey(DbCasePeer.CASECODE))
                whereClause = whereClause +" and " +DbCasePeer.CASECODE + " = '" +h.get(DbCasePeer.CASECODE) +"'";
        }
        
        String sql = colList + " " + tableStmt +" " + whereClause +" " + orderByClause + " LIMIT 50";
        
        return sql;
        
    }
}
