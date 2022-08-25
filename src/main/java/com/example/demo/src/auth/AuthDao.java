package com.example.demo.src.auth;

import com.example.demo.utils.FormatUtil;
import com.example.demo.src.auth.model.User;
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

    public Integer checkInfo(String id, String phone){

        // Static은 ValidationRegex참고하고 여긴 Public으로 끌고옴
        // 01000000000 -> 010-0000-0000으로 형변환
        FormatUtil formatUtils = new FormatUtil();
        String phoneForDBRegex = formatUtils.phoneFormat(phone);

        String checkIdQuery = "select userIdx from User where id = ? and phone = ?";
        Integer result = this.jdbcTemplate.queryForObject(checkIdQuery, int.class, id, phoneForDBRegex);

        return(result);
    }



}
