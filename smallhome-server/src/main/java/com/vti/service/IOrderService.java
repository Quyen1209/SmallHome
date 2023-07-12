package com.vti.service;

import com.vti.entity.Order;
import com.vti.form.OrderCreateForm;
import com.vti.form.OrderFilterForm;
import com.vti.form.OrderUpdateForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IOrderService {
    Page<Order> findAll(Pageable pageable, OrderFilterForm form);

    Order findById(int id);

    void create(OrderCreateForm form);

    void update(OrderUpdateForm form);

    void deleteById(int id);

    void deleteAll(List<Integer> ids);
}
