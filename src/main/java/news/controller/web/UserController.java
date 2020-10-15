package news.controller.web;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import news.model.UserModel;
import news.service.IUserService;
import news.utils.SessionUtil;

@WebServlet(urlPatterns = { "/user-view" })
public class UserController extends HttpServlet {

	@Inject
	private IUserService userService;

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if(action != null) {
			String message = req.getParameter("message");
			String alert   = req.getParameter("alert");
			req.setAttribute("action", action);
			req.setAttribute("message", message);
			req.setAttribute("alert", alert);
		}
		
		RequestDispatcher rd = req.getRequestDispatcher("/views/web/user/list.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String newPassword = req.getParameter("password").trim();
		String againPassword = req.getParameter("againPassword").trim();
		String presentPassword = req.getParameter("presentPassword").trim();
		UserModel user = ((UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL"));
		if(presentPassword.equals(user.getPassword())) {
			if(newPassword.equals(againPassword)) {
				
				 userService.changePassword(user.getId(),newPassword);
				 resp.sendRedirect(req.getContextPath()+"/user-view?action=changePassword&message=Doi_mat_khau_thanh_cong&alert=success");
			}else {
				resp.sendRedirect(req.getContextPath()+"/user-view?action=changePassword&message=Mat_khau_phai_giong_nhau&alert=warning");
			}
		}else {
			resp.sendRedirect(req.getContextPath()+"/user-view?action=changePassword&message=Mat_khau_khong_dung&alert=warning");
		}
		
		
		

	}

}
