package eStoreProduct.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class adminMapper implements RowMapper<adminModel> {

	public adminModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		adminModel am = new adminModel();
		am.setId(rs.getInt("ausr_id"));
		am.setTitle(rs.getString("ausr_title"));
		am.setDesignation(rs.getString("ausr_desg"));
		am.setEmail(rs.getString("ausr_email"));
		am.setPassword(rs.getString("ausr_pwd"));
		return am;
	}
}
