package news.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import news.dao.IUserDAO;
import news.mapper.UserMapper;
import news.model.UserModel;
import news.paging.Pageble;

public class UserDAO extends AbstractDAO<UserModel> implements IUserDAO{
	
	@Override
	public Integer getTotalItem() {
		StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM user");
		return count(sql.toString());
	}

	@Override
	public UserModel findOne(Long id) {
		StringBuilder sql = new StringBuilder("SELECT * FROM user INNER JOIN role ON user.roleid=role.id WHERE user.id = ?");
		List<UserModel> list = query(sql.toString(), new UserMapper(), id);
		return list.isEmpty() ? null : list.get(0); 
	}

	@Override
	public List<UserModel> findAll(Pageble... parameter) {
		StringBuilder sql = new StringBuilder("SELECT * FROM user INNER JOIN role ON user.roleid=role.id ");
		if (parameter.length == 1) {
			Pageble pageble = parameter[0];
			if (pageble.getSorter() != null && StringUtils.isNotBlank(pageble.getSorter().getSortName())
					&& StringUtils.isNotBlank(pageble.getSorter().getSortBy())) {
				sql.append(
						" ORDER BY " + pageble.getSorter().getSortName() + " " + pageble.getSorter().getSortBy() + "");
			}
			if (pageble.getOffset() != null && pageble.getLimit() != null) {
				sql.append(" LIMIT " + pageble.getOffset() + ", " + pageble.getLimit() + "");
			}
		}

		return query(sql.toString(), new UserMapper());
	}

	@Override
	public Long save(UserModel userModel) {
		StringBuilder sql = new StringBuilder("INSERT INTO user(username,password,fullname,status,roleid,createddate,modifieddate,createdby,modifiedby)"); 
				sql.append(" values (?,?,?,?,?,?,?,?,?)");
		return insert(sql.toString(), userModel.getUsername(),userModel.getPassword(),userModel.getFullname(),userModel.getStatus(),
				userModel.getRoleId(),userModel.getCreatedDate(),userModel.getModifiedDate(),userModel.getCreatedBy(),userModel.getModifiedBy());
	}

	@Override
	public void update(UserModel userModel) {
		StringBuilder sql =new StringBuilder("UPDATE user SET username=?,password=?,fullname=?,status=?,roleid=?,modifieddate=?,modifiedby=? WHERE id=?") ;
		update(sql.toString(), userModel.getUsername(),userModel.getPassword(),userModel.getFullname(),userModel.getStatus(),
				userModel.getRoleId(),userModel.getModifiedDate(),userModel.getModifiedBy(),userModel.getId());
		
	}

	@Override
	public void delete(Long id) {
		StringBuilder sql = new StringBuilder("DELETE FROM user WHERE id = ?");
		update(sql.toString(),id);
	}

	@Override
	public UserModel findByUserAndPass(String username,String password) {
		String sql = "SELECT * FROM user INNER JOIN role ON user.roleid=role.id WHERE username = ? AND password = ?";
		List<UserModel> list = query(sql, new UserMapper(), username,password);
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public void deleteByRoleId(Long id) {
		StringBuilder sql = new StringBuilder("DELETE FROM user WHERE roleid = ?");
		update(sql.toString(),id);
		
	}

	@Override
	public void changePassword(Long id, String newPassword) {
		StringBuilder sql = new StringBuilder("UPDATE user SET password = ? WHERE id = ?");
		update(sql.toString(),newPassword,id);
	}

	

	

}
