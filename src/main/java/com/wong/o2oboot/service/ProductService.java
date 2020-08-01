package com.wong.o2oboot.service;

import java.util.List;

import com.wong.o2oboot.dto.ImageHolder;
import com.wong.o2oboot.dto.ProductExecution;
import com.wong.o2oboot.entity.Product;
import com.wong.o2oboot.exceptions.ProductOperationException;

public interface ProductService {

	/**
	 * 
	 * @param product
	 * @param thumbnail
	 * @param productImgHolderList
	 * @return
	 * @throws ProductOperationException
	 */
	ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgHolderList)
			throws ProductOperationException;

	/**
	 * 
	 * @param productId
	 * @return
	 */
	Product getProductById(long productId);

	/**
	 * 
	 * @param product
	 * @param thumnail
	 * @param productHolderImgList
	 * @return
	 * @throws ProductOperationException
	 */
	ProductExecution modifyProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productHolderImgList)
			throws ProductOperationException;
	
	/**
	 * 
	 * @param productCondition
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize);
}
