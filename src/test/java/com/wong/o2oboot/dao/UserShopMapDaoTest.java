package com.wong.o2oboot.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wong.o2oboot.entity.PersonInfo;
import com.wong.o2oboot.entity.Shop;
import com.wong.o2oboot.entity.UserShopMap;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserShopMapDaoTest {

	@Autowired
	private UserShopMapDao mapDao;
	
	@Test
	public void testAInsertNQuery() {
		Shop shop = new Shop();
		shop.setShopId(32L);
		PersonInfo user = new PersonInfo();
		user.setUserId(2L);
		UserShopMap map = new UserShopMap();
		map.setShop(shop);
		map.setUser(user);
		map.setPoint(5);
		map.setCreateTime(new Date());
		int effectNum = mapDao.insertUserShopMap(map);
		assertEquals(1,effectNum);
		UserShopMap tmp = mapDao.queryUserShopMap(2L, 32L);
		System.out.println(tmp.getPoint());
	}
	
	@Test
	public void testBUpdate() {
		Shop shop = new Shop();
		shop.setShopId(32L);
		PersonInfo user = new PersonInfo();
		user.setUserId(2L);
		UserShopMap map = new UserShopMap();
		map.setShop(shop);
		map.setUser(user);
		map.setPoint(7);
		int effectNum = mapDao.updateUserShopMapPoint(map);
		assertEquals(1,effectNum);
	}
	
	@Test
	public void testCQueryNCount() {
		UserShopMap userShopCondition = new UserShopMap();
		Shop shop = new Shop();
		shop.setShopId(32L);
		PersonInfo user = new PersonInfo();
		user.setUserId(2L);
		userShopCondition.setShop(shop);
		userShopCondition.setUser(user);
		List<UserShopMap> mapList = mapDao.queryUserShopMapList(userShopCondition, 0, 2);
		int count = mapDao.queryUserShopMapCount(userShopCondition);
		assertEquals(1,mapList.size());
		assertEquals(1,count);
	}
}
