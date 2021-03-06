package ServletLauncher;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.servlets.DefaultServlet;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import Beans.User;
import Beans.UserReimbursement;
import ReimbursementDAO.DatabaseDAO;

public class ApproveServlet extends DefaultServlet {
	private DatabaseDAO dao = new DatabaseDAO();
    private Logger log = Logger.getRootLogger(); 

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {	
		super.service(request, response);
		response.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
       response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
       response.addHeader("Access-Control-Allow-Headers","Origin, Methods, Credentials, X-Requested-With, Content-Type, Accept");
       response.addHeader("Access-Control-Allow-Credentials", "true");
       response.setContentType("application/json");
	}
	
	


	// the information they want to receive 
		@Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws IOException, ServletException {
			
			
	        String json = request.getReader().lines().reduce((acc, cur) -> acc + cur).get();
			log.trace("json " + json);
			ObjectMapper om = new ObjectMapper();
			UserReimbursement credentials = (UserReimbursement) om.readValue(json, UserReimbursement.class);
			log.trace(credentials);
			UserReimbursement u = dao.UpdateStatus(credentials);
			
			if (u != null) {
				HttpSession sess = request.getSession();
				sess.setAttribute("user", u);
				String respjson = om.writeValueAsString(u);
				response.getWriter().write(respjson);
			} else {
				response.setStatus(401);
			}
		}
	        
	        
		}	

