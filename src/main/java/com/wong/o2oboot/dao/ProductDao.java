package com.wong.o2oboot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wong.o2oboot.entity.Product;

public interface ProductDao {

	/**
	 * 
	 * @param product
	 * @return
	 */
	int insertProduct(Product product);

	/**
	 * 
	 * @param productId
	 * @return
	 */
	Product queryProductById(long productId);

	/**
	 * 
	 * @param product
	 * @return
	 */
	int updateProduct(Product product);

	/**
	 * 
	 * @param productCategoryId
	 * @return
	 */
	int updateProductCategoryToNull(long productCategoryId);
	
	/**
	 * 
	 * @param productCondition
	 * @param rowIndex
	 * @param pageSize
	 * @return
	 */
	List<Product> queryProductList(@Param("productCondition") Product productCondition, @Param("rowIndex") int rowIndex,
			@Param("pageSize") int pageSize);
	
	/**
	 * 
	 * @param productCondition
	 * @return
	 */
	int queryProductCount(@Param("productCondition") Product productCondition);
}
