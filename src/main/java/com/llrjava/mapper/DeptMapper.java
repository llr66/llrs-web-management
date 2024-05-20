package com.llrjava.mapper;

import com.llrjava.pojo.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DeptMapper {
   //查询全部部门

   public List<Dept> list();

   //删除指定id的部门信息
   public void delete(Integer id);

   //根据名字添加部门
   public void add(Dept dept);

   //根据ID查询部门
   public Dept selet(Integer id);

   //修改部门
   void update(Dept dept);
}
