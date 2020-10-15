package news.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import news.dao.ICategoryDAO;
import news.mapper.CategoryMapper;
import news.model.CategoryModel;
import news.paging.Pageble;

public class CategoryDAO extends AbstractDAO<CategoryModel> implements ICategoryDAO{
	
	
	@Override
	public Integer getTotalItem() {
		StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM category");
		return count(sql.toString());
	}

	@Override
	public List<CategoryModel> findAll(Pageble... parameter) {
		StringBuilder sql = new StringBuilder("SELECT * FROM category ");
		if(parameter.length == 1 ) {
			Pageble pageble = parameter[0];
			if (pageble.getSorter() != null && StringUtils.isNotBlank(pageble.getSorter().getSortName()) && StringUtils.isNotBlank(pageble.getSorter().getSortBy())) {
				sql.append(" ORDER BY "+pageble.getSorter().getSortName()+" "+pageble.getSorter().getSortBy()+"");
			}
			if (pageble.getOffset() != null && pageble.getLimit() != null) {
				sql.append(" LIMIT "+pageble.getOffset()+", "+pageble.getLimit()+"");
			}
		}
		
		return query(sql.toString(), new CategoryMapper());
	}

	@Override
	public CategoryModel findOne(Long id) {
		StringBuilder sql = new StringBuilder("SELECT * FROM category WHERE id = ?");
		List<CategoryModel> list = query(sql.toString(), new CategoryMapper(), id);
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public Long save(CategoryModel categoryModel) {
		StringBuilder sql = new StringBuilder("INSERT INTO category(name,code,createddate,modifieddate,createdby,modifiedby) ");
		sql.append("values (?,?,?,?,?,?)");
		return insert(sql.toString(), categoryModel.getName(),categoryModel.getCode(),categoryModel.getCreatedDate(),
				categoryModel.getModifiedDate(),categoryModel.getCreatedBy(),categoryModel.getModifiedBy());
	}

	@Override
	public void update(CategoryModel categoryModel) {
		StringBuilder sql = new StringBuilder("UPDATE category SET name=?,code=?,modifieddate=?,modifiedby=? ");
		sql.append("WHERE id = ?");
		update(sql.toString(),categoryModel.getName(),categoryModel.getCode(),categoryModel.getModifiedDate(),
				categoryModel.getModifiedBy(),categoryModel.getId());
	}

	@Override
	public void delete(Long id) {
		StringBuilder sql = new StringBuilder("DELETE FROM category WHERE id = ?");
		update(sql.toString(), id);
	}

	@Override
	public void deleteByCategoryId(Long id) {
		StringBuilder sql = new StringBuilder("DELETE FROM category WHERE categoryid = ?");
		update(sql.toString(), id);
		
	}

}
