package com.wong.o2oboot.service;

import java.util.List;

import com.wong.o2oboot.dto.HeadLineExecution;
import com.wong.o2oboot.dto.ImageHolder;
import com.wong.o2oboot.entity.HeadLine;

public interface HeadLineService {
	
	public static final String HEADLINELISTKEY = "headlinelist";
	
	/**
	 * 
	 * @param headLineCondition
	 * @return
	 */
	List<HeadLine> getHeadLineList(HeadLine headLineCondition);
	
	/**
	 * 
	 * @param headLine
	 * @param thumbnail
	 * @return
	 */
	HeadLineExecution addHeadLine(HeadLine headLine, ImageHolder thumbnail);
}

