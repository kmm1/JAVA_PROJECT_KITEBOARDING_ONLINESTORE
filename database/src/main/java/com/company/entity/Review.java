package com.company.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by Kate M on 05.03.2018.
 */

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "review")
@ToString(callSuper = true)
public class Review extends BaseEntity {
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    @Getter
    @Setter
    @Column(name = "content", nullable = false)
    private String content;
    @Getter
    @Setter
    @Column(name = "date")
    private LocalDateTime localDateTime;

    public Review(User user, Product product, String content) {
        this.user = user;
        this.product = product;
        this.content = content;
    }

}
