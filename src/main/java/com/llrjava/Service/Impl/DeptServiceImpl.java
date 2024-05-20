package com.llrjava.Service.Impl;

import com.llrjava.Service.DeptLogService;
import com.llrjava.Service.DeptService;
import com.llrjava.mapper.DeptLogMapper;
import com.llrjava.mapper.DeptMapper;
import com.llrjava.mapper.EmpMapper;
import com.llrjava.pojo.Dept;
import com.llrjava.pojo.DeptLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private DeptLogService deptLogService;

    //查询所有部门信息
    @Override
    public List<Dept> list() {

        return deptMapper.list();
    }
    //删除指定id的部门信息
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Integer id) throws Exception {

        try {
            //根据部门id删除部门
            deptMapper.delete(id);
            //模拟异常发生
            if (true)
            {
                throw new Exception("出错了");
            }
            //根据部门id删除员工
            empMapper.deleteByDeptId(id);
        }//添加try finally保证即使前面报错后面的代码也可以执行到
        finally {
            //创建日志记录的实体类
            DeptLog deptLog=new DeptLog();
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("进行了删除操作");
            //通过注入的
            deptLogService.insert(deptLog);
        }
    }

    //根据名字添加部门
    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.add(dept);
    }

    //根据ID查询部门

    @Override
    public Dept select(Integer id) {
        Dept dept=deptMapper.selet(id);
        return dept;
    }

    //修改部门
    @Override
    public void update(Dept dept) {
        deptMapper.update(dept);
    }
}
