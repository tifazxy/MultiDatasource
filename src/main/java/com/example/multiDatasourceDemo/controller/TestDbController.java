package com.example.multiDatasourceDemo.controller;

import com.example.multiDatasourceDemo.anothermapper.AnotherMapper;
import com.example.multiDatasourceDemo.domain.User;
import com.example.multiDatasourceDemo.mapper.PrimaryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TestDbController {

    @Autowired
    private PrimaryMapper primaryMapper;

    @Autowired
    private AnotherMapper anotherMapper;


    @RequestMapping("mapping")
    public Map<String, String> testMapper(){
        Map<String,String> result = new HashMap<String,String>();
        User primary = primaryMapper.getUser("id1");
        User another = anotherMapper.getUser("id2");
        if (primary==null){
            primaryMapper.insertUser("id1","username1");
            primary = primaryMapper.getUser("id1");
        }
        if (another==null){
            anotherMapper.insertUser("id2","username2");
            another = anotherMapper.getUser("id2");
        }
        result.put("user1",primary.getId()+primary.getUsername());
        result.put("user2",another.getId()+another.getUsername());
        return result;
    }

}
