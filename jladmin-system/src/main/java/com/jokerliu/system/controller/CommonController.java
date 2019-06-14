package com.jokerliu.system.controller;

import com.jokerliu.entity.Result;
import com.jokerliu.enums.ResultStatusCode;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * common 模块
 * </p>
 *
 * @author alex
 * @since 2018-12-12
 */

@RequestMapping("/common")
@RestController
public class CommonController {

    /**
     * 未授权跳转方法
     * @return
     */
    @GetMapping("/unauth")
    public Result unauth(){
        SecurityUtils.getSubject().logout();
        return new Result(ResultStatusCode.UNAUTHO_ERROR);
    }

    @GetMapping("/ok")
    public Result ok(){
        return new Result(ResultStatusCode.OK);
    }
    /**
     * 被踢出后跳转方法
     * @return
     */
    @GetMapping("/kickout")
    public Result kickout(){
        return new Result(ResultStatusCode.TOKEN_TIME_OUT);
    }

}
