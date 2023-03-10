package neu.edu.controller.user;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mongodb.MongoClient;

import neu.edu.dao.PostMessageDAO;
import neu.edu.data.Message;
import neu.edu.data.UserRegistration;
import neu.edu.data.UserRegistration.Role;
import neu.edu.data.UserSession;

/**
 * Servlet implementation class DeleteMessage
 */
@WebServlet({ "/DeleteMessage", "/deleteMessage" })
public class DeleteMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteMessage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String messageTimestamp = request.getParameter("messagestamp");
		HttpSession session = request.getSession();
		UserSession userSession = (UserSession)session.getAttribute("userSession");	
		Message message = new Message(messageTimestamp);
		
		ServletContext application = request.getServletContext();
		MongoClient mongoClient = (MongoClient) application.getAttribute("mongodbClient");
		PostMessageDAO postMessageDAO = new PostMessageDAO(mongoClient);
		
		boolean isMessageDeleted = postMessageDAO.deleteMessage(message);
		if(isMessageDeleted) {
			Role tempRole = UserRegistration.Role.ADMIN;
			//System.out.println(tempRole);
			if(userSession.getRole().equals(tempRole)) {
				//System.out.println("adminDash");
				request.getRequestDispatcher("adminDashboard").forward(request, response);
			}else {
				//System.out.println("userdash");
				request.getRequestDispatcher("userDashboard").forward(request, response);
			}
			
		}else {
			System.out.println("Message not deleted: DeleteMessage Servlet");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
