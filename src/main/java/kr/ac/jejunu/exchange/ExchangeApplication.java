package kr.ac.jejunu.exchange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ExchangeApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ExchangeApplication.class, args);
		System.out.println("Spring boot start");
	}

	@Override protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) { return builder.sources(ExchangeApplication.class); }
}
