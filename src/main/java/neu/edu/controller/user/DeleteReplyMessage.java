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
import neu.edu.data.DeleteReplyMessageData;
import neu.edu.data.UserRegistration;
import neu.edu.data.UserSession;
import neu.edu.data.UserRegistration.Role;

/**
 * Servlet implementation class DeleteReplyMessage
 */
@WebServlet({ "/DeleteReplyMessage", "/deleteReplyMessage" })
public class DeleteReplyMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteReplyMessage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		UserSession userSession = (UserSession)session.getAttribute("userSession");	
		
		
		String primaryMessageTimeStamp = request.getParameter("primarymessagestamp");
		String replyMessageTimestamp = request.getParameter("replymessagestamp");
		
		DeleteReplyMessageData deleteReplyMessageData = new DeleteReplyMessageData(primaryMessageTimeStamp, replyMessageTimestamp);
		
		ServletContext application = request.getServletContext();
		MongoClient mongoClient = (MongoClient) application.getAttribute("mongodbClient");
		PostMessageDAO postMessageDAO = new PostMessageDAO(mongoClient);
		
		boolean isReplyMessageDeleted = postMessageDAO.deleteReplyMessage(deleteReplyMessageData);
		if(isReplyMessageDeleted) {
			Role tempRole = UserRegistration.Role.ADMIN;
			if(userSession.getRole().equals(tempRole)) {
				request.getRequestDispatcher("adminDashboard").forward(request, response);
			}else {
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
