package com.llrjava.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 部门删除记录日志实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeptLog {
    private Integer id;//id
    private LocalDateTime createTime;//日志记录时间
    private String description;//日志记录内容
}
