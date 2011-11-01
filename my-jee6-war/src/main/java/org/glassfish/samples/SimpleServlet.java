package org.glassfish.samples;

import javax.ejb.EJB;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.PrintWriter;
import java.io.IOException;

/**
 * Hello world!
 */
@WebServlet(urlPatterns={"/SimpleServlet"})
public class SimpleServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB SimpleEJB bean;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>Serving at: " + request.getContextPath() + "</h2>");
        out.println("<h2>Invoking EJB: " + bean.sayHello("Duke") + "</h2>");
        out.println("</body></html>");
    }
}
