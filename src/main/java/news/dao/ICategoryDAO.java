package news.dao;

import java.util.List;

import news.model.CategoryModel;
import news.paging.Pageble;

public interface ICategoryDAO extends GenericDAO<CategoryModel>{

	List<CategoryModel> findAll(Pageble... parameter);
	CategoryModel findOne(Long id);
	Long save(CategoryModel categoryModel);
	void update(CategoryModel categoryModel);
	void delete(Long id);
	void deleteByCategoryId(Long id);
	Integer getTotalItem();
	
}
