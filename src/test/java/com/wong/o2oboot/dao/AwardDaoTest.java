package com.wong.o2oboot.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wong.o2oboot.entity.Award;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AwardDaoTest {

	@Autowired
	private AwardDao awardDao;
	
	@Ignore
	public void testAInsertAward() {
		Award award = new Award();
		award.setAwardName("First Award");
		award.setPoint(5);
		award.setEnableStatus(1);
		award.setShopId(32L);
		int effectNum = awardDao.insertAward(award);
		System.out.println(effectNum);
		assertEquals(1,effectNum);
	}
	
	@Ignore
	public void testBQueryAwardListNCount() {
		Award awardCondition = new Award();
		awardCondition.setAwardName("First Award");
		awardCondition.setShopId(32L);
		List<Award> awardList = awardDao.queryAwardList(awardCondition, 0, 2);
		int count = awardDao.queryAwardCount(awardCondition);
		System.out.println(awardList.get(0).getAwardName());
		System.out.println(count);
	}
	
	@Test
	public void testCUpdateAwardNQueryById() {
		Award award = new Award();
		award.setShopId(32L);
		award.setAwardId(1L);
		award.setAwardDesc("Hello Award");
		award.setCreateTime(new Date());
		award.setAwardName("Second Award");
		int effectNum = awardDao.updateAward(award);
		assertEquals(1,effectNum);
		Award tmp = new Award();
		tmp = awardDao.queryAwardById(award.getAwardId());
		System.out.println(tmp.getAwardName());
	}
	
	@Test
	public void testDDeleteAward() {
		int effectNum = awardDao.deleteAward(1L, 32L);
		System.out.println(effectNum);
	}
}
