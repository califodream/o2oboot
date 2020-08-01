package com.wong.o2oboot.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wong.o2oboot.entity.PersonInfo;
import com.wong.o2oboot.entity.Shop;
import com.wong.o2oboot.entity.ShopAuthMap;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ShopAuthMapDaoTest {

	@Autowired
	private ShopAuthMapDao mapDao;
	
	@Ignore
	public void testAInsertNUpdate() {
		Shop shop = new Shop();
		shop.setShopId(32L);
		PersonInfo user = new PersonInfo();
		user.setUserId(8L);
		ShopAuthMap map = new ShopAuthMap();
		map.setShop(shop);
		map.setEmployee(user);
		map.setEnableStatus(1);
		map.setCreateTime(new Date());
		int effectNum = mapDao.insertShopAuthMap(map);
		assertEquals(1,effectNum);
		ShopAuthMap tmp = new ShopAuthMap();
		tmp.setShop(shop);
		tmp.setEmployee(user);
		tmp.setLastEditTime(new Date());
		tmp.setShopAuthId(map.getShopAuthId());
		effectNum = mapDao.updateShopAuthMap(tmp);
		assertEquals(1,effectNum);
	}
	
	@Ignore
	public void testCQuery() {
		List<ShopAuthMap> mapList = mapDao.queryShopAuthMapListByShopId(32L, 0, 2);
		assertEquals(1,mapList.size());
		long tmpId = mapList.get(0).getShopAuthId();
		ShopAuthMap tmp = mapDao.queryShopAuthMapById(tmpId);
		System.out.println(tmp.getShop().getShopName());
		int count = mapDao.queryShopAuthCountByShopId(32L);
		assertEquals(1,count);
	}
	
	@Test
	public void testDDelete() {
		int effectNum = mapDao.deleteShopAuthMap(6L);
		assertEquals(1,effectNum);
	}
}
