package com.llrjava.Service;

import com.llrjava.pojo.Dept;

import java.util.List;

public interface DeptService {
    //查询所有部门信息
    List<Dept> list();

    //删除指定id的部门信息
    void delete(Integer id) throws Exception;

    //根据名字添加部门
    void add(Dept dept);

    //根据ID查询部门
    Dept select(Integer id);

    //修改部门

    void update(Dept dept);
}
