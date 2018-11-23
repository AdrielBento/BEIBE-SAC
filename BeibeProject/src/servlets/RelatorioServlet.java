package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Login;
import dao.ConnectionFactory;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
	
@WebServlet("/Relatorio")
public class RelatorioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
	      
		HttpSession session = ((HttpServletRequest) req).getSession(true);
		Login lb = (Login) session.getAttribute("login");

		if (lb == null) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
			rd.forward(req, resp);
		} else if (!lb.getTipo().equals("G")) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/permissao.jsp");
			rd.forward(req, resp);
		}
        
        
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        String relatorio  = String.valueOf(req.getParameter("relatorio"));
        String nomeRelatorio = "";
        HashMap params = new HashMap();

        
        switch(relatorio){
            
            case "atendimentoData":
                    
                String[] paramsData = new String[2];
                Date[] objectsDate = new Date[2];
                String inicio  = String.valueOf(req.getParameter("start"));
                String fim = String.valueOf(req.getParameter("end"));
                fim = fim.trim();
                inicio = inicio.trim();
                
        
                try {
                    objectsDate[0] = format.parse(inicio);
                    objectsDate[1] = format.parse(fim);
                
                } catch (ParseException ex) { }
       
               
                params.put("DT_INICIO",objectsDate[0]);
                params.put("DT_FIM",objectsDate[1]);                
                nomeRelatorio = "/RelatorioAtendimentoData.jasper";
            break;
            
            case "maisReclamados":                
                nomeRelatorio = "/RelatorioMaisReclamados.jasper";
                break;    
            case "funcionarios":                  
                nomeRelatorio = "/RelatorioFuncionarios.jasper";
                break;
            case "paramReclamacoes":
            		
            	 String status = String.valueOf(req.getParameter("status"));
            	 if(status.equals("T")) {
            		 nomeRelatorio = "/RelatorioTodasReclamacoes.jasper";
            	 }else {
            		 params.put("STATUS",status);   
            		 nomeRelatorio = "/RelatorioReclamacaoParam.jasper";            		 
            	 }
               break;
        
            default:
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/relatorios.jsp");
                    rd.forward(req, resp);
                break;            
        }    
        
        
        if(!nomeRelatorio.isEmpty()) {       	
        
	        
	        try(Connection con = ConnectionFactory.getConnection()){                
	            
	            String jasper = ((HttpServletRequest) req).getContextPath() + nomeRelatorio;
	            String host = "http://" + req.getServerName() + ":" + req.getServerPort();
	            URL jasperURL = new URL(host + jasper);        
	
	           
	            byte[] bytes = JasperRunManager.runReportToPdf(jasperURL.openStream(), params, con);
	
	            if (bytes != null) {           
	                resp.setContentType("application/pdf");
	                ServletOutputStream ops = resp.getOutputStream();
	                ops.write(bytes);
	                ops.flush();
	                ops.close();
	            }
	        }catch(SQLException e) {
	            
	            req.setAttribute("msg", "Erro de conexão ou query: " + e.getMessage());
	        	StringWriter writer = new StringWriter();				
				e.printStackTrace(new PrintWriter(writer));			
				req.setAttribute("ExceptionMessage", e.getMessage());
				req.setAttribute("stackTrace",writer.toString());			
	            RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/erro.jsp");
	            rd.forward(req, resp);
	            
	        }catch(JRException e) {
	            req.setAttribute("msg", "Erro no Jasper : " + e.getMessage());
	        	StringWriter writer = new StringWriter();				
				e.printStackTrace(new PrintWriter(writer));			
				req.setAttribute("ExceptionMessage", e.getMessage());
				req.setAttribute("stackTrace",writer.toString());
	            RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/erro.jsp");
	            rd.forward(req, resp);            
	        }
        
        }
	}
	

}
