package org.example.rural_demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;
/**
 *通知公告类
 *对应notice表
 */
@TableName("notice")
public class Notice {

    //主键，自增
    @TableId(type = IdType.AUTO)
    private Long id;
    //通知标题
    private String title;
    //通知内容
    private String content;
    //创建时间
    private LocalDateTime createTime;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public String getContent() {
        return content;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
