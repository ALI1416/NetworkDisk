package com.ck.dao;

import java.util.List;

import com.ck.po.HistoryDownload;
import com.ck.vo.HistoryDownloadVo;

public interface HistoryDownloadDao {
	/**
	 * 插入。
	 * 
	 * @param historyDownload
	 * @return
	 */
	public Integer insert(HistoryDownloadVo historyDownload);

	/**
	 * 查询通过id。
	 * 
	 * @param id
	 * @return
	 */
	public HistoryDownload findById(Integer id);

	/**
	 * 更新。
	 * 
	 * @param historyDownload
	 * @return
	 */
	public Integer update(HistoryDownloadVo historyDownload);

	/**
	 * 查询。文件名、描述全文索引查询，时间范围查询，其他精确查询。
	 * 
	 * @param historyDownload
	 * @return
	 */
	public List<HistoryDownload> find(HistoryDownloadVo historyDownload);
}
