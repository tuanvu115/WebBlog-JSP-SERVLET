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
import news.model.RoleModel;
import news.model.UserModel;
import news.service.IRoleService;
import news.utils.HttpUtil;
import news.utils.SessionUtil;

@WebServlet(urlPatterns = {"/api-admin-role"})
public class RoleAPI extends HttpServlet{
	
	@Inject
	private IRoleService roleService;
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		RoleModel model = HttpUtil.of(req.getReader()).toModel(RoleModel.class);
		model.setCreatedBy(SystemConstant.USER);
		model.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		model.setModifiedBy("");
		model.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		
		model = roleService.save(model);
		mapper.writeValue(resp.getOutputStream(),model);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		RoleModel model = HttpUtil.of(req.getReader()).toModel(RoleModel.class);
		model.setModifiedBy(((UserModel)SessionUtil.getInstance().getValue(req, "USERMODEL")).getUsername()); 
		model.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		
		model = roleService.update(model);
		mapper.writeValue(resp.getOutputStream(),model);
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		RoleModel model = HttpUtil.of(req.getReader()).toModel(RoleModel.class);
		roleService.delete(model.getIds());
		mapper.writeValue(resp.getOutputStream(),model);
	}
}
