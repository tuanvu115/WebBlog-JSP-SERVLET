package news.controller.web;

import java.io.IOException;
import java.util.List;

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

@WebServlet(urlPatterns = {"/the-loai"})
public class CategoryController extends HttpServlet{

	@Inject
	private INewService newService;
	
	@Inject
	private ICategoryService categoryService;
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		NewModel model = FormUtil.toModel(NewModel.class, req);
		Pageble pageble = new PageRequest(model.getPage(),model.getMaxPageItem(),
				new Sorter(model.getSortName(),model.getSortBy()));
		model.setTotalItem(newService.getTotalItem());
		model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getMaxPageItem()));
		List<NewModel> list = newService.findAllByCategoryId(pageble,model.getCategoryId());
		model.setListResult(list);
		req.setAttribute(SystemConstant.MODEL,model);
		req.setAttribute("categories",categoryService.findAll());
		RequestDispatcher rd = req.getRequestDispatcher("/views/web/category/list.jsp");
		rd.forward(req, resp);
	}
}
