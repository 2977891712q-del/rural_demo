package org.example.rural_demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.rural_demo.entity.Notice;

/**
 * 通知公告Service接口
 * 继承IService，自带基础的增删改查
 */
public interface NoticeService extends IService<Notice> {

    //分页查询通知列表
    Page<Notice> getNoticePage(Integer pageNum, Integer pageSize);
}
