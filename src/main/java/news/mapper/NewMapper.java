package news.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import news.model.CategoryModel;
import news.model.NewModel;

public class NewMapper implements RowMapper<NewModel>{

	@Override
	public NewModel mapRow(ResultSet resultSet) {
		try {
			NewModel newModel = new NewModel();
			
			
			newModel.setId(resultSet.getLong("id"));
			newModel.setTitle(resultSet.getString("title"));
			newModel.setContent(resultSet.getString("content"));
			newModel.setThumbnail(resultSet.getString("thumbnail"));
			newModel.setShortDescription(resultSet.getString("shortdescription"));
			newModel.setCategoryId(resultSet.getLong("categoryid"));
			newModel.setUserId(resultSet.getLong("user_id"));
			if(resultSet.getString("createdby") != null) {
				newModel.setCreatedBy(resultSet.getString("createdby"));
			}
			
			if(resultSet.getTimestamp("createddate") != null) {
				newModel.setCreatedDate(resultSet.getTimestamp("createddate"));	
			}
			if(resultSet.getString("modifiedby") != null) {
				newModel.setModifiedBy(resultSet.getString("modifiedby"));
			}
			if(resultSet.getTimestamp("modifieddate") != null) {
				newModel.setModifiedDate(resultSet.getTimestamp("modifieddate"));
			}
			
			
			CategoryModel category = new CategoryModel();
			if(resultSet.getString("code") != null) {
				category.setCode(resultSet.getString("code"));
			}
			if(resultSet.getString("name") != null) {
				category.setName(resultSet.getString("name"));
			}
			
			newModel.setCategory(category);
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			return newModel;
		} catch (SQLException e) {
			return null;
		}
		
	}

}
