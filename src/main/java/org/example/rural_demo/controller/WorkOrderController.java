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

    //村民提交工单，userId从Token里取
    @PostMapping("/submit")
    public Result<Void> submitWorkOrder(@RequestBody WorkOrderSubmitDTO dto,
                                        @RequestAttribute Long userId) {
        workOrderService.submitWorkOrder(userId, dto);
        return Result.success();
    }

    //村干部处理工单，处理人就是当前登录的村干部
    @PutMapping("/handle")
    public Result<Void> handleWorkOrder(@RequestBody WorkOrderHandleDTO dto,
                                        @RequestAttribute Long userId) {
        workOrderService.handleWorkOrder(userId, dto);
        return Result.success();
    }

    //分页查询工单列表，userId和role从Token里取
    @GetMapping("/page")
    public Result<Page<WorkOrder>> getWorkOrderPage(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestAttribute Long userId,
            @RequestAttribute Integer role) {
        Page<WorkOrder> page = workOrderService.getWorkOrderPage(userId, role, pageNum, pageSize);
        return Result.success(page);
    }
}
