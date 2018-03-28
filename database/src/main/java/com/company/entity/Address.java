package com.company.entity;

import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by Kate M on 06.03.2018.
 */
@Entity
@Table(name = "address")
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Address extends BaseEntity {

    @Getter
    @Setter
    @Column(name = "recieverName")
    @NotBlank(message = "Please, Enter valid Name")
    private String recieverName;

    @Getter
    @Setter
    @Column(name = "recieverLastName")
    @NotBlank(message = "Please, Enter valid Lastname")
    private String recieverLastName;

    @Getter
    @Setter
    @Column(name = "addressLineOne")
    @NotBlank(message = "Please, Enter valid Address")
    private String addressLineOne;

    @Getter
    @Setter
    @Column(name = "addressLineTwo")
    private String addressLineTwo;

    @Getter
    @Setter
    @Column(name = "city")
    @NotBlank(message = "Please, Enter valid City")
    private String city;

    @Getter
    @Setter
    @Column(name = "state")
    @NotBlank(message = "Please, Enter valid State")
    private String state;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "country")
    @NotNull(message = "Please, Enter valid Country")
    private EnumCountry country;

    @Getter
    @Setter
    @Column(name = "zip")
    @NotBlank(message = "Please, Enter valid Zip")
    @Pattern(regexp = "[0-9]+")
    private String zip;

    @Getter
    @Setter
    @Column(name = "telephone")
    @NotBlank(message = "Please, Enter valid Phone Number")
    @Pattern(regexp = "[+)(0-9]+")
    private String telephone;

    @Getter
    @Setter
    @Column(name = "additionalInfo")
    private String additionalInfo;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
