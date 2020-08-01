package com.wong.o2oboot.service;

import com.wong.o2oboot.dto.WechatAuthExecution;
import com.wong.o2oboot.entity.WeChatAuth;
import com.wong.o2oboot.exceptions.WechatAuthOperationException;

public interface WechatAuthService {

	/**
	 * 
	 * @param openId
	 * @return
	 */
	WeChatAuth getWechatAuthByOpenId(String openId);
	
	/**
	 * 
	 * @param wechatAuth
	 * @return
	 * @throws WechatAuthOperationException
	 */
	WechatAuthExecution register(WeChatAuth wechatAuth) throws WechatAuthOperationException;
}
