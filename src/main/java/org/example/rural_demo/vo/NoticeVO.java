package org.example.rural_demo.vo;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class NoticeVO {
    private Long id;
    // 公告标题
    private String title;
    // 公告内容
    private String content;
    // 是否置顶 0否 1是
    private Integer isTop;
    // 发布人名称
    private String publishName;
    private LocalDateTime createTime;
}
