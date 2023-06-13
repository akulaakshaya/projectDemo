package eStoreProduct.DAO;

import java.sql.SQLException;

public interface cartDAO{
	public void addProduct(int productId, int customerId) throws SQLException;
}