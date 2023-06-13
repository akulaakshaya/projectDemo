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

	public void updateStocks(int prodid,String imageurl,int gstcid,int reorderlevel,double stock,double mrp) {
		int pid=prodid;
		double pstock=stock;
		double pmrp=mrp;
		
		String query="update slam_productstock set prod_stock=?,prod_mrp=? where prod_id=?";
		System.out.println(pid+""+pstock);
		jdbcTemplate.update(query, pstock,pmrp,pid);
	}

}