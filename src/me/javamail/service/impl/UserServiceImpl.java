package me.javamail.service.impl;

import me.javamail.dao.UserDao;
import me.javamail.dao.Impl.UserDaoImpl;
import me.javamail.domain.User;
import me.javamail.service.UserService;
import me.javamail.utils.MailUtils;

/**
 * 用户注册业务层实现
 * @author Administrator
 *
 */
public class UserServiceImpl implements UserService {

	/**
	 * 业务层用户注册方法
	 */
	@Override
	public void regist(User user) throws Exception{

		//将数据保存到数据库
		UserDao userDao = new UserDaoImpl();
		userDao.regist(user);
		//发送一封激活邮件
		MailUtils.sendMail(user.getEmail(), user.getCode());
	}

	/**
	 * 根据激活码查询用户
	 */
	@Override
	public User findUserByCode(String code) throws Exception {
		UserDao userDao = new UserDaoImpl();
		return userDao.findUserByCode(code);
	}

	/**
	 * 业务层修改用户
	 */
	@Override
	public void update(User user) throws Exception {
		UserDao userDao = new UserDaoImpl();
		userDao.update(user);
	}

}
