package com.wong.o2oboot.enums;

public enum ShopStateEnum {

	CHECK(0, "审核中"), OFFLINE(-1, "非法店铺"), SUCCESS(1, "操作成功"), PASS(2, "通过认证"), INNER_ERROR(-1001, "内部系统错误"),
	NULL_SHOPID(-1002, "SHOP ID为空"),NULL_SHOP(-1003, "SHOP为空");

	private int state;
	private String stateInfo;

	private ShopStateEnum(int i, String string) {
		// TODO Auto-generated constructor stub
		this.state = i;
		this.stateInfo = string;
	}
	
	/**
	 * 
	 * @param state
	 * @return
	 */
	public static ShopStateEnum stateOf(int state) {
		for(ShopStateEnum stateEnum : values()) {
			if(stateEnum.getState() == state) {
				return stateEnum;
			}
		}
		return null;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}
	
	
}
