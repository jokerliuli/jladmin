package com.jokerliu.leavemes.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.base.CaseFormat;

import com.jokerliu.entity.Result;
import com.jokerliu.enums.ResultStatusCode;
import com.jokerliu.leavemes.entity.Leavemes;
import com.jokerliu.leavemes.service.ILeavemesService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 留言表 前端控制器
 * </p>
 *
 * @author alex
 * @since 2018-12-11
 */
@Slf4j
@RestController
@RequestMapping("/admin/leavemes")
public class LeavemesController {

    @Value("${spring.mail.username}")
    private String sender;

    @Resource
    private JavaMailSender mailSender;

    @Resource
    private ILeavemesService iLeavemesService;

//    @RequiresPermissions("leavemes:add")
    @PostMapping("save")
    public Result save(@RequestBody Leavemes leavemes) {
        leavemes.setUpdateDate(null);
        //建立邮件消息
        SimpleMailMessage mainMessage = new SimpleMailMessage();

        log.info("emailsender:"+sender);
        //发送者
        mainMessage.setFrom(sender);
        //接收者
        mainMessage.setTo(sender);
        //发送的标题
        mainMessage.setSubject(leavemes.getTitle());
        //发送的内容
        mainMessage.setText(leavemes.getContent()+"\n"+"留言来自"+"\n"+
                "phone:"+leavemes.getMobile()+"\n"+"email:"+leavemes.getEmail());
        mailSender.send(mainMessage);
        log.info("sendsuccess");
        return new Result(ResultStatusCode.OK,iLeavemesService.save(leavemes));
    }
    @RequiresPermissions("leavemes:del")
    @PostMapping("remove")
    public Result remove(@RequestBody Leavemes leavemes) {
        leavemes.setUpdateDate(null);
        leavemes.setStatus(0);
        return new Result(ResultStatusCode.OK,iLeavemesService.updateById(leavemes));
    }
    @RequiresPermissions("leavemes:edit")
    @PostMapping("update")
    public Result update(@RequestBody Leavemes leavemes) {
        leavemes.setUpdateDate(null);
        return new Result(ResultStatusCode.OK,iLeavemesService.updateById(leavemes));
    }
    @RequiresPermissions("leavemes:search")
    @PostMapping("page")
    public Result page(@RequestParam(name = "page") int page,
                                @RequestParam(name = "limit") int limit,
                                @RequestParam(name = "content", required = false) String content,
                                @RequestParam(name = "startTime", required = false) String startTime,
                                @RequestParam(name = "endTime", required = false) String endTime,
                                @RequestParam(name = "prop",required = false,defaultValue = "update_date") String prop,
                                @RequestParam(name = "order",required = false,defaultValue = "descending") String order) {
        IPage<Leavemes> leavemesIPage = new Page<>();
        leavemesIPage.setCurrent(page);
        leavemesIPage.setSize(limit);

        QueryWrapper<Leavemes> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", 1);
        prop = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, prop);
        log.info(prop);
        if ("descending".equals(order)){
            queryWrapper.orderByDesc(prop);
        }else{
            queryWrapper.orderByAsc(prop);
        }
        if (StrUtil.isNotBlank(startTime)&&StrUtil.isNotBlank(endTime)){
            log.info(startTime);
            log.info(endTime);
            queryWrapper.gt("create_date",startTime);
            queryWrapper.lt("create_date",endTime);
        }
        if (StrUtil.isNotBlank(content)) {
            queryWrapper.like("content", content);
        }
        return new Result(ResultStatusCode.OK,iLeavemesService.page(leavemesIPage, queryWrapper));
    }
}
