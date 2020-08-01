package com.wong.o2oboot.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wong.o2oboot.entity.PersonInfo;
import com.wong.o2oboot.entity.Product;
import com.wong.o2oboot.entity.Shop;
import com.wong.o2oboot.entity.UserProductMap;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserProductMapDaoTest {

	@Autowired
	private UserProductMapDao mapDao;
	
	@Test
	public void testAInsertMap() {
		UserProductMap map = new UserProductMap();
		PersonInfo user = new PersonInfo();
		user.setUserId(2L);
		Product product = new Product();
		product.setProductId(2L);
		Shop shop = new Shop();
		shop.setShopId(32L);
		PersonInfo operator = new PersonInfo();
		operator.setUserId(8L);
		map.setUser(user);
		map.setProduct(product);
		map.setShop(shop);
		map.setOperator(operator);
		int effectNum = mapDao.insertMap(map);
		assertEquals(1,effectNum);
	}
	
	@Test
	public void testBQueryMapListNCount() {
		UserProductMap condition = new UserProductMap();
		PersonInfo user = new PersonInfo();
		user.setUserId(2L);
		Product product = new Product();
		product.setProductId(2L);
		Shop shop = new Shop();
		shop.setShopId(32L);
		PersonInfo operator = new PersonInfo();
		operator.setUserId(8L);
		condition.setUser(user);
		condition.setProduct(product);
		condition.setShop(shop);
		condition.setOperator(operator);
		int count = mapDao.queryMapCount(condition);
		List<UserProductMap> mapList = mapDao.queryMapList(condition, 0, 2);
		assertEquals(1,mapList.size());
		assertEquals(1,count);
	}
}
