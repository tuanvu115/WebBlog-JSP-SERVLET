package news.dao;

import java.util.List;

import news.model.RoleModel;
import news.paging.Pageble;

public interface IRoleDAO extends GenericDAO<RoleModel>{

	RoleModel findOne(Long id);
	List<RoleModel> findAll(Pageble... parameter);
	Long save(RoleModel roleModel);
	void update(RoleModel roleModel);
	void delete(Long id);
	Integer getTotalItem();
}
