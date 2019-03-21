package com.jokerliu.manage.controller;

import com.jokerliu.enums.Result;
import com.jokerliu.enums.ResultStatusCode;
import com.jokerliu.manage.service.IInformationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;

@Slf4j
@RestController
@RequestMapping("test")
public class TestController {

    @Resource
    private IInformationService iInformationService;

    @GetMapping("test1")
    public Result test(){
        ArrayList<Long>  ids = new ArrayList<>();
        ids.add((long) 6);
        ids.add((long) 1);
        ids.add((long) 2);
        ids.add((long) 3);
        ids.add((long) 4);
        ids.add((long) 5);
        return new Result(ResultStatusCode.OK,iInformationService.listByIds(ids));
    }
}
