package news.service.impl;

import java.util.List;

import javax.inject.Inject;

import news.dao.ICommentDAO;
import news.model.CommentModel;
import news.paging.Pageble;
import news.service.ICommentService;

public class CommentService implements ICommentService{

	@Inject
	private ICommentDAO commentDao; 
	
	@Override
	public List<CommentModel> findAllByNewId(Long id) {
		return commentDao.findAllByNewId(id);
	}

	@Override
	public List<CommentModel> findAll(Pageble... parameter) {
		List<CommentModel> list = null;
		if(parameter.length == 1) {
			list = commentDao.findAll(parameter[0]);
		}else if(parameter.length == 0){
			list = commentDao.findAll();
		}
		return list;
	}

	@Override
	public CommentModel save(CommentModel commentModel) {
		Long id = commentDao.save(commentModel);
		return commentDao.findOne(id);
	}

	@Override
	public CommentModel update(CommentModel commentModel) {
		commentDao.update(commentModel);
		return commentDao.findOne(commentModel.getId());
	}

	@Override
	public void delete(Long[] ids) {
		for(Long id:ids) {
			commentDao.delete(id);
		}
		
	}

	@Override
	public Integer getTotalItem() {
		return commentDao.getTotalItem();
	}

	@Override
	public CommentModel findOne(Long id) {
		return commentDao.findOne(id);
	}

}
