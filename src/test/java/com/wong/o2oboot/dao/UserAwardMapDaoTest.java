package com.wong.o2oboot.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wong.o2oboot.entity.Award;
import com.wong.o2oboot.entity.PersonInfo;
import com.wong.o2oboot.entity.Shop;
import com.wong.o2oboot.entity.UserAwardMap;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserAwardMapDaoTest {

	@Autowired
	private UserAwardMapDao mapDao;
	
	@Ignore
	public void testAInsertMap() {
		UserAwardMap map = new UserAwardMap();
		PersonInfo user = new PersonInfo();
		user.setUserId(2L);
		Award award = new Award();
		award.setAwardId(2L);
		Shop shop = new Shop();
		shop.setShopId(32L);
		PersonInfo operator = new PersonInfo();
		operator.setUserId(8L);
		map.setUser(user);
		map.setAward(award);
		map.setShop(shop);
		map.setOperator(operator);
		map.setUsedStatus(1);
		int effectNum = mapDao.insertMap(map);
		assertEquals(1,effectNum);
		
	}
	
	@Ignore
	public void testBQueryMapListNCountNId() {
		UserAwardMap mapCondition = new UserAwardMap();
		mapCondition.setUsedStatus(1);
		List<UserAwardMap> mapList = mapDao.queryMapList(mapCondition, 0, 2);
		System.out.println(mapList.size());
		System.out.println(mapList.get(0).getUserAwardId());
		System.out.println(mapList.get(0).getUser().getUserId());
		long id = mapList.get(0).getUserAwardId();
		System.out.println(id);
		int count = mapDao.queryMapCount(mapCondition);
		System.out.println(count);
		UserAwardMap tmp = new UserAwardMap();
		tmp = mapDao.queryMapById(id);
		System.out.println(tmp.getUserAwardId());
	}
	
	@Test
	public void testDUpdateMap() {
		UserAwardMap map = new UserAwardMap();
		map.setUsedStatus(1);
		PersonInfo operator = new PersonInfo();
		operator.setUserId(8L);
		PersonInfo user = new PersonInfo();
		user.setUserId(2L);
		map.setUser(user);
		map.setOperator(operator);
		map.setUserAwardId(1L);
		int effectNum = mapDao.updateMap(map);
		assertEquals(1,effectNum);
	}
}
