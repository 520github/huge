/**
 * 
 */
package me.power.speed.huge.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.power.speed.common.stream.StreamUtil;

/**
 * @author xuehui.miao
 *
 */
public class ThreadStackTraceServlet extends AbstractServlet {

	private static final long serialVersionUID = -7464881306097427097L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter out = null;
		try {
			this.setTextHtmlBaseHead(resp);
			out = resp.getWriter();
			out.println(this.getHtmlHead("thread stack track message"));
			out.println(this.getThreadStackTrace());
			out.println(this.getHtmlEnd());
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			StreamUtil.closeWrite(out);
		}
	}
	
	private String getThreadStackTrace() {
		StringBuffer stackTrace = new StringBuffer();
		Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();
		for(Thread thread : map.keySet()) {
			if(thread.equals(Thread.currentThread())) {
				continue;
			}
			StackTraceElement[] elements = map.get(thread);
			stackTrace.append("<ul>");
			stackTrace.append("<h5>");
			stackTrace.append("\n thread name:" + thread.getName() + " \n");
			stackTrace.append("</h5>");
			for(StackTraceElement element : elements) {
				stackTrace.append("<li>");
				stackTrace.append(element + " \n");
				stackTrace.append("</li>");
			}
			stackTrace.append("</ul>");
		}
		
		return stackTrace.toString();
	}
	
}
