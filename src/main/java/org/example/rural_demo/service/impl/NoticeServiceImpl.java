package org.example.rural_demo.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.rural_demo.entity.Notice;
import org.example.rural_demo.mapper.NoticeMapper;
import org.example.rural_demo.service.NoticeService;
import org.springframework.stereotype.Service;

/**
 * 通知公告Service实现类
 * 继承ServiceImpl，自动实现基础的增删改查
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

    //分页查询通知列表
    @Override
    public Page<Notice> getNoticePage(Integer pageNum, Integer pageSize) {
        //创建分页对象，第pageNum页，每页pageSize条
        Page<Notice> page = new Page<>(pageNum, pageSize);
        //按创建时间倒序查询（最新的在前面）
        return lambdaQuery()
                .orderByDesc(Notice::getCreateTime)
                .page(page);
    }
}
