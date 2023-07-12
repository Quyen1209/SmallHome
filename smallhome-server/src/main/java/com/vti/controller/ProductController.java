package com.vti.controller;



import com.vti.dto.ProductDTO;
import com.vti.entity.Product;
import com.vti.form.ProductCreateForm;
import com.vti.form.ProductFilterForm;
import com.vti.form.ProductUpdateForm;
import com.vti.service.IProductService;
import com.vti.validation.ProductExistsById;
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
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private IProductService service;
    @Autowired
    ModelMapper mapper;

    @GetMapping
    public Page<ProductDTO> findAll(Pageable pageable, ProductFilterForm form) {
        Page<Product> page = service.findAll(pageable, form);
        List<ProductDTO> productDTOS = mapper.map(
                page.getContent(),
                new TypeToken<List<ProductDTO>>() {
                }.getType()
        );
        //
        for (ProductDTO productDTO : productDTOS) {
            // add link cho category
            productDTO.getCategory().add(
                    WebMvcLinkBuilder.linkTo(
                            WebMvcLinkBuilder.methodOn(CategoryController.class)
                                    .findById(productDTO.getCategory().getId())
                    ).withSelfRel()
            );
            // add link cho product
            productDTO.add(
                    WebMvcLinkBuilder.linkTo(
                            WebMvcLinkBuilder
                                    .methodOn(ProductController.class)
                                    .findById(productDTO.getId())
                    ).withSelfRel()
            );

            // add link cho order
            List<ProductDTO.OrderDTO> orderDTOS = productDTO.getOrders();
            for(ProductDTO.OrderDTO orderDTO: orderDTOS) {
                orderDTO.add(
                        WebMvcLinkBuilder.linkTo(
                                WebMvcLinkBuilder.methodOn(OrderController.class)
                                        .findById(orderDTO.getId())
                        ).withSelfRel()
                );
            }

        }
        //

        return new PageImpl<>(productDTOS, pageable, page.getTotalElements());
    }

    @GetMapping("/{id}")
    public ProductDTO findById(@PathVariable("id") @ProductExistsById int id) {
        return mapper.map(service.findById(id), ProductDTO.class);
    }

    @PostMapping
    public void create(@RequestBody @Valid ProductCreateForm form) {
        service.create(form);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") int id, @RequestBody @Valid ProductUpdateForm form) {
        form.setId(id);
        service.update(form);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") @ProductExistsById int id) {
        service.deleteById(id);
    }

    @DeleteMapping
    public void deleteAll(@RequestBody List<@ProductExistsById Integer> ids) {
        service.deleteAll(ids);
    }
}
