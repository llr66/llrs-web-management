package com.llrjava.mapper;

import com.llrjava.pojo.DeptLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeptLogMapper {
    //往记录日志数据库表添加数据
    void log(DeptLog deptLog);
}
