package com.app.runadoc;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootRunadocApplication {

	public static void main(String[] args) throws JAXBException, IOException {
		SpringApplication.run(SpringBootRunadocApplication.class, args);
	}
}
