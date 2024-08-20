package com.example.estore.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class FrontController
 */
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
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestType = request.getParameter("type"); //categories
		
		//creates and empty RequestDispatcher for future forwarding
		RequestDispatcher dispatcher = null;
		
		if ("createCategory".equals(requestType) || "updateCategory".equals(requestType)) {
			dispatcher = request.getRequestDispatcher("categories");
		}else if ("login".equals(requestType)) {
			dispatcher = request.getRequestDispatcher("login");
		} else if ("home".equals(requestType)) {
			dispatcher = request.getRequestDispatcher("home");
		} else if ("categories".equals(requestType)) {
			dispatcher = request.getRequestDispatcher("categories");
		} else {
			dispatcher = request.getRequestDispatcher("error");
		}
		dispatcher.forward(request, response);
	}

}
