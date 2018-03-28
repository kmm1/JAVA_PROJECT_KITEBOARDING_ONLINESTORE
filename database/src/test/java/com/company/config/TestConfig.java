package com.company.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by Kate M on 16.03.2018.
 */
@Configuration
@ComponentScan(basePackages = {"com.company.dao", "com.company.util"})
@Import(DaoConfig.class)
public class TestConfig {

}