package org.example.rural_demo.dto;

/**
 * 处理工单参数DTO
 * 接收村干部处理工单的结果
 */
public class WorkOrderHandleDTO {
    //工单ID
    private Long id;
    //处理结果
    private String result;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
