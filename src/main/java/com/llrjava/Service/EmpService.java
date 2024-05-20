package com.llrjava.Service;

import com.llrjava.pojo.Emp;
import com.llrjava.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    //分页查询
    PageBean page(String name, Short gender, LocalDate begin, LocalDate end, Integer page, Integer pageSize);

     //批量删除
    void delete(List<Integer> ids);

    //新增员工
    void add(Emp emp);

    //根据ID查询
    Emp getById(Integer id);

    //修改员工
    void update(Emp emp);

    //登录校验
    Emp login(Emp emp);
}
