package neu.edu.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import neu.edu.dao.UserRegistrationDAO;
import neu.edu.data.UserRegistration;

/**
 * Servlet implementation class Registration
 */
@WebServlet({ "/Registration", "/registration" })
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Registration() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/registration.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String mobile = request.getParameter("mobile");
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		UserRegistration userRegistration = new UserRegistration(firstname, lastname,mobile, email,username,password, UserRegistration.Role.USER);
		UserRegistrationDAO userRegistrationDAO = new UserRegistrationDAO();
		boolean isUserCreated = userRegistrationDAO.createUser(userRegistration);
		
		String errorMsg = "Error: Username already exists";
		
		if(isUserCreated) {
			SendRegistrationEmail.sendEmail(email);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/registrationStatus.jsp");
			rd.forward(request, response);
		}
		else {
			request.setAttribute("errorMsg", errorMsg);
			request.setAttribute("userRegistration", userRegistration);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/registrationError.jsp");
			rd.forward(request, response);
		}
	}

}
