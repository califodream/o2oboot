package com.wong.o2oboot.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wong.o2oboot.dao.ProductDao;
import com.wong.o2oboot.dao.ProductImgDao;
import com.wong.o2oboot.dto.ImageHolder;
import com.wong.o2oboot.dto.ProductExecution;
import com.wong.o2oboot.entity.Product;
import com.wong.o2oboot.entity.ProductImg;
import com.wong.o2oboot.enums.ProductStateEnum;
import com.wong.o2oboot.exceptions.ProductOperationException;
import com.wong.o2oboot.service.ProductService;
import com.wong.o2oboot.util.ImageUtil;
import com.wong.o2oboot.util.PageCalculator;
import com.wong.o2oboot.util.PathUtil;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Autowired
	private ProductImgDao productImgDao;

	@Override
	@Transactional
	public ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgHolderList)
			throws ProductOperationException {
		if (product != null && product.getShop() != null && product.getShop().getShopId() != null) {
			product.setCreateTime(new Date());
			product.setLastEditTime(new Date());
			product.setEnableStatus(1);
			if (thumbnail != null) {
				addThumbnail(product, thumbnail);
			}
			try {
				int effectedNum = productDao.insertProduct(product);
				if (effectedNum <= 0) {
					throw new ProductOperationException("Failed Add Product");
				}
			} catch (Exception e) {
				throw new ProductOperationException("Failed Add Product" + e.toString());
			}
			if (productImgHolderList != null && productImgHolderList.size() > 0) {
				addProductImgList(product, productImgHolderList);
			}
			return new ProductExecution(ProductStateEnum.SUCCESS, product);
		} else {
			return new ProductExecution(ProductStateEnum.EMPTY);
		}
	}

	/**
	 * 
	 * @param product
	 * @param thumbnail
	 */
	private void addThumbnail(Product product, ImageHolder thumbnail) {
		String dest = PathUtil.getProductImagePath(product.getShop().getShopId());
		String thumbnailAddr = ImageUtil.generateThumbnail(thumbnail, dest);
		System.out.println("line 68: " + thumbnailAddr);
		product.setImgAddr(thumbnailAddr);
		System.out.println("line 70: " + product.getImgAddr());
	}

	private void addProductImgList(Product product, List<ImageHolder> productImgHolderList) {
		String dest = PathUtil.getProductImagePath(product.getShop().getShopId());
		List<ProductImg> productImgList = new ArrayList<ProductImg>();
		// 遍历图片一次去处理，并添加进productImg实体类里
		for (ImageHolder productImgHolder : productImgHolderList) {
			String imgAddr = ImageUtil.generateNormalImg(productImgHolder, dest);
			ProductImg productImg = new ProductImg();
			productImg.setImgAddr(imgAddr);
			productImg.setProductId(product.getProductId());
			productImg.setCreateTime(new Date());
			productImgList.add(productImg);
		}
		// 如果确实是有图片需要添加的，就执行批量添加操作
		if (productImgList.size() > 0) {
			try {
				int effectedNum = productImgDao.batchInsertProductImg(productImgList);
				if (effectedNum <= 0) {
					throw new ProductOperationException("创建商品详情图片失败");
				}
			} catch (Exception e) {
				throw new ProductOperationException("创建商品详情图片失败:" + e.toString());
			}
		}
	}

	@Override
	public Product getProductById(long productId) {
		// TODO Auto-generated method stub
		return productDao.queryProductById(productId);
	}

	@Override
	@Transactional
	public ProductExecution modifyProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgHolderList)
			throws ProductOperationException {
		if(product != null && product.getShop() != null && product.getShop().getShopId() != null) {
			if(thumbnail != null) {
				Product tempProduct = productDao.queryProductById(product.getProductId());
				System.out.println("line 111: " + tempProduct.getImgAddr());
				if(tempProduct.getImgAddr() != null) {
					ImageUtil.deleteFileOrPath(tempProduct.getImgAddr());
				}
				addThumbnail(product, thumbnail);
				System.out.println("line 116: " + product.getImgAddr());
			}
			if(productImgHolderList != null && productImgHolderList.size() > 0) {
				System.out.println("productImgHolderList is not null");
				deleteProductImgList(product.getProductId());
				System.out.println("line 121: " + product.getImgAddr());
				addProductImgList(product, productImgHolderList);
				System.out.println("line 123: " + product.getImgAddr());
			}
			try {
				System.out.println("line 126: " + product.getImgAddr());
				product.setLastEditTime(new Date());
				int effectedNum = productDao.updateProduct(product);
				System.out.println(effectedNum);
				if(effectedNum <= 0) {
					throw new ProductOperationException("更新商品信息失败");
				}
				return new ProductExecution(ProductStateEnum.SUCCESS, product);
			}
			catch(Exception e) {
				throw new ProductOperationException("更新商品信息失败" + e.toString());
			}
		}
		else {
			return new ProductExecution(ProductStateEnum.EMPTY);
		}
	}
	/**
	 * 
	 * @param productId
	 */
	private void deleteProductImgList(long productId) {
		// 根据productId获取原来的图片
		List<ProductImg> productImgList = productImgDao.queryProductImgList(productId);
		// 干掉原来的图片
		for (ProductImg productImg : productImgList) {
			ImageUtil.deleteFileOrPath(productImg.getImgAddr());
		}
		// 删除数据库里原有图片的信息
		productImgDao.deleteProductImgByProductId(productId);
	}

	@Override
	public ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize) {
		int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
		List<Product> productList = productDao.queryProductList(productCondition, rowIndex, pageSize);
		int count = productDao.queryProductCount(productCondition);
		ProductExecution pe = new ProductExecution();
		pe.setProductList(productList);
		pe.setCount(count);
		// TODO Auto-generated method stub
		return pe;
	}

}
