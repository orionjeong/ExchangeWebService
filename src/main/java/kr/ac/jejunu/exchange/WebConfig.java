package kr.ac.jejunu.exchange;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;

@Configuration
public class WebConfig implements WebMvcConfigurer{
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        for(HttpMessageConverter<?> messageConverter : restTemplate.getMessageConverters()) {
            if(messageConverter instanceof AllEncompassingFormHttpMessageConverter) {
                ((AllEncompassingFormHttpMessageConverter) messageConverter).setCharset(Charset.forName("UTF-8"));
                ((AllEncompassingFormHttpMessageConverter) messageConverter).setMultipartCharset(Charset.forName("UTF-8"));
            }
        }
        return restTemplate;
    }
}