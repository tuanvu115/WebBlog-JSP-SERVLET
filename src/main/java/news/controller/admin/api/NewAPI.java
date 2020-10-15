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

import news.model.NewModel;
import news.model.UserModel;
import news.service.INewService;
import news.utils.HttpUtil;
import news.utils.SessionUtil;

@WebServlet(urlPatterns = {"/api-admin-new"})
public class NewAPI extends HttpServlet{

	
	@Inject
	private INewService newService;
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		NewModel model = HttpUtil.of(req.getReader()).toModel(NewModel.class);
		UserModel userModel = (UserModel)SessionUtil.getInstance().getValue(req, "USERMODEL");
		model.setUserId(userModel.getId());
		model.setCreatedBy(userModel.getUsername());
		model.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		model.setModifiedBy("");
		model.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		model = newService.save(model);
		mapper.writeValue(resp.getOutputStream(),model);
	}
	
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		NewModel model = HttpUtil.of(req.getReader()).toModel(NewModel.class);
		model.setModifiedBy( ((UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL")).getUsername());
		model.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		model = newService.update(model);
		mapper.writeValue(resp.getOutputStream(),model);
		
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		NewModel model = HttpUtil.of(req.getReader()).toModel(NewModel.class);
		newService.delete(model.getIds());
		mapper.writeValue(resp.getOutputStream(),"Success");
	}
	
}
