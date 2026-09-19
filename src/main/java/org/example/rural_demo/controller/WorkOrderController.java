package org.example.rural_demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.rural_demo.common.Result;
import org.example.rural_demo.dto.WorkOrderHandleDTO;
import org.example.rural_demo.dto.WorkOrderSubmitDTO;
import org.example.rural_demo.entity.WorkOrder;
import org.example.rural_demo.service.WorkOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 工单Controller
 * 处理工单提交、处理、查询等请求
 */
@RestController
@RequestMapping("/workorder")
public class WorkOrderController {

    //注入WorkOrderService
    @Autowired
    private WorkOrderService workOrderService;

    //村民提交工单
    @PostMapping("/submit")
    public Result<Void> submitWorkOrder(@RequestBody WorkOrderSubmitDTO dto) {
        // 现在还没做登录拦截，先写死用户ID为1（村民张三）
        Long userId = 1L;
        workOrderService.submitWorkOrder(userId, dto);
        return Result.success();
    }

    //村干部处理工单
    @PutMapping("/handle")
    public Result<Void> handleWorkOrder(@RequestBody WorkOrderHandleDTO dto) {
        // 现在还没做登录拦截，先写死处理人ID为2（村干部李四）
        Long handleUserId = 2L;
        workOrderService.handleWorkOrder(handleUserId, dto);
        return Result.success();
    }

    //分页查询工单列表
    @GetMapping("/page")
    public Result<Page<WorkOrder>> getWorkOrderPage(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        // 现在还没做登录拦截，先写死：村民角色(role=0)，用户ID为1
        Long userId = 1L;
        Integer role = 0;
        Page<WorkOrder> page = workOrderService.getWorkOrderPage(userId, role, pageNum, pageSize);
        return Result.success(page);
    }
}
