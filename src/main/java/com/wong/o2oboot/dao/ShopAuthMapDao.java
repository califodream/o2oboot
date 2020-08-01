package com.wong.o2oboot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wong.o2oboot.entity.ShopAuthMap;

public interface ShopAuthMapDao {

	/**
	 * 
	 * @param shopId
	 * @param rowIndex
	 * @param pageSize
	 * @return
	 */
	List<ShopAuthMap> queryShopAuthMapListByShopId(@Param("shopId") long shopId, @Param("rowIndex") int rowIndex,
			@Param("pageSize") int pageSize);
	
	/**
	 * 
	 * @param shopId
	 * @return
	 */
	int queryShopAuthCountByShopId(@Param("shopId")long shopId);
	
	/**
	 * 
	 * @param shopAuthId
	 * @return
	 */
	ShopAuthMap queryShopAuthMapById(long shopAuthId);
	
	/**
	 * 
	 * @param shopAuthMap
	 * @return
	 */
	int insertShopAuthMap(ShopAuthMap shopAuthMap);
	
	/**
	 * 
	 * @param shopAuthMap
	 * @return
	 */
	int updateShopAuthMap(ShopAuthMap shopAuthMap);
	
	/**
	 * 
	 * @param shopAuthId
	 * @return
	 */
	int deleteShopAuthMap(long shopAuthId);
}
