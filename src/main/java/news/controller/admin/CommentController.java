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
import news.model.CommentModel;
import news.paging.PageRequest;
import news.paging.Pageble;
import news.service.ICommentService;
import news.service.IUserService;
import news.sort.Sorter;
import news.utils.FormUtil;

@WebServlet(urlPatterns = {"/admin-comment"})
public class CommentController extends HttpServlet{

	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ICommentService commentService;
	
	@Inject
	private IUserService userService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CommentModel model = FormUtil.toModel(CommentModel.class, req);
		String type = model.getType();
		String view = "";
		if(model != null && model.getType().equals(SystemConstant.LIST)) {
			Pageble pageble = new PageRequest(model.getPage(),model.getMaxPageItem(),
					new Sorter(model.getSortName(),model.getSortBy()));
			model.setTotalItem(commentService.getTotalItem());
			model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getMaxPageItem()));
			model.setListResult(commentService.findAll(pageble));
			
			view = "/views/admin/comment/list.jsp";
		}else if(model != null &&  model.getType().equals(SystemConstant.VIEW)) {
			if(model.getId() != null) {
				model = commentService.findOne(model.getId());
			}
			model.setUser(userService.findOne(model.getUserId()));
			view = "/views/admin/comment/view.jsp";
		}
		model.setType(type);
		req.setAttribute("model",model);
		RequestDispatcher rd = req.getRequestDispatcher(view);
		rd.forward(req, resp);
	}
}
