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

import neu.edu.dao.EditProfileDAO;
import neu.edu.dao.PostMessageDAO;
import neu.edu.data.UserSession;
import neu.edu.dao.EditProfileDAO;

/**
 * Servlet implementation class UserProfile
 */
@WebServlet({ "/UserProfile", "/userProfile" })
public class UserProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserProfile() {
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

		request.setAttribute("username", userSession.getUsername());
		request.setAttribute("email", userSession.getEmail());
		request.getRequestDispatcher("/WEB-INF/user/profile.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = (String) request.getAttribute("newAction");

		HttpSession session = request.getSession();
		UserSession userSession = (UserSession) session.getAttribute("userSession");

		String oldusername = userSession.getUsername();

		if (action.equals("editProfile")) {
			String username = request.getParameter("username");
			String email = request.getParameter("email");
			
			//Update username in Mongo Database
			ServletContext application = request.getServletContext();
			MongoClient mongoClient = (MongoClient) application.getAttribute("mongodbClient");
			PostMessageDAO postMessageDAO = new PostMessageDAO(mongoClient);
			
			String oldUserName = userSession.getUsername();
			String newUserName = username;
			boolean isMongoDBUserNameUpdated = postMessageDAO.updateUserName(oldUserName, newUserName);

			EditProfileDAO editProfileDAO = new EditProfileDAO();
			
			boolean isUpdated = editProfileDAO.updateUserData(username, email, oldusername);
			if (isUpdated && isMongoDBUserNameUpdated) {
				userSession.setUsername(username);
				userSession.setEmail(email);
				doGet(request, response);
			} else {
				postMessageDAO.updateUserName(newUserName, oldusername);
				String errorMsg = "Error: Username already exists";
				request.setAttribute("errorMsg", errorMsg);
				request.setAttribute("username", userSession.getUsername());
				request.setAttribute("email", userSession.getEmail());
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/user/profile.jsp");
				rd.forward(request, response);

			}
		} else if (action.equals("editProfilePassword")) {
			String password = request.getParameter("password");
			EditProfileDAO editProfileDAO = new EditProfileDAO();

			boolean isPasswordUpdated = editProfileDAO.updateUserPassword(password, oldusername);

			if (isPasswordUpdated) {
				session.invalidate();
				response.sendRedirect("index.jsp");

			}
		}
	}

}
