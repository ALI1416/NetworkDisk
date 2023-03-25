package com.ck.dao;

import java.util.List;

import com.ck.po.Folder;
import com.ck.vo.FolderVo;

public interface FolderDao {
	/**
	 * 插入。
	 * 
	 * @param folder
	 * @return
	 */
	public Integer insert(FolderVo folder);

	/**
	 * 查询通过id。
	 * 
	 * @param id
	 * @return
	 */
	public List<Folder> findById(Integer id);

	/**
	 * 更新。
	 * 
	 * @param folder
	 * @return
	 */
	public Integer update(FolderVo folder);

	/**
	 * 查询。文件名、描述全文索引查询，时间范围查询，其他精确查询。
	 * 
	 * @param folder
	 * @return
	 */
	public List<Folder> find(FolderVo folder);
}
