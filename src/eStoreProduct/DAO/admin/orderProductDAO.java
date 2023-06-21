package eStoreProduct.DAO.admin;

import java.util.List;

import eStoreProduct.model.admin.output.orderProductsModel;

public interface orderProductDAO {
	public List<orderProductsModel> getOrderWiseOrderProducts(int orderid);

	public int updateOrderProductStatus(int oid, int pid, String status);

}