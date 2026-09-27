package org.example.rural_demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.rural_demo.common.Result;
import org.example.rural_demo.entity.Product;
import org.example.rural_demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 分页查询农产品
     */
    @GetMapping("/page")
    public Result<Page<Product>> page(@RequestParam(defaultValue = "1") Long pageNum,
                                      @RequestParam(defaultValue = "10") Long pageSize,
                                      @RequestParam(required = false) String name) {
        Page<Product> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Product> wrapper = new LambdaQueryWrapper<>();
        if (name != null && !name.isEmpty()) {
            wrapper.like(Product::getName, name);
        }
        Page<Product> resPage = productService.page(page, wrapper);
        return Result.success(resPage);
    }

    /**
     * 查询全部农产品（小程序首页用）
     */
    @GetMapping("/list")
    public Result<List<Product>> list() {
        return Result.success(productService.list());
    }

    /**
     * 根据id查询详情
     */
    @GetMapping("/{id}")
    public Result<Product> getById(@PathVariable Long id) {
        return Result.success(productService.getById(id));
    }

    /**
     * 新增农产品（管理员）
     */
    @PostMapping
    public Result<Boolean> save(@RequestBody Product product) {
        return Result.success(productService.save(product));
    }

    /**
     * 修改农产品（管理员）
     */
    @PutMapping
    public Result<Boolean> update(@RequestBody Product product) {
        return Result.success(productService.updateById(product));
    }

    /**
     * 删除农产品（管理员）
     */
    @DeleteMapping("/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        return Result.success(productService.removeById(id));
    }
}