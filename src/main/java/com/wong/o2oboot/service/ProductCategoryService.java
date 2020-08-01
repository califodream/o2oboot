package com.wong.o2oboot.service;

import java.util.List;

import com.wong.o2oboot.dto.ProductCategoryExecution;
import com.wong.o2oboot.entity.ProductCategory;
import com.wong.o2oboot.exceptions.ProductCategoryOperationException;

public interface ProductCategoryService {

	/**
	 * 
	 * @param shopId
	 * @return
	 */
	List<ProductCategory> getProductCategoryList(long shopId);

	/**
	 * 
	 * @param productCategoryList
	 * @return
	 * @throws ProductCategoryOperationException
	 */
	ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList)
			throws ProductCategoryOperationException;

	/**
	 * 
	 * @param productCategoryId
	 * @param shopId
	 * @return
	 * @throws ProductCategoryOperationException
	 */
	ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId)
			throws ProductCategoryOperationException;
}
