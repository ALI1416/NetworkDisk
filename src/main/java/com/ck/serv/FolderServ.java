package com.ck.serv;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ck.dao.FolderDao;
import com.ck.po.Folder;
import com.ck.vo.FolderVo;

@Service
public class FolderServ {
	@Autowired
	private FolderDao folderDao;

	public Integer insert(FolderVo folder) {
		Integer ok = 0;
		try {
			ok = folderDao.insert(folder);
		} catch (Exception e) {
			return ok;
		}
		return ok;
	}

	public List<Folder> findById(Integer id) {
		List<Folder> ok = null;
		try {
			ok = folderDao.findById(id);
		} catch (Exception e) {
			return ok;
		}
		return ok;
	}

	public List<Folder> findByFolderId(Integer id) {
		List<Folder> ok = null;
		try {
			FolderVo folder = new FolderVo();
			folder.setFolderid(id);
			ok = folderDao.find(folder);
		} catch (Exception e) {
			return ok;
		}
		return ok;
	}

	public Integer update(FolderVo folder) {
		Integer ok = 0;
		try {
			ok = folderDao.update(folder);
		} catch (Exception e) {
			return ok;
		}
		return ok;
	}

	public List<Folder> find(FolderVo folder) {
		List<Folder> ok = null;
		try {
			ok = folderDao.find(folder);
		} catch (Exception e) {
			return ok;
		}
		return ok;
	}
}
