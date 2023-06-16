package eStoreProduct.DAO;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import eStoreProduct.model.EmailConfigModel;

@Component
public class EmailConfigDAOImpl implements EmailConfigDAO {
	JdbcTemplate jdbcTemplate;
	private String get_email = "select * from slam_EmailConfig where id=1";
	private String set_email = "update slam_EmailConfig set email=?,pwd=? where id=1";

	@Autowired
	public EmailConfigDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void changeEmail(EmailConfigModel ecm) {
		// TODO Auto-generated method stub
		jdbcTemplate.update(set_email, ecm.getEmail(), ecm.getPwd());

	}

	@Override
	public EmailConfigModel getEmail() {
		// TODO Auto-generated method stub
		EmailConfigModel wm = jdbcTemplate.queryForObject(get_email, EmailConfigModel.class);
		return wm;
	}

}