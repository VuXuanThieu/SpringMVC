package com.laptrinhjavaweb.URUtil;

import java.util.List;

public interface IURDAO extends GenericDAO<URModel> {

	URModel findOne(Long userId);

	List<URModel> findByRoleId(Long roleId);

	Long save(URModel urModel);

	void update(URModel updateUR);

	void delete(long userId);
}
