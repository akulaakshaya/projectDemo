package eStoreProduct.DAO;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import eStoreProduct.model.orderProductsMapper;
import eStoreProduct.model.adminMapper;
import eStoreProduct.model.adminModel;
import eStoreProduct.model.orderProductsModel;
import eStoreProduct.model.stockMapperMapper;
import eStoreProduct.model.stockSummaryModel;

public class shipmentsDAOImp implements shipmentsDAO {
	
	private EntityManager entityManager;
	private final JdbcTemplate jdbcTemplate;
	
	
	@Autowired
	public shipmentsDAOImp(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	
	
	
}
