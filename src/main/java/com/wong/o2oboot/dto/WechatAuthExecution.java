package com.wong.o2oboot.dto;

import java.util.List;

import com.wong.o2oboot.entity.WeChatAuth;
import com.wong.o2oboot.enums.WechatAuthStateEnum;

public class WechatAuthExecution {
	// 结果状态
	private int state;

	// 状态标识
	private String stateInfo;

	private int count;

	private WeChatAuth WeChatAuth;

	private List<WeChatAuth> WeChatAuthList;

	public WechatAuthExecution() {
	}

	// 失败的构造器
	public WechatAuthExecution(WechatAuthStateEnum stateEnum) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}

	// 成功的构造器
	public WechatAuthExecution(WechatAuthStateEnum stateEnum, WeChatAuth WeChatAuth) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.WeChatAuth = WeChatAuth;
	}

	// 成功的构造器
	public WechatAuthExecution(WechatAuthStateEnum stateEnum,
			List<WeChatAuth> WeChatAuthList) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.WeChatAuthList = WeChatAuthList;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public WeChatAuth getWeChatAuth() {
		return WeChatAuth;
	}

	public void setWeChatAuth(WeChatAuth WeChatAuth) {
		this.WeChatAuth = WeChatAuth;
	}

	public List<WeChatAuth> getWeChatAuthList() {
		return WeChatAuthList;
	}

	public void setWeChatAuthList(List<WeChatAuth> WeChatAuthList) {
		this.WeChatAuthList = WeChatAuthList;
	}

}
