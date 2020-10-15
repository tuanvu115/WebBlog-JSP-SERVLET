package news.dao;

import java.util.List;

import news.model.CommentModel;
import news.paging.Pageble;

public interface ICommentDAO extends GenericDAO<CommentModel>{
	
	CommentModel findOne(Long id);
	void deleteByNewId(Long id);
	void delete(Long id);
	void deleteByUserId(Long id);
	List<CommentModel> findAllByNewId(Long id);
	Long save(CommentModel commentModel);
	void update(CommentModel commentModel);
	List<CommentModel> findAll(Pageble... parameter);
	Integer getTotalItem();
	
	
}
