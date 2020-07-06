package com.ck.serv;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ck.dao.FileDao;
import com.ck.po.File;
import com.ck.vo.FileVo;

@Service
public class FileServ {
	@Autowired
	private FileDao fileDao;

	public Integer insert(FileVo file) {
		Integer ok = 0;
		try {
			ok = fileDao.insert(file);
		} catch (Exception e) {
			return ok;
		}
		return ok;
	}

	public File findById(Integer id) {
		File ok = null;
		try {
			ok = fileDao.findById(id);
		} catch (Exception e) {
			return ok;
		}
		return ok;
	}

	public File findByMD5(String md5) {
		File ok = null;
		try {
			FileVo file = new FileVo();
			file.setMd5(md5);
			ok = fileDao.find(file).get(0);
		} catch (Exception e) {
			return ok;
		}
		return ok;
	}

	public File findByTimestamp(String timestamp) {
		File ok = null;
		try {
			FileVo file = new FileVo();
			file.setTimestamp(timestamp);
			ok = fileDao.find(file).get(0);
		} catch (Exception e) {
			return ok;
		}
		return ok;
	}

	public File findByUuid(String uuid) {
		File ok = null;
		try {
			FileVo file = new FileVo();
			file.setUuid(uuid);
			ok = fileDao.find(file).get(0);
		} catch (Exception e) {
			return ok;
		}
		return ok;
	}

	public List<File> findByUserId(Integer userid) {
		List<File> ok = null;
		if (userid == 0) {
			return ok;
		}
		try {
			FileVo file = new FileVo();
			file.setUserid(userid);
			ok = fileDao.find(file);
		} catch (Exception e) {
			return ok;
		}
		return ok;
	}

	public Integer update(FileVo file) {
		Integer ok = 0;
		try {
			ok = fileDao.update(file);
		} catch (Exception e) {
			return ok;
		}
		return ok;
	}

	public Integer addBrowsing(Integer id) {
		Integer ok = 0;
		try {
			ok = fileDao.addBrowsing(id);
		} catch (Exception e) {
			return ok;
		}
		return ok;
	}

	public Integer addDownload(Integer id) {
		Integer ok = 0;
		try {
			ok = fileDao.addDownload(id);
		} catch (Exception e) {
			return ok;
		}
		return ok;
	}

	public List<File> find(FileVo file) {
		List<File> ok = null;
		try {
			ok = fileDao.find(file);
		} catch (Exception e) {
			return ok;
		}
		return ok;
	}
}
