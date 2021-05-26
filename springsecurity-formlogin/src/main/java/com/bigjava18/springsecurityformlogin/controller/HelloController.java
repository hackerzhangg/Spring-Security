package com.bigjava18.springsecurityformlogin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zgp
 * @Since 2021 -05 -26 10 :17
 * @Description
 */
@RestController
public class HelloController {

    @RequestMapping(value = "/hello")
    public List<String> Hello(){
        List<String> stringList=new ArrayList<>();
        stringList.add("张三");
        stringList.add("李四");
        stringList.add("王五");
        stringList.add("老王");
        return stringList;
    }

    @RequestMapping(value = "/success")
    public String Success(){

        return "success";
    }

}
