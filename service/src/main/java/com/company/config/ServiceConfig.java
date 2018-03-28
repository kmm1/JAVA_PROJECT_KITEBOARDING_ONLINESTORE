package com.company.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"com.company.service"})
@Import(value = {DaoConfig.class})
public class ServiceConfig {

}