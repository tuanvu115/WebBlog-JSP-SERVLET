package news.service;

import java.util.List;

import news.model.UserModel;
import news.paging.Pageble;

public interface IUserService {
	UserModel findUserAndPassAndStatus(String username,String password);
	UserModel save(UserModel userModel);
	UserModel update(UserModel userModel);
	List<UserModel> findAll(Pageble... parameter);
	void delete(Long ids[]);
	void changePassword(Long id,String newPassword);
	Integer getTotalItem();
	UserModel findOne(Long id);
}
