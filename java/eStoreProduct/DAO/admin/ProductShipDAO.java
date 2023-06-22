package eStoreProduct.DAO.admin;

import java.util.List;

import eStoreProduct.model.admin.output.ProductShip;

public interface ProductShipDAO {
	public List<ProductShip> getAll();

	public boolean update(ProductShip ps);

}
