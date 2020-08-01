package com.wong.o2oboot.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wong.o2oboot.entity.HeadLine;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HeadLineDaoTest {

	@Autowired
	private HeadLineDao headLineDao;
	
	@Test
	public void testQueryHeadLine() {
		HeadLine headLineCondition = new HeadLine();
		headLineCondition.setEnableStatus(1);
		List<HeadLine> headLineList = new ArrayList<HeadLine>();
		headLineList = headLineDao.queryHeadLine(headLineCondition);
		System.out.println(headLineList.size());
		assertEquals(4, headLineList.size());
	}
	
	@Test
	@Ignore
	public void testInsertHeadLine() {
		HeadLine h1 = new HeadLine();
		h1.setCreateTime(new Date());
		h1.setLastEditTime(new Date());
		h1.setEnableStatus(1);
		h1.setLineImg("There");
		h1.setLineName("Data Structure");
		h1.setLineLink("Here");
		int effectedNum = headLineDao.insertHeadLine(h1);
		assertEquals(1, effectedNum);
	}
}
