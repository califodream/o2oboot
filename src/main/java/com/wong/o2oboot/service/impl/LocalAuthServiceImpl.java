package com.wong.o2oboot.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wong.o2oboot.dao.LocalAuthDao;
import com.wong.o2oboot.dto.LocalAuthExecution;
import com.wong.o2oboot.entity.LocalAuth;
import com.wong.o2oboot.enums.LocalAuthStateEnum;
import com.wong.o2oboot.exceptions.LocalAuthOperationException;
import com.wong.o2oboot.service.LocalAuthService;
import com.wong.o2oboot.util.MD5;

@Service
public class LocalAuthServiceImpl implements LocalAuthService {

	@Autowired
	private LocalAuthDao localAuthDao;

	@Override
	public LocalAuth getLocalAuthByUserAndPass(String username, String password) {
		// TODO Auto-generated method stub
		return localAuthDao.queryLocalByNameAndPass(username, MD5.getMd5(password));
	}

	@Override
	public LocalAuth getLocalAuthByUserId(long userId) {
		// TODO Auto-generated method stub
		return localAuthDao.queryLocalByUserId(userId);
	}

	@Override
	@Transactional
	public LocalAuthExecution bindLocalAuth(LocalAuth localAuth) throws LocalAuthOperationException {
		if (localAuth == null || localAuth.getPassword() == null || localAuth.getUsername() == null
				|| localAuth.getPersonInfo() == null || localAuth.getPersonInfo().getUserId() == null) {
			return new LocalAuthExecution(LocalAuthStateEnum.NULL_AUTH_INFO);
		}
		LocalAuth temp = localAuthDao.queryLocalByUserId(localAuth.getPersonInfo().getUserId());
		if (temp != null) {
			return new LocalAuthExecution(LocalAuthStateEnum.ONLY_ONE_ACCOUNT);
		}
		try {
			localAuth.setCreateTime(new Date());
			localAuth.setLastEditTime(new Date());
			localAuth.setPassword(MD5.getMd5(localAuth.getPassword()));
			int effectedNum = localAuthDao.insertLocalAuth(localAuth);
			if (effectedNum <= 0) {
				throw new LocalAuthOperationException("Add LocalAuth Failed");
			} else {
				return new LocalAuthExecution(LocalAuthStateEnum.SUCCESS, localAuth);
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new LocalAuthOperationException("Add LocalAuth Failed " + e.getMessage());
		}
	}

	@Override
	@Transactional
	public LocalAuthExecution modifyLocalAuth(Long userId, String username, String password, String newPassword)
			throws LocalAuthOperationException {
		if (userId != null && username != null && password != null && newPassword != null
				&& !password.equals(newPassword)) {
			try {
				int effectedNum = localAuthDao.updateLocalAuth(userId, username, MD5.getMd5(password),
						MD5.getMd5(newPassword), new Date());
				if(effectedNum <= 0) {
					throw new LocalAuthOperationException("Update LocalAuth Failed");
				}
				else {
					return new LocalAuthExecution(LocalAuthStateEnum.SUCCESS);
				}
			} catch (Exception e) {
				throw new LocalAuthOperationException("Update LocalAuth Failed " + e.toString());
				// TODO: handle exception
			}
		}
		else {
			return new LocalAuthExecution(LocalAuthStateEnum.NULL_AUTH_INFO);
		}
	}
}
