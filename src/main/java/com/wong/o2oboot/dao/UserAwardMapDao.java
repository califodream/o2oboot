package com.wong.o2oboot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wong.o2oboot.entity.UserAwardMap;

public interface UserAwardMapDao {

	/**
	 * 
	 * @param mapCondition
	 * @param rowIndex
	 * @param pageSize
	 * @return
	 */
	List<UserAwardMap> queryMapList(@Param("mapCondition") UserAwardMap mapCondition, @Param("rowIndex") int rowIndex,
			@Param("pageSize") int pageSize);
	
	/**
	 * 
	 * @param mapCondition
	 * @return
	 */
	int queryMapCount(@Param("mapCondition") UserAwardMap mapCondition);
	
	/**
	 * 
	 * @param mapId
	 * @return
	 */
	UserAwardMap queryMapById(long mapId);
	
	/**
	 * 
	 * @param map
	 * @return
	 */
	int insertMap(UserAwardMap map);
	
	/**
	 * 
	 * @param map
	 * @return
	 */
	int updateMap(UserAwardMap map);
}
