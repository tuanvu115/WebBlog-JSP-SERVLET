package news.controller.admin.api;

import java.io.IOException;
import java.sql.Timestamp;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import news.constant.SystemConstant;
import news.model.UserModel;
import news.service.IUserService;
import news.utils.HttpUtil;
import news.utils.SessionUtil;

@WebServlet(urlPatterns = "/api-admin-user")
public class UserAPI extends HttpServlet{

	@Inject
	IUserService userService;
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		UserModel model = HttpUtil.of(req.getReader()).toModel(UserModel.class);
		model.setCreatedBy(SystemConstant.USER);
		model.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		model.setModifiedBy("");
		model.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		model.setUsername(model.getUsername().trim());
		model.setPassword(model.getPassword().trim());
		model = userService.save(model);
		mapper.writeValue(resp.getOutputStream(),model);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		UserModel model= HttpUtil.of(req.getReader()).toModel(UserModel.class);
		model.setModifiedBy(((UserModel)SessionUtil.getInstance().getValue(req, "USERMODEL")).getUsername()); 
		model.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		model.setUsername(model.getUsername().trim());
		model.setPassword(model.getPassword().trim());
		model = userService.update(model);
		mapper.writeValue(resp.getOutputStream(), model);
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		UserModel model = HttpUtil.of(req.getReader()).toModel(UserModel.class);
		userService.delete(model.getIds());
		mapper.writeValue(resp.getOutputStream(), "Success");
	}
	
}
