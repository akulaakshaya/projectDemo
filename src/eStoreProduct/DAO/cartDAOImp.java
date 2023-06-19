package eStoreProduct.DAO;

import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import eStoreProduct.model.ProductRowMapper;
import eStoreProduct.utility.ProductStockPrice;

@Component
public class cartDAOImp implements cartDAO {
	JdbcTemplate jdbcTemplate;
	private ProdStockDAO prodStockDAO;
	@Autowired
	public cartDAOImp(DataSource dataSource, ProdStockDAO prodStockDAO) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		 this.prodStockDAO = prodStockDAO;
	}
	private String insert_slam_cart = "INSERT INTO slam_cart (c_id,p_id) VALUES (?, ?)";
	private String delete_slam_cart = "DELETE FROM SLAM_CART WHERE c_id=? AND p_id=?";
	private String select_cart_products = "SELECT 	p.prod_id, p.prod_title, p.prod_brand, p.image_url, p.prod_desc, ps.prod_price FROM \r\n"
			+ "       slam_Products p, slam_productstock ps,slam_cart sc \r\n"
			+ "       where p.prod_id = ps.prod_id and sc.c_id = ? AND sc.p_id = p.prod_id";
	public int addToCart(int productId, int customerId) {
		int r = jdbcTemplate.update(insert_slam_cart, customerId, productId);
		if (r > 0) {
			System.out.println("inserted into cart");
			return productId;

		} else {
			return -1;
		}
	}

	public int removeFromCart(int productId, int customerId) {
		int r = jdbcTemplate.update(delete_slam_cart, customerId, productId);
		if (r > 0) {
			System.out.println("deleted from  cart");
			return productId;
		} else {
			return -1;
		}
	}

	public List<ProductStockPrice> getCartProds(int cust_id) {
		System.out.println(cust_id + " from model");
		try {
			List<ProductStockPrice> cproducts = jdbcTemplate.query(select_cart_products, new ProductRowMapper(prodStockDAO), cust_id);
			System.out.println(cproducts.toString());
			return cproducts;
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList(); // or throw an exception if required
		}
	}

}