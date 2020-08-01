package com.wong.o2oboot.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wong.o2oboot.dao.ShopAuthMapDao;
import com.wong.o2oboot.dto.ShopAuthMapExecution;
import com.wong.o2oboot.entity.ShopAuthMap;
import com.wong.o2oboot.enums.ShopAuthMapStateEnum;
import com.wong.o2oboot.exceptions.ShopAuthMapOperationException;
import com.wong.o2oboot.service.ShopAuthMapService;
import com.wong.o2oboot.util.PageCalculator;

@Service
public class ShopAuthMapServiceImpl implements ShopAuthMapService {

	@Autowired
	private ShopAuthMapDao shopAuthMapDao;

	@Override
	public ShopAuthMapExecution listShopAuthMapByShopId(Long shopId, Integer pageIndex, Integer pageSize) {
		// TODO Auto-generated method stub
		if (shopId != null && pageIndex != null && pageSize != null) {
			List<ShopAuthMap> mapList = null;
			int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
			mapList = shopAuthMapDao.queryShopAuthMapListByShopId(shopId, rowIndex, pageSize);
			int count = shopAuthMapDao.queryShopAuthCountByShopId(shopId);
			ShopAuthMapExecution same = new ShopAuthMapExecution();
			same.setCount(count);
			same.setShopAuthMapList(mapList);
			same.setState(ShopAuthMapStateEnum.SUCCESS.getState());
			return same;
		} else {
			return new ShopAuthMapExecution(ShopAuthMapStateEnum.NULL_SHOPAUTH_ID);
		}
	}

	@Override
	public ShopAuthMap getShopAuthMapById(Long shopAuthId) {
		// TODO Auto-generated method stub
		return shopAuthMapDao.queryShopAuthMapById(shopAuthId);
	}

	@Override
	@Transactional
	public ShopAuthMapExecution addShopAuthMap(ShopAuthMap shopAuthMap) throws ShopAuthMapOperationException {
		// TODO Auto-generated method stub
		if (shopAuthMap != null && shopAuthMap.getShop() != null && shopAuthMap.getShop().getShopId() != null
				&& shopAuthMap.getEmployee() != null && shopAuthMap.getEmployee().getUserId() != null) {
			shopAuthMap.setCreateTime(new Date());
			shopAuthMap.setLastEditTime(new Date());
			shopAuthMap.setEnableStatus(1);
			try {
				int effectNum = shopAuthMapDao.insertShopAuthMap(shopAuthMap);
				if(effectNum <= 0) {
					return new ShopAuthMapExecution(ShopAuthMapStateEnum.INNER_ERROR);
				}
				return new ShopAuthMapExecution(ShopAuthMapStateEnum.SUCCESS, shopAuthMap);
			} catch (Exception e) {
				// TODO: handle exception
				throw new ShopAuthMapOperationException("Failed Insert ShopAuthMap " + e.toString());
			}
		} else {
			return new ShopAuthMapExecution(ShopAuthMapStateEnum.NULL_SHOPAUTH_INFO);
		}
	}

	@Override
	@Transactional
	public ShopAuthMapExecution modifyShopAuthMap(ShopAuthMap shopAuthMap) throws ShopAuthMapOperationException {
		// TODO Auto-generated method stub
		if (shopAuthMap != null && shopAuthMap.getShopAuthId() != null) {
			shopAuthMap.setLastEditTime(new Date());
			try {
				int effectNum = shopAuthMapDao.updateShopAuthMap(shopAuthMap);
				if(effectNum <= 0) {
					return new ShopAuthMapExecution(ShopAuthMapStateEnum.INNER_ERROR);
				}
				return new ShopAuthMapExecution(ShopAuthMapStateEnum.SUCCESS, shopAuthMap);
			} catch (Exception e) {
				// TODO: handle exception
				throw new ShopAuthMapOperationException("Failed Update ShopAuthMap " + e.toString());
			}
		} else {
			return new ShopAuthMapExecution(ShopAuthMapStateEnum.NULL_SHOPAUTH_INFO);
		}
	}
}
