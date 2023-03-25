package com.ck.serv;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ck.dao.HistoryBrowsingDao;
import com.ck.po.HistoryBrowsing;
import com.ck.vo.HistoryBrowsingVo;

@Service
public class HistoryBrowsingServ {
	@Autowired
	private HistoryBrowsingDao historyBrowsingDao;

	public Integer insert(HistoryBrowsingVo historyBrowsing) {
		Integer ok = 0;
		try {
			ok = historyBrowsingDao.insert(historyBrowsing);
		} catch (Exception e) {
			return ok;
		}
		return ok;
	}

	public HistoryBrowsing findById(Integer id) {
		HistoryBrowsing ok = null;
		try {
			ok = historyBrowsingDao.findById(id);
		} catch (Exception e) {
			return ok;
		}
		return ok;
	}

	public Integer update(HistoryBrowsingVo historyBrowsing) {
		Integer ok = 0;
		try {
			ok = historyBrowsingDao.update(historyBrowsing);
		} catch (Exception e) {
			return ok;
		}
		return ok;
	}

	public List<HistoryBrowsing> find(HistoryBrowsingVo historyBrowsing) {
		List<HistoryBrowsing> ok = null;
		try {
			ok = historyBrowsingDao.find(historyBrowsing);
		} catch (Exception e) {
			return ok;
		}
		return ok;
	}

	public List<HistoryBrowsing> findByUserId(Integer id) {
		List<HistoryBrowsing> ok = null;
		try {
			HistoryBrowsingVo historyBrowsing = new HistoryBrowsingVo();
			historyBrowsing.setUserid(id);
			ok = historyBrowsingDao.find(historyBrowsing);
		} catch (Exception e) {
			return ok;
		}
		return ok;
	}

	public List<HistoryBrowsing> findByFileId(Integer id) {
		List<HistoryBrowsing> ok = null;
		try {
			HistoryBrowsingVo historyBrowsing = new HistoryBrowsingVo();
			historyBrowsing.setFileid(id);
			ok = historyBrowsingDao.find(historyBrowsing);
		} catch (Exception e) {
			return ok;
		}
		return ok;
	}
}
