package com.wong.o2oboot.web.local;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("local")
public class LocalController {

	@RequestMapping(value = "/accountbind", method = RequestMethod.GET)
	private String accountBind() {
		return "local/accountbind";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	private String login() {
		return "local/login";
	}

	@RequestMapping(value = "/changepass", method = RequestMethod.GET)
	private String changePass() {
		return "local/changepass";
	}
}
