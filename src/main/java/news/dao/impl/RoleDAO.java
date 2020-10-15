package news.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import news.dao.IRoleDAO;
import news.mapper.RoleMapper;
import news.model.RoleModel;
import news.paging.Pageble;

public class RoleDAO extends AbstractDAO<RoleModel> implements IRoleDAO {
	
	
	@Override
	public Integer getTotalItem() {
		StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM role");
		return count(sql.toString());
	}

	@Override
	public Long save(RoleModel roleModel) {
		StringBuilder sql = new StringBuilder(
				"INSERT INTO role(name,code,createddate,modifieddate,createdby,modifiedby)");
		sql.append(" values (?,?,?,?,?,?)");
		return insert(sql.toString(), roleModel.getName(),
				roleModel.getCode(), roleModel.getCreatedDate(), roleModel.getModifiedDate(),
				roleModel.getCreatedBy(), roleModel.getModifiedBy());

	}

	@Override
	public void update(RoleModel roleModel) {
		StringBuilder sql = new StringBuilder(
				"UPDATE role SET name = ?,code = ?,modifieddate = ?,modifiedby = ? WHERE id = ?");
		update(sql.toString(), roleModel.getName(),
				roleModel.getCode(),  roleModel.getModifiedDate(),
				 roleModel.getModifiedBy(),roleModel.getId());

	}

	@Override
	public void delete(Long id) {
		StringBuilder sql = new StringBuilder("DELETE FROM role WHERE id = ?");
		update(sql.toString(),id);

	}

	@Override
	public List<RoleModel> findAll(Pageble... parameter) {
		StringBuilder sql = new StringBuilder("SELECT * FROM role ");
		if (parameter.length == 1) {
			Pageble pageble = parameter[0];
			if (pageble.getSorter() != null && StringUtils.isNotBlank(pageble.getSorter().getSortName())
					&& StringUtils.isNotBlank(pageble.getSorter().getSortBy())) {
				sql.append(
						" ORDER BY " + pageble.getSorter().getSortName() + " " + pageble.getSorter().getSortBy() + "");
			}
			if (pageble.getOffset() != null && pageble.getLimit() != null) {
				sql.append(" LIMIT " + pageble.getOffset() + ", " + pageble.getLimit() + "");
			}
		}

		return query(sql.toString(), new RoleMapper());
	}

	@Override
	public RoleModel findOne(Long id) {
		StringBuilder sql = new StringBuilder("SELECT * FROM role WHERE id = ?");
		List<RoleModel> list = query(sql.toString(), new RoleMapper(), id);
		return list.isEmpty() ? null : list.get(0);
	}

}
