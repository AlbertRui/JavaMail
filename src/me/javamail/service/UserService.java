package me.javamail.service;

import me.javamail.domain.User;

/**
 * 用户注册业务层接口
 * @author Administrator
 *
 */
public interface UserService {

	/**
	 * 用户注册接口方法
	 * @param user
	 * @throws Exception
	 */
	public void regist(User user) throws Exception;

	/**
	 * 业务层通过验证码查找用户的接口方法
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public User findUserByCode(String code) throws Exception;

	/**
	 * 业务层修改用户信息的接口方法
	 * @param user
	 * @throws Exception
	 */
	public void update(User user) throws Exception;
}
