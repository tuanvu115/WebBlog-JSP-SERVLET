package news.dao;

import java.util.List;

import news.model.NewModel;
import news.paging.Pageble;

public interface INewDAO extends GenericDAO<NewModel>{

	List<NewModel> findAll(Object... parameter);
	List<NewModel> findAllByCategoryId(Pageble pageble,Long id);
	List<NewModel> findTopInteractive();
	Integer getTotalItem();
	Long save(NewModel newModel);
	void update(NewModel newModel);
	void delete(Long id);
	NewModel findOne(Long id);
	void deleteByCategoryId(Long id);
	
}
