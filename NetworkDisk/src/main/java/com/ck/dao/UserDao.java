package com.ck.dao;

import java.util.List;

import com.ck.po.User;
import com.ck.vo.UserVo;

public interface UserDao {

	/**
	 * 插入。
	 * 
	 * @param user
	 * @return
	 */
	public Integer insert(UserVo user);

	/**
	 * 查询通过id。
	 * 
	 * @param id
	 * @return
	 */
	public User findById(Integer id);

	/**
	 * 更新。
	 * 
	 * @param user
	 * @return
	 */
	public Integer update(UserVo user);

	/**
	 * 查询。用户名、昵称模糊查询，时间范围查询，其他精确查询。
	 * 
	 * @param user
	 * @return
	 */
	public List<User> find(UserVo user);

	/**
	 * 精确查询。
	 * 
	 * @param user
	 * @return
	 */
	public List<User> findExact(UserVo user);
}
