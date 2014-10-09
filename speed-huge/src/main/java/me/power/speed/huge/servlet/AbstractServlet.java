/**
 * 
 */
package me.power.speed.huge.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xuehui.miao
 *
 */
public abstract class AbstractServlet extends HttpServlet {

	private static final long serialVersionUID = -1096977684630825785L;
	
	protected String getHtmlHead(String title) {
		StringBuffer head = new StringBuffer();
		head.append("<html><head>");
		head.append("</head><body>");
		head.append("<h2 align=\"left\">"+title+"</h2>");
		return head.toString();
	}
	
	protected String getHtmlEnd() {
		return "</body></html>";
	}
	
	protected void setTextHtmlBaseHead(HttpServletResponse response) {
		response.setContentType("text/html");
		response.setCharacterEncoding( "UTF-8"); 
	}

}
