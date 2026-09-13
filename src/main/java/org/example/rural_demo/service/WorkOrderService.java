package org.example.rural_demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.rural_demo.dto.WorkOrderHandleDTO;
import org.example.rural_demo.dto.WorkOrderSubmitDTO;
import org.example.rural_demo.entity.WorkOrder;

/**
 * 工单Service接口
 * 继承IService，自带基础的增删改查
 */
public interface WorkOrderService extends IService<WorkOrder> {

    //村民提交工单
    void submitWorkOrder(Long userId, WorkOrderSubmitDTO dto);

    //村干部处理工单
    void handleWorkOrder(Long handleUserId, WorkOrderHandleDTO dto);

    //分页查询工单列表（村民只能看自己的，村干部看所有）
    Page<WorkOrder> getWorkOrderPage(Long userId, Integer role, Integer pageNum, Integer pageSize);
}
