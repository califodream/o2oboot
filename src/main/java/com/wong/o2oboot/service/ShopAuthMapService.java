package com.wong.o2oboot.service;

import com.wong.o2oboot.dto.ShopAuthMapExecution;
import com.wong.o2oboot.entity.ShopAuthMap;
import com.wong.o2oboot.exceptions.ShopAuthMapOperationException;

public interface ShopAuthMapService {

	/**
	 * 
	 * @param shopId
	 * @param PageIndex
	 * @param pageSize
	 * @return
	 */
	ShopAuthMapExecution listShopAuthMapByShopId(Long shopId, Integer pageIndex, Integer pageSize);
	
	/**
	 * 
	 * @param shopAuthId
	 * @return
	 */
	ShopAuthMap getShopAuthMapById(Long shopAuthId);
	
	/**
	 * 
	 * @param shopAuthMap
	 * @return
	 * @throws ShopAuthMapOperationException
	 */
	ShopAuthMapExecution addShopAuthMap(ShopAuthMap shopAuthMap) throws ShopAuthMapOperationException;
	
	/**
	 * 
	 * @param shopAuthMap
	 * @return
	 * @throws ShopAuthMapOperationException
	 */
	ShopAuthMapExecution modifyShopAuthMap(ShopAuthMap shopAuthMap) throws ShopAuthMapOperationException;
}
