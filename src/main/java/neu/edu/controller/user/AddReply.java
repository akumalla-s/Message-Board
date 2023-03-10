package neu.edu.controller.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mongodb.MongoClient;

import neu.edu.dao.PostMessageDAO;
import neu.edu.data.ReplyMessage;
import neu.edu.data.UserSession;

/**
 * Servlet implementation class AddReply
 */
@WebServlet({ "/AddReply", "/addReply" })
public class AddReply extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddReply() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserSession userSession = (UserSession) session.getAttribute("userSession");
		
		String messageId = request.getParameter("primaryMessageId");
		String username = userSession.getUsername();
		String userMessage = request.getParameter("replyMessage");
		
		ReplyMessage message = new ReplyMessage(messageId, username, userMessage);
		
		ServletContext application = request.getServletContext();
		MongoClient mongoClient = (MongoClient) application.getAttribute("mongodbClient");
		PostMessageDAO postMessageDAO = new PostMessageDAO(mongoClient);
		
		boolean isReplySaved = postMessageDAO.reply(message);
		
		if (isReplySaved) {
			request.getRequestDispatcher("userDashboard").forward(request, response);
		}else {
			System.out.println("Reply Message not stored: AddReply Servlet");
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
