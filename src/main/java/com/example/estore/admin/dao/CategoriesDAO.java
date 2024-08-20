package com.example.estore.admin.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.example.estore.admin.model.Categories;
import com.example.estore.dao.DAO;
import com.example.estore.db.DB;

//interact with our Categogies Table in our MySQL DB
public class CategoriesDAO implements DAO<Categories> {

	// fields
	DB db = DB.getInstance();

	// METHODS
	@Override
	public Categories get(long id) {

		String selectCategorySql = "SELECT * FROM categories WHERE categoryId = ?";

		try (Connection connection = db.getConnection();
				PreparedStatement ps = connection.prepareStatement(selectCategorySql)) {

//			ps.setLong(1, id);
			ps.setLong(1, id);

			ResultSet result = db.executeQuery(ps);
			
			// try (ResultSet rs = ps.executeQuery()) {
			if (result.next()) {

				//placeholder object
				Categories category = new Categories();

				category.setCategoryId(result.getInt("categoryId"));
				category.setCategoryName(result.getString("categoryName"));
				category.setCategoryDescription(result.getString("categoryDescription"));
				category.setCategoryImageUrl(result.getString("categoryImageUrl"));
				category.setActive(result.getInt("activeId"));
				category.setAddedOn(result.getDate("addedOn").toLocalDate());
				
				//return the loaded category object to my CategoriesServlet class
				return category;
			} else {
				return null;
			}

		} catch (

		Exception e) {
			System.out.println("SQL Exception occurred: ");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Categories> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Categories object) {
		// TODO Auto-generated method stub
		// SQL query to insert Categories into the database
		String insertCategoriesSql = "INSERT INTO Categories (categoryName, categoryDescription, categoryImageUrl, activeId, addedOn) VALUES (?, ?, ?, ?, ?)";

		try (Connection connection = db.getConnection();
				PreparedStatement ps = connection.prepareStatement(insertCategoriesSql)) {

			// Set parameters for the Categories insertion
			ps.setString(1, object.getCategoryName());
			ps.setString(2, object.getCategoryDescription());
			ps.setString(3, object.getCategoryImageUrl());
			ps.setInt(4, object.getActive());

			// Convert java.util.LocalDate to java.sql.Date
			java.sql.Date sqlDate = Date.valueOf(object.getAddedOn());

			// Set the date on the PreparedStatement
			ps.setDate(5, sqlDate);

			int result = db.executeUpdate(ps);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("SQL Exception occurred: ");
			e.printStackTrace();

		}
	}

	@Override
	public void update(Categories object) {
		// TODO Auto-generated method stub

		// validation to make sure that the category ID exists
		
		// SQL query to update Categories object into the database
		String updateSql = "UPDATE categories SET categoryName = ?, categoryDescription = ?, categoryImageUrl = ?,activeId = ?, addedOn = ?  WHERE categoryId = ?";
		
		try (Connection connection = db.getConnection();
				PreparedStatement ps = connection.prepareStatement(updateSql)){
			
			// Set parameters for the Categories update
			ps.setString(1, object.getCategoryName());
			ps.setString(2, object.getCategoryDescription());
			ps.setString(3, object.getCategoryImageUrl());
			ps.setInt(4, object.getActive());
			
				// Convert java.util.LocalDate to java.sql.Date
			java.sql.Date sqlDate = Date.valueOf(object.getAddedOn());
			
			ps.setDate(5, sqlDate);
			ps.setInt(6, object.getCategoryId());
			
			
			 db.executeUpdate(ps);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("SQL Exception occurred: ");
			e.printStackTrace();
		}
		
		

	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub

	}

}
