package com.wong.o2oboot.web.local;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wong.o2oboot.dto.LocalAuthExecution;
import com.wong.o2oboot.entity.LocalAuth;
import com.wong.o2oboot.entity.PersonInfo;
import com.wong.o2oboot.enums.LocalAuthStateEnum;
import com.wong.o2oboot.service.LocalAuthService;
import com.wong.o2oboot.util.HttpServletRequestUtil;

@Controller
@RequestMapping(value = "local", method = { RequestMethod.GET, RequestMethod.POST })
public class LocalAuthController {

	@Autowired
	private LocalAuthService localAuthService;

	@RequestMapping(value = "/bindlocalauth", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> bindLocalAuth(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		String username = HttpServletRequestUtil.getString(request, "userName");
		String password = HttpServletRequestUtil.getString(request, "password");
		PersonInfo user = (PersonInfo) request.getSession().getAttribute("user");
		if (username != null && password != null && user != null && user.getUserId() != null) {
			LocalAuth auth = new LocalAuth();
			auth.setUsername(username);
			auth.setPassword(password);
			auth.setPersonInfo(user);
			LocalAuthExecution lae = localAuthService.bindLocalAuth(auth);
			if (lae.getState() == LocalAuthStateEnum.SUCCESS.getState()) {
				modelMap.put("success", true);
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", lae.getStateInfo());
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "用户名或密码不能为空");
		}
		return modelMap;
	}

	@RequestMapping(value = "/changepwd", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> changePwd(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		String username = HttpServletRequestUtil.getString(request, "userName");
		String password = HttpServletRequestUtil.getString(request, "password");
		String newPassword = HttpServletRequestUtil.getString(request, "newPassword");
		PersonInfo user = (PersonInfo) request.getSession().getAttribute("user");
		if (username != null && password != null && newPassword != null && user != null && user.getUserId() != null
				&& !password.equals(newPassword)) {
			try {
				LocalAuth auth = localAuthService.getLocalAuthByUserId(user.getUserId());
				if(auth == null || !auth.getUsername().equals(username)) {
					modelMap.put("success", false);
					modelMap.put("errMsg", "输入账号非本次登陆账号");
					return modelMap;
				}
				LocalAuthExecution lae = localAuthService.modifyLocalAuth(user.getUserId(), username, password, newPassword);
				if(lae.getState() == LocalAuthStateEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
				}
				else {
					modelMap.put("success", false);
					modelMap.put("errMsg", lae.getStateInfo());
				}
			} catch (Exception e) {
				// TODO: handle exception
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
				return modelMap;
			}
		}
		else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入密码");
		}
		return modelMap;
	}
	
	@RequestMapping(value = "/logincheck", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> loginCheck(HttpServletRequest request){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		String username = HttpServletRequestUtil.getString(request, "userName");
		String password = HttpServletRequestUtil.getString(request, "password");
		if(username != null && password != null) {
			LocalAuth localAuth = localAuthService.getLocalAuthByUserAndPass(username, password);
			if(localAuth != null) {
				modelMap.put("success", true);
				request.getSession().setAttribute("user", localAuth.getPersonInfo());
			}
			else {
				modelMap.put("success", false);
				modelMap.put("errMsg", "用户名或密码错误");
			}
		}
		else {
			modelMap.put("sucess", false);
			modelMap.put("errMsg", "用户名或密码均不能为空");
		}
		return modelMap;
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> logout(HttpServletRequest request){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		request.getSession().setAttribute("user", null);
		modelMap.put("success", true);
		return modelMap;
	}
}
