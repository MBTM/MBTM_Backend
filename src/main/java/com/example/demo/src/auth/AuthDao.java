package com.example.demo.src.auth;

import com.example.demo.src.auth.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class AuthDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public User getPwd(String Id){
        System.out.println(Id);
        String getPwdQuery = "select userIdx, id ,nickName, email, password from User Where id = ?";
        String getPwdParams = Id;
        //System.out.println(Id);

        return this.jdbcTemplate.queryForObject(getPwdQuery,
                (rs,rowNum)-> new User(
                        rs.getInt("userIdx"),
                        rs.getString("id"),
                        rs.getString("nickName"),
                        rs.getString("email"),
                        rs.getString("password")
                ),
                getPwdParams
        );
    }

}
