package com.aldorsolutions.webfdms.arrangers.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aldorsolutions.webfdms.arrangers.model.ArrangersFieldDTO;
import com.aldorsolutions.webfdms.util.DAO;

public class ArrangersFieldDAO extends DAO {

	public ArrayList<ArrangersFieldDTO> getArrangersFieldByASCOrder(String dbLookup) throws SQLException {
		ArrayList<ArrangersFieldDTO> list = new ArrayList<ArrangersFieldDTO>();

		try {
			StringBuffer sql = new StringBuffer();
			sql
					.append("SELECT category,categoryValue,mainCategory,subCategory,displayField,alias,id FROM arranger_fields ORDER BY mainCategory,subCategory,displayField ASC");
			makeConnection(dbLookup);
			statement = conn.prepareStatement(sql.toString());
			rs = statement.executeQuery();

			ArrangersFieldDTO arrangerFieldDTO;

			if (rs != null) {
				while (rs.next()) {
					arrangerFieldDTO = new ArrangersFieldDTO();
					arrangerFieldDTO.setCategory(rs.getString(1));
					arrangerFieldDTO.setCategoryValue(rs.getBoolean(2));
					arrangerFieldDTO.setMainCategory(rs.getInt(3));
					arrangerFieldDTO.setSubCategory(rs.getInt(4));
					arrangerFieldDTO.setDisplayFields(rs.getInt(5));
					arrangerFieldDTO.setAlias(rs.getString(6));
					arrangerFieldDTO.setId(rs.getInt(7));
					list.add(arrangerFieldDTO);
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return list;
	}

	public ArrayList<ArrangersFieldDTO> getArrangersFieldMainCategory(String dbLookup) throws SQLException {
		ArrayList<ArrangersFieldDTO> list = new ArrayList<ArrangersFieldDTO>();

		try {
			StringBuffer sql = new StringBuffer();
			sql
					.append("SELECT category,categoryValue,mainCategory,subCategory,displayField,alias,id FROM arranger_fields WHERE subCategory = 0 AND displayField = 0 ORDER BY mainCategory ASC");
			makeConnection(dbLookup);
			statement = conn.prepareStatement(sql.toString());
			rs = statement.executeQuery();

			ArrangersFieldDTO arrangerFieldDTO;

			if (rs != null) {
				while (rs.next()) {
					arrangerFieldDTO = new ArrangersFieldDTO();
					arrangerFieldDTO.setCategory(rs.getString(1));
					arrangerFieldDTO.setCategoryValue(rs.getBoolean(2));
					arrangerFieldDTO.setMainCategory(rs.getInt(3));
					arrangerFieldDTO.setSubCategory(rs.getInt(4));
					arrangerFieldDTO.setDisplayFields(rs.getInt(5));
					arrangerFieldDTO.setAlias(rs.getString(6));
					arrangerFieldDTO.setId(rs.getInt(7));
					list.add(arrangerFieldDTO);
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return list;
	}

	public ArrayList<ArrangersFieldDTO> getArrangersFieldSubCategory(
			String dbLookup) throws SQLException {
		ArrayList<ArrangersFieldDTO> list = new ArrayList<ArrangersFieldDTO>();

		try {
			StringBuffer sql = new StringBuffer();
			// sql.append("SELECT category,categoryValue,mainCategory,subCategory,displayField,alias,id FROM arranger_fields WHERE subCategory > 0 AND displayField = 0 ORDER BY mainCategory,subCategory,displayField ASC");
			sql
					.append("SELECT category,categoryValue,mainCategory,subCategory,displayField,alias,id FROM arranger_fields WHERE subCategory > 0 AND displayField < 1 ORDER BY mainCategory,subCategory,displayField ASC");
			makeConnection(dbLookup);
			statement = conn.prepareStatement(sql.toString());
			rs = statement.executeQuery();

			ArrangersFieldDTO arrangerFieldDTO;

			if (rs != null) {
				while (rs.next()) {
					arrangerFieldDTO = new ArrangersFieldDTO();
					arrangerFieldDTO.setCategory(rs.getString(1));
					arrangerFieldDTO.setCategoryValue(rs.getBoolean(2));
					arrangerFieldDTO.setMainCategory(rs.getInt(3));
					arrangerFieldDTO.setSubCategory(rs.getInt(4));
					arrangerFieldDTO.setDisplayFields(rs.getInt(5));
					arrangerFieldDTO.setAlias(rs.getString(6));
					arrangerFieldDTO.setId(rs.getInt(7));
					list.add(arrangerFieldDTO);
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return list;
	}

	public ArrayList<ArrangersFieldDTO> getArrangersFieldCategory(
			String dbLookup) throws SQLException {
		ArrayList<ArrangersFieldDTO> list = new ArrayList<ArrangersFieldDTO>();

		try {
			StringBuffer sql = new StringBuffer();
			sql
					.append("SELECT category,categoryValue,mainCategory,subCategory,displayField,alias,id FROM arranger_fields WHERE subCategory > 0 AND displayField > 0 ORDER BY mainCategory,subCategory,displayField ASC");
			makeConnection(dbLookup);
			statement = conn.prepareStatement(sql.toString());
			rs = statement.executeQuery();

			ArrangersFieldDTO arrangerFieldDTO;

			if (rs != null) {
				while (rs.next()) {
					arrangerFieldDTO = new ArrangersFieldDTO();
					arrangerFieldDTO.setCategory(rs.getString(1));
					arrangerFieldDTO.setCategoryValue(rs.getBoolean(2));
					arrangerFieldDTO.setMainCategory(rs.getInt(3));
					arrangerFieldDTO.setSubCategory(rs.getInt(4));
					arrangerFieldDTO.setDisplayFields(rs.getInt(5));
					arrangerFieldDTO.setAlias(rs.getString(6));
					arrangerFieldDTO.setId(rs.getInt(7));
					list.add(arrangerFieldDTO);
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return list;
	}

	public String[] getSelectedArrangers(String dbLookup) throws SQLException {

		List<String> list = new ArrayList<String>();
		try {
			StringBuffer sql = new StringBuffer();
			sql
					.append("SELECT id FROM arranger_fields where categoryValue = 1");
			makeConnection(dbLookup);
			statement = conn.prepareStatement(sql.toString());
			rs = statement.executeQuery();

			if (rs != null) {
				while (rs.next()) {
					list.add(rs.getInt(1) + "");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		String[] listArray = list.toArray(new String[list.size()]);
		return listArray;
	}

	public void updateArrangerFieldsCategoryValue(String selectedId,
			String dbLookup) {

		try {
			StringBuffer sql = new StringBuffer();
			sql
					.append("UPDATE arranger_fields SET categoryValue=TRUE WHERE id IN ("
							+ selectedId + ")");

			makeConnection(dbLookup);
			statement = conn.prepareStatement(sql.toString());
			statement.executeUpdate();

		} catch (SQLException e) {

		} catch (Exception e) {

		} finally {
			closeConnection();
		}

	}

	public void updateArrangerFieldsCategoryValueNull(String dbLookup) {

		try {
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE arranger_fields SET categoryValue=FALSE");

			makeConnection(dbLookup);
			statement = conn.prepareStatement(sql.toString());
			statement.executeUpdate();

		} catch (SQLException e) {

		} catch (Exception e) {

		} finally {
			closeConnection();
		}

	}

	public void updateArrangerFieldsAlias(int id, String alias, String dbLookup) {

		try {

			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE arranger_fields SET alias='" + alias
					+ "' WHERE id = " + id);
			/*
			 * sql.append("UPDATE arranger_fields SET alias='alias' WHERE id = 3"
			 * );
			 */
			makeConnection(dbLookup);
			statement = conn.prepareStatement(sql.toString());
			statement.executeUpdate();

		} catch (SQLException e) {

		} catch (Exception e) {

		} finally {
			closeConnection();
		}

	}

	// TODO Auto-generated method stub

}
