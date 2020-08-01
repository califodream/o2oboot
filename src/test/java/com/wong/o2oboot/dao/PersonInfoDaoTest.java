package com.wong.o2oboot.dao;

import static org.junit.Assert.assertEquals;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wong.o2oboot.entity.PersonInfo;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonInfoDaoTest {

	@Autowired
	private PersonInfoDao personInfoDao;
	
	@Test
	public void testAInsertPersonInfo() {
		PersonInfo person = new PersonInfo();
		person.setName("Elon Mask");
		person.setGender("W");
		person.setUserType(1);
		person.setEnableStatus(1);
		int effectedNum = personInfoDao.insertPersonInfo(person);
		assertEquals(1,effectedNum);
	}
	
	@Test
	public void testBQueryPersonInfoById() {
		long userId = 2;
		PersonInfo person = personInfoDao.queryPersonInfoById(userId);
		System.out.println(person.getName());
	}
}
