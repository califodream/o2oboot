package com.wong.o2oboot.web.frontend;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wong.o2oboot.entity.Product;
import com.wong.o2oboot.service.ProductService;
import com.wong.o2oboot.util.HttpServletRequestUtil;

@Controller
@RequestMapping("/frontend")
public class ProductDetailController {

	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "/listproductdetailpageinfo", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> listProductDetailPageInfo(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Product product = null;
		long productId = HttpServletRequestUtil.getLong(request, "productId");
		if(productId != -1) {
			product = productService.getProductById(productId);
			modelMap.put("product", product);
			modelMap.put("success", true);
		}
		else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "Empty productId");
		}
		return modelMap;
	}
}
