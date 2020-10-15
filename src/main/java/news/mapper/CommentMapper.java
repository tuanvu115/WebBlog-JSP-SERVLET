package news.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import news.model.CommentModel;
import news.model.UserModel;

public class CommentMapper implements RowMapper<CommentModel>{

	@Override
	public CommentModel mapRow(ResultSet resultSet) {
		try {
			
			CommentModel commentModel = new CommentModel();
			commentModel.setId(resultSet.getLong("id"));
			commentModel.setContent(resultSet.getString("content"));
			commentModel.setNewId(resultSet.getLong("new_id"));
			commentModel.setUserId(resultSet.getLong("user_id"));
			
			
			
			if(resultSet.getString("createdby") != null) {
				commentModel.setCreatedBy(resultSet.getString("createdby"));
			}
			
			if(resultSet.getTimestamp("createddate") != null) {
				commentModel.setCreatedDate(resultSet.getTimestamp("createddate"));	
			}
			if(resultSet.getString("modifiedby") != null) {
				commentModel.setModifiedBy(resultSet.getString("modifiedby"));
			}
			if(resultSet.getTimestamp("modifieddate") != null) {
				commentModel.setModifiedDate(resultSet.getTimestamp("modifieddate"));
			}
			
			
			
			return commentModel;
		} catch (SQLException e) {
			return null;
		}
		
	}

	
}
