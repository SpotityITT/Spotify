package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DB.UserDAO;
import Exceptions.InvalidEmailException;
import Exceptions.InvalidMobileNumberException;
import Exceptions.InvalidNameException;
import Exceptions.InvalidPasswordException;
import Exceptions.InvalidUserLoginException;
import Exceptions.InvalidUserNameException;
import Model.Platform;
import Model.User;


/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/register")
public class registserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		String name = request.getParameter("name");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String city = request.getParameter("city");
		String gender = request.getParameter("gender");
	
//		Date birthday = null;
//		
//		try {
//			birthday = new SimpleDateFormat("mm/dd/yyyy").parse(request.getParameter("birthday"));
//		} catch (ParseException e) {
//			System.out.println("Couldn't parse.");
//		}
//		
		String mobileNumber = request.getParameter("mobileNumber");
		
		//some problems here with the parametes
		
		User user = null;
			try {
				user = new User(name, username, password, email, city, gender,null, mobileNumber);
			} catch (InvalidUserLoginException e) {
				System.out.println(e.getMessage());
			} catch (InvalidEmailException e) {
				System.out.println(e.getMessage());
			} catch (InvalidPasswordException e) {
				System.out.println(e.getMessage());
			} catch (InvalidMobileNumberException e) {
				System.out.println(e.getMessage());
			} catch (InvalidNameException e) {
				System.out.println(e.getMessage());
			} catch (InvalidUserNameException e) {
				System.out.println(e.getMessage());
			}
		if (!UserDAO.getInstance().saveUser(user)) {
			System.out.println("SMT wrong.");
			RequestDispatcher view = request.getRequestDispatcher("somFail.html"); //TODO
			view.forward(request, response);
		}
		
		RequestDispatcher view2 = request.getRequestDispatcher("index.html");
		view2.forward(request, response);
	}

}
