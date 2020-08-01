package com.wong.o2oboot.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wong.o2oboot.dao.PersonInfoDao;
import com.wong.o2oboot.dao.WechatAuthDao;
import com.wong.o2oboot.dto.WechatAuthExecution;
import com.wong.o2oboot.entity.PersonInfo;
import com.wong.o2oboot.entity.WeChatAuth;
import com.wong.o2oboot.enums.WechatAuthStateEnum;
import com.wong.o2oboot.exceptions.WechatAuthOperationException;
import com.wong.o2oboot.service.WechatAuthService;

@Service
public class WechatAuthServiceImpl implements WechatAuthService {

	private static Logger log = LoggerFactory.getLogger(WechatAuthService.class);

	@Autowired
	private WechatAuthDao wechatAuthDao;

	@Autowired
	private PersonInfoDao personInfoDao;

	@Override
	public WeChatAuth getWechatAuthByOpenId(String openId) {
		// TODO Auto-generated method stub
		return wechatAuthDao.queryWechatInfoByOpenId(openId);
	}

	@Override
	@Transactional
	public WechatAuthExecution register(WeChatAuth wechatAuth) throws WechatAuthOperationException {
		if (wechatAuth == null || wechatAuth.getOpenId() == null) {
			return new WechatAuthExecution(WechatAuthStateEnum.NULL_AUTH_INFO);
		}
		try {
			wechatAuth.setCreateTime(new Date());
			if (wechatAuth.getPersonInfo() != null && wechatAuth.getPersonInfo().getUserId() == null) {
				try {
					wechatAuth.getPersonInfo().setCreateTime(new Date());
					wechatAuth.getPersonInfo().setLastEditTime(new Date());
					wechatAuth.getPersonInfo().setEnableStatus(1);
					PersonInfo personInfo = wechatAuth.getPersonInfo();
					int effectedNum = personInfoDao.insertPersonInfo(personInfo);
					wechatAuth.setPersonInfo(personInfo);
					if (effectedNum <= 0) {
						throw new WechatAuthOperationException("Add User Failed");
					}
				} catch (Exception e) {
					// TODO: handle exception
					log.error("Insert PersonInfo Error " + e.toString());
					throw new WechatAuthOperationException("Insert PersonInfo Error " + e.getMessage());
				}
			}
			int effectedNum = wechatAuthDao.insertWechatAuth(wechatAuth);
			if(effectedNum <= 0) {
				throw new WechatAuthOperationException("Add WechatAuth Failed");
			}
			else {
				return new WechatAuthExecution(WechatAuthStateEnum.SUCCESS, wechatAuth);
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.error("Insert WechatAuth Error " + e.toString());
			throw new WechatAuthOperationException("Insert WechatAuth Error " + e.getMessage());
		}
	}

}
