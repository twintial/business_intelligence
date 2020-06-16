package com.bi.dbpedia.dao;

import com.bi.dbpedia.model.MyUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    @Select("select username, password, authorities from user where username=#{username}")
    List<MyUser> selectUserByUserName(String username);
}
