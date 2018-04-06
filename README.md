Kiteboarding SHOP - online coffee shop with a delivery support.
The were used such technologies as: Java 8, Spring Core, Spring MVC, Spring Security, TomCat, Maven, Hibernate, Rest.
Three-model application.
     Database layer:
Generic Dao for Crud operations. Access to persistent storage, such as to a database (DAO).
Hibernate ORM (Hibernate in short) was used as an object-relational mapping tool.
     Service layer:
Service layer encapsulates the application's business logic, controlling transactions and coor-dinating responses in the implementation of its operations.
     Web layer:
UI is developed includes Bootstrap.
Thymeleaf is used as a modern server-side Java template engine for  web environments.
Files are uploaded using MultipartResolver. 
Custom error page was created by using Global exception handler.
Autentificaution is creaded through Spring Security. Use login page to login. Use app is secure from CSRF.

The culture of testing the code through JUnit, HamCrest is imposed. The H2database is selected as the testing base.

Runs by deploying applications on Tomcat server. Database MySQL.
Run shop.sql file before using.
You can enter as an admin using login:admin, password:admin.
