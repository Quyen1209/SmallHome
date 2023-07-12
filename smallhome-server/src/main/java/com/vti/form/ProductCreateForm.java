package com.vti.form;


import com.vti.validation.CategoryExistsById;
import com.vti.validation.ProductNotExistsByName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;


@Getter
@Setter
@NoArgsConstructor
public class ProductCreateForm {
    @NotBlank(message = "{ProductForm.name.NotBlank}")
    @Length(max = 50, message = "{ProductForm.name.Length}")
    @ProductNotExistsByName
    private String name;

    @NotBlank(message = "{ProductForm.description.NotBlank}")
    @Length(max = 1023, message = "{ProductForm.description.Length}")
    private String description;

    @NotBlank(message = "{ProductForm.thumbnailUrl.NotBlank}")
    @Length(max = 255, message = "{ProductForm.thumbnailUrl.Length}")
    private String thumbnailUrl;

    @NotNull(message = "{ProductForm.quantity.NotNull}")
    @PositiveOrZero(message = "{ProductForm.quantity.PositiveOrZero}")
    private Double quantity;

    @NotNull(message = "{ProductForm.soldQuantity.NotNull}")
    @PositiveOrZero(message = "{ProductForm.soldQuantity.PositiveOrZero}")
    private Double soldQuantity;

    @NotNull(message = "{ProductForm.price.NotNull}")
    @PositiveOrZero(message = "{ProductForm.price.PositiveOrZero}")
    private Double price;

    @NotNull(message = "{ProductForm.salePrice.NotNull}")
    @PositiveOrZero(message = "{ProductForm.salePrice.PositiveOrZero}")
    private Double salePrice;

    @NotNull(message = "{ProductForm.status.NotNull}")
    private Boolean status = Boolean.TRUE;

    @NotNull(message = "{CategoryForm.id.NotNull}")
    @CategoryExistsById
    private Integer categoryId;
}
