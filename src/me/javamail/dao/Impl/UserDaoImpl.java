package me.javamail.dao.Impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import me.javamail.dao.UserDao;
import me.javamail.domain.User;
import me.javamail.utils.JDBCUtils;

/**
 * UserDao的具体实现类
 * @author Administrator
 *
 */
public class UserDaoImpl implements UserDao {

	/**
	 * Dao中保存用户的方法
	 * @throws Exception 
	 */
	@Override
	public void regist(User user) throws Exception {

		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "insert into user values (?,?,?,?,?,?,?)";
		Object[] params = {user.getUid(), user.getUsername(), user.getPassword(), user.getNickname(), user.getEmail(), user.getState(), user.getCode()};
		queryRunner.update(sql, params);
	}

	/**
	 * 根据激活码查询用户
	 */
	@Override
	public User findUserByCode(String code) throws Exception {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from user where code = ?";
		User user = queryRunner.query(sql, new BeanHandler<User>(User.class), code);
		return user;
	}

	/**
	 * 修改用户
	 */
	@Override
	public void update(User user) throws Exception {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "update user set username = ?, password = ?, nickname = ?, email = ?, state =?, code = ? where uid = ?";
		Object[] params = {user.getUsername(), user.getPassword(), user.getNickname(), user.getEmail(), user.getState(), user.getCode(), user.getUid()};
		queryRunner.update(sql, params);
	}

}
