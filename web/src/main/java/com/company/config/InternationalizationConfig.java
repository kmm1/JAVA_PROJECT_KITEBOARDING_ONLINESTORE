package com.company.config;

//@Configuration
//public class InternationalizationConfig extends WebMvcConfigurerAdapter {
//
//    @Bean
//    public MessageSource messageSource() {
//        ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
//        resourceBundleMessageSource.setBasename("translations");
//        resourceBundleMessageSource.setDefaultEncoding("UTF-8");
//        return resourceBundleMessageSource;
//    }
//
//    @Bean
//    public SessionLocaleResolver localeResolver() {
//        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
//        sessionLocaleResolver.setDefaultLocale(Locale.ENGLISH);
//        return sessionLocaleResolver;
//    }
//
//    @Bean
//    public LocaleChangeInterceptor localeChangeInterceptor() {
//        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
//        localeChangeInterceptor.setParamName("language");
//        return localeChangeInterceptor;
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(localeChangeInterceptor());
//    }
//}
