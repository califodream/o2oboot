package com.wong.o2oboot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wong.o2oboot.entity.UserProductMap;

public interface UserProductMapDao {

	/**
	 * 
	 * @param mapCondition
	 * @param rowIndex
	 * @param pageSize
	 * @return
	 */
	List<UserProductMap> queryMapList(@Param("mapCondition") UserProductMap mapCondition,
			@Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);
	
	/**
	 * 
	 * @param mapCondition
	 * @return
	 */
	int queryMapCount(@Param("mapCondition") UserProductMap mapCondition);
	
	/**
	 * 
	 * @param map
	 * @return
	 */
	int insertMap(UserProductMap map);
}
