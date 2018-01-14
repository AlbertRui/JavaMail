package me.javamail.dao;

import me.javamail.domain.User;

/**
 * 处理与数据库之间的业务交互的接口
 * @author Administrator
 *
 */
public interface UserDao {

	/**
	 * 用户注册接口方法
	 * @param user
	 * @throws Exception
	 */
	public void regist(User user) throws Exception;

	/**
	 * 通过验证码查找用户的接口方法
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public User findUserByCode(String code) throws Exception;

	/**
	 * 更新用户信息的接口方法
	 * @param user
	 * @throws Exception
	 */
	public void update(User user) throws Exception;
}
