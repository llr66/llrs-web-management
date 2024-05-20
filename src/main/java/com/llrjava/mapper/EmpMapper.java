package com.llrjava.mapper;

import com.llrjava.pojo.Dept;
import com.llrjava.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {



    //获取数据库数据数量
    public Long total();

    //获取条件筛选数据
    public List<Emp> list(String name, Short gender, LocalDate begin, LocalDate end, Integer start, Integer pageSize);

    //批量删除
    public void delete(List<Integer> ids);

    //新增员工共
    void add(Emp emp);

    //根据ID查询
    Emp getById(Integer id);

    //修改员工
    void update(Emp emp);

    //根据用户名和密码来查询员工
    Emp getByUsernameAndPassword(Emp emp);

    //根据部门id来删除员工
    void deleteByDeptId(Integer deptId);
}
