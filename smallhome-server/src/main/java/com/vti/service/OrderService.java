package com.vti.service;

import com.vti.entity.Order;
import com.vti.form.OrderCreateForm;
import com.vti.form.OrderFilterForm;
import com.vti.form.OrderUpdateForm;
import com.vti.repository.IOrderRepository;
import com.vti.specification.OrderSpecification;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private IOrderRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Page<Order> findAll(Pageable pageable, OrderFilterForm form) {
        return repository.findAll(OrderSpecification.buildWhere(form), pageable);
    }


    @Override
    public Order findById(int id) {
    return repository.findById(id).orElse(null);}

    @Override
    public void create(OrderCreateForm form) {
        TypeMap<OrderCreateForm, Order> typeMap = mapper.getTypeMap(OrderCreateForm.class,Order.class);
                if(typeMap == null) {
                    mapper.addMappings(new PropertyMap<OrderCreateForm, Order>() {
                    @Override
                    protected void configure() {
                        skip(destination.getId());
                    }
                });}
               repository.save(mapper.map(form,Order.class));
    }

    @Override
    public void update(OrderUpdateForm form) {
        repository.save(mapper.map(form, Order.class));
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAll(List<Integer> ids) {
        repository.deleteAllById(ids);
    }


}


