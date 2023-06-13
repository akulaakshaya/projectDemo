package eStoreProduct.DAO;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository; // Import the Repository annotation

import eStoreProduct.model.stockMapperMapper;
import eStoreProduct.model.stockSummaryModel;

@Repository // Add the Repository annotation
public class stockSummaryDAOImp implements stockSummaryDAO {
	@PersistenceContext
	private EntityManager entityManager;
	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public stockSummaryDAOImp(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<stockSummaryModel> getStocks() {
		String custSelectQuery = "SELECT slp.*, spc.prct_title, shc.sgst, shc.igst, shc.cgst, shc.gst, sps.prod_price, sps.prod_stock, sps.prod_mrp "
				+ "FROM slam_products slp " + "JOIN slam_Productstock sps ON slp.prod_id = sps.prod_id "
				+ "JOIN slam_hsn_code shc ON slp.prod_gstc_id = shc.hsn_code "
				+ "JOIN SLAM_PRODUCTCATEGORIES spc ON spc.prct_id = slp.prod_prct_id";

		try {
			System.out.print("in query1");
			List<stockSummaryModel> stocks = jdbcTemplate.query(custSelectQuery, new stockMapperMapper());
			System.out.print("in query2");
			return stocks;
		} catch (Exception e) {
			// Handle the exception appropriately (e.g., logging, throwing custom exception, etc.)
			e.printStackTrace();
			return Collections.emptyList(); // or throw an exception if required
		}
	}

	/*
	 * public List<Object[]> fetchStockSummary() { try { System.out.println("Entered stock method1");
	 * TypedQuery<Object[]> query = entityManager.createQuery(
	 * "SELECT slp, spc.prct_title, shc.sgst, shc.igst, shc.cgst, shc.gst, sps.prod_price, sps.prod_stock, sps.prod_mrp FROM slam_products slp  JOIN slam_Productstock sps ON slp.prod_id = sps.prod_id JOIN slam_hsn_code shc ON slp.prod_gstc_id = shc.hsn_code  JOIN SLAM_PRODUCTCATEGORIES spc ON spc.prct_id = slp.prod_prct_id"
	 * , Object[].class);
	 * 
	 * System.out.println("Entered stock method2"); List<Object[]> resultList = query.getResultList();
	 * 
	 * 
	 * for (Object[] result : resultList) { // Access the values from each result row productsModel product =
	 * (productsModel) result[0]; String categoryTitle = (String) result[1]; double sgst = (double) result[2]; double
	 * igst = (double) result[3]; double cgst = (double) result[4]; double gst = (double) result[5]; double price =
	 * (double) result[6]; Integer stock = (Integer) result[7]; Integer mrp = (Integer)result[8]; }
	 * 
	 * 
	 * return resultList; } catch (Exception e) { // Handle the exception e.printStackTrace(); // You can add more
	 * specific exception handling logic here if needed } return null; // or an empty list, depending on your error
	 * handling strategy }
	 */

}
