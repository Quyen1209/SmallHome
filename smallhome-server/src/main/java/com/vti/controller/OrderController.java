package com.vti.controller;

import com.vti.dto.OrderDTO;
import com.vti.dto.ProductDTO;
import com.vti.entity.Order;
import com.vti.entity.Product;
import com.vti.form.*;
import com.vti.service.IOrderService;
import com.vti.validation.OrderExistsById;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@Validated
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    @Autowired
    private IOrderService service;
    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public Page<OrderDTO> findAll(Pageable pageable, OrderFilterForm form) {
        Page<Order> page = service.findAll(pageable, form);
        List<OrderDTO> orderDTOS = mapper.map(
                page.getContent(),
                new TypeToken<List<OrderDTO>>() {
                }.getType()
        );
        for (OrderDTO orderDTO : orderDTOS) {
            // add link cho account
            orderDTO.getAccount().add(
                    WebMvcLinkBuilder.linkTo(
                            WebMvcLinkBuilder.methodOn(AccountController.class)
                                    .findById(orderDTO.getAccount().getId())
                    ).withSelfRel()
            );
            // add link cho product
            orderDTO.getProduct().add(
                    WebMvcLinkBuilder.linkTo(
                            WebMvcLinkBuilder
                                    .methodOn(ProductController.class)
                                    .findById(orderDTO.getProduct().getId())
                    ).withSelfRel()
            );
            // add link cho order
            orderDTO.add(
                        WebMvcLinkBuilder.linkTo(
                                WebMvcLinkBuilder.methodOn(OrderController.class)
                                        .findById(orderDTO.getId())
                        ).withSelfRel()
                );
        }
        return new PageImpl<>(orderDTOS, pageable, page.getTotalElements());
    }

    @GetMapping("/{id}")
    public OrderDTO findById(@PathVariable("id") @OrderExistsById int id) {
        return mapper.map(service.findById(id), OrderDTO.class);
    }

    @PostMapping
    public void create(@RequestBody @Valid OrderCreateForm form) {
        service.create(form);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") @OrderExistsById int id, @RequestBody @Valid OrderUpdateForm form) {
        form.setId(id);
        service.update(form);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") @OrderExistsById  int id) {
        service.deleteById(id);
    }

    @DeleteMapping
    public void deleteAll(@RequestBody List<@OrderExistsById Integer> ids) {
        service.deleteAll(ids);
    }
}
