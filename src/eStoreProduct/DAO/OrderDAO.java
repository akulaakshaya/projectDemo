package eStoreProduct.DAO;

import java.util.List;

import eStoreProduct.model.orderModel;

public interface OrderDAO {
	void insertOrder(orderModel order);

	List<orderModel> getAllOrders();
}
