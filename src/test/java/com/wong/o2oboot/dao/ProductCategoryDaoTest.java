package com.wong.o2oboot.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
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

import com.wong.o2oboot.entity.ProductCategory;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryDaoTest {
	
	@Autowired
	private ProductCategoryDao productCategoryDao;
	
	@Test
	@Ignore
	public void testAQueryProductCategoryList() {
		long shopId = 14L;
		List<ProductCategory> productCategoryList = productCategoryDao.queryProductCategoryList(shopId);
		System.out.println(productCategoryList.size());
	}
	
	@Test
	public void testBBatchInsertProductCategory() {
		ProductCategory productCategory = new ProductCategory();
		productCategory.setProductCategoryName("Fans");
		productCategory.setPriority(4);
		productCategory.setCreateTime(new Date());
		productCategory.setShopId(16L);
		ProductCategory productCategory2 = new ProductCategory();
		productCategory2.setProductCategoryName("Nature Science");
		productCategory2.setPriority(3);
		productCategory2.setCreateTime(new Date());
		productCategory2.setShopId(16L);
		List<ProductCategory> productCategoryList = new ArrayList<ProductCategory>();
		productCategoryList.add(productCategory);
		productCategoryList.add(productCategory2);
		int effectedNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
		assertEquals(2, effectedNum);
	}
	
	@Test
	public void testCDeleteProductCategory() {
		int effectedNum = productCategoryDao.deleteProductCategory(7L, 21L);
		assertEquals(1, effectedNum);
	}

}
