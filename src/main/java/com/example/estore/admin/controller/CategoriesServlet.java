package com.example.estore.admin.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.example.estore.admin.dao.CategoriesDAO;
import com.example.estore.admin.model.Categories;

/**
 * Servlet implementation class CategoriesServlet
 */
public class CategoriesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// fields
	private CategoriesDAO cDao = new CategoriesDAO();

	// constructors
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CategoriesServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	// other methods
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String categoryIdParam = request.getParameter("id");

		// validation for if id is null or empty
		if (categoryIdParam == null || categoryIdParam.isEmpty()) {
			request.setAttribute("errorMessage", "Missing category ID");
			request.getRequestDispatcher("/categories-admin.jsp").forward(request, response);
			return;
		}

		long categoryId;
		// changing from String to Whole number
		try {
			categoryId = Long.parseLong(categoryIdParam);
		} catch (NumberFormatException e) {
			request.setAttribute("errorMessage", "Invalid category ID");
			request.getRequestDispatcher("/categoies-admin.jsp").forward(request, response);
			return;
		}

		// create a categories object as a placeholder
		Categories object = new Categories();

		// set object ID using ID from client
		object.setCategoryId((int) categoryId);

		try {
			// Actual SEND to my MySQL DB
			Categories categoryReadFromSql = cDao.get(categoryId);

			if (categoryReadFromSql != null) {
				System.out.println(categoryReadFromSql);
			}

			if (categoryReadFromSql == null) {
				request.setAttribute("errorMessage", "Category not found");
				request.getRequestDispatcher("/categories-admin.jsp").forward(request, response);
			}

			request.setAttribute("successMessage", "Category retrieved successfully!");
			request.setAttribute("categoryId", categoryReadFromSql.getCategoryId());
			request.setAttribute("categoryName", categoryReadFromSql.getCategoryName());

			// Forward the request to the JSP for rendering the view
			RequestDispatcher dispatcher = request.getRequestDispatcher("/categories-admin.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			System.out.println("SQL Exception occured: ");
			e.printStackTrace();
			request.setAttribute("errorMessage", "Database error");
			request.getRequestDispatcher("/categories-admin.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String type = request.getParameter("type");

		if (("createCategory".equals(type))) {

			Categories categories = loadObject(request);

			try {
				// Actual SAVE to my MySQL DB here
				cDao.save(categories);
				// Send a success response
				// response.setContentType("text/html");
				// response.getWriter().println("Categories object added successfully!");

				request.setAttribute("successMessage", "Category object added successfully!");
				request.setAttribute("categoryName", categories.getCategoryName());
				

				// Forward the request to the JSP for rendering the view
				RequestDispatcher dispatcher = request.getRequestDispatcher("/categories-admin.jsp");
				dispatcher.forward(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				throw new ServletException("Database error", e);
				
			}

		} else if ("updateCategory".equals(type)) {

			// loadObject method populates a categories object with request parameters
			Categories categoriesUpdate = loadObject(request); // a loaded category object

			try {
				//the appropriate to UPDATE the category object created above
				cDao.update(categoriesUpdate);
				System.out.println(categoriesUpdate);
				
				//setting an additional attribute for the end user
				request.setAttribute("successMessage", "Category object Updated successfully!");

				// Forward the request to the JSP for rendering the view
				RequestDispatcher dispatcher = request.getRequestDispatcher("/categories-admin.jsp");
				dispatcher.forward(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("update failure!: ");
				e.printStackTrace();
				
			}
			
		}
		// java.sql.Date sqlDate = java.sql.Date.valueOf(dateString);
		// categories.setA
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Extract and validate parameters from the request
		String idParam = request.getParameter("id");
		if (idParam == null || idParam.isEmpty()) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing category ID");
			return;
		}

		long id;
		try {
			id = Long.parseLong(idParam);
		} catch (NumberFormatException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid category ID");
			return;
		}

		String categoryName = request.getParameter("categoryName");
		String categoryDesc = request.getParameter("categoryDesc");
		String categoryImgUrl = request.getParameter("categoryImgUrl");
		String activeIdParam = request.getParameter("activeId");
		String addedOnParam = request.getParameter("addedOn");

		if (categoryName == null || categoryDesc == null || categoryImgUrl == null || activeIdParam == null
				|| addedOnParam == null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing category data");
			return;
		}

		Integer activeId;
		try {
			activeId = Integer.parseInt(activeIdParam);
		} catch (NumberFormatException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid active ID");
			return;
		}

		LocalDate addedOn;
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
			addedOn = LocalDate.parse(addedOnParam, formatter);
		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid date format");
			return;
		}

		// Create and populate the Categories object
		Categories category = new Categories();
		category.setCategoryId((int) id);
		category.setCategoryName(categoryName);
		category.setCategoryDescription(categoryDesc);
		category.setCategoryImageUrl(categoryImgUrl);
		category.setActive(activeId);
		category.setAddedOn(addedOn);

		try {
			cDao.update(category);

			// Send a success response
			response.setContentType("application/json");
			response.getWriter().println("{\"message\": \"Category updated successfully!\"}");
		} catch (Exception e) {
			// Handle exceptions and send an error response
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
		}
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	private Categories loadObject(HttpServletRequest request) {
		// create a placeholder Category object
		Categories categories = new Categories();
		// update the category object with appropriate field values
		// drawn from the HttpServletRequest
		
		//set CategoryId in case its available:

		if (request.getParameter("categoryId") != null) {
			String categoryId = request.getParameter("categoryId");
			categories.setCategoryId(Integer.parseInt(categoryId));
			
		}
		
		
		categories.setCategoryName(request.getParameter("categoryName"));
		categories.setCategoryDescription(request.getParameter("categoryDesc"));
		categories.setCategoryImageUrl(request.getParameter("categoryImgUrl"));
		// transformations of object fields to fit the setter method contract
		Integer activeId = Integer.parseInt(request.getParameter("activeId"));
		categories.setActive(activeId);

		// transformations of object fields to fit the setter method contract
		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
		String dateString = request.getParameter("addedOn"); // 2024-07-28
		LocalDate addedOn = LocalDate.parse(dateString, formatter);
		categories.setAddedOn(addedOn);

		return categories;

	}

}
