package eStoreProduct.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import eStoreProduct.DAO.OrderDAOView;
import eStoreProduct.DAO.ProductDAO;
import eStoreProduct.model.OrdersViewModel;
import eStoreProduct.model.Product;
@Controller
public class customerOrderController {
	@Autowired
	private  ProductDAO pdaoimp;
	
	@Autowired
	 private OrderDAOView orderdaov;
	
	  @RequestMapping("/CustomerOrdersProfile")
	  public String showOrders(Model model) {
		  System.out.println("inshow");
	    List<OrdersViewModel> orderProducts = orderdaov. getorderProds();
	    model.addAttribute("orderProducts", orderProducts);
	    return "orders";
	  }
	  @GetMapping("/productDetails")
	    public String getProductDetails(@RequestParam("id") int productId, Model model) {
	        // Use your product DAO implementation to fetch the product details by ID
		  OrdersViewModel product = orderdaov.OrdProductById( productId);
	        model.addAttribute("product", product);
	        return "OrdProDetails";
	    }
	  
	  @PostMapping("/cancelOrder")
	  @ResponseBody
	  public String cancelOrder(@RequestParam("orderproId") Integer productId) {
	      orderdaov.cancelorderbyId(productId);
	     
	      return "Order with ID " +productId + " has been cancelled.";
	  }
	  
	  
	  @RequestMapping(value = "/trackOrder", method = RequestMethod.GET)
	  @ResponseBody
	  public String trackOrder(@RequestParam("orderproId") int orderId) {
	      // Retrieve the shipment status for the given order ID
	      String shipmentStatus = orderdaov.getShipmentStatus(orderId);
	      
	      return shipmentStatus;
	  }
	  
	  
	  @PostMapping("/applyFilters")
	  public String applyFilters(@RequestParam("sortBy") String sortBy, Model model) {
	    // Call the appropriate DAO method to fetch filtered/sorted products
	    List<OrdersViewModel> filteredProducts;
	    if (sortBy.equals("price")) {
	      filteredProducts = orderdaov.getProductsSortedByPrice();
	    } else if (sortBy.equals("shipping")) {
	      filteredProducts = orderdaov.getProductsSortedByShippingStatus();
	    } else {
	      filteredProducts = orderdaov.getorderProds();
	    }
	    
	    // Add the filtered/sorted products to the model
	    model.addAttribute("orderProducts", filteredProducts);
	    
	    // Return the view name for displaying the updated product list
	    return "orders";
	  }



}
