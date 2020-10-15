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
import news.model.UserModel;
import news.paging.PageRequest;
import news.paging.Pageble;
import news.service.ICategoryService;
import news.service.ICommentService;
import news.service.INewService;
import news.sort.Sorter;
import news.utils.FormUtil;
import news.utils.SessionUtil;

@WebServlet(urlPatterns = {"/new-list","/new-edit","/view-new"})
public class NewController extends HttpServlet{

	@Inject
	private INewService newService;
	
	@Inject
	private ICategoryService categoryService;
	
	@Inject
	private ICommentService commentService;
	
	
	
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "";
		NewModel model = FormUtil.toModel(NewModel.class, req);
		if(model.getType().equals(SystemConstant.EDIT)) {
			req.setAttribute("categories", categoryService.findAll());
			view = "/views/web/new/edit.jsp";
		}else if(model.getType().equals(SystemConstant.LIST)){
			
			Pageble pageble = new PageRequest(model.getPage(),model.getMaxPageItem(),
					new Sorter(model.getSortName(),model.getSortBy()));
			
			model.setTotalItem(newService.getTotalItem());
			model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getMaxPageItem()));
			UserModel userModel = (UserModel)SessionUtil.getInstance().getValue(req, "USERMODEL");
			List<NewModel> list = newService.findAll(pageble,userModel.getId());
			model.setListResult(list);
			view = "/views/web/new/list.jsp";
		}else if(model.getType().equals(SystemConstant.VIEW)){
			req.setAttribute("categories", categoryService.findAll());
			req.setAttribute("comments",commentService.findAllByNewId(model.getId()));
			model = newService.findOne(model.getId());
			view = "/views/web/new/view.jsp";
			
		}
		req.setAttribute(SystemConstant.MODEL,model);
		RequestDispatcher rd = req.getRequestDispatcher(view);
		rd.forward(req, resp);
		
	}
}
