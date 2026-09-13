package org.example.rural_demo.dto;

/**
 * 提交工单参数DTO
 * 接收村民提交的工单信息
 */
public class WorkOrderSubmitDTO {
    //工单标题
    private String title;
    //工单内容
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
