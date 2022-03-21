package com.laptrinhjavaweb.URUtil;

import java.util.List;

public class URDAO extends AbstractDAO<URModel> implements IURDAO {

	@Override
	public List<URModel> findByRoleId(Long roleId) {
		String sql = "SELECT * FROM thuvienquan9.user_role WHERE roleid = ?";
		return query(sql, new URMapper(), roleId);
	}

	@Override
	public Long save(URModel urModel) {
		String sql = "INSERT INTO thuvienquan9.user_role (userid, roleid) VALUES(?, ?)";
		return insert(sql, urModel.getUserid(), urModel.getRoleid());
	}

	@Override
	public URModel findOne(Long userId) {
		String sql = "SELECT * FROM thuvienquan9.user_role WHERE userId = ?";
		List<URModel> user_role = query(sql, new URMapper(), userId);
		return user_role.isEmpty() ? null : user_role.get(0);
	}

	@Override
	public void update(URModel updateUR) {
		String sql = "UPDATE thuvienquan9.user_role SET roleid = ? WHERE userid = ?";
		update(sql, updateUR.getRoleid(), updateUR.getUserid());
	}

	@Override
	public void delete(long userId) {
		String sql = "DELETE FROM thuvienquan9.user_role WHERE userId = ?";
		update(sql, userId);
	}

}
