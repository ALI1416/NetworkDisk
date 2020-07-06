package com.ck.dao;

import java.util.List;

import com.ck.po.HistoryBrowsing;
import com.ck.vo.HistoryBrowsingVo;

public interface HistoryBrowsingDao {
	/**
	 * 插入。
	 * 
	 * @param historyBrowsing
	 * @return
	 */
	public Integer insert(HistoryBrowsingVo historyBrowsing);

	/**
	 * 查询通过id。
	 * 
	 * @param id
	 * @return
	 */
	public HistoryBrowsing findById(Integer id);

	/**
	 * 更新。
	 * 
	 * @param historyBrowsing
	 * @return
	 */
	public Integer update(HistoryBrowsingVo historyBrowsing);

	/**
	 * 查询。文件名、描述全文索引查询，时间范围查询，其他精确查询。
	 * 
	 * @param historyBrowsing
	 * @return
	 */
	public List<HistoryBrowsing> find(HistoryBrowsingVo historyBrowsing);
}
