package com.wong.o2oboot.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wong.o2oboot.entity.ProductImg;

//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductImgDaoTest {

	@Autowired
	private ProductImgDao productImgDao;
	
	@Test
	@Ignore
	public void testBatchInsertProductImg() {
		ProductImg productImg1 = new ProductImg();
		productImg1.setCreateTime(new Date());
		productImg1.setImgDesc("WiFi");
		productImg1.setImgAddr("Here");
		productImg1.setPriority(2);
		productImg1.setProductId(1L);
		ProductImg productImg2 = new ProductImg();
		productImg2.setCreateTime(new Date());
		productImg2.setImgDesc("Curtain");
		productImg2.setImgAddr("There");
		productImg2.setPriority(2);
		productImg2.setProductId(1L);
		List<ProductImg> productImgList = new ArrayList<ProductImg>();
		productImgList.add(productImg1);
		productImgList.add(productImg2);
		int effectedNum = productImgDao.batchInsertProductImg(productImgList);
		assertEquals(2, effectedNum);
	}
	
	@Test
	@Ignore
	public void testDeleteProductImgByProductId() {
		int effectedNum = productImgDao.deleteProductImgByProductId(1L);
		assertEquals(1, effectedNum);
	}
	
	@Test
	public void testQueryProductImgList() {
		List<ProductImg> productImgList = productImgDao.queryProductImgList(3L);
		assertEquals(1, productImgList.size());
	}
}
