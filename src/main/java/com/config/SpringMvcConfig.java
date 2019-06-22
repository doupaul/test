package com.config;

import com.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc   //等同于 mvc:annotation-driven 在XML中
@ComponentScan("com.controller")  //等同于 context:component-scan base-package="..."     ，出错是没加controller
public class SpringMvcConfig extends WebMvcConfigurerAdapter {

    @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setViewClass(JstlView.class);
        internalResourceViewResolver.setPrefix("/WEB-INF/jsp/");
        internalResourceViewResolver.setSuffix(".jsp");
        return internalResourceViewResolver;
    }

    //文件上传，bean必须写name属性且必须为multipartResolver，不然取不到文件对象，别问我为什么，我也唔知
     @Bean(name="multipartResolver")
     protected CommonsMultipartResolver MultipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
//     multipartResolver.setUploadTempDir(new FileSystemResource("/tmp"));//可不设置
        multipartResolver.setMaxUploadSize(5242440);//5M
        multipartResolver.setDefaultEncoding("UTF-8");
        return multipartResolver;
     }

     //静态资源
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/static/**").addResourceLocations("/WEB-INF/static/");
    }

    //拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor());
    }

    //默认请求处理器
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer){
        configurer.enable();
    }
}
