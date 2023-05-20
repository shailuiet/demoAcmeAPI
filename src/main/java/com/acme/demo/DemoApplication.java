package com.acme.demo;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@SpringBootApplication
@ComponentScan(basePackages = "com.acme.demo")
public class DemoApplication {

	public static void main(String[] args) {
	try {
		
		System.out.println("inside the app");
		SpringApplication.run(DemoApplication.class, args);
	}catch(Exception e) {
		e.printStackTrace();
	}
		
	}
	
}
