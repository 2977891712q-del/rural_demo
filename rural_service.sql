-- 乡村便民小程序 数据库初始化脚本
-- 数据库：rural_service
-- 包含表：user、notice、work_order、product

-- 创建数据库
CREATE DATABASE IF NOT EXISTS rural_service DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE rural_service;

-- ============================================
-- 用户表
-- ============================================
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` VARCHAR(50) NOT NULL COMMENT '用户名',
  `password` VARCHAR(100) NOT NULL COMMENT '密码（MD5加密）',
  `real_name` VARCHAR(50) DEFAULT NULL COMMENT '真实姓名',
  `phone` VARCHAR(20) DEFAULT NULL COMMENT '手机号',
  `role` INT DEFAULT 0 COMMENT '角色：0=村民，1=村干部/网格员',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 插入测试用户（密码都是123456，MD5加密后是e10adc3949ba59abbe56e057f20f883e）
INSERT INTO `user` (`username`, `password`, `real_name`, `phone`, `role`) VALUES
('zhangsan', 'e10adc3949ba59abbe56e057f20f883e', '张三', '13800138000', 0),
('lisi', 'e10adc3949ba59abbe56e057f20f883e', '李四', '13800138001', 1),
('wangwu', 'e10adc3949ba59abbe56e057f20f883e', '王五', '13800138002', 0);

-- ============================================
-- 通知公告表
-- ============================================
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '通知ID',
  `title` VARCHAR(200) NOT NULL COMMENT '标题',
  `content` TEXT COMMENT '内容',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='通知公告表';

-- 插入测试通知
INSERT INTO `notice` (`title`, `content`, `create_time`) VALUES
('关于秋收工作的通知', '各位村民，本周六开始秋收，请大家做好准备。', '2026-09-15 09:00:00'),
('关于医保缴费的通知', '2026年医保缴费开始了，请大家在10月底前完成缴费。', '2026-09-16 10:30:00'),
('关于村道维修的通知', '村东头道路维修，本周四全天封闭，请绕行。', '2026-09-17 14:00:00');

-- ============================================
-- 工单表
-- ============================================
DROP TABLE IF EXISTS `work_order`;
CREATE TABLE `work_order` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '工单ID',
  `user_id` BIGINT NOT NULL COMMENT '提交人ID',
  `title` VARCHAR(200) NOT NULL COMMENT '工单标题',
  `content` TEXT COMMENT '工单内容',
  `status` INT DEFAULT 0 COMMENT '状态：0=待处理，1=处理中，2=已完成',
  `handle_user_id` BIGINT DEFAULT NULL COMMENT '处理人ID',
  `result` TEXT COMMENT '处理结果',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '提交时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='工单表';

-- 插入测试工单
INSERT INTO `work_order` (`user_id`, `title`, `content`, `status`, `handle_user_id`, `result`, `create_time`, `update_time`) VALUES
(1, '村东头水管坏了', '3组村东头水管破裂，水流一地，影响村民用水', 2, 2, '已安排维修人员前往维修，预计下午修好', '2026-09-18 09:00:00', '2026-09-18 15:00:00'),
(3, '村口路灯不亮', '5组村口路灯坏了好几天了，晚上走路不安全', 0, NULL, NULL, '2026-09-19 10:00:00', '2026-09-19 10:00:00');

-- ============================================
-- 农产品表
-- ============================================
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '农产品ID',
  `name` VARCHAR(100) NOT NULL COMMENT '农产品名称',
  `price` DECIMAL(10,2) NOT NULL COMMENT '单价（元）',
  `stock` INT DEFAULT 0 COMMENT '库存',
  `description` TEXT COMMENT '产品描述',
  `img_url` VARCHAR(500) DEFAULT NULL COMMENT '图片地址',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='农产品表';

-- 插入测试农产品
INSERT INTO `product` (`name`, `price`, `stock`, `description`, `img_url`, `create_time`) VALUES
('临沧普洱茶', 128.00, 100, '云南临沧古树普洱茶，口感醇厚，回甘持久', NULL, '2026-09-10 10:00:00'),
('核桃', 25.00, 200, '高山核桃，皮薄仁满，营养丰富', NULL, '2026-09-11 10:00:00'),
('蜂蜜', 68.00, 50, '纯天然野生蜂蜜，无添加，绿色健康', NULL, '2026-09-12 10:00:00'),
('土鸡', 88.00, 30, '散养土鸡，肉质鲜美，营养丰富', NULL, '2026-09-13 10:00:00'),
('腊肉', 58.00, 80, '农家自制腊肉，烟熏工艺，香味浓郁', NULL, '2026-09-14 10:00:00');

-- ============================================
-- 完成
-- ============================================
SELECT '数据库初始化完成！' AS message;
