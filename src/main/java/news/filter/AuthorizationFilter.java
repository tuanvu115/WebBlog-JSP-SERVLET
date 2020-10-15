package news.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import news.constant.SystemConstant;
import news.model.UserModel;
import news.utils.SessionUtil;

public class AuthorizationFilter implements Filter{

	private ServletContext myContext;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.myContext = filterConfig.getServletContext();		
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest  req  = (HttpServletRequest)servletRequest;
		HttpServletResponse resp = (HttpServletResponse)servletResponse;
		if(req.getServletPath().startsWith("/admin")) {
			UserModel model =(UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL");
			if (model != null) {
                if (model.getRole().getCode().equals(SystemConstant.ADMIN)) {
                    filterChain.doFilter(servletRequest, servletResponse);
                } else if (model.getRole().getCode().equals(SystemConstant.USER)) {
                    resp.sendRedirect(req.getContextPath()+"/dang-nhap?action=login&message=not_permission&alert=danger");
                }
            } else {
                resp.sendRedirect(req.getContextPath()+"/dang-nhap?action=login&message=not_login&alert=danger");
            }
        }else if(req.getServletPath().startsWith("/new") || req.getServletPath().startsWith("/user")) {
        	UserModel model = (UserModel)SessionUtil.getInstance().getValue(req, "USERMODEL");
        	if(model != null) {
        		filterChain.doFilter(servletRequest, servletResponse);
        	}else {
        		resp.sendRedirect(req.getContextPath()+"/dang-nhap?action=login&message=not_login&alert=danger");
        	}
        }
		else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
		}
		

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
