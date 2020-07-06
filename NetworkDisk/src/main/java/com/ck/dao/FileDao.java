package com.ck.dao;

import java.util.List;

import com.ck.po.File;
import com.ck.vo.FileVo;

public interface FileDao {
	/**
	 * 插入。
	 * 
	 * @param file
	 * @return
	 */
	public Integer insert(FileVo file);

	/**
	 * 查询通过id。
	 * 
	 * @param id
	 * @return
	 */
	public File findById(Integer id);

	/**
	 * 更新。
	 * 
	 * @param file
	 * @return
	 */
	public Integer update(FileVo file);

	/**
	 * 增加浏览次数1次
	 * 
	 * @param id
	 * @return
	 */
	public Integer addBrowsing(Integer id);

	/**
	 * 增加下载次数1次
	 * 
	 * @param id
	 * @return
	 */
	public Integer addDownload(Integer id);

	/**
	 * 查询。文件名、描述全文索引查询，时间范围查询，其他精确查询。
	 * 
	 * @param file
	 * @return
	 */
	public List<File> find(FileVo file);

	/**
	 * 精确查询。
	 * 
	 * @param file
	 * @return
	 */
	public List<File> findExact(FileVo file);
}
