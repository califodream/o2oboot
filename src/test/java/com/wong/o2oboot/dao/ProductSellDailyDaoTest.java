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

import com.wong.o2oboot.entity.ProductSellDaily;
import com.wong.o2oboot.entity.Shop;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductSellDailyDaoTest {

	@Autowired
	private ProductSellDailyDao sellDailyDao;
	
	@Ignore
	public void testAInsertProductSellDaily() {
		int effectNum = sellDailyDao.insertProductSellDaily();
		assertEquals(3,effectNum);
	}
	
	@Test
	public void testBQueryProductSellDailyList() {
		ProductSellDaily condition = new ProductSellDaily();
		Shop shop = new Shop();
		shop.setShopId(32L);
		condition.setShop(shop);
		List<ProductSellDaily> list = sellDailyDao.queryProductSellDailyList(condition, null, null);
		assertEquals(1,list.size());
	}
}
