package com.company.util;

import com.company.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Kate M on 05.03.2018.
 */
@Component
public final class TestDataImporterImpl implements TestDataImporter {

    @Autowired
    private SessionFactory sessionFactory;

    public void importTestData() {
        Session session = sessionFactory.getCurrentSession();
        User admin = saveUser(session, "admin", "km@gmail.com", "1", EnumRole.ADMIN, LocalDateTime.of(LocalDate.of(2018, Month.JANUARY, 01), LocalTime.NOON));
        User user1 = saveUser(session, "user1", "user1@gmail.com", "1", EnumRole.USER, LocalDateTime.of(LocalDate.of(2018, Month.JANUARY, 01), LocalTime.NOON));
        User user2 = saveUser(session, "user2", "user2@gmail.com", "1", EnumRole.USER, LocalDateTime.of(LocalDate.of(2018, Month.JANUARY, 01), LocalTime.NOON));

        saveAddress(session, "Ivan", "Ivanov", "addressLineOne", "addressLineTwo",
                "Minsk", "Minsk region", EnumCountry.BELARUS,
                "220057", "+375296000000",
                "", user1);
        saveAddress(session, "Lena", "Shmudenkova", "addressLineOne", "addressLineTwo",
                "Mogilev", "Mogilev region", EnumCountry.BELARUS,
                "220000", "+375447000000",
                "", user2);

        Category categoryKite = saveCategory(session, EnumCategory.KITE);
        Category categoryKiteboard = saveCategory(session, EnumCategory.KITEBOARD);
        Category categorySnowboard = saveCategory(session, EnumCategory.SNOWBOARD);
        Category categoryAccessory = saveCategory(session, EnumCategory.ACCESSORY);

        Product kite50Fifty = saveProduct(session, "50/FIFTY", "50/Fifty is the Superman of kites. It will do anything", 1119.00, 5, true, "50fifty_01.jpg", categoryKite);
        Product kiteMrBig = saveProduct(session, "mr-big", "This kite has a huge surface with a flat", 999.00, 5, true, "mr-big_03.jpg", categoryKite);
        Product kiteNo1 = saveProduct(session, "NO. 1", "Nobile No.1 is a professional kite designed for...", 599.00, 5, true, "no1_02.jpg", categoryKite);
        Product kiteT5 = saveProduct(session, "T5", "The Nobile T5 Kite is simply one of the best kites", 899.00, 1, true, "t5_04.jpg", categoryKite);
        Product kiteboardTrideNBL = saveProduct(session, "Tride-NBL", "The NBL is the classic, all-round Nobile board", 479.00, 2, true, "tride-nbl_01.jpg", categoryKiteboard);

        saveOrder(session, user1, new ArrayList(Arrays.asList(kite50Fifty, kite50Fifty, kiteboardTrideNBL)), LocalDateTime.of(LocalDate.of(2018, Month.JANUARY, 01), LocalTime.NOON), EnumOrderStatus.NEW);
        saveOrder(session, user2, new ArrayList(Arrays.asList(kiteMrBig)), LocalDateTime.of(LocalDate.of(2018, Month.JANUARY, 01), LocalTime.NOON), EnumOrderStatus.PAID);
        saveOrder(session, user1, new ArrayList(Arrays.asList(kite50Fifty)), LocalDateTime.of(LocalDate.of(2018, Month.JANUARY, 01), LocalTime.NOON), EnumOrderStatus.PAID);

        saveReview(session, admin, kiteT5, "The best kite ever", LocalDateTime.of(LocalDate.of(2018, Month.JANUARY, 01), LocalTime.NOON));

    }

    private User saveUser(Session session, String name, String email, String password, EnumRole role, LocalDateTime localDateTime) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);
        user.setRegistrationDate(localDateTime);
        session.save(user);
        return user;
    }

    private void saveAddress(Session session, String recieverFirstName, String recieverLastName, String addressLineOne, String addressLineTwo,
                             String city, String state, EnumCountry country,
                             String zip, String telephone,
                             String additionalInfo, User user) {
        Address address = new Address();
        address.setRecieverName(recieverFirstName);
        address.setRecieverLastName(recieverLastName);
        address.setAddressLineOne("AddressLineOne");
        address.setAddressLineTwo("AddressLineTwo");
        address.setCity("Minsk");
        address.setState("Minsk region");
        address.setCountry(EnumCountry.BELARUS);
        address.setZip("220057");
        address.setTelephone("+375296000000");
        address.setAdditionalInfo(additionalInfo);
        address.setUser(user);
        session.save(address);
    }

    private Category saveCategory(Session session, EnumCategory enumCategory) {
        Category category = new Category(enumCategory);
        session.save(category);
        return category;
    }

    private Product saveProduct(Session session, String name, String description, double price,
                                int amount, boolean availability, String imageURL, Category category) {
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setAmountAvailable(amount);
        product.setAvailability(availability);
        product.setImageURL(imageURL);
        product.setCategory(category);
        session.save(product);
        return product;
    }


    private Orders saveOrder(Session session, User user, ArrayList<Product> product, LocalDateTime localDateTime, EnumOrderStatus status) {
        Orders order = new Orders(localDateTime, user, status, product);
        session.save(order);
        return order;
    }

    private Review saveReview(Session session, User user, Product product, String content, LocalDateTime localDateTime) {
        Review review = new Review();
        review.setContent(content);
        review.setProduct(product);
        review.setUser(user);
        review.setLocalDateTime(localDateTime);
        session.save(review);
        return review;
    }


}
