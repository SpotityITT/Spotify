package Servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DB.PlayListDAO;
import DB.UserDAO;

/**
 * Servlet implementation class playlistServlet
 */
@WebServlet("/playlistServlet")
public class playlistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String playlist = request.getParameter("playlistName");
		System.out.println(playlist);
		int playlistId = PlayListDAO.getInstance().getPlaylistId(playlist);
		int songId = Integer.parseInt(request.getParameter("songId"));
		System.out.println("Hello???????" +songId);
		try {
			PlayListDAO.getInstance().addSongInPlayList(playlistId, songId);
		} catch (SQLException e) {
			System.out.println("unsucessful added song.");
		}
		
		String jspFile = "user.jsp"; 
		
		RequestDispatcher view = request.getRequestDispatcher(jspFile);
		view.forward(request, response);
		
	}

}
