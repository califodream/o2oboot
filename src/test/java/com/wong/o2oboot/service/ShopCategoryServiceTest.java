package com.wong.o2oboot.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wong.o2oboot.dto.ImageHolder;
import com.wong.o2oboot.dto.ShopCategoryExecution;
import com.wong.o2oboot.entity.ShopCategory;
import com.wong.o2oboot.enums.ShopCategoryStateEnum;
import com.wong.o2oboot.exceptions.ShopCategoryOperationException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopCategoryServiceTest {
	
	@Autowired
	private ShopCategoryService shopCategoryService;
	
	@Test
	public void testGetShopCategoryList() {
		List<ShopCategory> shopCategoryList = shopCategoryService.getShopCategoryList(null);
		assertEquals(6, shopCategoryList.size());
	}
	
	@Test
	@Ignore
	public void testAddShopCategory() throws FileNotFoundException, ShopCategoryOperationException {
		ShopCategory shopCategory = new ShopCategory();
		ShopCategory parentCategory = new ShopCategory();
		parentCategory.setShopCategoryId(13L);
		shopCategory.setShopCategoryName("交通工具");
		shopCategory.setShopCategoryDesc("交通工具");
		shopCategory.setPriority(69);
		shopCategory.setParent(parentCategory);
		File thumbnail = new File("/Users/wong/Desktop/2017060422121144586.png");
		InputStream is = new FileInputStream(thumbnail);
		ImageHolder img = new ImageHolder(thumbnail.getName(), is);
		ShopCategoryExecution se = shopCategoryService.addShopCategory(shopCategory, img);
		assertEquals(ShopCategoryStateEnum.SUCCESS.getState(), se.getState());
	}
}
