package com.wong.o2oboot.service;

import static org.junit.Assert.assertEquals;

import org.junit.FixMethodOrder;
//import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wong.o2oboot.dto.ShopAuthMapExecution;
import com.wong.o2oboot.entity.PersonInfo;
import com.wong.o2oboot.entity.Shop;
import com.wong.o2oboot.entity.ShopAuthMap;
import com.wong.o2oboot.enums.ShopAuthMapStateEnum;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ShopAuthServiceTest {

	@Autowired
	private ShopAuthMapService shopAuthMapService;
	
	@Test
	public void testAAddShopAuthMapNQuery() {
		Shop shop = new Shop();
		shop.setShopId(34L);
		PersonInfo employee = new PersonInfo();
		employee.setUserId(8L);
		ShopAuthMap auth = new ShopAuthMap();
		auth.setShop(shop);
		auth.setEmployee(employee);
		auth.setTitle("店员");
		auth.setTitleFlag(1);
		auth.setEnableStatus(1);
		ShopAuthMapExecution same = shopAuthMapService.addShopAuthMap(auth);
		assertEquals(ShopAuthMapStateEnum.SUCCESS.getState(), same.getState());
		ShopAuthMap tmp = shopAuthMapService.getShopAuthMapById(same.getShopAuthMap().getShopAuthId());
		System.out.println(tmp.getShopAuthId());
	}
	
	@Test
	public void testBListShopAuthMapById() {
		ShopAuthMapExecution same = shopAuthMapService.listShopAuthMapByShopId(34L, 0, 2);
		assertEquals(ShopAuthMapStateEnum.SUCCESS.getState(), same.getState());
		System.out.println(same.getShopAuthMapList().size());
	}
	
	@Test
	public void testCModifyShopAuthMap() {
		ShopAuthMap map = new ShopAuthMap();
		map.setShopAuthId(8L);
		map.setTitle("老板");
		map.setTitleFlag(0);
		ShopAuthMapExecution same = shopAuthMapService.modifyShopAuthMap(map);
		assertEquals(ShopAuthMapStateEnum.SUCCESS.getState(), same.getState());
	}
	
}
