package neu.edu.controller.user;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCursor;

import neu.edu.dao.GetMessageDAO;
import neu.edu.data.UserSession;

/**
 * Servlet implementation class GetMessages
 */
@WebServlet({ "/GetMessages", "/getMessages" })
public class GetMessages extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetMessages() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserSession userSession = (UserSession) session.getAttribute("userSession");
		
		ServletContext sc = request.getServletContext();
		MongoClient mongoClient = (MongoClient) sc.getAttribute("mongodbClient");
	    		
		GetMessageDAO getMessageDAO = new GetMessageDAO(mongoClient);
		MongoCursor<Document> cursor = getMessageDAO.getMessages();
		if (cursor != null) {
			session.setAttribute("cursor", cursor);
			System.out.println("getmessages");
			request.getRequestDispatcher("/WEB-INF/user/dashboard.jsp").forward(request, response);
		}else {
			System.out.println("No Messages in Mongo: GetMessages");
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
