package com.wong.o2oboot.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wong.o2oboot.cache.JedisUtil;
import com.wong.o2oboot.dao.AreaDao;
import com.wong.o2oboot.entity.Area;
import com.wong.o2oboot.exceptions.AreaOperationException;
import com.wong.o2oboot.service.AreaService;

@Service
public class AreaServiceImpl implements AreaService {

	@Autowired
	private AreaDao areaDao;

	@Autowired
	private JedisUtil.Keys jedisKeys;

	@Autowired
	private JedisUtil.Strings jedisStrings;

	private Logger logger = LoggerFactory.getLogger(AreaServiceImpl.class);

	@Override
	@Transactional
	public List<Area> getAreaList() {
		String key = AREALISTKEY;
		List<Area> areaList = null;
		ObjectMapper mapper = new ObjectMapper();
		if (!jedisKeys.exists(key)) {
			areaList = areaDao.queryArea();
			String jsonString;
			try {
				jsonString = mapper.writeValueAsString(areaList);
			} catch (JsonProcessingException e) {
				// TODO: handle exception
				e.printStackTrace();
				logger.error(e.getMessage());
				throw new AreaOperationException(e.getMessage());
			}
			jedisStrings.set(key, jsonString);
		} else {
			String jsonString = jedisStrings.get(key);
			JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, Area.class);
			try {
				areaList = mapper.readValue(jsonString, javaType);
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error(e.getMessage());
				throw new AreaOperationException(e.getMessage());
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error(e.getMessage());
				throw new AreaOperationException(e.getMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error(e.getMessage());
				throw new AreaOperationException(e.getMessage());
			}
		}
		// TODO Auto-generated method stub
		return areaList;
	}

}
