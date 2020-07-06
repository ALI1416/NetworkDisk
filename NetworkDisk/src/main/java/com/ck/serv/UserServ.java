package com.ck.serv;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ck.dao.UserDao;
import com.ck.po.User;
import com.ck.vo.UserVo;

@Service
public class UserServ {
	@Autowired
	private UserDao userDao;

	public User findByAccount(String account) {
		User ok = null;
		try {
			UserVo uv = new UserVo();
			uv.setAccount(account);
			ok = userDao.findExact(uv).get(0);
		} catch (Exception e) {
			return ok;
		}
		return ok;
	}

	public Integer insert(UserVo user) {
		Integer ok = 0;
		try {
			ok = userDao.insert(user);
		} catch (Exception e) {
			return ok;
		}
		return ok;
	}

	public User findById(Integer id) {
		User ok = null;
		try {
			ok = userDao.findById(id);
		} catch (Exception e) {
			return ok;
		}
		return ok;
	}

	public User findByUserinfoid(Integer userinfoid) {
		User ok = null;
		try {
			UserVo uv = new UserVo();
			uv.setUserinfoid(userinfoid);
			ok = userDao.findExact(uv).get(0);
		} catch (Exception e) {
			return ok;
		}
		return ok;
	}

	public Integer update(UserVo user) {
		Integer ok = 0;
		try {
			ok = userDao.update(user);
		} catch (Exception e) {
			return ok;
		}
		return ok;
	}

	public List<User> find(UserVo user) {
		List<User> ok = null;
		try {
			ok = userDao.find(user);
		} catch (Exception e) {
			return ok;
		}
		return ok;
	}
}
