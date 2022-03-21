package com.laptrinhjavaweb.URUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class URMapper implements RowMapper<URModel> {

	@Override
	public URModel mapRow(ResultSet resultSet) {
		try {
			URModel urmodel = new URModel();
			urmodel.setUserid(resultSet.getLong("userid"));
			urmodel.setRoleid(resultSet.getLong("roleid"));

			return urmodel;
		} catch (SQLException e) {
			return null;
		}
	}
}
