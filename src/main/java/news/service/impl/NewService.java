package news.service.impl;

import java.util.List;

import javax.inject.Inject;

import news.dao.ICommentDAO;
import news.dao.INewDAO;
import news.model.NewModel;
import news.paging.PageRequest;
import news.paging.Pageble;
import news.service.INewService;
import news.sort.Sorter;

public class NewService implements INewService{

	@Inject
	private INewDAO newDao;
	
	@Inject
	private ICommentDAO commentDao;
	
	@Override
	public List<NewModel> findTopSix() {
		Pageble pageble = new PageRequest(1,6,new Sorter());
		return newDao.findAll(pageble);
	}

	@Override
	public List<NewModel> findAll(Object... parameter) {
		List<NewModel> list = null;
		if(parameter.length == 2) {
			list = newDao.findAll(parameter[0],parameter[1]);
		}
		else if(parameter.length == 1) {
			list = newDao.findAll(parameter[0]);
		}else if(parameter.length == 0){
			list = newDao.findAll();
		}
		return list;
	}

	@Override
	public Integer getTotalItem() {
		return newDao.getTotalItem();
	}

	@Override
	public NewModel save(NewModel newModel) {
		Long id = newDao.save(newModel);
		return newDao.findOne(id);
	}

	@Override
	public NewModel update(NewModel newModel) {
		newDao.update(newModel);
		return newDao.findOne(newModel.getId());
	}

	@Override
	public void delete(Long[] ids) {
		for(int i=0;i<ids.length;i++) {
			commentDao.deleteByNewId(ids[i]);
			newDao.delete(ids[i]);
		}
		
	}

	@Override
	public NewModel findOne(Long id) {
		return newDao.findOne(id);
	}

	@Override
	public List<NewModel> findAllByCategoryId(Pageble pageble, Long id) {
		return newDao.findAllByCategoryId(pageble, id);
	}

	@Override
	public List<NewModel> findTopInteractive() {
		return newDao.findTopInteractive();
	}

}
