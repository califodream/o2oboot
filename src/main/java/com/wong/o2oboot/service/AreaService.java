package com.wong.o2oboot.service;

import java.util.List;

import com.wong.o2oboot.entity.Area;

public interface AreaService {
	
	public static final String AREALISTKEY = "arealist";
	
	List<Area> getAreaList();
}
