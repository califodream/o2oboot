package com.wong.o2oboot.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wong.o2oboot.entity.Area;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AreaServiceTest {

	@Autowired
	private AreaService areaService;
	
	@Autowired
	private CacheService cacheService;
	
	@Test
	public void testGetAreaList() {
		cacheService.removeFromCache(areaService.AREALISTKEY);
		List<Area> areaList = areaService.getAreaList();
		assertEquals(4, areaList.size());
		System.out.println(areaList.get(0).getAreaName());
		System.out.println(areaList.get(1).getAreaName());
	}
	
}
