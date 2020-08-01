package com.wong.o2oboot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wong.o2oboot.entity.Award;

public interface AwardDao {

	/**
	 * 
	 * @param awardCondition
	 * @param rowIndex
	 * @param pageSize
	 * @return
	 */
	List<Award> queryAwardList(@Param("awardCondition") Award awardCondition, @Param("rowIndex") int rowIndex,
			@Param("pageSize") int pageSize);
	
	/**
	 * 
	 * @param awardCondition
	 * @return
	 */
	int queryAwardCount(@Param("awardCondition")Award awardCondition);
	
	/**
	 * 
	 * @param awardId
	 * @return
	 */
	Award queryAwardById(long awardId);
	
	/**
	 * 
	 * @param award
	 * @return
	 */
	int insertAward(Award award);
	
	/**
	 * 
	 * @param award
	 * @return
	 */
	int updateAward(Award award);
	
	/**
	 * 
	 * @param awardId
	 * @param shopId
	 * @return
	 */
	int deleteAward(@Param("awardId")long awardId, @Param("shopId")long shopId);
	
}
