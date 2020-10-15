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
import news.model.CategoryModel;
import news.model.UserModel;
import news.service.ICategoryService;
import news.utils.HttpUtil;
import news.utils.SessionUtil;

@WebServlet(urlPatterns = {"/api-admin-category"})
public class CategoryAPI extends HttpServlet{

	@Inject
	private ICategoryService categoryService;
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		CategoryModel model = HttpUtil.of(req.getReader()).toModel(CategoryModel.class);
		model.setCreatedBy(SystemConstant.USER);
		model.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		model.setModifiedBy("");
		model.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		model = categoryService.save(model);
		mapper.writeValue(resp.getOutputStream(),model);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		CategoryModel model = HttpUtil.of(req.getReader()).toModel(CategoryModel.class);
		model.setModifiedBy(((UserModel)SessionUtil.getInstance().getValue(req, "USERMODEL")).getUsername()); 
		model.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		model = categoryService.update(model);
		mapper.writeValue(resp.getOutputStream(),model);
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		CategoryModel model = HttpUtil.of(req.getReader()).toModel(CategoryModel.class);
		categoryService.delete(model.getIds());
		mapper.writeValue(resp.getOutputStream(),model);
	}
	
	
}
