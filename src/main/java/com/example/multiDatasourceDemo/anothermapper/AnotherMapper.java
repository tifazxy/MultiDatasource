package com.example.multiDatasourceDemo.anothermapper;

import com.example.multiDatasourceDemo.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AnotherMapper {

    public void insertUser(@Param("id")String id, @Param("username")String username);

    public User getUser(@Param("id")String id);
}
