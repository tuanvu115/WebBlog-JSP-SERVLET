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
import news.model.NewModel;
import news.paging.PageRequest;
import news.paging.Pageble;
import news.service.ICategoryService;
import news.service.INewService;
import news.sort.Sorter;
import news.utils.FormUtil;

@WebServlet(urlPatterns = {"/admin-new"})
public class NewController extends HttpServlet{

	@Inject
	private INewService newService;
	
	@Inject
	private ICategoryService categoryService;
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		NewModel model = FormUtil.toModel(NewModel.class, req);
		String type = model.getType();
		String view = "";
		if(model != null && model.getType().equals(SystemConstant.LIST)) {
			Pageble pageble = new PageRequest(model.getPage(),model.getMaxPageItem(),
					new Sorter(model.getSortName(),model.getSortBy()));
			model.setTotalItem(newService.getTotalItem());
			model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getMaxPageItem()));
			model.setListResult(newService.findAll(pageble));
			view = "/views/admin/new/list.jsp";
		}else if(model != null && model.getType().equals(SystemConstant.EDIT) || model.getType().equals(SystemConstant.VIEW)) {
			view = "/views/admin/new/edit.jsp";
			req.setAttribute("categories",categoryService.findAll());
			if(model.getId() != null) {
				model = newService.findOne(model.getId());
			}
		}
		model.setType(type);
		req.setAttribute("model",model);
		RequestDispatcher rd = req.getRequestDispatcher(view);
		rd.forward(req, resp);
	}
	
	
	
}
