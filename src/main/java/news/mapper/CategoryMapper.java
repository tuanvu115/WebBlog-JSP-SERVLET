package news.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import news.model.CategoryModel;

public class CategoryMapper implements RowMapper<CategoryModel>{

	@Override
	public CategoryModel mapRow(ResultSet resultSet) {
		try {
			CategoryModel categoryModel = new CategoryModel();
			categoryModel.setId(resultSet.getLong("id"));
			categoryModel.setCode(resultSet.getString("code"));
			categoryModel.setName(resultSet.getString("name"));
			
			
			
			if(resultSet.getString("createdby") != null) {
				categoryModel.setCreatedBy(resultSet.getString("createdby"));
			}
			
			if(resultSet.getTimestamp("createddate") != null) {
				categoryModel.setCreatedDate(resultSet.getTimestamp("createddate"));	
			}
			if(resultSet.getString("modifiedby") != null) {
				categoryModel.setModifiedBy(resultSet.getString("modifiedby"));
			}
			if(resultSet.getTimestamp("modifieddate") != null) {
				categoryModel.setModifiedDate(resultSet.getTimestamp("modifieddate"));
			}
			return categoryModel;
		} catch (SQLException e) {
			return null;
		}
		
	}

}
