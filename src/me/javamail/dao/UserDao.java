package me.javamail.dao;

import me.javamail.domain.User;

public interface UserDao {

	public void regist(User user) throws Exception;

	public User findUserByCode(String code) throws Exception;

	public void update(User user) throws Exception;
}
