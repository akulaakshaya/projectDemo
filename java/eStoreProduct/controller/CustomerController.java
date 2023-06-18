package eStoreProduct.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import eStoreProduct.model.Product;
import eStoreProduct.model.custCredModel;
import eStoreProduct.model.productqty;
import eStoreProduct.utility.ProductStockPrice;
import eStoreProduct.BLL.BLL;
import eStoreProduct.BLL.BLLClass2;
//import eStoreProduct.BLL.BLLClass;
import eStoreProduct.DAO.ProductDAO;
import eStoreProduct.DAO.cartDAO;
import eStoreProduct.DAO.customerDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

@Controller
public class CustomerController {
	customerDAO cdao;
	BLL bl1;
    BLLClass2 bl2;
	//BLLClass obj;
	cartDAO cartimp;
	@Autowired
	public CustomerController(cartDAO cartdao,customerDAO customerdao,BLLClass2 bl2,BLL bl1 ) {
		cdao = customerdao;
		cartimp=cartdao;
		this.bl2=bl2;
		this.bl1=bl1;
		//cartdao1 = cartdao;
	}
	@RequestMapping(value = "/profilePage")
	public String sendProfilePage(Model model, HttpSession session) {
		custCredModel cust = (custCredModel) session.getAttribute("customer");
		//System.out.println(cust.getCustId());
		model.addAttribute("cust", cust);
		return "profile";
	}

	// on clicking update Profile in profile page
	@RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
	public String userupdate(@ModelAttribute("Customer") custCredModel cust, Model model, HttpSession session) {
		cdao.updatecustomer(cust);
		custCredModel custt = cdao.getCustomerById(cust.getCustId());
		//System.out.print(custt.getCustLocation());
		if (custt != null) {
			model.addAttribute("cust", custt);
		}
		return "profile";
	}
	
	@RequestMapping(value = "/payment", method = RequestMethod.POST)
	public String showPaymentOptions(Model model,HttpSession session) {
		//custCredModel cust = (custCredModel) session.getAttribute("customer");
		//System.out.println(cust.getCustId());
		//model.addAttribute("cust", cust);
		return "payment";
	}
	
	@GetMapping("/buycartitems")
	public String confirmbuycart(Model model, HttpSession session) {
	
		custCredModel cust1 = (custCredModel) session.getAttribute("customer");
		List<ProductStockPrice> products = cartimp.getCartProds(cust1.getCustId());
		List<productqty> prqty =bl1.getproductqtys();
		model.addAttribute("prqty", prqty);

		model.addAttribute("products", products);

		return "paymentcart";

	}
	@GetMapping("/done")
	public String orderCreation(Model model) {
		//System.out.println("hiiiiiiiiii");
		return "redirect:/OrderCreation";
	}
	@RequestMapping(value = "/invoice", method = RequestMethod.POST)
	public String showPaymentOptions(Model model) {
		return "invoice";
	}
	
	@PostMapping("/confirmShipmentAddress")
	@ResponseBody
	public String confirm(@RequestParam(value = "mobile") String mobile,@RequestParam(value = "custsaddress") String custsaddress,@RequestParam(value = "spincode") String spincode, Model model, HttpSession session) {
		//System.out.println("confirm shipmentaddress");
		custCredModel cust1 = (custCredModel) session.getAttribute("customer");
		custCredModel cust2=new custCredModel();
		cust2.setCustMobile(mobile);
		cust2.setCustSAddress(custsaddress);
		cust2.setCustPincode(spincode);
		session.setAttribute("cust2", cust2);
		//cdao.updateshipmentaddress(cust1.getCustId(), shaddr);
		return "OK";

	}
	@GetMapping("/paymentoptions")
	public String orderCreate(Model model,HttpSession session) {
		String orderId = bl2.createRazorpayOrder(Double.parseDouble((String) session.getAttribute("qtycost")));
		double var =bl1.getcartcost();
		//System.out.println("amount in controller "+var);
		model.addAttribute(orderId);
		//System.out.println("hiiiiii---" +var);
		model.addAttribute("amt", var);
		//model.addAttribute("amt", var);
		return "payment-options";
	}
}
