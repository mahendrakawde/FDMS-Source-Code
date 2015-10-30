package com.aldorsolutions.webfdms.admin.user.dao;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.aldorsolutions.fdms.to.common.Options;
import com.aldorsolutions.fdms.to.common.VitalsIdInfos;
import com.aldorsolutions.fdms.to.vitals.VitalsIdInfo;
import com.aldorsolutions.webfdms.admin.user.model.UserDTO;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.util.DAO;
public class GeneralQueryDAO extends DAO {

	private Logger logger = Logger.getLogger(GeneralQueryDAO.class.getName());
	
	public GeneralQueryDAO( ) {
    	super();
    }
	
	public Options getComboServiceType(DbUserSession user) {
        Options options = new Options();
        
        String jndiLookup = user.getDbLookup();
        logger.debug("Entering getComboServiceType()");
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT name, value ");
            sql.append("FROM combodata ");
            sql.append("WHERE  type = 2");
            
            
            makeConnection(jndiLookup);
            statement = conn.prepareStatement(sql.toString());
            
            rs = statement.executeQuery();
           
            while (rs.next()) {  
            	int col = 0;
            	String name = rs.getString(++col);
                int code = rs.getInt(++col);
                options.addIntOption(name, code);
            }
            
        } catch (SQLException e) {
            logger.error("SQL Exception in getComboServiceType() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getComboServiceType() : ", e);
        } finally {
            closeConnection();
        }
        
        return options;
    }

	public VitalsIdInfos getVitalsIdInfo(DbUserSession user,
			VitalsIdInfo vitalsIdInfo) {
		String jndiLookup = user.getDbLookup();
        logger.debug("Entering getVitalsIdInfo()");
        VitalsIdInfos vitalsIdInfos = new VitalsIdInfos();
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT DeceasedFirstName,DeceasedMidName,DeceasedLastName,VitalsMasterKey,");
            sql.append("ContractCode,CaseCode,LocaleNumber,ChapelNumber FROM vitalstats");
            if (vitalsIdInfo.getContractCode().length()> 0 && vitalsIdInfo.getLocationId() > 0) {
                 sql.append(" WHERE  ContractCode = '" +vitalsIdInfo.getContractCode()+"' and ChapelNumber =" + vitalsIdInfo.getLocationId());
            	
            }else if (vitalsIdInfo.getCaseCode().length()> 0 && vitalsIdInfo.getLocationId() > 0) {
            	sql.append(" WHERE  CaseCode = '" +vitalsIdInfo.getCaseCode()+"' and ChapelNumber =" + vitalsIdInfo.getLocationId());
            }else if (vitalsIdInfo.getLastName().length()> 0 && vitalsIdInfo.getLocationId() > 0) {
            	sql.append(" WHERE   DeceasedLastName like '%" +vitalsIdInfo.getLastName()+"%' and ChapelNumber =" + vitalsIdInfo.getLocationId());
            }
            
            makeConnection(jndiLookup);
            statement = conn.prepareStatement(sql.toString());
            
            rs = statement.executeQuery();
           
            while (rs.next()) {  
            	int col = 0;
            	VitalsIdInfo info = new VitalsIdInfo();
            	info.setFirstName(rs.getString(++col));
            	info.setMiddleName(rs.getString(++col));
            	info.setLastName(rs.getString(++col));
            	info.setVitalsMasterKey(rs.getInt(++col));
            	info.setContractCode(rs.getString(++col));
            	info.setCaseCode(rs.getString(++col));
            	info.setLocaleId(rs.getInt(++col));
            	info.setLocationId(rs.getInt(++col));
            	vitalsIdInfos.addInfos(info);
                
            }
            
        } catch (SQLException e) {
            logger.error("SQL Exception in getVitalsIdInfo() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getVitalsIdInfo() : ", e);
        } finally {
            closeConnection();
        }
		return vitalsIdInfos;
	}
	
}
