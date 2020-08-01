package com.wong.o2oboot.service;

import java.util.List;

import com.wong.o2oboot.dto.ImageHolder;
import com.wong.o2oboot.dto.ShopCategoryExecution;
import com.wong.o2oboot.entity.ShopCategory;


public interface ShopCategoryService {
	
	public static final String SHOPCATELIST = "shopcategorylist";
	
	/**
	 * 
	 * @param shopCategoryCondition
	 * @return
	 */
	List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition);
	
	/**
	 * 
	 * @param shopCategory
	 * @param thumbnail
	 * @return
	 */
	ShopCategoryExecution addShopCategory(ShopCategory shopCategory, ImageHolder thumbnail);
}
