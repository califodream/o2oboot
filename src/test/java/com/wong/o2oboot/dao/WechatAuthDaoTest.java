package com.wong.o2oboot.dao;

import static org.junit.Assert.assertEquals;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wong.o2oboot.entity.PersonInfo;
import com.wong.o2oboot.entity.WeChatAuth;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class WechatAuthDaoTest {

	@Autowired
	private WechatAuthDao wechatAuthDao;
	
	@Test
	public void testAInsertWechatAuth() {
		WeChatAuth wechat = new WeChatAuth();
		PersonInfo person = new PersonInfo();
		person.setUserId(3L);
		wechat.setPersonInfo(person);
		wechat.setOpenId("3l4y");
		int effectedNum = wechatAuthDao.insertWechatAuth(wechat);
		assertEquals(1,effectedNum);
	}
	
	@Test
	public void testBQueryWechatInfoByOpenId() {
		WeChatAuth wechat = wechatAuthDao.queryWechatInfoByOpenId("3l4y");
		System.out.println(wechat.getOpenId());
		System.out.println(wechat.getPersonInfo().getUserId());
	}
	
}
