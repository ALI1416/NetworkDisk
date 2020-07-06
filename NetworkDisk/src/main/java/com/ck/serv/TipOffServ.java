package com.ck.serv;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ck.dao.TipOffDao;
import com.ck.po.TipOff;
import com.ck.vo.TipOffVo;

@Service
public class TipOffServ {
	@Autowired
	private TipOffDao tipOffDao;

	public Integer insert(TipOffVo tipOff) {
		Integer ok = 0;
		try {
			ok = tipOffDao.insert(tipOff);
		} catch (Exception e) {
			return ok;
		}
		return ok;
	}

	public TipOff findById(Integer id) {
		TipOff ok = null;
		try {
			ok = tipOffDao.findById(id);
		} catch (Exception e) {
			return ok;
		}
		return ok;
	}

	public Integer update(TipOffVo tipOff) {
		Integer ok = 0;
		try {
			ok = tipOffDao.update(tipOff);
		} catch (Exception e) {
			return ok;
		}
		return ok;
	}

	public List<TipOff> find(TipOffVo tipOff) {
		List<TipOff> ok = null;
		try {
			ok = tipOffDao.find(tipOff);
		} catch (Exception e) {
			System.out.println(e);
			return ok;
		}
		System.out.println(ok);
		return ok;
	}

	public List<TipOff> findByUserId(Integer id) {
		List<TipOff> ok = null;
		try {
			TipOffVo tipOff = new TipOffVo();
			tipOff.setUserid(id);
			ok = tipOffDao.find(tipOff);
		} catch (Exception e) {
			return ok;
		}
		return ok;
	}

	public List<TipOff> findByFileId(Integer id) {
		List<TipOff> ok = null;
		try {
			TipOffVo tipOff = new TipOffVo();
			tipOff.setFileid(id);
			ok = tipOffDao.find(tipOff);
		} catch (Exception e) {
			return ok;
		}
		return ok;
	}
}
