package news.service;


import java.util.List;

import news.model.NewModel;
import news.paging.Pageble;

public interface INewService {
	List<NewModel> findTopSix();
	List<NewModel> findAll(Object... parameter);
	List<NewModel> findAllByCategoryId(Pageble pageble,Long id);
	List<NewModel> findTopInteractive();
	Integer getTotalItem();
	NewModel save(NewModel newModel);
	NewModel update(NewModel newModel);
	void delete(Long ids[]);
	NewModel findOne(Long id);
}
