package com.wong.o2oboot.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wong.o2oboot.dto.HeadLineExecution;
import com.wong.o2oboot.dto.ImageHolder;
import com.wong.o2oboot.entity.HeadLine;
import com.wong.o2oboot.enums.HeadLineStateEnum;
import com.wong.o2oboot.exceptions.HeadLineOperationException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HeadLineServiceTest {

	@Autowired
	private HeadLineService headLineService;
	
	@Test
	public void testGetHeadLineList() {
		HeadLine headLineCondition = new HeadLine();
		headLineCondition.setEnableStatus(1);
		List<HeadLine> headLineList = headLineService.getHeadLineList(headLineCondition);
		System.out.println(headLineList.size());
		System.out.println(headLineList.get(0).getLineImg());
	}
	
	@Test
	@Ignore
	public void testAddHeadLine() throws FileNotFoundException, HeadLineOperationException {
		HeadLine h1 = new HeadLine();
		File thumbnail = new File("/Users/wong/Desktop/headtitle/2017061320400198256.jpg");
		InputStream is = new FileInputStream(thumbnail);
		ImageHolder img = new ImageHolder(thumbnail.getName(), is);
		h1.setLineLink("There");
		h1.setLineName("4");
		h1.setEnableStatus(1);
		h1.setPriority(4);
		HeadLineExecution he =  headLineService.addHeadLine(h1, img);
		assertEquals(HeadLineStateEnum.SUCCESS.getState(), he.getState());
	}
}
