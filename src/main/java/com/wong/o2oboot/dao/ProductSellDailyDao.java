package com.wong.o2oboot.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wong.o2oboot.entity.ProductSellDaily;

public interface ProductSellDailyDao {

	/**
	 * 
	 * @param productSellDailyCondition
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	List<ProductSellDaily> queryProductSellDailyList(
			@Param("productSellDailyCondition") ProductSellDaily productSellDailyCondition,
			@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);
	
	/**
	 * 
	 * @return
	 */
	int insertProductSellDaily();
	
	/**
	 * 
	 * @return
	 */
	//int insertDefaultProductSellDaily();
}
