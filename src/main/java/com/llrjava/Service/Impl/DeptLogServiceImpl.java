package com.llrjava.Service.Impl;

import com.llrjava.Service.DeptLogService;
import com.llrjava.mapper.DeptLogMapper;
import com.llrjava.pojo.DeptLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeptLogServiceImpl implements DeptLogService {

    @Autowired
    private DeptLogMapper deptLogMapper;

    //往记录日志数据库表添加数据
    //并且加上事务管理并且指定这是一个独立的事件,不会被调用方法的事务合并
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void insert(DeptLog deptLog) {
        deptLogMapper.log(deptLog);
    }
}
