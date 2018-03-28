package com.company.entity;

import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by Kate M on 27.03.2018.
 */
@NoArgsConstructor
@Entity
@Table(name = "product_orders")
@ToString(callSuper = true)
public class ProductOrders extends BaseEntity {

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private Orders order;
}
