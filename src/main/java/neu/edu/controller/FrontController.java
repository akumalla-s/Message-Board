package neu.edu.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import neu.edu.data.UserRegistration;
import neu.edu.data.UserSession;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("/controller")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		RequestDispatcher rd = null;
		
		HttpSession session = request.getSession();
		UserSession userSession = (UserSession) session.getAttribute("userSession");
		
		UserRegistration.Role role = userSession.getRole();
		
		String currentPage = userSession.getCurrentPage();
		//System.out.println(currentPage);
		
		String newAction = null;
		
		//System.out.println("Calling login");
		String dispatcher = "/login";
		
		switch (role) {
		case ADMIN:
			if (currentPage.equals("adminDashboard")) {
				if(action == null) {
					dispatcher = "adminDashboard";
				}else if(action.equals("viewUsers")) {
					userSession.setCurrentPage("viewUsers");
					dispatcher = "viewUsers";
				}else if(action.equals("deleteMessage")) {
					dispatcher = "deleteMessage";
				}else if(action.equals("deleteReplyMessage")) {
					dispatcher = "deleteReplyMessage";
				}
			}else if(currentPage.equals("viewUsers")) {
				if(action == null) {
					dispatcher = "viewUsers";
				}else if(action.equals("deleteUser")) {
					dispatcher = "deleteUser";
				}else if(action.equals("adminDashboard")) {
					userSession.setCurrentPage("adminDashboard");
					dispatcher = "controller";
				}
			}
			
			rd = request.getRequestDispatcher(dispatcher);
			rd.forward(request, response);
			
			break;
			
		case USER:
			if(currentPage.equals("userDashboard")) {
				if(action == null) {
					dispatcher = "userDashboard";
				}else if(action.equals("profile")) {
					userSession.setCurrentPage("userProfile");
					dispatcher = "userProfile";
				}else if(action.equals("postMessage")) {
					//userSession.setCurrentPage("postMessage");
					dispatcher = "postMessage";
				}else if(action.equals("addReply")) {
					dispatcher = "addReply";
				}else if(action.equals("deleteMessage")) {
					dispatcher = "deleteMessage";
				}else if(action.equals("deleteReplyMessage")) {
					dispatcher = "deleteReplyMessage";
				}
			}else if(currentPage.equals("userProfile")) {
				action = request.getParameter("page");
				request.setAttribute("newAction", action);
				if(action == null) {
					userSession.setCurrentPage("userDashboard");
				}else if(action.equals("profile") || action.equals("editProfile") || action.equals("editProfilePassword")) {
					dispatcher = "userProfile";
				}
			}
			
			rd = request.getRequestDispatcher(dispatcher);
			rd.forward(request, response);
			break;

		default:
			System.out.println("Session invalidated");
			session.invalidate();
			response.sendRedirect("index.jsp");
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
