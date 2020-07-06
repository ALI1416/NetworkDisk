package com.ck.dao;

import java.util.List;

import com.ck.po.UserInfo;
import com.ck.vo.UserInfoVo;

public interface UserInfoDao {
	/**
	 * 插入。
	 * 
	 * @param userInfo
	 * @return
	 */
	public Integer insert(UserInfoVo userInfo);

	/**
	 * 查询通过id。
	 * 
	 * @param id
	 * @return
	 */
	public UserInfo findById(Integer id);

	/**
	 * 更新。
	 * 
	 * @param userInfo
	 * @return
	 */
	public Integer update(UserInfoVo userInfo);

	/**
	 * 查询。文件名、描述全文索引查询，时间范围查询，其他精确查询。
	 * 
	 * @param userInfo
	 * @return
	 */
	public List<UserInfo> find(UserInfoVo userInfo);

	/**
	 * 精确查询。
	 * 
	 * @param userInfo
	 * @return
	 */
	public List<UserInfo> findExact(UserInfoVo userInfo);
}
