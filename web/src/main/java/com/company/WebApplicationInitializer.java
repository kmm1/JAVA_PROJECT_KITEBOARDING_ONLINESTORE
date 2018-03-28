package com.company;

import com.company.config.SecurityConfig;
import com.company.config.ServiceConfig;
import com.company.config.WebConfig;
import com.company.filter.EncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

public class WebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    private static final String UPLOAD_DIRECTORY = (System.getProperty("java.io.tmpdir")); // Temporary location where files will be stored
    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024; // 5MB : Max file size.
    private static final long MAX_REQUEST_SIZE = 5 * 1024 * 1024 * 20; // 20MB : Total request size containing Multi part.
    private static final int FILE_SIZE_THRESHOLD = 0; // Size threshold after which files will be written to disk

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{ServiceConfig.class, SecurityConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        return new Filter[]{new EncodingFilter()};
    }

    //setInitParameter -- for customer error page
    //setMultipartConfig --for uploading file
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setInitParameter("throwExceptionIfNoHandlerFound", "true"); // -> true
        registration.setMultipartConfig(new MultipartConfigElement(UPLOAD_DIRECTORY, MAX_FILE_SIZE, MAX_REQUEST_SIZE, FILE_SIZE_THRESHOLD));
    }
}

