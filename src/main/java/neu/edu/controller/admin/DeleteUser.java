package neu.edu.controller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.MongoClient;

import neu.edu.dao.DeleteUserDAO;
import neu.edu.dao.PostMessageDAO;

/**
 * Servlet implementation class DeleteUser
 */
@WebServlet({ "/DeleteUser", "/deleteUser" })
public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		
		//Delete Usermessages
		ServletContext application = request.getServletContext();
		MongoClient mongoClient = (MongoClient) application.getAttribute("mongodbClient");
		
		PostMessageDAO postMessageDAO = new PostMessageDAO(mongoClient);
		boolean isMessagesDeletedMongo = postMessageDAO.deleteUserMessagesAdmin(username);
		
		DeleteUserDAO deleteUserDAO = new DeleteUserDAO();
		boolean isUserDeleted = deleteUserDAO.deleteUser(username);
		
		if(isUserDeleted && isMessagesDeletedMongo) {
			RequestDispatcher rd = request.getRequestDispatcher("viewUsers");
			rd.forward(request, response);
		}else {
			System.out.println("Delete User Servlet");
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
