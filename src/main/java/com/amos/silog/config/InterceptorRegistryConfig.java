package com.amos.silog.config;

import com.amos.silog.issue.controller.interceptor.AppInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@AllArgsConstructor
public class InterceptorRegistryConfig implements WebMvcConfigurer {
    private AppInterceptor appInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(appInterceptor)
                .addPathPatterns("/api/v1/**")
                .excludePathPatterns("/api/v1/issues//ai/suggest");
    }


}
