package com.company.dto;

import com.company.entity.EnumRole;
import com.company.entity.Orders;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Created by Kate M on 26.03.2018.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class UserDto {

    private Long id;
    private String name;
    private EnumRole role;
    private Long numberOfProductsInCart;
    private Orders currentOrder;
    private Double cartTotal;


}
