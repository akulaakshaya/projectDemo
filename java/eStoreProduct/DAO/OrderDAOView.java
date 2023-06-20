package eStoreProduct.DAO;

import java.util.List;


import eStoreProduct.model.OrdersViewModel;


public interface OrderDAOView {
	public List<OrdersViewModel> getorderProds();
	public  OrdersViewModel OrdProductById(Integer productId);
	public void cancelorderbyId(Integer productId);
	 public String getShipmentStatus(int orderId);
	 public List<OrdersViewModel> getProductsSortedByPrice();
	 public List<OrdersViewModel> getProductsSortedByShippingStatus();
}
