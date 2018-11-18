package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
		
      HttpSession s = ((HttpServletRequest) req).getSession(false);

        if (s != null) {
            s.invalidate();
        }
        
    	req.setAttribute("message", "👋 Logout realizado. ");
    	req.setAttribute("type","success");
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
		rd.forward(req, resp);
        
	}

}
