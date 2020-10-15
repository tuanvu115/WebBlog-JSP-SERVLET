package news.service;

import java.util.List;

import news.model.CategoryModel;
import news.paging.Pageble;

public interface ICategoryService {
	CategoryModel save(CategoryModel categoryModel);
	CategoryModel update(CategoryModel categoryModel);
	void delete(Long ids[]);
	List<CategoryModel> findAll(Pageble... parameter);
	Integer getTotalItem();
	CategoryModel findOne(Long id);
	
}
