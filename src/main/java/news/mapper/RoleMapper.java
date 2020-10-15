package news.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import news.model.RoleModel;

public class RoleMapper implements RowMapper<RoleModel>{

	@Override
	public RoleModel mapRow(ResultSet resultSet) {
		try {
			RoleModel roleModel = new RoleModel();
			
			roleModel.setId(resultSet.getLong("id"));
			roleModel.setCode(resultSet.getString("code"));
			roleModel.setName(resultSet.getString("name"));
			
			
			
			if(resultSet.getString("createdby") != null) {
				roleModel.setCreatedBy(resultSet.getString("createdby"));
			}
			
			if(resultSet.getTimestamp("createddate") != null) {
				roleModel.setCreatedDate(resultSet.getTimestamp("createddate"));	
			}
			if(resultSet.getString("modifiedby") != null) {
				roleModel.setModifiedBy(resultSet.getString("modifiedby"));
			}
			if(resultSet.getTimestamp("modifieddate") != null) {
				roleModel.setModifiedDate(resultSet.getTimestamp("modifieddate"));
			}
			return roleModel;
		} catch (SQLException e) {
			return null;
		}
		
	}

}
