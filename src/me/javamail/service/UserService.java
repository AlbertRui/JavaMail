package me.javamail.service;

import me.javamail.domain.User;

/**
 * 用户注册业务层接口
 * @author Administrator
 *
 */
public interface UserService {

	public void regist(User user) throws Exception;

	public User findUserByCode(String code) throws Exception;

	public void update(User user) throws Exception;
}
