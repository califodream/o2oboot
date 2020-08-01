package com.wong.o2oboot.web.wechat;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wong.o2oboot.util.wechat.SignUtil;

@Controller
@RequestMapping("wechat")
public class WechatController {

	private static Logger log = LoggerFactory.getLogger(WechatController.class);
	
	@RequestMapping(method = { RequestMethod.GET })
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		log.debug("wechat get......");
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		
		PrintWriter out = null;
		try {
			out = response.getWriter();
			if(SignUtil.checkSignature(signature, timestamp, nonce)) {
				log.debug("wechat get success......");
				out.print(echostr);
			}
		} catch (IOException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		finally {
			if(out != null)
				out.close();
		}
	}
}
