package com.wong.o2oboot.web.shopadmin;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.wong.o2oboot.dto.ShopAuthMapExecution;
import com.wong.o2oboot.dto.UserAccessToken;
import com.wong.o2oboot.dto.WechatInfo;
import com.wong.o2oboot.entity.PersonInfo;
import com.wong.o2oboot.entity.Shop;
import com.wong.o2oboot.entity.ShopAuthMap;
import com.wong.o2oboot.entity.WeChatAuth;
import com.wong.o2oboot.enums.ShopAuthMapStateEnum;
import com.wong.o2oboot.service.PersonInfoService;
import com.wong.o2oboot.service.ShopAuthMapService;
import com.wong.o2oboot.service.WechatAuthService;
import com.wong.o2oboot.util.CodeUtil;
import com.wong.o2oboot.util.HttpServletRequestUtil;
import com.wong.o2oboot.util.wechat.WechatUtil;

@Controller
@RequestMapping(value = "/shopadmin")
@Configuration
public class ShopAuthManagementController {

	@Autowired
	private ShopAuthMapService shopAuthMapService;

	@Autowired
	private WechatAuthService wechatAuthService;

	@Autowired
	private PersonInfoService personInfoService;

	private static Logger log = LoggerFactory.getLogger(ShopAuthManagementController.class);

	@RequestMapping(value = "/listshopauthmapsbyshop", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> listShopAuthMapByShop(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
		int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
		Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
		if (pageIndex > 0 && pageSize > -1 && currentShop != null && currentShop.getShopId() != null) {
			ShopAuthMapExecution same = shopAuthMapService.listShopAuthMapByShopId(currentShop.getShopId(), pageIndex,
					pageSize);
			modelMap.put("shopAuthMapList", same.getShopAuthMapList());
			modelMap.put("count", same.getCount());
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "Empty pagesize or pageindex or shopid");
		}
		return modelMap;
	}

	@RequestMapping(value = "/getshopauthmapbyid", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getShopAuthMapById(@RequestParam Long shopAuthId) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		if (shopAuthId != null && shopAuthId > 0) {
			ShopAuthMap shopAuthMap = shopAuthMapService.getShopAuthMapById(shopAuthId);
			modelMap.put("shopAuthMap", shopAuthMap);
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "Empty shopid");
		}
		return modelMap;
	}

	@RequestMapping(value = "/modifyshopauthmap", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> modifyShopAuthMap(String shopAuthMapStr, HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		ObjectMapper mapper = new ObjectMapper();
		ShopAuthMap shopAuthMap = null;
		try {
			shopAuthMap = (ShopAuthMap) mapper.readValue(shopAuthMapStr, ShopAuthMap.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
		}
		if (shopAuthMap != null && shopAuthMap.getShopAuthId() != null) {
			if (!checkPerssion(shopAuthMap.getShopAuthId())) {
				modelMap.put("success", false);
				modelMap.put("errMsg", "错误的权限");
				return modelMap;
			}
			try {
				ShopAuthMapExecution same = shopAuthMapService.modifyShopAuthMap(shopAuthMap);
				if (same.getState() == ShopAuthMapStateEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", same.getStateInfo());
				}
			} catch (RuntimeException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
				return modelMap;
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "Empty shopauthmap");
		}
		return modelMap;
	}

	private boolean checkPerssion(Long shopAuthId) {
		// TODO Auto-generated method stub
		ShopAuthMap shopAuthMap = shopAuthMapService.getShopAuthMapById(shopAuthId);
		if (shopAuthMap.getTitleFlag() == 0) {
			return false;
		}
		return true;
	}

	private static String urlPrefix;

	private static String urlMiddle;

	private static String urlSuffix;

	private static String authUrl;

	@Value("${wechat.prefix}")
	public void setUrlPrefix(String urlPrefix) {
		ShopAuthManagementController.urlPrefix = urlPrefix;
	}

	@Value("${wechat.middle}")
	public void setUrlMiddle(String urlMiddle) {
		ShopAuthManagementController.urlMiddle = urlMiddle;
	}

	@Value("${wechat.suffix}")
	public void setUrlSuffix(String urlSuffix) {
		ShopAuthManagementController.urlSuffix = urlSuffix;
	}

	@Value("${wechat.auth.url}")
	public void setAuthUrl(String authUrl) {
		ShopAuthManagementController.authUrl = authUrl;
	}

	@RequestMapping(value = "/generateqrcode4shopauth", method = RequestMethod.GET)
	@ResponseBody
	private void generateQRCode4ShopAuth(HttpServletRequest request, HttpServletResponse response) {
		Shop shop = (Shop) request.getSession().getAttribute("currentShop");
		if (shop != null && shop.getShopId() != null) {
			long timeStamp = System.currentTimeMillis();
			String content = "{aaashopIdaaa:" + shop.getShopId() + ",aaacreateTimeaaa:" + timeStamp + "}";
			try {
				String longUrl = urlPrefix + authUrl + urlMiddle + URLEncoder.encode(content, "UTF-8") + urlSuffix;
				String shortUrl = WechatUtil.getShortURL(longUrl);
				BitMatrix qrCodeImg = CodeUtil.generateQRCode(shortUrl, response);
				MatrixToImageWriter.writeToStream(qrCodeImg, "png", response.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			} catch (WriterException e) {
				e.printStackTrace();
			}
		}
	}

	@RequestMapping(value = "/addshopauthmap", method = RequestMethod.GET)
	@ResponseBody
	private String addShopAuthMap(HttpServletRequest request, HttpServletResponse response) {
		WeChatAuth auth = getEmployeeInfo(request);
		if (auth != null) {
			PersonInfo user = personInfoService.getPersonInfo(auth.getPersonInfo().getUserId());
			request.getSession().setAttribute("user", user);
			WechatInfo wechatInfo = null;
			try {
				String qrCodeInfo = new String(
						URLDecoder.decode(HttpServletRequestUtil.getString(request, "state"), "UTF-8"));
				ObjectMapper mapper = new ObjectMapper();
				wechatInfo = mapper.readValue(qrCodeInfo.replace("aaa", "\""), WechatInfo.class);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "shop/operationfail";
			}
			if (!checkQRCodeInfo(wechatInfo)) {
				return "shop/operationfail";
			}
			ShopAuthMapExecution same1 = shopAuthMapService.listShopAuthMapByShopId(wechatInfo.getShopId(), 0, 100);
			List<ShopAuthMap> authList = same1.getShopAuthMapList();
			for(ShopAuthMap sam : authList) {
				if(sam.getEmployee().getUserId() == user.getUserId()) {
					return "sho/operationfail";
				}
			}			
			try {
				ShopAuthMap shopAuthMap = new ShopAuthMap();
				Shop shop = new Shop();
				shop.setShopId(wechatInfo.getShopId());
				shopAuthMap.setShop(shop);
				shopAuthMap.setEmployee(user);
				shopAuthMap.setTitle("员工");
				shopAuthMap.setTitleFlag(1);
				ShopAuthMapExecution same2 = shopAuthMapService.addShopAuthMap(shopAuthMap);
				if (same2.getState() == ShopAuthMapStateEnum.SUCCESS.getState()) {
					return "shop/operationsuccess";
				} else {
					return "shop/operationfail";
				}
			} catch (RuntimeException e) {
				return "shop/operationfail";
			}
		} else {
			return "shop/operationfail";
		}
	}

	private boolean checkQRCodeInfo(WechatInfo wechatInfo) {
		if (wechatInfo != null && wechatInfo.getCreateTime() != null) {
			long now = System.currentTimeMillis();
			if ((now - wechatInfo.getCreateTime()) <= 600000) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	private WeChatAuth getEmployeeInfo(HttpServletRequest request) {
		String code = request.getParameter("code");
		WeChatAuth auth = null;
		if (code != null) {
			UserAccessToken token = null;
			try {
				token = WechatUtil.getUserAccessToken(code);
				log.debug("wechat auth token: " + token);
				String openId = token.getOpenId();
				request.getSession().setAttribute("openId", openId);
				auth = wechatAuthService.getWechatAuthByOpenId(openId);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return auth;
	}

}
