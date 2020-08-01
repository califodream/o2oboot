package com.wong.o2oboot.dao;

import com.wong.o2oboot.entity.WeChatAuth;

public interface WechatAuthDao {

	/**
	 * 
	 * @param openId
	 * @return
	 */
	WeChatAuth queryWechatInfoByOpenId(String openId);
	
	/**
	 * 
	 * @param wechatAuth
	 * @return
	 */
	int insertWechatAuth(WeChatAuth wechatAuth);
}
