/**
 * 
 */
package me.power.speed.huge.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xuehui.miao
 *
 */
public class SystemInitServlet extends HttpServlet {

	private static final long serialVersionUID = 3034643551790391126L;
	
	@Override
	public void destroy() {
		super.destroy();
	}
	
	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.service(req, resp);
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	@Override
	public void init() throws ServletException {
		System.out.println("init system servlet.");
		super.init();
	}
}
