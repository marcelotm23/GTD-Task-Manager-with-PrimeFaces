package com.sdi.persistence.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.sdi.model.User;
import com.sdi.model.types.UserStatus;
import com.sdi.persistence.UserDao;
import com.sdi.persistence.util.JdbcTemplate;
import com.sdi.persistence.util.RowMapper;

public class UserDaoJdbcImpl implements UserDao {

	public class UserMapper implements RowMapper<User> {
		@Override
		public User toObject(ResultSet rs) throws SQLException {
			User user= new User();
			user.setId(rs.getLong("id"));
			user.setLogin(rs.getString("login"));
			user.setPassword(rs.getString("password"));
			user.setEmail(rs.getString("email"));
			user.setIsAdmin(rs.getBoolean("isAdmin"));
			user.setStatus(UserStatus.valueOf(rs.getString("status")));
			return user;
		}
	}

	private JdbcTemplate jdbcTemplate = new JdbcTemplate();

	@Override
	public Long save(User dto) {
		jdbcTemplate.execute("USER_INSERT", dto.getLogin(), dto.getPassword(),
				dto.getEmail(), dto.getIsAdmin(),
				toStringOrNull(dto.getStatus()));
		return jdbcTemplate.getGeneratedKey();
	}

	private String toStringOrNull(Object obj) {
		return obj == null ? null : obj.toString();
	}

	@Override
	public int update(User dto) {
		return jdbcTemplate.execute("USER_UPDATE", dto.getLogin(),
				dto.getPassword(), dto.getEmail(), dto.getIsAdmin(),
				toStringOrNull(dto.getStatus()), dto.getId());
	}

	@Override
	public int delete(Long id) {
		return jdbcTemplate.execute("USER_DELETE", id);
	}

	@Override
	public User findById(Long id) {
		return jdbcTemplate.queryForObject("USER_FIND_BY_ID", new UserMapper(),
				id);
	}

	@Override
	public List<User> findAll() {
		return jdbcTemplate.queryForList("USER_FIND_ALL", new UserMapper());
	}

	@Override
	public User findByLogin(String login) {
		return jdbcTemplate.queryForObject("USER_FIND_BY_LOGIN",
				new UserMapper(), login);
	}

	@Override
	public User findByLoginAndPassword(String login, String password) {
		return jdbcTemplate.queryForObject("USER_FIND_BY_LOGIN_AND_PASSWORD",
				new UserMapper(), login, password);
	}

}
