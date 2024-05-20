package com.llrjava.Service;

import com.llrjava.pojo.DeptLog;

public interface DeptLogService {
    ////往记录日志数据库表添加数据
    void insert(DeptLog deptLog);
}
