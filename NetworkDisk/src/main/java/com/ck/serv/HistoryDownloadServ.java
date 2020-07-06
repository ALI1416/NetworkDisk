package com.ck.serv;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ck.dao.HistoryDownloadDao;
import com.ck.po.HistoryDownload;
import com.ck.vo.HistoryDownloadVo;

@Service
public class HistoryDownloadServ {
	@Autowired
	private HistoryDownloadDao historyDownloadDao;

	public Integer insert(HistoryDownloadVo historyDownload) {
		Integer ok = 0;
		try {
			ok = historyDownloadDao.insert(historyDownload);
		} catch (Exception e) {
			return ok;
		}
		return ok;
	}

	public HistoryDownload findById(Integer id) {
		HistoryDownload ok = null;
		try {
			ok = historyDownloadDao.findById(id);
		} catch (Exception e) {
			return ok;
		}
		return ok;
	}

	public Integer update(HistoryDownloadVo historyDownload) {
		Integer ok = 0;
		try {
			ok = historyDownloadDao.update(historyDownload);
		} catch (Exception e) {
			return ok;
		}
		return ok;
	}

	public List<HistoryDownload> find(HistoryDownloadVo historyDownload) {
		List<HistoryDownload> ok = null;
		try {
			ok = historyDownloadDao.find(historyDownload);
		} catch (Exception e) {
			return ok;
		}
		return ok;
	}

	public List<HistoryDownload> findByUserId(Integer id) {
		List<HistoryDownload> ok = null;
		try {
			HistoryDownloadVo historyDownload = new HistoryDownloadVo();
			historyDownload.setUserid(id);
			ok = historyDownloadDao.find(historyDownload);
		} catch (Exception e) {
			return ok;
		}
		return ok;
	}

	public List<HistoryDownload> findByFileId(Integer id) {
		List<HistoryDownload> ok = null;
		try {
			HistoryDownloadVo historyDownload = new HistoryDownloadVo();
			historyDownload.setFileid(id);
			ok = historyDownloadDao.find(historyDownload);
		} catch (Exception e) {
			return ok;
		}
		return ok;
	}
}
