package com.wong.o2oboot.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wong.o2oboot.dto.ImageHolder;
import com.wong.o2oboot.dto.ProductExecution;
import com.wong.o2oboot.entity.Product;
import com.wong.o2oboot.entity.ProductCategory;
import com.wong.o2oboot.entity.Shop;
import com.wong.o2oboot.enums.ProductStateEnum;
import com.wong.o2oboot.exceptions.ProductOperationException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {

	@Autowired
	private ProductService productService;
	
	@Test
	@Ignore
	public void testAddProduct() throws ProductOperationException, FileNotFoundException {
		Shop shop = new Shop();
		shop.setShopId(21L);
		ProductCategory productCategory = new ProductCategory();
		productCategory.setProductCategoryId(9L);
		Product p1 = new Product();
		p1.setShop(shop);
		p1.setProductCategory(productCategory);
		p1.setProductName("Number1");
		p1.setProductDesc("Gionee");
		p1.setPriority(2);
		File thumbnail = new File("/Users/wong/Desktop/e1.jpg");
		InputStream is = new FileInputStream(thumbnail);
		ImageHolder image = new ImageHolder(thumbnail.getName(), is);
		File productImg1 = new File("/Users/wong/Desktop/e2.jpeg");
		InputStream is1 = new FileInputStream(productImg1);
		ImageHolder img1 = new ImageHolder(productImg1.getName(), is1);
		List<ImageHolder> imageList = new ArrayList<ImageHolder>();
		imageList.add(img1);
		ProductExecution pe = productService.addProduct(p1, image, imageList);
		assertEquals(ProductStateEnum.SUCCESS.getState(), pe.getState());
	}
	
	@Test
	@Ignore
	public void testModifyProduct() throws ProductOperationException, FileNotFoundException {
		Product product = new Product();
		product.setProductId(10L);
		Shop shop = new Shop();
		shop.setShopId(21L);
		ProductCategory productCategory = new ProductCategory();
		productCategory.setProductCategoryId(9L);
		product.setShop(shop);
		product.setProductCategory(productCategory);
		File thumbnail = new File("/Users/wong/Desktop/e2.jpeg");
		InputStream is = new FileInputStream(thumbnail);
		ImageHolder image = new ImageHolder(thumbnail.getName(), is);
		File productImg2 = new File("/Users/wong/Desktop/e1.jpg");
		InputStream is2 = new FileInputStream(productImg2);
		ImageHolder img2 = new ImageHolder(productImg2.getName(), is2);
		File productImg1 = new File("/Users/wong/Desktop/eminem.jpg");
		InputStream is1 = new FileInputStream(productImg1);
		ImageHolder img1 = new ImageHolder(productImg1.getName(), is1);
		List<ImageHolder> imageList = new ArrayList<ImageHolder>();
		imageList.add(img2);
		imageList.add(img1);
		ProductExecution pe = productService.modifyProduct(product, image, imageList);
		assertEquals(ProductStateEnum.SUCCESS.getState(), pe.getState());
	}
	
	@Test
	@Ignore
	public void testGetProductList() {
		Product productCondition = new Product();
		productCondition.setProductName("Hello");
		ProductExecution pe = productService.getProductList(productCondition, 1, 2);
		System.out.println(pe.getProductList().size());
		System.out.println(pe.getCount());
		System.out.println(pe.getProductList().get(0).getProductName());
		System.out.println(pe.getProductList().get(1).getProductName());
		
	}
}
