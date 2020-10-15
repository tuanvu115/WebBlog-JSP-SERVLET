package news.service.impl;


import java.util.List;

import javax.inject.Inject;

import news.dao.ICategoryDAO;
import news.dao.INewDAO;
import news.model.CategoryModel;
import news.paging.Pageble;
import news.service.ICategoryService;

public class CategoryService implements ICategoryService{

	@Inject
	private ICategoryDAO categoryDao;
	
	@Inject
	private INewDAO newDao;
	
	@Override
	public List<CategoryModel> findAll(Pageble... parameter) {
		List<CategoryModel> list = null;
		if(parameter.length == 1) {
			list = categoryDao.findAll(parameter[0]);
		}else if(parameter.length == 0){
			list = categoryDao.findAll();
		}
		return list;
	}

	@Override
	public CategoryModel save(CategoryModel categoryModel) {
		Long id = categoryDao.save(categoryModel);
		return categoryDao.findOne(id);
	}

	@Override
	public CategoryModel update(CategoryModel categoryModel) {
		categoryDao.update(categoryModel);
		return categoryDao.findOne(categoryModel.getId());
	}

	@Override
	public void delete(Long[] ids) {
		for(Long id:ids) {
			newDao.deleteByCategoryId(id);
			categoryDao.delete(id);
		}
		
	}

	@Override
	public Integer getTotalItem() {
		return categoryDao.getTotalItem();
	}

	@Override
	public CategoryModel findOne(Long id) {
		return categoryDao.findOne(id);
	}

	

}
