package com.wong.o2oboot.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "shopadmin", method={RequestMethod.GET})
public class ShopAdminController {
	
	@RequestMapping(value = "/shopoperation")
	public String shopOperation() {
		return "shop/shopoperation";
	}
	
	@RequestMapping(value = "/shoplist")
	public String shopList() {
		return "shop/shoplist";
	}
	
	@RequestMapping(value = "/shopmanagement")
	public String shopManagement() {
		return "shop/shopmanagement";
	}
	
	@RequestMapping(value = "/shopauthmanagement")
	public String shopAuthManagement() {
		return "shop/shopauthmanagement";
	}
	
	@RequestMapping(value = "/shopauthedit")
	public String shopAuthEdit() {
		return "shop/shopauthedit";
	}
	
	@RequestMapping(value = "/operationsuccess")
	public String shopOperationSuccess() {
		return "shop/operationsuccess";
	}
	
	@RequestMapping(value = "/operationfail")
	public String shopOperationFail() {
		return "shop/operationfail";
	}
	
	@RequestMapping(value = "/productcategorymanagement")
	public String productCategoryManagement() {
		return "shop/productcategorymanagement";
	}
	
	@RequestMapping(value = "/productoperation")
	public String productOperation() {
		return "shop/productoperation";
	}
	
	@RequestMapping(value = "/productmanagement")
	public String productManagement() {
		return "shop/productmanagement";
	}
	
}
