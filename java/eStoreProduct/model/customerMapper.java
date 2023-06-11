package eStoreProduct.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class customerMapper implements RowMapper<custCredModel> {

	public custCredModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		custCredModel cust = new custCredModel();
		cust.setCustId(rs.getInt("cust_id"));
		cust.setCustName(rs.getString("cust_name"));
		cust.setCustEmail(rs.getString("cust_email"));
		cust.setCustPassword(rs.getString("cust_pswd"));
		cust.setCustMobile(rs.getString("cust_mobile"));
		cust.setCustLocation(rs.getString("cust_location"));
		cust.setCustAddress(rs.getString("cust_address"));
		cust.setCustSAddress(rs.getString("cust_saddress"));
		cust.setCustPincode(rs.getString("cust_pincode"));
		cust.setCustLastLoginDate(rs.getTimestamp("cust_lastlogindate"));
		return cust;
	}
}