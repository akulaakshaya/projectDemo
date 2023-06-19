package eStoreProduct.DAO;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import eStoreProduct.model.adminMapper;
import eStoreProduct.model.adminModel;

public class adminDAOImp implements adminDAO {
	JdbcTemplate jdbcTemplate;

	@Autowired
	public adminDAOImp(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public adminModel getAdmin(String email, String password) {

		System.out.println(email + "" + password);

		String adminSelectQuery = "SELECT * FROM slam_ausr WHERE ausr_email = ? AND ausr_pwd = ?";

		try {
			adminModel am = jdbcTemplate.queryForObject(adminSelectQuery, new Object[] { email, password },
					new adminMapper());
			return am;
		} catch (Exception e) {
			// Handle the exception appropriately (e.g., logging, throwing custom exception, etc.)
			e.printStackTrace();
			return null; // or throw an exception if required
		}

	}

	public void updateAdmin(adminModel am) {
		String query = "update slam_ausr set ausr_title=?,ausr_desg=?,ausr_pwd=?,ausr_email=? where ausr_id=? ";
		jdbcTemplate.update(query, am.getTitle(), am.getDesignation(), am.getPassword(), am.getEmail(), am.getId());
	}

}
