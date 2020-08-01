package com.wong.o2oboot.dao;

import com.wong.o2oboot.entity.PersonInfo;

public interface PersonInfoDao {

	/**
	 * 
	 * @param userId
	 * @return
	 */
	PersonInfo queryPersonInfoById(long userId);
	
	/**
	 * 
	 * @param personInfo
	 * @return
	 */
	int insertPersonInfo(PersonInfo personInfo);
}
