package com.ck.serv;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ck.dao.UserInfoDao;
import com.ck.po.UserInfo;
import com.ck.vo.UserInfoVo;

@Service
public class UserInfoServ {
	@Autowired
	private UserInfoDao userInfoDao;

	public Integer insert(UserInfoVo userInfo) {
		Integer ok = 0;
		try {
			ok = userInfoDao.insert(userInfo);
		} catch (Exception e) {
			return ok;
		}
		return ok;
	}

	public UserInfo findById(Integer id) {
		UserInfo ok = null;
		try {
			ok = userInfoDao.findById(id);
		} catch (Exception e) {
			return ok;
		}
		return ok;
	}

	public UserInfo findByNumber(String number) {
		UserInfo ok = null;
		try {
			UserInfoVo uiv = new UserInfoVo();
			uiv.setNumber(number);
			ok = userInfoDao.findExact(uiv).get(0);
		} catch (Exception e) {
			return ok;
		}
		return ok;
	}

	public UserInfo findByIdcard(String idcard) {
		UserInfo ok = null;
		try {
			UserInfoVo uiv = new UserInfoVo();
			uiv.setIdcard(idcard);
			ok = userInfoDao.findExact(uiv).get(0);
		} catch (Exception e) {
			return ok;
		}
		return ok;
	}

	public Integer update(UserInfoVo userInfo) {
		Integer ok = 0;
		try {
			ok = userInfoDao.update(userInfo);
		} catch (Exception e) {
			return ok;
		}
		return ok;
	}

	public UserInfo check(UserInfoVo userInfo) {
		UserInfo ok = null;
		try {
			UserInfoVo uiv = new UserInfoVo();
			uiv.setName(userInfo.getName());
			uiv.setNumber(userInfo.getNumber());
			uiv.setIdcard(userInfo.getIdcard());
			ok = userInfoDao.findExact(uiv).get(0);
		} catch (Exception e) {
			return ok;
		}
		return ok;
	}

	public List<UserInfo> find(UserInfoVo userInfo) {
		List<UserInfo> ok = null;
		try {
			ok = userInfoDao.find(userInfo);
		} catch (Exception e) {
			return ok;
		}
		return ok;
	}
}
