package eStoreProduct.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class adminController {

	
	@RequestMapping(value = "/adminDashboard", method = RequestMethod.GET)
	public String getAdminDashboard(Model model) {
		System.out.println("Admin Page");
		

		// call the view
		return "admin";
	}

	@RequestMapping(value = "/viewOrders", method = RequestMethod.GET)
	public String getOrders(Model model) {
		System.out.println("hello redirect");
		

		// call the view
		return "signIn";
	}
}
