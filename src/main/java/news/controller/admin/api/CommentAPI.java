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
import news.model.CommentModel;
import news.model.UserModel;
import news.service.ICommentService;
import news.utils.HttpUtil;
import news.utils.SessionUtil;

@WebServlet(urlPatterns = {"/api-admin-comment"})
public class CommentAPI extends HttpServlet{
	
	@Inject
	private ICommentService commentService;

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		CommentModel model = HttpUtil.of(req.getReader()).toModel(CommentModel.class);
		model.setCreatedBy(SystemConstant.USER);
		model.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		model.setModifiedBy("");
		model.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		
		model = commentService.save(model);
		mapper.writeValue(resp.getOutputStream(),model);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		CommentModel model = HttpUtil.of(req.getReader()).toModel(CommentModel.class);
		model.setModifiedBy(((UserModel)SessionUtil.getInstance().getValue(req, "USERMODEL")).getUsername()); 
		model.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		
		model = commentService.update(model);
		mapper.writeValue(resp.getOutputStream(),model);
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		CommentModel model = HttpUtil.of(req.getReader()).toModel(CommentModel.class);
		commentService.delete(model.getIds());
		mapper.writeValue(resp.getOutputStream(),model);
	}
	
}
