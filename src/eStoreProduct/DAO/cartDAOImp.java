package eStoreProduct.DAO;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import eStoreProduct.model.cartModel;

@Component
public class cartDAOImp implements cartDAO {

	JdbcTemplate jdbcTemplate;

	private final String SQL_INSERT_CART = "insert into slam_cart values(?,?)";

	@Autowired
	public cartDAOImp(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public boolean addToCart(cartModel cm) {

		return jdbcTemplate.update(SQL_INSERT_CART, cm.getCid(), cm.getPid()) > 0;
	}

}