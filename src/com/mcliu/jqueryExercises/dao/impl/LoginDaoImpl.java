package com.mcliu.jqueryExercises.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Repository;

import com.mcliu.jqueryExercises.dao.LoginDao;
import com.mcliu.jqueryExercises.entity.User;

@Repository("LoginDao")
public class LoginDaoImpl implements LoginDao {
    protected Logger logger = LoggerFactory.getLogger(LoginDaoImpl.class);

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    @Qualifier("dataSourceMcliu")
    public void setDataSource(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    /**
     * get User's Info by login id
     * @param loginId
     * @return
     * @throws Exception
     */
    @Override
    public User selectUserByLoginId(int loginId) throws Exception {
        String sql = "SELECT "
                + "t_id, "
                + "t_login_name, "
                + "t_password, "
                + "t_name "
                + "FROM t_user "
                + "WHERE t_id = :loginId ";

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("loginId", loginId);
        return namedParameterJdbcTemplate.query(sql, param, new UserResultSetExtractor());
    }

    /**
     * get User's Info by login name
     * @param loginName
     * @return
     * @throws Exception
     */
    @Override
    public User selectUserByLoginName(String loginName) throws Exception {
        String sql = "SELECT "
                + "t_id, "
                + "t_login_name, "
                + "t_password, "
                + "t_name "
                + "FROM t_user "
                + "WHERE t_login_name = :loginName ";

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("loginName", loginName);
        return namedParameterJdbcTemplate.query(sql, param, new UserResultSetExtractor());
    }

    /**
     * get User's Info list by name
     * @param name
     * @return
     * @throws Exception
     */
    @Override
    public List<User> selectUserListByName(String name) throws Exception {
        String sql = "SELECT "
                + "t_id, "
                + "t_login_name, "
                + "t_password, "
                + "t_name "
                + "FROM t_user "
                + "WHERE t_name = :name ";

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("name", name);
        return namedParameterJdbcTemplate.query(sql, param, new UserRowMapper());
    }

    protected class UserResultSetExtractor implements ResultSetExtractor<User> {

        @Override
        public User extractData(ResultSet rs) throws SQLException, DataAccessException {
            User user = new User();
            if (rs.next()) {
                user.setId(rs.getInt("t_id"));
                user.setLoginName(rs.getString("t_login_name"));
                user.setPassword(rs.getString("t_password"));
                user.setName(rs.getString("t_name"));
            }
            return user;
        }

    }

    protected class UserRowMapper implements ParameterizedRowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int index) throws SQLException {
            User user = new User();

            user.setId(rs.getInt("t_id"));
            user.setLoginName(rs.getString("t_login_name"));
            user.setPassword(rs.getString("t_password"));
            user.setName(rs.getString("t_name"));
            return user;
        }

    }

}
