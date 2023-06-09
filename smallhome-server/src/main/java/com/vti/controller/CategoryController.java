package com.vti.controller;


import com.vti.dto.CategoryDTO;
import com.vti.entity.Category;
import com.vti.form.CategoryCreateForm;
import com.vti.form.CategoryFilterForm;
import com.vti.form.CategoryUpdateForm;
import com.vti.service.ICategoryService;
import com.vti.validation.CategoryExistsById;
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
@RequestMapping("api/v1/categories")
public class CategoryController {
    @Autowired
    private ICategoryService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public Page<CategoryDTO> findAll(Pageable pageable, CategoryFilterForm form) {
        Page<Category> page = service.findAll(pageable,form);
        List<CategoryDTO> dtos = mapper.map(
                page.getContent(),
                new TypeToken<List<CategoryDTO>>() {}.getType()
        );

        for(CategoryDTO categoryDTO : dtos) {
            // add link cho products
            List<CategoryDTO.ProductDTO> productDTOS = categoryDTO.getProducts();
            for(CategoryDTO.ProductDTO productDTO: productDTOS) {
                productDTO.add(
                        WebMvcLinkBuilder.linkTo(
                                WebMvcLinkBuilder.methodOn(ProductController.class)
                                        .findById(productDTO.getId())
                        ).withSelfRel()
                );
            }
            // add link cho category
            categoryDTO.add(
                    WebMvcLinkBuilder.linkTo(
                            WebMvcLinkBuilder.methodOn(CategoryController.class)
                                    .findById(categoryDTO.getId())
                    ).withSelfRel()
            );
        }
        return new PageImpl<>(dtos, pageable, page.getTotalElements());
    }

    @GetMapping("/{id}")
    public CategoryDTO findById(@PathVariable("id") @CategoryExistsById int id) {
        Category category = service.findById(id);
        if (category == null) {
            return null;
        }
        return mapper.map(category, CategoryDTO.class).add(
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(CategoryController.class).findById(id)
                ).withSelfRel()
        );
    }
    @PostMapping
    public  void create(@RequestBody @Valid CategoryCreateForm form) {
        service.create(form);
    }
    @PutMapping("/{id}")
    public void update(@PathVariable("id") @CategoryExistsById int id, @RequestBody @Valid CategoryUpdateForm form) {
        form.setId(id);
        service.update(form);
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") @CategoryExistsById int id) {
        service.deleteById(id);
    }

    @DeleteMapping
    public void deleteAll(@RequestBody List<@CategoryExistsById Integer> ids) {
        service.deleteAll(ids);
    }
}
