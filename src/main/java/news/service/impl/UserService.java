package news.service.impl;


import java.util.List;

import javax.inject.Inject;

import news.dao.ICommentDAO;
import news.dao.IUserDAO;
import news.model.UserModel;
import news.paging.Pageble;
import news.service.IUserService;

public class UserService implements IUserService{

	@Inject
	private IUserDAO userDao;
	
	@Inject
	private ICommentDAO commentDao;
	
	@Override
	public UserModel findUserAndPassAndStatus(String username,String password) {
		return userDao.findByUserAndPass(username,password);
		
	}

	@Override
	public UserModel save(UserModel userModel) {
		Long id = userDao.save(userModel);
		return userDao.findOne(id);
	}

	@Override
	public UserModel update(UserModel userModel) {
		userDao.update(userModel);
		return userDao.findOne(userModel.getId());
	}

	@Override
	public List<UserModel> findAll(Pageble... parameter) {
		List<UserModel> list = null;
		if(parameter.length == 1) {
			list = userDao.findAll(parameter[0]);
		}else if(parameter.length == 0){
			list = userDao.findAll();
		}
		return list;
	}

	@Override
	public void delete(Long[] ids) {
		for(Long id:ids) {
			commentDao.deleteByUserId(id);
			userDao.delete(id);
		}
		
	}

	@Override
	public void changePassword(Long id, String newPassword) {
		userDao.changePassword(id,newPassword);
		
	}

	@Override
	public Integer getTotalItem() {
		return userDao.getTotalItem();
	}

	@Override
	public UserModel findOne(Long id) {
		return userDao.findOne(id);
	}

}
