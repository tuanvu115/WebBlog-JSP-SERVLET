package news.controller.web;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import news.constant.SystemConstant;
import news.model.UserModel;
import news.service.ICategoryService;
import news.service.INewService;
import news.service.IUserService;
import news.utils.FormUtil;
import news.utils.SessionUtil;

@WebServlet(urlPatterns = {"/trang-chu","/dang-nhap","/dang-ky","/thoat"})
public class HomeController extends HttpServlet{

	@Inject
	private IUserService userService;
	
	@Inject
	private ICategoryService categoryService;
	
	@Inject
	private INewService newService;
	
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		String view = "";
		if(action != null && action.equals("login")) {
			String message = req.getParameter("message");
			String alert = req.getParameter("alert");
			req.setAttribute("message", message);
			req.setAttribute("alert", alert);
			view = "/views/login.jsp";
			
		}else if(action != null && action.equals("logout")){
			SessionUtil.getInstance().removeValue(req,"USERMODEL");
			resp.sendRedirect(req.getContextPath()+"/trang-chu");
			return;
			
		}
		else {
			req.setAttribute("categories",categoryService.findAll());
			req.setAttribute("newsTopSix",newService.findTopSix());
			req.setAttribute("newTopInteractive", newService.findTopInteractive());
			view = "/views/web/home.jsp";
		}
		RequestDispatcher rd = req.getRequestDispatcher(view);
		rd.forward(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		UserModel userModel = FormUtil.toModel(UserModel.class, req);
		String temp[] = userModel.getUsername().split("@");
		userModel.setUsername(temp[0].trim());
		UserModel user = userService.findUserAndPassAndStatus(userModel.getUsername(),userModel.getPassword());
		if(user != null && user.getStatus() == 1) {
			SessionUtil.getInstance().putValue(req,"USERMODEL", user);
			if(user.getRole().getCode().equals(SystemConstant.USER)) {
				resp.sendRedirect(req.getContextPath()+"/trang-chu");
			}
			else if(user.getRole().getCode().equals(SystemConstant.ADMIN)) {
				resp.sendRedirect(req.getContextPath()+"/admin-home");
			}
		}else {
			resp.sendRedirect(req.getContextPath()+"/dang-nhap?action=login&message=username_password_invalid_or_user_disable&alert=danger");
		}
	}
	
	
	
	
	
	
	

}
