package com.company.entity;

import lombok.*;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Kate M on 05.03.2018.
 */
@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true, exclude = {"profile", "orders", "reviews"})

public class User extends BaseEntity {

    @Getter
    @Setter
    @Column(name = "name", nullable = false, unique = true)
    @NotBlank(message = "Please, enter valid Name")
    @Pattern(regexp = "[A-Za-z.0-9]+", message = "The Name must have only Letters and Numbers")
    @Length(min = 4, max = 15, message = "Name should be greater than 4 and less that 15")
    private String name;

    @Getter
    @Setter
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private EnumRole role;

    //this is for spring security
    @Getter
    @Setter
    @Column(name = "enabled")
    private Boolean enabled;

    @Getter
    @Setter
    @Column(name = "email", nullable = false, unique = true)
    @NotBlank(message = "Please, enter valid Email")
    @Email(message = "Please, enter valid Email")
    private String email;

    @Getter
    @Setter
    @Column(name = "password", nullable = false)
    @NotBlank(message = "Please, enter valid Password")
    // @Length(min = 4, max = 15, message = "The password length must be between 4 and 15")
    //@Pattern(regexp = "[A-Za-z0-9]+", message = "The password must have only Letters and Numbers")
    private String password;

    @Getter
    @Setter
    @Transient
    private String confirmPassword;

    @Getter
    @Setter
    @Column(name = "registration_date")
    private LocalDateTime registrationDate;

    @Getter
    @Setter
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Review> reviews = new HashSet<>();

    @Getter
    @Setter
    @OneToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private List<Orders> orders = new ArrayList<>();

    public User(String name, EnumRole role, String email, String password) {
        this.name = name;
        this.role = role;
        this.email = email;
        this.password = password;
    }

}
