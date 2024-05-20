package com.llrjava.Service.Impl;

import com.llrjava.Service.EmpService;
import com.llrjava.mapper.EmpMapper;
import com.llrjava.pojo.Emp;
import com.llrjava.pojo.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    //条件查询
    @Override
    public PageBean page(String name, Short gender, LocalDate begin, LocalDate end, Integer page, Integer pageSize) {
        Integer start=(page-1)*pageSize;
        System.out.println(start);
        List<Emp> rows=empMapper.list(name,gender,begin,end,start,pageSize);

        Long total= empMapper.total();


        return new PageBean(total,rows);
    }

    //批量删除

    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }

    //新增员工
    @Override
    public void add(Emp emp) {
        empMapper.add(emp);
    }

    //根据ID查询

    @Override
    public Emp getById(Integer id) {
        Emp emp=empMapper.getById(id);
        return emp;
    }

    //修改员工

    @Override
    public void update(Emp emp) {
        empMapper.update(emp);
    }

    //登录校验
    @Override
    public Emp login(Emp emp) {
        return empMapper.getByUsernameAndPassword(emp);
    }
}
