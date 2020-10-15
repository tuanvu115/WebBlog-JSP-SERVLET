package news.controller.admin;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import news.constant.SystemConstant;
import news.model.RoleModel;
import news.paging.PageRequest;
import news.paging.Pageble;
import news.service.IRoleService;
import news.sort.Sorter;
import news.utils.FormUtil;

@WebServlet(urlPatterns = {"/admin-role"})
public class RoleController extends HttpServlet{

	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private IRoleService roleService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RoleModel model = FormUtil.toModel(RoleModel.class, req);
		String type = model.getType();
		String view = "";
		if(model != null && model.getType().equals(SystemConstant.LIST)) {
			Pageble pageble = new PageRequest(model.getPage(),model.getMaxPageItem(),
					new Sorter(model.getSortName(),model.getSortBy()));
			model.setTotalItem(roleService.getTotalItem());
			model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getMaxPageItem()));
			model.setListResult(roleService.findAll(pageble));
			view = "/views/admin/role/list.jsp";
		}else if(model != null && model.getType().equals(SystemConstant.EDIT) || model.getType().equals(SystemConstant.VIEW)) {
			req.setAttribute("roles",roleService.findAll());
			if(model.getId() != null) {
				model = roleService.findOne(model.getId());
			}
			view = "/views/admin/role/edit.jsp";
		}
		model.setType(type);
		req.setAttribute("model",model);
		RequestDispatcher rd = req.getRequestDispatcher(view);
		rd.forward(req, resp);
	}

}
