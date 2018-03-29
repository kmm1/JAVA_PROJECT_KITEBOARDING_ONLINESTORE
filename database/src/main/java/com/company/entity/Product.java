package com.company.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Kate M on 05.03.2018.
 */
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
@ToString(callSuper = true, exclude = {"reviews", "orders"})
public class Product extends BaseEntity implements Comparable<Product> { //<--dao TreeSet//
    @Getter
    @Setter
    @Column(name = "name", nullable = false, unique = true)
    @NotBlank(message = "Please, enter the Product Name")
    private String name;

    @Getter
    @Setter
    @Column(name = "description", nullable = false)
    @NotBlank(message = "Please, enter the Description for Product")
    private String description;

    @Getter
    @Setter
    @Column(name = "price", nullable = false)
    @NotNull(message = "Please, enter the Price for Product")
    private Double price;

    @Getter
    @Setter
    @Column(name = "amount_available", nullable = false)
    @NotNull(message = "Please, enter Valid Amount for Product")
    @Min(value = 0, message = "Please, enter Amount for Product")
    private int amountAvailable;

    @Getter
    @Setter
    @Column(name = "availability")
    private boolean availability;

    @Getter
    @Setter
    @Column(name = "image_url")
    private String imageURL;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Getter
    @Setter
    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private Set<Review> reviews = new HashSet();

    @Getter
    @Setter
    @ManyToMany(mappedBy = "products")
    @JsonIgnore
    private Set<Orders> orders = new HashSet<>();

    @Transient
    @Getter
    @Setter
    private MultipartFile multipartFile;

    public Product(String name, String description, double price, int amountAvailable, boolean availability, String imageURL, Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.amountAvailable = amountAvailable;
        this.availability = availability;
        this.imageURL = imageURL;
        this.category = category;
    }


    @Override
    public int compareTo(Product p) {
        return this.getName().compareTo(p.getName());
    }
}
