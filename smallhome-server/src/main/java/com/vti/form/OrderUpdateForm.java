package com.vti.form;


import com.vti.validation.AccountExistsById;
import com.vti.validation.OrderExistsById;
import com.vti.validation.ProductExistsById;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@NoArgsConstructor
public class OrderUpdateForm {

    @NotNull(message = "{OrderForm.id.NotNull}")
    @OrderExistsById
    private Integer id;

    @NotNull(message = "{OrderForm.count.NotNull}")
    @PositiveOrZero(message = "{OrderForm.count.PositiveOrZero}")
    private Double count;

    @NotNull(message = "{OrderForm.totalPrice.NotNull}")
    @PositiveOrZero(message = "{OrderForm.totalPrice.PositiveOrZero}")
    private Double totalPrice;

    @NotNull(message = "{OrderForm.status.NotNull}")
    private Boolean status;

    @NotNull(message = "{AccountForm.id.NotNull}")
    @AccountExistsById
    private Integer accountId;

    @NotNull(message = "{ProductForm.id.NotNull}")
    @ProductExistsById
    private Integer productId;
}
