package org.example.rural_demo.vo;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserVO {
    private Long id;
    private String username;
    private String realName;
    private String phone;
    // 0村民 1村干部
    private Integer role;
    private LocalDateTime createTime;
    // 没有password字段！安全
}