package com.example.multiDatasourceDemo.mapper;

import com.example.multiDatasourceDemo.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface PrimaryMapper {

    public void insertUser(@Param("id")String id, @Param("username")String username);

    public User getUser(@Param("id")String id);
}
