package ${package.Controller};

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.slf4j.Slf4j;
import com.jokerliu.enums.Result;
import com.jokerliu.enums.ResultStatusCode;
import javax.annotation.Resource;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${package.Service}.${table.serviceName};
import ${package.Entity}.${entity};
<#if restControllerStyle>
import org.springframework.web.bind.annotation.*;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Slf4j
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>

    @Resource
    private  ${table.serviceName} i${entity}Service;

    /**
    * 单个新增
    * @param ${table.entityPath}  传递的实体
    * @return Result
    */
    @PostMapping("save")
    public Result save(@RequestBody ${entity} ${table.entityPath}){
            //log.info(${table.entityPath}.toString());
        return new Result(ResultStatusCode.OK,i${entity}Service.save(${table.entityPath}));
    }

    /**
    * 单个删除
    * @param ${table.entityPath}  传递的实体
    * @return Result
    */
    @PostMapping("remove")
    public Result remove(@RequestBody ${entity} ${table.entityPath}) {
        return new Result(ResultStatusCode.OK,i${entity}Service.remove(new QueryWrapper(${table.entityPath})));
    }

    /**
    * 单个更新
    * @param ${table.entityPath}  传递的实体
    * @return Result
    */
    @PostMapping("update")
    public Result update(@RequestBody ${entity} ${table.entityPath}) {
        //${table.entityPath}.setUpdateDate(null);
        return new Result(ResultStatusCode.OK,i${entity}Service.updateById(${table.entityPath}));
    }

    /**
    * 根据id查询获取一个返回
    * @param  id  对象id
    * @return Result
    */
    @GetMapping("getOne")
    public Result getOne(@RequestParam(name = "id") Long id){
        return new Result(ResultStatusCode.OK,i${entity}Service.getById(id));
    }

    /**
    * 条件查询list
    * @param ${table.entityPath}  传递的实体
    * @return Result
    */
    @PostMapping("list")
    public Result list( @RequestBody ${entity} ${table.entityPath}) {
        return new Result(ResultStatusCode.OK,i${entity}Service.list(new QueryWrapper(${table.entityPath})));
    }

    /**
    * 条件分页查询
    * @param  page  第几页（1开始）
    * @param  limit  每页size
    * @return Result
    */
    @PostMapping("page")
    public Result page(@RequestParam(name = "page") int page,
                        @RequestParam(name = "limit") int limit,
                        @RequestBody ${entity} ${table.entityPath}) {
        return new Result(ResultStatusCode.OK,i${entity}Service.page(new Page<>(page,limit),new QueryWrapper(${table.entityPath})));
    }
}
</#if>
