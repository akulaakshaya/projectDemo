package eStoreProduct.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// import eStoreProduct.BLL.BLLClass;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import eStoreProduct.DAO.ProductDAO;
import eStoreProduct.DAO.cartDAO;
import eStoreProduct.model.cartModel;
import eStoreProduct.model.custCredModel;
import eStoreProduct.utility.ProductStockPrice;

@Controller
public class CartController {
	cartDAO cartimp;
	private final ProductDAO pdaoimp;
	List<ProductStockPrice> alist = new ArrayList<>();

	// BLLClass obj = new BLLClass();
	@Autowired
	public CartController(cartDAO cartdao, ProductDAO productdao) {
		cartimp = cartdao;
		pdaoimp = productdao;
	}

	@GetMapping("/addToCart")
	@ResponseBody
	public String addToCart(@RequestParam(value = "productId", required = true) int productId, Model model,
			HttpSession session) throws NumberFormatException, SQLException {
		custCredModel cust1 = (custCredModel) session.getAttribute("customer");
		if (cust1 != null) {
			return cartimp.addToCart(productId, cust1.getCustId()) + " Added to cart";
		} else {
			ProductStockPrice product = pdaoimp.getProductById(productId);
			product.setQuantity(1);
			alist.add(product);
			model.addAttribute("alist", alist);
			return "added to cart";

		}
	}

	@RequestMapping(value = "/cartDisplay", method = RequestMethod.GET)
	public String getSignUpPage(Model model, HttpSession session) {
		double cartt = 0;
		// ProductDAO pdao = new ProductDAO();
		custCredModel cust1 = (custCredModel) session.getAttribute("customer");
		if (cust1 != null) {
			List<ProductStockPrice> products = cartimp.getCartProds(cust1.getCustId());
			model.addAttribute("products", products);
			for (ProductStockPrice product : products) {
				System.out.println("here is the quantity: " + product.getQuantity());
			}
			// Forward to the cart.jsp view
			return "cart";
		} else {
			// Set the products attribute in the model
			for (ProductStockPrice product : alist) {
				System.out.println("here is the quantity of alist: " + product.getQuantity());
			}
			model.addAttribute("alist", alist);
			return "cart";

		}
	}

	@GetMapping("/removeFromCart")
	@ResponseBody
	public String removeFromCart(@RequestParam(value = "productId", required = true) int productId, Model model,
			HttpSession session) throws NumberFormatException, SQLException {
		// System.out.println("pid remove from cart "+productId);

		custCredModel cust1 = (custCredModel) session.getAttribute("customer");
		if (cust1 != null) {
			System.out.println("remove from cart login");
			return cartimp.removeFromCart(productId, 1) + " remove from cart";
		} else {
			// Product product=pdaoimp.getProductById(productId);
			// System.out.println("remove from cart nonlogin");
			for (ProductStockPrice p : alist) {
				if (p.getProd_id() == productId)

					alist.remove(p);
			}

			return "remove from cart";
		}

	}

	@PostMapping("/updateQuantity")
	public int updateQuantity(@RequestParam(value = "productId", required = true) int productId,
			@RequestParam(value = "quantity", required = true) int quantity, Model model, HttpSession session)
			throws NumberFormatException, SQLException {
		custCredModel cust1 = (custCredModel) session.getAttribute("customer");
		if (cust1 != null) {
			cartModel cart = new cartModel(cust1.getCustId(), productId, quantity);
			return cartimp.updateQty(cart);
		} else {
			for (ProductStockPrice product : alist) {
				if (product.getProd_id() == productId) {
					product.setQuantity(quantity);
				}
			}
		}
		return quantity;
	}
}
