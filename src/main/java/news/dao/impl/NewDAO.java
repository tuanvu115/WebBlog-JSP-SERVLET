package news.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import news.dao.INewDAO;
import news.mapper.NewMapper;
import news.model.NewModel;
import news.paging.Pageble;

public class NewDAO extends AbstractDAO<NewModel> implements INewDAO {

	@Override
	public List<NewModel> findAll(Object... parameter) {
		StringBuilder sql = new StringBuilder("SELECT * FROM news INNER JOIN category ON news.categoryid=category.id ");
		if(parameter.length == 1) {
			Pageble pageble = (Pageble)parameter[0];
			if (pageble.getSorter() != null && StringUtils.isNotBlank(pageble.getSorter().getSortName()) && StringUtils.isNotBlank(pageble.getSorter().getSortBy())) {
				sql.append(" ORDER BY "+pageble.getSorter().getSortName()+" "+pageble.getSorter().getSortBy()+"");
			}
			if (pageble.getOffset() != null && pageble.getLimit() != null) {
				sql.append(" LIMIT "+pageble.getOffset()+", "+pageble.getLimit()+"");
			}
		}else if(parameter.length == 2) {
			Pageble pageble = (Pageble)parameter[0];
			Long id = (Long)parameter[1];
			if(id != null) {
				sql.append("WHERE user_id = "+id+" ");
			}
			
			if (pageble.getSorter() != null && StringUtils.isNotBlank(pageble.getSorter().getSortName()) && StringUtils.isNotBlank(pageble.getSorter().getSortBy())) {
				sql.append(" ORDER BY "+pageble.getSorter().getSortName()+" "+pageble.getSorter().getSortBy()+"");
			}
			if (pageble.getOffset() != null && pageble.getLimit() != null) {
				sql.append(" LIMIT "+pageble.getOffset()+", "+pageble.getLimit()+" ");
			}
			
		}
		return query(sql.toString(), new NewMapper());
	}

	

	@Override
	public Integer getTotalItem() {
		StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM news");
		return count(sql.toString());
	}

	@Override
	public Long save(NewModel newModel) {
		StringBuilder sql = new StringBuilder("INSERT INTO news(title,thumbnail,shortdescription,content,categoryid,user_id,createddate,modifieddate,createdby,modifiedby) ");
		sql.append("values (?,?,?,?,?,?,?,?,?,?) ");
		return insert(sql.toString(), newModel.getTitle(),newModel.getThumbnail(),newModel.getShortDescription(),
				newModel.getContent(),newModel.getCategoryId(),newModel.getUserId(),newModel.getCreatedDate(),newModel.getModifiedDate(),newModel.getCreatedBy(),newModel.getModifiedBy());
	}

	@Override
	public void update(NewModel newModel) {
		StringBuilder sql = new StringBuilder("UPDATE news SET title=?,thumbnail=?,shortdescription=?,content=?,categoryid=?,modifieddate=?,modifiedby=? ");
		sql.append("WHERE id = ?");
		update(sql.toString(), newModel.getTitle(),newModel.getThumbnail(),newModel.getShortDescription(),
				newModel.getContent(),newModel.getCategoryId(),newModel.getModifiedDate(),newModel.getModifiedBy(),newModel.getId());
	}

	@Override
	public void delete(Long id) {
		StringBuilder sql = new StringBuilder("DELETE FROM news WHERE id = ?");
		update(sql.toString(),id);
	}

	@Override
	public NewModel findOne(Long id) {
		StringBuilder sql = new StringBuilder("SELECT * FROM news INNER JOIN category ON news.categoryid=category.id WHERE news.id = ?");
		List<NewModel> list = query(sql.toString(), new NewMapper(),id);
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public void deleteByCategoryId(Long id) {
		StringBuilder sql = new StringBuilder("DELETE FROM news WHERE categoryid = ?");
		update(sql.toString(),id);
	}

	@Override
	public List<NewModel> findAllByCategoryId(Pageble pageble, Long id) {
		StringBuilder sql = new StringBuilder("SELECT * FROM news INNER JOIN category ON news.categoryid=category.id WHERE category.id = ?");
		if (pageble.getSorter() != null && StringUtils.isNotBlank(pageble.getSorter().getSortName()) && StringUtils.isNotBlank(pageble.getSorter().getSortBy())) {
			sql.append(" ORDER BY "+pageble.getSorter().getSortName()+" "+pageble.getSorter().getSortBy()+"");
		}
		if (pageble.getOffset() != null && pageble.getLimit() != null) {
			sql.append(" LIMIT "+pageble.getOffset()+", "+pageble.getLimit()+" ");
		}
		
		return query(sql.toString(), new NewMapper(),id);
	}

	@Override
	public List<NewModel> findTopInteractive() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM news,(SELECT COUNT(*) AS SL,new_id AS newid FROM comment GROUP BY new_id ) AS Q ,");
		sql.append("category WHERE news.id = Q.newid AND news.categoryid = category.id ");
		sql.append("order by SL desc limit 0,3;");
		return query(sql.toString(), new NewMapper());
	}

}
