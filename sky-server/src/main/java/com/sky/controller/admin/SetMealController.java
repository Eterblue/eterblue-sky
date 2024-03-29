package com.sky.controller.admin;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetMealServie;
import com.sky.vo.SetmealVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/setmeal")
@Slf4j
@Api(tags = "套餐管理")
public class SetMealController {

    @Autowired
    private SetMealServie setMealServie;

    @PostMapping
    @ApiOperation("新增套餐")
    @CacheEvict(cacheNames = "setmealCache",key = "#setmealDTO.categoryId")
    public Result save(@RequestBody SetmealDTO setmealDTO){

        log.info("新增套餐:{}",setmealDTO);
        setMealServie.save(setmealDTO);

        return Result.success();
    }

    @GetMapping("/page")
    @ApiOperation("套餐分页查询")
    public Result<PageResult> page( SetmealPageQueryDTO setmealPageQueryDTO){

        log.info("套餐分页查询:{}",setmealPageQueryDTO);

        PageResult pageResult=setMealServie.page(setmealPageQueryDTO);

        return Result.success(pageResult);
    }

    @DeleteMapping
    @ApiOperation("删除套餐")
    @CacheEvict(cacheNames = "setmealCache",allEntries = true)
    public Result delete(@RequestParam List<Long> ids){

        log.info("(批量)删除套餐:{}",ids);
        setMealServie.delete(ids);

        return Result.success();
    }

    @PutMapping
    @ApiOperation("修改套餐")
    @CacheEvict(cacheNames = "setmealCache",allEntries = true)
    public Result update(@RequestBody SetmealDTO setmealDTO){

        log.info("修改套餐:{}",setmealDTO);
        setMealServie.update(setmealDTO);

        return Result.success();
    }

    @GetMapping("/{id}")
    @ApiOperation("根据id查询")
    public Result<SetmealVO> getById(@PathVariable Long id){

        log.info("查询id为：{}",id);
        SetmealVO setmealVO=setMealServie.getById(id);

        return Result.success(setmealVO);
    }

    @PostMapping("/status/{status}")
    @ApiOperation("套餐售卖状态")
    @CacheEvict(cacheNames = "setmealCache",allEntries = true)
    public Result startORend(@PathVariable Integer status,Long id){

        log.info("状态修改为：{},id:{}",status,id);
        setMealServie.startORend(status,id);

        return Result.success();
    }
}
