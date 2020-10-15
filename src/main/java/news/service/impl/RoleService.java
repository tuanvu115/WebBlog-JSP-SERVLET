package news.service.impl;

import java.util.List;

import javax.inject.Inject;

import news.dao.IRoleDAO;
import news.dao.IUserDAO;
import news.model.RoleModel;
import news.paging.Pageble;
import news.service.IRoleService;

public class RoleService implements IRoleService{

	@Inject
	private IRoleDAO roleDao;
	
	@Inject
	private IUserDAO userDao;
	
	@Override
	public RoleModel save(RoleModel roleModel) {
		Long id = roleDao.save(roleModel);
		return roleDao.findOne(id);
	}

	@Override
	public RoleModel update(RoleModel roleModel) {
		roleDao.update(roleModel);
		return roleDao.findOne(roleModel.getId());
	}

	@Override
	public void delete(Long[] ids) {
		for(Long id:ids) {
			userDao.deleteByRoleId(id);
			roleDao.delete(id);
		}
		
	}

	@Override
	public List<RoleModel> findAll(Pageble... parameter) {
		List<RoleModel> list = null;
		if(parameter.length == 1) {
			list = roleDao.findAll(parameter[0]);
		}else if(parameter.length == 0){
			list = roleDao.findAll();
		}
		return list;
	}

	@Override
	public Integer getTotalItem() {
		return roleDao.getTotalItem();
	}

	@Override
	public RoleModel findOne(Long id) {
		return roleDao.findOne(id);
	}

}
