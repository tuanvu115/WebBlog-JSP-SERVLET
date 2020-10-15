package news.service;

import java.util.List;

import news.model.RoleModel;
import news.paging.Pageble;

public interface IRoleService {

	RoleModel save(RoleModel roleModel);
	RoleModel update(RoleModel roleModel);
	void delete(Long ids[]);
	List<RoleModel> findAll(Pageble... parameter);
	Integer getTotalItem();
	RoleModel findOne(Long id);
}
