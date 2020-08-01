package com.wong.o2oboot.web.wechat;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wong.o2oboot.dto.UserAccessToken;
import com.wong.o2oboot.dto.WechatAuthExecution;
import com.wong.o2oboot.dto.WechatUser;
import com.wong.o2oboot.entity.PersonInfo;
import com.wong.o2oboot.entity.WeChatAuth;
import com.wong.o2oboot.enums.WechatAuthStateEnum;
import com.wong.o2oboot.service.PersonInfoService;
import com.wong.o2oboot.service.WechatAuthService;
import com.wong.o2oboot.util.wechat.WechatUtil;

/**
 * 获取关注公众号之后的微信用户信息的接口，如果在微信浏览器里访问
 * https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxd76120c84ce40803&redirect_uri=http://o2o.califodream.cn/o2o/wechatlogin/logincheck&role_type=1&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect
 * 则这里将会获取到code,之后再可以通过code获取到access_token 进而获取到用户信息
 * 
 */

@Controller
@RequestMapping("wechatlogin")
public class WechatLoginController {

	private static Logger log = LoggerFactory.getLogger(WechatLoginController.class);

	private static final String FRONTEND = "1";

	private static final String SHOPEND = "2";

	@Autowired
	private PersonInfoService personInfoService;

	@Autowired
	private WechatAuthService wechatAuthService;

	@RequestMapping(value = "/logincheck", method = { RequestMethod.GET })
	public String doGet(HttpServletRequest request, HttpServletResponse response) {
		log.debug("wechat login get......");
		String code = request.getParameter("code");
		String roleType = request.getParameter("state");
		log.debug("wechat login code: " + code);
		WechatUser user = null;
		String openId = null;
		WeChatAuth auth = null;
		if (code != null) {
			UserAccessToken token = null;
			try {
				token = WechatUtil.getUserAccessToken(code);
				log.debug("wechat login token: " + token.toString());
				String accessToken = token.getAccessToken();
				openId = token.getOpenId();
				user = WechatUtil.getUserInfo(accessToken, openId);
				log.debug("wechat login user: " + user.toString());
				request.getSession().setAttribute("openId", openId);
				auth = wechatAuthService.getWechatAuthByOpenId(openId);
			} catch (IOException e) {
				// TODO: handle exception
				log.error("error in getUserAccessToken or getUserInfo or findByOpenId: " + e.toString());
				e.printStackTrace();
			}
		}
		if (auth == null) {
			PersonInfo person = WechatUtil.getPersonInfoFromRequest(user);
			auth = new WeChatAuth();
			if (FRONTEND.equals(roleType)) {
				person.setUserType(1);
			} else {
				person.setUserType(2);
			}
			auth.setPersonInfo(person);
			auth.setOpenId(openId);
			WechatAuthExecution wae = wechatAuthService.register(auth);
			if (wae.getState() != WechatAuthStateEnum.SUCCESS.getState()) {
				return null;
			}
			else {
				person = personInfoService.getPersonInfo(auth.getPersonInfo().getUserId());
				request.getSession().setAttribute("user", person);
			}
		}
		else {
			request.getSession().setAttribute("user", auth.getPersonInfo());
		}
		if (SHOPEND.equals(roleType)) {
			return "shop/shoplist";
		} else {
			return "frontend/index";
		}
	}
}
