package me.power.speed.db.orm.ibatis.spring;

import java.util.List;

import me.power.speed.db.orm.UserDao;
import me.power.speed.entity.User;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapExecutor;

@Repository
public class IbatisSpringUserDaoImpl extends BaseIbatisSpringDao implements UserDao {

	public void insertUser(User user) {
		this.getSqlMapClientTemplate().insert("insertUser", user);
	}

	@SuppressWarnings("unchecked")
	public void batchInsertUser(final List<User> userList) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws java.sql.SQLException {
				executor.startBatch();
				
				for(User user : userList) {
					executor.insert("insertUser", user);
				}
				
				executor.executeBatch();
				return null;
			};
		});
	}
	
	public void batchInsertUserByIbatisSql(List<User> userList) {
		this.getSqlMapClientTemplate().insert("batchInsertUser", userList);
	}
	
	public User getUserByEmail(User user) {
		return (User)this.getSqlMapClientTemplate().queryForObject("selectUserByEmail", user);
	}

}
