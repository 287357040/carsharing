package com.carsharing.server.conf;

import com.carsharing.server.interceptor.QqInterceptor;
import com.carsharing.server.interceptor.QqWebInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class WebConfigurer extends WebMvcConfigurerAdapter {

    //private final Logger log = LoggerFactory.getLogger(WebConfigurer.class);

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 暂时不提供admin接口 不需要做拦截
//        registry.addInterceptor(new QqInterceptor())
//                .addPathPatterns("/admin/*/**");
        registry.addInterceptor(new QqWebInterceptor())
                .addPathPatterns("/front/*/**").
                excludePathPatterns("/front/user/loginByCode");
        super.addInterceptors(registry);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
//                .allowCredentials(false).maxAge(3600)
                .allowedMethods("GET", "HEAD", "POST", "PUT", "PATCH", "DELETE", "OPTIONS", "TRACE");
    }

    @Bean
   public ThreadPoolTaskExecutor threadPoolTaskExecutor(){
        ThreadPoolTaskExecutor  threadPoolTaskExecutor=new ThreadPoolTaskExecutor();
        //核心线程数
        threadPoolTaskExecutor.setCorePoolSize(10);
        //最大线程数
        threadPoolTaskExecutor.setMaxPoolSize(15);
        //队列最大长度
        threadPoolTaskExecutor.setQueueCapacity(20);
        //线程池维护线程所允许的空闲时间
        threadPoolTaskExecutor.setKeepAliveSeconds(300);
        //主线程直接执行该任务，执行完之后尝试添加下一个任务到线程池中，可以有效降低向线程池内添加任务的速度
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return  threadPoolTaskExecutor;
    }
}
