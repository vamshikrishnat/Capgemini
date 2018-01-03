package com.capgemini.config;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.capgemini.bean.Cart1;
import com.capgemini.bean.Catalog;
import com.capgemini.bean.GiftCard;
import com.capgemini.bean.Order;
import com.capgemini.bean.ProductList;
import com.capgemini.login.model.UserBean;
import com.capgemini.login.social.providers.LinkedInProvider;
import com.capgemini.serviceimpl.CartServiceimpl;

/**
 * @author dimehta
 *
 */

@Controller
public class WebRequestController {
	@Autowired
	public CartServiceimpl cartServiceimpl;

	@Autowired
	LinkedInProvider linkedInProvider;

	UserBean userBean = new UserBean();

	@RequestMapping({ "/", "/home" })
	public String homeBeforeLogin(ModelMap model) {
		Collection<Catalog> cat = cartServiceimpl.getDetails();
		model.addAttribute("catalog", cat);
		return "Home";
	}

	@RequestMapping(value = "/home1", method = RequestMethod.GET)
	public String homeAfterLogin(ModelMap model) {
		Collection<Catalog> cat = cartServiceimpl.getDetails();
		model.addAttribute("catalog", cat);
		model.addAttribute("name", linkedInProvider.populateUserDetailsFromLinkedIn(userBean).getFirstName());
		return "index";
	}

	@RequestMapping(value = "/userinfo", method = RequestMethod.GET)
	public String showOrderInfo(ModelMap model) {
		UserBean bean = linkedInProvider.populateUserDetailsFromLinkedIn(userBean);
		model.addAttribute("userinfo", bean);
		model.addAttribute("name", bean.getFirstName());
		return "UserInfo";
	}

	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String error(ModelMap model) {
		model.addAttribute("name", linkedInProvider.populateUserDetailsFromLinkedIn(userBean).getFirstName());
		return "error";
	}

	/*
	 * @RequestMapping(value = "/order", method = RequestMethod.GET) public String
	 * showOrderPage(ModelMap model) { ArrayList<ProductList> productlist =
	 * cartServiceimpl.getAllProduct(); model.addAttribute("prodInfo", productlist);
	 * UseDetails userDetails = cartServiceimpl.getAllDetails();
	 * model.addAttribute("details", userDetails); return "orderList"; }
	 */

	@RequestMapping(value = "/cancel", method = RequestMethod.GET)
	public String cancelUpdateUser(HttpServletRequest request) {
		return "redirect:/order";
	}

	@RequestMapping(value = "/userOrder", method = RequestMethod.GET)
	public String userOrder(ModelMap model) {
		ArrayList<Order> orderlist = cartServiceimpl.getAllOrder();
		model.addAttribute("orderInfo", orderlist);
		model.addAttribute("name", linkedInProvider.populateUserDetailsFromLinkedIn(userBean).getFirstName());
		return "UserOrder";
	}

	@RequestMapping(value = "/giftCard", method = RequestMethod.GET)
	public String showgiftCardInfo(ModelMap model) {
		GiftCard giftcard = cartServiceimpl.getAllgiftCard();
		model.addAttribute("giftcard", giftcard);
		model.addAttribute("name", linkedInProvider.populateUserDetailsFromLinkedIn(userBean).getFirstName());
		return "GiftCard";
	}

	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public String showCartPage(ModelMap model) {

		ArrayList<Cart1> cart = cartServiceimpl.getAllCart1();
		model.addAttribute("cartinf", cart);
		model.addAttribute("name", linkedInProvider.populateUserDetailsFromLinkedIn(userBean).getFirstName());
		return "UserCart";
	}

	@RequestMapping(value = "/addGiftCard", method = RequestMethod.GET)
	public String addGiftCard(ModelMap model) {
		model.addAttribute("name", linkedInProvider.populateUserDetailsFromLinkedIn(userBean).getFirstName());
		return "AddGiftCart";
	}

	@RequestMapping(value = "/addProduct", method = RequestMethod.GET)
	public String showAddPage(ModelMap model) {
		model.addAttribute("name", linkedInProvider.populateUserDetailsFromLinkedIn(userBean).getFirstName());
		return "addProduct";
	}

	@RequestMapping(value = "/orderList", method = RequestMethod.GET)
	public String showAdminOrderPage(ModelMap model) {
		ArrayList<ProductList> productlist = cartServiceimpl.getAllProduct();
		model.addAttribute("prodInf", productlist);
		model.addAttribute("name", linkedInProvider.populateUserDetailsFromLinkedIn(userBean).getFirstName());
		return "AdminOrderList";
	}

	@RequestMapping(value = "/adminlogin", method = RequestMethod.GET)
	public String showAdminLoginPage(ModelMap model) {
		return "AdminLogin";
	}

	@RequestMapping(value = "/adminHome", method = RequestMethod.GET)
	public String AdminHome(ModelMap model) {
		Collection<Catalog> cat = cartServiceimpl.getDetails();
		model.addAttribute("catalog", cat);
		model.addAttribute("name", linkedInProvider.populateUserDetailsFromLinkedIn(userBean).getFirstName());
		return "AdminHome";
	}

	@RequestMapping(value = "/checkout", method = RequestMethod.GET)
	public String showCheckoutPage(ModelMap model) {
		model.addAttribute("name", linkedInProvider.populateUserDetailsFromLinkedIn(userBean).getFirstName());
		return "checkout";
	}

	/*
	 * @RequestMapping(value = "/checkout/complete", method = RequestMethod.GET)
	 * public String showCheckoutPage(@RequestBody Checkout checkout) {
	 * 
	 * System.out.println(checkout.getName()); return "Hi"; }
	 */

	@RequestMapping(value = "/linkedin", method = RequestMethod.GET)
	public String linkedInUserInfo(Model model) {
		return linkedInProvider.getLinkedInUserData(model, new UserBean());
	}

	@RequestMapping(value = "/loginauth", method = RequestMethod.GET)
	public String login() {
		return "login-auth";
	}
	
	@ResponseBody
	@RequestMapping(value = "/addcart", method = RequestMethod.GET)
	public String addcart(@RequestParam("id")  int id) {
		return "id";
	}
}
