package news.dao;

import java.util.List;

import news.model.UserModel;
import news.paging.Pageble;

public interface IUserDAO extends GenericDAO<UserModel>{
	UserModel findOne(Long id);
	List<UserModel> findAll(Pageble... parameter);
	Long save(UserModel userModel);
	void update(UserModel userModel);
	void delete(Long id);
	UserModel findByUserAndPass(String username,String password);
	void deleteByRoleId(Long id);
	void changePassword(Long id, String newPassword);
	Integer getTotalItem();
}
