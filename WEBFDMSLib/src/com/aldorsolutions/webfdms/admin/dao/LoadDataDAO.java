package com.aldorsolutions.webfdms.admin.dao;

import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.beans.LoadDataDTO;
import com.aldorsolutions.webfdms.util.DAO;

public class LoadDataDAO extends DAO {
	
	private Logger logger = Logger.getLogger(LoadDataDAO.class.getName());

	private String dbLookup =  null;
	public LoadDataDAO(String dbLookup) {
		super();
		this.dbLookup = dbLookup;
	}
    /**
     * Loads data into table
     * @param loadDataDTO data details
     * @return Numbers of newly created reords.
     */
	public long loadDataFile(LoadDataDTO loadDataDTO) {
		
		selectDataBase(loadDataDTO.getDataBaseName(), dbLookup);
		long rows = -1;
		String[] cols = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("LOAD DATA ");
			sql.append(loadDataDTO.getPriority());
			sql.append(" ");
			if(loadDataDTO.isLocal()){
				sql.append(LoadDataDTO.LOCAL);
			}
			sql.append(" INFILE '");
			sql.append(loadDataDTO.getFileName());
			sql.append("'");
			if(loadDataDTO.isReplace())
				sql.append(" " + LoadDataDTO.REPLACE);
			else
				sql.append(" " + LoadDataDTO.IGNORE);
			sql.append(" INTO TABLE `"+loadDataDTO.getTableName());
			
		//	sql.append("` CHARACTER SET utf8 ");
			sql.append("` FIELDS ");
			sql.append(" TERMINATED BY '" + loadDataDTO.getFiledTerminator());
			sql.append("' ENCLOSED BY '" + loadDataDTO.getFieldEnclouser());
			sql.append("' ESCAPED BY '" + loadDataDTO.getEscapeChar());
			sql.append("' LINES ");
			if(loadDataDTO.getLineStartingby() != null)
				sql.append(" STARTING BY" + loadDataDTO.getLineStartingby());
				
			sql.append(" TERMINATED BY '" + loadDataDTO.getLineTerminatedBy()+"' ");
			
			if(loadDataDTO.getColList().length > 0){
				cols = loadDataDTO.getColList();
				for(int i=0; i< cols.length; i++){
					if(i==0){
						sql.append(" (");
						sql.append(cols[i]);
					}else{
						sql.append(", "+cols[i]);
					}
					if(i==cols.length -1){
						sql.append(") ");
					}
				}
			}
			sql.append(";");
			logger.debug(sql.toString());

			makeConnection(dbLookup);
			Statement statement = conn.createStatement();
			
			rows = statement.executeUpdate(sql.toString());

			if (rows > 0) {
				insertAudit(sql.toString());
				logger.debug("Successfully created Rows :"+rows+" in Table :"+loadDataDTO.getTableName()+" Database Name :" + loadDataDTO.getDataBaseName());
			}else{
				logger.debug("No Records entered in database :" + loadDataDTO.getDataBaseName());
			}

		} catch (SQLException e) {
			logger.error("SQLException in loadDataFile() : ", e);
		} catch (Exception e) {
			logger.error("Exception in loadDataFile() : ", e);
		} finally {
			closeConnection();
		}
		
		return rows;
	}

}
