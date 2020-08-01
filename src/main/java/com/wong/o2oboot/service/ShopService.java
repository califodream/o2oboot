package com.wong.o2oboot.service;

import com.wong.o2oboot.dto.ImageHolder;
import com.wong.o2oboot.dto.ShopExecution;
import com.wong.o2oboot.entity.Shop;
import com.wong.o2oboot.exceptions.ShopOperationException;

public interface ShopService {
	
	
	/**
	 * 
	 * @param shopCondition
	 * @param rowIndex
	 * @param pageSize
	 * @return
	 */
	public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize);
	
	/**
	 * 注册店铺信息，包括图片处理
	 * 
	 * @param shop
	 * @param shopImgInputStream
	 * @param fileName
	 * @return
	 * @throws ShopOperationException
	 */
	ShopExecution addShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException;
	
	/**
	 * 
	 * @param shopId
	 * @return
	 */
	Shop getByShopId(Long shopId);
	
	/**
	 * 更新店铺信息，包括图像处理
	 * 
	 * @param shop
	 * @param shopImgInputStream
	 * @param fileName
	 * @return
	 * @throws ShopOperationException
	 */
	ShopExecution modifyShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException;
}
