package com.company.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kate M on 05.03.2018.
 */
@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true, exclude = "reviews")
public class Orders extends BaseEntity {

    @Getter
    @Setter
    @Column(name = "order_date")
    private LocalDateTime orderDate;
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User users;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @Getter
    @Setter
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EnumOrderStatus status;

    @Getter
    @Setter
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "product_orders",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products = new ArrayList<>();

    public Orders(User users, List<Product> products) {
        this.users = users;
        this.products = products;
    }

    public Orders(LocalDateTime orderDate, User users, EnumOrderStatus status, List<Product> products) {
        this.orderDate = orderDate;
        this.users = users;
        this.status = status;
        this.products = products;
    }


}
