package com.sky.mapper;
import com.sky.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface OrderMapper {
    void insert(Orders orders);

    List<Orders> getStatusAndOrderTime(Integer status, LocalDateTime time);

    void update(Orders order);
}
