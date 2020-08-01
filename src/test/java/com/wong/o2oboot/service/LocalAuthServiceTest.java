package com.wong.o2oboot.service;

import static org.junit.Assert.assertEquals;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wong.o2oboot.dto.LocalAuthExecution;
import com.wong.o2oboot.entity.LocalAuth;
import com.wong.o2oboot.entity.PersonInfo;
import com.wong.o2oboot.enums.LocalAuthStateEnum;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class LocalAuthServiceTest {

	@Autowired
	private LocalAuthService localAuthService;
	
	@Test
	@Ignore
	public void testABindLocalAuth() {
		LocalAuth auth = new LocalAuth();
		PersonInfo person = new PersonInfo();
		person.setUserId(3L);
		auth.setUsername("Elon");
		auth.setPassword("spacex");
		auth.setPersonInfo(person);
		LocalAuthExecution lae = localAuthService.bindLocalAuth(auth);
		assertEquals(LocalAuthStateEnum.SUCCESS.getState(),lae.getState());
		System.out.println(lae.getLocalAuth().getPersonInfo().getName());
		System.out.println(lae.getLocalAuth().getUsername());
		System.out.println(lae.getLocalAuth().getPassword());
	}
	
	@Test
	public void testBModifyLocalAuth() {
		LocalAuthExecution lae = localAuthService.modifyLocalAuth(3L, "Elon", "spacex", "spaceone");
		assertEquals(LocalAuthStateEnum.SUCCESS.getState(),lae.getState());
	}
}
