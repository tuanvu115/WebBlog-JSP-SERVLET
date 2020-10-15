package news.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import news.dao.ICommentDAO;
import news.mapper.CommentMapper;
import news.model.CommentModel;
import news.paging.Pageble;

public class CommentDAO extends AbstractDAO<CommentModel> implements ICommentDAO{
	
	
	@Override
	public Integer getTotalItem() {
		StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM comment");
		return count(sql.toString());
	}

	@Override
	public void deleteByNewId(Long id) {
		StringBuilder sql = new StringBuilder("DELETE FROM comment WHERE new_id = ?");
		update(sql.toString(), id);
		
	}

	@Override
	public void delete(Long id) {
		StringBuilder sql = new StringBuilder("DELETE FROM comment WHERE id = ?");
		update(sql.toString(), id);
		
	}

	@Override
	public void deleteByUserId(Long id) {
		StringBuilder sql = new StringBuilder("DELETE FROM comment WHERE user_id = ?");
		update(sql.toString(), id);
	}

	@Override
	public List<CommentModel> findAllByNewId(Long id) {
		StringBuilder sql = new StringBuilder("SELECT * FROM comment INNER JOIN user ON comment.user_id = user.id WHERE new_id = ?");
		return query(sql.toString(), new CommentMapper(), id);
	}

	@Override
	public Long save(CommentModel commentModel) {
		StringBuilder sql = new StringBuilder(
				"INSERT INTO comment(content,user_id,new_id,createddate,modifieddate,createdby,modifiedby)");
		sql.append(" values (?,?,?,?,?,?,?)");
		return insert(sql.toString(),commentModel.getContent(),commentModel.getUserId(),commentModel.getNewId() , commentModel.getCreatedDate(), commentModel.getModifiedDate(),
				commentModel.getCreatedBy(), commentModel.getModifiedBy());
	}

	@Override
	public void update(CommentModel commentModel) {
		StringBuilder sql = new StringBuilder(
				"UPDATE comment SET content=?,user_id=?,new_id=?,modifieddate=?,modifiedby=? WHERE id=?");
		sql.append(" values (?,?,?,?,?,?,?)");
		update(sql.toString(),commentModel.getContent(),commentModel.getUserId(),commentModel.getNewId() , commentModel.getModifiedDate(),
				 commentModel.getModifiedBy(),commentModel.getId());
		
	}

	@Override
	public List<CommentModel> findAll(Pageble... parameter) {
		StringBuilder sql = new StringBuilder("SELECT * FROM comment ");
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

		return query(sql.toString(), new CommentMapper());
	}

	@Override
	public CommentModel findOne(Long id) {
		StringBuilder sql = new StringBuilder("SELECT * FROM comment WHERE id = ?");
		List<CommentModel> list = query(sql.toString(), new CommentMapper(), id);
		return list.isEmpty() ? null : list.get(0);
	}

	

}
