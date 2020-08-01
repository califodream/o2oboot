package com.wong.o2oboot.service;

import static org.junit.Assert.assertEquals;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wong.o2oboot.dto.WechatAuthExecution;
import com.wong.o2oboot.entity.PersonInfo;
import com.wong.o2oboot.entity.WeChatAuth;
import com.wong.o2oboot.enums.WechatAuthStateEnum;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class WechatAuthServiceTest {

	@Autowired
	private WechatAuthService wechatAuthService;
	
	@Test
	public void testARegisterAndQuery() {
		WeChatAuth wechat = new WeChatAuth();
		PersonInfo person = new PersonInfo();
		String openId = "helloworld";
		person.setGender("M");
		person.setName("Bill Gates");
		person.setUserType(1);
		wechat.setPersonInfo(person);
		wechat.setOpenId(openId);
		WechatAuthExecution wae = wechatAuthService.register(wechat);
		assertEquals(WechatAuthStateEnum.SUCCESS.getState(),wae.getState());
		wechat = wechatAuthService.getWechatAuthByOpenId(openId);
		System.out.println(wechat.getOpenId());
		System.out.println(wechat.getPersonInfo().getName());
	}
}
