package org.example.rural_demo.vo;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class WorkOrderVO {
    private Long id;
    // 工单标题
    private String title;
    // 问题描述
    private String description;
    // 工单状态
    private Integer status;
    // 提交用户id
    private Long submitUserId;
    // 处理人id
    private Long handleUserId;
    // 处理结果
    private String handleResult;
    // 提交时间
    private LocalDateTime submitTime;
    // 办结时间
    private LocalDateTime finishTime;
}
