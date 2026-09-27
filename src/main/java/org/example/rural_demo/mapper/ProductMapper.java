package org.example.rural_demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.rural_demo.entity.Product;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {
}