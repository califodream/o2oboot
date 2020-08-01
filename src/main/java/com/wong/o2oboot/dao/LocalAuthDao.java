package com.wong.o2oboot.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.wong.o2oboot.entity.LocalAuth;

public interface LocalAuthDao {

	/**
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	LocalAuth queryLocalByNameAndPass(@Param("username") String username, @Param("password") String password);

	/**
	 * 
	 * @param userId
	 * @return
	 */
	LocalAuth queryLocalByUserId(@Param("userId") long userId);

	/**
	 * 
	 * @param localAuth
	 * @return
	 */
	int insertLocalAuth(LocalAuth localAuth);

	/**
	 * 
	 * @param userId
	 * @param username
	 * @param password
	 * @param newPassword
	 * @param lastEditTime
	 * @return
	 */
	int updateLocalAuth(@Param("userId") long userId, @Param("username") String username,
			@Param("password") String password, @Param("newPassword") String newPassword,
			@Param("lastEditTime") Date lastEditTime);
}
