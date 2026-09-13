package org.example.rural_demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.rural_demo.common.BusinessException;
import org.example.rural_demo.dto.WorkOrderHandleDTO;
import org.example.rural_demo.dto.WorkOrderSubmitDTO;
import org.example.rural_demo.entity.WorkOrder;
import org.example.rural_demo.mapper.WorkOrderMapper;
import org.example.rural_demo.service.WorkOrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 工单Service实现类
 * 实现提交工单、处理工单、查询工单等业务逻辑
 */
@Service
public class WorkOrderServiceImpl extends ServiceImpl<WorkOrderMapper, WorkOrder> implements WorkOrderService {

    //村民提交工单
    @Override
    public void submitWorkOrder(Long userId, WorkOrderSubmitDTO dto) {
        //1. 创建工单对象，设置字段
        WorkOrder workOrder = new WorkOrder();
        workOrder.setUserId(userId);
        workOrder.setTitle(dto.getTitle());
        workOrder.setContent(dto.getContent());
        //刚提交的工单，状态是"待处理"（0）
        workOrder.setStatus(0);
        workOrder.setCreateTime(LocalDateTime.now());

        //2. 保存到数据库
        this.save(workOrder);
    }

    //村干部处理工单
    @Override
    public void handleWorkOrder(Long handleUserId, WorkOrderHandleDTO dto) {
        //1. 根据ID查询工单
        WorkOrder workOrder = this.getById(dto.getId());
        if (workOrder == null) {
            throw new BusinessException("工单不存在");
        }

        //2. 判断工单状态，已完成的工单不能再处理
        if (workOrder.getStatus() == 2) {
            throw new BusinessException("工单已完成，不能重复处理");
        }

        //3. 更新工单状态和处理结果
        workOrder.setStatus(2); //状态改为"已完成"（2）
        workOrder.setHandleUserId(handleUserId); //设置处理人
        workOrder.setResult(dto.getResult()); //设置处理结果
        workOrder.setUpdateTime(LocalDateTime.now()); //更新时间

        //4. 更新到数据库
        this.updateById(workOrder);
    }

    //分页查询工单列表
    @Override
    public Page<WorkOrder> getWorkOrderPage(Long userId, Integer role, Integer pageNum, Integer pageSize) {
        //1. 创建分页对象
        Page<WorkOrder> page = new Page<>(pageNum, pageSize);

        //2. 构造查询条件
        LambdaQueryWrapper<WorkOrder> wrapper = new LambdaQueryWrapper<>();

        //3. 村民只能看自己的工单，村干部可以看所有
        if (role == 0) {
            //村民：只查自己提交的工单
            wrapper.eq(WorkOrder::getUserId, userId);
        }
        //村干部（role==1）：不加用户条件，查所有工单

        //4. 按创建时间倒序
        wrapper.orderByDesc(WorkOrder::getCreateTime);

        //5. 执行分页查询
        return this.page(page, wrapper);
    }
}
