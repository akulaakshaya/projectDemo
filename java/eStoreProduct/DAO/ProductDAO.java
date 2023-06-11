package eStoreProduct.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import eStoreProduct.model.Product;

public class ProductDAO {
	private static final String JDBC_DRIVER = "org.postgresql.Driver";
	private static final String DB_URL = "jdbc:postgresql://LocalHost:5432/postgres";
	private static final String USERNAME = "postgres";
	private static final String PASSWORD = "Akshaya@2001";

	public List<Product> getAllProducts() {
		List<Product> products = new ArrayList<>();

		try {
			Class.forName(JDBC_DRIVER);
			Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

			String query = "SELECT * FROM productsData";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				double price = resultSet.getDouble("price");
				String description = resultSet.getString("description");
				String imageUrl = resultSet.getString("image_url");
				String category = resultSet.getString("category");

				Product product = new Product(id, name, price, description, imageUrl, category);
				products.add(product);
			}

			resultSet.close();
			statement.close();
			connection.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return products;
	}

	public List<Product> getProductsByCategory(String category) {
		List<Product> products = new ArrayList<>();
		System.out.println(category + " from model");
		try {
			Class.forName(JDBC_DRIVER);
			Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			System.out.println(category + " from model");
			String query = "SELECT * FROM productsData WHERE category = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, category);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				double price = resultSet.getDouble("price");
				String description = resultSet.getString("description");
				String imageUrl = resultSet.getString("image_url");

				Product product = new Product(id, name, price, description, imageUrl, category);
				products.add(product);
			}
			System.out.println(products.toString());
			resultSet.close();
			statement.close();
			connection.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return products;
	}
	
	
	
	public int addToCart(int productId, int customerId) {
		try {
			Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			String query = "INSERT INTO slam_cart(c_id,p_id) VALUES (?, ?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, customerId);
			statement.setInt(2, productId);
			int r = statement.executeUpdate();
			if (r > 0) {
				System.out.println("inserted into cart");
				return productId;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		return -1;
	}

	public int addToWishlist(int productId, int customerId) {
		try {
			Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			String query = "INSERT INTO slam_wishlist(c_id,p_id) VALUES (?, ?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, customerId);
			statement.setInt(2, productId);
			int r = statement.executeUpdate();
			if (r > 0) {
				System.out.println("inserted into wishlist");
				return productId;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		return -1;
	}

	public int removeFromCart(int productId, int customerId) {
		try {
			Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			String query = "DELETE FROM SLAM_CART WHERE c_id=? AND p_id=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, customerId);
			statement.setInt(2, productId);
			int r = statement.executeUpdate();
			if (r > 0) {
				System.out.println("deleted from  cart");
				return productId;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		return -1;
	}

	public int removeFromWishlist(int productId, int customerId) {
		try {
			Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			String query = "DELETE FROM SLAM_WISHLIST WHERE c_id=? AND p_id=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, customerId);
			statement.setInt(2, productId);
			int r = statement.executeUpdate();
			if (r > 0) {
				System.out.println("deleted from  wishlist");
				return productId;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		return -1;
	}

	public Product getProductById(int productId) {
		Product product = null;
		try {
			Class.forName(JDBC_DRIVER);
			Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			String query = "SELECT * FROM productdetails WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, productId);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				// Retrieve the product details from the result set
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				double price = resultSet.getDouble("price");
				String description = resultSet.getString("description");
				String imageUrl = resultSet.getString("image_url");
				String category = resultSet.getString("category");

				// Create a new Product object
				product = new Product(id, name, price, description, imageUrl, category);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return product;

	}

	

	public List<Product> getOrderProds(int cust_id) {
		List<Product> products = new ArrayList<>();
		System.out.println(cust_id + " from model");
		try {
			Class.forName(JDBC_DRIVER);
			Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			System.out.println(cust_id + " from model");
			String query = "select pd.* from productsdata pd ,slam_cart sc where sc.c_id=? and sc.p_id=pd.id";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, cust_id);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				double price = resultSet.getDouble("price");
				String description = resultSet.getString("description");
				String imageUrl = resultSet.getString("image_url");
				String category = resultSet.getString("category");
				Product product = new Product(id, name, price, description, imageUrl, category);
				products.add(product);
			}
			System.out.println(products.toString());
			resultSet.close();
			statement.close();
			connection.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return products;
	}

	public List<Product> getWishlistProds(int cust_id) {
		List<Product> products = new ArrayList<>();
		System.out.println(cust_id + " from model");
		try {
			Class.forName(JDBC_DRIVER);
			Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			System.out.println(cust_id + " from model");
			String query = "select pd.* from productsdata pd ,slam_wishlist sc where sc.c_id=? and sc.p_id=pd.id";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, cust_id);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				double price = resultSet.getDouble("price");
				String description = resultSet.getString("description");
				String imageUrl = resultSet.getString("image_url");
				String category = resultSet.getString("category");
				Product product = new Product(id, name, price, description, imageUrl, category);
				products.add(product);
			}
			System.out.println(products.toString());
			resultSet.close();
			statement.close();
			connection.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return products;
	}
	
	
	
	
}
