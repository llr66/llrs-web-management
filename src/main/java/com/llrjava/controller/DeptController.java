package com.llrjava.controller;

import com.llrjava.Service.DeptService;
import com.llrjava.anno.OperateLog;
import com.llrjava.pojo.Dept;
import com.llrjava.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    //private static Logger log = LoggerFactory.getLogger(DeptController.class);

    //查询全部的部门数据
    //@RequestMapping(value = "/depts",method = RequestMethod.GET)//指定请求方式为GEt
    @OperateLog
    @GetMapping("/depts")
    public Result list(){
        log.info("查询全部的部门数据");
        //调用service层方法查询部门数据
        List<Dept> deptList=deptService.list();
        return Result.success(deptList);
    }

    //删除指定id的部门数据
    @OperateLog
    @DeleteMapping("/depts/{id}")
    public Result Delete(@PathVariable Integer id) throws Exception {
        log.info("删除部门数据的id为"+id);
        deptService.delete(id);
        //调用Service层方法向下一步一步去传递删除信息
        return Result.success();
    }

    //根据名字添加部门
    @OperateLog
    @PostMapping("/depts")
    public Result add(@RequestBody  Dept dept){
        log.info("要添加的部门名称是"+dept);
        deptService.add(dept);
        return Result.success();
    }

    //根据ID查询部门
    @OperateLog
    @GetMapping("/depts/{id}")
    public Result select(@PathVariable Integer id){
        log.info("查询的部门的id为"+id);
        Dept dept=deptService.select(id);
        return Result.success(dept);
    }

    //修改部门
    @OperateLog
    @PutMapping("/depts")
    public Result update(@RequestBody Dept dept){
        log.info("跟新部门数据"+dept);
        deptService.update(dept);
        return Result.success();
    }
}
