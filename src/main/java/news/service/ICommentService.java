package news.service;

import java.util.List;

import news.model.CommentModel;
import news.paging.Pageble;

public interface ICommentService {

	List<CommentModel> findAllByNewId(Long id);
	List<CommentModel> findAll(Pageble... parameter);
	CommentModel save(CommentModel commentModel);
	CommentModel update(CommentModel commentModel);
	void delete(Long ids[]);
	Integer getTotalItem();
	CommentModel findOne(Long id);
}
