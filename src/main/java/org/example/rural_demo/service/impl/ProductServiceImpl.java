package org.example.rural_demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.rural_demo.entity.Product;
import org.example.rural_demo.mapper.ProductMapper;
import org.example.rural_demo.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
}