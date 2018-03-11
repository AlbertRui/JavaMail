package me.javamail.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.javamail.domain.User;
import me.javamail.service.UserService;
import me.javamail.service.impl.UserServiceImpl;

/**
 * 实现用户激活业务逻辑的Servlet
 * @author Administrator
 *
 */
public class ActiveServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//接收激活码
		String code = request.getParameter("code");
		//根据激活码查询用户
		UserService userService = new UserServiceImpl();
		try {
			User user = userService.findUserByCode(code);
			if (user != null) {
				//已经查询到，修改用户状态
				user.setState(1);//1代表已经激活
				user.setCode(null);//只允许激活一次
				userService.update(user);
				request.setAttribute("message", "您已经成功激活，请去登录！！！");
			} else {
				//根据激活码没有查询到用户
				request.setAttribute("message", "您的激活码有误，请您重新激活！！！");
			}
			//页面跳转
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	/**
	 * 调用doGet方法
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
