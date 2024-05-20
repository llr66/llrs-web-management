package com.llrjava.controller;

import com.llrjava.Service.EmpService;
import com.llrjava.anno.OperateLog;
import com.llrjava.pojo.Emp;
import com.llrjava.pojo.PageBean;
import com.llrjava.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

    //分页查询
    @OperateLog
    @GetMapping("/emps")
    public Result page(
            String name, Short gender,
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        log.info("进行分页查询数据"+name+gender+begin+end + page + pageSize);
        //将封装好的分页结果返回,并返还给客户端
        PageBean pageBean = empService.page(name,gender,begin,end,page, pageSize);
        return Result.success(pageBean);
    }

    //批量删除
    @OperateLog
    @DeleteMapping("/emps/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("批量删除操作,ids:",ids);
        System.out.println(ids);
        empService.delete(ids);
        return Result.success();
    }
    //增加员工
    @OperateLog
    @PostMapping("/emps")
    public Result add(@RequestBody Emp emp){
        System.out.println("需要添加的员工对象为"+emp);
        empService.add(emp);
        return Result.success();
    }

    //根据ID查询
    @OperateLog
    @GetMapping("/emps/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("要查询的员工ID为 {}",id);
        Emp emp=empService.getById(id);

        return Result.success(emp);
    }

    //修改员工
    @OperateLog
    @PutMapping("/emps")
    public Result update(@RequestBody Emp emp){
        log.info("修改的员工是{}",emp);
        empService.update(emp);
        return Result.success();
    }
}
