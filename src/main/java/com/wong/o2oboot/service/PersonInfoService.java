package com.wong.o2oboot.service;

import com.wong.o2oboot.entity.PersonInfo;

public interface PersonInfoService {

	/**
	 * 
	 * @param userId
	 * @return
	 */
	PersonInfo getPersonInfo(long userId);
}
