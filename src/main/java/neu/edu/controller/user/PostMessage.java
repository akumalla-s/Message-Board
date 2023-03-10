package neu.edu.controller.user;

import java.io.IOException;
import java.util.List;

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
import neu.edu.data.UserSession;

/**
 * Servlet implementation class PostMessage
 */
@WebServlet({ "/PostMessage", "/postMessage" })
public class PostMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PostMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		UserSession userSession = (UserSession) session.getAttribute("userSession");

		String username = userSession.getUsername();
		String userMessage = request.getParameter("message");
		//String messageTimestamp = request.getParameter("datetimestamp");

		Message message = new Message(username,  userMessage, "messageTimeStamp");

		ServletContext application = request.getServletContext();
		MongoClient mongoClient = (MongoClient) application.getAttribute("mongodbClient");
		PostMessageDAO postMessageDAO = new PostMessageDAO(mongoClient);

		boolean isMessageSaved = postMessageDAO.create(message);
		if (isMessageSaved) {
			//request.getRequestDispatcher("/WEB-INF/user/dashboard.jsp").forward(request, response);
			request.getRequestDispatcher("userDashboard").forward(request, response);
		}else {
			System.out.println("Message not posted: PostMessage Servlet");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
