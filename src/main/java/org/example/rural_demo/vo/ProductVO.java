package org.example.rural_demo.vo;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProductVO {
    private Long id;
    // 农产品名称
    private String productName;
    // 描述
    private String description;
    // 价格
    private BigDecimal price;
    // 图片地址
    private String imageUrl;
    // 发布人id
    private Long userId;
    // 库存
    private Integer stock;
    private LocalDateTime createTime;
}
