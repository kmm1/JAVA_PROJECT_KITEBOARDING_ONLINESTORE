package com.company.entity;

import lombok.*;

import javax.persistence.*;

/**
 * Created by Kate M on 05.03.2018.
 */
@Entity
@Table(name = "category")
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Category extends BaseEntity {

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private EnumCategory enumCategory;

}
