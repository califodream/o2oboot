package com.wong.o2oboot.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wong.o2oboot.dao.ShopAuthMapDao;
import com.wong.o2oboot.dao.ShopDao;
import com.wong.o2oboot.dto.ImageHolder;
import com.wong.o2oboot.dto.ShopExecution;
import com.wong.o2oboot.entity.Shop;
import com.wong.o2oboot.entity.ShopAuthMap;
import com.wong.o2oboot.enums.ShopStateEnum;
import com.wong.o2oboot.exceptions.ShopOperationException;
import com.wong.o2oboot.service.ShopService;
import com.wong.o2oboot.util.ImageUtil;
import com.wong.o2oboot.util.PageCalculator;
import com.wong.o2oboot.util.PathUtil;

@Service
public class ShopServiceImpl implements ShopService {

	@Autowired
	private ShopDao shopDao;
	
	@Autowired
	private ShopAuthMapDao shopAuthMapDao;
	
	private static Logger log = LoggerFactory.getLogger(ShopServiceImpl.class);

	@Override
	@Transactional
	public ShopExecution addShop(Shop shop, ImageHolder thumbnail) {
		// TODO Auto-generated method stub
		if (shop == null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		}
		try {
			shop.setEnableStatus(1);
			shop.setCreateTime(new Date());
			shop.setLastEditTime(new Date());
			int effectedNum = shopDao.insertShop(shop);
			if (effectedNum <= 0) {
				log.error("Throw 0 EffectNum When Insert Shop Info");
				throw new ShopOperationException("FAILED ADD SHOP ");
			}
			if (thumbnail.getImage() != null) {
				// 存储图片操作
				try {
					System.out.println("line 45 " + thumbnail);
					addShopImg(shop, thumbnail);
				} catch (Exception e) {
					log.error("Add Shop Img Failed " + e.getMessage());
					throw new ShopOperationException("ADD SHO IMG EXCEPTION " + e.getMessage());
				}
				effectedNum = shopDao.updateShop(shop);
				if (effectedNum <= 0) {
					log.error("Update Shop Failed Line 63");
					throw new ShopOperationException("UPDATE SHOP FAILED");
				}
				ShopAuthMap shopAuthMap = new ShopAuthMap();
				shopAuthMap.setEmployee(shop.getOwner());
				shopAuthMap.setShop(shop);
				shopAuthMap.setTitle("店家");
				shopAuthMap.setTitleFlag(0);
				shopAuthMap.setCreateTime(new Date());
				shopAuthMap.setLastEditTime(new Date());
				shopAuthMap.setEnableStatus(1);
				try {
					effectedNum = shopAuthMapDao.insertShopAuthMap(shopAuthMap);
					if(effectedNum <= 0) {
						log.error("Failed Add ShopAuthMap");
						throw new ShopOperationException("Authorization Failed");
					}
				} catch (Exception e) {
					// TODO: handle exception
					log.error("Add ShopAuthMap Error " + e.getMessage());
					throw new ShopOperationException("Authrization Failed");
				}
			}
		} catch (Exception e) {
			log.error("Add Shop Error " + e.getMessage());
			throw new ShopOperationException("ADD SHOP ERROR");
		}
		return new ShopExecution(ShopStateEnum.CHECK, shop);
	}

	private void addShopImg(Shop shop, ImageHolder thumbnail) {
		// TODO Auto-generated method stub
		String dest = PathUtil.getShopImagePath(shop.getShopId());
		System.out.println("line: 64 " + dest);
		System.out.println("line: 65 " + thumbnail);
		String shopImgAddr = ImageUtil.generateThumbnail(thumbnail, dest);
		shop.setShopImg(shopImgAddr);
	}

	@Override
	public Shop getByShopId(Long shopId) {
		// TODO Auto-generated method stub
		return shopDao.queryByShopId(shopId);
	}

	@Override
	public ShopExecution modifyShop(Shop shop, ImageHolder thumbnail)
			throws ShopOperationException {
		// TODO Auto-generated method stub
		if (shop == null || shop.getShopId() == null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		} else {
			try {
				// 如果存在图片
				if (thumbnail.getImage() != null && thumbnail.getImageName() != null && !"".equals(thumbnail.getImageName())) {
					Shop tempShop = shopDao.queryByShopId(shop.getShopId());
					if (tempShop.getShopImg() != null) {
						ImageUtil.deleteFileOrPath(tempShop.getShopImg());
					}
					addShopImg(shop, thumbnail);
				}
				// 更新图片信息
				shop.setLastEditTime(new Date());
				int effectedNum = shopDao.updateShop(shop);
				if (effectedNum <= 0) {
					return new ShopExecution(ShopStateEnum.INNER_ERROR);
				} else {
					shop = shopDao.queryByShopId(shop.getShopId());
					return new ShopExecution(ShopStateEnum.SUCCESS, shop);
				}
			} catch (Exception e) {
				throw new ShopOperationException("Modify Shop ERROR: " + e.getMessage());
			}
		}
	}

	@Override
	public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
		List<Shop> shopList = shopDao.queryShopList(shopCondition, rowIndex, pageSize);
		int count = shopDao.queryShopCount(shopCondition);
		ShopExecution se = new ShopExecution();
		if(shopList != null) {
			se.setCount(count);
			se.setShopList(shopList);	
		}else {
			se.setState(ShopStateEnum.NULL_SHOP.getState());
		}
		return se;
	}

}
