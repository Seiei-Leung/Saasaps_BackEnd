package top.seiei.saasaps.interceptors;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import top.seiei.saasaps.bean.User;
import top.seiei.saasaps.common.Const;
import top.seiei.saasaps.common.ResponseCode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 获取请求的uri:去除http:localhost:8080这部分剩下的
		String uri = request.getRequestURI();
		// 如果是登陆接口不拦截
		if (uri.contains("/login") || uri.contains("/signout")) {
			return true;
		}
		// 拦截后根据 session 对象，检查用户是否已经登陆
		User user = (User) request.getSession().getAttribute(Const.CURRENT_USER);
		if (user != null) {
			return true;
		}
		// 允许跨域，但小心前端有些框架进行跨域请求的是否默认不带cookie，导致每次申请后端都归纳到不同的 session
//		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//		response.setHeader("Access-Control-Max-Age", "3600");
//		response.setHeader("Access-Control-Allow-Credentials", "true");
//		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
//		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		
		// 设置 json 响应对象返回
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = null;
		out = response.getWriter();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", ResponseCode.NEED_LOGIN.getCode());
		jsonObject.put("msg", "用户尚未登录或登录已过期请重新登录");
		out.append(jsonObject.toJSONString());
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
