package news.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import news.model.RoleModel;
import news.model.UserModel;

public class UserMapper implements RowMapper<UserModel> {

	@Override
	public UserModel mapRow(ResultSet resultSet) {
		try {
			
			UserModel userModel = new UserModel();
			userModel.setId(resultSet.getLong("id"));
			userModel.setFullname(resultSet.getString("fullname"));
			userModel.setUsername(resultSet.getString("username"));
			userModel.setPassword(resultSet.getString("password"));
			userModel.setStatus(resultSet.getInt("status"));
			userModel.setRoleId(resultSet.getLong("roleid"));

			if (resultSet.getString("createdby") != null) {
				userModel.setCreatedBy(resultSet.getString("createdby"));
			}

			if (resultSet.getTimestamp("createddate") != null) {
				userModel.setCreatedDate(resultSet.getTimestamp("createddate"));
			}
			if (resultSet.getString("modifiedby") != null) {
				userModel.setModifiedBy(resultSet.getString("modifiedby"));
			}
			if (resultSet.getTimestamp("modifieddate") != null) {
				userModel.setModifiedDate(resultSet.getTimestamp("modifieddate"));
			}

			RoleModel roleModel = new RoleModel();

			roleModel.setName(resultSet.getString("name"));

			roleModel.setCode(resultSet.getString("code"));

			userModel.setRole(roleModel);

			return userModel;
		} catch (SQLException e) {
			return null;
		}

	}

}
