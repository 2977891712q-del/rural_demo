package org.example.rural_demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.rural_demo.common.Result;
import org.example.rural_demo.entity.Notice;
import org.example.rural_demo.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 通知公告Controller
 * 处理通知公告的增删改查请求
 */
@RestController
@RequestMapping("/notice")
public class NoticeController {

    //注入NoticeService
    @Autowired
    private NoticeService noticeService;

    //分页查询通知列表
    @GetMapping("/page")
    public Result<Page<Notice>> getNoticePage(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        Page<Notice> page = noticeService.getNoticePage(pageNum, pageSize);
        return Result.success(page);
    }

    //根据ID查询通知详情
    @GetMapping("/{id}")
    public Result<Notice> getNoticeById(@PathVariable Long id) {
        Notice notice = noticeService.getById(id);
        return Result.success(notice);
    }

    //新增通知
    @PostMapping
    public Result<Void> addNotice(@RequestBody Notice notice) {
        noticeService.save(notice);
        return Result.success();
    }

    //修改通知
    @PutMapping
    public Result<Void> updateNotice(@RequestBody Notice notice) {
        noticeService.updateById(notice);
        return Result.success();
    }

    //删除通知
    @DeleteMapping("/{id}")
    public Result<Void> deleteNotice(@PathVariable Long id) {
        noticeService.removeById(id);
        return Result.success();
    }
}
