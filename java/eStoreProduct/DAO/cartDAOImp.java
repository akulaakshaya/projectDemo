package eStoreProduct.DAO;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import eStoreProduct.model.cartModel;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class cartDAOImp implements cartDAO {
	private static final String JDBC_DRIVER = "org.postgresql.Driver";
	private static final String DB_URL = "jdbc:postgresql://LocalHost:5432/postgres";
	private static final String USERNAME = "postgres";
	private static final String PASSWORD = "Akshaya@2001";

	public void addProduct(int productId, int customerId) throws SQLException {
		// Establish a database connection
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			// Create the SQL query
			String query = "INSERT INTO slam_cart (c_id, p_id) VALUES (?, ?)";

			// Open a connection
			connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

			// Create a prepared statement
			statement = connection.prepareStatement(query);

			// Set the parameter values
			statement.setInt(1, customerId);
			statement.setInt(2, productId);

			// Execute the query
			statement.executeUpdate();
		} finally {
			// Close the statement and connection
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}
}