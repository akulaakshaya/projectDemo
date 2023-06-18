package eStoreProduct.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import eStoreProduct.DAO.EmailConfigDAO;
import eStoreProduct.DAO.RegionDAO;
import eStoreProduct.DAO.adminDAO;
import eStoreProduct.model.EmailConfigModel;
import eStoreProduct.model.Regions;

@Controller
public class adminSettingsController {
	adminDAO adao;
	EmailConfigDAO edao;
	RegionDAO redao;

	@Autowired
	public adminSettingsController(adminDAO admindao, EmailConfigDAO edao, RegionDAO redao) {
		adao = admindao;
		this.edao = edao;
		this.redao = redao;
	}

	@RequestMapping(value = "/addRegion", method = RequestMethod.POST)
	@ResponseBody
	public String addRegion(@Validated Regions reg, Model model) {
		System.out.println("Admin Page");
		redao.addRegion(reg);
		// call the view
		return "DONE";
	}

	@RequestMapping(value = "/remRegion", method = RequestMethod.POST)
	@ResponseBody
	public String addRegion(@RequestParam("regionId") String id, Model model) {
		System.out.println("Admin Page");
		int Id = Integer.parseInt(id);
		redao.removeRegion(Id);
		// call the view
		return "DONE";
	}

	@RequestMapping(value = "/ShippingRedirect", method = RequestMethod.GET)
	public String shippingRedirect(Model model) {
		System.out.println("shippingRedirect");
		List<Regions> regionList = redao.getRegions();
		System.out.println("shippingRedirect1");
		model.addAttribute("regionList", regionList);
		System.out.println("shippingRedirect2");

		// call the view
		return "regions";
	}

	@RequestMapping(value = "/EmailConfiguration", method = RequestMethod.POST)
	@ResponseBody
	public String emailConfiguration(@RequestParam("email") String email, @RequestParam("pwd") String pwd,
			Model model) {
		System.out.println("emailConfiguration");
		// EmailConfigDAO edao=new EmailConfigDAOImpl();
		// call the view
		EmailConfigModel ecm = new EmailConfigModel();
		ecm.setEmail(email);
		ecm.setPwd(pwd);
		edao.changeEmail(ecm);
		return "done";
	}

	@RequestMapping(value = "/EmailConfigurationPage", method = RequestMethod.GET)
	public String returnpage(Model model) {
		System.out.println("emailConfigurationpage");
		// EmailConfigDAO edao=new EmailConfigDAOImpl();
		// call the view
		return "emailConfig";
	}

}