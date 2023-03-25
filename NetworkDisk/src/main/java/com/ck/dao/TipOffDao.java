package com.ck.dao;

import java.util.List;

import com.ck.po.TipOff;
import com.ck.vo.TipOffVo;

public interface TipOffDao {

	/**
	 * 插入。
	 * 
	 * @param tipOff
	 * @return
	 */
	public Integer insert(TipOffVo tipOff);

	/**
	 * 查询通过id。
	 * 
	 * @param id
	 * @return
	 */
	public TipOff findById(Integer id);

	/**
	 * 更新。
	 * 
	 * @param tipOff
	 * @return
	 */
	public Integer update(TipOffVo tipOff);

	/**
	 * 精确查询。
	 * 
	 * @param tipOff
	 * @return
	 */
	public List<TipOff> find(TipOffVo tipOff);
}
