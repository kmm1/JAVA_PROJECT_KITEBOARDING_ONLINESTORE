package com.company;

import com.company.config.DaoConfig;
import com.company.dao.OrderDao;
import com.company.dao.ProductDao;
import com.company.dao.UserDao;
import com.company.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Kate M on 27.03.2018.
 */
@Transactional
public class Runner {

    private static final SessionFactory SESSION_FACTORY
            = new Configuration().configure().buildSessionFactory();

    public static void main(String[] args) {
        Session session = SESSION_FACTORY.openSession();


        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DaoConfig.class);
        OrderDao orderDao = context.getBean(OrderDao.class);
        UserDao userDao = context.getBean(UserDao.class);
        ProductDao productDao = context.getBean(ProductDao.class);


//        Orders orders = new Orders();
//        User user = userDao.findById(2L);
//        orders.setUsers(user);
//        orders.setStatus(EnumOrderStatus.NEW);
//        Product product = productDao.findById(1L);
//        ArrayList list = new ArrayList(Arrays.asList(product, product));
//        orders.setProducts(list);
//        orderDao.save(orders);

//        Long aLong = orderDao.countProductsInCart(4L);
//        //  System.out.println(aLong);
//        Double cartTotal = orderDao.findCartTotal(4L);
////        System.out.println(cartTotal);
//        Orders userCart = orderDao.findUserCart(4L);
////        System.out.println(userCart);
//        Map<Product, Integer> infoAboutProductsInCart = orderDao.findInfoAboutProductsInCart(4L);
//        infoAboutProductsInCart.entrySet().stream().forEach(System.out::println);
        int count = 3;
        Orders foundOrder = orderDao.findById(6L);
        Product foundProduct = productDao.findById(1L);
        List<Product> productList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            productList.add(foundProduct);
        }
        foundOrder.setProducts(productList);
        List<Product> products = foundOrder.getProducts();
        products.add(foundProduct);
        orderDao.update(foundOrder);
        // System.out.println(Arrays.asList(infoAboutProductsInCart));


//        User admin = saveUser(session, "admin", "km@gmail.com", "1", EnumRole.ADMIN, LocalDateTime.of(LocalDate.of(2018, Month.JANUARY, 01), LocalTime.NOON));
//        User user1 = saveUser(session, "user1", "user1@gmail.com", "1", EnumRole.USER, LocalDateTime.of(LocalDate.of(2018, Month.JANUARY, 01), LocalTime.NOON));
//        User user2 = saveUser(session, "user2", "user2@gmail.com", "1", EnumRole.USER, LocalDateTime.of(LocalDate.of(2018, Month.JANUARY, 01), LocalTime.NOON));
//
//        saveAddress(session, "Ivan", "Ivanov", "addressLineOne", "addressLineTwo",
//                "Minsk", "Minsk region", EnumCountry.BELARUS,
//                "220057", "+375296000000",
//                "", user1);
//        saveAddress(session, "Lena", "Shmudenkova", "addressLineOne", "addressLineTwo",
//                "Mogilev", "Mogilev region", EnumCountry.BELARUS,
//                "220000", "+375447000000",
//                "", user2);
//
//        Category categoryKite = saveCategory(session, EnumCategory.KITE);
//        Category categoryKiteboard = saveCategory(session, EnumCategory.KITEBOARD);
//
//        Product kite50Fifty = saveProduct(session, "50/FIFTY", "50/Fifty is the Superman of kites. It will do anything", 1119.00, 5, true, "50fifty_01.jpg", categoryKite);
//        Product kiteMrBig = saveProduct(session, "mr-big", "This kite has a huge surface with a flat", 999.00, 5, true, "mr-big_03.jpg", categoryKite);
//        Product kiteNo1 = saveProduct(session, "NO. 1", "Nobile No.1 is a professional kite designed for...", 599.00, 5, true, "no1_02.jpg", categoryKite);
//        Product kiteT5 = saveProduct(session, "T5", "The Nobile T5 Kite is simply one of the best kites", 899.00, 1, true, "t5_04.jpg", categoryKite);
//        Product kiteboardTrideNBL = saveProduct(session, "Tride-NBL", "The NBL is the classic, all-round Nobile board", 479.00, 2, true, "tride-nbl_01.jpg", categoryKiteboard);
//
//
//        Product product1 = session.find(Product.class, 1L);
//        Product product2 = session.find(Product.class, 2L);
//        Product product3 = session.find(Product.class, 3L);

//        saveOrder(session, user1, new ArrayList(Arrays.asList(product1, product1, product3)), LocalDateTime.of(LocalDate.of(2018, Month.JANUARY, 01), LocalTime.NOON), EnumOrderStatus.NEW);
//        saveOrder(session, user2, new ArrayList(Arrays.asList(product3)), LocalDateTime.of(LocalDate.of(2018, Month.JANUARY, 01), LocalTime.NOON), EnumOrderStatus.PAID);
//        saveOrder(session, user1, new ArrayList(Arrays.asList(product2)), LocalDateTime.of(LocalDate.of(2018, Month.JANUARY, 01), LocalTime.NOON), EnumOrderStatus.PAID);

//        saveReview(session, admin, kiteT5, "The best kite ever", LocalDateTime.of(LocalDate.of(2018, Month.JANUARY, 01), LocalTime.NOON));


        session.close();
        SESSION_FACTORY.close();
    }


    private static User saveUser(Session session, String name, String email, String password, EnumRole role, LocalDateTime localDateTime) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);
        user.setRegistrationDate(localDateTime);
        session.save(user);
        return user;
    }

    private static void saveAddress(Session session, String recieverFirstName, String recieverLastName, String addressLineOne, String addressLineTwo,
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

    private static Category saveCategory(Session session, EnumCategory enumCategory) {
        Category category = new Category(enumCategory);
        session.save(category);
        return category;
    }

    private static Product saveProduct(Session session, String name, String description, double price,
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


    private static Orders saveOrder(Session session, User user, ArrayList<Product> product, LocalDateTime localDateTime, EnumOrderStatus status) {
        Orders order = new Orders();
        order.setUsers(user);
        order.setStatus(status);
        Product product2 = session.find(Product.class, 1L);
        ArrayList list2 = new ArrayList(Arrays.asList(product2));
        order.setProducts(list2);
        session.save(order);
        return order;
    }

    private static Review saveReview(Session session, User user, Product product, String content, LocalDateTime localDateTime) {
        Review review = new Review();
        review.setContent(content);
        review.setProduct(product);
        review.setUser(user);
        review.setLocalDateTime(localDateTime);
        session.save(review);
        return review;
    }
}
